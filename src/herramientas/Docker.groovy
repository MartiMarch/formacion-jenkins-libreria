package herramientas

class Docker {
    private def steps

    Docker(steps){
        this.steps = steps
    }

    def prune(){
        steps.sh "docker image prune -a -f"
    }

    def commonImage(String type){
        List types = ["java"]
        switch(type){
            case "java":
                def jarPath = steps.sh(returnStdout: true, script: "chdir=${steps.WORKSPACE} find -name *.jar*").trim()
                def jarName = jarPath.split("/")
                jarName = jarName[jarName.size() - 1].trim()
                steps.sh "touch Dockerfile"
                steps.sh "echo -e 'FROM openjdk' >> Dockerfile"
                steps.sh "echo -e 'COPY ${jarPath} ${jarName}' >> Dockerfile"
                steps.sh "echo -e 'CMD java -jar ${jarName}' >> Dockerfile"
                break;
            default:
                String error = "[ERROR]: Can't create Dockerfile, invalid type.\n"
                error += "Available image types:\n"
                types.each {it -> error += "-" + it }
                steps.echo "${error}"
                break;
        }
    }

    def printDockerfile(){
        def dockerfileContent = steps.sh (returnStdout: true, script: "cat Dockerfile")
        steps.echo "[DEBUG] Dockerfile content:\n${dockerfileContent}"
    }

    def build(type){
        List types = ["java"]
        switch(type) {
            case "java":
                def jarPath = steps.sh(returnStdout: true, script: "chdir=${steps.WORKSPACE} find -name *.jar*").trim()
                def jarName = jarPath.split("/")
                jarName = jarName[jarName.size() - 1].trim()
                jarName = jarName.split("-")
                def dockerName = jarName[0].toLowerCase() + ":" + jarName[1].toLowerCase()
                steps.sh "docker image build -f Dockerfile -t ${dockerName} ."
                break;
            default:
                String error = "[ERROR]: Can't create Dockerfile, invalid type.\n"
                error += "Available image types:\n"
                types.each {it -> error += "-" + it }
                steps.echo "${error}"
                break;
        }
    }
}

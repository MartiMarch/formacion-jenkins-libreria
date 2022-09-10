package herramientas

class Helm {
    def steps

    Helm(steps){
        this.steps = steps
    }

    def template(){
        steps.sh "helm template -f values.yaml ."
    }

    def lint(){
        def out = steps.sh(returnStdout: true, script: "helm lint -f values.yaml .")
        steps.echo "${out}"
        if(!out.contains("0 chart(s) failed")){
            steps.currentBuild.result = "FAILURE"
            throw new Exception("Linted helm chart failed!!!")
        }
    }

    def deploy(){
        steps.withCredentials([steps.file(credentialsId: 'KUBECONFIG', variable: 'KUBECONFIG')]) {
            steps.sh """
                set +x
                helm install formacion-jenkins . --kubeconfig ${steps.KUBECONFIG}
                set -x
            """
        }
    }
}

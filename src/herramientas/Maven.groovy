package herramientas

class Maven {
    private def steps

    Maven(steps){
        this.steps = steps
    }

    def commonPhases(){
        steps.sh "mvn validate compile test package verify install"
    }
}

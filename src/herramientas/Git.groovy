package herramientas

class Git {
    private def steps

    Git(steps){
        this.steps = steps
    }

    def clone(String http_repo){
        this.steps.sh "rm -rf ./*"
        http_repo = http_repo.substring(7, http_repo.length())
        this.steps.withCredentials([this.steps.usernamePassword(credentialsId: 'GITHUB', passwordVariable: 'GIT_PASS', usernameVariable: 'GIT_USER')]){
            this.steps.sh "git clone https://${this.steps.GIT_USER}:${this.steps.GIT_PASS}@" + http_repo
        }
    }
}

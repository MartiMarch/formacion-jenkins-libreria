package herramientas

class Git {
    private def steps

    Git(steps){
        this.steps = steps
    }

    def clone(String http_repo){
        steps.sh "rm -rf ./*"
        http_repo = http_repo.substring(7, http_repo.length())
        steps.echo "${http_repo}"
        steps.withCredentials([steps.usernamePassword(credentialsId: 'GITHUB', passwordVariable: 'GIT_PASS', usernameVariable: 'GIT_USER')]){
            steps.sh "git clone https://${steps.GIT_USER}:${steps.GIT_PASS}@" + http_repo
        }
    }
}

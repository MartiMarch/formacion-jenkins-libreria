package herramientas

class Git {
    private def gitUser = ""
    private def gitPass = ""

    Git(gitUser, gitPass){
        this.gitUser = gitUser
        this.gitPass = gitPass
    }

    clone(String http_repo){
        sh "rm -rf ./*"
        sh "git clone https://" + this.gitUser + ":" + this.gitPass + "@" + http_repo.substring(7, http_repo.length())
    }
}

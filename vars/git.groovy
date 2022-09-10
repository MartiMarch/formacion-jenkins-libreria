import herramientas.Git

def clone(def http_url)
{
    Git git = new Git()
    git.clone(http_url)
}
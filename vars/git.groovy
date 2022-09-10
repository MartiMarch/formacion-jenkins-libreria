import herramientas.Git

def clone(def steps, def http_url)
{
    Git git = new Git(steps)
    git.clone(http_url)
}
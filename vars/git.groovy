import herramientas.Git

def clone(def steps, def http_url)
{
    Git git = new Git(steps)
    git.clone(steps, http_url)
}
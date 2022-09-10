import herramientas.Helm

def template(steps){
    Helm helm = new Helm(steps)
    helm.template()
}

def lint(steps){
    Helm helm = new Helm(steps)
    helm.lint()
}

def deploy(steps){
    Helm helm = new Helm(steps)
    helm.deploy()
}
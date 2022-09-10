import herramientas.Docker

def prune(steps){
    Docker docker = new Docker(steps)
    docker.prune()
}

def createDockerfile(steps, type){
    Docker docker = new Docker(steps)
    docker.createDockerfile(type)
}

def printDockerfile(steps){
    Docker docker = new Docker(steps)
    docker.printDockerfile()
}

def build(steps, type){
    Docker docker = new Docker(steps)
    docker.build(type)
}
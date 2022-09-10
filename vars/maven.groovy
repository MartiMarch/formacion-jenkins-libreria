import herramientas.Maven

def commonPhases(steps){
    Maven maven = new Maven(steps)
    maven.commonPhases()
}

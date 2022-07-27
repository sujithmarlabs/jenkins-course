job('NodeJS Docker example') {
    scm {
        git('https://github.com/sujithmarlabs/docker-demo-nodejs.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@sujith_jenkins.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('sujithmarlabs/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('sujith-dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

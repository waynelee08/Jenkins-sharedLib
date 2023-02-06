#!/usr/bin/env groovy
package com.example

class Docker implements Serializable{
    def script
    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String ImageName) {
        script.echo "buidling the docker image...."
        script.sh "docker build -t $ImageName ."

    }

    def dockerLogin(){
        script.withCredentials([script.usernamePassword(credentialsId: "docker-hub-repo", passwordVariable: 'PASS', usernameVariable: "USER")]) {

            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"

        }
    }

    def dockerPush(String ImageName) {
        script.sh "docker push $ImageName"
    }
}
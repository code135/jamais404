 pipeline {
    agent any
    stages {
        stage('Echo Sample') {
            steps {
                echo "ECHO SAMPLE"
                sh '(printenv)'
            }
        }
        stage('Build') {
            agent {
                docker {
                    image 'maven:3.6.3-jdk-11-slim'
                }
            }
            steps {
                echo "Build"
                sh '(cd ./jamais404/; mvn clean package)'
                stash name: "app", includes: "**"
            }
        }
        stage('Test') {
            steps {
                echo "Test: TODO"
            }
        }
        stage('Deploy') {
            steps {
                echo "Deploy: TODO"
            }
        }
    }
}
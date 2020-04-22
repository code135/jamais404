 pipeline {
    agent any
    options {
        disableConcurrentBuilds()
    }
    stages {

        stage('Build') {
            agent {
                docker {
                    image 'maven:3.6.3-jdk-11-slim'
                }
            }
            steps {
                echo 'Build'
                sh '(cd ./jamais404/; mvn clean package; pwd; ls -l)'
                stash name: "app", includes: "**"
            }
        }

        stage('Test') {
            agent {
                docker {
                    image 'maven:3.6.3-jdk-11-slim'
                }
            }
            steps {
                echo 'Test'
                unstash "app"
                sh '(cd ./jamais404/; mvn test)'
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploy: TODO"
            }
        }
    }

    post
	{
        always
		{
            echo 'Always clean up'
            deleteDir()
        }
    }
}

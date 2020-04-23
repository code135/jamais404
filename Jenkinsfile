 pipeline {

    agent any

    options {
        disableConcurrentBuilds()
    }
	
	environment {
        SPRING_DATASOURCE_USERNAME = credentials('SPRING_DATASOURCE_USERNAME')
        SPRING_DATASOURCE_PASSWORD = credentials('SPRING_DATASOURCE_PASSWORD')
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
                sh '(cd ./jamais404/; mvn clean package -Dspring.profiles.active=prod)'
                stash name: "app", includes: "**"
            }
        }

        stage('SonarCloud') {
            steps {
                unstash "app"
                sh '(cd ./jamais404/; mvn sonar:sonar -Dspring.profiles.active=prod'
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
                sh '(cd ./jamais404/; mvn test -Dspring.profiles.active=prod)'
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

pipeline {
    agent any

    stages {
        stage('Build java') {
            steps {
                echo 'Fetching SCM'
                checkout scm
            }
            steps {
                echo 'Init gradle wrapper'
                sh 'chmod u+x ./gradlew'
            }
            steps {
                echo 'Building..'
                sh './gradlew clean build -x --no-deamon'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh './gradlew runUnitTests'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
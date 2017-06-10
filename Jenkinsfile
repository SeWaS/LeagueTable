pipeline {
    agent any

    stages {
        stage('Init') {
            steps {
                echo 'Init gradle wrapper'
                sh 'chmod u+x ./gradlew'
            }
        }
        stage('Build java') {
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
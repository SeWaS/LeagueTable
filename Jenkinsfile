pipeline {
    agent any

    stages {
        stage('Build java') {
            steps {
                echo 'Building..'
                sh 'gradle clean build'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'gradle runUnitTests'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
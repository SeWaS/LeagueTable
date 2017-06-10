pipeline {
    agent any

    stages {
        stage('Build java') {
                echo 'Fetching SCM'
                checkout scm

                echo 'Init gradle wrapper'
                sh 'chmod u+x ./gradlew'

                echo 'Building..'
                sh './gradlew clean build -x --no-deamon'
        }
        stage('Test') {
                echo 'Testing..'
                sh './gradlew runUnitTests'
        }
        stage('Deploy') {
                echo 'Deploying....'
        }
    }
}
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
                sh './gradlew clean build -x test --no-daemon'
            }
        }
        stage('Unit Tests') {
            steps {
                echo 'Start Unittests'
                sh './gradlew runUnitTests'
            }
        }
        stage('Integration Tests') {
            steps {
                echo 'Start IntegrationTests'
                sh './gradlew runIntegrationTests'
            }
        }
        stage('Acceptance Tests') {
            steps {
                echo 'Start AcceptanceTests'
                sh './gradlew runFeatureTests'
            }
        }
        stage('Reporting') {
            steps {
                echo 'Aggregate Serenity Report'
                sh './gradlew aggregate'
                publishHTML (target: [
                            allowMissing: false,
                            alwaysLinkToLastBuild: true,
                            keepAll: true,
                            reportDir: 'target/site/serenity/',
                            reportFiles: 'index.html',
                            reportName: "Feature Report"
                            ])
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
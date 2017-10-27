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
                sh './gradlew runAcceptanceTests'
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
                echo 'Generate Unittest Coverage report'
                sh './gradlew unitTestReport'
                publishHTML (target: [
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: false,
                    reportDir: 'build/reports/jacoco/unitTestReport/html',
                    reportFiles: 'index.html',
                    reportName: "Unittest Coverage"
                ])

                echo 'Generate Integrationtest Coverage report'
                sh './gradlew integrationTestReport'
                publishHTML (target: [
                                    allowMissing: true,
                                    alwaysLinkToLastBuild: true,
                                    keepAll: false,
                                    reportDir: 'build/reports/jacoco/integrationTestReport/html',
                                    reportFiles: 'index.html',
                                    reportName: "Integrationtest Coverage"
                                ])

                echo 'Generate Unit and IT COverage report'
                sh './gradlew unitAndITcoverageReport'
                publishHTML (target: [
                                    allowMissing: true,
                                    alwaysLinkToLastBuild: true,
                                    keepAll: false,
                                    reportDir: 'build/reports/jacoco/unitAndITcoverageReport/html',
                                    reportFiles: 'index.html',
                                    reportName: "Unit and IT Coverage"
                                ])

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

        stage('Publish') {
            steps {
                echo 'Publishing'
                sh './gradlew publishToMavenLocal'
            }
        }
    }
}
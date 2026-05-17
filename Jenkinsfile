pipeline {

    agent any

    tools {
        jdk 'JDK21'
    }

    environment {
        APP_NAME = "banking-app"
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo 'Pulling code from GitHub...'
                git branch: 'main',
                    url: 'https://github.com/girivenkata118/banking-app.git'
            }
        }

        stage('Verify Java') {
            steps {
                sh 'java -version'
            }
        }

        stage('Clean Project') {
            steps {
                echo 'Cleaning old build files...'
                sh './gradlew clean'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running unit tests...'
                sh './gradlew test'
            }
        }

        stage('Build Application') {
            steps {
                echo 'Building Spring Boot application...'
                sh './gradlew build'
            }
        }

        stage('List Generated JAR') {
            steps {
                echo 'Generated JAR files:'
                sh 'ls -lh build/libs'
            }
        }
    }

    post {

        success {
            echo 'Pipeline completed successfully!'
        }

        failure {
            echo 'Pipeline failed!'
        }

        always {
            echo 'Pipeline execution finished.'
        }
    }
}

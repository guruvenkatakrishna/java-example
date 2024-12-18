pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/guruvenkatakrishna/jenkinspipeline.git'
            }
        }
        stage('Code Analysis') {
            steps {
                sh 'mvn clean verify sonar:sonar'
            }
        }
        stage('Unit Testing') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Build Artifact') {
            steps {
                sh 'mvn package'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t app:latest .'
            }
        }
        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-cred', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh 'docker login -u $USERNAME -p $PASSWORD'
                    sh 'docker push app:latest'
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker run -d -p 80:80 app:latest'
            }
        }
    }
}

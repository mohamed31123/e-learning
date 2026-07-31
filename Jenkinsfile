pipeline {
    agent any

    stages {



        stage('Build') {
            steps {
            echo 'compiling ... !'
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
            echo 'Testing  .... ! '
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {

                bat 'mvn package'
            }
        }
    }

    post {
        success {
            echo 'Build completed successfully!'
        }

        failure {
            echo 'Build failed!'
        }
    }
}
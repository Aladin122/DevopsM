pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
        jdk 'jdk17'
    }

    environment {
        SONARQUBE_ENV = 'SonarQubeServer'
        NEXUS_URL = '192.168.235.132:8081'
        NEXUS_CREDENTIALS_ID = 'nexus-creds'
        GROUP_ID = 'tn.esprit.spring'
        ARTIFACT_ID = 'kaddem'
        VERSION = '0.0.1-SNAPSHOT'
        IMAGE_NAME = 'kaddem-backend'
        DOCKER_TAG = 'latest'
    }

    stages {
        stage('Clone') {
            steps {
                git branch: 'main', url: 'https://github.com/Aladin122/DevopsM.git'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv("${SONARQUBE_ENV}") {
                    withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                        sh "mvn clean verify sonar:sonar -Dsonar.token=$SONAR_TOKEN"
                    }
                }
            }
        }

        stage('Build') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${IMAGE_NAME}:${DOCKER_TAG} ."
                }
            }
        }

        stage('Upload to Nexus') {
            steps {
                script {
                    def repo = env.VERSION.endsWith('-SNAPSHOT') ? 'maven-snapshots' : 'maven-releases'
                    echo "Uploading to Nexus repository: ${repo}"
                    nexusArtifactUploader(
                        nexusVersion: 'nexus3',
                        protocol: 'http',
                        nexusUrl: env.NEXUS_URL,
                        repository: repo,
                        groupId: env.GROUP_ID,
                        version: env.VERSION,
                        credentialsId: env.NEXUS_CREDENTIALS_ID,
                        artifacts: [
                            [artifactId: env.ARTIFACT_ID, file: "target/${env.ARTIFACT_ID}-${env.VERSION}.jar", type: 'jar']
                        ]
                    )
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}

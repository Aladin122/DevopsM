pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
        jdk 'jdk17'
    }

    environment {
        SONARQUBE_ENV = 'SonarQubeServer'
        GROUP_ID = 'tn.esprit.spring'
        ARTIFACT_ID = 'kaddem'
        VERSION = '0.0.1-SNAPSHOT'

        NEXUS_URL = '192.168.235.132:8081'
        IMAGE_NAME = 'kaddem-backend'
        DOCKER_TAG = 'latest'
        NEXUS_DOCKER_REPO = 'docker-releases2'
        NEXUS_DOCKER_URL = '192.168.235.132:8082'
        NEXUS_DOCKER_CREDS_ID = 'nexus-docker-creds'
        NEXUS_CREDENTIALS_ID = 'nexus-creds'
    }

    stages {
        stage('Clone') {
            steps {
                git branch: 'main', url: 'https://github.com/Aladin122/DevopsM.git'
            }
        }


        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Backend Docker Image') {
            steps {
                sh "docker build -t ${IMAGE_NAME}:${DOCKER_TAG} ."
            }
        }

        stage('Push Backend Docker Image to Nexus') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: "${NEXUS_DOCKER_CREDS_ID}",
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh """
                        echo "$DOCKER_PASS" | docker login ${NEXUS_DOCKER_URL} -u "$DOCKER_USER" --password-stdin
                        docker tag ${IMAGE_NAME}:${DOCKER_TAG} ${NEXUS_DOCKER_URL}/${NEXUS_DOCKER_REPO}/${IMAGE_NAME}:${DOCKER_TAG}
                        docker push ${NEXUS_DOCKER_URL}/${NEXUS_DOCKER_REPO}/${IMAGE_NAME}:${DOCKER_TAG}
                    """
                }
            }
        }

        stage('Upload JAR to Nexus Maven Repo') {
            steps {
                script {
                    def repo = env.VERSION.endsWith('-SNAPSHOT') ? 'maven-snapshots' : 'maven-releases'
                    echo "Uploading artifact to Nexus repo: ${repo}"
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

        stage('Deploy Backend + Frontend + H2') {
            steps {
                dir('.') {
                    script {
                        echo "🚀 Deploying backend, frontend, and H2 database containers"
                        sh '''
                            # Create the network if not exists
                            docker network ls | grep kaddem-network || docker network create --driver bridge kaddem-network

                            # Stop & clean old containers
                            docker-compose down --remove-orphans || true
                            docker rm -f kaddem-app h2-db frontend-app || true

                            # Pull all images from Nexus
                            docker-compose pull || true

                            # Run all services
                            docker-compose up -d
                        '''
                    }
                }
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed.'
        }
    }
}

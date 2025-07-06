pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
        jdk 'jdk17'
    }

    environment {
        // General Configs
        SONARQUBE_ENV = 'SonarQubeServer'
        GROUP_ID = 'tn.esprit.spring'
        ARTIFACT_ID = 'kaddem'
        VERSION = '0.0.1-SNAPSHOT'

        // Maven Nexus
        NEXUS_URL = '192.168.235.132:8081'  // For Maven repo

        // Docker Image
        IMAGE_NAME = 'kaddem-backend'
        DOCKER_TAG = 'latest'

        // Nexus Docker Registry
        NEXUS_DOCKER_REPO = 'docker-releases2'  // updated repo name
        NEXUS_DOCKER_URL = '192.168.235.132:8082'  // HTTPS port for docker repo
        NEXUS_DOCKER_CREDS_ID = 'nexus-docker-creds'
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

        stage('Build JAR') {
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

        stage('Push Docker Image to Nexus') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: "${NEXUS_DOCKER_CREDS_ID}",
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    script {
                        sh """
                            echo "$DOCKER_PASS" | docker login ${NEXUS_DOCKER_URL} -u "$DOCKER_USER" --password-stdin
                            docker tag ${IMAGE_NAME}:${DOCKER_TAG} ${NEXUS_DOCKER_URL}/${NEXUS_DOCKER_REPO}/${IMAGE_NAME}:${DOCKER_TAG}
                            docker push ${NEXUS_DOCKER_URL}/${NEXUS_DOCKER_REPO}/${IMAGE_NAME}:${DOCKER_TAG}
                        """
                    }
                }
            }
        }

        stage('Upload JAR to Nexus Maven Repo') {
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
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed.'
        }
    }
}

# Use OpenJDK base image
FROM eclipse-temurin:17-jdk-alpine

# Set app directory
WORKDIR /app

# Copy the built JAR into the container
COPY target/kaddem-0.0.1-SNAPSHOT.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]

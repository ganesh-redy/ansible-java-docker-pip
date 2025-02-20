# Use a smaller Maven image with Temurin JDK
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

# Set working directory
WORKDIR /app

# Copy the project files
COPY . .

# Build the application and remove cache to save space
RUN mvn clean package \
    && rm -rf /root/.m2/repository

# Use a lightweight Tomcat base image (Alpine-based)
FROM tomcat:jre8-alpine

# Set working directory inside Tomcat
WORKDIR /usr/local/tomcat/webapps/

# Copy only the built WAR file
COPY --from=builder /app/target/simple-java-maven-app.war ./ROOT.war

# Expose port 8080 for Tomcat
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]


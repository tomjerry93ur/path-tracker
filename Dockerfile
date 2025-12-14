# # ===== 1) BUILD STAGE: use Maven to build the jar =====
# FROM maven:3.9-eclipse-temurin-17 AS build
#
# WORKDIR /app
#
# # Copy pom.xml and download dependencies (cache layer)
# COPY pom.xml .
# RUN mvn dependency:go-offline
#
# # Copy source code and build the app
# COPY src ./src
# RUN mvn clean package -DskipTests
#

# ===== 2) RUNTIME STAGE: lightweight JDK image =====
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# ⛔ IMPORTANT: jar name must match your real jar name
# Default if pom.xml has artifactId "path-tracker" and version "0.0.1-SNAPSHOT":
COPY target/*.jar app.jar

# Railway will map the PORT env var; 8080 is just for docs/local
EXPOSE 8080

# Use PORT from Railway, fall back to 8080 locally
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]

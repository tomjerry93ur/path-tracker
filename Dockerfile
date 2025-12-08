FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy pom and source if you want multi-stage build (optional), but simplest is:
COPY target/path-tracker-0.0.1-SNAPSHOT.jar app.jar

# Expose default port (for docs; Railway will still map PORT -> container)
EXPOSE 8080

# Use SERVER_PORT env var and fall back to 8080
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${SERVER_PORT:-8080}"]

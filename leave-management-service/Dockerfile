# Stage 1: Build the application
FROM eclipse-temurin:21-jdk-alpine as builder
WORKDIR /workspace
COPY . .
RUN ./mvnw package -DskipTests

# Stage 2: Runtime image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Create and switch to non-root user
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy built JAR from builder stage
COPY --from=builder --chown=spring:spring /workspace/target/leave-management-service-*.jar leave-management-service.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "leave-management-service.jar"]
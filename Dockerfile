# Stage 1: Build the application
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn package

# Stage 2: Create the final runtime image
FROM amazoncorretto:17 AS runtime
WORKDIR /app
# Copy the JAR file from the build stage to the runtime image
COPY --from=build /app/target/inventario-back*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

# Expose port 8080
EXPOSE 8080

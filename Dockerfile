
# Stage 1: Build the application
FROM amazoncorretto:17 AS build
WORKDIR /app
COPY . .
RUN ./mvnw package


# Stage 2: Create the final runtime image
FROM amazoncorretto:17 AS runtime
WORKDIR /app
# Replace 'inventario-back-0.0.1.jar' with the correct JAR file name
COPY --from=build /app/target/inventario-back*.jar app.jar
#COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]

# Expose port 8080
EXPOSE 8080

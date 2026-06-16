# Step 1: Build stage inside an official enterprise Temurin Maven container
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Runtime stage inside a lightweight JRE container
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
# Dynamic wildcard matching to safely grab whichever JAR was generated
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

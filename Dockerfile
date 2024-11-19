# Etapa de Build
FROM maven:3.8.8-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:resolve
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de Execução
FROM openjdk:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

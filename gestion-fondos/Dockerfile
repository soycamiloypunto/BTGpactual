# --- Fase 1: Build ---
FROM gradle:8.5.0-jdk21 AS build

WORKDIR /home/gradle/src

COPY build.gradle settings.gradle ./

COPY src ./src

RUN gradle build --no-daemon

# --- Fase 2: Run ---
FROM openjdk:21-jre-slim

WORKDIR /app

COPY --from=build /home/gradle/src/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
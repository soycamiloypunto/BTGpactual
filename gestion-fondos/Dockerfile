# --- Fase 1: Build ---
# Usamos una imagen de Gradle con JDK 21 para compilar nuestro proyecto
FROM gradle:8.5.0-jdk21 AS build

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /home/gradle/src

# Copiamos los archivos de build de Gradle para cachear las dependencias
COPY build.gradle settings.gradle ./

# Copiamos el resto del código fuente del backend
COPY src ./src

# Ejecutamos el comando de Gradle para construir el JAR ejecutable
# --no-daemon es importante para entornos de CI/CD y Docker
RUN gradle build --no-daemon

# --- Fase 2: Run ---
# Usamos una imagen oficial de Java 21 más ligera, solo con el JRE
FROM openjdk:21-jre-slim

# Establecemos el directorio de trabajo
WORKDIR /app

# Copiamos el JAR compilado desde la fase de 'build' a nuestro contenedor final
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar

# Exponemos el puerto 8080, que es donde corre nuestra aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicie el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]
# magen base
FROM openjdk:22-jdk-alpine

# directorio para la aplicación
WORKDIR /app

# Copia el JAR del proyecto al contenedor
COPY target/mi-proyecto-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Ejecuta JAR
ENTRYPOINT ["java", "-jar", "app.jar"]

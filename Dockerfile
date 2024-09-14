FROM openjdk:17-jdk-slim
COPY target/demo-0.0.1-SNAPSHOT.jar /app/java-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/java-app.jar"]

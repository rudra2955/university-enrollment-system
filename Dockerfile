FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/University_Enrollment_System-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]

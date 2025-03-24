FROM openjdk:17-jdk-slim
WORKDIR /student
COPY target/student-0.0.1-SNAPSHOT.jar student.jar
ENTRYPOINT ["java", "-jar", "student.jar"]
EXPOSE 8080

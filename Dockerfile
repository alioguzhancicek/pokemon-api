FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} pokemon-api.jar
ENTRYPOINT ["java", "-jar", "pokemon-api.jar"]
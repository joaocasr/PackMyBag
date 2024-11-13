
FROM openjdk:17-jdk

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENV DB_CONNECTION=postgresql
ENV DB_HOST=postgres
ENV DB_PORT=5432
ENV DB_DATABASE=catalogo
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=postgres

# Expose port 8081
EXPOSE 8081

# Command to run the JAR file
ENTRYPOINT ["java","-jar","/app.jar"]
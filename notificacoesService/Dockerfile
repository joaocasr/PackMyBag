
FROM openjdk:17-jdk

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENV DB_CONNECTION=postgresql
ENV DB_HOST=postgres
ENV DB_PORT=5432
ENV DB_DATABASE=notificacoes
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=postgres

# Expose port 8085
EXPOSE 8085

# Command to run the JAR file
ENTRYPOINT ["java","-jar","/app.jar"]
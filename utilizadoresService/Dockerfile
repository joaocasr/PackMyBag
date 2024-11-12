
FROM openjdk:17-jdk

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENV DB_CONNECTION=postgresql
ENV DB_HOST=postgres
ENV DB_PORT=5432
ENV DB_DATABASE=utilizadores
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=postgres
ENV JWT_SECRET=PEI-PROJECT
ENV EXPIRATION_TIME=3600000

# Expose port 8080
EXPOSE 8087

# Command to run the JAR file
ENTRYPOINT ["java","-jar","/app.jar"]
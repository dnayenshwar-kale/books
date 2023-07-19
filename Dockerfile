# Use openjdk:17-jdk-slim as the base image
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copy the jar file to the image
COPY target/books-0.0.1-SNAPSHOT.jar /app/book.jar
EXPOSE 8080
# Set the entrypoint to run the jar file
ENTRYPOINT ["java","-jar","/app/book.jar"]

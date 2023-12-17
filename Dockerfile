FROM openjdk:17-alpine
ADD target/Example.jar Example1.jar
ENTRYPOINT ["java" , "-jar", "Example.jar"]
EXPOSE 8080
FROM openjdk:11
ADD target/store-1.0.0.jar store-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "store-1.0.0.jar"]
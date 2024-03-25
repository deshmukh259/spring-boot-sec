#Start with base image conting java runtime
FROM openjdk:11-slim as build

MAINTAINER PURUSHOTTAM

#Add the application jar to container
COPY target/spring-boot-sec-0.0.1-SNAPSHOT.jar spring-boot-sec-0.0.1-SNAPSHOT.jar

#execute the applicatio spring-boot-sec-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/spring-boot-sec-0.0.1-SNAPSHOT.jar"]
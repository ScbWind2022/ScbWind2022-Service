FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
WORKDIR /home/app
RUN mvn -q clean package

FROM openjdk:11
COPY --from=build /home/app/target/rate-service-0.0.1-SNAPSHOT.jar rateservice.jar
ENTRYPOINT ["java","-jar","rateservice.jar"]
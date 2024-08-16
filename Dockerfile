FROM maven:3.9.8-amazoncorretto-21 AS build

WORKDIR /app

COPY ./pom.xml /app
COPY ./src /app/src

RUN mvn clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21.0.4_7-jre

WORKDIR /app

COPY --from=build /app/target/transaction-service-*.jar /app/transaction-service.jar

ENTRYPOINT ["java", "-jar", "/app/transaction-service.jar"]
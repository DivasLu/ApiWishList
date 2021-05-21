FROM maven:3.6.0-jdk-11-slim AS build
ARG SKIP_TESTS
COPY ./src /home/app/src
COPY ./pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install -DskipTests=$SKIP_TESTS

FROM openjdk:8-jre
RUN mkdir app
COPY --from=build /home/app/target/ApiWishlist-0.0.1-SNAPSHOT.jar /app/spring-docker.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar",	"-Dspring.datasource.url=jdbc:mysql://host.docker.internal:3307/db_wihslist?createDatabaseIfNotExist=true&serverTimezone=UTC&useSSl=false", "-Dspring.datasource.username=root", "-Dspring.datasource.password=root", "spring-docker.jar"]
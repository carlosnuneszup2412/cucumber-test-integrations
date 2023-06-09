FROM gradle:7-jdk11-focal AS build
WORKDIR /app
COPY . .
RUN gradle build --parallel -x test

FROM eclipse-temurin:11-jdk-focal AS run
WORKDIR /app
COPY --from=build /app/build/libs/cucumber*-SNAPSHOT.jar /app/server.jar
CMD java -jar server.jar
EXPOSE 8080
HEALTHCHECK CMD curl --silent --fail --request GET http://localhost:8080/actuator/health | grep UP || exit 1
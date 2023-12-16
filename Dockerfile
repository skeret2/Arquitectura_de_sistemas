FROM gradle:8.3.0-jdk17 AS build
FROM eclipse-temurin:17-alpine
FROM gradle:8.3 as gradle


WORKDIR /usr/src/as
COPY --chown=gradle:gradle build.gradle settings.gradle .
COPY --chown=gradle:gradle ./src/ ./src/
RUN gradle shadowJar

FROM eclipse-temurin:17-alpine

WORKDIR /opt/as
COPY --from=gradle /usr/src/as/build/libs/conserjeria-0.0.5-all.jar /opt/as/conserjeria.jar

EXPOSE 7070
EXPOSE 50123
ENTRYPOINT exec java -jar /opt/as/conserjeria.jar
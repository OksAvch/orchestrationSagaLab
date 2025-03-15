ARG GRADLE_IMG=gradle:jdk23
ARG JAVA_IMG=openjdk:23-jdk-slim

FROM $GRADLE_IMG AS builder
WORKDIR /workdir

COPY ./inventory-service ./inventory-service
COPY build.gradle .
COPY settings.gradle .

ARG VERSION
ENV GRADLE_OPTS -Dorg.gradle.daemon=false
RUN gradle :inventory-service:inventory-server:build -x check --no-daemon


FROM $JAVA_IMG
ARG DEPENDENCY=/workdir/inventory-service/inventory-server/build

RUN ls .
COPY --from=builder ${DEPENDENCY}/libs/inventory-server*.jar /opt/inventory-service/inventory-service.jar

WORKDIR /opt/inventory-service
CMD java -jar inventory-service.jar
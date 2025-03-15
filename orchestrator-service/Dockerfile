ARG GRADLE_IMG=gradle:jdk23
ARG JAVA_IMG=openjdk:23-jdk-slim

FROM $GRADLE_IMG AS builder
WORKDIR /workdir

COPY ./orchestrator-service ./orchestrator-service
COPY ./inventory-service ./inventory-service
COPY ./payment-service ./payment-service
COPY ./order-service ./order-service
COPY build.gradle .
COPY settings.gradle .

ARG VERSION
ENV GRADLE_OPTS -Dorg.gradle.daemon=false
RUN gradle :orchestrator-service:orchestrator-server:build -x check --no-daemon


FROM $JAVA_IMG
ARG DEPENDENCY=/workdir/orchestrator-service/orchestrator-server/build

RUN ls .
COPY --from=builder ${DEPENDENCY}/libs/orchestrator-server*.jar /opt/orchestrator-service/orchestrator-service.jar

WORKDIR /opt/orchestrator-service
CMD java -jar orchestrator-service.jar
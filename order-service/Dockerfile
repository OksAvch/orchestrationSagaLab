ARG GRADLE_IMG=gradle:jdk23
ARG JAVA_IMG=openjdk:23-jdk-slim

FROM $GRADLE_IMG AS builder
WORKDIR /workdir

COPY ./order-service ./order-service
COPY build.gradle .
COPY settings.gradle .

ARG VERSION
ENV GRADLE_OPTS -Dorg.gradle.daemon=false
RUN gradle :order-service:order-server:build -x check --no-daemon


FROM $JAVA_IMG
ARG DEPENDENCY=/workdir/order-service/order-server/build

RUN ls .
COPY --from=builder ${DEPENDENCY}/libs/order-server*.jar /opt/order-service/order-service.jar

WORKDIR /opt/order-service
CMD java -jar order-service.jar
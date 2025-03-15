ARG GRADLE_IMG=gradle:jdk23
ARG JAVA_IMG=openjdk:23-jdk-slim

FROM $GRADLE_IMG AS builder
WORKDIR /workdir

COPY ./payment-service ./payment-service
COPY build.gradle .
COPY settings.gradle .

ARG VERSION
ENV GRADLE_OPTS -Dorg.gradle.daemon=false
RUN gradle :payment-service:payment-server:build -x check --no-daemon


FROM $JAVA_IMG
ARG DEPENDENCY=/workdir/payment-service/payment-server/build

RUN ls .
COPY --from=builder ${DEPENDENCY}/libs/payment-server*.jar /opt/payment-service/payment-service.jar

WORKDIR /opt/payment-service
CMD java -jar payment-service.jar
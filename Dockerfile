# the base image with JDK use to build and run your java application
#FROM maven:3.9.9-eclipse-temurin-23
FROM openjdk:23-jdk-oracle

# labeling the dockerfile
LABEL MAINTAINER="dharmesh"
LABEL description="This is VTTP5 SSF day 12 workshop"
LABEL name="vttp5-ssf-day12workshop"

ARG APP_DIR=/APP

# directory where your source code will reside
# directory where you copy your project to (in the next step)
WORKDIR ${APP_DIR}

# copy the required files and/or directories into the image 
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY src src
COPY .mvn .mvn

# package the application using the RUN directive
# this will download the dependencies definedin pom.xml
# compile and package to jar
# RUN chmod a+x ./mvnw
RUN ./mvnw clean package -Dmaven.test.skip=true
# RUN mvn clean package -Dmaven.test.skip=true

ENV SERVER_PORT=3000

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java","-jar","target/vttp5_ssf_day12workshop-0.0.1-SNAPSHOT.jar","--server.port=${SERVER_PORT}"]
FROM node:20.17.0-alpine3.20 AS webapp

WORKDIR /webapp
COPY ./webapp /webapp

RUN yarn
RUN yarn run build

FROM maven:3.9-eclipse-temurin-22 AS service

WORKDIR /service
COPY ./service /service

RUN mvn clean
RUN mvn install

WORKDIR /service/program
CMD mvn spring-boot:run

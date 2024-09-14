FROM node:20.17.0-alpine3.20 AS builder

WORKDIR /frontend
COPY ./frontend /frontend

RUN yarn install
RUN yarn run build

FROM maven:3.9-eclipse-temurin-22 AS service

WORKDIR /backend
COPY ./backend /backend
COPY --from=builder /frontend/dist/frontend /backend/src/main/resources/webapp

CMD mvn spring-boot:run

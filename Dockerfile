# syntax=docker/dockerfile:1

FROM gradle:7.4.2-jdk17
MAINTAINER Anatoly Prets <prets.anatoly@gmail.com>
COPY . /app
WORKDIR /app
ENTRYPOINT gradle bootrun
EXPOSE 8080

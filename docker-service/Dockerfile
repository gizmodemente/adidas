FROM       alpine:latest

MAINTAINER Fernando de los Rios <fdelosriosfernandez@gmail.com>

RUN apk --update add openjdk8-jre

ARG filename=target.jar
ARG enviroment=compose

ENV ENVIRONMENT=${enviroment}

RUN mkdir /deployment
ADD /target/${filename} /deployment/target.jar

CMD java -jar /deployment/target.jar

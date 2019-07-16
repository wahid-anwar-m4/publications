FROM fabric8/java-alpine-openjdk8-jdk:1.2.6
MAINTAINER John Doe<John@doe.com>

ENV JAVA_APP_JAR my_jar.jar
ENV AB_OFF true

EXPOSE 8080

COPY build/libs/*.jar /deployments/my_jar.jar

FROM postgres
ENV POSTGRES_DB publications 
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD postgres


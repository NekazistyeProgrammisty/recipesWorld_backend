FROM openjdk:17-alpine
EXPOSE 8080
RUN apk update
RUN apk add maven
RUN mvn -version
WORKDIR foodboard
COPY . .
RUN mvn clean install


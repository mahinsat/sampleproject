FROM openjdk:8-jdk-alpine
ARG JAR_FILE=out/artifacts/sampleproject_one_jar/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

FROM azul/zulu-openjdk-alpine:21.0.10-21.48-jre
WORKDIR /app
COPY build/libs/*.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","app.jar"]
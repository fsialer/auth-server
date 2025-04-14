FROM azul/zulu-openjdk-alpine:21-jre-latest
WORKDIR /app
COPY build/libs/*.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","app.jar"]
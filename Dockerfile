
FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/rentaAutos-1.0-SNAPSHOT.jar /app/rentaAutos.jar

COPY src/main/resources/application.yml /app/application.yml

EXPOSE 8090

CMD ["java", "-jar", "rentaAutos.jar", "--spring.config.location=file:/app/application.yml"]





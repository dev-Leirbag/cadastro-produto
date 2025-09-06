FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/produto-0.0.1-SNAPSHOT.jar /app/produto.jar

EXPOSE 9091

CMD ["java", "-jar", "/app/produto.jar"]
FROM openjdk:18-jdk-slim
RUN apt-get update && apt-get install -y
WORKDIR /app
COPY target/peliculas-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_MFZE2MBJQ81CLQL8 /app/wallet
ENV TNS_ADMIN=/app/wallet
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

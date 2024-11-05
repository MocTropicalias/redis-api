# Fase de build da aplicação
FROM maven:3.8.5-openjdk-17 as build
COPY . .
RUN mvn clean package -DskipTests

# Fase final com OpenJDK e Redis
FROM openjdk:17-jdk-slim

# Instalar o Redis
RUN apt-get update && apt-get install -y redis-server

# Configuração do Redis
RUN sed -i 's/^bind 127.0.0.1 ::1/bind 0.0.0.0/' /etc/redis/redis.conf

# Copiar o jar da aplicação
COPY --from=build /target/RedisApi-0.0.1-SNAPSHOT.jar app.jar

# Expor as portas da aplicação e do Redis
EXPOSE 8080 6379

# Rodar o Redis em background e a aplicação Java
CMD redis-server --daemonize yes && java -jar app.jar

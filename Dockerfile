# Etapa de Build do projeto Java com Maven
FROM ubuntu:latest AS build

# Atualiza e instala dependências necessárias
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Define o diretório de trabalho
WORKDIR /app

# Copia todos os arquivos do projeto para o diretório de trabalho
COPY . .

# Executa o Maven para construir o projeto
RUN mvn clean install -DskipTests

# Etapa de Execução: Ubuntu com Redis e Java
FROM ubuntu:latest

# Atualiza pacotes e instala Redis e OpenJDK
RUN apt-get update && \
    apt-get install -y redis-server openjdk-17-jdk

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR gerado da etapa de build
COPY --from=build /target/RedisApi-0.0.1-SNAPSHOT.jar app.jar

# Copia o arquivo de configuração do Redis, se necessário (opcional)
# COPY redis.conf /etc/redis/redis.conf

# Expõe a porta 8080 para o aplicativo Spring Boot e 6379 para o Redis
EXPOSE 8080 6379

# Script de inicialização para Redis e Spring Boot
# Esse script iniciará o Redis e, logo após, o Spring Boot
CMD service redis-server start && \
    java -jar app.jar

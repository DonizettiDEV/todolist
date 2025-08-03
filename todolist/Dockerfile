# Etapa de build
FROM ubuntu:latest AS build

# Instala dependências necessárias
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para dentro do container
COPY . .

# Executa o build da aplicação
RUN mvn clean package -DskipTests

# Etapa de runtime
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho no container final
WORKDIR /app

# Expõe a porta 8080
EXPOSE 8080

# Copia o JAR da etapa de build para o container final
COPY --from=build /app/target/todolist-0.0.1-SNAPSHOT.jar app.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

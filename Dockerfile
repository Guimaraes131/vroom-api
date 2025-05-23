# Usa uma imagem leve com Java 17
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo .jar gerado pelo Maven
COPY ../target/vroom-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080 (ajuste se seu app usar outra)
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
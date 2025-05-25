
# Vroom API

API RESTful desenvolvida em Java com Spring Boot para gerenciamento de motocicletas em um ambiente de estacionamento monitorado por setores e tags RFID.

## 📦 Funcionalidades

- Cadastro, listagem, atualização e exclusão de motos
- Associação de motos a setores e coordenadas de tags
- Integração com banco H2 em memória
- Documentação automática com Swagger
- Pronta para deploy em Docker e na Azure

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Security (com acesso liberado à API)
- H2 Database
- Swagger/OpenAPI 3
- Docker

## 🔧 Como rodar localmente

### Pré-requisitos

- Java 17 instalado
- Maven instalado

### Passos

```bash
# Clonar o repositório
git clone https://github.com/Guimaraes131/vroom-api.git
cd vroom-api

# Gerar o .jar
mvn clean package -DskipTests

# Executar a aplicação
java -jar target/vroom-0.0.1-SNAPSHOT.jar
```

A API estará disponível em: `http://localhost:8080`

## 🐳 Deploy com Docker

### Build da imagem

```bash
docker build -t vroom-api .
```

### Execução do container

```bash
docker run -d -p 80:8080 vroom-api
```

Acesse: `http://localhost` ou o IP público da sua VM se estiver na Azure.

## ☁️ Deploy na Azure (via script)

Você pode usar o script `scripts/deploy.sh` para criar sua infraestrutura na Azure, e o `install_docker.sh` para configurar o Docker na VM.

### Etapas gerais:

1. Subir os arquivos para a VM (Dockerfile, `.jar`, scripts)
2. Acessar via SSH
3. Rodar `install_docker.sh`
4. Buildar e executar o container

## 📘 Endpoints principais

- `GET /motos` – Lista todas as motos
- `POST /motos` – Cadastra nova moto
- `PUT /motos/{id}` – Atualiza uma moto existente
- `DELETE /motos/{id}` – Remove uma moto
- `GET /motos/{id}` – Detalhes de uma moto

## 🧪 H2 Console e Swagger

- **H2 Console:** `http://<host>/h2-console`
  - JDBC URL: `jdbc:h2:mem:vroom`
  - Usuário: `sa`
  - Senha: *(vazia)*

- **Swagger UI:** `http://<host>/swagger-ui/index.html`

---

## 👨‍💻 Desenvolvido por

- Matheus Oliveira de Luna RM: 555547 TURMA: 2TDSA
- Guilherme Guimarães RM: 557074 TURMA: 2TDSA
- Nicollas Guedes Pontes RM: 556850 TURMA: 2TDSB
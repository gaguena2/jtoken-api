# 🚀 jtoken-api

API de autenticação simples com geração e persistência de tokens utilizando **Spring Boot** e **MongoDB**.

---

## 📌 Sobre o projeto

O **jtoken-api** é uma API REST que permite:

- Cadastro de usuários
- Autenticação (login)
- Geração de tokens
- Persistência de tokens no MongoDB
- Validação e revogação de tokens

Projeto ideal para estudo de:
- Arquitetura REST
- Spring Boot
- MongoDB
- Autenticação baseada em token

---

## 🧱 Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data MongoDB
- Lombok
- Maven
- MongoDB

---

## ⚙️ Configuração

### 🔹 MongoDB (Docker)

```bash
docker run -d \
  --name mongodb \
  -p 27017:27017 \
  -e MONGO_INITDB_ROOT_USERNAME=xxx \
  -e MONGO_INITDB_ROOT_PASSWORD=xxxx \
  -v mongodb_data:/data/db

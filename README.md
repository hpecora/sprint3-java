# 🚀 Projeto Mottu Challenge - Java Advanced

Este projeto é uma API REST desenvolvida em **Java com Spring Boot**, com banco de dados **H2**, que simula o sistema de rastreamento e gestão de motos da empresa **Mottu**.

## 📜 Descrição

O sistema permite:
- Cadastro de motos.
- Cadastro de localizações associadas às motos.
- Consulta, atualização e remoção de motos e localizações.
- Relacionamento entre motos e localizações.
- Paginação, ordenação e busca.
- Validações e tratamento de erros.
- Documentação dos endpoints via Swagger.

## 🧠 Tecnologias utilizadas
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- Banco de dados H2
- Bean Validation
- OpenAPI/Swagger
- Spring Cache
- Lombok
- Gradle

## 📂 Estrutura do Projeto
```
com.mottu.mototracker
├── controller
├── exception
├── model
├── repository
├── service
└── MotoTrackerApplication.java
```

## 🔥 Como executar o projeto
1. Clone este repositório:
```
git clone https://github.com/hpecora/Mottu_Challenge
```
2. Abra no IntelliJ, VSCode ou Spring Tool Suite.
3. Execute a classe:
```
MotoTrackerApplication.java
```

4. Acesse:
- Swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- Banco H2: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
   - JDBC URL: `jdbc:h2:mem:motodb`
   - Usuário: `sa`
   - Senha: (em branco)

## 👥 Integrantes
- Lívia de Oliveira Lopes | RM: 556281
- Henrique Pecora | RM: 556612
- Santhiago de Gobbi | RM: 98420

## 🚀 Desafio proposto pela disciplina JAVA ADVANCED - FIAP
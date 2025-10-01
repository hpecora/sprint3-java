🚀 Projeto MotoTracker – Sprint 3 (Java Advanced)

Este projeto foi desenvolvido como parte da disciplina Java Advanced no curso de ADS – FIAP.
O sistema MotoTracker é uma aplicação em Java com Spring Boot que simula o rastreamento e a gestão de motos da empresa Mottu.

📌 Descrição

O MotoTracker permite:

Cadastro, edição e exclusão de motos.

Controle de status: Ativa, Manutenção e Desativada.

Visualização das motos em um mapa interativo (Leaflet + OpenStreetMap).

Histórico de localizações por moto.

Painel de gestão com autenticação de usuários.

🛠️ Tecnologias Utilizadas

Java 17

Spring Boot

Spring Data JPA

Hibernate

H2 Database (simulação local)

Thymeleaf

Leaflet / OpenStreetMap

📂 Estrutura do Projeto
Challenge - JAVA
 └── Mottu_Challenge_Complete
      ├── src/main/java/com/mottu/mototracker
      │   ├── controller   # Controladores REST e páginas
      │   ├── model        # Entidades (Moto, Localização, Usuário)
      │   ├── repository   # Repositórios JPA
      │   └── service      # Regras de negócio
      ├── src/main/resources
      │   ├── db/migration # Scripts Flyway
      │   ├── static       # CSS / JS
      │   └── templates    # Páginas HTML (Thymeleaf)
      ├── build.gradle
      ├── settings.gradle
      └── README.md

▶️ Como Executar o Projeto
Pré-requisitos

Java 17+

Gradle (ou wrapper incluso no projeto)

Passos
# Clone este repositório
git clone https://github.com/hpecora/sprint3-java.git

# Acesse o diretório
cd sprint3-java/Challenge - JAVA/Mottu_Challenge_Complete

# Rode o projeto
./gradlew bootRun


A aplicação ficará disponível em:
👉 http://localhost:8080/login

👨‍💻 Integrantes

Lívia de Oliveira Lopes | RM: 556281

Henrique Pecora | RM: 556612

Santhiago de Gobbi | RM: 98420

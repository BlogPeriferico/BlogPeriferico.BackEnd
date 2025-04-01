# BlogPeriferico.BackEnd

Este repositório contém o backend do projeto **Blog Periférico**, desenvolvido com **Spring Boot** e seguindo arquitetura RESTful.

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Maven**
- **JPA/Hibernate**
- **Banco de Dados PostgreSQL**
- **Swagger/OpenAPI**

## 📂 Estrutura do Projeto

```
BlogPeriferico.BackEnd/
│-- src/
│   ├── main/java/com/blogperiferico/
│   │   ├── controllers/
│   │   ├── services/
│   │   ├── repositories/
│   │   ├── models/
│   │   ├── dto/
│   │   └── config/
│   ├── main/resources/
│   │   ├── application.properties
│   │   └── data.sql
│   └── test/java/com/blogperiferico/
│-- pom.xml
│-- README.md
```

## 📌 Como Configurar o Projeto

1. Clone o repositório:
   ```sh
   git clone https://github.com/BlogPeriferico/BlogPeriferico.BackEnd.git
   ```
2. Acesse o diretório do projeto:
   ```sh
   cd BlogPeriferico.BackEnd
   ```
3. Configure o banco de dados no `application.properties`.
4. Execute o projeto com:
   ```sh
   mvn spring-boot:run
   ```

## 🔥 Testando a API com Swagger

O projeto possui integração com **Swagger/OpenAPI** para facilitar a visualização e testes das rotas. Após rodar o backend, acesse:

🔗 **Documentação Swagger:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Lá, você poderá explorar e testar os endpoints disponíveis.

# Escola Api

## Descrição

Esse projeto é uma API para cadastro de cursos e alunos.
este foi desenvolvido com o intuito de praticar os conhecimentos adquiridos em java e spring boot
e foi criado para aprendizado e não para uso em produção.

## Tecnologias

- [Java](https://www.java.com/pt-BR/) - Linguagem de programação
- [Spring Boot](https://spring.io/projects/spring-boot) - Framework
- [Maven](https://maven.apache.org/) - Gerenciador de dependências
- [H2](https://www.h2database.com/html/main.html) - Banco de dados em memória
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) - Framework para persistência de
  dados
- [Spring validation](https://spring.io/guides/gs/validating-form-input/) - Framework para validação
  de dados
- [Lombok](https://projectlombok.org/) - Biblioteca para reduzir o código boilerplate

## Requisitos do sistema

- Possuir o JDK 17 instalado
- Possuir o Maven instalado
- Uma IDE ou editor de sua preferência ( Opcional )

## Executando o projeto

Para executar o projeto você pode utilizar a IDE de sua preferência, executar através do Maven ou
através do Docker.

### IDE

Para executar o projeto através da IDE, basta importar o projeto como um projeto Maven e executar a
classe `EscolaApiApplication`.

Por padrão aplicação estará disponível em `http://localhost:8080` você pode alterar a porta no
arquivo 'application.yaml' na pasta 'src/main/resources

### Maven

Para executar o projeto através do Maven, basta executar o comando abaixo na raiz do projeto:

```bash
mvn spring-boot:run
```

## Endpoints

### Cursos

| Método |       Endpoint       |              Descrição              |
|:------:|:--------------------:|:-----------------------------------:|
|  GET   |   "/api/v1/curso"    | Retorna todos os cursos cadastrados |
|  GET   | "/api/v1/curso/{id}" | Retorna o curso com o id informado  |
|  POST  |   "/api/v1/curso"    |       Cadastra um novo curso        |
| PATCH  | "/api/v1/curso/{id}" | Atualiza o curso com o id informado |
| DELETE | "/api/v1/curso/{id}" |  Deleta o curso com o id informado  |

Payload para cadastro e atualização de curso:

```json
{
  "titulo": "Spring Boot Essentials",
  "creditos": 4
}
```

### Estudantes

| Método |         Endpoint         |                Descrição                |
|:------:|:------------------------:|:---------------------------------------:|
|  GET   |   "/api/v1/estudante"    | Retorna todos os estudantes cadastrados |
|  GET   | "/api/v1/estudante/{id}" | Retorna o estudante com o id informado  |
|  POST  |   "/api/v1/estudante"    |       Cadastra um novo estudante        |
| PATCH  | "/api/v1/estudante/{id}" | Atualiza o estudante com o id informado |
| DELETE | "/api/v1/estudante/{id}" |  Deleta o estudante com o id informado  |

Payload para cadastro e atualização de estudante:

```json
{
  "nome": "Anderson",
  "data_matricula": "05/12/2014"
}
```

### Matrículas

| Método |         Endpoint         |                Descrição                |
|:------:|:------------------------:|:---------------------------------------:|
|  GET   |   "/api/v1/matricula"    | Retorna todas as matrículas cadastradas |
|  GET   | "/api/v1/matricula/{id}" | Retorna a matrícula com o id informado  |
|  POST  |   "/api/v1/matricula"    |       Cadastra uma nova matrícula       |
| PATCH  | "/api/v1/matricula/{id}" | Atualiza a matrícula com o id informado |
| DELETE | "/api/v1/matricula/{id}" |  Deleta a matrícula com o id informado  |

Payload para cadastro e atualização de matrícula:

```json
{
  "curso_id": 1,
  "estudante_id": 1
}
```

### Postman

Para facilitar o teste dos endpoints, foi criado uma collection no Postman com todos os endpoints e exemplos de requisições.

[Escola.postman_collection.json]( https://github.com/andersonhsporto/escola-api/blob/master/Escola.postman_collection.json)

## Curl

Retorna todos os cursos cadastrados

```bash
curl -X GET "http://localhost:8080/api/v1/curso" -H "accept: */*"
```

Retorna o curso com o id informado

```bash
curl -X GET "http://localhost:8080/api/v1/curso/1" -H "accept: */*"
```

Cadastra um novo curso

```bash
curl -X POST "http://localhost:8080/api/v1/curso" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"creditos\": 4, \"titulo\": \"Spring Boot Essentials\"}"
```

Atualiza o curso com o id informado

```bash
curl -X PATCH "http://localhost:8080/api/v1/curso/1" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"creditos\": 4, \"titulo\": \"Spring Boot Essentials\"}"
```

Deleta o curso com o id informado

```bash
curl -X DELETE "http://localhost:8080/api/v1/curso/1" -H "accept: */*"
```

Retorna todos os estudantes cadastrados

```bash
curl -X GET "http://localhost:8080/api/v1/estudante" -H "accept: */*"
```

Retorna o estudante com o id informado

```bash
curl -X GET "http://localhost:8080/api/v1/estudante/1" -H "accept: */*"
```

Cadastra um novo estudante

```bash
curl -X POST "http://localhost:8080/api/v1/estudante" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"data_matricula\": \"05/12/2014\", \"nome\": \"Anderson\"}"
```

Atualiza o estudante com o id informado

```bash
curl -X PATCH "http://localhost:8080/api/v1/estudante/1" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"data_matricula\": \"05/12/2014\", \"nome\": \"Anderson\"}"
```

Deleta o estudante com o id informado

```bash
curl -X DELETE "http://localhost:8080/api/v1/estudante/1" -H "accept: */*"
```


Retorna todas as matrículas cadastradas

```bash
curl -X GET "http://localhost:8080/api/v1/matricula" -H "accept: */*"
```

Retorna a matrícula com o id informado

```bash
curl -X GET "http://localhost:8080/api/v1/matricula/1" -H "accept: */*"
```

Cadastra uma nova matrícula

```bash
curl -X POST "http://localhost:8080/api/v1/matricula" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"curso_id\": 1, \"estudante_id\": 1}"
```

Atualiza a matrícula com o id informado

```bash
curl -X PATCH "http://localhost:8080/api/v1/matricula/1" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"curso_id\": 1, \"estudante_id\": 1}"
```

Deleta a matrícula com o id informado

```bash
curl -X DELETE "http://localhost:8080/api/v1/matricula/1" -H "accept: */*"
```



## Informações de contato

Caso tenha alguma dúvida, sugestão ou crítica, entre em contato comigo pelo [email](mailto:anderson.higo2@gmail.com)
ou pelo [LinkedIn](https://www.linkedin.com/in/andersonhsporto/).
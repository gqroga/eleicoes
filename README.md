# API Eleições - Java

Uma API para cadastro de pessoas e gestão de eleições.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Git
- Postman
- PostgreSQL
- Maven

## Como Executar a API

1. Certifique-se de ter o Maven e o Java JDK 17 instalados em sua máquina.
2. Clone o repositório da aplicação.
3. O Maven fará o download de todas as dependências automaticamente.
4. Por padrão, a aplicação irá rodar na porta 8080.
5. Utilize o Swagger pela url: http://localhost:8080/swagger-ui/index.html#/ ou qualquer outro serviço de sua preferência para realizar as requisições.

## Recursos da API

Abaixo estão listados os recursos fornecidos pela API e seus respectivos endpoints:

- **Cargos**
  - [POST] `/cargos`: Criar um novo cargo
  - [PUT] `/cargos/{id}`: Alterar um cargo existente
  - [DELETE] `/cargos/{id}`: Deletar um cargo existente
  - [GET] `/cargos`: Listar todos os cargos

- **Eleitores**
  - [POST] `/eleitores`: Criar um novo eleitor
  - [PUT] `/eleitores/{id}`: Alterar um eleitor existente
  - [DELETE] `/eleitores/{id}`: Deletar um eleitor existente
  - [GET] `/eleitores`: Listar todos os eleitores

- **Candidatos**
  - [POST] `/candidatos`: Criar um novo candidato
  - [PUT] `/candidatos/{id}`: Alterar um candidato existente
  - [DELETE] `/candidatos/{id}`: Deletar um candidato existente
  - [GET] `/candidatos`: Listar todos os candidatos

- **Recursos Adicionais**
  - [GET] `/votos/quantidade/{id}`: Verificar quantidade de votos de um candidato específico
  - [GET] `/votos/vencedor`: Verificar o candidato vencedor das eleições

## Observações

Certifique-se de substituir `{id}` pelos identificadores reais dos cargos, eleitores ou candidatos conforme necessário.


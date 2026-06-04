# Sistema Bancário API

## Sobre o projeto

O Sistema Bancário API é uma aplicação backend desenvolvida com Java e Spring Boot com o objetivo de simular operações bancárias básicas, como criação de usuários, gerenciamento de contas, depósitos, saques e histórico de transações.

Este projeto foi criado com fins de estudo para praticar conceitos fundamentais de desenvolvimento backend, arquitetura em camadas, banco de dados relacionais e APIs REST.

---

## Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Lombok
* Hibernate
* Swagger/OpenAPI (futuro)
* Docker (futuro)

---

## Funcionalidades

### Usuários

* Criar usuário
* Buscar usuário por ID
* Listar usuários
* Atualizar usuário
* Remover usuário

### Contas

* Criar conta bancária
* Consultar saldo
* Buscar conta por ID

### Transações

* Realizar depósito
* Realizar saque
* Consultar histórico de transações

---

## Estrutura do Projeto

```text
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── exception
 ├── config
 └── enums
```

### Responsabilidades

* Controller: recebe as requisições HTTP.
* Service: contém as regras de negócio.
* Repository: comunicação com o banco de dados.
* Entity: mapeamento das tabelas.
* DTO: transferência de dados entre cliente e API.
* Exception: tratamento de erros.
* Enums: tipos fixos utilizados no sistema.

---

## Modelagem Inicial

### User

| Campo | Tipo   |
| ----- | ------ |
| id    | Long   |
| nome  | String |
| email | String |

### Account

| Campo   | Tipo       |
| ------- | ---------- |
| id      | Long       |
| saldo   | BigDecimal |
| user_id | Long       |

### Transaction

| Campo      | Tipo            |
| ---------- | --------------- |
| id         | Long            |
| valor      | BigDecimal      |
| tipo       | TransactionType |
| account_id | Long            |

### TransactionType

```java
DEPOSITO
SAQUE
TRANSFERENCIA
```

---

## Relacionamentos

```text
User 1 ---- 1 Account

Account 1 ---- N Transactions
```

---

## Fluxo de Operações

### Depósito

1. Usuário informa o valor.
2. O sistema valida o valor.
3. O saldo da conta é atualizado.
4. Uma transação do tipo DEPOSITO é registrada.

### Saque

1. Usuário informa o valor.
2. O sistema verifica saldo disponível.
3. O saldo é atualizado.
4. Uma transação do tipo SAQUE é registrada.

---

## Próximas Implementações

* [ ] Categorias de transações
* [ ] Transferência entre contas
* [ ] Histórico filtrado por período
* [ ] Validações avançadas
* [ ] Tratamento global de exceções
* [ ] Documentação Swagger
* [ ] Autenticação com JWT
* [ ] Docker
* [ ] Deploy em nuvem

---

## Objetivos de Aprendizado

Este projeto tem como foco o estudo de:

* Programação Orientada a Objetos
* APIs REST
* Spring Boot
* JPA/Hibernate
* PostgreSQL
* Arquitetura em Camadas
* DTOs
* Relacionamentos entre entidades
* Boas práticas de desenvolvimento backend

```
```

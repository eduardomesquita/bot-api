# The Bot API

A simple sample [Spring Boot](http://projects.spring.io/spring-boot/) application, using [Liquibase](http://www.liquibase.org/) to control the database.

## Requires

- Java (>= 1.8)
- Apache Maven (>= 3.3.9)
- Docker (>= 1.11.2)
- Docker Compose (>= 1.8.0)

## Create Docker Network
```bash
docker network create bot_api
```

## Run database PostgreSQL with Docker

First, set your environment variables

```bash
export POSTGRES_HOST=localhost
export POSTGRES_PORT=32768
export POSTGRES_USER=postgres
export POSTGRES_DATABASE=postgres
```

Change to database directory

```bash
cd docker/postgres
```

Run Compose

```bash
docker-compose up
```

## Run Spring-Boot App with Docker

Change to maven directory

```bash
cd codigo-fonte
```

Build & Liquibase

```bash
mvn clean package liquibase:update
```

Copy jar

```bash
cp target/bot-api-0.0.1-SNAPSHOT.jar ../docker/spring/app.jar
```

Change to docker app directory
```bash
cd ../docker/spring
```

## Run docker compose 
```bash
docker-compose up --build
```

## Testing

#### Create a Bot

```bash
curl -X POST http://localhost:8080/bots -H 'content-type: application/json' -d '{"name": "Bot 1"}'
```

Response code:

```json
HTTP status 201 created
```

Output:

```json

{
    "id": "0659db0b-8568-4f4e-abf7-c845eed53942",
    "name": "Bot 1"
}
```

#### Search a Bot

```bash
curl -X GET  http://localhost:8080/bots/0659db0b-8568-4f4e-abf7-c845eed53942
```

Response code:

```json
HTTP status 200 OK
```

Output:

```json
{
    "id": "0659db0b-8568-4f4e-abf7-c845eed53942",
    "name": "Bot 1"
}
```

#### Replace a Bot Name

```bash
curl -X PUT http://localhost:8080/bots/0659db0b-8568-4f4e-abf7-c845eed53942 -H 'content-type: application/json' -d '{"name": "Bot 2"}'
```

Response code:

```json
HTTP status 200 OK
```

#### Delete a Bot

```bash
curl -X DELETE http://localhost:8080/bots/0659db0b-8568-4f4e-abf7-c845eed53942 -H 'content-type: application/json'
```

Response code:

```json
HTTP status 200 OK
```

#### Create a Message

```bash
curl -X POST http://localhost:8080/messages -H 'content-type: application/json' -d '{"conversationId": "7665ada8-3448-4acd-a1b7-d688e68fe9a1", "timestamp": "2018-11-16T23:30:52.6917722Z", "from": "36b9f842-ee97-11e8-9443-0242ac120002", "to": "16edd3b3-3f75-40df-af07-2a3813a79ce9", "text": "Oi! Como posso te ajudar?" }'
```

Response code:

```json
HTTP status 201 created
```

Output:

```json
{
    "id": "a5d9f749-84e0-4389-a40f-e4c1f55a2ea4",
    "to": "16edd3b3-3f75-40df-af07-2a3813a79ce9",
    "from": "36b9f842-ee97-11e8-9443-0242ac120002",
    "conversationId": "7665ada8-3448-4acd-a1b7-d688e68fe9a1",
    "createdAt": "2018-11-16T23:30:52.6917722Z",
    "text": "Oi! Como posso te ajudar?"
}
```

#### Search a Message

```bash
curl -X GET http://localhost:8080/messages/a5d9f749-84e0-4389-a40f-e4c1f55a2ea4
```

Response code:

```json
HTTP status 200 OK
```

Output:

```json
{
    "id": "a5d9f749-84e0-4389-a40f-e4c1f55a2ea4",
    "to": "16edd3b3-3f75-40df-af07-2a3813a79ce9",
    "from": "36b9f842-ee97-11e8-9443-0242ac120002",
    "conversationId": "7665ada8-3448-4acd-a1b7-d688e68fe9a1",
    "createdAt": "2018-11-16T23:30:52.6917722Z",
    "text": "Oi! Como posso te ajudar?"
}
```

#### Search a Conversation

```bash
curl -X GET http://localhost:8080/messages?conversationId=7665ada8-3448-4acd-a1b7-d688e68fe9a1
```

Response code:

```json
HTTP status 200 OK
```

Output:

```json
[
    {
        "id": "a5d9f749-84e0-4389-a40f-e4c1f55a2ea4",
        "to": "16edd3b3-3f75-40df-af07-2a3813a79ce9",
        "from": "36b9f842-ee97-11e8-9443-0242ac120002",
        "conversationId": "7665ada8-3448-4acd-a1b7-d688e68fe9a1",
        "createdAt": "2018-11-16T23:30:52.6917722Z",
        "text": "Oi! Como posso te ajudar?"
    }
]
```
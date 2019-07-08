# The Bot API

A simple sample [Spring Boot](http://projects.spring.io/spring-boot/) application, using [Liquibase](http://www.liquibase.org/) to control the database.

## Requires

- Java (>= 1.8)
- Apache Maven (>= 3.3.9)
- Docker (>= 1.11.2)
- Docker Compose (>= 1.8.0)


## Build
```bash
mvn clean package
```

## Configure liquibase
- Create schema 'public' in the database postgres
```bash
mvn liquibase:update
```

## Run
```bash
mvn spring-boot:run
```

## Testing
Create a Bot 
```bash
curl -X POST http://localhost:8080/bots -H 'content-type: application/json' -d '{"name": "<NAME>"}'
```

Search a Bot
```bash
curl -X GET  http://localhost:8080/bots/<ID>
```

Replace a Bot Name
```bash
curl -X PUT http://localhost:8080/bots/<ID> -H 'content-type: application/json' -d '{"name": "<NAME>"}'
```

Delete a Bot
```bash
curl -X DELETE http://localhost:8080/bots/<ID> -H 'content-type: application/json'
```

Create a Message
```bash
curl -X POST http://localhost:8080/messages -H 'content-type: application/json' -d '{"conversationId": "7665ada8-3448-4acd-a1b7-d688e68fe9a1", "timestamp": "2018-11-16T23:30:52.6917722Z", "from": "36b9f842-ee97-11e8-9443-0242ac120002", "to": "16edd3b3-3f75-40df-af07-2a3813a79ce9", "text": "34434343}'
```

Search a Message
```bash
curl -X GET http://localhost:8080/messages/<ID>
```

Search a Conversation
```bash
curl -X GET http://localhost:8080/messages?conversationId=<conversationId>
```





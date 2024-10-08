# springboot-with-graphql

This project is a simple example of how to use GraphQL with Spring Boot.

Status: subscription is not working with jpa. Flux has to use reactive. Query is working well.

## How to run

1. Clone this repository
2. Run the command `mvn spring-boot:run`
3. Access the URL `http://localhost:8075/graphiql`
4. Run the query below:

```graphql
query getMessagesForChat($id : ID!) {
  getMessagesByChatId(id: $id) {
    id
    message
    user {
      id
      firstName
      lastName
    }
  }
}

// variables
{
  "id": "1"
}
```

```graphql
subscription streamChatMessages($id : ID!) {
  streamMessagesForChat(id: $id) {
    id
    message
    user{
      id
      firstName
      lastName
    }
  }
}

// variables
{
  "id": "1"
}
```

## Technologies
- Spring Boot
- GraphQL
- MySQL
- JPA


## Use postman Learning Projects

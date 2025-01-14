# Spring Boot and GraphQL
- Access: http://localhost:8080/graphiql?path=/graphql
This repo contains:
- Spring Boot and Graphql
- Frontend With React, Next.js and TailwindCSS

---

![Screenshot 2024-07-11 at 11 20 30](https://github.com/amigoscode/spring-boot-graphql/assets/154710368/0bc4b647-92dd-43af-98fb-fdafa1e05913)

# Type Query - Type Mutation

- Query: Fetch all data
```graphql
query {
    getAllUsers{
        id
        name
    }
}
```
```json
{
  "data": {
    "getAllUsers": [
      {
        "id": "1",
        "name": "Mama Samba"
      },
      {
        "id": "2",
        "name": "Jamila"
      }
    ]
  }
}
```
- Fetch data by id
```graphql

query {
    bookById(id: 1){
        id
        name
    }
}
```
```json
{
  "data": {
    "bookById": {
      "id": "1",
      "name": "Quran"
    }
  }
}
```

- Mutation: Modify data(create, update, delete)

```graphql
mutation {
  login(email: "newuser@example.com", password: "password123") {
    success
    message
    token
  }
}
```
- Response
```json
{
  "data": {
    "login": {
      "success": true,
      "message": "Login successful",
      "token": "fake-jwt-token"
    }
  }
}
```
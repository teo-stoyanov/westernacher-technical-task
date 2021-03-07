# Simple REST Service

Service perform a CRUD operation on simple account object

## Requirements:
 - Java 11
 - Gradle 6.8.2

## Technical stack
 - Spring boot 2.4.3
 - Hibernate
 - H2 Database

## Installation:
- clone Git Repo
- run in your local environment/IDE

### User information:
- starting the application, load objects in the H2 database

### CRUD Endpoints
- POST http://localhost:8080/api/v1/accounts
with sample body  - {
  "firstName" : "Adam",
  "lastName" :  "Lee",
  "email" : "adam.lee@work.com",
  "birthdate" : "2020-05-05"
  }
- GET by ID http://localhost:8080/api/v1/accounts/id
- GET all(paged) sorted by criteria and direction http://localhost:8080/api/v1/accounts?criteria=birthdate&direction=desc
- DELETE by ID http://localhost:8080/api/v1/accounts/id
- PUT sample update http://localhost:8080/api/v1/accounts/id with body {
  "firstName" : "Adam",
  "lastName" :  "Lee",
  "email" : "adam.lee@home.com",
  "birthdate" : "2020-05-05"
  }
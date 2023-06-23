# Kineo Technical Test Setup

## Software Requirements

- Java 8 SDK
- Apache Maven
- MySQL Server 5.6

## Database Setup

### 1. For non-ARM instances (local mysql installation of v5.6)
Run `setup.sql` against your database to create the schema and test data.

### 2. For ARM (Apple Silicon M1/M2) instances  
The database is dockerized using `liupeng0518/mysql:5.7-arm64` image where the database is initialized using `docker/data.sql` with a test user.

To start the database (on a machine with Docker engine running) use, 
```shell
cd docker
docker compose up --build
```

## Application Configuration

Update `src/main/resources/application.properties` with your database username and password.

## Build and Run

The project is a spring boot project. The following command will build the module, run tests and start an application server:

`mvn install spring-boot:run`

## Accessing the application

Example endpoints:

`http://localhost:8080/company/1`
`http://localhost:8080/employee/1`
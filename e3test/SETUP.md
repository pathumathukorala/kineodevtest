# Kineo Technical Test Setup

## Software Requirements

- Java 8 SDK
- Apache Maven
- MySQL Server 5.6

## Database Setup

Run `setup.sql` to create the schema and test data.

## Application Configuration

Update `src/main/resources/application.properties` with your database username and password.

## Build and Run

`mvn install spring-boot:run`

## Accessing the application

Example endpoints:

`http://localhost:8080/company/1`
`http://localhost:8080/employee/1`


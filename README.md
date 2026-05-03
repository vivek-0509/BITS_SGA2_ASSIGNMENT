# Library Management System (BITS SGA-2 Assignment)

Spring Boot web application that manages **Authors** and **Books** with full
Create / Read / Update functionality, JSP views, an inner-join custom query,
and unit tests.

See [REPORT.md](REPORT.md) for the full submission write-up (approach, ER
design, implementation details, challenges).

## Quick start

```bash
mvn spring-boot:run
```

- Home: <http://localhost:8080/>
- Authors: <http://localhost:8080/authors>
- Books: <http://localhost:8080/books>
- H2 console: <http://localhost:8080/h2-console> (`jdbc:h2:mem:librarydb`, user `sa`, no password)

## Run tests

```bash
mvn test
```

10 tests across `BookRepositoryTest`, `BookServiceTest`, and
`AuthorServiceTest`.

## Stack

- Spring Boot 3.2 (Web, Data JPA, Validation)
- JSP + JSTL (Jakarta EE 9+)
- H2 in-memory database
- JUnit 5 + Mockito + AssertJ
- Java 17, Maven

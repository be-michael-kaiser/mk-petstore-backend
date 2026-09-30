# mk-petstore-backend

Spring Boot REST API for the React-based pet store migration. It uses Spring
Data JPA with an in-memory H2 database and seeds sample pets on startup.

## Requirements

- Java 25
- Maven 3.9+

## Running

From this directory, start the backend with:

```bash
mvn spring-boot:run
```

The API is available at <http://localhost:8080/api/pets>. The OpenAPI
documentation is available at <http://localhost:8080/swagger-ui.html>.

The database is in memory, so data is reset when the application stops.

## Testing

```bash
mvn test
```

## Production build

```bash
mvn package
java -jar target/mk-petstore-backend-1.0.0.jar
```
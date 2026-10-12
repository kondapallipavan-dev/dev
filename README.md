# dev-service

Spring Boot service for the [kondapallipavan-dev/dev](https://github.com/kondapallipavan-dev/dev) repository.

- Java 21
- Spring Boot 4.1.1
- REST API + Actuator health
- In-memory item store (no database)

## Run

```bash
./mvnw spring-boot:run
```

On Windows:

```bat
mvnw.cmd spring-boot:run
```

The service listens on `http://localhost:8080`.

## Endpoints

| Method | Path | Description |
| --- | --- | --- |
| GET | `/api/v1/hello` | Greeting. Optional `?name=` |
| GET | `/api/v1/items` | List items |
| GET | `/api/v1/items/{id}` | Get one item |
| POST | `/api/v1/items` | Create an item |
| DELETE | `/api/v1/items/{id}` | Delete an item |
| GET | `/actuator/health` | Liveness/health |

### Examples

```bash
curl http://localhost:8080/api/v1/hello?name=Pavan

curl -X POST http://localhost:8080/api/v1/items \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"Notebook\",\"description\":\"Dev notes\"}"

curl http://localhost:8080/api/v1/items
```

## Test

```bash
./mvnw test
```

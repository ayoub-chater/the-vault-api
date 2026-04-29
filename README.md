# The Vault - API

> Spring Boot REST API for The Vault, a premium fashion lifestyle platform.

---

## Tech Stack

- Java 21
- Spring Boot 4.x
- PostgreSQL
- Flyway (database versioning)
- Spring Security (JWT authentication)
- Stripe (payments)
- AWS S3 (file storage)
- Lombok

---

## Prerequisites

- Java 21+
- Maven 3.9+
- Docker & Docker Compose
- PostgreSQL (or use Docker)

---

## Run Locally

```bash
# 1. Clone the repository
git clone https://github.com/ayoub-chater/the-vault-api.git
cd the-vault-api

# 2. Set up environment variables
cp .env.example .env
# Edit .env with your values

# 3. Start the database
docker-compose up -d db

# 4. Run the application
./mvnw spring-boot:run
```

---

## Environment Variables

See [`.env.example`](.env.example) for all required variables.
Never commit the `.env` file.

---

## API Documentation

Once the app is running:
```
http://localhost:8080/swagger-ui.html
```

---

## Running Tests

```bash
./mvnw test
```

---

## Branch Strategy

| Branch | Purpose |
|--------|---------|
| `main` | Production — protected, PR only |
| `dev`  | Integration — protected, PR only |
| `feature/xxx` | Feature branches |

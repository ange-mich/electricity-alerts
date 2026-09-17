# Electricity Alerts

Spring Boot application that monitors household electricity credits and sends low-balance email alerts.

## Configuration

Copy `.env.example` to a local environment configuration and replace every placeholder. Never commit real credentials. Required variables are `ADMIN_USERNAME`, `ADMIN_PASSWORD`, `MAIL_USERNAME`, and `MAIL_PASSWORD`.

The admin dashboard is protected by Spring Security at `/admin`. The H2 console is disabled, and production database schema changes default to `validate`.

## Run locally

Set the variables from `.env.example`, then run:

```powershell
./mvnw.cmd spring-boot:run
```

Open `http://localhost:8080/admin` and authenticate with the configured admin credentials.

## Test

```powershell
./mvnw.cmd test
```
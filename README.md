# Splitinam

A backend REST API for splitting shared expenses between groups - built with Spring Boot.
> Create a session with a total amount and participant list, receive a 6-character session code, and track each person's payment status via API calls.

## Features

- Create a shared expense session with a unique code
- Automatically calculate the per-person share
- Track payment status per participant via API
- Sessions identified by a short 6-character code
- Persistent storage - sessions survive page refreshes

## Architecture

```
splitinam/
├── api/                        # Spring Boot backend
│   └── src/main/java/com/splitinam/api/
│       ├── controller/         # REST endpoints
│       ├── service/            # Business logic
│       ├── repository/         # Data access layer (JPA)
│       └── model/              # JPA entities
```

The backend follows a standard **Controller → Service → Repository** layered architecture, with Model entities mapped to the database via JPA/Hibernate. Sessions are persisted in a MySQL database, with JSON column support for participant lists and payment states.

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3 |
| ORM | Spring Data JPA (Hibernate) |
| Database | MySQL |
| Build tool | Maven |
| API style | RESTful JSON |

## API Endpoints

### Create a session
```
POST /api/sessions
```
**Request body:**
```json
{
  "title": "Dinner at Forto Dvaras",
  "total": 87.50,
  "paidBy": "Matas",
  "people": ["Matas", "Jonas", "Gabija"]
}
```
**Response:**
```json
{
  "id": 1,
  "sessionCode": "A3F9BC",
  "title": "Dinner at Forto Dvaras",
  "total": 87.50,
  "paidBy": "Matas",
  "perPerson": 29.17,
  "people": ["Matas", "Jonas", "Gabija"],
  "payments": {},
  "createdAt": "2025-05-14T18:32:00"
}
```

### Get a session by code
```
GET /api/sessions/{sessionCode}
```

### Mark a person as paid
```
PATCH /api/sessions/{sessionCode}/pay?person=Jonas
```

## Running Locally

### Prerequisites
- Java 17+
- Maven
- MySQL running locally

### 1. Set up the database

```sql
CREATE DATABASE splitinam;
```

### 2. Configure credentials

Edit `api/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/splitinam
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 3. Start the backend

```bash
cd api
mvn spring-boot:run
```

API will be available at `http://localhost:8080`

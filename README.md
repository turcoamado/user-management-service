# User Management Service

A simple user management REST API built with Kotlin, Spring Boot 3, Spring Security, and JWT for authentication and authorization.

## Description

This service provides basic user authentication, registration, and role-based access control. It uses PostgreSQL for data persistence and supports token-based authentication with JWT (access and refresh tokens).

## Features

- User registration
- User authentication with JWT
- Token refresh endpoint
- Fetch user by ID or email
- Role-based authorization
- Preloaded roles and admin user via `data.sql`

## Technologies

- Kotlin
- Spring Boot 3
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT (io.jsonwebtoken)
- MapStruct

## Getting Started

### Prerequisites

- Java 21+
- Kotlin
- Gradle
- PostgreSQL

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/turcoamado/user-management-service.git
   ```

2. Configure the database
   Ensure you have a running PostgreSQL instance and create the database manually:
   ```sql
   CREATE DATABASE UserManagementService;
   ```

3. Configure the environment
   Modify [application.properties](src/main/resources/application.properties) with your PostgreSQL credentials if needed.
   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/UserManagementService
   spring.datasource.username=your_postgres_user
   spring.datasource.password=your_postgres_password
   ```

4. Add your secret JWT key in an environment variable:

   This service uses JWT (JSON Web Tokens) for authentication. To ensure security, you must define a signing key through an environment variable.
   The key must be at least 32 characters long.

   #### Setting the environment variable in IntelliJ IDEA
   - Go to **Run** > **Edit Configurations...** 
   - Select your application’s run configuration. 
   - Under **"Environment variables"**, click the `...` button. 
   - Add a new variable:
     - Name: JWT_KEY 
     - Value: your secret key (at least 32 characters). 
   - Click OK and Apply.

5. Run the application:
   ```bash
   ./gradlew bootRun
   ```
   When you run the application, data in [data.sql](src/main/resources/sql/data.sql) file will be added into the database created.

## Endpoints

### Authentication

- **User authentication**
   ```
   POST /auth
   ```

  Request:
  ```json
  {
    "email": "admin@example.com",
    "password": "admin123"
  }
  ```

  Response:
  ```json
  {
    "accessToken": "...",
    "refreshToken": "..."
  }
  ```

- **Refresh token**
   ```
   POST /auth/refresh
   ```
  Request:
  ```json
  {
    "refreshToken": "your_refresh_token_here"
  }
  ```

### Users

- **Create a new user**
   ```
   POST /users
   ```
   Request:
  ```json
  {
    "name": "Ariel",
    "lastName": "Ortega",
    "email": "ariel.ortega@example.com",
    "password": "elburrito"
  }
  ```

- **Get user by ID**
   ```
   GET /users/{id}
   ```

- **Get user by email**
   ```
   GET /users/email/{email}
   ```

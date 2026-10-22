# HealStack 🏥

**HealStack** is a production-ready Healthcare Management RESTful API engineered with **Spring Boot 3**, **Java 17**, and **Neon PostgreSQL**. Built with a clean layered architecture, the system manages core healthcare domains—patients, doctors, departments, appointments, and insurance—while enforcing strict relational integrity, Jakarta request validation, and clean DTO mapping.

---

## 🚀 Features
* **Domain Management:** Full CRUD operations across Patients, Doctors, Departments, Appointments, and Insurance records.
* **Relational Data Modeling:** One-to-Many relational linking and foreign key enforcement managed via Hibernate/JPA.
* **Request Validation:** Strict payload validation using Jakarta Annotations (`@NotNull`, `@Past`, `@Size`, `@Email`).
* **DTO Decoupling:** Complete separation of entity and presentation layers using `ModelMapper`.
* **Cloud Database Persistence:** Serverless PostgreSQL integration hosted on Neon DB with HikariCP connection pooling.
* **Interactive API Documentation:** Built-in Swagger UI powered by OpenAPI 3 for dynamic testing.
* **Clean Architecture:** Standard separation of concerns (`Controller` $\rightarrow$ `Service` $\rightarrow$ `Repository` $\rightarrow$ `Entity`).

---

## 🛠️ Tech Stack

| Domain | Technology |
| :--- | :--- |
| **Language & Core** | Java 17 |
| **Framework** | Spring Boot 3.3.1 (Spring Web MVC, Spring Data JPA) |
| **Database** | Neon PostgreSQL (Serverless Cloud Database) |
| **ORM & Persistence** | Hibernate 6.5.2, HikariCP |
| **Validation & Mapping** | Jakarta Bean Validation, ModelMapper |
| **Documentation** | Springdoc OpenAPI / Swagger UI |
| **Build & Utilities** | Maven / Gradle, Lombok |

---

## 📐 System Architecture & Data Model

+-----------------+           +---------------------+           +----------------+
|     Patient     |           |     Appointment     |           |     Doctor     |
+-----------------+           +---------------------+           +----------------+
| id (PK)         | 1       * | id (PK)             | *       1 | id (PK)        |
| name            |<----------| appointmentTime     |---------->| name           |
| email (Unique)  |           | reason              |           | email (Unique) |
| dateOfBirth     |           | status (ENUM)       |           | specialization |
+-----------------+           +---------------------+           +----------------+


### Key Relationships
* **Patient $\rightarrow$ Appointment:** `@OneToMany` — A patient can schedule multiple appointments.
* **Doctor $\rightarrow$ Appointment:** `@OneToMany` — A doctor can handle multiple scheduled consults.
* **Appointment:** Acts as the owning side holding relational foreign keys (`patient_id`, `doctor_id`).

---

## 📂 Project Structure

```text
HealStack
├── src/main/java/org/example
│   ├── controller      # REST API Endpoints & Request Mapping
│   ├── service         # Business Logic Layer
│   ├── repository      # Spring Data JPA Repositories
│   ├── entity          # Database Entities (Patient, Doctor, Appointment, etc.)
│   ├── dto             # Data Transfer Objects & Validation Payload Schemas
│   ├── config          # Bean & ModelMapper Configurations
│   └── Main.java       # Application Entry Point
│
├── src/main/resources
│   └── application.properties / application.yml
│
├── pom.xml / build.gradle
└── .gitignore
⚙️ Configuration & Environment Setup
1. Externalized Database Credentials
To ensure sensitive database credentials are not hardcoded, configure the following environment variables in your IDE or system environment:

DB_USERNAME: Your Neon PostgreSQL username.

DB_PASSWORD: Your Neon PostgreSQL password.

2. application.properties
Properties
# Cloud PostgreSQL Configuration
spring.datasource.url=jdbc:postgresql://your-neon-host.neon.tech/neondb?sslmode=require
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

# JPA / Hibernate Settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
⏹️ Running the Application
Using Maven
Bash
# Run locally
mvn spring-boot:run

# Build executable JAR
mvn clean package
java -jar target/HealStack-1.0-SNAPSHOT.jar
Using Gradle
Bash
# Run locally
./gradlew bootRun

# Build executable JAR
./gradlew build
java -jar build/libs/HealStack-1.0-SNAPSHOT.jar
The server starts at http://localhost:8080.

📡 API Overview & Endpoints
Interactive Swagger UI documentation is available at:http://localhost:8080/swagger-ui/index.html

Base URL: /api
Patient Management (/api/patients)
POST /api/patients – Create new patient record (validates past date of birth, non-blank fields, and unique email).

GET /api/patients – Retrieve all registered patients.

GET /api/patients/{id} – Fetch patient details by ID.

Doctor Management (/api/doctors)
POST /api/doctors – Register new doctor with medical specialization.

GET /api/doctors – List all active doctors.

GET /api/doctors/{id} – Retrieve doctor details by ID.

Appointment Management (/api/appointments)
POST /api/appointments – Schedule a new appointment linking patientId and doctorId.

GET /api/appointments – Fetch all appointments with populated Patient and Doctor details.

GET /api/appointments/patient/{patientId} – Retrieve all appointments for a specific patient.

Department & Insurance Services
GET /api/departments – Retrieve department mappings.

GET /api/insurance/{id} – Fetch insurance coverage details.

🧪 Testing
Execute unit and integration tests using your preferred build tool:

Bash
# Maven
mvn test

# Gradle
./gradlew test
📌 Future Enhancements
[ ] Spring Security & JWT: Implement token-based authentication and role-based access control (Admin, Doctor, Patient).

[ ] Global Exception Handling: Add a @ControllerAdvice component for structured custom error responses (400 Bad Request, 404 Not Found).

[ ] Pagination & Sorting: Optimize large relational queries using Pageable parameters.

👤 Author
Paulastha Maitra

GitHub: @paulmaitra10

📄 License
This project is open-source and available for educational and recruitment showcase purposes.
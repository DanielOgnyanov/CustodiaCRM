# CustodiaCRM

CustodiaCRM is a Spring Boot based Customer Relationship Management (CRM) system designed for managing users, customers, contacts, and opportunities efficiently.

---

## Features

- User management with role-based access
- Customer and contact management
- Opportunity tracking
- Data persistence with MySQL and JPA
- Automatic database initialization on startup
- RESTful API support with Spring Boot
- Lombok for reducing boilerplate code
- OpenAPI documentation integration
- **Docker support for easy containerized deployment**
- **Continuous Integration (CI) pipeline configured with GitHub Actions**

---

## Technologies Used

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- MySQL
- Lombok
- H2 (for testing/dev in-memory database)
- SpringDoc OpenAPI
- Docker
- GitHub Actions (CI/CD)

---

## Getting Started

### Prerequisites

- Java 17 or higher (if running locally without Docker)
- Maven 3.8+
- MySQL database (or use H2 for testing)
- Docker (for containerized deployment)
- Git (optional, for cloning the repo)

### Installation

1. Clone the repository

```bash
git clone https://github.com/your-username/CustodiaCRM.git
cd CustodiaCRM

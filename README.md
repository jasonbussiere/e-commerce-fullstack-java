# E-Commerce Storefront | Java & Jakarta EE

A full-stack e-commerce web application built with Java, Jakarta EE, Maven, and MariaDB. The project simulates a digital storefront for video games, including product browsing, cart management, user authentication, and database-backed order flows.

This project was developed as a team-based academic project. My primary contributions focused on the data layer and application logic that support the storefront experience.

## Run the application with Docker

The fastest way to try the application is Docker Compose. It starts the Java web app and MariaDB, initializes the database schema and sample data, and waits for the database to become healthy before starting the app.

### Prerequisites

- Docker Desktop, or Docker Engine with the Docker Compose plugin

### Start

Clone the repository and start the services from the project root:

```bash
git clone https://github.com/jasonbussiere/e-commerce-fullstack-java.git
cd e-commerce-fullstack-java
docker compose up --build
```

When the containers are ready, open [http://localhost:8080](http://localhost:8080).

To stop the services, press `Ctrl+C`, then run:

```bash
docker compose down
```

MariaDB data is stored in a named Docker volume and persists when the services are stopped. To remove the containers **and** the database volume (which deletes the stored database data), run:

```bash
docker compose down -v
```

### Optional local credentials and port

Compose has local-demo defaults. You can override them by setting `DB_USERNAME`, `DB_PASSWORD`, `DB_ROOT_PASSWORD`, or `APP_PORT` in your shell or a `.env` file in the project root. For example:

```dotenv
DB_USERNAME=ecommerce_app
DB_PASSWORD=change-this-local-password
DB_ROOT_PASSWORD=change-this-root-password
APP_PORT=8080
```

These defaults and sample values are for local development only; use secure secrets for any deployed environment. See [DOCKER-QUICKSTART.md](DOCKER-QUICKSTART.md) for the short version of the Docker instructions.

## My Role and Contributions

I was responsible for much of the backend foundation that powers the storefront, especially:

- Database design and schema development using MariaDB
- Defining entities, relationships, and constraints for users, products, cart data, and related records
- Building the DAO layer for CRUD operations and database access logic
- Implementing the service layer to manage business rules, validation, and orchestration between the persistence and controller layers
- Supporting integration between the persistence layer and the MVC web application flow

My work centered on ensuring the application could reliably store and retrieve data while keeping business logic maintainable and aligned with the web layer.

## Overview

The application is structured around a classic MVC pattern:

- Model: domain entities and persistence logic
- View: JSP pages and HTML/CSS front end
- Controller: Java Servlets handling requests and user actions

The project includes a product catalog, shopping cart logic, account creation and login, localized UI text, and database-backed persistence for users, products, and cart data.

## Features

- Product catalog with game listings
- Product search and browsing
- Shopping cart management
- User sign-up and login flow
- Session-based cart and account state
- Checkout flow simulation
- MariaDB persistence
- Multilingual support for interface text
- Docker Compose local environment with MariaDB and Tomcat

## Tech Stack

- Java 17
- Jakarta Servlet API
- JSP / JSTL
- Maven
- MariaDB
- Apache Tomcat 10
- Docker and Docker Compose
- HTML / CSS / JavaScript

## Architecture

The project follows a layered structure to separate responsibilities:

- Controllers: request handling and page navigation
- Services: business logic and orchestration
- DAOs / persistence layer: database access and query execution
- Models: domain objects
- Views: JSP pages rendered to the user

## Project Structure

```text
.
├── db/
│   ├── schema.sql
│   ├── data_users.sql
│   └── data_videogames.sql
├── src/
│   ├── main/
│   │   ├── java/
│   │   ├── resources/
│   │   └── webapp/
│   └── test/
├── Dockerfile
├── docker-compose.yaml
├── DOCKER-QUICKSTART.md
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## Database Design

The application uses MariaDB to persist key entities:

- users
- videogames
- cart
- cart_items

The schema uses relational constraints, including foreign keys and unique constraints, to support the shopping workflow from catalog browsing to cart updates. On the first Compose startup, the SQL files in `db/` initialize the database with the schema and sample data.

## Screenshots and Diagrams

### Application Schema

![Schema](https://github.com/user-attachments/assets/db574ebc-9b19-4ea7-b5b5-f481ae967e79)

### Class Diagram

![Class Diagram](https://github.com/user-attachments/assets/55ca3466-9081-4558-969d-51fc36af1508)

## Manual Build and Deployment

Docker Compose is the recommended way to run the complete application locally. To build the WAR without Docker, use JDK 17+ and run:

```bash
./mvnw clean package
```

Deploy the generated WAR from `target/` to a compatible Tomcat 10 server. The application reads its database settings from environment variables:

- `DB_HOST` — database hostname
- `DB_NAME` — database name (`ecommerce-assassins_db` in Compose)
- `DB_PORT` — database port (defaults to `3306`)
- `DB_USERNAME` — database username
- `DB_PASSWORD` — database password

When running outside Compose, configure these variables for the Tomcat process and provide a MariaDB database initialized with the SQL files in `db/`. The `db/schema.sql` script selects the `ecommerce-assassins_db` database, so create that database before importing the schema.

## Notes

This project demonstrates practical experience with MVC architecture, JDBC persistence, session management, prepared statements, and integrating a Java web application with a relational database. It is especially relevant to my portfolio because it highlights my work in the data-access and service layers. This is a team-based academic project; the contribution section above describes my role.

## Future Improvements

- Add and expand automated tests
- Improve validation and error handling
- Add admin product management
- Improve UI responsiveness and user experience
- Add CI/CD and deployment automation
- Strengthen the security configuration for production use

## Summary

This project demonstrates Java-based web development with the Jakarta EE stack, relational data modeling, persistence logic, service orchestration, and a Docker Compose environment for local evaluation.
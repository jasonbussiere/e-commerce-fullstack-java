# E-Commerce Storefront | Java & Jakarta EE

A full-stack e-commerce web application built with Java, Jakarta EE, Maven, and MariaDB. The project simulates a digital storefront for video games, including product browsing, cart management, user authentication, and database-backed order flows.

This project was developed as a team-based academic project, and my primary contributions focused on the data layer and application logic that support the storefront experience.

## My Role and Contributions

I was responsible for most of the backend foundation that powers the storefront, especially:

- Database design and schema development using MariaDB
- Defining entities, relationships, and constraints for users, products, cart data, and related records
- Building the DAO layer for CRUD operations and database access logic
- Implementing the service layer to manage business rules, validation, and orchestration between the persistence and controller layers
- Supporting integration between the persistence layer and the MVC web application flow

This meant my work centered on ensuring the application could reliably store and retrieve data while keeping the business logic clean, maintainable, and aligned with the web layer.

## Overview

The application is structured around a classic MVC pattern:
- Model: domain entities and persistence logic
- View: JSP pages and HTML/CSS front-end
- Controller: Java Servlets handling requests and user actions

The project includes a product catalog, shopping cart logic, account creation/login, localized UI text, and database-backed persistence for users, products, and cart data.

## Features

- Product catalog with game listings
- Search and browsing experience for products
- Shopping cart management
- User sign-up and login flow
- Session-based cart and account state
- Checkout flow simulation
- Database-backed persistence using MariaDB
- Multilingual support for interface text
- Tomcat deployment-ready Java web app

## Tech Stack

- Java 17
- Jakarta Servlet API
- JSP / JSTL
- Maven
- MariaDB
- Apache Tomcat
- HTML / CSS / JavaScript

## Architecture

The project follows a layered structure to separate responsibilities and improve maintainability:

- Controllers: request handling and page navigation
- Services: business logic and orchestration
- DAOs / Persistence layer: database access and query execution
- Models: domain objects
- Views: JSP pages rendered to the user

This design allows the application to scale in complexity while keeping the web layer and the persistence layer decoupled.

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
│   │   │   └── edu/tp/a11/assassinscreedstore/
│   │   ├── resources/
│   │   └── webapp/
│   └── test/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── README.md
└── .gitignore
```

## Database Design

The application uses MariaDB to persist key entities:

- users
- videogames
- cart
- cart_items

The schema enforces relational integrity with foreign keys and unique constraints, and is designed to support the shopping workflow from catalog browsing to cart updates.

## Screenshots

### Application Schema

![Schema](https://github.com/user-attachments/assets/db574ebc-9b19-4ea7-b5b5-f481ae967e79)

### Class Diagram

![Class Diagram](https://github.com/user-attachments/assets/55ca3466-9081-4558-969d-51fc36af1508)

## Local Development Setup

### Prerequisites

- JDK 17+
- Maven 3.8+
- MariaDB
- Apache Tomcat 10+

### 1. Clone the repository

```bash
git clone https://github.com/jasonbussiere/e-commerce-fullstack-java.git
cd e-commerce-fullstack-java
```

### 2. Create the database

Create a MariaDB database and import the schema file:

```bash
mysql -u <username> -p < db/schema.sql
```

### 3. Configure database access

Create a configuration file based on the project settings used by the repository:

```properties
# src/main/resources/config.properties
# Example values

db.host=localhost
db.name=ecommerce_db
db.port=3306
db.username=your_db_user
db.password=your_db_password
```

The application loads this file at runtime to establish the JDBC connection.

### 4. Build the project

```bash
./mvnw clean package
```

### 5. Run the application

Deploy the generated WAR file to Tomcat or run it in a supported local servlet container.

```bash
./mvnw tomcat:run
```

If the project is being run in a Tomcat environment, deploy the WAR from the target directory.

## Notes

This project is designed as a Java web application that demonstrates practical skills in:

- enterprise-style MVC architecture
- connection pooling and database integration
- user session management
- secure form handling patterns
- CRUD-style persistence for product and customer data
- building a storefront experience from a relational model

From a portfolio perspective, this project is especially relevant because it highlights my work in the data-access and service layers, which are critical for maintaining real-world application integrity, scalability, and maintainability.

## Future Improvements

Potential enhancements for a stronger portfolio version include:

- adding automated tests
- improving validation and error handling
- introducing a REST API layer
- adding admin product management
- improving UI responsiveness and user experience
- integrating CI/CD and deployment automation
- adding a production-ready security layer

## Summary

This project reflects a strong foundation in Java-based web development and demonstrates the ability to design and implement a real-world e-commerce application using the Jakarta EE stack. It is particularly valuable as a portfolio project because it combines backend data modeling, persistence logic, service orchestration, and web-layer integration in a single application.

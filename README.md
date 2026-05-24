# SmartCommerce AI Backend 
A professional Spring Boot backend project built using Java, Spring Boot, PostgreSQL, JPA/Hibernate, Validation, Swagger, and REST APIs.

This project demonstrates industry-level backend development concepts including CRUD operations, DTO validation, exception handling, API documentation, and database integration.

---

##  Features

 Create Product API
 Get All Products API
 Get Product By ID API
 Update Product API
 Delete Product API
 PostgreSQL Database Integration
 DTO-based Request Handling
 Validation using Jakarta Validation
 Global Exception Handling
 Swagger/OpenAPI Documentation
 Layered Architecture (Controller-Service-Repository)
 Spring Data JPA & Hibernate

---

##  Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Swagger/OpenAPI
* Hibernate
* IntelliJ IDEA
* Postman

---

## Project Structure

src/main/java/com/swapnil/smartcommerce

├── controller

├── service

├── repository

├── entity

├── dto

├── exception

├── config

---

##  API Endpoints

### Product APIs

| Method | Endpoint           | Description       |
| ------ | ------------------ | ----------------- |
| POST   | /api/products      | Create Product    |
| GET    | /api/products      | Get All Products  |
| GET    | /api/products/{id} | Get Product By ID |
| PUT    | /api/products/{id} | Update Product    |
| DELETE | /api/products/{id} | Delete Product    |

---

## Swagger Documentation

After running the project:

http://localhost:8080/swagger-ui/index.html

---

##  Database Configuration

PostgreSQL is used as the database.

Example application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/smartcommerce

spring.datasource.username=postgres

spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

---

## Running the Project

### Clone Repository

git clone https://github.com/swapniltalloo/SmartCommerce-AI-Backend.git

### Navigate to Project

cd SmartCommerce-AI-Backend

### Run Application

mvn spring-boot:run

---

##  Sample Request JSON

{
"name": "Samsung S25",
"description": "Graphite",
"price": 33000,
"quantity": 2
}

---

## Future Enhancements

* JWT Authentication
* Role-Based Authorization
* Redis Caching
* Docker Deployment
* AI Product Search
* Payment Integration
* Cloud Deployment

---

##Swagger output
<img width="1901" height="891" alt="image" src="https://github.com/user-attachments/assets/709180ab-92e7-4a69-9a88-cce310f71927" />

##  Author

Swapnil Talloo

GitHub: https://github.com/swapniltalloo

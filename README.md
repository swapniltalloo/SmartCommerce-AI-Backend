# SmartCommerce AI

A production-ready AI-powered e-commerce backend built using Spring Boot, PostgreSQL, Redis, JWT Authentication, and Google Gemini AI.

## Overview

SmartCommerce AI is a scalable backend platform that simulates a real-world e-commerce ecosystem. The system provides secure authentication, role-based access control, product and inventory management, shopping cart functionality, order processing, analytics, caching, and AI-powered product recommendations.

The project was designed with modern backend engineering principles including layered architecture, RESTful APIs, security best practices, caching strategies, containerization, and cloud deployment.

---

## Key Features

### Authentication & Security

* JWT-based authentication and authorization
* Secure password hashing using BCrypt
* Role-Based Access Control (RBAC)
* Admin and User access separation
* Protected API endpoints using Spring Security

### Product Management

* Create, update, delete, and retrieve products
* Product categorization
* Inventory management
* Input validation using Jakarta Validation

### Category Management

* Category CRUD operations
* Product-category relationship management

### Shopping Cart

* Add products to cart
* Update quantities
* Remove products from cart
* View active cart items

### Order Management

* Place orders from cart
* Order history tracking
* Order status updates
* Order cancellation support

### Administrative Features

* Manage products and categories
* View all orders across the platform
* Update order statuses
* Revenue analytics dashboard APIs

### Caching

* Redis integration for high-frequency product queries
* Reduced database load
* Improved response times

### Artificial Intelligence

* Integration with Google Gemini API
* AI-powered product recommendation engine
* Personalized recommendations based on user purchase history

### API Documentation

* Interactive Swagger/OpenAPI documentation
* Endpoint testing directly from browser

---

## System Architecture

```text
Client Applications
        │
        ▼
Spring Boot REST APIs
        │
 ┌──────┼─────────┐
 │      │         │
 ▼      ▼         ▼
Security  Redis   Gemini AI
(JWT)    Cache    Service
 │
 ▼
PostgreSQL Database
```

---

## Technology Stack

### Backend

* Java 21
* Spring Boot 3
* Spring Security
* Spring Data JPA
* Hibernate
* Maven

### Database

* PostgreSQL

### Caching

* Redis

### AI Integration

* Google Gemini API

### Documentation

* Swagger / OpenAPI

### Deployment & DevOps

* Docker
* Render Cloud Platform
* Git & GitHub

---

## Project Structure

```text
src/main/java/com/swapnil/smartcommerce

├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
├── service
└── SmartcommerceApplication
```

The project follows a layered architecture:

* Controller Layer
* Service Layer
* Repository Layer
* Persistence Layer

This separation improves maintainability, scalability, and testability.

---

## Security Architecture

Authentication Flow:

```text
User Login
    │
    ▼
JWT Generation
    │
    ▼
Client Stores Token
    │
    ▼
JWT Sent With Requests
    │
    ▼
JwtAuthFilter Validation
    │
    ▼
Access Granted / Denied
```

Roles:

### USER

* Browse products
* Manage cart
* Place orders
* View order history
* Access AI recommendations

### ADMIN

* Manage products
* Manage categories
* Manage orders
* Update order statuses
* Access analytics endpoints

---

## AI Recommendation Engine

The recommendation engine analyzes a user's historical purchases and leverages Google Gemini to generate personalized product suggestions.

Workflow:

```text
User Orders
      │
      ▼
Purchase History
      │
      ▼
Gemini Prompt Generation
      │
      ▼
AI Recommendation Engine
      │
      ▼
Personalized Suggestions
```

This feature demonstrates the integration of Generative AI within a traditional enterprise backend system.

---

## API Documentation

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

Production Deployment:

```text
https://smartcommerce-ai-backend.onrender.com/swagger-ui/index.html
```

---

## Environment Variables

```env
PGHOST=
PGPORT=
PGDATABASE=
PGUSER=
PGPASSWORD=

REDIS_HOST=
REDIS_PORT=

GEMINI_API_KEY=
```

---

## Docker Support

Build Image:

```bash
docker build -t smartcommerce-ai .
```

Run Container:

```bash
docker run -p 8080:8080 smartcommerce-ai
```

---

## Deployment

### Backend

Deployed on Render Cloud Platform.

### Database

PostgreSQL hosted in the cloud.

### Cache Layer

Redis integration for optimized API performance.

---

## Future Enhancements

* React Frontend
* Payment Gateway Integration
* Elasticsearch-Based Product Search
* Event-Driven Architecture with Kafka
* CI/CD Pipeline using GitHub Actions
* Kubernetes Deployment
* Comprehensive Unit & Integration Testing
* Microservices Migration

---

## Learning Outcomes

Through this project, the following concepts were implemented and explored:

* Spring Boot Application Development
* REST API Design
* JWT Authentication & Authorization
* Spring Security
* ORM with Hibernate/JPA
* PostgreSQL Database Design
* Redis Caching
* Cloud Deployment
* Docker Containerization
* AI Integration using Gemini API
* Enterprise Backend Architecture

---

## Author

**Swapnil Talloo**

B.Tech Student | Backend Developer | AI Enthusiast

GitHub: https://github.com/swapniltalloo

---

If you found this project valuable, consider giving it a star.

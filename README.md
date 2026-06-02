# SmartCommerce AI Backend

A production-style e-commerce backend application built using Java, Spring Boot, PostgreSQL, Redis, JWT Authentication, and Gemini AI integration. The project provides secure REST APIs for product management, cart operations, order processing, admin analytics, and AI-powered product assistance.

---

## Features

### Authentication & Authorization

* User Registration and Login
* JWT-based Authentication
* BCrypt Password Encryption
* Role-Based Access Control (USER / ADMIN)
* Spring Security Integration

### Product Management

* Add Product (Admin)
* Update Product (Admin)
* Delete Product (Admin)
* Get All Products
* Get Product By ID
* Category-wise Product Organization

### Category Management

* Create Category
* View Categories
* Product-Category Relationship Mapping

### Cart Management

* Add Products to Cart
* Update Product Quantity
* Remove Product from Cart
* View Cart Items
* Clear Cart
* Cart Summary with Total Amount

### Order Management

* Place Order from Cart
* View User Orders
* View Order Details
* Cancel Order
* Order Status Tracking
* Automatic Cart Cleanup After Order Placement

### Admin Dashboard

* Update Order Status
* View Revenue Statistics
* Monitor Order Metrics

### Redis Caching

* Redis Integration using Docker
* Product API Response Caching
* Cache Invalidation using @CacheEvict
* Improved API Performance

### AI Integration

* Gemini API Integration
* AI-powered Product Search & Recommendations

### API Documentation

* Swagger/OpenAPI Integration
* Interactive API Testing

---

## Tech Stack

### Backend

* Java 17
* Spring Boot 3
* Spring Security
* Spring Data JPA
* Hibernate

### Database

* PostgreSQL

### Caching

* Redis

### Authentication

* JWT (JSON Web Tokens)

### API Documentation

* Swagger / OpenAPI

### AI

* Google Gemini API

### DevOps

* Docker
* Git
* GitHub

---

## Project Architecture

```text
Client
   ↓
Spring Security
   ↓
Controllers
   ↓
Services
   ↓
Repositories
   ↓
PostgreSQL

           ↓
         Redis
        (Cache)

           ↓
       Gemini AI
```

---

## Database Entities

### User

* id
* username
* password
* role

### Category

* id
* name

### Product

* id
* name
* description
* price
* quantity
* category

### Cart

* id
* user

### CartItem

* id
* quantity
* cart
* product

### Order

* id
* totalAmount
* status
* user

### OrderItem

* id
* quantity
* price
* order
* product

---

## Security Features

### User Role

Can:

* Browse Products
* Manage Cart
* Place Orders
* View Orders
* Cancel Orders

### Admin Role

Can:

* Manage Products
* Manage Categories
* Update Order Status
* Access Revenue Dashboard

---

## Redis Caching

Implemented caching for frequently accessed product APIs.

```java
@Cacheable("products")
public List<Product> getAllProducts()
```

Cache invalidation:

```java
@CacheEvict(value = "products", allEntries = true)
```

Benefits:

* Reduced Database Queries
* Faster Response Time
* Better Scalability

---

## API Endpoints

### Authentication

```http
POST /auth/register
POST /auth/login
```

### Products

```http
GET    /api/products
GET    /api/products/{id}
POST   /api/products
PUT    /api/products/{id}
DELETE /api/products/{id}
```

### Categories

```http
POST /api/categories
GET  /api/categories
```

### Cart

```http
POST   /api/cart/add
GET    /api/cart
PUT    /api/cart/update
DELETE /api/cart/remove/{id}
DELETE /api/cart/clear
```

### Orders

```http
POST /api/orders
GET  /api/orders
GET  /api/orders/{id}
PUT  /api/orders/{id}/cancel
PUT  /api/orders/{id}/status
```

### Admin Analytics

```http
GET /api/admin/revenue
```

### AI APIs

```http
POST /api/gemini/ask
```

---

## Running the Project

### Clone Repository

```bash
git clone https://github.com/swapniltalloo/SmartCommerce-AI-Backend.git
```

### Navigate

```bash
cd SmartCommerce-AI-Backend
```

### Configure Database

Update:

```properties
application.properties
```

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/smartcommerce
spring.datasource.username=postgres
spring.datasource.password=your_password
```

### Run Application

```bash
mvn spring-boot:run
```

---

## Future Enhancements

* Docker Compose Deployment
* Global Exception Handling
* React Frontend
* Payment Gateway Integration
* Email Notifications
* Kafka Integration
* Microservices Architecture
* CI/CD Pipeline
* Cloud Deployment (AWS)

---

## Learning Outcomes

Through this project, I gained hands-on experience with:

* Spring Boot Development
* REST API Design
* JWT Authentication
* Role-Based Authorization
* Database Design
* PostgreSQL
* Redis Caching
* Docker
* Swagger Documentation
* AI API Integration
* Git & GitHub Workflows
* Production-Style Backend Architecture

---

## Author

**Swapnil Talloo**

GitHub: https://github.com/swapniltalloo

---

⭐ If you found this project useful, consider giving it a star!

# SmartCommerce AI Backend

SmartCommerce is a production-ready Spring Boot E-Commerce Backend that provides secure authentication, product management, cart management, order processing, Redis caching, AI integration, Docker support, and admin analytics.

## Features

### Authentication & Authorization

* JWT-based Authentication
* User Registration & Login
* Role-Based Access Control (USER / ADMIN)
* Spring Security Integration

### Product Management

* Add Products (Admin)
* Update Products (Admin)
* Delete Products (Admin)
* View Products
* Product Categories

### Cart Management

* Add Items to Cart
* View Cart
* Remove Items from Cart

### Order Management

* Place Orders
* View Order History
* Order Details
* Cancel Orders
* Update Order Status (Admin)

### Admin Dashboard

* Total Orders
* Total Revenue
* Placed Orders Count
* Cancelled Orders Count
* Shipped Orders Count

### Redis Caching

* Product List Caching
* Cache Eviction on Product Updates
* Reduced Database Load
* Faster API Response Time

### AI Integration

* Google Gemini API Integration
* AI-Powered Product Assistance Endpoint

### API Documentation

* Swagger UI Integration
* OpenAPI 3 Documentation

### Error Handling

* Global Exception Handling
* Validation Error Handling
* Resource Not Found Handling

### DevOps

* Docker Support
* Docker Compose Setup
* Health Check Endpoint

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

### Cache

* Redis

### Documentation

* Swagger / OpenAPI

### AI

* Google Gemini API

### DevOps

* Docker
* Docker Compose

### Build Tool

* Maven

---

## Project Structure

```text
src
├── controller
├── service
├── repository
├── entity
├── dto
├── security
├── config
├── exception
└── resources
```

---

## API Modules

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
GET    /api/categories
POST   /api/categories
PUT    /api/categories/{id}
DELETE /api/categories/{id}
```

### Cart

```http
POST   /api/cart/add
GET    /api/cart
DELETE /api/cart/remove/{id}
```

### Orders

```http
POST   /api/orders
GET    /api/orders
GET    /api/orders/{id}
PUT    /api/orders/{id}/cancel
PUT    /api/orders/{id}/status
```

### Admin Analytics

```http
GET /api/admin/dashboard
```

### Gemini AI

```http
GET /api/ai?prompt=your_prompt
```

### Health Check

```http
GET /api/health
```

---

## Running Locally

### Clone Repository

```bash
git clone https://github.com/swapniltalloo/SmartCommerce-AI-Backend.git
cd SmartCommerce-AI-Backend
```

### Create Configuration

Create:

```text
src/main/resources/application.properties
```

Copy values from:

```text
application-example.properties
```

and replace placeholder values with your own credentials.

---

## PostgreSQL Configuration

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/smartcommerce
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

## Redis Configuration

```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

---

## Gemini Configuration

```properties
gemini.api.key=your_gemini_api_key
```

---

## Run Application

```bash
mvn spring-boot:run
```

---

## Swagger Documentation

```text
http://localhost:8080/swagger-ui/index.html
```

---

## Docker

### Build Image

```bash
docker build -t smartcommerce .
```

### Run Application

```bash
docker-compose up
```

---

## Security Features

* JWT Authentication
* Password Encryption using BCrypt
* Role-Based Authorization
* Protected Endpoints
* Secure API Access

---



---

## Author

**Swapnil Talloo**



GitHub:
https://github.com/swapniltalloo

---

## License

This project is built for educational, learning, and portfolio purposes.

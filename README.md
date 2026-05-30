# SmartCommerce AI Backend

SmartCommerce AI Backend is a scalable e-commerce backend application built using Java, Spring Boot, Spring Security, PostgreSQL, Hibernate/JPA, and JWT Authentication. The project follows a layered architecture with DTO-based API design, role-based access control, shopping cart management, and category-based product organization.

## Features

### Authentication & Authorization

- User Registration
- User Login
- JWT Token Generation
- JWT Token Validation
- BCrypt Password Encryption
- Spring Security Integration
- Role-Based Access Control (ADMIN, USER)
- Swagger JWT Authorization Support

### Product Management

- Add Product
- Get All Products
- Get Product By ID
- Update Product
- Delete Product
- Product Validation Using Jakarta Validation
- Product DTO Implementation

### Category Management

- Add Category
- Get All Categories
- Product-Category Mapping
- One-to-Many Category Relationship

### Shopping Cart Module

- User-Specific Cart Management
- Add Product To Cart
- Prevent Duplicate Cart Items (UPSERT Logic)
- View Cart
- Update Cart Item Quantity
- Remove Product From Cart
- Clear Entire Cart
- Cart Total Calculation
- Cart Summary DTO Response

### Security Features

- JWT Authentication Filter
- SecurityContextHolder Integration
- User-Based Resource Access
- Password Protection Using @JsonIgnore
- Protected REST Endpoints

### API Documentation

- Swagger UI Integration
- Interactive API Testing
- JWT Authorization Support Inside Swagger

## Technologies Used

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL
- JWT
- Lombok
- Swagger/OpenAPI
- Maven

## Project Structure

```text
src/main/java/com/swapnil/smartcommerce

├── config
├── controller
├── dto
├── entity
├── repository
├── security
├── service
└── exception
```

## Database Design

### User

```text
id
username
password
role
```

### Category

```text
id
name
```

### Product

```text
id
name
description
price
quantity
category_id
```

### Cart

```text
id
user_id
```

### CartItem

```text
id
cart_id
product_id
quantity
```

## Implemented APIs

### Authentication

```http
POST /auth/register
POST /auth/login
```

### Products

```http
POST   /api/products
GET    /api/products
GET    /api/products/{id}
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
DELETE /api/cart/remove/{productId}
DELETE /api/cart/clear
```

## Example Cart Response

```json
{
  "items": [
    {
      "productName": "MacBook Pro",
      "price": 199999,
      "quantity": 5
    }
  ],
  "totalAmount": 999995
}
```

## Architecture

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

### Security Flow

```text
Client
   ↓
JWT Token
   ↓
JwtAuthFilter
   ↓
SecurityContextHolder
   ↓
Controller
   ↓
Service
```

### Cart Flow

```text
User Login
    ↓
JWT Token
    ↓
Add Product To Cart
    ↓
CartItem Creation
    ↓
View Cart
    ↓
Calculate Total
```

## Key Concepts Implemented

- JWT Authentication
- Role-Based Access Control
- DTO Pattern
- Builder Pattern
- Entity Relationships
- One-To-One Mapping
- One-To-Many Mapping
- Many-To-One Mapping
- Spring Data JPA Query Methods
- Exception Handling
- Input Validation
- Stream API
- Cart Total Calculation
- UPSERT Logic

## Upcoming Features

- Order Management Module
- Order Item Management
- Order Status Tracking
- AI Product Recommendations Using Gemini API
- AI Product Search
- Redis Caching
- Docker Support
- Product Search and Filtering
- Pagination and Sorting
- Inventory Management
- Deployment

## Current Progress

| Module | Status |
|----------|----------|
| Authentication | Completed |
| Product Management | Completed |
| Category Management | Completed |
| Shopping Cart | Completed |
| Order Management | In Progress |
| AI Integration | Planned |
| Redis Caching | Planned |
| Dockerization | Planned |

Project Completion: Approximately 75% Complete

## Swagger
<img width="1170" height="904" alt="image" src="https://github.com/user-attachments/assets/076e8a42-de9f-4e85-ad6d-40d157fe67b4" />

## Author

**Swapnil Talloo**

GitHub: https://github.com/swapniltalloo/SmartCommerce-AI-Backend

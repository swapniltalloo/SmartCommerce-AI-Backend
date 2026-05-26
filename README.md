# SmartCommerce AI Backend

A production-style E-Commerce Backend built using Spring Boot, PostgreSQL, Spring Security, JWT Authentication, Hibernate/JPA, and Swagger.

This project demonstrates modern backend development practices including authentication, authorization, DTO architecture, database relationships, validation, exception handling, and shopping cart architecture.

## Features

### Authentication and Authorization

- User Registration
- User Login
- JWT Authentication
- BCrypt Password Encryption
- Role-Based Access Control (RBAC)
- ADMIN and USER Roles
- Protected APIs using Spring Security

### Product Management

- Create Product
- Get All Products
- Get Product By ID
- Update Product
- Delete Product
- Input Validation using Jakarta Validation

### Category Management

- Create Category
- Get All Categories
- Get Category By ID
- Delete Category

### Product-Category Relationship

Implemented using JPA/Hibernate:

```java
@ManyToOne
@JoinColumn(name = "category_id")
private Category category;
```

```java
@OneToMany(mappedBy = "category")
@JsonIgnore
private List<Product> products;
```

### DTO Architecture

Implemented DTO-based API design:

- LoginRequest
- RegisterRequest
- AuthResponse
- ProductDTO
- AddToCartRequest

Benefits:

- Clean API Design
- Better Security
- Validation Support
- Separation of Concerns

### Exception Handling

Custom Exception Handling:

- ResourceNotFoundException
- Global Exception Handling
- Validation Error Responses

### Swagger Documentation

Integrated Swagger OpenAPI.

Features:

- Interactive API Documentation
- JWT Authentication Support
- API Testing Interface

Access:

```text
http://localhost:8080/swagger-ui/index.html
```

### Shopping Cart Architecture

Implemented database architecture for shopping cart functionality.

Cart:

```java
@OneToOne
@JoinColumn(name = "user_id")
private User user;
```

CartItem:

```java
@ManyToOne
@JoinColumn(name = "product_id")
private Product product;
```

```java
@ManyToOne
@JoinColumn(name = "cart_id")
private Cart cart;
```

Current Structure:

```text
User
 |
 Cart
 |
 CartItem
 |      |
 Product Quantity
```

## Project Architecture

```text
Controller
    |
Service
    |
Repository
    |
PostgreSQL
```

## Tech Stack

Backend

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Hibernate

Database

- PostgreSQL

Authentication

- JWT
- BCrypt

Documentation

- Swagger OpenAPI

Build Tool

- Maven

## Project Structure

```text
src/main/java/com/swapnil/smartcommerce

config
├── SecurityConfig
└── SwaggerConfig

controller
├── AuthController
├── ProductController
└── CategoryController

dto
├── LoginRequest
├── RegisterRequest
├── AuthResponse
├── ProductDTO
└── AddToCartRequest

entity
├── User
├── Product
├── Category
├── Cart
└── CartItem

repository
├── UserRepository
├── ProductRepository
├── CategoryRepository
├── CartRepository
└── CartItemRepository

security
├── JwtService
└── JwtAuthFilter

service
├── AuthService
├── ProductService
├── CategoryService
└── CartService

exception
```

## Database Relationships

User and Cart

```text
One User -> One Cart
```

Cart and CartItem

```text
One Cart -> Many CartItems
```

Product and Category

```text
Many Products -> One Category
```

CartItem and Product

```text
Many CartItems -> One Product
```

## API Security

| Endpoint | Access |
|-----------|---------|
| Register | Public |
| Login | Public |
| Get Products | USER / ADMIN |
| Create Product | ADMIN |
| Update Product | ADMIN |
| Delete Product | ADMIN |
| Create Category | ADMIN |
| Delete Category | ADMIN |

## Upcoming Features

- Add To Cart API
- View Cart API
- Remove From Cart API
- Order Management
- Order Items
- Inventory Management
- Pagination and Sorting
- Search APIs
- Docker Support
- Deployment
- Unit Testing
- Integration Testing

## Learning Outcomes

This project demonstrates:

- REST API Development
- Spring Security
- JWT Authentication
- Role-Based Authorization
- Hibernate/JPA Relationships
- DTO Architecture
- Exception Handling
- Swagger Documentation
- PostgreSQL Integration
- Enterprise Backend Design Patterns

  Swagger output
<img width="1117" height="827" alt="image" src="https://github.com/user-attachments/assets/07b3a02f-227a-4d53-8a3c-9f4eaeaafc88" />


## Author

Swapnil Talloo

B.Tech Electronics and Computer Science

Passionate about Backend Development, Spring Boot, AI/ML, System Design and Software Engineering.

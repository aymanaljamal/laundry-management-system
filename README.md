# Laundry Management System

## Overview

**Laundry Management System** is a backend ERP platform designed to manage and organize laundry business operations through one integrated system.

The platform covers customer management, laundry orders, employees, services, payments, invoices, deliveries, tailoring operations, reviews, coupons, and notifications.

The backend is built using **Spring Boot 4.1** with a modular domain-based architecture. It exposes a **GraphQL API** and uses **JWT authentication** with role-based authorization.

---

## Current Project Status

The project currently includes:

* Modular JPA domain model
* MySQL database integration
* GraphQL schema organized by domain
* GraphQL login mutation
* JWT token generation and validation
* BCrypt password hashing
* Role-based and method-level security
* Custom GraphQL Scalars
* Gradle-based project configuration
* Docker Compose development support

Additional GraphQL queries and mutations will be implemented progressively for each domain.

---

## Features

### Authentication and Security

* GraphQL login mutation
* JWT-based authentication
* Stateless security configuration
* BCrypt password encryption
* Role-based access control
* Method-level authorization using `@PreAuthorize`
* Account locking after repeated failed login attempts
* Account status and credential validation
* Email verification and password-reset support

### User Management

* Manage user accounts
* Assign user roles
* Manage account status
* Enable or disable accounts
* Track successful and failed login attempts
* Store profile and contact information

### Admin Management

* Link administrators with user accounts
* Manage admin departments and positions
* Assign or remove super-admin privileges
* Generate unique employee numbers

### Customer Management

* Manage customer profiles
* Link customers with optional user accounts
* Track laundry and tailoring orders
* Manage customer status
* Loyalty points system
* Customer reviews

### Laundry Order Management

* Create and manage laundry orders
* Assign employees
* Track order and payment statuses
* Manage order items
* Calculate order totals
* Manage pickup and delivery information
* Link orders with invoices, payments, and deliveries

### Laundry Services

* Manage available laundry services
* Define service types and descriptions
* Configure service prices
* Activate or deactivate services

### Cloth Management

* Manage supported cloth types
* Organize cloth categories
* Configure base prices
* Activate or deactivate cloth records

### Employee Management

* Manage employee profiles
* Assign departments and positions
* Manage employee shifts and salaries
* Track employment status
* Assign tasks to employees
* Assign tailors to tailoring orders
* Track task start and completion times

### Payment and Invoice Management

* Record multiple payments for an order
* Support different payment methods
* Track payment transaction status
* Generate invoices
* Calculate subtotal, discount, tax, and total amounts
* Track invoice status

### Delivery Management

* Manage pickup and delivery operations
* Assign delivery employees
* Track delivery status
* Store customer phone numbers and delivery addresses
* Record pickup and delivery times

### Tailoring Management

* Create and manage tailoring orders
* Assign tailors
* Record customer measurements
* Manage clothing alterations
* Track tailoring workflow status
* Manage payment and pickup methods
* Calculate paid and remaining amounts

### Coupon Management

* Create discount coupons
* Support fixed-amount and percentage discounts
* Manage coupon expiration dates
* Activate or deactivate coupons

### Review Management

* Allow customers to review completed orders
* Store ratings from 1 to 5
* Store customer comments
* Restrict each order to one review

### Notification Management

* Send notifications to users
* Link notifications to laundry or tailoring orders
* Track read and unread notifications
* Support different notification types

---

## Technologies

### Backend

* Java 17
* Spring Boot 4.1
* Spring Web MVC
* Spring for GraphQL
* Spring Security
* Spring Data JPA
* Hibernate ORM
* Jakarta Validation
* JWT using JJWT
* Lombok

### GraphQL

* GraphQL Java
* Spring for GraphQL
* GraphQL Java Extended Scalars
* Modular `.graphqls` schema files
* Custom Scalars:

  * `Date`
  * `DateTime`
  * `BigDecimal`

### Database

* MySQL 8
* MySQL Connector/J
* JPA entity relationships
* Hibernate schema management

### Development Tools

* Gradle
* Gradle Wrapper
* Git and GitHub
* Docker
* Docker Compose
* Postman
* Visual Studio Code

---

## Project Architecture

The project follows a modular, domain-based package structure:

```text
com.ayman.laundry
├── admin
├── cloth
├── common
├── config
├── coupon
├── customer
├── delivery
├── employee
├── invoice
├── notification
├── order
├── payment
├── review
├── security
├── service
├── tailoring
└── user
```

Each domain may contain:

```text
domain
├── controller
├── dto
├── entity
├── enums
├── repository
└── service
```

The security module contains:

```text
security
├── config
├── controller
├── dto
├── jwt
└── service
```

---

## GraphQL Schema Structure

GraphQL schema files are organized by domain:

```text
src/main/resources/graphql
├── schema.graphqls
├── auth
│   └── auth.graphqls
├── common
│   ├── pagination.graphqls
│   └── scalars.graphqls
├── admin
│   └── admin.graphqls
├── cloth
│   └── cloth.graphqls
├── coupon
│   └── coupon.graphqls
├── customer
│   └── customer.graphqls
├── delivery
│   └── delivery.graphqls
├── employee
│   └── employee.graphqls
├── notification
│   └── notification.graphqls
├── order
│   ├── invoice-payment.graphqls
│   └── order.graphqls
├── review
│   └── review.graphqls
├── service
│   └── laundry-service.graphqls
├── tailoring
│   └── tailoring.graphqls
└── user
    └── user.graphqls
```

Spring automatically loads and merges the schema resources into one executable GraphQL schema.

---

## Authentication Flow

The GraphQL login process follows this flow:

```text
GraphQL Login Mutation
        ↓
AuthenticationManager
        ↓
DaoAuthenticationProvider
        ↓
CustomUserDetailsService
        ↓
UserRepository
        ↓
BCrypt Password Verification
        ↓
JWT Generation
        ↓
LoginPayload
```

After logging in, protected requests must include:

```http
Authorization: Bearer <JWT_TOKEN>
```

The application uses stateless authentication and does not create server-side sessions.

---

## GraphQL Login Example

### Endpoint

```text
POST http://localhost:8080/graphql
```

### Mutation

```graphql
mutation Login($input: LoginInput!) {
  login(input: $input) {
    token
    type
    id
    username
    fullName
    email
    profileImage
    role
  }
}
```

### Variables

```json
{
  "input": {
    "username": "test",
    "password": "123456"
  }
}
```

### Example Response

```json
{
  "data": {
    "login": {
      "token": "eyJhbGciOiJIUzI1NiJ9...",
      "type": "Bearer",
      "id": "1",
      "username": "test",
      "fullName": "Test User",
      "email": "test@example.com",
      "profileImage": null,
      "role": "CUSTOMER"
    }
  }
}
```

---

## Database Design

The system uses a relational MySQL database with the following JPA relationships:

* One-to-One
* One-to-Many
* Many-to-One

The main entities include:

* User
* Admin
* Customer
* Employee
* EmployeeTask
* Cloth
* LaundryService
* Coupon
* Order
* OrderItem
* Payment
* Invoice
* Delivery
* TailoringOrder
* Measurement
* Alteration
* Review
* Notification

All entities inherit common audit and soft-delete fields from `BaseEntity`, including:

* `id`
* `createdAt`
* `updatedAt`
* `createdBy`
* `updatedBy`
* `deleted`

---

## Running the Project

### Prerequisites

Make sure the following tools are installed:

* Java 17
* MySQL 8
* Git

The project includes the Gradle Wrapper, so a separate Gradle installation is not required.

### Clone the Repository

```bash
git clone <repository-url>
cd laundry-management
```

### Configure the Database

Create a MySQL database:

```sql
CREATE DATABASE laundry;
```

Configure the database connection through application properties or environment variables.

Do not commit database passwords, JWT secrets, or other sensitive values to Git.

### Run the Application

On Windows:

```powershell
.\gradlew.bat bootRun
```

On Linux or macOS:

```bash
./gradlew bootRun
```

The application runs by default at:

```text
http://localhost:8080
```

The GraphQL endpoint is:

```text
http://localhost:8080/graphql
```

### Build the Project

On Windows:

```powershell
.\gradlew.bat clean build
```

On Linux or macOS:

```bash
./gradlew clean build
```

---

## Git Workflow

The project uses a branch-based workflow:

```text
main
└── develop
    └── feature/*
```

New features should be implemented in dedicated branches, for example:

```text
feature/graphql-authentication
feature/customer-management
feature/order-management
```

After completing and testing a feature, open a Pull Request into:

```text
develop
```

---

## Future Improvements

* Complete GraphQL queries and mutations for all domains
* Implement pagination, sorting, filtering, and searching
* Add GraphQL DataLoader to prevent N+1 query problems
* Add refresh-token support
* Add logout and token revocation
* Add email verification flow
* Add password-reset flow
* Add GraphQL exception handling
* Add automated unit and integration tests
* Add Docker deployment configuration
* Build an administrative web dashboard
* Build a mobile customer application
* Add advanced reports and business analytics
* Integrate online payment services
* Add email, SMS, and push notifications
* Add CI/CD using GitHub Actions

---

## Author

**Ayman Al-Jamal**

Backend Developer
Computer Science Student

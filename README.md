# Laundry Management System

## Overview

**Laundry Management System** is an ERP system designed to manage laundry business operations efficiently.
The system helps manage customers, orders, employees, services, payments, deliveries, tailoring operations, and notifications in one integrated platform.

The project is built using **Spring Boot** following a clean and modular architecture to provide scalability, maintainability, and easy future expansion.

---

## Features

### User Management

* User registration and authentication
* Role-based access control
* Account status management
* Security features

### Customer Management

* Manage customer profiles
* Track customer orders
* Loyalty points system
* Customer reviews

### Order Management

* Create and manage laundry orders
* Assign employees
* Track order status
* Manage order items
* Calculate total prices

### Laundry Services

* Manage available laundry services
* Service categories
* Pricing management
* Activate/deactivate services

### Cloth Management

* Manage cloth types
* Cloth categories
* Pricing configuration

### Employee Management

* Manage employees
* Employee departments and positions
* Task assignment
* Employee status tracking

### Payment & Invoice Management

* Payment processing
* Invoice generation
* Payment status tracking

### Delivery Management

* Manage deliveries
* Assign delivery employees
* Track delivery status

### Tailoring Management

* Manage tailoring orders
* Customer measurements
* Alteration services
* Tailoring workflow tracking

### Notification System

* User notifications
* Order updates
* Status alerts

---

## Technologies

### Backend

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* Spring Security
* REST API

### Database

* MySQL

### Tools

* Gradle
* Git & GitHub
* Docker

---

## Project Architecture

The project follows a modular package structure:

```
com.ayman.laundry

├── user
├── customer
├── employee
├── order
├── payment
├── invoice
├── delivery
├── notification
├── tailoring
├── service
├── cloth
├── coupon
├── review
└── common
```

Each module contains its own:

* Entity
* Repository
* Service
* Controller
* DTOs

---

## Database Design

The system uses a relational database with entities connected through JPA relationships:

* One-to-One
* One-to-Many
* Many-to-One

The main entities include:

* User
* Customer
* Employee
* Order
* OrderItem
* Payment
* Invoice
* Delivery
* LaundryService
* Cloth
* TailoringOrder
* Review
* Notification

---

## Future Improvements

* REST API documentation using Swagger
* Frontend application
* Mobile application
* Advanced reporting dashboard
* Online payment integration
* Email and SMS notifications

---

## Author

**Ayman Al-Jamal**

Backend Developer
Computer Science Student

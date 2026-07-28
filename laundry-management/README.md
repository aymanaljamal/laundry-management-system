# Laundry Management System

A Spring Boot-based system for managing laundry and tailoring orders, customers, employees, invoices, payments, reviews, and delivery operations.

## Features

- Customer management
- Employee and task management
- Laundry orders and order items
- Tailoring orders with measurements and alterations
- Invoice generation
- Payment tracking
- Reviews and feedback
- Delivery and pickup handling

## Technology Stack

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- Lombok
- Gradle
- MySQL via Docker Compose

## Project Structure

- `src/main/java` – application source code
- `src/main/resources` – configuration and static resources
- `src/test/java` – test classes

## Getting Started

### Prerequisites

- Java 21 or later
- Gradle
- Docker (optional, for MySQL)

### Run the application

```bash
./gradlew bootRun
```

### Build the project

```bash
./gradlew build
```

## Environment

The application uses configuration from `src/main/resources/application.yaml`.

## Notes

This project is currently focused on the domain model and core entity structure for the laundry and tailoring workflows.

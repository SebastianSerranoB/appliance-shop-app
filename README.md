# Appliance Shop Microservices Application

Welcome to the Appliance Shop Microservices Application! This project demonstrates a microservices-based architecture for managing an appliance shop, utilizing various Spring technologies and design patterns.

## Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Microservices](#microservices)
- [Design Patterns Implemented](#design-patterns-implemented)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Testing](#testing)
- [Database](#database)
- [Contributing](#contributing)
- [License](#license)

## Overview

This application is built using the following technologies:

- **Java 21**: The core programming language.
- **Spring Boot & Spring Cloud**: Frameworks for building and deploying microservices.
- **Feign**: A declarative HTTP client for inter-service communication.
- **Hibernate**: An Object-Relational Mapping (ORM) tool for database interactions.
- **XAMPP**: A free and open-source cross-platform web server solution stack package, used here for MySQL.
- **MySQL**: The relational database management system.
- **Postman**: A tool for testing APIs.

## Architecture

The application follows a microservices architecture, where each service is designed to handle specific business functionalities. This approach ensures scalability, maintainability, and independent deployment of services.

## Microservices

The system comprises the following microservices:

1. **Product Service**: Manages product-related operations.
2. **Sales Service**: Handles sales transactions and records.
3. **Shopping Cart Service**: Manages user shopping cart functionalities.
4. **API Gateway**: Acts as a single entry point for all client requests, routing them to the appropriate services.
5. **Eureka Server**: Provides service discovery and registration.

## Design Patterns Implemented

The application leverages several design patterns provided by Spring Cloud:

- **Config Server**: Centralized configuration management for all microservices.
- **Circuit Breaker**: Implements fault tolerance to handle service failures gracefully.
- **API Gateway**: Serves as a unified entry point for client requests, routing them to respective services.
- **Service Registry & Discovery**: Allows services to register themselves and discover other services for communication.
- **Load Balancing**: Distributes incoming requests evenly across multiple instances of a service to ensure reliability and performance.

## Prerequisites

Before setting up the application, ensure you have the following installed:

- **XAMPP**: To run the MySQL database server.
- **Java 21**: Ensure it's set up in your system's environment variables.
- **Postman**: For API endpoint testing.

## Setup Instructions

1) **Clone the Repositories**:

      git clone https://github.com/SebastianSerranoB/appliance-shop-app.git
      git clone https://github.com/SebastianSerranoB/appliance-shop-config-server.git

2) **Database Setup:**

      Launch XAMPP and start the MYSQL service.
      
      Create the necessary databases for each microservice:
      product_service_db
      sales_service_db
      shopping_cart_service_db
      
      Import the SQL scripts located in the db-schema directory of each microservice to set up the database schema and initial data.

3) **Configure Environment Variables:**

      Set up the following environment variables to match your local setup:
      
      DB_HOST: Database host (e.g., localhost).
      
      DB_PORT: Database port (default is 3306).
      
      DB_USERNAME: Your MySQL username.
      
      DB_PASSWORD: Your MySQL password.

4) **Start the Config Server:**

      Navigate to the appliance-shop-config-server directory and run:
      ./mvnw spring-boot:run

5) **Start the Eureka Server:**

      Navigate to the eureka-sv directory within appliance-shop-app and run:
      ./mvnw spring-boot:run

6) **Start the Microservices:**

      For each microservice (product-service, sales-service, shopping-cart-service), navigate to its directory and run:
      ./mvnw spring-boot:run

7) **Start the API Gateway:**

      Navigate to the api-gateway directory and run:
      ./mvnw spring-boot:run


## Testing
Postman collections are provided in the postman directory for testing the API endpoints. Import these collections into Postman to simulate various operations and verify the functionality of each microservice.

## Database
Each microservice has its own database schema, promoting data encapsulation and service autonomy. SQL scripts for setting up these schemas are located in the respective db-schema directories of each service.

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure that your code adheres to the project's coding standards and includes appropriate tests.

## License
Will follow soon.

By following this README, you should be able to set up and run the Appliance Shop Microservices Application locally. If you encounter any issues or have questions, feel free to open an issue in the repository. Happy coding!

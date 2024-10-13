# Notification Service - E-commerce Application

This is the Notification Service for an e-commerce application. It handles notifications triggered by order placements and payment processing, integrating with the User, Order, and Payment services for seamless interservice communication using WebClient and Apache Kafka. The service is built with Spring Boot and supports both synchronous and asynchronous communication, containerization with Docker, and service discovery via Eureka. It ensures that users are notified of important events such as order status updates and payment confirmations.

# Table of Contents
Technologies
Features
Installation
Usage
Endpoints
Swagger/OpenAPI
Docker
Environment Variables
Contributing
License
Contact

# Technologies
Spring Boot 3.2.7 - Backend framework
WebFlux - For handling asynchronous non-blocking requests
Apache Kafka - Message broker for asynchronous communication
Eureka Client - Service discovery
Spring Actuator - Monitoring and management
OpenAPI (Swagger) - API documentation and testing tool
Lombok - For reducing boilerplate code
Docker - Containerization tool for deployment
MySQL - Database for storing notification data
# Features
Sends notifications for order placements and payment processing
Consumes messages from the Order and Payment services using Apache Kafka
Asynchronous communication using Apache Kafka
Synchronous communication using WebClient
Service registration and discovery using Eureka
Load balancing for handling requests efficiently
RESTful API endpoints with exception handling
API documentation with OpenAPI/Swagger
Full support for containerization using Docker and orchestration via Docker Compose
Health checks and application metrics via Spring Actuator

# Installation
Prerequisites
Ensure you have the following installed:

Java 17+ (JDK)
Maven 3.6+
Docker
Steps to Run Locally
1.Clone the repository
  git clone https://github.com/habeneyasu/notificationservice.git
  cd notification-service
2.Set up environment variables by creating a .env file in the root directory and set the following variables:
  SPRING_DATASOURCE_PASSWORD=your_db_password
3.Build the project:
  mvn clean install
Using Docker
To run the service using Docker, follow these steps:

1.Build and start the services using Docker Compose:
  docker-compose up --build
# Contributing
If you wish to contribute to this project:

1.Fork the repository.
2.Create a new branch for your feature or bugfix.
3.Commit your changes with clear messages.
4.Open a pull request.

License
This project is licensed under the MIT License.

Contact
For any questions, feel free to reach out:

Email: habeneyasu@gmail.com
LinkedIn: https://www.linkedin.com/in/habeneyasu

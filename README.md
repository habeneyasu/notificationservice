# 📧 Notification Service - E-Commerce Microservices Platform

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.7-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2022.0.3-blue.svg)](https://spring.io/projects/spring-cloud)
[![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-Messaging-black.svg)](https://kafka.apache.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)](https://www.mysql.com/)
[![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED.svg)](https://www.docker.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A robust, **multi-channel Notification Service** built as part of a comprehensive e-commerce microservices platform. This service handles real-time notifications across multiple channels including email, SMS, push notifications, and WhatsApp using event-driven architecture and modern messaging patterns.

## �� Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Features](#features)
- [Technologies](#technologies)
- [Quick Start](#quick-start)
- [API Documentation](#api-documentation)
- [Configuration](#configuration)
- [Monitoring & Observability](#monitoring--observability)
- [Docker Deployment](#docker-deployment)
- [Development](#development)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Overview

The Notification Service is a critical component of our e-commerce microservices platform, providing:

- **Multi-Channel Notifications** across email, SMS, push, and WhatsApp
- **Event-Driven Architecture** with Apache Kafka for real-time processing
- **Template Management** with dynamic content personalization
- **Delivery Tracking** with comprehensive status monitoring
- **Rate Limiting** and throttling for optimal performance
- **Retry Mechanisms** for reliable message delivery

## 🏗️ Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   API Gateway   │───▶│Notification Svc │───▶│   MySQL         │
│                 │    │  (Multi-Channel)│    │   Database      │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         │                       ▼                       │
         │              ┌─────────────────┐              │
         │              │   Apache Kafka  │              │
         │              │   Event Bus     │              │
         │              └─────────────────┘              │
         │                       │                       │
         │                       ▼                       │
         │              ┌─────────────────┐              │
         │              │ Notification    │              │
         │              │ Channels        │              │
         │              │ • Email         │              │
         │              │ • SMS (Twilio)  │              │
         │              │ • Push          │              │
         │              │ • WhatsApp      │              │
         └──────────────┴─────────────────┴──────────────┘
```

## ✨ Features

### 📧 Multi-Channel Notifications
- **Email Notifications** with HTML templates and attachments
- **SMS Notifications** via Twilio integration
- **Push Notifications** for mobile and web applications
- **WhatsApp Notifications** for instant messaging
- **Template Engine** with dynamic content personalization
- **Bulk Notifications** for marketing campaigns

### 🔄 Event-Driven Processing
- **Kafka Integration** for real-time event processing
- **Event Sourcing** for notification state management
- **Message Queuing** with priority-based processing
- **Dead Letter Queues** for failed message handling
- **Event Replay** for message recovery

### 🎯 Smart Delivery
- **Delivery Scheduling** with timezone support
- **Rate Limiting** to prevent spam and ensure compliance
- **Retry Mechanisms** with exponential backoff
- **Delivery Tracking** with detailed status reporting
- **Bounce Handling** for email delivery failures

### 🔧 Advanced Features
- **Template Management** with version control
- **User Preferences** for notification channels
- **A/B Testing** for notification optimization
- **Analytics Dashboard** for delivery metrics
- **Webhook Support** for external integrations

### 🏗️ Design Patterns
- **Factory Pattern** - Different notification channel implementations
- **Strategy Pattern** - Notification delivery strategies
- **Observer Pattern** - Event-driven notification processing
- **Template Method** - Common notification processing flow
- **Repository Pattern** - Data access abstraction

### 🔗 Service Integration
- **Order Service Integration** for order notifications
- **Payment Service Integration** for payment alerts
- **User Service Integration** for user preferences
- **Service Discovery** with Eureka client

## 🛠️ Technologies

| Category | Technology | Version |
|----------|------------|---------|
| **Framework** | Spring Boot | 3.2.7 |
| **Cloud** | Spring Cloud | 2022.0.3 |
| **Service Discovery** | Eureka Client | 3.0+ |
| **Messaging** | Apache Kafka | 3.0+ |
| **Database** | MySQL | 8.0+ |
| **ORM** | Spring Data JPA | 3.0+ |
| **API** | GraphQL | 3.0+ |
| **Documentation** | OpenAPI 3.0 (Swagger) | 3.0+ |
| **SMS** | Twilio | Latest |
| **Email** | JavaMail | Latest |
| **Monitoring** | Spring Actuator | 3.0+ |
| **Containerization** | Docker | Latest |
| **Build Tool** | Maven | 3.6+ |
| **Java** | OpenJDK | 17+ |

## 🚀 Quick Start

### Prerequisites

- **Java 17+** (OpenJDK recommended)
- **Maven 3.6+**
- **Docker & Docker Compose**
- **MySQL 8.0+** (or use Docker)
- **Apache Kafka** (or use Docker)
- **Twilio Account** (for SMS notifications)

### 1. Clone the Repository

```bash
git clone https://github.com/habeneyasu/e-commerce.git
cd e-commerce/notificationservice
```

### 2. Environment Setup

Create a `.env` file in the project root:

```bash
# Database Configuration
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3309/notification_service_db
SPRING_DATASOURCE_USERNAME=user
SPRING_DATASOURCE_PASSWORD=your_secure_password

# Kafka Configuration
KAFKA_BOOTSTRAP_SERVERS=localhost:9092
KAFKA_TOPIC_NOTIFICATION_EVENT=notification-event

# Twilio Configuration
TWILIO_ACCOUNT_SID=your_twilio_account_sid
TWILIO_AUTH_TOKEN=your_twilio_auth_token
TWILIO_PHONE_NUMBER=your_twilio_phone_number

# Email Configuration
SPRING_MAIL_HOST=smtp.gmail.com
SPRING_MAIL_PORT=587
SPRING_MAIL_USERNAME=your_email@gmail.com
SPRING_MAIL_PASSWORD=your_app_password

# Service Discovery
EUREKA_CLIENT_SERVICE_URL_DEFAULTZONE=http://localhost:8761/eureka
```

### 3. Run with Docker Compose (Recommended)

```bash
# Start all services including dependencies
docker-compose up -d

# Check service status
docker-compose ps

# View logs
docker-compose logs -f notification-service
```

### 4. Run Locally

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### 5. Verify Installation

- **Service Health**: http://localhost:8184/api/v1/actuator/health
- **API Documentation**: http://localhost:8184/api/v1/swagger-ui/index.html
- **GraphQL Playground**: http://localhost:8184/api/v1/graphql

## 📚 API Documentation

### Base URL
```
http://localhost:8184/api/v1
```

### Notification Management Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `POST` | `/notifications` | Send notification | ✅ |
| `GET` | `/notifications` | Get all notifications (paginated) | ✅ |
| `GET` | `/notifications/{id}` | Get notification by ID | ✅ |
| `PUT` | `/notifications/{id}` | Update notification | ✅ |
| `DELETE` | `/notifications/{id}` | Cancel notification | ✅ |
| `GET` | `/notifications/user/{userId}` | Get notifications by user | ✅ |
| `POST` | `/notifications/bulk` | Send bulk notifications | ✅ |

### Channel-Specific Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `POST` | `/notifications/email` | Send email notification | ✅ |
| `POST` | `/notifications/sms` | Send SMS notification | ✅ |
| `POST` | `/notifications/push` | Send push notification | ✅ |
| `POST` | `/notifications/whatsapp` | Send WhatsApp notification | ✅ |

### Template Management Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `GET` | `/templates` | Get all templates | ✅ |
| `GET` | `/templates/{id}` | Get template by ID | ✅ |
| `POST` | `/templates` | Create new template | ✅ |
| `PUT` | `/templates/{id}` | Update template | ✅ |
| `DELETE` | `/templates/{id}` | Delete template | ✅ |

### GraphQL Endpoints

| Endpoint | Description |
|----------|-------------|
| `POST /graphql` | GraphQL query endpoint |
| `GET /graphql` | GraphQL playground |

### Example API Usage

#### 1. Send Email Notification
```bash
curl -X POST http://localhost:8184/api/v1/notifications/email \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "to": "user@example.com",
    "subject": "Order Confirmation",
    "templateId": "order-confirmation",
    "data": {
      "orderId": "ORD-12345",
      "customerName": "John Doe",
      "totalAmount": 99.99
    }
  }'
```

#### 2. Send SMS Notification
```bash
curl -X POST http://localhost:8184/api/v1/notifications/sms \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "to": "+1234567890",
    "message": "Your order ORD-12345 has been confirmed. Total: $99.99",
    "templateId": "order-sms"
  }'
```

#### 3. Send Push Notification
```bash
curl -X POST http://localhost:8184/api/v1/notifications/push \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "userId": 123,
    "title": "Order Update",
    "body": "Your order has been shipped",
    "data": {
      "orderId": "ORD-12345",
      "status": "shipped"
    }
  }'
```

#### 4. Send WhatsApp Notification
```bash
curl -X POST http://localhost:8184/api/v1/notifications/whatsapp \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "to": "+1234567890",
    "message": "Hello! Your order ORD-12345 is ready for pickup.",
    "templateId": "whatsapp-order"
  }'
```

#### 5. Get Notification History
```bash
curl -X GET http://localhost:8184/api/v1/notifications/user/123 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### 6. GraphQL Query
```graphql
query {
  notifications(userId: 123) {
    id
    type
    channel
    status
    subject
    content
    createdAt
    deliveredAt
  }
}
```

## ⚙️ Configuration

### Application Properties

The service uses Spring Boot's configuration system with profiles:

- **`application.properties`** - Base configuration
- **`application-container.properties`** - Container-specific settings
- **`application-dev.properties`** - Development environment
- **`application-prod.properties`** - Production environment

### Key Configuration Options

```properties
# Server Configuration
server.port=8184
server.servlet.context-path=/api/v1
spring.application.name=NOTIFICATION-SERVICE

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3309/notification_service_db
spring.datasource.username=user
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
spring.jpa.hibernate.ddl-auto=validate

# Kafka Configuration
spring.kafka.bootstrap-servers=${KAFKA_BOOTSTRAP_SERVERS}
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer

# Twilio Configuration
twilio.account-sid=${TWILIO_ACCOUNT_SID}
twilio.auth-token=${TWILIO_AUTH_TOKEN}
twilio.phone-number=${TWILIO_PHONE_NUMBER}

# Email Configuration
spring.mail.host=${SPRING_MAIL_HOST}
spring.mail.port=${SPRING_MAIL_PORT}
spring.mail.username=${SPRING_MAIL_USERNAME}
spring.mail.password=${SPRING_MAIL_PASSWORD}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Service Discovery
eureka.client.service-url.defaultZone=${EUREKA_CLIENT_SERVICE_URL_DEFAULTZONE}
```

## 📊 Monitoring & Observability

### Spring Actuator Endpoints

| Endpoint | Description |
|----------|-------------|
| `/actuator/health` | Service health status |
| `/actuator/info` | Application information |
| `/actuator/metrics` | Application metrics |

### Business Metrics

The service tracks various notification-related metrics:

- **Notification Delivery Rate** - Successful deliveries percentage
- **Channel Performance** - Delivery rates by channel (email, SMS, push)
- **Template Usage** - Most used notification templates
- **Error Rate** - Failed notification percentage
- **Response Time** - Average notification processing time
- **Queue Depth** - Pending notifications count

### Channel-Specific Metrics

- **Email Metrics** - Bounce rate, open rate, click rate
- **SMS Metrics** - Delivery rate, cost per message
- **Push Metrics** - Delivery rate, click-through rate
- **WhatsApp Metrics** - Delivery rate, read receipts

### Logging

Structured JSON logging with different levels:

```json
{
  "timestamp": "2024-01-15T10:30:00.000Z",
  "level": "INFO",
  "logger": "com.ecommerce.notificationservice.service.NotificationService",
  "message": "Notification sent successfully",
  "notificationId": "NOTIF-12345",
  "userId": 67890,
  "channel": "EMAIL",
  "templateId": "order-confirmation",
  "status": "DELIVERED"
}
```

## 🐳 Docker Deployment

### Dockerfile

```dockerfile
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/notification_app.jar app.jar
EXPOSE 8184
ENTRYPOINT ["java","-jar","/app.jar"]
```

### Docker Compose

```yaml
version: '3.8'
services:
  notification-service:
    build: ./notificationservice
    ports:
      - "8184:8184"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://notification-service-db:3306/notification_service_db
      - SPRING_DATASOURCE_USERNAME=user
      - SPRING_DATASOURCE_PASSWORD=test@123
      - KAFKA_BOOTSTRAP_SERVERS=kafka:9092
      - TWILIO_ACCOUNT_SID=${TWILIO_ACCOUNT_SID}
      - TWILIO_AUTH_TOKEN=${TWILIO_AUTH_TOKEN}
      - SPRING_MAIL_USERNAME=${SPRING_MAIL_USERNAME}
      - SPRING_MAIL_PASSWORD=${SPRING_MAIL_PASSWORD}
    depends_on:
      - notification-service-db
      - kafka
    networks:
      - microservice-net

  notification-service-db:
    image: mysql:8.0
    environment:
      - MYSQL_DATABASE=notification_service_db
      - MYSQL_USER=user
      - MYSQL_PASSWORD=test@123
      - MYSQL_ROOT_PASSWORD=test@123
    ports:
      - "3309:3306"
    networks:
      - microservice-net
```

### Deployment Commands

```bash
# Build and start services
docker-compose up --build

# Run in background
docker-compose up -d

# View logs
docker-compose logs -f notification-service

# Stop services
docker-compose down
```

## 🛠️ Development

### Project Structure

```
notificationservice/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/ecommerce/notificationservice/
│   │   │       ├── config/          # Configuration classes
│   │   │       ├── controller/      # REST & GraphQL controllers
│   │   │       ├── integration/     # External service integration
│   │   │       ├── model/           # JPA entities
│   │   │       ├── modeldto/        # Data Transfer Objects
│   │   │       ├── repository/      # Data repositories
│   │   │       └── service/         # Business logic & strategies
│   │   └── resources/
│   │       ├── application.properties
│   │       └── templates/           # Notification templates
│   └── test/                        # Test classes
├── Dockerfile
├── pom.xml
└── README.md
```

### Design Patterns Used

- **Factory Pattern** - Different notification channel implementations
- **Strategy Pattern** - Notification delivery strategies
- **Observer Pattern** - Event-driven notification processing
- **Template Method** - Common notification processing flow
- **Repository Pattern** - Data access abstraction
- **Builder Pattern** - Complex notification object creation

### Notification Channel Integration

The service supports multiple notification channels:

#### Email Service
```java
@Service
public class EmailService implements MessageService {
    @Override
    public void sendMessage(NotificationDTO notification) {
        // Email sending logic with template engine
    }
}
```

#### SMS Service (Twilio)
```java
@Service
public class SmsService implements MessageService {
    @Override
    public void sendMessage(NotificationDTO notification) {
        // Twilio SMS sending logic
    }
}
```

#### Push Notification Service
```java
@Service
public class PushNotificationService implements MessageService {
    @Override
    public void sendMessage(NotificationDTO notification) {
        // Push notification sending logic
    }
}
```

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=NotificationServiceTest

# Run tests with coverage
mvn test jacoco:report
```

### Code Quality

The project follows:
- **Java Coding Standards** (Google Java Style)
- **Spring Boot Best Practices**
- **Event-Driven Architecture Principles**
- **RESTful API Design Principles**
- **SOLID Principles**
- **Clean Architecture**

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. **Fork** the repository
2. **Create** a feature branch: `git checkout -b feature/amazing-feature`
3. **Commit** your changes: `git commit -m 'Add amazing feature'`
4. **Push** to the branch: `git push origin feature/amazing-feature`
5. **Open** a Pull Request

### Development Guidelines

- Write **unit tests** for new features
- Follow **existing code style**
- Update **documentation** as needed
- Ensure **all tests pass**
- Add **appropriate logging**
- Consider **performance implications**
- Test **all notification channels**

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

## 📞 Contact

**Haben Eyasu** - Senior Backend Developer

- **Email**: [habeneyasu@gmail.com](mailto:habeneyasu@gmail.com)
- **LinkedIn**: [linkedin.com/in/habeneyasu](https://linkedin.com/in/habeneyasu)
- **GitHub**: [github.com/habeneyasu](https://github.com/habeneyasu)

---

## 🎯 Project Status

- ✅ **Core Features**: Complete
- ✅ **Multi-Channel Support**: Email, SMS, Push, WhatsApp
- ✅ **Event-Driven Architecture**: Kafka integration
- ✅ **Template Engine**: Dynamic content personalization
- ✅ **Service Integration**: Order, Payment, User services
- ✅ **API Documentation**: OpenAPI 3.0 with Swagger UI
- ✅ **GraphQL Support**: Flexible data querying
- ✅ **Docker Support**: Containerized deployment ready
- 🔄 **Testing**: Unit and integration tests in progress
- 🔄 **Performance**: Load testing and optimization ongoing

---

**Built with ❤️ by [Haben Eyasu](https://github.com/habeneyasu)**

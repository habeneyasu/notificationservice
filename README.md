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

### �� Smart Delivery
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

### 1. Clone the Repository

```bash
git clone https://github.com/habeneyasu/e-commerce.git
cd e-commerce/notificationservice
```

### 2. Run with Docker Compose (Recommended)

```bash
# Start all services including dependencies
docker-compose up -d

# Check service status
docker-compose ps

# View logs
docker-compose logs -f notification-service
```

### 3. Run Locally

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### 4. Verify Installation

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

#### 3. GraphQL Query
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

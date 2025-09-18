# banking-app

🏦 Banking Microservices Project

A Banking Application built with Spring Boot Microservices. It provides user authentication, transaction management, and account statements using Spring Cloud (Eureka, Config Server, API Gateway) and JWT-based security.

📌 Services

Identity Service (Auth Service) – Manages users, registration, login, and JWT token generation.

Transaction Service – Handles deposits, credits, debits, and balance tracking for users.

Statement Service – Retrieves transaction history for users by calling Transaction Service.

API Gateway – Single entry point, performs routing and JWT validation.

Service Registry (Eureka Server) – Manages service discovery.

Config Server – Centralized configuration management.

🏗️ Architecture
               +------------------+
               |  Config Server   |
               +------------------+
                        |
                        v
   +------------------+   +------------------+
   | Identity Service |   | Transaction Svc  |
   +------------------+   +------------------+
             |                   |
             v                   v
       Generates JWT       Handles Txns
             |                   |
             +---------+---------+
                       |
              +------------------+
              | Statement Svc    |
              +------------------+
                       |
                       v
             +-------------------+
             |   API Gateway     |
             | Auth Filter + JWT |
             +-------------------+
                       |
                       v
             +-------------------+
             |  Eureka Registry  |
             +-------------------+

⚙️ Tech Stack

Java 17

Spring Boot 3.2.9

Spring Cloud 2023.0.3 (Leyton)

Spring Security + JWT

Spring Cloud Config, Eureka, Gateway

JPA + PostgreSQL/MySQL

WebFlux (WebClient)

Lombok

Maven

🚀 Getting Started
1️⃣ Clone the Repository
git clone https://github.com/your-repo/banking-microservices.git
cd banking-microservices

2️⃣ Start Config Server
mvn spring-boot:run -pl config-server

3️⃣ Start Eureka Server
mvn spring-boot:run -pl service-registry

4️⃣ Start Identity Service
mvn spring-boot:run -pl identity-service

5️⃣ Start Transaction Service
mvn spring-boot:run -pl transaction-service

6️⃣ Start Statement Service
mvn spring-boot:run -pl statement-service

7️⃣ Start API Gateway
mvn spring-boot:run -pl api-gateway

🔑 Authentication

JWT tokens are generated in Identity Service (/auth/login).

All secured endpoints (/transactions/**, /statements/**) require:

Authorization: Bearer <jwt-token>

📡 API Endpoints
Identity Service
Method	Endpoint	Description
POST	/auth/register	Register new user
POST	/auth/login	Login & generate JWT
GET	/auth/validate	Validate JWT (optional)
Transaction Service
Method	Endpoint	Description
POST	/transactions/deposit/{userId}	Deposit money
POST	/transactions/credit/{userId}	Credit money
POST	/transactions/debit/{userId}	Debit money
GET	/transactions/balance/{userId}	Get balance
GET	/transactions/history/{userId}	Transaction history
Statement Service
Method	Endpoint	Description
GET	/statements/{userId}	User’s statement (history)
🧪 Example Usage
1. Register User
curl -X POST http://localhost:9191/auth/register \
     -H "Content-Type: application/json" \
     -d '{"name":"John Doe","email":"john@example.com","password":"1234"}'

2. Login & Get Token
curl -X POST http://localhost:9191/auth/login \
     -H "Content-Type: application/json" \
     -d '{"email":"john@example.com","password":"1234"}'


Response:

{ "token": "<jwt-token>" }

3. Deposit Money
curl -X POST "http://localhost:9191/transactions/deposit/1?amount=1000" \
     -H "Authorization: Bearer <jwt-token>"

4. Get Statement
curl -X GET "http://localhost:9191/statements/1" \
     -H "Authorization: Bearer <jwt-token>"

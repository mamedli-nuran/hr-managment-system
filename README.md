# HR Management & Microservices System

A progressive microservices-based system designed to automate HR processes, manage employee profiles, handle payroll calculations, and track leaves. The architecture is built on top of **Spring Cloud Gateway**, which acts as a single entry point and an OAuth2 Resource Server, alongside a dedicated **Auth-Service** handling stateless authentication via **JWT (JSON Web Tokens)**.

---

## 🏗 Architecture & Request Flow

All security measures are centralized at the API Gateway level. Internal business microservices reside within a private network partition and fully trust incoming requests that have passed the gateway's verification phase.

1. **Authentication:** The client sends a registration or login request. The `API Gateway` transparently routes it to the `Auth-Service`. The client receives a JWT access token signed with the `HMAC-SHA256` algorithm in return.
2. **Protected Requests:** To access any business-logic endpoints, the client attaches the token to the `Authorization` header as a `Bearer <token>`.
3. **Verification:** The `API Gateway` acts as an **OAuth2 Resource Server**. It automatically validates the token's cryptographic signature using `NimbusReactiveJwtDecoder`. Upon successful verification, it proxies the request to the target microservice.

---

## 🛠 Technology Stack

* **Backend:** Java 17, Spring Boot 3.3.x, Spring Cloud Gateway (Reactive WebFlux)
* **Security:** Spring Security Crypto, OAuth2 Resource Server, JWT (io.jsonwebtoken / Nimbus JWT)
* **Database & Migrations:** PostgreSQL, Liquibase
* **Tools:** Lombok, Gradle

---

## 🗂 Service Structure & Ports

| Service | Port | Description | Gateway Routed URL |
| :--- | :--- | :--- | :--- |
| **`api-gateway`** | `8080` | Single entry point, OAuth2 JWT token verification | `http://localhost:8080/` |
| **`auth-service`** | `8084` | User registration, authentication, and JWT generation | `/api/auth/**` (Public) |
| **`employee-service`** | `8081` | Core employee profile management | `/api/employees/**` (Protected) |
| **`payroll-service`** | `8082` | Payroll generation, processing, and history tracking | `/api/payroll/**` (Protected) |
| **`leave-service`** | `8083` | Paid time off (PTO) and sick leave management | `/api/leaves/**` (Protected) |

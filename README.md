# jwt-verification-service
## Description
This application exposes a web API that receives a JWT as a parameter and checks whether it is valid according to the specified rules.


## Technologies Used
- **Java 17**
- **Maven 3**
- **Spring Boot 3**
- **Spring Web**
- **Springdoc OpenAPI** (Swagger)
- **Lombok**
- **Resilience4j** (Rate Limiting, Circuit Breaker, Retry)
- **Auth0 JWT** - JWT token handling
- **JUnit 5** - these unitary and integration
- **Mockito** - mocking in tests

## Functionalities
Checking the JWT structure.
- Verification of `Name`, `Role` and `Seed` claims.
- Validation of claim `Name` to contain only alphabetic characters and spaces.
- Validation of the `Role` claim to contain only one of the three values: `Admin`, `Member` or `External`.
- Validation of the `Seed` claim to be a prime number.

## Architecture
The project architecture follows the hexagonal architecture pattern (Ports and Adapters). This architecture promotes a clear separation between business logic (domain) and infrastructure, facilitating code testability and maintenance.

## Packages

- `app`: Contains the API controllers.
- `domain`: Contains the business logic and services.
- `infra`: Contains the infrastructure, such as configurations, validations and utilities.

## Main Classes

- `JwtVerificationController`: Controller that exposes an API for JWT verification.
- `JwtVerifyService`: Service that contains JWT validation logic.
- `JwtValidatorComponent`: Interface for JWT validation components.
- `JwtValidatorComponentImpl`: Implementation of the JWT validation component.
- `GlobalExceptionHandler`: Global discussion handler.
- `SwaggerConfig`: Swagger settings for API documentation.
- `Utils`: Utility for auxiliary functions, such as checking prime numbers.

## Resilience Settings

The service uses Resilience4j to provide resilience with the following configurations:

- **Rate Limiting**: Limits the number of requests allowed per second.
- **Circuit Breaker**: Opens the circuit after a certain number of failures, preventing subsequent calls until the circuit is closed again.
- **Retry**: Retries to execute the method in case of failure, up to a maximum number of attempts.


The settings are defined in the `application.properties` file.

## Running the Project

To run the project, follow the steps below:

1. Clone the repository:

   ```bash
   git clone https://github.com/Phellype-Guilherme/jwt-verification-service.git


2. Navigate to the project directory:

   ```bash
   cd jwt-verification-service


3. Run the project using Maven:

   ```bash
   ./mvnw spring-boot:run

4. An API will be available at http://localhost:8080.
5. Swagger documentation will be available at http://localhost:8080/swagger-ui.html.

## Running Tests

he project includes unit and integration tests to ensure the functionality and reliability of the code. To run the tests, use the following command:

1. test
    ```bash 
       ./mvnw test

## Test Coverage
- `JwtVerificationControllerTest`: Unit tests for the controller to verify different JWT scenarios.
- `JwtVerifyServiceTest`: Unit tests for the service to validate JWT logic and fallback mechanism.
- `JwtValidatorComponentImplTest`: Unit tests for the JWT validator component implementation.
- `GlobalExceptionHandlerTest`: Unit tests for the global exception handler.
- `UtilsTest`: Unit tests for utility functions.
- `JwtVerificationApplicationTest`: Integration test to ensure the Spring context loads and beans are correctly instantiated.
## Endpoints API
### METHOD GET:
**/jwt/verify**: Checks the validity of a JWT. Requires an Authorization header with a JWT token with Bearer flag.

# README

This README provides a clear overview of the project, including the technologies used, functionalities, architecture, packages, main classes, resiliency settings, execution instructions, test, and API endpoint details.






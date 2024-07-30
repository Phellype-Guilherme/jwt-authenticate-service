[![Push-to-EC2](https://github.com/Phellype-Guilherme/jwt-verification-service/actions/workflows/deployAWS.yml/badge.svg)](https://github.com/Phellype-Guilherme/jwt-verification-service/actions/workflows/deployAWS.yml) [![SonarCloud](https://github.com/Phellype-Guilherme/jwt-verification-service/actions/workflows/SonarCloudAnalys.yml/badge.svg)](https://github.com/Phellype-Guilherme/jwt-verification-service/actions/workflows/SonarCloudAnalys.yml)
# jwt-verification-service
## Description
This application exposes a REST FULL API that receives a JWT in the authorization header and checks if it is valid according to the specified rules.

![image](https://github.com/user-attachments/assets/6045a677-4903-4a34-87a3-28328ee475ea)


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
- **AWS EC2** - for hosting the application
- **AWS VPC** - for network isolation and security
- **Sonar cloud** -  Automatically analyze code

## Functionalities
Checking the JWT structure.
- Verification of `Name`, `Role` and `Seed` claims.
- Validation of claim `Name` to contain only alphabetic characters and spaces.
- Validation of the `Role` claim to contain only one of the three values: `Admin`, `Member` or `External`.
- Validation of the `Seed` claim to be a prime number.

## Architecture
The project architecture follows the principles of hexagonal architecture (Ports and Adapters) and clean architecture. This approach promotes a clear separation between business logic (domain) and infrastructure, facilitating code testability, maintainability, and adaptability to changes. By doing so, we ensure that business rules are isolated from external dependencies, making the application more robust and flexible for future developments.

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
- `JwtVerifyServiceTest`: Unit tests for the service to validate JWT logic.
- `JwtValidatorComponentImplTest`: Unit tests for the JWT validator component implementation.
- `GlobalExceptionHandlerTest`: Unit tests for the global exception handler.
- `UtilsTest`: Unit tests for utility functions.
- `JwtVerificationApplicationTest`: Integration test to ensure the Spring context loads and beans are correctly instantiated.


## SonarCloud Analysis
This project uses SonarCloud to continuously inspect the code quality. SonarCloud is integrated via GitHub Actions to automatically analyze the code on every push to the `main` and `release/develop` branches.

## Viewing SonarCloud Results
After setting up the workflow, every push to the main and release/develop branches will trigger a SonarCloud analysis. The results can be viewed on your SonarCloud project dashboard.
![image](https://github.com/user-attachments/assets/d718d4ef-eb1e-4dd9-b5d3-3b9954a3d633)

## Benefits of SonarCloud
Code Quality: Automatically analyze code quality and get detailed feedback on bugs, code smells, and vulnerabilities.
Continuous Inspection: Ensure that every code change is analyzed and meets the quality standards.
Maintainability: Identify potential issues early and maintain a high standard of code maintainability.


## Deployment

The project is configured to be deployed to an AWS EC2 instance using GitHub Actions. The deployment process is triggered on every push to the main branch. The following GitHub Actions workflow is used deploy.yaml


## Steps for Deployment
1. Ensure you have an EC2 instance running and accessible.
2. Add your EC2 instance SSH key to the GitHub Secrets with the name EC2_SSH_KEY.
3. Push changes to the main branch to trigger the deployment process.


## AWS Technologies Used
- Amazon EC2: Used to host the application, providing scalable computing capacity.
- Amazon VPC: Used for network isolation and security, ensuring the application's resources are securely accessible and managed.

## Endpoints API
### METHOD GET:
**/jwt/verify**: Checks the validity of a JWT. Requires an Authorization header with a JWT token with Bearer flag.

## Endpoint in AWS
### METHOD GET:
The application is deployed and running on an AWS EC2 instance. The API endpoint is available at:

example URL: 
http://ec2-15-228-167-219.sa-east-1.compute.amazonaws.com:8080/jwt/verify
Requires an Authorization header with a JWT token with Bearer flag.

## Contributing
1. Fork the repository.
2. Create a new branch (git checkout -b feature-branch).
3. Make your changes.
4. Commit your changes (git commit -m 'Add some feature').
5. Push to the branch (git push origin feature-branch).
6. Open a pull request.
# README

This README provides a clear overview of the project, including the technologies used, functionalities, architecture, packages, main classes, resiliency settings, execution instructions, tests, API endpoint details, and deployment information..





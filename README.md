# Coin Calculator - Full Stack Web Application
## Introduction
This documentation provides useful information about the implementation and setup of the project.

## Business Requirements
* User should be able to pick USD or EUR as a fiat currency.
* User should be able to enter a fiat amount.
* User should be able to see updated price every 10 seconds (price availability period).
* User should be able to see how much BTC they receive along with coin type, date, and the input they provided if they sell the fiat amount.

## Technologies Used
* Java 11
* Spring Boot (Web, Security, Data JPA, Validation, Test)
* REST API
* Spring Cloud OpenFeign (and Feign OkHttp)
* Docker
* Postgres DB
* VueJS
* Nginx
* Jakarta Bean Validation
* Google Guava
* Maven
* SLF4J
* Lombok
* Junit
* Mockito
* SpringDoc - Open API

## Setup
You can start the applications by running the following docker compose commands at the root level of the project:
```
docker-compose pull
docker-compose up
```

## Specifications
### Backend Side

#### API
A RESTful API has been implemented by using Spring Boot.

#### DB
Postgres DB keeps the records of successful conversions which are stored as transactions by the user.

#### DB Communication
Spring Data JPA provides the abstraction for the communication between the Postgres DB and the SpringBoot backend application.

#### Containerization
There are separate Docker files for backend and frontend. A docker compose file uses these files and also includes a Postgres DB image to create a ready-to-use application.
###### Ports
| Port | Service            |
|------|--------------------|
| 8080 | VueJS Frontend     |
| 8090 | SpringBoot Backend |
| 5433 | Postgres DB        |

#### External API Communication
Feign client lets the application communicate with external APIs. Only `toBTC` method of [Exchange Rates API](https://www.blockchain.com/api/exchange_rates_api) has been used as it safely and simply meets the requirement of converting EUR or USD to BTC (ETH is out of scope). Feign configuration can be detailed as an improvement.

#### Validation
Jakarta Bean Validation annotations validates the request body attributes. Application raises validation specific errors for each field if the input is invalid.

#### Exception Handling
ExceptionHandler & ControllerAdvice have been used for handling different types of exceptions in a readable and global way. Proper error responses are returned for application exceptions, authentication exceptions, invalid input exception, and so on. 

#### Security
Basic authentication with username `admin` and password `Qwer1234` has been used. Communication between frontend and backend also gets through this authentication. One possible improvement might be using JWT in the future.

#### Code Quality
The entire source code has been analyzed with SonarLint to improve and maintain the code quality.

#### Testing
JUnit and Mockito have been used while writing the unit tests. To be able to test the exception handler MockMVC has been used. Test cases can be found under test folder of the project.

#### Logging
SLF4J has been used for logging. Application log level is `DEBUG` and Spring framework log level is `ERROR`. Log format and coloring has been also configured.

#### API Documentation Tool
SpringDoc - OpenAPI has been used for swagger style API documentation. You may use the credentials provided under Security section of the document if a prompt comes up on the browser. You can reach it from: [http://localhost:8090/swagger-ui/index.html](http://localhost:8090/swagger-ui/index.html).

### Frontend Side
The web application consists of a single page. VueJS has been used along with Nginx. You may access the application from: [http://localhost:8080/](http://localhost:8080/).

The user can pick the fiat currency and fill the `Amount to spend` to see how much BTC they can receive. The user can also store the most recent successful transaction displayed on the UI by clicking on `Store Transaction` button. After clicking this button the transaction will be persisted to Postgres DB under a table named `CONVERSION_TRANSACTIONS`.

There is a counter which refreshes itself every 10 seconds which is the price availability period. If the user focus gets out of the fiat amount (i.e. Amount to spend) input field or the fiat currency is changed or the counter reaches to 0 backend API is called. If the fiat amount is not in range `[25, 5000]` the value gets corrected with the closest limit before the API call occurs.

Vuex toast messages could improve here by getting rid of the necessity of changing the focus from input field to somewhere else to have the calculation.

### Other
* Application does not require any external configuration to be run.
* A RESTful API has been implemented by using Spring Boot.
* Maven has been used for dependency management and build.
* Interfaces have been separated from the classes.
* Code structure has been organized according to separation of concerns.
* DTO, BO, and DAO objects are separated from each other.

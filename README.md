# Insurance Service

The `insurance-service` is a Java-based Spring Boot application that provides APIs to manage and retrieve insurance details for individuals.

## Features
- Retrieve insurance details for a person.
- Calculate total insurance cost for a person.
- Mock data integration for testing purposes.

## Prerequisites
- Java 11 or higher
- Maven 3.6 or higher
- Spring Boot 2.5 or higher

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd insurance-service
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. The application will start on `http://localhost:8080` by default.

## Configuration
The application uses the following configuration properties:
- `insurance.service.host`: The base URL for the insurance service (default: `http://localhost:8080`).

You can override these properties in the `application.properties` or via environment variables.

## API Endpoints
### Get Insurance Details
- **URL**: `/api/v1/insurance/{personId}`
- **Method**: `GET`
- **Response**:
  ```json
  {
    "personId": "456",
    "totalInsuranceCost": 1800.0,
    "insurances": [
      {
        "type": "Health Insurance",
        "cost": 1000.0,
        "status": "Active"
      },
      {
        "type": "Car Insurance",
        "cost": 500.0,
        "status": "Active"
      }
    ]
  }
  ```

## Running Tests
To run the integration tests:
```bash
mvn test
```

The integration tests are located in:

## Mock Data
The application uses `MockInsurance` and `MockInsuranceData` classes to simulate insurance data for testing purposes.


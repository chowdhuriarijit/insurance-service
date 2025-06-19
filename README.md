# Insurance Service

insurance-service is a Java-based Spring Boot application that provides an API to retrieve insurance details for individuals. I have only implemented a GET method to fetch insurance data; PUT, DELETE, and POST methods are not included. The API is currently public, but in a real-world scenario, it should be secured—either by implementing OAuth 2.0 or using a service key for service-to-service communication.

## Features
- Retrieve insurance details for a person.
- Calculate total insurance cost for a person.
- Mock data integration for testing purposes.


## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/chowdhuriarijit/insurance-service.git
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

4. In the local environment application will start on `http://localhost:8080` by default.

## API Endpoints
### Get Insurance Details
Feature flag discountEnabled is configured in launchdarkly portal and for demo purpose I have only enabled it for person with Id 456 , as the bellow insurance response is for person with id 123 thats why discountEnabled false for this person and discount is not enabled. 
- **URL**: `/api/v1/insurance/{personId}`
- **Method**: `GET`
- **Response**:
  ```json
    {
        "personId": "123",
        "insurances": [
            {
            "name": "Pet insurance",
            "premium": 250,
            "status": "Active"
            },
            {
            "name": "Personal health insurance",
            "premium": 1200,
            "status": "Active"
            },
            {
            "name": "Car insurance",
            "premium": 900,
            "status": "Active"
            }
        ],
        "totalInsuranceCost": 2350,
        "vehicle": {
            "registrationNumber": "TEST123",
            "make": "Toyota",
            "model": "Corolla",
            "color": "Blue"
        },
        "discountApplied": "false"
    }
  ```
For person with id 456 discount is enabled so the response is bellow
```json
    {
        "personId": "456",
        "insurances": [
            {
            "name": "Home insurance",
            "premium": 1500,
            "status": "Active"
            },
            {
            "name": "Travel insurance",
            "premium": 300,
            "status": "Active"
            }
        ],
        "totalInsuranceCost": 1800,
        "discountApplied": "true",
        "discountAmount": 180,
        "discountedTotalInsuranceCost": 1620
    }
  ```

## Running Tests
To run the integration tests:
```bash
mvn test
```

## Mock Data
The application uses `MockInsurance` and `MockInsuranceData` classes to simulate insurance data for testing purposes.

## Note regarding feature toggle
I've used feature flags in my various projects to control application behavior dynamically without redeploying code. While here I demonstrated it with a simple case—enabling or disabling discount enabled flag  using LaunchDarkly, but the use of feature toggles goes far beyond that.
In real-world applications, I've also worked with feature toggle tools like Optimizely, Azure App Configuration (feature management),  implemented both feature toggles and A/B testing.


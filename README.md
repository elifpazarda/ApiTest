# API Test Automation Project

This repository contains REST API test automation scenarios developed using Rest Assured and TestNG. It includes Allure
integration for detailed reporting and is configured to run in a CI/CD pipeline using GitHub Actions with parallel
execution support.

## Project Structure

```
ApiTest/
├── .github/workflows/                 # GitHub Actions CI pipeline
│   └── test-report.yml
├── logs/                              # Logback log files
│   ├── test-execution.log
│   └── archived/
├── src/
│   └── test/
│       ├── java/
│       │   ├── base/                  # BaseTest setup (Allure, RestAssured, Logger)
│       │   ├── tests/                 # Test cases for various APIs
│       │   └── utils/                 # Utility for config reading
│       └── resources/
│           ├── config.properties      # Environment configuration
│           ├── logback.xml            # Logging configuration
│           ├── allure.properties      # Allure setup
│           └── testdata/              # Sample request payloads
│               └── createUser.json
├── testng.xml                         # Parallel execution configuration
├── .gitignore
├── pom.xml

```

## Supported APIs & Tests

- **fakerestapi.azurewebsites.net** – Validate activity list and titles
- **restcountries.com** – Verify countries where Turkish is spoken
- **reqres.in** – CRUD operations for users (GET, POST, PUT, DELETE)

## Sample Test Validations

- HTTP status code correctness
- Content-Type headers
- JSON response body values
- Data-driven tests via `@DataProvider`

## Running the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/elifpazarda/ApiTest.git
   cd ApiTest
   ```

2. Run tests locally:
   ```bash
   mvn clean test
   ```

3. Generate Allure report (optional):
   ```bash
   allure serve target/allure-results
   ```

## GitHub Actions CI/CD

Test scenarios are triggered on every push or pull request to `main`. The pipeline:

- Runs `mvn test`
- Exports reports (HTML, Allure)
- Uploads them as artifacts

You can find the config under: `.github/workflows/test-report.yml`

## Technologies Used

- Java 17
- Rest Assured
- TestNG
- Allure Reporting
- Logback Logging
- GitHub Actions
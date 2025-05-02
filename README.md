# API Test Automation Project

This repository contains API test automation examples developed for various web services. The project is built using the Rest Assured library, with test scenarios managed through the TestNG framework.

To provide detailed reporting of test results, Extent Reports has been integrated, and the reports are converted into PDF format for archiving. GitHub Actions has been configured for Continuous Integration/Continuous Deployment (CI/CD) processes.

---

##  Project Structure

```
ApiTest/
├── src/
│   └── test/
│       └── java/
│           ├── base/                   
│           │   └── BaseTest.java
│           ├── tests/                 
│           │   ├── FakeRestApiTest.java
│           │   ├── GetPostsTest.java
│           │   ├── ReqresTest.java
│           │   └── RestCountriesTest.java
│           └── utils/                  
│               └── PdfReportUtil.java
│
├── src/
│   └── test/
│       └── resources/
│           └── testng.xml
│           └── testdata/              
│               └── createUser.json
│
├── test-output/
│   ├── extent-report.html             
│   └── pdf-reports/
│       └── extent-report.pdf          
│
└── README.md
```
---

## Contents

This project includes test scenarios for the following APIs:

- **jsonplaceholder.typicode.com**: Examples that test basic GET operations and user data
- **reqres.in**: Performing CRUD operations – retrieving, creating, updating, and deleting user information
- **restcountries.com**: Verifying region and capital information of countries where Turkish is spoken
- **fakerestapi.azurewebsites.net**: Testing the number of activities and their titles

Each test scenario validates the responses of the related API endpoints based on the following criteria:
- HTTP status codes
- Content-Type
- JSON body content

---

##  How to Run the Tests

1. Clone the repository:

```bash
git clone https://github.com/your-username/ApiTest.git
cd ApiTest
```

2. Install dependencies (if using Maven):

```bash
mvn clean install
```

3. Run the tests:

```bash
mvn test
```

4. View Reports:
    - HTML Report: `test-output/extent-report.html`
    - PDF Report: `test-output/pdf-reports/extent-report.pdf`


---
## Technologies Used

- **Java**: Main programming language of the project
- **Rest Assured**: Java library used for testing API requests and responses
- **TestNG**:  Powerful framework for organizing tests and enabling parallel execution
- **Extent Reports**: Visualizes test outputs as interactive HTML reports
- **openhtmltopdf + jsoup**: Used to convert HTML reports into PDF format
- **GitHub Actions**: Automates test execution and uploads reports during the CI/CD process
---
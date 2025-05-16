# Jupiter Testing Framework

## Overview
The Jupiter Testing Framework is a Selenium-based automation framework for testing the PlanIt Jupiter Toys web application. It implements the Page Object Model (POM) design pattern.

## Features
- Page Object Model implementation for better test maintenance
- Cross-browser testing support (Chrome, Firefox, Edge)
- Logging capabilities for test execution traceability
- JUnit Jupiter for test execution and assertions
- Configurable test environment via base URL setting

## Project Structure
```
com.planittesting.cloud.jupiter
├── pages
│   ├── BasePage.java - Base class for all page objects
│   └── ContactPage.java - Page object for Contact page
├── tests
│   ├── BaseTest.java - Base test setup and teardown
│   └── ContactPageTest.java - Test cases for Contact page
└── utility
    └── Browser.java - Browser factory for WebDriver creation
```

## Requirements
- Java JDK 11 or higher
- Maven
- WebDrivers for Chrome, Firefox, and Edge browsers

## Setup Instructions
1. Clone this repository
2. Install the required WebDrivers and ensure they're in your PATH
3. Run `mvn clean install` to build the project

## Running Tests
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ContactPageTest
```

## Available Test Cases
- `ContactPageTest.enterInvalidEmailTest()` - Validates error message for invalid email format

## Browser Configuration
The default browser is Chrome. To change the browser, modify the `Browser` selection in the `BaseTest.java` file.


## Base URL Configuration
The base URL is configured in `BaseTest.java`.

```java
protected String baseUrl = "https://jupiter.cloud.planittesting.com/#/home";
```

## Adding New Tests
1. Create a new test class that extends `BaseTest`
2. Create corresponding page objects in the `pages` package
3. Implement test methods with JUnit annotations

## Logging
The framework uses Java's built-in logging. Logs include:
- WebDriver initialization
- Navigation steps
- Test execution events

# swag-labs-test-automation

Java Playwright Test Automation

Welcome to the Java Playwright Test Automation repository. This repository contains automated tests for demo saucelabs application using the Playwright framework in Java.
Technology Stack

    Java
    Maven
    Playwright
    JUnit 5
    Lombok
    ExtentReport

### Test Execution Options
#### View Mode

You can choose to run tests in different view modes using the `-DVIEW_MODE` Maven option. Supported view modes include:

    Desktop (Default)
    Mobile

Example:

```bash
mvn clean test -DVIEW_MODE=MOBILE
```
#### Browsers

You have the flexibility to run tests in different browsers using the `-DBROWSER` Maven option. Supported browsers include:

    Chrome (Default)
    Firefox
    WebKit

Example:

```bash
mvn clean test -DBROWSER=FIREFOX
````
#### Test Suites

You can execute different test suites based on the `-DGROUPS` Maven option. Define your test tags and group your tests accordingly.

Example:

```bash
mvn clean test -DGROUPS=TEST_TAG
```
### Atomic Test Approach

Tests follow an atomic approach, ensuring that each test is independent of the others. This promotes maintainability and reliability in your test suite.
Custom Assertions

I've also included custom assertions to enhance the readability and expressiveness of your test cases. These assertions are tailored to the specific needs of your application.
### Screenshot on Test Failure

In case of a test failure, a screenshot will be captured and saved for your reference. This helps in diagnosing and debugging issues.
### Test Reporting

After the execution of the entire test plan, a test report will be automatically generated using ExtentReport. This report provides a comprehensive view of the test results.
### Email Notifications

You can configure the repository to send an email with the test report upon test execution completion. To enable this feature, change the parameters in `EmailParameters.class` and provide the -DEMAIL_PASSWORD option.

Example:

```bash
mvn clean test -DEMAIL_PASSWORD=your_password
```
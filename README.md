# ReadyAPI Test Automation HTML Report

This project demonstrates a simple Spring Boot service that parses a `testResult.xml` file and generates an HTML report using **ExtentReports v5**.

## How it works

When the application starts it reads `src/main/resources/testResult.xml`, parses the executed test cases and steps and writes an HTML report to `report.html`. Executed test cases are shown on the left of the report and the corresponding steps and results on the right.

## Running

Ensure Maven and Java 17 are installed. Then run:

```bash
mvn spring-boot:run
```

After the application finishes, open `report.html` in a browser to view the report.

## Sample XML

A minimal sample `testResult.xml` is provided in `src/main/resources` for demonstration.

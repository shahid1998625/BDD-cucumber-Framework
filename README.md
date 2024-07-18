# BDD-cucumber-Framework with TestNG, Java, and Selenium
BDD cucumber Framework

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Prerequisites](#prerequisites)
4. [Installation](#installation)
5. [Project Structure](#project-structure)
6. [Reports](#reports)
7. [Dependencies](#dependencies)


## Introduction
This repository contains a Behavior Driven Development (BDD) test automation framework using Cucumber, TestNG, Java, and Selenium WebDriver.
The framework supports the Page Object Model (POM) pattern and integrates Extent Reports for test reporting.

## Features
1. **Driver Management**
    - Utilized `DriverManager.java` class to initiate the Driver instance and support execution on different types of browsers.

2. **Page Object Model**
   - Implemented the Page Object Model pattern using @FindBy with the Page Factory model for better maintainability and readability of web page classes.

3. **Cucumber**
   - Cucumber used for BDD Styling in which feature files are written in Gherkin Format/language for easily understandable for anyone who uses this framework.
   - Developed Feature files for different features to outline the behavior of the application under test.

4. **Step Definitions**
   - Created Step Definition files to define the steps of each scenario in the feature files.

5. **TestNG**
   - Integrated TestNG as the Test Runner for test execution.

6. **Extent Reports**
   - Generated Cucumber Report and detailed Extent Reports.
   - Utilized an Extent Report Utility File to log the pass/fail status of test steps.
   - Captured and included screenshots for each test step in the HTML Extent Report, enhancing the clarity and detail of the test results.

7. **Screenshot Handling**
   - Used Screenshot Listeners to capture and embed screenshots Base64 images to Word format, storing them in a `word.doc` for easy access and review.

8. **Test Data Input**
    - Implemented an Excel Utility File to manage and input test data efficiently using File Input Stream for Data-driven testing with Excel utility

9. **Test Data Security**
    - Implemented encryption and decryption of test data using Jar files to ensure the data security.

## Prerequisites
- Java JDK 11 or higher 
- Maven 3 or higher
- An IDE (e.g., IntelliJ IDEA)
- Web browsers (e.g., Chrome, Edge, Firefox)

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/your-repository.git
   mvn clean install

## Project Structure
BDD-Cucumber/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── QM/
│   │   │           ├── drivers/
│   │   │               └── DriverManager.java
│   │   │           ├── jars/
│   │   │               └── DataDecryptor.jar
│   │   │           ├── listeners/
│   │   │               └── CaptureScreenshot.java
│   │   │               └── ImageToWord.java
│   │   │               └── ScreenshotListener.java
│   │   │           ├── pages/
│   │   │               └── GmailPage.java
│   │   │               └── LoginPracticePage.java
│   │   │               └── outlookPage.java
│   │   │           ├── utils/
│   │   │               └── DataDecrypt.java
│   │   │               └── DecryptionUtil.java
│   │   │               └── ExcelUtil.java
│   │   │               └── ExtentReportUtil.java
│   ├── test/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── QM/
│   │   │           ├── hooks/
│   │   │               └── Hooks.java
│   │   │           ├── runners/
│   │   │               └── BaseClass.java
│   │   │               └── TestRunner.java
│   │   │           ├── steps/
│   │   │               └── gmailStepDef.java
│   │   │               └── loginPracticeStepDef.java
│   │   │               └── outlookStepDef.java
│   │   └── resources/
│   │       ├── features/
│   │            └── gmail.feature
│   │            └── loginPractice.feature
│   │            └── outlook.feature
│   │       ├── Test Data/
│   │           └── InputTestData.xlsx
└── pom.xml
└── README.md


## Reports
Cucumber Reports:
Cucumber reports are generated in the target/cucumber-reports directory.

Extent Reports:
Extent reports are generated in the reports/extent-reports directory. These reports include detailed information about test execution and screenshots.

## Dependencies
Cucumber
TestNG
Selenium WebDriver
Extent Reports
Apache POI (for Excel utility)
Encryption libraries (for data security)
For detailed versioning, please refer to the pom.xml file.
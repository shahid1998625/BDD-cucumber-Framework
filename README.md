# BDD-cucumber-Framework
BDD cucumber Framework 

## Features

1. **Driver Management**
    - Utilized `DriverManager.java` class to initiate the Driver instance and support execution on different types of browsers.

2. **Test Data Input**
    - Employed an Excel Utility File to manage and input test data efficiently using File Input Stream.

3. **Test Data Security**
    - Implemented encryption and decryption of test data using Jar files to ensure the data security.

4. **Screenshot Handling**
    - Used Screenshot Listeners to convert Base64 images to Word format, storing them in a `word.doc` for easy access and review.

5. **Reporting**
   - Generated detailed Cucumber and Extent Reports.
   - Utilized an Extent Report Utility File to log the pass/fail status of test steps.
   - Captured and included screenshots for each test step in the HTML Extent Report, enhancing the clarity and detail of test results.

6. **Test Runner**
   - Integrated TestNG as the Test Runner for executing the tests.

7. **Step Definitions**
   - Created Step Definition files to define the steps of each scenario in the feature files.

8. **Page Object Model**
   - Implemented the Page Object Model pattern using @FindBy with the Page Factory model for better maintainability and readability of web page classes.

9. **Feature Files**
   - Developed Feature files for different features to outline the behavior of the application under test.
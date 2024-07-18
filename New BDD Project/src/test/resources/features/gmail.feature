Feature: Gmail Login and Send an email

@GmailTestCase1 @run
  Scenario: Gmail Login with username and Password
    Given User Launches the URL
    When user enters the username and password
    Then Verify Gmail Homepage is displayed and compose and email
    Then Close the browser


  @GmailTestCase2 @run
  Scenario: Gmail login2
    Given User Launches the Google Account URL
    When user enters the username and password
    Then user clicks on google apps button and navigate to
    Then Verify Gmail Homepage is displayed and compose and email
    Then Close the browser
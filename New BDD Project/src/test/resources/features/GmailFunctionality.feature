Feature: Gmail Login and Send an email

@GmailTestCase1
  Scenario Outline: Gmail Login with username and Password
    Given User Launches the URL "<URL>"
    When user enters the username and password "<username>" "<password>"
    Then Verify Gmail Homepage is displayed and compose and email


    Examples:
    | URL               | username                  | password |
    |https://gmail.com/ |  testingemail625@gmail.com | Password62598@ |
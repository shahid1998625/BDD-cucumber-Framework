Feature: Outlook Login and Send an email


  @run
  Scenario: outlook Login
    Given user launches microsoft link
    When user enters username and password
    Then User will be navigated to Microsoft dashboard homepage
    Then Now click on outlook , will be navigated to outlook mail homepage
    And Now click on new email and write a email and send
    Then Close the browser

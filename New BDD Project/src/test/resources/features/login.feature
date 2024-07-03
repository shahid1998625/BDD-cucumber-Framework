Feature: Login Functionality


  @Test123
  Scenario Outline: Successful login with valid credentials
    Given the user is on the login page
    When the user will be entering the username "<validTextArea>"
#    When the user enters username "validUsername"
#    And the user enters password "validPassword"
#    And the user clicks on the login button
    Then the user should be redirected to the home page

    Examples:
      |  validTextArea |
      | quality matrix |
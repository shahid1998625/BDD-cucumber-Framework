Feature: Outlook Login and Send an email


  @T1u
  Scenario Outline: Outlook Login
    Given user launches outlook "<URL>"
    When user enters username and password <username> <password>
    Then User will be navigated to outlook homepage
    And Now click on new email and write a email and send "<Toemail>" "<ccEmail>" "<AddSubject>"


    Examples:
    | URL                                 |               username              |      password     |             Toemail               |                ccEmail                      |       AddSubject              |       MessageBody         |
    | https://login.microsoftonline.com/  | |                                                       |                                   |                                             |                               |                           |
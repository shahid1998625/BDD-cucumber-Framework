Feature: Outlook Login and Send an email


  @T1u
  Scenario Outline: Outlook Login
    Given user launches microsoft link "<URL>"
    When user enters username and password <username> <key>
    Then User will be navigated to Microsoft dashboard homepage
    Then Now click on outlook , will be navigated to outlook mail homepage
    And Now click on new email and write a email and send "<Toemail>" "<ccEmail>" "<AddSubject>"


    Examples:
    | URL                                 |               username                                           |          key                |             Toemail                 |                ccEmail                      |       AddSubject              |       MessageBody         |
    | https://login.microsoftonline.com/  | ac3nKr/qRTJJwHMCjJ2JuxVrEhlKlh6zpIcCUjvmch4hbynj6TblwdUufHaWef/C |      qIl41qx4njXiA7VHCy3G2Q==    | abdulsami_shahid@quality-matrix.com |     abdulsami_shahid@quality-matrix.com     |     Applying for a leave      |                           |


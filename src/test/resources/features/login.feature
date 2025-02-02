Feature: LoginPage Scenario

  Background:
    Given Go to catchylabs

  Scenario: Success login
    When Enter the username
    And  Enter the password
    And  Click on the login
    Then The title will contains the "apps"

  Scenario: Fail Login Invalid Username
    When Enter the wrong username
    And  Enter the password
    And  Click on the login
    Then The error message "Username or Password Invalid!" is displayed

  Scenario: Fail login Invalid Password
    When Enter the username
    And  Enter the wrong password
    And  Click on the login
    Then The error message "Username or Password Invalid!" is displayed

  Scenario: Fail login Empty Fields
    When  Click on the login
    Then The error message "Username or Password Invalid!" is displayed

  Scenario: Open Money Transfer Page To Back
    When Enter the username
    And  Enter the password
    And  Click on the login
    Then The title will contains the "apps"
    When Click "OPEN MONEY TRANSFER" button
    Then The title will contains the "money-transfer"
    When Click "Back" button


Feature: Account Details Page

  Background:
    Given Go to catchylabs
    When Enter the username
    And  Enter the password
    And  Click on the login
    Then The title will contains the "apps"
    When Click "OPEN MONEY TRANSFER" button

  Scenario: Open My Account Screen
    Then The my account screen is displayed

  Scenario: Verify account details information
    Then The account name should be "EceAccount"
    And The account type should be "CHECKING"

  Scenario: Open Transfer Money Screen
    When User clicks on "Transfer money" button
    Then The transfer money screen is displayed

  Scenario: Open Add Money Screen
    When User clicks on "Add money" button
    Then The add money screen is displayed

  Scenario: Open Edit Account Screen
    When User clicks on "Edit account" button
    Then The edit account screen is displayed

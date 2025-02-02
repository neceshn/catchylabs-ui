Feature: Edit Account Popup Validation

  Background:
    Given Go to catchylabs
    When Enter the username
    And  Enter the password
    And  Click on the login
    Then The title will contains the "apps"
    When Click "OPEN MONEY TRANSFER" button
    Then The title will contains the "money-transfer"
    And User clicks on "Edit account" button

  Scenario: Verify Edit Account Popup UI Elements
    Then The edit account popup title should be "Edit account"
    And The account name input field should be displayed
    And The "UPDATE" button should be disabled

  Scenario: Validate Account Name Field - Empty Input
    When User clears the account name input field
    And User moves focus away from the account name input field
    Then The "UPDATE" button should be disabled

  Scenario: Validate Account Name Field - Numeric Input
    When User enters "12345" field
    And User moves focus away from the account name input field
    Then The "UPDATE" button should be disabled

  Scenario: Validate Account Name Field - Valid Input
    When User enters "NewEceAccount" field
    And The "UPDATE" button should be enabled

  @smoke
  Scenario: Save Edited Account with Valid Data
    When User enters "UpdatedAccount" field
    And User clicks on "UPDATE" button
    Then The account name should be "UpdatedAccount"

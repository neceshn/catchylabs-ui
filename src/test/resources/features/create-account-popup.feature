Feature: Create Account Popup Scenario

  Background:
    Given Go to catchylabs
    When Enter the username
    And  Enter the password
    And  Click on the login
    Then The title will contains the "apps"
    When Click "OPEN MONEY TRANSFER" button
    Then The title will contains the "money-transfer"
    When Click "CREATE AN ACCOUNT" button
    Then System Open The Popup "Create account"

  Scenario: Checking Account Popup Test
    And User check the "CREATE" button is disabled
    And User fill the Account Name "EceAccount"
    And User check the "CREATE" button is enabled

  Scenario: Saving Account Popup Test
    When User Select Account Type "Saving account"
    And User check the "CREATE" button is disabled
    And User fill the Account Name "EceAccount"
    And User check the "CREATE" button is enabled

  Scenario: Create Saving Account
    When User Select Account Type "Saving account"
    And User check the "CREATE" button is disabled
    And User fill the Account Name "EceAccount"
    And User check the "CREATE" button is enabled
    And Click Account Create button

  Scenario: Create Checking Account
    When User Select Account Type "Checking account"
    And User check the "CREATE" button is disabled
    And User fill the Account Name "EceAccount"
    And User check the "CREATE" button is enabled
    And Click Account Create button

  Scenario: Check Checking Account
    When User Select Account Type "Checking account"
    And User check the "CREATE" button is disabled
    And User fill the Account Name "EceAccount"
    And User check the "CREATE" button is enabled
    And Click Account Create button
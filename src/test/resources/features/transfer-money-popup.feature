Feature: Transfer Money Popup Feature

  Background:
    Given Go to catchylabs
    When Enter the username
    And  Enter the password
    And  Click on the login
    Then The title will contains the "apps"
    When Click "OPEN MONEY TRANSFER" button

  Scenario: Open Transfer Money Screen
    When User clicks on "Transfer money" button
    Then The transfer money popup title should be "Transfer money"
    And The sender account dropdown should be displayed
    And The receiver account dropdown should be displayed
    And The amount input field should be displayed
    And The "Send" button should be disabled

  @smoke
  Scenario: Transfer Money
    When Store Amount
    And User clicks on "Transfer money" button
    And User enters "1" amount field
    And Click transfer Send button
    Then The money should be mined "1" successfully
Feature: Add Money Popup

  Background:
    Given Go to catchylabs
    When Enter the username
    And  Enter the password
    And  Click on the login
    Then The title will contains the "apps"
    When Click "OPEN MONEY TRANSFER" button
    And User clicks on "Add money" button in add money popup
    And Store Amount

  Scenario: Verify Add Money Popup UI Elements
    Then The add money popup title should be "Add money"
    And The card number field should be displayed
    And The card holder field should be displayed
    And The expiry date field should be displayed
    And The CVV field should be displayed
    And The amount field should be displayed

  Scenario: Validate Card Number Field with invalid input
    When User enters "123456789123456789123456789" into "Card number" field
    Then An error message "Too Long!" should be displayed for "Card number" field

  Scenario: Validate Card Number Field with invalid input
    When User enters "123" into "Card number" field
    Then An error message "Too Short!" should be displayed for "Card number" field

  Scenario: Validate Card Holder Field with invalid input
    When User enters "12345" into "Card holder" field
    Then An error message "Invalid card holder name" should be displayed for "Card holder" field

  Scenario: Validate Expiry Date Field with invalid input
    When User enters "13/99" into "Expiry date" field
    Then An error message "Wrong date. Please give a correct date" should be displayed for "Expiry date" field

  Scenario: Validate Expiry Date Field with invalid input
    When User enters "12345677" into "Expiry date" field
    Then An error message "Wrong date. Please give a correct date" should be displayed for "Expiry date" field

  Scenario: Validate Expiry Date Field with invalid input
    When User enters "11/11" into "Expiry date" field
    Then An error message "Wrong date. Please give a correct date" should be displayed for "Expiry date" field

  Scenario: Validate CVV Field with invalid input
    When User enters "123456" into "CVV" field
    Then An error message "Required" should be displayed for "CVV" field

  Scenario: Validate CVV Field with invalid input
    When User enters "1" into "CVV" field
    Then An error message "Too Short!" should be displayed for "CVV" field

  Scenario: Validate CVV Field with invalid input
    When User enters "abc" into "CVV" field
    Then An error message "Required" should be displayed for "CVV" field

  Scenario: Validate Amount Field with invalid input
    When User enters "ten dollars" into "Amount" field
    Then An error message "Invalid amount" should be displayed for "Amount" field

  @smoke
  Scenario: Successful Add Money Operation
    When User enters "4111111111111111" into "Card number" field
    And User enters "Ece Şahin" into "Card holder" field
    And User enters "12/25" into "Expiry date" field
    And User enters "123" into "CVV" field
    And User enters "10.20" into "Amount" field
    And User clicks on "Add" button in add money popup
    Then The money should be added "10.20" successfully

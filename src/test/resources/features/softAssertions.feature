Feature: Practice soft assertions

  @soft
  Scenario: Soft assertions practise
    Given user is on Docuport login page
    Then user validates "Login" text as displayed
    Then user validates "Docuport" text as displayed
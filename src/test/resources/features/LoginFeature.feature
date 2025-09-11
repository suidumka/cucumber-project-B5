Feature: Docuport Login Logout Feature

  Background:
    Given

  @smoke
  Scenario: Login as a client
    Given user is on Docuport login page
    When user enters username for client
    And user enters password for client
    Then user clicks login button and user should be able to see home page for client


  Scenario: Login as a employee
    #Given user is on Docuport login page
    When user enters username for employee
    And user enters password for employee
    Then user should be able to see home page for employee


  Scenario: Login as a advisor
    #Given user is on Docuport login page
    When user enters username for advisor
    And user enters password for advisor
    Then user should be able to see home page for advisor


  Scenario: Login as a supervisor
   # Given user is on Docuport login page
    When user enters username for supervisor
    And user enters password for supervisor
    Then user should be able to see home page for supervisor



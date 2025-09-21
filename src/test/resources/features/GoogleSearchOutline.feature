Feature: Scenario Outline Practice


  @google_search_outline @smoke
  Scenario Outline:
    Given user is on Google search page
    When user searches for "<country>"
    Then user should be able to see "<capital>" in the results as capital
    And we love Loop Academy

    Examples:
      | country     | capital          |
      | Kyrgyzstan  | Bishkek          |
      | USA         | Washington, D.C. |
      | Azerbaijan  | Baku             |
      | Ukraine     | Kyiv             |
      | Afghanistan | Kabul            |
      | Turkiye     | Ankara           |
      | Uzbekistan  | Tashkent         |
      | Georgia     | Tbilisi          |
Feature: passing multiple parameters to the same step

  @google_search_data_table
  Scenario: Searching multiple items||
    Given  user is on Google search page
    Then user searches the following items
    |cup|
    |Suidum|
    |Mac|
    |Love Bishkek|
    |Tom and Jerry|
    And we love Loop Academy


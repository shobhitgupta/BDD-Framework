@browser
Feature: Google Search Demo

  Scenario Outline: Google Search
    Given user is on google search page
    When user search for "<text>"
    Then result should display the link "<result>"

    Examples: 
      | text     | result                 |
      | selenium | Web Browser Automation |
      | selenium | Mercury QTP            |


 
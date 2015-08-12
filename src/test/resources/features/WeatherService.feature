@service
Feature: Weather Service
As a system
I want a service to return weather information for a location
So that I can obtain current weather and present it in a user interface

 Scenario: Obtain the weather in London with JSON results
    Given weather service is available
    When system checks weather for city "London" with country code "uk"
    Then user validates weather service sent the following JSON response:
    | xpath       | value    |
    | //coord/lon | -0.13    |
    | //coord/lat | 51.51    |
 
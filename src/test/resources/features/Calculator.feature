@test
Feature: Calculator Testing
  As a user
  I want to use a calculator
  So that I dont need to calculate myself

  Scenario: Add two numbers
  	Given i have a calculator
    When I add 2 and 3
    Then the result should be 5
    
  Scenario: Subtract two numbers
  	Given i have a calculator
  	When I subtract 10 and 5
  	Then the result should be 5 
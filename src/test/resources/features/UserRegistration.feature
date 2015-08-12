Feature: User Registration

# Imperative Style
Scenario: Redirect user to originally requested page after logging in
 	
  Given a User "dave" exists with password "secret"
  And I am not logged in
  When I navigate to the home page
  Then I am redirected to the login form
  When I fill in "Username" with "dave"
  And I fill in "Password" with "secret"	
  And I press "Login"
  Then I should be on the home page
  

# Declarative Style 
 Scenario: Redirect user to originally requested page after logging in
  Given I am an unauthenticated User
  When I attempt to view some restricted content
  Then I am shown a login form
  When I authenticate with valid credentials
  Then I should be shown the restricted content


 
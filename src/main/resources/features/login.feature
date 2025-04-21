@abc
Feature: Login functionality

  Scenario: Valid user login
    Given the user is on the base page
    And the user clicks on signIn
    When the user enters valid credentials
    And clicks on the login button
    Then the user should be redirected to the homepage


  Scenario: Invalid user login
    Given the user is on the base page
    And the user clicks on signIn
    When the user enters invalid credentials
    And clicks on the login button
    Then the user should see an error message
    And should not be redirected to the homepage

  Scenario: Login with empty username and password
    Given the user navigates to the login page
    When the user clicks the login button without entering credentials
    Then an error message should be displayed

  Scenario: Password field should mask the input
    Given the user navigates to the login page
    When the user enters password
    Then the password field should mask the entered characters




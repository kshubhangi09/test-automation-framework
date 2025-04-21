@api
Feature: Account Management API

  Scenario Outline: Register a new user
    Given a user with login "<login>", email "<email>", and password "<password>"
    When the user registers via the API with username "admin" and password "admin"
    Then the API response status code should be 201
    Then the API response should not have a body

    Examples:
      | login  | email             | password      |
      | hbv    | hbv@example.com   | hbvPassword   |
      | ra     | ra@example.com    | raPass123     |
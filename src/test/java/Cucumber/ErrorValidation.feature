Feature: Error message is displayed
  Scenario Outline: Negative test on ecommerce pae
    Given I landed on ecommerce page
    When Loged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed
    Examples:
      | name                  | password     |
      | animash0805@gmail.com | Animesh@0806 |
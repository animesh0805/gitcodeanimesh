Feature: Puchase the Order from Ecoomerse website

  Background:
    Given I landed on ecommerce page

 Scenario Outline: Positive test of Submitting the order
   Given Loged in with username <name> and password <password>
   When I add the product <product> to Cart
   And Checkout <product> and submit the order
   Then "THANKYOU FOR THE ORDER." message is displayed on confirmantion page

   Examples:
     | name                  | password   | product     |
     | animash9961@gmail.com | Manoj@0805 | ZARA COAT 3 |


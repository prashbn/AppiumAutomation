Feature: The user should be able to login and update


  @Regression
  Scenario Outline: Login into Amazon app, select a 65-inch tv and select a random product and proceed to checkout. Validate the selected product details in checkout.
    Given The Amazon app installed and launched
    When the user logins in the app with Username "<userName>" and Password "<password>"
    Then the user should be able to search a productName "<productName>" and filter based on FilterValue "<filterValue>"
    Then the selected product details should match the product in checkout page



    Examples:
     |userName         ||password    ||productName||filterValue|
     |prashbn@gmail.com||XXXXXXX   ||65-Inch TV ||TVs        |
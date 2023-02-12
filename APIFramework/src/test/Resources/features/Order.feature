Feature: It is an API test about order from getting products to deleting order

  @GetProducts
  Scenario: Enter given information and get product list in an array
    Given enter given url "https://simple-grocery-store-api.glitch.me"
    Then enter the end point "/products" store the response to products array and verify status code "200"


  @CreateCart
  Scenario: Create a cart for adding items and end up the order successfully
    Given enter given url "https://simple-grocery-store-api.glitch.me"
    Then post the end point "/carts" and verify status code "201"

  @AddItemToCart
  Scenario: Add an item to the cart end verify the status code
    Given enter given url "https://simple-grocery-store-api.glitch.me" and use json format
    Then serialise the request json body and post the end point and verify status code "201"

  @CreateOrder
  Scenario: Create an order and verify the status code
    Given enter given url "https://simple-grocery-store-api.glitch.me" and use json format
    Then enter requested parameters with end point "/orders" and verify statuscode "201"
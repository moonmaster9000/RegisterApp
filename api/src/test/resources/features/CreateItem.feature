Feature: Create Item

  Scenario: POST "/items"
    When POST Content-Type:"application/json" url:"/items" body:
      """
      {
        "displayName": "ACME BRAND MILK",
        "priceInCents": 1,
        "barcode": "unique barcode"
      }
      """
    Then 201 response:
      """
      {
        "displayName": "ACME BRAND MILK",
        "priceInCents": 1,
        "barcode": "unique barcode"
      }
      """
Feature: Create Item

  Scenario: POST "/items" with valid data
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


  Scenario: POST "/items" with invalid data
    When POST Content-Type:"application/json" url:"/items" body:
      """
      {
        "displayName": null,
        "priceInCents": -1,
        "barcode": null
      }
      """
    Then 422 response:
      """
      [
        {
          "property": "displayName",
          "constraint": "REQUIRED"
        },
        {
          "property": "priceInCents",
          "constraint": "POSITIVE"
        },
        {
          "property": "barcode",
          "constraint": "REQUIRED"
        },
      ]
      """

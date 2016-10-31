Feature: Add Item To Transaction

  Scenario: Valid Transaction and Existing Item Barcode
    Given POST Content-Type:"application/json" url:"/transactions" body:
      """
      {
        "id": "transactionid"
      }
      """

    And POST Content-Type:"application/json" url:"/items" body:
      """
      {
        "barcode": "itembarcode",
        "priceInCents": 1,
        "displayName": "milk"
      }
      """

    When POST Content-Type:"application/json" url:"/transactions/transactionid/items/itembarcode"

    Then 201 response:
      """
      {
        "id": "transactionid",
        "items": [
          {
            "barcode": "itembarcode"
          }
        ]
      }
      """
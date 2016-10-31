Feature: Create Transaction

  Scenario: POST "/transactions"
    When POST Content-Type:"application/json" url:"/transactions" body:
      """
      {
        "id": "1"
      }
      """

    Then 201 response:
      """
      {
        "id": "1",
        "items": [],
        "totalInCents": 0
      }
      """
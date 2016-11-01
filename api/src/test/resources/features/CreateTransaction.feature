Feature: Create Transaction

  Scenario: POST "/transactions"
    When POST Content-Type:"application/json" url:"/transactions" body:
      """
      {
        "id": "userprovidedid"
      }
      """

    Then 201 response:
      """
      {
        "id": "userprovidedid",
        "items": [],
        "totalInCents": 0
      }
      """
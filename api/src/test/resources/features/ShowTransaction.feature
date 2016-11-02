Feature: Show Transaction

  Scenario: Valid Transaction ID
    Given POST Content-Type:"application/json" url:"/transactions" body:
      """
      {
        "id": "userprovidedtransactionid"
      }
      """

    When GET url:"/transactions/userprovidedtransactionid"

    Then 200 response:
      """
      {
        "id": "userprovidedtransactionid",
        "items": []
      }
      """
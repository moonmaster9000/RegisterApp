Feature: Create Transaction

  Scenario: POST "/transactions"
    When POST Content-Type:"application/json" url:"/transactions"
    Then 201 response:
    """
    {
      "items": [],
      "totalInCents": 0
    }
    """
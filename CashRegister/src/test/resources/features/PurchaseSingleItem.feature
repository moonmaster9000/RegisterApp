Feature: Purchase Single Item

  Scenario: Item is in system
    Given an admin has added an item to the system
    When a cashier scans in the barcode of that item
    Then the register should display a total equal to the price of that item
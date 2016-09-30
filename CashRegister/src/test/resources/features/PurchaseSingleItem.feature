Feature: Purchase Single Item

  Scenario: Item is in system
    Given an admin has added "MILK" brand milk to the cash register system, including display name, barcode, and price in cents
    When a cashier scans in the barcode of "MILK"
    Then the register should display a total equal to the price of the "MILK"
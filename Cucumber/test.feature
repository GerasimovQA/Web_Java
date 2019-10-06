Feature: Test sum reqest.
  My first Cucumber

  Scenario Outline: Correct or not correct sum in response
    Given Url "<url>" and Request "<request>"
    When I go to Url and input Request
    Then Sum answers should be "<compare>" "<sum>"

  Examples:
      | url                     | request | compare | sum   |
      | https://www.google.com/ | Огурец  | more    | 10000 |
      | https://www.google.com/ | Тыква   | smaller | 10    |



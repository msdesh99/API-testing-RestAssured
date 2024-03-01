@updateUser
Feature: PUT Request
  @updateAPIUserPositive
Scenario Outline: Positive scenario TestCases to create user in UserAPI
Given User creates PUT Request for UserAPI module "withBasicAuth" with testcases
When user sends request Body with details for "14522" "positive" scenario from excel for "<RowNo>".
Then user receives Status with PUT response body.
Examples:
| RowNo |
| 1 |
| 2 |
| 3 |
| 4 |
| 5 |
| 6 |
| 7 |
| 8 |
| 9 |
  
     @updateAPIUserNegative
Scenario Outline: Negative scenario TestCases to create user in UserAPI
Given User creates PUT Request for UserAPI module "withBasicAuth" with testcases
When user sends request Body with details for "14522" "negative" scenario from excel for "<RowNo>".
Then user receives Status with PUT response body.
Examples:
| RowNo |
| 1 |
| 2 |
| 3 |
| 4 |
| 5 |

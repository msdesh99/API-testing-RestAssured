@createUser
Feature: Create User
  @createAPIUserNegative
Scenario Outline: Negative scenario TestCases to create user in UserAPI
Given User creates POST  Request for UserAPI module "withBasicAuth" with testcases
When user sends request Body with details for "negative" scenario from excel for "<RowNo>".
Then user receives Status with response body.
Examples:
| RowNo |
| 1 |
| 2 |
| 3 |
| 4 |
| 5 |

  @createAPIUserPositive
Scenario Outline: Negative scenario TestCases to create user in UserAPI
Given User creates POST  Request for UserAPI module "withBasicAuth" with testcases
When user sends request Body with details for "positive" scenario from excel for "<RowNo>".
Then user receives Status with response body.
Examples:
| RowNo |
| 1 |
| 2 |
   #@createAPIUserNegative
#Scenario Outline: Positive scenario TestCases to create user in UserAPI
#Given User creates POST  Request for UserAPI module "<AuthStatus>"
#When user sends request Body with details for "negative" scenario from excel.
#Then user receives Status with response body.
#Examples: 
 #      | AuthStatus    |  
  #     | NoAuth			 |      
   #    | withBasicAuth | 
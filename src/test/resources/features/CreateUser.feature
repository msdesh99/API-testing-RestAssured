@createUser
Feature: Create User
  @createAPIUser
  Scenario outline: Creating UserAPI
    Given User creates POST Request to create a user<KeyOption>
    When user sends request Body with details from excel.
    Then user receives Status with response body.

    Examples: 
       | KeyOption        |        
       | No-Auth					|					     
       | with Basic Auth  |  
     
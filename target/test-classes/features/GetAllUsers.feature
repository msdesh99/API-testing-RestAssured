@getUserModule
Feature: Check Users

@getAllUsers
Scenario Outline: Check if user able to retrieve all users
Given User creates GET Request for UserAPI module "<AuthStatus>"
When User sends HTTPS Request 
Then User receives 200 OK Status with response body.     
Examples: 
       | AuthStatus    |
       | NoAuth			   |
       | withBasicAuth | 
     
@getUserByUserId
Scenario Outline: Check if user able to retrieve user by userId
Given User creates GET Request for UserAPI module "<AuthStatus>" "UserId" "<Status>" 
When User sends GET Request with "<UserId>" 
Then User receives "<Code>" Status with response body.     
Examples: 
        | AuthStatus    | UserId | Code | Status   |
        | NoAuth			  | 14519  | 401  |	positive |
        | NoAuth			  | 00000  | 401  |	positive |        
        | withBasicAuth | 14519  | 200  | positive |
        | withBasicAuth | 00000  | 404  | negative |
        
       
@getUserByUserName
Scenario Outline: Check if user able to retrieve all users by userName
Given User creates GET Request for UserAPI module "<AuthStatus>" "UserName" "<Status>"
When User sends GET Request with "<UserName>"
Then User receives "<Code>" Status with response body.     
Examples: 
        | AuthStatus    | UserName | Code | Status   |
        | NoAuth			  | TeamNine | 401  |	positive |
        | NoAuth			  | TEAMTeam | 401  |	positive |        
        | withBasicAuth | TeamNine | 200  | positive |
        | withBasicAuth | TEAMTeam | 404  | negative |
        
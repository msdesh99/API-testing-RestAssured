@deleteUserModule
Feature: Delete Users
@deleteUserByUserId
Scenario Outline: Check if user able to delete user by userId
Given User creates DELETE Request for UserAPI module "<AuthStatus>" "Id" "<Status>" 
When User sends DELETE Request with "<UserId>" 
Then User receives "<Code>" Status with response body for Delete Request.     
Examples: 
        | AuthStatus    | UserId | Code | Status   |
        | NoAuth			  | 14519  | 401  |	positive |
        | NoAuth			  | 00000  | 401  |	positive |        
        | withBasicAuth | 14519  | 200  | positive |
        | withBasicAuth | 00000  | 404  | negative |
        
       
@deleteUserByUserName
Scenario Outline: Check if user able to delete all users by userName
Given User creates DELETE Request for UserAPI module "<AuthStatus>" "name" "<Status>"
When User sends DELETE Request with "<UserName>"
Then User receives "<Code>" Status with response body for Delete Request.     
Examples: 
        | AuthStatus    | UserName | Code | Status   |
        | NoAuth			  | TeamNine | 401  |	positive |
        | NoAuth			  | TEAMTeam | 401  |	positive |        
        | withBasicAuth | TeamNine | 200  | positive |
        | withBasicAuth | Tesamesa | 404  | negative |
        
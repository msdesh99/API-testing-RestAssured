@getUserModule
Feature: Check Users

@getAllUsers
Scenario: Check if user able to retrieve all users
Given User creates GET  Request for  All  all users
When User sends HTTPS Request
Then User receives 200 OK Status with response body.     



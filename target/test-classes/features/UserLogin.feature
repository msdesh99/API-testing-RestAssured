@login
Feature: UserApi Login

  @userApiLogin
  Scenario Outline: Check if user able to login using Basic Auth
    Given User passing credentials for authentication.
    When User sends username and  password with "<Username>" and "<Password>" from excel.
    Then User will be Authorized

    Examples: 
      | KeyOption        | password |
      | Numpy@gmail.com  | userAPI  |
      
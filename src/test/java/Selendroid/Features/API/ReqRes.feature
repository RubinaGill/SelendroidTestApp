@API
Feature:ReqRes User API Testing

  @GetUser
  Scenario: validate first name from API response
    Given the users endpoint exists
    When user sends Get API request of user from page 2
    Then user must get status code 200
    And user verifies first name of id 10 is 'Byron'

  @PostUser
  Scenario: validate post user request
    Given the users endpoint exists
    When user sends Post API request of users with name as 'Brynt' and job as 'BA'
    Then user must get status code 201
    And user verifies id is generated
    And user verifies response json scheme

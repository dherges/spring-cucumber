Feature: Hello testing

  Scenario: Hello API talks with us
    Given welcome message is "Hello again!"
    When client calls GET /hello
    Then response code is 200
    And response body is "Hello again!"
    And controller was not null

  Scenario: Hello API goes bust
    Given welcome message is "Well guys..."
    When client calls GET /hello
    Then response body is "Hello again!"

Feature: Hello testing

  Scenario: Hello API talks with us
    When client calls GET /hello
    Then response code is 200
    And response body is "Hello again!"

  Scenario: Hello API goes bust
    Given welcome message is "Well guys..."
    When client calls GET /hello
    Then response code is 200
    And controller was not null
    And response body is "Hello again!"

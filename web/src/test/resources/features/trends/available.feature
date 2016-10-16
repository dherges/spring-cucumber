Feature: Trends API: /trends/available

  Returns locations that Twitter has trending topic information for.


  Scenario: Returns 200 OK and a JSON document containing an array of locations.
    When client calls GET /trends/available
    Then response code is 200
    And response content type matches "application/json"
    And response json path '$.length()' equals 2
    And response json path '$[0].name' equals "Worldwide"

  Scenario: Returns example JSON document.
    When client calls GET /trends/available
    Then response body is
      """
      [{"country":null,"countryCode":null,"name":"Worldwide","placeType":null,"url":null,"woeid":1},{"country":"Germany","countryCode":"GER","name":"Mainz","placeType":{"code":7,"name":"town"},"url":"http://where.yahooapis.com/v1/place/2","woeid":2}]
      """

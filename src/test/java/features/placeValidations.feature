Feature: Validating place API

  @AddPlace
  Scenario Outline: Verify if place is successfully added using AddPlaceAPI
    Given Add place payload with "<name>", "<language>", "<address>"
    When user calls "AddPlaceAPI" with "Post" HTTP request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name    | language | address            |
      | AAhouse | English  | World Cross Center |
    |   BBhouse      |    Spanish      |          Sea cross center          |

    @DeletePlace
    Scenario: Verify if delete place functionality is working
      Given DeletePlace payload
      When user calls "deletePlaceAPI" with "POST" HTTP request
      Then the API call is success with status code 200
      And "status" in response body is "OK"

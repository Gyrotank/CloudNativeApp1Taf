Feature: Home Page can be accessed

#  Run configuration VM options to filter by tags:
#  -Dcucumber.filter.tags="@api"
#  -Dcucumber.filter.tags="@ui"
#  -Dcucumber.filter.tags="@api and @ui"
#  -Dcucumber.filter.tags="@api or @ui"

  @api
  Scenario: User accesses Home Page via API
    When User sends GET request to "/"
    Then User receives status code of 200
    And Response body contains text: "Health Check Passed"

  @api
  Scenario: User queries image data by label API - label exists
    When User sends GET request to "/image/Blue"
    Then User receives status code of 200
    And Response body contains text: "49bbcb86-f033-49ed-8829-f3c3180abe26"
    And Response body contains text: "cbdc6c04-5b8e-4281-8c34-a2c5420afda5"
    And Response body contains text: "Blue"

  @api
  Scenario: User queries image data by label API - label not exists
    When User sends GET request to "/image/BadLabel"
    Then User receives status code of 200
    And Response body contains text: "[ ]"

  @api
  Scenario: User uploads image file via API
    When User sends POST request to "/image" with multipart file "TestImage.png" attached
    Then User receives status code of 200
    And Response body contains text: "-TestImage.png"

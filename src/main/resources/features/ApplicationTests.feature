Feature: Application can be accessed

#  Run configuration VM options to filter by tags:
#  -Dcucumber.filter.tags="@api"
#  -Dcucumber.filter.tags="@ui"
#  -Dcucumber.filter.tags="@api and @ui"
#  -Dcucumber.filter.tags="@api or @ui"

  @api @local
  Scenario: User accesses Home Page via API
    When User sends GET request to "/"
    Then User receives status code of 200
    And Response body contains text: "Health Check Passed"

  @api
  Scenario Outline: User queries image data by label API - label exists
    When User sends GET request to "/image/Blue"
    Then User receives status code of 200
    And Response body contains text: <firstKey>
    And Response body contains text: <secondKey>
    And Response body contains text: "Blue"

    @prod
    Examples:
      | firstKey                               | secondKey                              |
      | "49bbcb86-f033-49ed-8829-f3c3180abe26" | "cbdc6c04-5b8e-4281-8c34-a2c5420afda5" |

    @dev-tf
    Examples:
      | firstKey                               | secondKey                              |
      | "49bbcb86-f033-49ed-8829-f3c3180abe26" | "cbdc6c04-5b8e-4281-8c34-a2c5420afda5" |

    @qa-tf
    Examples:
      | firstKey                               | secondKey                              |
      | "37e22595-7c5c-450d-8d34-d03daa3499cd" | "b41c05f7-39d6-48fd-9f02-493634026d52" |

    @prod-tf
    Examples:
      | firstKey                               | secondKey                              |
      | "11f1d6fa-e6aa-433f-b7d4-6f72f680c7ab" | "97fae2c2-cac8-4b45-ab85-7b31070640b4" |


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

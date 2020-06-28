Feature: Core nzbn test to verify api calls

    @run
    Scenario Outline: Automated test to verify response code on health status from nzbn service
        When I call API Endpoint request for health status
        Then I should get response with "<ResponseStatusCode>"

        Examples:
            | ResponseStatusCode |
            | 200                |

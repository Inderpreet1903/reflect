Feature: Trademe test to verify api calls

    Scenario Outline: Automated api test to check there is at least one listing in the TradeMe UsedCars category.
        When I call API Endpoint request for searching Motors
        Then I should get response with "<ResponseStatusCode>"
        And I verify the result has at least one listing

        Examples:
            | ResponseStatusCode |
            | 200                |

    Scenario Outline: Automated api test to check that the make ‘Kia’ exists.
        When I call API Endpoint request for used car makes
        Then I should get response with "<ResponseStatusCode>"
        And I verify the result has make 'Kia'

        Examples:
            | ResponseStatusCode |
            | 200                |

    Scenario Outline: Automated api test to check existing Used Car listing and confirm the details
        When I call API Endpoint request for searching Motors
        Then I should get response with "<ResponseStatusCode>"
        And I verify the result has a listing with details

        Examples:
            | ResponseStatusCode |
            | 200                |

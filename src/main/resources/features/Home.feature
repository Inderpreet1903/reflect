Feature: Tests to verify the used car search and data

    Scenario: Automated test to check there is at least one listing in the TradeMe UsedCars category.
        Given I open the website
        When I goto motors tab and search for used cars
        Then I verify result listing

    Scenario: Automated test to check that the make ‘Kia’ exists on website motor search.
        Given I open the website
        When I goto motors tab and select advance search link
        And I verify the result has make 'Kia' in the dropdown

    Scenario: Automated test to check any existing used Car listing and confirm the details
        Given I open the website
        When I goto motors tab and search for used cars
        Then I select the first listing to verify the details exists
            | Number plate         |
            | Kilometres           |
            | Body                 |
#            | Seats                |
            | Fuel type            |
            | Engine               |
            | Transmission         |
            | History              |
            | Registration expires |
            | WoF expires          |
#            | Model detail         |

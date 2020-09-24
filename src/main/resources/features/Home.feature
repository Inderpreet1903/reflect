Feature: Tests to verify the used car search and data

#    Scenario: Automated test to check loading page with language options.
#        Given I open the website
#        When I select the language drop down
#        Then I verify the languages available
#
#    Scenario Outline: Automated test to choose different language options '<language>'
#        Given I open the website
#        When I select the language drop down
#        Then I choose language '<language>'
#        And I choose next page to save data
#        And I choose next page to save data
#        And I choose next page to save data
#        Examples:
#            | language |
#            | Original |
#            | Thai     |
#            | Japanese |
#            | English  |

    Scenario: Automated test to write reflection
        Given I open the website
        When I click on button 'Write a Reflection'
        Then I fill the form to submit

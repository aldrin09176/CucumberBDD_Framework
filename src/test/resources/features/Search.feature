Feature: Search functionality

Scenario: User searches for a valid product
When User enters valid product "HP" into Search box field
And User clicks on Search button
Then User should get valid product displayed in search results

Scenario: User searches for an invalid product
When User enters invalid product "Honda" into Search box field
And User clicks on Search button
Then User should get a message about no product matching

Scenario: User searches without any product
When User dont enter any product name into Search box field
And User clicks on Search button
Then User should get a message about no product matching
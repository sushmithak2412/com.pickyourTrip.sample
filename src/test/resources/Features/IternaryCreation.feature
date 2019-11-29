Feature: ItineraryCreation
Scenario: PickYourTrip Login
Given Open Browser in chrome
When Click on 'homePage.LoginButton' button
Then Click on 'loginPage.phoneNumber' button
When Enter '8328092122' into 'loginPage.phoneNumber'
When Enter '5859' into 'loginPage.password'
Then Click on 'loginPage.loginButton' button
#Then Click on 'yourVaction.logo' button
Then Wait for Element to present 'yourVacation.planavacationtotextbox'
Then Click on 'yourVacation.planavacationtotextbox' button
Then Enter 'Bali' into 'yourVacation.planavacationtotextbox'
When Select place for 'Bali' from suggestion for 'yourVacation.autosuggestedValue'
Then Wait for Element to present 'yourVacation.month.PlanaVactionlabel'
Then Select 'MARCH' for 'yourVacation.month.value' with 'yourVacation.month.label' for month
Then Wait for Element to present 'yourVacation.days.Nowplanninglabel'
Then Select number of days as '3-5 days' for 'yourVacation.days.value' with 'yourVacation.days.label'
Then Wait for Element to present 'yourVacation.woudLikeTo.seelabel'
Then Wait for 5 seconds
Then Select like to visit 'Art & Culture,Adventure & Outdoor,Shopping & Leisure' for 'yourVacation.wouldLikeToSee.value' with 'yourVacation.wouldLikeToSee.label'
Then Click on 'getIternaryDetailsButton' button
Then Wait for 5 seconds
When Try to handle notification popup
Then Click on 'ItenaryPage.Tooltip' button
Then Click on 'ItenaryPage.editButton.clickable' button
Then Clear the value 'ItenaryPage.editButton'
Then Enter 'My Bali Vacation 2020' into 'ItenaryPage.editButton'
Then Click on 'GetTripCostButton' button
Then Wait for Element to present 'Departingdetails.departfrom.input'
Then Click on 'Departingdetails.departfrom.input' button
Then Wait for Element to present 'Departingdetails.departfrom.searchCities'
Then Click on 'Departingdetails.departfrom.searchCities' button
Then Enter 'MAA' into 'Departingdetails.departfrom.searchCities'
Then Click on 'Departingdetails.departfrom.searchCities.dropdown' button
Then Click on 'GetCostButton' button
#Then Wait for Element to present 'InclusionButton'
Then Wait for 40 seconds
Then Assert the title with entered Value as 'My Bali Vacation 2020'
Then Click on 'yourVaction.logo' button
Then switch to new element 'homePage.honeymoonlabel'
Then Click on 'homePage.honeymoonlink' button
Then Switch to new tab
Then Assert that 'ItineraryButton' is visible
Then Click on 'GetLatestCostButton' button
Then Wait for Element to present 'Departingdetails.departfrom.input'
Then Click on 'Departingdetails.departfrom.input' button
Then Wait for Element to present 'Departingdetails.departfrom.searchCities'
Then Click on 'Departingdetails.departfrom.searchCities' button
Then Enter 'MAA' into 'Departingdetails.departfrom.searchCities'
Then Click on 'Departingdetails.departfrom.forhoneymoonsearchCities.dropdown' button
Then Click on 'GetCostButton' button
Then Wait for 40 seconds
Then Assert that 'InclusionButton' is visible
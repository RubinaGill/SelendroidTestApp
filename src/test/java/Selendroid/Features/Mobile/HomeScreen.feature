@Mobile
Feature: Home Screen

  @HomeScreenElements
  Scenario: Validate elements of home screen
    Given user launches the app and is on home screen
    Then user verifies the title as 'selendroid-test-app' on home screen
    And user verifies UI elements

  @ENButton
  Scenario: Validate user is navigated to home screen on selecting no from EN Button
    Given user launches the app and is on home screen
    When user taps on EN button
    And user selects option as 'No, no' to end activity
    Then user must be navigated back to home page

  @ChromeLogoButton
  Scenario Outline: Validate user is able to select and view preferred car
    Given user launches the app and is on home screen
    When user taps on Chrome logo button
    Then user verifies the title as 'Web View Interaction'
    And user verifies the text as 'Hello, can you please tell me your name?'
    Then user enters name as '<username>'
    And user selects preferred car as '<preferredCar>'
    Then user clicks on send me your name button
    And verifies the confirmation text 'This is my way of saying hello'
    And user verifies '<username>' and '<preferredCar>' details
    Then user clicks on bottom link to start again
    Then user verifies default car selected is 'Volvo'
    Examples:
      | username | preferredCar |
      | TestUser | mercedes     |

  @UserRegistration
  Scenario Outline: Validate a new user is able to register
    Given user launches the app and is on home screen
    When user taps on File logo button
    Then user verifies the title as 'Welcome to register a new User'
    And user verifies the UI elements of register screen
    Then user verifies the name field is pre-populated with 'Mr. Burns'
    And user verifies default language is selected as 'Ruby'
    Then user enters the details - '<username>', '<email>', '<password>', '<name>', '<programmingLanguage>'
    And user accepts adds
    Then user taps on Register User (verify)
    And user verifies details - '<username>', '<email>', '<password>', '<name>', '<programmingLanguage>' on the next screen
    And user tap on Register User
    Then user must be navigated back to home page
    Examples:
      | username | email             | password  | name     | programmingLanguage |
      | TestUser | testuser@test.com | pass@Test | testName | Java                |


  @ProgressBarRegistration
  Scenario: Validate user is navigated to registration screen after progress bar completes
    Given user launches the app and is on home screen
    When user taps on Show Progress bar
    Then user waits for progress bar to disappear
    And user verifies the UI elements of register screen

  @ToastMessage
  Scenario: Validate user is able to view toast message
    Given user launches the app and is on home screen
    When user taps on Displays a toast button
    Then user verifies toast 'Hello selendroid toast!' on home screen

  @PopUpWindow
  Scenario: Validate user is able to dismiss pop-up window
    Given user launches the app and is on home screen
    When user taps on Display Pop up Window button
    Then user is able to dismiss the popup

  @UnhandledException
  Scenario: Validate user is able to dismiss pop-up window
    Given user launches the app and is on home screen
    When user taps on Press to throw unhandled exception button
    Then user app is stopped

  @UnhandledCustomException
  Scenario: Validate user is able to dismiss pop-up window
    Given user launches the app and is on home screen
    When user types 'test' to throw unhandled exception button
    Then user app keeps stopping
@Web
Feature:perform actions on webElements

  @DragComponent
  Scenario: user is able to drag component
    Given user navigates to website
    When user selects option as 'Droppable'
    Then user is able to drag Drag me to my target component to Drop here component

  @SelectComponent
  Scenario: user is able to select items
    Given user navigates to website
    When user selects option as 'Selectable'
    Then user is able to select items 'Item 1','Item 3','Item 7'

  @ControlGroupComponent
  Scenario: user is able to select options
    Given user navigates to website
    When user selects option as 'Controlgroup'
    Then user is able to select rental car options as 'Automatic' and 'Insurance' on horizontal toolbar
    And user is able to select rental car options as 'Automatic' and 'Insurance' on vertical toolbar
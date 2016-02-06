Feature: Smart House login
  To allow a user to get access to house's devices management

  Scenario: login with wrong name and password
    Given user pass his name 'hacker' and password 'wrong password'
    When the user press 'Submit' button
    Then the message should be shown
      """
      ERROR! You have entered an incorrect username or password. Please try again.
      """

  Scenario: login with empty name and password
    Given user pass his name '' and password ''
    When the user press 'Submit' button
    Then the message should be shown
      """
      ERROR! You have entered an incorrect username or password. Please try again.
      """

  Scenario: login with right name and wrong password
    Given user pass his name 'rusakovich' and password 'wrong password'
    When the user press 'Submit' button
    Then the message should be shown
      """
      ERROR! You have entered an incorrect username or password. Please try again.
      """
Feature: Car valuation Test


@test
    Scenario: Check car valuations
      Given A user opens url "https://www.webuyanycar.com/"
      And The user retrieves car reg numbers from "car_input.txt"
      When The user retrieves valuations from "car_output.txt"
      Then each reg should match the expected results
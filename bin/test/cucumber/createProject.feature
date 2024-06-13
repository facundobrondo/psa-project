Feature: create Project

  Scenario: Create and assign leader to a new project
    Given A new project will be started for product with code 14, named PSA_Project, with start date 2024-04-01 and end date 2024-06-30
    When I create the project and assign employee with code 55 as the leader
    Then The project should be created with leader code 55, product code 14, name PSA_Project, start date 2024-04-01, end date 2024-06-30 and status initiated

  Scenario: Create a new project without leader
      Given A new project will be started for product with code 7, named PSA_Idea, with start date 2025-05-15 and end date 2025-07-17
      When I create the project and do not assign a leader
      Then The project should be created without leader, product code 7, name PSA_Idea, start date 2025-05-15, end date 2025-07-17 and status initiated
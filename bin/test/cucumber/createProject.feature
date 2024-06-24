Feature: create Project

  Scenario: Create and assign leader to a new project
    Given A new project will be started for product with code 14, with employee 55 as leader, named PSA_Mailing, with status initiated, description Cloud based mailing system, start date 2024-04-01 and end date 2024-06-30
    When Attempting to create the project with the given data
    Then The project should be created with leader code 55, product code 14, name PSA_Mailing, status initiated, description Cloud based mailing system, start date 2024-04-01 and end date 2024-06-30
    
  Scenario: Create a new project with no assigned leader
    Given A new project will be started for product with code 14, named PSA_Mailing, with status initiated, description Cloud based mailing system, start date 2024-04-01, end date 2024-06-30 and no assigned leader
    When Attempting to create the project with the given data
    Then The project should be created without a leader, with product code 14, name PSA_Mailing, status initiated, description Cloud based mailing system, start date 2024-04-01 and end date 2024-06-30

  Scenario: Create a new project with no start date
    Given A new project will be started for product with code 14, with employee 55 as leader, named PSA_Mailing, with status initiated, description Cloud based mailing system, no start date and end date 2024-06-30
    When Attempting to create the project with the given data
    Then The project should be created with leader code 55, product code 14, name PSA_Mailing, status initiated, description Cloud based mailing system, no start date and end date 2024-06-30

  Scenario: Create a new project with no end date
    Given A new project will be started for product with code 14, with employee 55 as leader, named PSA_Mailing, with status initiated, description Cloud based mailing system, start date 2024-04-01 and no end date
    When Attempting to create the project with the given data
    Then The project should be created with leader code 55, product code 14, name PSA_Mailing, status initiated, description Cloud based mailing system, start date 2024-04-01 and no end date

  Scenario: Create a new project with no description
    Given A new project will be started for product with code 14, with employee 55 as leader, named PSA_Mailing, with status initiated, start date 2024-04-01, end date 2024-06-30 and no description
    When Attempting to create the project with the given data
    Then The project should be created with leader code 55, product code 14, name PSA_Mailing, status initiated, start date 2024-04-01, end date 2024-06-30 and no description

  Scenario: Create a new project with no specific status
    Given A new project will be started for product with code 14, with employee 55 as leader, named PSA_Mailing, description Cloud based mailing system, start date 2024-04-01, end date 2024-06-30 and no specific status
    When Attempting to create the project with the given data
    Then The project should be created with leader code 55, product code 14, name PSA_Mailing, status initiated, description Cloud based mailing system, start date 2024-04-01 and end date 2024-06-30

  Scenario: Create a project with no specific name
    Given A new project will be started for product with code 14, with employee 55 as leader, with status initiated, description Cloud based mailing system, start date 2024-04-01, end date 2024-06-30 but no specific name
    When Attempting to create the project with the given data
    Then Creation should be denied due to invalid project name

  Scenario: Create a project with end date prior to start date
    Given A new project will be started for product with code 14, with employee 55 as leader, named PSA_Mailing, with status initiated, description Cloud based mailing system, start date 2024-04-01 and end date 2024-02-26
    When Attempting to create the project with the given data
    Then Creation should be denied due to invalid project end date
    
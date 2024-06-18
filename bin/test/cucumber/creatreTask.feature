Feature: create Task

  Scenario: Create a task assigned to a Project
    Given An existing project PSA_Project, a task Task_Render will be crated with status new, description Render_distance, assigned to employee 31, priority low, start date 2024-04-01 and end date 2024-06-30
    When I create the new task with the given data
    Then The task is successfully created with name Task_Render, status new, description Render_distance, assigned to employee 31, priority low, start date 2024-04-01 and end date 2024-06-30

  Scenario: Create a task without a developer 
    Given An existing project PSA_Project, a task Task_Render will be crated with status new, description Render_distance, priority low, start date 2024-04-01 and end date 2024-06-30
    When I create the new task with the given data
    Then The task is successfully created with name Task_Render, status new, description Render_distance, priority low, start date 2024-04-01, end date 2024-06-30 and no assigned developer

  Scenario: Create a task with no description
    Given An existing project PSA_Project, a task Task_Render will be crated with status new, assigned to employee 31, priority low, start date 2024-04-01 and end date 2024-06-30
    When I create the new task with the given data
    Then The task is successfully created with name Task_Render, status new, assigned to employee 31, priority low, start date 2024-04-01, end date 2024-06-30 and no description

  Scenario: Create a task with no end date
    Given An existing project PSA_Project, a task Task_Render will be crated with status new, description Render_distance, assigned to employee 31, priority low, start date 2024-04-01 and no estimated end date
    When I create the new task with the given data
    Then The task is successfully created with name Task_Render, status new, description Render_distance, assigned to employee 31, priority low, start date 2024-04-01 and no estimated end date

  Scenario: Create a task with no name
    Given An existing project PSA_Project, a task will be crated with status new, description Render_distance, assigned to employee 31, priority low, start date 2024-04-01, end date 2024-06-30 but no specific name
    When I create the new task with the given data
    Then Creation should be denied due to invalid task name
  
  Scenario: Create a task assigned to suspended Project
    Given An existing project PSA_Project with status suspended, a task Task_Render will be crated with status new, description Render_distance, assigned to employee 31, priority low, start date 2024-04-01 and end date 2024-06-30
    When I create the new task with the given data
    Then Creation should be denied due to invalid project
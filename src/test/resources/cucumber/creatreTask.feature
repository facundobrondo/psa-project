Feature: create Task

  Scenario: Create a task assigned to a Project
    Given An existing project PSA_Mailing, a task Task_Receiver_Info will be crated with status new, description Show detailed info of the receiver, assigned to employee 31, priority low, start date 2024-04-01 and end date 2024-06-30
    When Attempting to create the task assigned to the project
    Then The task should be successfully created with name Task_Receiver_Info, status new, description Show detailed info of the receiver, assigned to employee 31, priority low, start date 2024-04-01 and end date 2024-06-30

  Scenario: Create a task without a developer 
    Given An existing project PSA_Mailing, a task Task_Receiver_Info will be crated with status new, description Show detailed info of the receiver, priority low, start date 2024-04-01, end date 2024-06-30 and no assigned developer
    When Attempting to create the task assigned to the project
    Then The task should be successfully created with name Task_Receiver_Info, status new, description Show detailed info of the receiver, priority low, start date 2024-04-01, end date 2024-06-30 and no assigned developer

  Scenario: Create a task with no description
    Given An existing project PSA_Mailing, a task Task_Receiver_Info will be crated with status new, assigned to employee 31, priority low, start date 2024-04-01, end date 2024-06-30 and no description
    When Attempting to create the task assigned to the project
    Then The task should be successfully created with name Task_Receiver_Info, status new, assigned to employee 31, priority low, start date 2024-04-01, end date 2024-06-30 and no description

  Scenario: Create a task with no start date
    Given An existing project PSA_Mailing, a task Task_Receiver_Info will be crated with status new, description Show detailed info of the receiver, assigned to employee 31, priority low, no start date and end date 2024-06-30
    When Attempting to create the task assigned to the project
    Then The task should be successfully created with name Task_Receiver_Info, status new, description Show detailed info of the receiver, assigned to employee 31, priority low, no start date and end date 2024-06-30

  Scenario: Create a task with no end date
    Given An existing project PSA_Mailing, a task Task_Receiver_Info will be crated with status new, description Show detailed info of the receiver, assigned to employee 31, priority low, start date 2024-04-01 and no end date
    When Attempting to create the task assigned to the project
    Then The task should be successfully created with name Task_Receiver_Info, status new, description Show detailed info of the receiver, assigned to employee 31, priority low, start date 2024-04-01 and no estimated end date

  Scenario: Create a task with no specific status
    Given An existing project PSA_Mailing, a task Task_Receiver_Info will be crated with no specific status, description Show detailed info of the receiver, assigned to employee 31, priority low, start date 2024-04-01 and end date 2024-06-30
    When Attempting to create the task assigned to the project
    Then The task should be successfully created with name Task_Receiver_Info, status new, description Show detailed info of the receiver, assigned to employee 31, priority low, start date 2024-04-01 and end date 2024-06-30

  Scenario: Create a task with no specific priority
    Given An existing project PSA_Mailing, a task Task_Receiver_Info will be crated with status new, description Show detailed info of the receiver, assigned to employee 31, start date 2024-04-01, end date 2024-06-30 and no specific priority
    When Attempting to create the task assigned to the project
    Then The task should be successfully created with name Task_Receiver_Info, status new, description Show detailed info of the receiver, assigned to employee 31, priority low, start date 2024-04-01 and end date 2024-06-30

  Scenario: Create a task with no name
    Given An existing project PSA_Mailing, a task will be crated with status new, description Show detailed info of the receiver, assigned to employee 31, priority low, start date 2024-04-01, end date 2024-06-30 but no specific name
    When Attempting to create the task assigned to the project
    Then Creation should be denied due to invalid task name

  Scenario: Create a task with end date prior to start date
    Given An existing project PSA_Mailing, a task Task_Receiver_Info will be crated with status new, description Show detailed info of the receiver, assigned to employee 31, priority low, start date 2024-04-01 and end date 2024-02-28
    When Attempting to create the task assigned to the project
    Then Creation should be denied due to invalid end date

  Scenario: Create a task assigned to suspended Project
    Given An existing project PSA_Mailing with status suspended, a task Task_Receiver_Info will be crated with status new, description Show detailed info of the receiver, assigned to employee 31, priority low, start date 2024-04-01 and end date 2024-06-30
    When Attempting to create the task assigned to the project
    Then Creation should be denied due to invalid project status

  Scenario: Create a task assigned to finished Project
    Given An existing project PSA_Mailing with status finished, a task Task_Receiver_Info will be crated with status new, description Show detailed info of the receiver, assigned to employee 31, priority low, start date 2024-04-01 and end date 2024-06-30
    When Attempting to create the task assigned to the project
    Then Creation should be denied due to invalid project status

  Scenario: Create a task with no existing projects
    Given No current created projects, a task Task_Receiver_Info will be crated
    When Attempting to create the task with no project code
    Then Creation should be denied because a task can't be created without projects
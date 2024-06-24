Feature: update Task

  Scenario: Update task with new title
    Given An existing task Task_Receiver_Info in project PSA_Mailing, with status new, description Show detailed info of the receiver, start date 2024-04-01 and end date 2024-06-30
    When Attempting to update the task title to Task_Receiver_Details
    Then The task should now be called Task_Receiver_Details

  Scenario: Update task with new status
    Given An existing task Task_Receiver_Info in project PSA_Mailing, with status new, description Show detailed info of the receiver, start date 2024-04-01 and end date 2024-06-30
    When Attempting to update the task status to closed
    Then The task should now have closed status

  Scenario: Update task with new description
    Given An existing task Task_Receiver_Info in project PSA_Mailing, with status new, description Show detailed info of the receiver, start date 2024-04-01 and end date 2024-06-30
    When Attempting to update the task description to Show detailed info of the receiver, must include code
    Then The task description should now be Show detailed info of the receiver, must include code

  Scenario: Update task with new end date
    Given An existing task Task_Receiver_Info in project PSA_Mailing, with status new, description Show detailed info of the receiver, start date 2024-04-01 and end date 2024-06-30
    When Attempting to update the task end date to 2024-05-12
    Then The task end date should now be 2024-05-12

  Scenario: Update task with start date posterior to start date
    Given An existing task Task_Receiver_Info in project PSA_Mailing, with status new, description Show detailed info of the receiver, start date 2024-04-01 and end date 2024-06-30
    When Attempting to update the task start date to 2024-07-05
    Then Update should be denied due to invalid start date

  Scenario: Update task with end date prior to start date
    Given An existing task Task_Receiver_Info in project PSA_Mailing, with status new, description Show detailed info of the receiver, start date 2024-04-01 and end date 2024-06-30
    When Attempting to update the task end date to 2024-03-25
    Then Update should be denied due to invalid end date

  Scenario: Assign developer
    Given An existing task Task_Receiver_Info in project PSA_Mailing, with status new, description Show detailed info of the receiver, start date 2024-04-01 and end date 2024-06-30
    When Attempting to assign employee 51 as developer
    Then The task developer should now be employee 51

  Scenario: Change developer
    Given An existing task Task_Receiver_Info in project PSA_Mailing, with status new, employee with code 55 as developer, description Show detailed info of the receiver, start date 2024-04-01 and end date 2024-06-30
    When Attempting to assign employee 51 as developer
    Then The task developer should now be employee 51

  Scenario: Update task with new developer on suspended project
    Given An existing task Task_Receiver_Info in suspended project PSA_Mailing, with status new, description Show detailed info of the receiver, start date 2024-04-01 and end date 2024-06-30
    When Attempting to update the task developer to employee 51
    Then Update should be denied due to invalid project status

  Scenario: Update task with new developer on finished project
    Given An existing task Task_Receiver_Info in finished project PSA_Mailing, with status new, description Show detailed info of the receiver, start date 2024-04-01 and end date 2024-06-30
    When Attempting to update the task developer to employee 51
    Then Update should be denied due to invalid project status
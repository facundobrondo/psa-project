Feature: delete Task

  Scenario: Delete an existing task
    Given An existing task Task_Receiver_Info in project PSA_Mailing, with status new, description Show detailed info of the receiver, start date 2024-04-01 and end date 2024-06-30
    When Attempting to delete the task
    Then The task should no longer exist
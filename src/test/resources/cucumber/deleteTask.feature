Feature: delete Task

  Scenario: Delete an existing task
    Given An existing task Task_Receiver_Info in project PSA_Mailing with status initiated
    When Attempting to delete the task
    Then The task should no longer exist
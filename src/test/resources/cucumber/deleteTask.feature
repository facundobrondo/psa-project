Feature: delete Task

  Scenario: Delete an existing task on initiated project
    Given An existing task Task_Receiver_Info in project PSA_Mailing with status initiated
    When Attempting to delete the task
    Then The task should no longer exist

  Scenario: Delete an existing task on suspended project
    Given An existing task Task_Receiver_Info in project PSA_Mailing with status suspended
    When Attempting to delete the task
    Then Deletion should be denied due to project being finished

  Scenario: Delete an existing task on finished project
    Given An existing task Task_Receiver_Info in project PSA_Mailing with status finished
    When Attempting to delete the task
    Then Deletion should be denied due to project being finished
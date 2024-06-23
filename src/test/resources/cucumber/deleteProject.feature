Feature: delete Project

  Scenario: Delete an existing project with its tasks
    Given An existing project PSA_Mailing with tasks Task_Receiver_Info and Task_Cloud_Clone
    When Attempting to delete the project with its tasks
    Then The project and both tasks should no longer exist

  Scenario: Delete an existing project with no tasks
    Given An existing project PSA_Mailing with no tasks
    When Attempting to delete the project
    Then The project should no longer exist
Feature: terminate Task

  Scenario: terminate a new task
    Given An existing task PSA_Mailing in project PSA_Main_Banking, with status new, description Single_Mailing_System, start date 2024-04-01 and end date 2024-06-30
    When I terminate the task
    Then The task should now have Locked status

  Scenario: terminate a task in progress
    Given An existing task PSA_Mailing in project PSA_Main_Banking, with status in_progress, description Single_Mailing_System, start date 2024-04-01 and end date 2024-06-30
    When I terminate the task
    Then The task should now have Locked status

  Scenario: terminate a locked task
    Given An existing task PSA_Mailing in project PSA_Main_Banking, with status locked, description Single_Mailing_System, start date 2024-04-01 and end date 2024-06-30
    When I terminate the task
    Then Termination should be denied due to task already locked

  Scenario: terminate a closed task
    Given An existing task PSA_Mailing in project PSA_Main_Banking, with status closed, description Single_Mailing_System, start date 2024-04-01 and end date 2024-06-30
    When I terminate the task
    Then Termination should be denied due to closed task
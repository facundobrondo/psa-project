Feature: update Project

  Scenario: Update task with new title
    Given An existing task PSA_Mailing in project PSA_Main_Banking, with status new, description Single_Mailing_System, start date 2024-04-01 and end date 2024-06-30
    When I update the task title to PSA_MultiMailing
    Then The task should now be called PSA_MultiMailing

  Scenario: Update task with new status
    Given An existing task PSA_Mailing in project PSA_Main_Banking, with status new, description Single_Mailing_System, start date 2024-04-01 and end date 2024-06-30
    When I update the task status to closed
    Then The task should now have closed status

  Scenario: Update task with new description
    Given An existing task PSA_Mailing in project PSA_Main_Banking, with status new, description Single_Mailing_System, start date 2024-04-01 and end date 2024-06-30
    When I update the task description to Single_Mailing_App
    Then The task description should now be Single_Mailing_App

  Scenario: Update task with new end date
    Given An existing task PSA_Mailing in project PSA_Main_Banking, with status new, description Single_Mailing_System, start date 2024-04-01 and end date 2024-06-30
    When I update the task end date to 2024-05-12
    Then The task end date should now be 2024-05-12

  Scenario: Update task with new developer
    Given An existing task PSA_Mailing in project PSA_Main_Banking, with status new, description Single_Mailing_System, start date 2024-04-01 and end date 2024-06-30
    When I assign employee 51 as developer
    Then The task developer should now be employee 51
Feature: update Project

  Scenario: Update project with new title
    Given An existing project PSA_Mailing with employee 55 as leader, with status initiated, description Cloud based mailing system, start date 2024-04-01 and end date 2024-06-30
    When Attempting to update the title to PSA_Cloud_Mailing
    Then The project should now be called PSA_Cloud_Mailing

  Scenario: Update project with new status
    Given An existing project PSA_Mailing with employee 55 as leader, with status initiated, description Cloud based mailing system, start date 2024-04-01 and end date 2024-06-30
    When Attempting to update the project status to finished
    Then The project should now have finished as status

  Scenario: Assign leader
    Given An existing project PSA_Mailing with no leader, with status initiated, description Cloud based mailing system, start date 2024-04-01 and end date 2024-06-30
    When Attempting to assign eployee 55 as the leader
    Then The project should now have employee 55 as leader

  Scenario: Update project with new leader
    Given An existing project PSA_Mailing with employee 55 as leader, with status initiated, description Cloud based mailing system, start date 2024-04-01 and end date 2024-06-30
    When Attempting to assign eployee 52 as the leader
    Then The project should now have employee 52 as leader
  
  Scenario: Update project with new description
    Given An existing project PSA_Mailing with employee 55 as leader, with status initiated, description Cloud based mailing system, start date 2024-04-01 and end date 2024-06-30
    When Attempting to update the project description to Sistema de envio de mails basado en la nube
    Then The project should now have Sistema de envio de mails basado en la nube as description

  Scenario: Update project with new end date
    Given An existing project PSA_Mailing with employee 55 as leader, with status initiated, description Cloud based mailing system, start date 2024-04-01 and end date 2024-06-30
    When Attempting to update the end date to 2024-10-31
    Then The end date should now be 2024-10-31

  Scenario: Update project with end date prior to start date
    Given An existing project PSA_Mailing with employee 55 as leader, with status initiated, description Cloud based mailing system, start date 2024-04-01 and end date 2024-06-30
    When Attempting to update the end date to 2024-02-25
    Then Update should be denied due to invalid project end date
    
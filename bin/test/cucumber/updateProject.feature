Feature: update Project

  Scenario: Update project with new title
    Given An existing project PSA_Mailing with employee 55 as leader, with status initiated, description Arquitecture_Project, start date 2024-04-01 and end date 2024-06-30
    When I update the title to PSA_Banking
    Then The project should now be called PSA_Banking

  Scenario: Update project with new status
    Given An existing project PSA_Mailing with employee 55 as leader, with status initiated, description Arquitecture_Project, start date 2024-04-01 and end date 2024-06-30
    When I update the project status to finished
    Then The project should now have finished as status

  Scenario: Update project with new leader
    Given An existing project PSA_Mailing with no leader, with status initiated, description Arquitecture_Project, start date 2024-04-01 and end date 2024-06-30
    When I assign eployee 55 as the leader
    Then The project should now have employee 55 as leader
  
  Scenario: Update project with new description
    Given An existing project PSA_Mailing with employee 55 as leader, with status initiated, description Arquitecture_Project, start date 2024-04-01 and end date 2024-06-30
    When I update the project description to Accomodations_Register
    Then The project should now have Accomodations_Register as description

  Scenario: Update project with new description
    Given An existing project PSA_Mailing with employee 55 as leader, with status initiated, description Arquitecture_Project, start date 2024-04-01 and end date 2024-06-30
    When I update the end date to 2024-10-31
    Then The end date should now be 2024-10-31
    
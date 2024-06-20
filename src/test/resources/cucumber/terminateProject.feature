Feature: terminate Project

  Scenario: terminate an initiated project
    Given An existing project PSA_Roots with no leader, with status initiated, description Roots_archive, start date 2024-04-01 and end date 2024-06-30
    When I terminate the project
    Then The project should now have suspended as status

  Scenario: terminate a suspended project
    Given An existing project PSA_Roots with no leader, with status suspended, description Roots_archive, start date 2024-04-01 and end date 2024-06-30
    When I terminate the project
    Then Termination should be denied due to project already terminated

  Scenario: terminate a finished project
    Given An existing project PSA_Roots with no leader, with status finished, description Roots_archive, start date 2024-04-01 and end date 2024-06-30
    When I terminate the project
    Then Termination should be denied due to finished project
Feature: terminate Project

  Scenario: terminate an initiated project
    Given An existing project PSA_Roots with no leader, with status initiated, description Base model architecture, start date 2024-04-01 and end date 2024-06-30
    When Attempting to terminate the project
    Then The project should now have suspended as status

  Scenario: terminate a suspended project
    Given An existing project PSA_Roots with no leader, with status suspended, description Base model architecture, start date 2024-04-01 and end date 2024-06-30
    When Attempting to terminate the project
    Then Termination should be denied due to status already being suspended

  Scenario: terminate a finished project
    Given An existing project PSA_Roots with no leader, with status finished, description Base model architecture, start date 2024-04-01 and end date 2024-06-30
    When Attempting to terminate the project
    Then Termination should be denied due to project being finished
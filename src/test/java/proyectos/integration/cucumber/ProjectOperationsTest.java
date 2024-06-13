package proyectos.integration.cucumber;

import proyectos.model.Project;
import proyectos.model.StatusProject;
import proyectos.service.ProjectService;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

public class ProjectOperationsTest extends ProjectIntegrationServiceTest {

    private Project project;

    private Long productCode;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @Given("^A new project will be started for product with code (\\d+), named (\\w+), with start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void a_new_project_will_be_started(Long productCode, String name, String startDate, String endDate) {
        this.productCode = productCode;
        this.name = name;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @When("^I create the project and assign employee with code (\\d+) as the leader$")
    public void i_create_the_project_and_assign_employee_as_the_leader(Long leaderCode) {
        this.project = createProject(leaderCode, productCode, name, startDate, endDate);
    }

    @When("^I create the project and do not assign a leader$")
    public void i_create_the_project_and_do_not_assign_a_leader() {
        this.project = createProject(productCode, name, startDate, endDate);
    }

    @Then("^The project should be created with leader code (\\d+), product code (\\d+), name (\\w+), start date (\\d{4}-\\d{2}-\\d{2}), end date (\\d{4}-\\d{2}-\\d{2}) and status (\\w+)$")
    public void the_project_should_be_created_with_leader(Long leaderCode, Long productCode, String name, String startDate, String endDate, String status) {
        assertNotNull(project);
        assertEquals(leaderCode, project.getLeaderCode());
        assertEquals(productCode, project.getProductCode());
        assertEquals(name, project.getName());
        assertEquals(LocalDate.parse(startDate), project.getStartDate());
        assertEquals(LocalDate.parse(endDate), project.getEndDate());
        assertEquals(StatusProject.valueOf(status.toUpperCase()), project.getStatus());
    }

    @Then("^The project should be created without leader, product code (\\d+), name (\\w+), start date (\\d{4}-\\d{2}-\\d{2}), end date (\\d{4}-\\d{2}-\\d{2}) and status (\\w+)$")
    public void the_project_should_be_created_without_leader(Long productCode, String name, String startDate, String endDate, String status) {
        assertNotNull(project);
        assertNull(project.getLeaderCode());
        assertEquals(productCode, project.getProductCode());
        assertEquals(name, project.getName());
        assertEquals(LocalDate.parse(startDate), project.getStartDate());
        assertEquals(LocalDate.parse(endDate), project.getEndDate());
        assertEquals(StatusProject.valueOf(status.toUpperCase()), project.getStatus());
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}

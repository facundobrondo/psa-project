package proyectos.integration.cucumber;

import proyectos.model.Project;
import proyectos.model.ProjectStatus;
import proyectos.exceptions.*;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import javax.naming.InvalidNameException;

public class ProjectOperationsTest extends ProjectIntegrationServiceTest {

    private Project project;

    private Long productCode;
    private Long leaderCode;
    private String name;
    private String status;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    private InvalidNameException invalid_name;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @Given("^A new project will be started for product (\\d+), with employee (\\d+) as developer, named (\\w+), with status (\\w+), description (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void a_new_project_will_be_started(Long productCode, Long employeeCode, String name, String status, String description, String startDate, String endDate) {
        this.productCode = productCode;
        this.leaderCode = employeeCode;
        this.name = name;
        this.status = status;
        this.description = description;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Given("^A new project will be started for product with code (\\d+), named (\\w+), with status (\\w+), description (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void a_new_project_will_be_started_with_no_leader(Long productCode, String name, String status, String description, String startDate, String endDate) {
        this.productCode = productCode;
        this.name = name;
        this.status = status;
        this.description = description;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Given("^A new project will be started for product (\\d+), with employee (\\d+) as developer, named (\\w+), with status (\\w+), description (\\w+) and start date (\\d{4}-\\d{2}-\\d{2})$")
    public void a_new_project_will_be_started_with_no_end_date(Long productCode, Long employeeCode, String name, String status, String description, String startDate) {
        this.productCode = productCode;
        this.leaderCode = employeeCode;
        this.name = name;
        this.status = status;
        this.description = description;
        this.startDate = LocalDate.parse(startDate);
    }

    @Given("^A new project will be started for product (\\d+), with employee (\\d+) as developer, named (\\w+), with status (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void a_new_project_will_be_started_with_no_description(Long productCode, Long employeeCode, String name, String status, String startDate, String endDate) {
        this.productCode = productCode;
        this.leaderCode = employeeCode;
        this.name = name;
        this.status = status;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Given("^A new project will be started for product (\\d+), with employee (\\d+) as developer, named (\\w+), description (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void a_new_project_will_be_started_with_no_specific_status(Long productCode, Long employeeCode, String name, String description, String startDate, String endDate) {
        this.productCode = productCode;
        this.leaderCode = employeeCode;
        this.name = name;
        this.description = description;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @When("^I create the project with said data$")
    public void i_create_the_project_and_assign_employee_as_the_leader() {
        this.project = createProject(leaderCode, productCode, name, status, description, startDate, endDate);
    }

    @When("^I create the project without assigning a leader$")
    public void i_create_the_project_without_assigning_a_leader() {
        this.project = createProject(leaderCode, productCode, name, status, description, startDate, endDate);
    }

    @When("^I create the project without stating an end date$")
    public void i_create_the_project_without_stating_end_date() {
        this.project = createProject(leaderCode, productCode, name, status, description, startDate, endDate);
    }

    @When("^I create the project without stating a description$")
    public void i_create_the_project_without_stating_a_description() {
        this.project = createProject(leaderCode, productCode, name, status, description, startDate, endDate);
    }

    @When("^I create the project with no specific status$")
    public void i_create_the_project_with_no_specific_state() {
        this.project = createProject(leaderCode, productCode, name, status, description, startDate, endDate);
    }

    @Then("^The project should be created with leader code (\\d+), product code (\\d+), name (\\w+), status (\\w+), description (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void the_project_should_be_created_with_all_data(Long leaderCode, Long productCode, String name, String status, String description, String startDate, String endDate) {
        assertNotNull(project);
        assertEquals(leaderCode, project.getLeaderCode());
        assertEquals(productCode, project.getProductCode());
        assertEquals(name, project.getName());
        assertEquals(description, project.getDescription());
        assertEquals(LocalDate.parse(startDate), project.getStartDate());
        assertEquals(LocalDate.parse(endDate), project.getEndDate());
        assertEquals(ProjectStatus.valueOf(status.toUpperCase()), project.getStatus());
    }

    @Then("^The project should be created without a leader, with product code (\\d+), name (\\w+), status (\\w+), description (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void the_project_should_be_created_without_leader(Long productCode, String name, String status, String description, String startDate, String endDate) {
        assertNotNull(project);
        assertNull(project.getLeaderCode());
        assertEquals(productCode, project.getProductCode());
        assertEquals(name, project.getName());
        assertEquals(description, project.getDescription());
        assertEquals(LocalDate.parse(startDate), project.getStartDate());
        assertEquals(LocalDate.parse(endDate), project.getEndDate());
        assertEquals(ProjectStatus.valueOf(status.toUpperCase()), project.getStatus());
    }

    @Then("^The project should be created with leader code (\\d+), product code (\\d+), name (\\w+), status (\\w+), description (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and no end date$")
    public void the_project_should_be_created_with_no_end_date(Long leaderCode, Long productCode, String name, String status, String description, String startDate) {
        assertNotNull(project);
        assertEquals(leaderCode, project.getLeaderCode());
        assertEquals(productCode, project.getProductCode());
        assertEquals(name, project.getName());
        assertEquals(description, project.getDescription());
        assertEquals(LocalDate.parse(startDate), project.getStartDate());
        assertNull(project.getEndDate());
        assertEquals(ProjectStatus.valueOf(status.toUpperCase()), project.getStatus());
    }

    @Then("^The project should be created with leader code (\\d+), product code (\\d+), name (\\w+), status (\\w+), start date (\\d{4}-\\d{2}-\\d{2}), end date (\\d{4}-\\d{2}-\\d{2}) and no description$")
    public void the_project_should_be_created_with_no_description(Long leaderCode, Long productCode, String name, String status, String startDate, String endDate) {
        assertNotNull(project);
        assertEquals(leaderCode, project.getLeaderCode());
        assertEquals(productCode, project.getProductCode());
        assertEquals(name, project.getName());
        assertNull(project.getDescription());
        assertEquals(LocalDate.parse(startDate), project.getStartDate());
        assertEquals(LocalDate.parse(endDate), project.getEndDate());
        assertEquals(ProjectStatus.valueOf(status.toUpperCase()), project.getStatus());
    }
    
    @Then("^The project should be created with status (\\w+)$")
    public void the_project_should_be_created_with_status_initiated(String status) {
        assertEquals(ProjectStatus.valueOf(status.toUpperCase()), project.getStatus());
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}

package proyectos.integration.cucumber;

import proyectos.model.Project;
import proyectos.model.ProjectStatus;
import proyectos.ProjectsApp;
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

import org.springframework.beans.factory.annotation.Autowired;

public class ProjectOperationsTest extends ProjectIntegrationServiceTest {

    private Project project;
    private Project updatedProject;

    private Long productCode;
    private Long leaderCode;
    private String name;
    private String status;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    private Exception nsn;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @Given("^A new project will be started for product with code (\\d+), with employee (\\d+) as leader, named (\\w+), with status (\\w+), description (.+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void a_new_project_will_be_started(Long productCode, Long employeeCode, String name, String status, String description, String startDate, String endDate) {
        this.productCode = productCode;
        this.leaderCode = employeeCode;
        this.name = name;
        this.status = status;
        this.description = description;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Given("^A new project will be started for product with code (\\d+), named (\\w+), with status (\\w+), description (.+), start date (\\d{4}-\\d{2}-\\d{2}), end date (\\d{4}-\\d{2}-\\d{2}) and no assigned leader$")
    public void a_new_project_will_be_started_with_no_leader(Long productCode, String name, String status, String description, String startDate, String endDate) {
        this.productCode = productCode;
        this.name = name;
        this.status = status;
        this.description = description;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Given("^A new project will be started for product with code (\\d+), with employee (\\d+) as leader, named (\\w+), with status (\\w+), description (.+), start date (\\d{4}-\\d{2}-\\d{2}) and no end date$")
    public void a_new_project_will_be_started_with_no_end_date(Long productCode, Long employeeCode, String name, String status, String description, String startDate) {
        this.productCode = productCode;
        this.leaderCode = employeeCode;
        this.name = name;
        this.status = status;
        this.description = description;
        this.startDate = LocalDate.parse(startDate);
    }

    @Given("^A new project will be started for product with code (\\d+), with employee (\\d+) as leader, named (\\w+), with status (\\w+), start date (\\d{4}-\\d{2}-\\d{2}), end date (\\d{4}-\\d{2}-\\d{2}) and no description$")
    public void a_new_project_will_be_started_with_no_description(Long productCode, Long employeeCode, String name, String status, String startDate, String endDate) {
        this.productCode = productCode;
        this.leaderCode = employeeCode;
        this.name = name;
        this.status = status;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Given("^A new project will be started for product with code (\\d+), with employee (\\d+) as leader, named (\\w+), description (.+), start date (\\d{4}-\\d{2}-\\d{2}), end date (\\d{4}-\\d{2}-\\d{2}) and no specific status$")
    public void a_new_project_will_be_started_with_no_specific_status(Long productCode, Long employeeCode, String name, String description, String startDate, String endDate) {
        this.productCode = productCode;
        this.leaderCode = employeeCode;
        this.name = name;
        this.description = description;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Given("^A new project will be started for product with code (\\d+), with employee (\\d+) as leader, with status (\\w+), description (.+), start date (\\d{4}-\\d{2}-\\d{2}), end date (\\d{4}-\\d{2}-\\d{2}) but no specific name$")
    public void a_new_project_will_be_started(Long productCode, Long employeeCode, String status, String description, String startDate, String endDate) {
        this.productCode = productCode;
        this.leaderCode = employeeCode;
        this.status = status;
        this.description = description;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Given("^An existing project (\\w+) with employee (\\d+) as leader, with status (\\w+), description (.+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void an_existing_project_will_be_updated_with_a_new_name(String name, Long employeeCode, String status, String description, String recievedStartDate, String recievedEndDate) {
        this.leaderCode = employeeCode;
        this.name = name;
        this.description = description;
        this.startDate = LocalDate.parse(recievedStartDate);
        this.endDate = LocalDate.parse(recievedEndDate);

        this.project = createProject(leaderCode, productCode, name, status, description, startDate, endDate);
    }

    @Given("^An existing project (\\w+) with no leader, with status (\\w+), description (.+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void an_existing_project_will_be_updated_a_leader(String name, String status, String description, String recievedStartDate, String recievedEndDate) {
        this.name = name;
        this.description = description;
        this.startDate = LocalDate.parse(recievedStartDate);
        this.endDate = LocalDate.parse(recievedEndDate);

        this.project = createProject(leaderCode, productCode, name, status, description, startDate, endDate);
    }

    // @When("^I create the project with said data$")
    // public void i_create_the_project_and_assign_employee_as_the_leader() {
    //     this.project = createProject(leaderCode, productCode, name, status, description, startDate, endDate);
    // }

    // @When("^I create the project without assigning a leader$")
    // public void i_create_the_project_without_assigning_a_leader() {
    //     this.project = createProject(leaderCode, productCode, name, status, description, startDate, endDate);
    // }

    // @When("^I create the project without stating an end date$")
    // public void i_create_the_project_without_stating_end_date() {
    //     this.project = createProject(leaderCode, productCode, name, status, description, startDate, endDate);
    // }

    // @When("^I create the project without stating a description$")
    // public void i_create_the_project_without_stating_a_description() {
    //     this.project = createProject(leaderCode, productCode, name, status, description, startDate, endDate);
    // }

    // @When("^I create the project with no specific status$")
    // public void i_create_the_project_with_no_specific_state() {
    //     this.project = createProject(leaderCode, productCode, name, status, description, startDate, endDate);
    // }

    @When("^Attempting to create the project with the given data$")
    public void i_create_the_task_assigned_to_the_project() {
        try {
            this.project = createProject(leaderCode, productCode, name, status, description, startDate, endDate);
        } catch (Exception nsn){
            this.nsn = nsn;
        };   
    }

    @When("^Attempting to update the title to (\\w+)$")
    public void i_udpate_the_project_with_a_new_name(String name) {
        project = updateProject(project.getProjectCode(), new Project(null, null, name, null, null, null, null));
    }

    @When("^Attempting to update the project status to (\\w+)$")
    public void i_udpate_the_project_with_a_new_status(String status) {
        project = updateProject(project.getProjectCode(), new Project(null, null, null, status, null, null, null));
    }

    @When("^Attempting to assign eployee (\\d+) as the leader$")
    public void i_udpate_the_project_with_a_leader(Long employeeCode) {
        project = updateProject(project.getProjectCode(), new Project(employeeCode, null, null, null, null, null, null));
    }

    @When("^Attempting to update the project description to (.+)$")
    public void i_udpate_the_project_with_a_new_description(String description) {
        project = updateProject(project.getProjectCode(), new Project(null, null, null, null, description, null, null));
    }

    @When("^Attempting to update the end date to (\\d{4}-\\d{2}-\\d{2})$")
    public void i_udpate_the_project_with_a_new_end_date(String endDate) {
        try {
            project = updateProject(project.getProjectCode(), new Project(null, null, null, null, null, null, LocalDate.parse(endDate)));
        } catch (Exception nsn){
            this.nsn = nsn;
        };
    }

    @When("^Attempting to terminate the project$")
    public void i_terminate_the_project() {
        try {
            project = terminateProject(project.getProjectCode());
        } catch (Exception nsn){
            this.nsn = nsn;
        };   
    }

    @Then("^The project should be created with leader code (\\d+), product code (\\d+), name (\\w+), status (\\w+), description (.+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
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

    @Then("^The project should be created without a leader, with product code (\\d+), name (\\w+), status (\\w+), description (.+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
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

    @Then("^The project should be created with leader code (\\d+), product code (\\d+), name (\\w+), status (\\w+), description (.+), start date (\\d{4}-\\d{2}-\\d{2}) and no end date$")
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

    @Then("^Creation should be denied due to invalid project name$")
    public void the_project_cant_be_created_with_no_name() {
        assertNotNull(nsn);
    }

    @Then("^Creation should be denied due to invalid project end date$")
    public void the_project_cant_be_created_with_invalid_end_date() {
        assertNotNull(nsn);
    }

    @Then("^The project should now be called (\\w+)$")
    public void the_project_should_now_be_called(String name) {
        assertEquals(name, project.getName());
    }

    @Then("^The project should now have (\\w+) as status$")
    public void the_project_should_now_have_finished_status(String status) {
        assertEquals(ProjectStatus.valueOf(status.toUpperCase()), project.getStatus());
    }

    @Then("^The project should now have employee (\\d+) as leader$")
    public void the_project_should_now_have_finished_status(Long leaderCode) {
        assertEquals(leaderCode, project.getLeaderCode());
    }

    @Then("^The project should now have (.+) as description$")
    public void the_project_should_now_have_new_description(String description) {
        assertEquals(description, project.getDescription());
    }

    @Then("^The end date should now be (\\d{4}-\\d{2}-\\d{2})$")
    public void the_project_should_now_have_new_end_date(String endDate) {
        assertEquals(LocalDate.parse(endDate), project.getEndDate());
    }

    @Then("^Update should be denied due to invalid project end date$")
    public void the_project_cant_be_updated_with_invalid_end_date() {
        assertNotNull(nsn);
    }

    @Then("^Termination should be denied due to status already being suspended$")
    public void suspended_project_cant_be_terminated() {
        assertNotNull(nsn);
    }

    @Then("^Termination should be denied due to project being finished$")
    public void finished_project_cant_be_terminated() {
        assertNotNull(nsn);
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}

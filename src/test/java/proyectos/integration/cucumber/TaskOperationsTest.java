package proyectos.integration.cucumber;

import proyectos.model.Task;
import proyectos.model.TaskStatus;
import proyectos.model.TaskPriority;
import proyectos.ProjectsApp;
import proyectos.model.Project;
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

import org.springframework.beans.factory.annotation.Autowired;

public class TaskOperationsTest extends TaskIntegrationServiceTest {

    private Task task;
    private Project project;

    private String name;
    private String status;
    private String description;
    private Long employeeCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private String priority;

    private Exception nsn;

    @Autowired
    private ProjectsApp repository;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @Given("^An existing project (\\w+), a task (\\w+) will be crated with status (\\w+), description (\\w+), assigned to employee (\\d+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void a_new_task_will_be_created(String projectName, String name, String status, String description, Long employeeCode, String priority, String startDate, String endDate) {
        
        this.project = new Project(null, null, projectName, null, null, null, null);
        this.repository.createProject(project);

        this.name = name;
        this.status = status;
        this.description = description;
        this.employeeCode = employeeCode;
        this.priority = priority;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Given("^An existing project (\\w+), a task (\\w+) will be crated with status (\\w+), description (\\w+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void a_new_task_will_be_created_with_no_developer(String projectName, String name, String status, String description, String priority, String startDate, String endDate) {
        
        this.project = new Project(null, null, projectName, null, null, null, null);
        this.repository.createProject(project);

        this.name = name;
        this.status = status;
        this.description = description;
        this.priority = priority;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Given("^An existing project (\\w+), a task (\\w+) will be crated with status (\\w+), assigned to employee (\\d+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void a_new_task_will_be_created_with_no_description(String projectName, String name, String status, Long employeeCode, String priority, String startDate, String endDate) {
        
        this.project = new Project(null, null, projectName, null, null, null, null);
        this.repository.createProject(project);

        this.name = name;
        this.status = status;
        this.employeeCode = employeeCode;
        this.priority = priority;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Given("^An existing project (\\w+), a task (\\w+) will be crated with status (\\w+), description (\\w+), assigned to employee (\\d+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and no estimated end date$")
    public void a_new_task_will_be_created_with_no_end_date(String projectName, String name, String status, String description, Long employeeCode, String priority, String startDate) {
        
        this.project = new Project(null, null, projectName, null, null, null, null);
        this.repository.createProject(project);

        this.name = name;
        this.status = status;
        this.description = description;
        this.employeeCode = employeeCode;
        this.priority = priority;
        this.startDate = LocalDate.parse(startDate);
    }

    @Given("^An existing project (\\w+), a task will be crated with status (\\w+), description (\\w+), assigned to employee (\\d+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}), end date (\\d{4}-\\d{2}-\\d{2}) but no specific name$")
    public void a_new_task_will_be_created_with_no_name(String projectName, String status, String description, Long employeeCode, String priority, String startDate, String endDate) {
        
        this.project = new Project(null, null, projectName, null, null, null, null);
        this.repository.createProject(project);

        this.status = status;
        this.description = description;
        this.employeeCode = employeeCode;
        this.priority = priority;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Given("^An existing project (\\w+) with status (\\w+), a task (\\w+) will be crated with status (\\w+), description (\\w+), assigned to employee (\\d+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void a_new_task_will_be_created(String projectName, String projectStatus, String name, String status, String description, Long employeeCode, String priority, String startDate, String endDate) {
        
        this.project = new Project(null, null, projectName, projectStatus, null, null, null);
        this.repository.createProject(project);

        this.name = name;
        this.status = status;
        this.description = description;
        this.employeeCode = employeeCode;
        this.priority = priority;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @When("^I create the new task with the given data$")
    public void i_create_the_task_assigned_to_the_project() {
        try {
            this.task = createTask(project.getProjectCode(), name, status, description, employeeCode, priority, startDate, endDate);
        } catch (Exception nsn){
            this.nsn = nsn;
        };

        try {
            this.repository.createTask(project.getProjectCode(), task);
        } catch (Exception nsn){
            this.nsn = nsn;
        };        
    }

    @Then("^The task is successfully created with name (\\w+), status (\\w+), description (\\w+), assigned to employee (\\d+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void the_task_should_be_created_with_all_data(String name, String status, String description, Long employeeCode, String priority, String startDate, String endDate) {
        assertNotNull(task);
        assertNotNull(project);
        assertNull(nsn);
        assertEquals(name, task.getName());
        assertEquals(TaskStatus.valueOf(status.toUpperCase()), task.getStatus());
        assertEquals(description, task.getDescription());
        assertEquals(employeeCode, task.getEmployeeCode());
        assertEquals(TaskPriority.valueOf(priority.toUpperCase()), task.getPriority());
        assertEquals(LocalDate.parse(startDate), task.getStartDate());
        assertEquals(LocalDate.parse(endDate), task.getEndDate());
        assertEquals(project.getProjectCode(), task.getProjectCode());
    }

    @Then("^The task is successfully created with name (\\w+), status (\\w+), description (\\w+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}), end date (\\d{4}-\\d{2}-\\d{2}) and no assigned developer$")
    public void the_task_should_be_created_without_a_developer(String name, String status, String description, String priority, String startDate, String endDate) {
        assertNotNull(task);
        assertNotNull(project);
        assertNull(nsn);
        assertEquals(name, task.getName());
        assertEquals(TaskStatus.valueOf(status.toUpperCase()), task.getStatus());
        assertEquals(description, task.getDescription());
        assertNull(task.getEmployeeCode());
        assertEquals(TaskPriority.valueOf(priority.toUpperCase()), task.getPriority());
        assertEquals(LocalDate.parse(startDate), task.getStartDate());
        assertEquals(LocalDate.parse(endDate), task.getEndDate());
        assertEquals(project.getProjectCode(), task.getProjectCode());
    }

    @Then("^The task is successfully created with name (\\w+), status (\\w+), assigned to employee (\\d+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}), end date (\\d{4}-\\d{2}-\\d{2}) and no description$")
    public void the_task_should_be_created_with_no_description(String name, String status, Long employeeCode, String priority, String startDate, String endDate) {
        assertNotNull(task);
        assertNotNull(project);
        assertNull(nsn);
        assertEquals(name, task.getName());
        assertEquals(TaskStatus.valueOf(status.toUpperCase()), task.getStatus());
        assertNull(task.getDescription());
        assertEquals(employeeCode, task.getEmployeeCode());
        assertEquals(TaskPriority.valueOf(priority.toUpperCase()), task.getPriority());
        assertEquals(LocalDate.parse(startDate), task.getStartDate());
        assertEquals(LocalDate.parse(endDate), task.getEndDate());
        assertEquals(project.getProjectCode(), task.getProjectCode());
    }

    @Then("^The task is successfully created with name (\\w+), status (\\w+), description (\\w+), assigned to employee (\\d+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and no estimated end date$")
    public void the_task_should_be_created_with_no_end_date(String name, String status, String description, Long employeeCode, String priority, String startDate) {
        assertNotNull(task);
        assertNotNull(project);
        assertNull(nsn);
        assertEquals(name, task.getName());
        assertEquals(TaskStatus.valueOf(status.toUpperCase()), task.getStatus());
        assertEquals(description, task.getDescription());
        assertEquals(employeeCode, task.getEmployeeCode());
        assertEquals(TaskPriority.valueOf(priority.toUpperCase()), task.getPriority());
        assertEquals(LocalDate.parse(startDate), task.getStartDate());
        assertNull(task.getEndDate());
        assertEquals(project.getProjectCode(), task.getProjectCode());
    }

    @Then("^Creation should be denied due to invalid task name$")
    public void the_task_cant_be_created_with_no_name() {
        assertNotNull(nsn);
    }

    @Then("^Creation should be denied due to invalid project$")
    public void the_task_cant_be_created_with_invalid_project() {
        assertNotNull(nsn);
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}

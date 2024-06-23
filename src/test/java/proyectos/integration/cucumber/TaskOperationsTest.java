package proyectos.integration.cucumber;

import proyectos.model.Task;
import proyectos.model.TaskStatus;
import proyectos.model.TaskPriority;
import proyectos.ProjectsApp;
import proyectos.model.Project;
import proyectos.service.ProjectService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TaskOperationsTest extends TaskIntegrationServiceTest {

    private Task task;
    private Project project;

    private String taskName;
    private String projectName;
    private String status;
    private String description;
    private Long employeeCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private String priority;

    private Exception nsn;
    private Long deletedTaskCode;

    @Autowired
    private ProjectsApp repository;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @Given("^An existing project (\\w+), a task (\\w+) will be crated with status (\\w+), description (.+), assigned to employee (\\d+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void a_new_task_will_be_created(String projectName, String name, String status, String description, Long employeeCode, String priority, String startDate, String endDate) {
        
        this.project = new Project(null, null, projectName, null, null, null, null);
        this.repository.createProject(project);

        this.taskName = name;
        this.status = status;
        this.description = description;
        this.employeeCode = employeeCode;
        this.priority = priority;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Given("^An existing project (\\w+), a task (\\w+) will be crated with status (\\w+), description (.+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}), end date (\\d{4}-\\d{2}-\\d{2}) and no assigned developer$")
    public void a_new_task_will_be_created_with_no_developer(String projectName, String name, String status, String description, String priority, String startDate, String endDate) {
        
        this.project = new Project(null, null, projectName, null, null, null, null);
        this.repository.createProject(project);

        this.taskName = name;
        this.status = status;
        this.description = description;
        this.priority = priority;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Given("^An existing project (\\w+), a task (\\w+) will be crated with status (\\w+), assigned to employee (\\d+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}), end date (\\d{4}-\\d{2}-\\d{2}) and no description$")
    public void a_new_task_will_be_created_with_no_description(String projectName, String name, String status, Long employeeCode, String priority, String startDate, String endDate) {
        
        this.project = new Project(null, null, projectName, null, null, null, null);
        this.repository.createProject(project);

        this.taskName = name;
        this.status = status;
        this.employeeCode = employeeCode;
        this.priority = priority;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Given("^An existing project (\\w+), a task (\\w+) will be crated with status (\\w+), description (.+), assigned to employee (\\d+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and no end date$")
    public void a_new_task_will_be_created_with_no_end_date(String projectName, String name, String status, String description, Long employeeCode, String priority, String startDate) {
        
        this.project = new Project(null, null, projectName, null, null, null, null);
        this.repository.createProject(project);

        this.taskName = name;
        this.status = status;
        this.description = description;
        this.employeeCode = employeeCode;
        this.priority = priority;
        this.startDate = LocalDate.parse(startDate);
    }

    @Given("^An existing project (\\w+), a task (\\w+) will be crated with no specific status, description (.+), assigned to employee (\\d+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void a_new_task_will_be_created_with_no_status(String projectName, String name, String description, Long employeeCode, String priority, String startDate, String endDate) {
        
        this.project = new Project(null, null, projectName, null, null, null, null);
        this.repository.createProject(project);

        this.taskName = name;
        this.description = description;
        this.employeeCode = employeeCode;
        this.priority = priority;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Given("^An existing project (\\w+), a task (\\w+) will be crated with status (\\w+), description (.+), assigned to employee (\\d+), start date (\\d{4}-\\d{2}-\\d{2}), end date (\\d{4}-\\d{2}-\\d{2}) and no specific priority$")
    public void a_new_task_will_be_created_with_no_priority(String projectName, String name, String status, String description, Long employeeCode, String startDate, String endDate) {
        
        this.project = new Project(null, null, projectName, null, null, null, null);
        this.repository.createProject(project);

        this.taskName = name;
        this.status = status;
        this.description = description;
        this.employeeCode = employeeCode;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Given("^An existing project (\\w+), a task will be crated with status (\\w+), description (.+), assigned to employee (\\d+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}), end date (\\d{4}-\\d{2}-\\d{2}) but no specific name$")
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

    @Given("^An existing project (\\w+) with status (\\w+), a task (\\w+) will be crated with status (\\w+), description (.+), assigned to employee (\\d+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void a_new_task_will_be_created(String projectName, String projectStatus, String name, String status, String description, Long employeeCode, String priority, String startDate, String endDate) {
        
        project = repository.createProject(new Project(null, null, projectName, projectStatus, null, null, null));

        this.taskName = name;
        this.status = status;
        this.description = description;
        this.employeeCode = employeeCode;
        this.priority = priority;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Given("^No current created projects, a task (\\w+) will be crated$")
    public void an_existing_task_will_be_updated_with_no_projects(String taskName) {
        this.taskName = taskName;
    }

    @Given("^An existing task (\\w+) in project (\\w+), with status (\\w+), description (.+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void an_existing_task_will_be_updated_with_a_new_name(String taskName, String projectName, String status, String description, String recievedStartDate, String recievedEndDate) {
        this.taskName = taskName;
        this.projectName = projectName;
        this.status = status;
        this.description = description;
        this.startDate = LocalDate.parse(recievedStartDate);
        this.endDate = LocalDate.parse(recievedEndDate);

        project = repository.createProject(new Project(null, null, projectName, null, null, null, null));
        this.task = createTask(project.getProjectCode(), this.taskName, this.status, this.description, null, null, this.startDate, this.endDate);
    }

    @Given("^An existing task (\\w+) in project (\\w+), with status (\\w+), employee with code (\\d+) as developer, description (.+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void an_existing_task_will_be_updated_with_a_new_name(String taskName, String projectName, String status, Long employeeCode, String description, String recievedStartDate, String recievedEndDate) {
        this.taskName = taskName;
        this.projectName = projectName;
        this.status = status;
        this.employeeCode = employeeCode;
        this.description = description;
        this.startDate = LocalDate.parse(recievedStartDate);
        this.endDate = LocalDate.parse(recievedEndDate);

        project = repository.createProject(new Project(null, null, projectName, null, null, null, null));
        this.task = createTask(project.getProjectCode(), this.taskName, this.status, this.description, this.employeeCode, null, this.startDate, this.endDate);
    }

    @Given("^An existing task (\\w+) in (\\w+) project (\\w+), with status (\\w+), description (.+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
    public void an_existing_task_will_be_updated_with_a_new_name(String taskName, String projectStatus, String projectName, String status, String description, String recievedStartDate, String recievedEndDate) {
        this.taskName = taskName;
        this.status = status;
        this.description = description;
        this.startDate = LocalDate.parse(recievedStartDate);
        this.endDate = LocalDate.parse(recievedEndDate);

        project = repository.createProject(new Project(null, null, projectName, null, null, null, null));
        this.task = createTask(project.getProjectCode(), this.taskName, this.status, this.description, null, null, this.startDate, this.endDate);
        repository.updateProject(project.getProjectCode(), new Project(null, null, null, projectStatus, null, null, null));
    }

    @Given("^An existing task (\\w+) in project (\\w+) with status (\\w+)$")
    public void an_existing_task_in_project(String taskName, String projectName, String projectStatus) {

        project = repository.createProject(new Project(null, null, projectName, projectStatus, null, null, null));
        task = createTask(project.getProjectCode(), taskName, null, null, null, null, null, null);
        repository.createTask(project.getProjectCode(), task);
    }


    @When("^Attempting to create the task assigned to the project$")
    public void i_create_the_task_assigned_to_the_project() {
        try {
            this.task = createTask(project.getProjectCode(), taskName, status, description, employeeCode, priority, startDate, endDate);
        } catch (Exception nsn){
            this.nsn = nsn;
        };

        try {
            this.repository.createTask(project.getProjectCode(), task);
        } catch (Exception nsn){
            this.nsn = nsn;
        };        
    }

    @When("^Attempting to create the task with no project code$")
    public void i_create_the_task_with_no_project_code() {
        try {
            this.task = createTask(null, taskName, null, null, null, null, null, null);
        } catch (Exception nsn){
            this.nsn = nsn;
        };    
    }

    @When("^Attempting to update the task title to (\\w+)$")
    public void i_udpate_the_task_with_a_new_name(String name) {
        task = updateTask(task.getTaskCode(), new Task(name, null, null, null, null, null, null));
    }

    @When("^Attempting to update the task status to (\\w+)$")
    public void i_udpate_the_task_with_a_new_status(String status) {
        task = updateTask(task.getTaskCode(), new Task(null, status, null, null, null, null, null));
    }

    @When("^Attempting to update the task description to (.+)$")
    public void i_udpate_the_task_with_a_new_description(String description) {
        task = updateTask(task.getTaskCode(), new Task(null, null, description, null, null, null, null));
    }

    @When("^Attempting to update the task end date to (\\d{4}-\\d{2}-\\d{2})$")
    public void i_udpate_the_task_with_a_new_end_date(String endDate) {
        try {
            task = updateTask(task.getTaskCode(), new Task(null, null, null, null, null, null, LocalDate.parse(endDate)));
        } catch (Exception nsn){
            this.nsn = nsn;
        };
    }

    @When("^Attempting to assign employee (\\d+) as developer$")
    public void i_udpate_the_task_with_a_new_developer(Long employeeCode) {
        task = updateTask(task.getTaskCode(), new Task(null, null, null, employeeCode, null, null, null));
    }

    @When("^Attempting to update the task developer to employee (\\d+)$")
    public void i_udpate_the_task_with_a_new_developer_on_suspended_project(Long employeeCode) {
        try {
            repository.updateTask(task.getTaskCode(), new Task(null, null, null, employeeCode, null, null, null));
        } catch (Exception nsn){
            this.nsn = nsn;
        };     
    }

    @When("^Attempting to terminate the task$")
    public void i_terminate_the_task() {
        try {
            task = terminateTask(task.getTaskCode());
        } catch (Exception nsn){
            this.nsn = nsn;
        };   
    }

    @When("^Attempting to delete the task$")
    public void i_delete_the_task() {
        this.deletedTaskCode = task.getTaskCode();
        repository.deleteTask(task.getTaskCode());
    }

    @Then("^The task should be successfully created with name (\\w+), status (\\w+), description (.+), assigned to employee (\\d+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and end date (\\d{4}-\\d{2}-\\d{2})$")
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

    @Then("^The task should be successfully created with name (\\w+), status (\\w+), description (.+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}), end date (\\d{4}-\\d{2}-\\d{2}) and no assigned developer$")
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

    @Then("^The task should be successfully created with name (\\w+), status (\\w+), assigned to employee (\\d+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}), end date (\\d{4}-\\d{2}-\\d{2}) and no description$")
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

    @Then("^The task should be successfully created with name (\\w+), status (\\w+), description (.+), assigned to employee (\\d+), priority (\\w+), start date (\\d{4}-\\d{2}-\\d{2}) and no estimated end date$")
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

    @Then("^Creation should be denied due to invalid end date$")
    public void the_task_cant_be_created_with_older_end_date() {
        assertNotNull(nsn);
    }

    @Then("^Creation should be denied due to invalid project status$")
    public void the_task_cant_be_created_with_invalid_project() {
        assertNotNull(nsn);
    }

    @Then("^Creation should be denied because a task can't be created without projects$")
    public void the_task_cant_be_created_with_no_project() {
        assertNotNull(nsn);
    }

    @Then("^The task should now be called (\\w+)$")
    public void the_task_should_now_be_called(String name) {
        assertEquals(name, task.getName());
    }

    @Then("^The task should now have (\\w+) status$")
    public void the_task_should_now_have_closed_status(String status) {
        assertEquals(TaskStatus.valueOf(status.toUpperCase()), task.getStatus());
    }

    @Then("^The task description should now be (.+)$")
    public void the_task_should_now_have_new_description(String description) {
        assertEquals(description, task.getDescription());
    }

    @Then("^The task end date should now be (\\d{4}-\\d{2}-\\d{2})$")
    public void the_task_should_now_have_new_end_date(String endDate) {
        assertEquals(LocalDate.parse(endDate), task.getEndDate());
    }

    @Then("^Update should be denied due to invalid end date$")
    public void the_task_cant_be_updated_with_invalid_end_date() {
        assertNotNull(nsn);
    }

    @Then("^The task developer should now be employee (\\d+)$")
    public void the_task_should_now_have_new_developer(Long employeeCode) {
        assertEquals(employeeCode, task.getEmployeeCode());
    }

    @Then("^Update should be denied due to invalid project status$")
    public void the_task_cant_be_updated() {
        assertNotNull(nsn);
    }

    @Then("^Termination should be denied due to task already locked$")
    public void locked_task_cant_be_terminated() {
        assertNotNull(nsn);
    }

    @Then("^Termination should be denied due to closed task$")
    public void closed_task_cant_be_terminated() {
        assertNotNull(nsn);
    }

    @Then("^The task should no longer exist$")
    public void deleted_task_no_longer_exists() {
        ResponseEntity<?> response = repository.getTask(deletedTaskCode);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}

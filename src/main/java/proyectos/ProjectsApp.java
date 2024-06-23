package proyectos;

import proyectos.exceptions.CantCreateTaskOnInvalidProjectException;
import proyectos.model.Project;
import proyectos.model.ProjectStatus;
import proyectos.model.Task;
import proyectos.model.TaskStatus;
import proyectos.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

import proyectos.service.TaskService;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class ProjectsApp {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private TaskService taskService;

	public static void main(String[] args) {
		SpringApplication.run(ProjectsApp.class, args);
	}

	@PostMapping("/projects")
	@ResponseStatus(HttpStatus.CREATED)
	public Project createProject(@RequestBody Project project) {
		return projectService.createProject(project);
	}

	@GetMapping("/projects")
	public Collection<Project> getProjects() {
		return projectService.getProjects();
	}

	@GetMapping("/projects/{projectCode}")
	public ResponseEntity<Project> getProject(@PathVariable Long projectCode) {
		Optional<Project> projectOptional = projectService.getByCode(projectCode);
		return ResponseEntity.of(projectOptional);
	}

	@PutMapping("/projects/{projectCode}")
	public ResponseEntity<Project> updateProject(@PathVariable Long projectCode, @RequestBody Project updatedProject) {
		Project project = projectService.updateProject(projectCode, updatedProject);
		return project != null ? ResponseEntity.ok(project) : ResponseEntity.notFound().build();
	}

	@PostMapping("/projects/{projectCode}/tasks")
	@ResponseStatus(HttpStatus.CREATED)
	public Task createTask(@PathVariable Long projectCode, @RequestBody Task task) {
		Optional<Project> projectOptional = projectService.getByCode(projectCode);
    
		if (projectOptional.isPresent()) {
			Project currentProject = projectOptional.get();
			
			if (currentProject.getStatus() == ProjectStatus.INITIATED) {
				return taskService.createTask(projectCode, task);
			} else {
				throw new CantCreateTaskOnInvalidProjectException("Can't create a new task on" + currentProject.getStatus() + "project");
			}
		} else {
			throw new CantCreateTaskOnInvalidProjectException("Project with code " + projectCode + " not found");
		}
	}

	@GetMapping("/tasks/{taskCode}")
	public ResponseEntity<Task> getTask(@PathVariable Long taskCode) {
		Optional<Task> taskOptional = taskService.getByCode(taskCode);
		return ResponseEntity.of(taskOptional);
	}

	@GetMapping("/projects/{projectCode}/tasks")
	public Collection<Task> getTasksByProject(@PathVariable Long projectCode) {
		return taskService.getByProject(projectCode);
	}

	@PutMapping("/tasks/{taskCode}")
	public ResponseEntity<Task> updateTask(@PathVariable Long taskCode, @RequestBody Task updatedTask) {
		Optional<Task> taskOptional = taskService.getByCode(taskCode);
		Task task = null;
		if (taskOptional.isPresent()) {
			Task currentTask = taskOptional.get();
			Optional<Project> projectOptional = projectService.getByCode(currentTask.getProjectCode());
			if(projectOptional.isPresent()){
				Project currentProject = projectOptional.get();
				if (currentProject.getStatus() == ProjectStatus.INITIATED) {
					task = taskService.updateTask(taskCode, updatedTask);
				} else {
					throw new CantCreateTaskOnInvalidProjectException("Can't update a task on" + currentProject.getStatus() + "project");
				}
			}
		} else {
			throw new CantCreateTaskOnInvalidProjectException("No task found for code " + taskCode);
		}

		return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
	}

	@PutMapping("/projects/{projectCode}/terminate")
	public void terminateProject(@PathVariable Long projectCode) {
		projectService.terminateProject(projectCode);
	}

	@PutMapping("/tasks/{taskCode}/terminate")
	public void terminateTask(@PathVariable Long taskCode) {
		taskService.terminateTask(taskCode);
	}

	@DeleteMapping("/projects/{projectCode}")
	public void deleteProject(@PathVariable Long projectCode) {
		projectService.deleteProject(projectCode);
	}

	@DeleteMapping("/tasks/{taskCode}")
	public void deleteTask(@PathVariable Long taskCode) {
		taskService.deleteTask(taskCode);
	}

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build();
	}

}

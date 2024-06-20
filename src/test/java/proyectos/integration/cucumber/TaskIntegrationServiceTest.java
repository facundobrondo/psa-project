package proyectos.integration.cucumber;

import proyectos.ProjectsApp;
import proyectos.model.Project;
import proyectos.model.Task;
import proyectos.exceptions.*;
import proyectos.model.TaskStatus;
import proyectos.service.TaskService;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = ProjectsApp.class)
@WebAppConfiguration
public class TaskIntegrationServiceTest{

    @Autowired
    TaskService taskService;

    Task createTask(Long projectCode, String name, String status, String description, Long employeeCode, String priority, LocalDate startDate, LocalDate endDate) throws InvalidNameException {
        return taskService.createTask(projectCode, new Task(name, status, description, employeeCode, priority, startDate, endDate));
    }

    Task updateTask(Long code, Task updatedTask) {
        return taskService.updateTask(code, updatedTask);
    }

    Task terminateTask(Long code) {
        return taskService.terminateTask(code);
    }

}

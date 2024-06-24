package proyectos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectos.model.Project;
import proyectos.model.ProjectStatus;
import proyectos.model.Task;
import proyectos.model.TaskStatus;
import proyectos.exceptions.*;
import proyectos.repository.TaskRepository;
import proyectos.repository.ProjectRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Long projectCode, Task task) {
        if(projectCode == null){
            throw new CantCreateTaskOnInvalidProjectException("Can't create a task with no assigned project");
        }
        if(task.getName() == null || task.getName().isEmpty()){
            throw new InvalidNameException("Name cannot be null or empty");
        }
        task.setProjectCode(projectCode);
        return taskRepository.save(task);
    }

    public Optional<Task> getByCode(Long code) {
        return taskRepository.findById(code);
    }

    public Collection<Task> getByProject(Long projectCode) {
        return taskRepository.findAllByProjectCode(projectCode);
    }

    public Task updateTask(Long code, Task updatedTask) {
        return taskRepository.findById(code).map(task -> {
            if (updatedTask.getProjectCode() != null) {
                task.setProjectCode(updatedTask.getProjectCode());
            }
            if (updatedTask.getEmployeeCode() != null) {
                task.setEmployeeCode(updatedTask.getEmployeeCode());
            }
            if (updatedTask.getName() != null) {
                task.setName(updatedTask.getName());
            }
            if (updatedTask.getStartDate() != null) {
                if(task.getEndDate() != null && task.getEndDate().isBefore(updatedTask.getStartDate())){
                    throw new InvalidEndDateException("Start date can't be posterior to end date");
                }
                task.setStartDate(updatedTask.getStartDate());
            }
            if (updatedTask.getEndDate() != null) {
                if(task.getStartDate() != null && updatedTask.getEndDate().isBefore(task.getStartDate())){
                    throw new InvalidEndDateException("End date can't be prior to start date");
                }
                task.setEndDate(updatedTask.getEndDate());
            }
            if (updatedTask.getStatus() != null) {
                task.setStatus(updatedTask.getStatus());
            }
            if (updatedTask.getPriority() != null) {
                task.setPriority(updatedTask.getPriority());
            }
            if (updatedTask.getDescription() != null) {
                task.setDescription(updatedTask.getDescription());
            }
            return taskRepository.save(task);
        }).orElse(null);
    }

    public void deleteTask(Long code) {
        taskRepository.deleteById(code);
    }
}

package proyectos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectos.model.Task;
import proyectos.repository.TaskRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Long projectCode, Task task) {
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
                task.setStartDate(updatedTask.getStartDate());
            }
            if (updatedTask.getEndDate() != null) {
                task.setEndDate(updatedTask.getEndDate());
            }
            if (updatedTask.getStatus() != null) {
                task.setStatus(updatedTask.getStatus());
            }
            if (updatedTask.getPriority() != null) {
                task.setPriority(updatedTask.getPriority());
            }
            return taskRepository.save(task);
        }).orElse(null);
    }
}

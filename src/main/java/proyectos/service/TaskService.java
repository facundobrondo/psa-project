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
}

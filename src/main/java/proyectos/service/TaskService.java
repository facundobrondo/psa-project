package proyectos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectos.model.Task;
import proyectos.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Long projectCode, Task task) {
        task.setProjectCode(projectCode);
        return taskRepository.save(task);
    }
}

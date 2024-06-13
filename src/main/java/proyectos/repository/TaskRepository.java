package proyectos.repository;

import proyectos.model.Task;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TaskRepository extends CrudRepository<Task, Long> {
    Task findTaskByTaskCode(Long taskCode);

    List<Task> findAllByProjectCode(Long projectCode);

    @Override
    List<Task> findAll();
}
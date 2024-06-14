package proyectos.repository;

import proyectos.model.Task;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    Optional<Task> findById(Long id);

    List<Task> findAllByProjectCode(Long projectCode);
}
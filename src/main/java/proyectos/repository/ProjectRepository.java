package proyectos.repository;

import proyectos.model.Project;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProjectRepository extends CrudRepository<Project, Long> {
    @Override
    Optional<Project> findById(Long id);

    @Override
    List<Project> findAll();
}
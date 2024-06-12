package proyectos.repository;

import proyectos.model.Project;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProjectRepository extends CrudRepository<Project, Long> {
    Project findProjectByProjectCode(Long projectCode);

    @Override
    List<Project> findAll();
}
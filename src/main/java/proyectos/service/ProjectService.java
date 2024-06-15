package proyectos.service;

import proyectos.model.Project;
import proyectos.model.Task;
import proyectos.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProjectService {

	@Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Collection<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getByCode(Long code) {
        return projectRepository.findById(code);
    }

    public Project updateProject(Long code, Project updatedProject) {
        return projectRepository.findById(code).map(project -> {
            if (updatedProject.getLeaderCode() != null) {
                project.setLeaderCode(updatedProject.getLeaderCode());
            }
            if (updatedProject.getProductCode() != null) {
                project.setProductCode(updatedProject.getProductCode());
            }
            if (updatedProject.getName() != null) {
                project.setName(updatedProject.getName());
            }
            if (updatedProject.getStartDate() != null) {
                project.setStartDate(updatedProject.getStartDate());
            }
            if (updatedProject.getEndDate() != null) {
                project.setEndDate(updatedProject.getEndDate());
            }
            if (updatedProject.getStatus() != null) {
                project.setStatus(updatedProject.getStatus());
            }
            return projectRepository.save(project);
        }).orElse(null);
    }
}
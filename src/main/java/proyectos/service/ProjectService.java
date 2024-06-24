package proyectos.service;

import proyectos.model.Project;
import proyectos.exceptions.*;
import proyectos.model.ProjectStatus;
import proyectos.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.PropertyAccessor;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProjectService {

	@Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project) {
        if(project.getName() == null || project.getName().isEmpty()){
            throw new InvalidNameException("Name cannot be null or empty");
        }
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
                if(project.getEndDate() != null && project.getEndDate().isBefore(updatedProject.getStartDate())){
                    throw new InvalidEndDateException("Start date can't be posterior to end date");
                }
                project.setStartDate(updatedProject.getStartDate());
            }
            if (updatedProject.getEndDate() != null) {
                if(project.getStartDate() != null && updatedProject.getEndDate().isBefore(project.getStartDate())){
                    throw new InvalidEndDateException("End date can't be prior to start date");
                }
                project.setEndDate(updatedProject.getEndDate());
            }
            if (updatedProject.getStatus() != null) {
                project.setStatus(updatedProject.getStatus());
            }
            if (updatedProject.getDescription() != null) {
                project.setDescription(updatedProject.getDescription());
            }
            return projectRepository.save(project);
        }).orElse(null);
    }

    public void deleteProject(Long code) {
        projectRepository.deleteById(code);
    }
}
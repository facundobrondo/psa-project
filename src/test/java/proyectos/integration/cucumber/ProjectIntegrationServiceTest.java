package proyectos.integration.cucumber;

import proyectos.ProjectsApp;
import proyectos.model.Project;
import proyectos.exceptions.*;
import proyectos.model.ProjectStatus;
import proyectos.service.ProjectService;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = ProjectsApp.class)
@WebAppConfiguration
public class ProjectIntegrationServiceTest{

    @Autowired
    ProjectService projectService;

    Project createProject(Long leaderCode, Long productCode, String name, String status, String description, LocalDate startDate, LocalDate endDate) {
        return projectService.createProject(new Project(leaderCode, productCode, name, status, description, startDate, endDate));
    }

    Project updateProject(Long code, Project updatedProject) {
        return projectService.updateProject(code, updatedProject);
    }

    Project terminateProject(Long code) {
        return projectService.terminateProject(code);
    }

}

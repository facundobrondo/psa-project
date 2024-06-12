package proyectos.integration.cucumber;

import proyectos.ProjectsApp;
import proyectos.model.Project;
import proyectos.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = ProjectsApp.class)
@WebAppConfiguration
public class ProjectIntegrationServiceTest {

    @Autowired
    ProjectService projectService;

}

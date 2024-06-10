package proyectos.integration.cucumber;

import proyectos.ProyectosApp;
import proyectos.model.Proyecto;
import proyectos.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = ProyectosApp.class)
@WebAppConfiguration
public class ProjectIntegrationServiceTest {

    @Autowired
    ProjectService projectService;

}

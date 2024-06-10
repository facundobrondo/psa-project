package proyectos.service;

import proyectos.model.Proyecto;
import com.aninfo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

	@Autowired
    private ProjectRepository projectRepository;

}
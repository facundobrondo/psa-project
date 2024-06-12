package proyectos.integration.cucumber;

import proyectos.model.Project;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProjectOperationsTest extends ProjectIntegrationServiceTest {

    private Project project;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}

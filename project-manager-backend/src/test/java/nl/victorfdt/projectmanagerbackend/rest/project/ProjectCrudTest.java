package nl.victorfdt.projectmanagerbackend.rest.project;

import nl.victorfdt.projectmanagerbackend.entity.Project;
import nl.victorfdt.projectmanagerbackend.service.ProjectService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProjectCrudTest {

    @Autowired
    private ProjectService service;


    private static final String IDENTIFIER_LOWERCASE_1 = "jira1";
    private static final String IDENTIFIER_UPPERCASE_1 = "JIRA1";

    private static final String IDENTIFIER_LOWERCASE_2 = "jira2";
    private static final String IDENTIFIER_LOWERCASE_3 = "jira3";


    private static final String NAME = "Test project";
    private static final String DESCRIPTION = "Description of the project";
    private static final LocalDate START_DATE = LocalDate.of(2020, 12, 10);
    private static final LocalDate END_DATE = LocalDate.of(2021, 12, 10);

    @DisplayName("Create a project and search it")
    @Test
    void createNewProjectAndSearchIt() {
        // Create a project to be persisted
        Project project = createProject(IDENTIFIER_LOWERCASE_1);
        service.add(project);

        // Retrieve the project from database and validate information
        Project projectDB = service.findByIdentifier(IDENTIFIER_LOWERCASE_1);
        assertEquals(NAME, projectDB.getName());
        assertEquals(IDENTIFIER_UPPERCASE_1, projectDB.getIdentifier());
        assertEquals(DESCRIPTION, projectDB.getDescription());
        assertEquals(START_DATE, projectDB.getStartDate());
        assertEquals(END_DATE, projectDB.getEndDate());

        // Check if the project was created today
        assertTrue(projectDB.getCreatedAt().toLocalDate().isEqual(LocalDate.now()));
    }

    @DisplayName("Find all projects")
    @Test
    void findAllProjects() {
        // Create two projects project
        Project project1 = createProject(IDENTIFIER_LOWERCASE_2);
        service.add(project1);

        Project project2 = createProject(IDENTIFIER_LOWERCASE_3);
        service.add(project2);

        assertEquals(2, service.findAll().size());
    }

    private Project createProject(String identifier) {
        Project project = new Project();
        project.setName(NAME);
        project.setIdentifier(identifier);
        project.setDescription(DESCRIPTION);
        project.setStartDate(START_DATE);
        project.setEndDate(END_DATE);

        return project;
    }

}

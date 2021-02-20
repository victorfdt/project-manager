package nl.victorfdt.projectmanagerbackend.rest.project;

import nl.victorfdt.projectmanagerbackend.dao.ProjectDAO;
import nl.victorfdt.projectmanagerbackend.data.entity.Project;
import nl.victorfdt.projectmanagerbackend.data.vo.ProjectVO;
import nl.victorfdt.projectmanagerbackend.service.ProjectService;
import org.junit.jupiter.api.Disabled;
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
    private ProjectDAO projectDAO;


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
    @Disabled
    void createNewProjectAndSearchIt() {
        // Create a project to be persisted
        ProjectVO projectVO = createProject(IDENTIFIER_LOWERCASE_1);
        service.add(projectVO);

        // Retrieve the project from database and validate information
        ProjectVO projectDB = service.findByIdentifier(IDENTIFIER_LOWERCASE_1);
        assertEquals(NAME, projectDB.getName());
        assertEquals(IDENTIFIER_UPPERCASE_1, projectDB.getIdentifier());
        assertEquals(DESCRIPTION, projectDB.getDescription());
        assertEquals(START_DATE, projectDB.getStartDate());
        assertEquals(END_DATE, projectDB.getEndDate());

        // Check if the project was created today
        assertTrue(projectDAO.findByIdentifier(projectDB.getIdentifier()).getCreatedAt().toLocalDate().isEqual(LocalDate.now()));
    }

    @DisplayName("Find all projects")
    @Test
    @Disabled
    void findAllProjects() {
        // Create two projects project
        ProjectVO projectVO1 = createProject(IDENTIFIER_LOWERCASE_2);
        service.add(projectVO1);

        ProjectVO project2 = createProject(IDENTIFIER_LOWERCASE_3);
        service.add(project2);

        assertEquals(2, service.findAll().size());
    }

    private ProjectVO createProject(String identifier) {
        ProjectVO projectVO = new ProjectVO();
        projectVO.setName(NAME);
        projectVO.setIdentifier(identifier);
        projectVO.setDescription(DESCRIPTION);
        projectVO.setStartDate(START_DATE);
        projectVO.setEndDate(END_DATE);

        return projectVO;
    }

}

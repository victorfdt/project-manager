package nl.victorfdt.projectmanagerbackend.service;

import nl.victorfdt.projectmanagerbackend.dao.ProjectDAO;
import nl.victorfdt.projectmanagerbackend.data.entity.Project;
import nl.victorfdt.projectmanagerbackend.exception.EntityNotFoundException;
import nl.victorfdt.projectmanagerbackend.exception.UniqueKeyViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDAO projectDAO;

    @Override
    public List<Project> findAll() {

        // Convert Iterable to List.
        return StreamSupport
                .stream(projectDAO.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Project findById(Long id) {
        return projectDAO.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Project with id '%d' was not found!", id))
        );
    }

    @Override
    public Project findByIdentifier(String identifier) {
        Project project = projectDAO.findByIdentifier(identifier.toUpperCase());

        if (project == null) {
            throw new EntityNotFoundException(String.format("It was not found a project with identifier '%s'", identifier.toUpperCase()));
        }

        return project;
    }

    @Override
    public void add(Project project) {
        try {
            projectDAO.save(project);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueKeyViolationException(String.format("The given project identifier '%s' already exists.", project.getIdentifier().toUpperCase()));
        }
    }

    @Override
    public void update(Project project) {
        Project projectDB = findByIdentifier(project.getIdentifier());

        // Persist the createdAt information at the given project
        project.setCreatedAt(projectDB.getCreatedAt());

        try {
            projectDAO.save(project);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueKeyViolationException(String.format("The given project identifier '%s' doest not exist.", project.getIdentifier().toUpperCase()));
        }
    }

    @Override
    public void deleteById(Long id) {
        projectDAO.deleteById(id);
    }

    @Override
    public void deleteByIdentifier(String identifier) {
        Project project = projectDAO.findByIdentifier(identifier.toUpperCase());

        if (project == null) {
            throw new EntityNotFoundException(String.format("There is not project with identifier '%s'", identifier.toUpperCase()));
        }

        projectDAO.delete(project);
    }
}

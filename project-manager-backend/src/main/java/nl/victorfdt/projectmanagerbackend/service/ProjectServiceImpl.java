package nl.victorfdt.projectmanagerbackend.service;

import nl.victorfdt.projectmanagerbackend.dao.ProjectDAO;
import nl.victorfdt.projectmanagerbackend.entity.Project;
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

        // Convert Iterator to List.
        return StreamSupport
                .stream(projectDAO.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Project findById(Long id) {
        if (projectDAO.findById(id).isEmpty()) {
            throw new EntityNotFoundException(String.format("Project with id '%d' was not found!", id));
        }

        return projectDAO.findById(id).get();
    }

    @Override
    public Project findByIdentifier(String identifier) {
        Project project = projectDAO.findByIdentifier(identifier.toUpperCase());

        if (project == null) {
            throw new EntityNotFoundException(String.format("It was not found a project with identifier '%s'", identifier));
        }

        return project;
    }

    @Override
    public void save(Project project) {
        try {
            project.setIdentifier(project.getIdentifier().toUpperCase());
            projectDAO.save(project);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueKeyViolationException(String.format("The given project identifier '%s' already exists.", project.getIdentifier()));
        }
    }

    @Override
    public void deleteById(Long id) {
        projectDAO.deleteById(id);
    }

    @Override
    public void deleteByIdentifier(String identifier) {
        Project project = projectDAO.findByIdentifier(identifier);

        if (project == null) {
            throw new EntityNotFoundException(String.format("There is not project with identifier '%s'", identifier));
        }

        projectDAO.delete(project);
    }
}

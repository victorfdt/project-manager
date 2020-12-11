package nl.victorfdt.projectmanagerbackend.service;

import nl.victorfdt.projectmanagerbackend.dao.ProjectDAO;
import nl.victorfdt.projectmanagerbackend.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new RuntimeException(String.format("Project with id %d was not found!", id));
        }

        return projectDAO.findById(id).get();
    }

    @Override
    public void save(Project project) {
        projectDAO.save(project);
    }

    @Override
    public void deleteById(Long id) {
        projectDAO.deleteById(id);
    }
}

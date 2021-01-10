package nl.victorfdt.projectmanagerbackend.service;

import nl.victorfdt.projectmanagerbackend.converter.DozerConverter;
import nl.victorfdt.projectmanagerbackend.dao.ProjectDAO;
import nl.victorfdt.projectmanagerbackend.data.entity.Project;
import nl.victorfdt.projectmanagerbackend.data.vo.ProjectVO;
import nl.victorfdt.projectmanagerbackend.exception.EntityNotFoundException;
import nl.victorfdt.projectmanagerbackend.exception.UniqueKeyViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDAO projectDAO;

    public List<ProjectVO> findAll() {
        return DozerConverter.parse(projectDAO.findAll(), ProjectVO.class);
    }

    @Override
    public ProjectVO findByIdentifier(String identifier) {
        Project project = findProjectByIdentifier(identifier);
        return DozerConverter.parse(project, ProjectVO.class);
    }

    @Override
    public void add(ProjectVO projectVO) {
        try {
            projectDAO.save(DozerConverter.parse(projectVO, Project.class));
        } catch (DataIntegrityViolationException e) {
            throw new UniqueKeyViolationException(String.format("The given project identifier '%s' already exists.", projectVO.getIdentifier().toUpperCase()));
        }
    }

    @Override
    public void update(ProjectVO projectVO) {
        Project projectToSave = DozerConverter.parse(projectVO, Project.class);

        // Set the ID and the createdAt. The VO does not have those information, so I need to set from the DB
        Project projectDB = findProjectByIdentifier(projectVO.getIdentifier());
        projectToSave.setCreatedAt(projectDB.getCreatedAt());
        projectToSave.setId(projectDB.getId());

        try {
            projectDAO.save(DozerConverter.parse(projectToSave, Project.class));
        } catch (DataIntegrityViolationException e) {
            throw new UniqueKeyViolationException(String.format("The given project identifier '%s' doest not exist.", projectVO.getIdentifier().toUpperCase()));
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

    private Project findProjectByIdentifier(String identifier) {
        var project = projectDAO.findByIdentifier(identifier.toUpperCase());

        if (project == null) {
            throw new EntityNotFoundException(String.format("It was not found a project with identifier '%s'", identifier.toUpperCase()));
        }

        return project;
    }
}

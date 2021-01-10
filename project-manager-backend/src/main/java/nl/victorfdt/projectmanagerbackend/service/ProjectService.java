package nl.victorfdt.projectmanagerbackend.service;

import nl.victorfdt.projectmanagerbackend.data.vo.ProjectVO;

import java.util.List;

public interface ProjectService {

    List<ProjectVO> findAll();

    ProjectVO findByIdentifier(String identifier);

    void add(ProjectVO project);

    void update(ProjectVO project);

    void deleteById(Long id);

    void deleteByIdentifier(String identifier);

}

package nl.victorfdt.projectmanagerbackend.service;

import nl.victorfdt.projectmanagerbackend.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> findAll();

    Project findById(Long id);

    void save(Project theEmployee);

    void deleteById(Long id);

}

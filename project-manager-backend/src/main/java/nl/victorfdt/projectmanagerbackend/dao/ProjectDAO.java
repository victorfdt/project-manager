package nl.victorfdt.projectmanagerbackend.dao;

import nl.victorfdt.projectmanagerbackend.entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDAO extends CrudRepository<Project, Long> {

}

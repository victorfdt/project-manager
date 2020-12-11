package nl.victorfdt.projectmanagerbackend.repository;

import nl.victorfdt.projectmanagerbackend.entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

}

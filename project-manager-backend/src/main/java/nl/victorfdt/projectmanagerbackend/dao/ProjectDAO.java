package nl.victorfdt.projectmanagerbackend.dao;

import nl.victorfdt.projectmanagerbackend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository
@Repository
public interface ProjectDAO extends JpaRepository<Project, Long> {

    Project findByIdentifier(String identifier);
}

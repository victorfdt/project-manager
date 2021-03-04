package nl.victorfdt.projectmanagerbackend.repository;

import nl.victorfdt.projectmanagerbackend.data.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByIdentifier(String identifier);
}

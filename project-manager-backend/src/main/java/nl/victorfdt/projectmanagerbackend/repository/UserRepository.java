package nl.victorfdt.projectmanagerbackend.repository;

import nl.victorfdt.projectmanagerbackend.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Using Java Persistence Query Language (JPQL)
    @Query("select u from User u where u.userName =:userName ")
    User findByUserName(@Param("userName") String userName);
}

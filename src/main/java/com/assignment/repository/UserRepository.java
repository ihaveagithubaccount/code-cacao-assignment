package main.java.com.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.java.com.assignment.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
    User findById(Long id);
    void deleteUserById(Long id);

}
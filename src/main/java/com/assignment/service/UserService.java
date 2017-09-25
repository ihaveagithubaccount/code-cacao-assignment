package main.java.com.assignment.service;

import java.util.List;

import main.java.com.assignment.entity.User;




public interface UserService {

	List<User> findAllUsers();
	
	User findById(Long id);
	
    User saveUser(User user);
    
    User updateUser(User user);
    
    void deleteUser (User user);
 
    boolean isUserExist(User user);

	void deleteUserById(Long id);
	
}

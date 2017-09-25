package main.java.com.assignment.controller;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import main.java.com.assignment.entity.User;
import main.java.com.assignment.service.UserService;
import main.java.com.assignment.util.CustomErrorType;





@RestController
public class UserController {

	
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    UserService userService;

    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        logger.info("Fetching User with id {}", id);
        User user = userService.findById(id);
        if (user == null) {
            logger.error("User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("User with id " + id 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/registerUser")
    public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        logger.info("Creating User : {}", user);
 
        if (userService.isUserExist(user)) {
            logger.error("Unable to create. A User with id {} already exist", user.getId());
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
            user.getId() + " already exist."),HttpStatus.CONFLICT);
        }
        userService.saveUser(user);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/registerUser/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity(user,HttpStatus.CREATED);
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        logger.info("Updating User with id {}", id);
 
        User currentUser = userService.findById(id);
 
        if (currentUser == null) {
            logger.error("Unable to update. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
 
        currentUser.setEmail(user.getEmail());
        currentUser.setActive(user.getActive());
        currentUser.setName(user.getName());
        currentUser.setLastName(user.getLastName());
        currentUser.setRoles(user.getRoles());
        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
    
    

    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        logger.info("Fetching & Deleting User with id {}", id);
 
        User user = userService.findById(id);
        if (user == null) {
            logger.error("Unable to delete. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

}

package main.java.com.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.com.assignment.entity.User;
import main.java.com.assignment.repository.UserRepository;





@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
 
    @Autowired
    private UserRepository userRepository;
    

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

	@Override
	public User findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User saveUser(User user) {
		userRepository.save(user);
		return user;
		
	}

	@Override
	public User updateUser(User user) {
		   saveUser(user);
		   return user;
		
	}

	@Override
	public boolean isUserExist(User user) {
		return findById(user.getId()) != null;
	}

	@Override
	public void deleteUserById(Long id) {
		userRepository.delete(id);
		
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

}

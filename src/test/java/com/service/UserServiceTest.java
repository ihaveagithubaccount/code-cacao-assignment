package test.java.com.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import main.java.com.assignment.entity.User;
import main.java.com.assignment.repository.UserRepository;
import main.java.com.assignment.service.UserServiceImpl;




@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
	
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllUsers(){
		List<User> userList = new ArrayList<User>();
		userList.add(new User(1L, "", "", "", true, null));
		userList.add(new User(2L, "", "", "", true, null));
		userList.add(new User(3L, "", "", "", true, null));
		when(userRepository.findAll()).thenReturn(userList);
		List<User> result = userService.findAllUsers();
		assertEquals(3, result.size());
	}
	
	@Test
	public void testGetUserById(){
		User user = new User(1L, "", "", "", true, null);
		when(userRepository.findById(1L)).thenReturn(user);
		User result = userService.findById(1L);
		assertEquals(Long.valueOf(1L), result.getId());
		assertEquals("", result.getName());
		assertEquals("", result.getLastName());
		assertEquals(true, result.getActive());
	} 


	
	@Test
	public void saveUser(){
		User user = new User(1L, "name", "lastname", "email", true, null);
		when(userRepository.save(user)).thenReturn(user);
		User result = userService.saveUser(user);
		assertEquals(Long.valueOf(1), result.getId());
		assertEquals("name", result.getName());
		assertEquals("lastname", result.getLastName());
		assertEquals("email", result.getEmail());
		assertEquals(true, result.getActive());
	}
	
	
	@Test
	public void removeUser(){
		User user = new User(1L, "name", "lastname", "email", true, null);
		userService.deleteUser(user);
        verify(userRepository, times(1)).delete(user);
	}
	
	

}


package test.java.com.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import main.java.com.assignment.MyApplication;
import main.java.com.assignment.entity.User;
import main.java.com.assignment.repository.UserRepository;
import main.java.com.assignment.service.UserServiceImpl;






@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class UserControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserServiceImpl userService;
	

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	@Test
	public void getAllUsers() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization","Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUwNzIxOTU2N30._dw0Jfzp0ONpygZn606r_RV45qL9YfanYz_zK0If29xRHGp0Hx_VodSKrIDs-sNHHIwbB7sHkAESdkJBxXFmnA");
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<User[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, User[].class);
        User[] users = responseEntity.getBody();
        for(User user : users) {
              System.out.println("Id:"+user.getId()+", Name:"+user.getName()
                      +", Last Name: "+user.getLastName());
        }
    }
	
	@Test
    public void getUserById() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.set("Authorization","Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUwNzIxOTU2N30._dw0Jfzp0ONpygZn606r_RV45qL9YfanYz_zK0If29xRHGp0Hx_VodSKrIDs-sNHHIwbB7sHkAESdkJBxXFmnA");
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, User.class, 1);
        User user = responseEntity.getBody();
        System.out.println("Id:"+user.getId()+", Name:"+user.getName()
        		+", Last Name: "+user.getLastName());    
    }
	

	@Test
	public void verifyUserById() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/user/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.name").exists())
		.andExpect(jsonPath("$.email").exists())
		.andExpect(jsonPath("$.lastName").exists())
		.andExpect(jsonPath("$.active").exists())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.name").value("David"))
		.andExpect(jsonPath("$.lastName").value("Tennant"))
		.andDo(print());
	}
	

	@Test
	public void verifyRegisterUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/registerUser")
        .contentType(MediaType.APPLICATION_JSON)
        .content("  {\" id \" : 1,\"name\" : \"David\",\"lastName\" : \"Tennant\",\"email\" : \"dt@bluebox.com\",\"active\" : false,\"roles\" : [{\"roleId\" : 10,\"name\" : \"tenth\"}]}   ")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.name").exists())
		.andExpect(jsonPath("$.roles").exists())
		.andExpect(jsonPath("$.name").value("David"))
		.andExpect(jsonPath("$.lastName").value("Tennant"))
		.andDo(print());
	}

	@Test
	public void verifyUpdateUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.patch("/updateUser/2")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"id\" : 2,\"name\" : \"Matt\",\"lastName\" : \"Smith\",\"email\" : \"ms@bluebox.com\",\"active\" : false,\"roles\" : [{\"roleId\" : 11,\"name\" : \"eleventh\"}]}")
        .accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.name").exists())
		.andExpect(jsonPath("$.lastName").exists())
		.andExpect(jsonPath("$.id").value(2))
		.andExpect(jsonPath("$.name").value("Matt"))
		.andExpect(jsonPath("$.lastName").value("Smith"))
		.andDo(print());
	}

}

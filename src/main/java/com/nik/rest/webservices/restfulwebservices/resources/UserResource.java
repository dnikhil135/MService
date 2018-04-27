package com.nik.rest.webservices.restfulwebservices.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nik.rest.webservices.restfulwebservices.dao.User;
import com.nik.rest.webservices.restfulwebservices.dao.UserDaoService;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> findAllResource(){
		List<User> users = service.findAll();
		if(users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users,HttpStatus.FOUND);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> findOneResource(@PathVariable int id) {
		User user = service.findOne(id);
		if(user==null) {
			throw new UserNotFoundException("Id"+id);
		}
		return new ResponseEntity<User>(user,HttpStatus.FOUND);
	}
	
	@PostMapping("/saveuser")
	public ResponseEntity<User> saveUserResource(@Valid @RequestBody User user) {
		User userInfo = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userInfo.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/deleteUser/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable int id) {
		User userDeleted = service.deleteUser(id);
		if(userDeleted==null) {
			throw new UserNotFoundException("User Not Found: "+id);
		}
		return new ResponseEntity<User>(userDeleted, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/deleteAllUser")
	public ResponseEntity<User> deleteUser() {
		service.deleteAllUser();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
}

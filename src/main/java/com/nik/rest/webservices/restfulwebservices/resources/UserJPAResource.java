package com.nik.rest.webservices.restfulwebservices.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nik.rest.webservices.restfulwebservices.dao.PostRepository;
import com.nik.rest.webservices.restfulwebservices.dao.Posts;
import com.nik.rest.webservices.restfulwebservices.dao.User;
import com.nik.rest.webservices.restfulwebservices.dao.UserDaoService;
import com.nik.rest.webservices.restfulwebservices.dao.UserRepository;

@RestController
public class UserJPAResource {

	@Autowired
	private UserDaoService service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public ResponseEntity<List<User>> findAllResource(){
		List<User> users = userRepository.findAll();
		if(users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users,HttpStatus.FOUND);
	}
	
	@GetMapping("/jpa/user/{id}")
	public ResponseEntity<User> findOneResource(@PathVariable int id) {
		 Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("Id"+id);
		}
		return new ResponseEntity<User>(user.get(),HttpStatus.FOUND);
	}
	
	@PostMapping("/jpa/saveuser")
	public ResponseEntity<User> saveUserResource(@Valid @RequestBody User user) {
		User userInfo = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userInfo.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/deleteUser/{id}")
	public void deleteUser(@PathVariable int id) {
		 userRepository.deleteById(id);
	}
	
	@DeleteMapping(" /deleteAllUser")
	public ResponseEntity<User> deleteUser() {
		userRepository.deleteAll();
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@GetMapping("/jpa/user/{id}/posts")
	public ResponseEntity<List<Posts>> findPostsForUser(@PathVariable int id) {
		 Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("Id"+id);
		}
		return new ResponseEntity<List<Posts>>(user.get().getPosts(),HttpStatus.FOUND);
	}
	
	@PostMapping("/jpa/saveuser/{id}/posts")
	public ResponseEntity<User> saveUserResource(@PathVariable int id, @RequestBody Posts post) {
		 Optional<User> userFind = userRepository.findById(id);
			if(!userFind.isPresent()) {
				throw new UserNotFoundException("Id"+id);
			}
			
			User user = userFind.get();
		    post.setUser(user);
		postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post).toUri();
		return ResponseEntity.created(location).build();
	}
}

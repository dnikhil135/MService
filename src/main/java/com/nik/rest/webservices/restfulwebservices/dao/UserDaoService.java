package com.nik.rest.webservices.restfulwebservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<User>();
	private static int userCount=3;
	
	static {
		
		users.add(new User(1, "nikhil", new Date()));
		users.add(new User(2, "vagmini", new Date()));
		users.add(new User(3, "neelam", new Date()));
		
	}
	
	public List<User> findAll(){
		return users;
		
	}
	
	public User findOne(int id) {
		for (User user : users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	
	public User save(User user) {
		for(User user1:users) {
			if(user1.getId()==user.getId()) {
				return null;
			}
		}
		if(user.getId()==null) {
			user.setId(++userCount);
			
		}
		users.add(user);
		return user;
	}
	
	public User deleteUser(int id) {
	 for (User user1 : users) {
			if(user1.getId()==id) {
				users.remove(user1);
				return user1;
			}
		}
		return null;
	}
	
	public Boolean deleteAllUser() {
		Boolean deleteAll = users.removeAll(users);
		return deleteAll;
		 
		}
}
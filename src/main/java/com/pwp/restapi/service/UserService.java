package com.pwp.restapi.service;

import com.pwp.restapi.model.User;

public interface UserService {
	
	public User login(User u);
	public Boolean signup(User u);
	public Boolean checkUserExist(User u);
	public User getUserById(User u);
	
}

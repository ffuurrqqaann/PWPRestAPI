package com.pwp.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwp.restapi.dao.UserDao;
import com.pwp.restapi.model.User;


@Service("UserService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDAO;
	
	public void setUserDAO(UserDao userDAO) {
		this.userDAO = userDAO;
	}

	public User login(User u) {
		// TODO Auto-generated method stub
		User user = this.userDAO.authenticateUser(u);
		
		return user;
	}

	public Boolean signup(User u) {
		// TODO Auto-generated method stub
		Boolean isUserAlreadyExist = this.checkUserExist(u);
		
		if( isUserAlreadyExist ) {
			return false;
		} 
		
		Boolean isUserSignedUp = this.userDAO.addUser(u);
		
		return isUserSignedUp;
	}

	public Boolean checkUserExist(User u) {
		// TODO Auto-generated method stub
		User user = this.userDAO.checkUserExist(u);
		
		if( user!=null ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public User getUserById( User u ) {
		
		//getting user from user dao by user id.
		User user = this.userDAO.getUser(u);
		
		return user;
	}
	
}
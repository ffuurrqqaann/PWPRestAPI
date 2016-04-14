package com.pwp.restapi.dao;

import java.util.List;

import com.pwp.restapi.model.Stringsets;
import com.pwp.restapi.model.User;

public interface UserDao {
	 
    public Boolean addUser(User u);
    public User getUser(User u);
    public User authenticateUser(User u);
    public User checkUserExist(User u);
    
}
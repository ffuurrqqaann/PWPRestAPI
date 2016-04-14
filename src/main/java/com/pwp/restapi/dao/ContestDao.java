package com.pwp.restapi.dao;

import java.util.List;

import com.pwp.restapi.model.Contest;
import com.pwp.restapi.model.Stringsets;
import com.pwp.restapi.model.User;

public interface ContestDao {
	 
    public Boolean addContest(Contest c, int userId);
    public List<Contest> getAllContests();
    
}
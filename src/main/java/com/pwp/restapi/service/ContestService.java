package com.pwp.restapi.service;

import java.util.List;

import com.pwp.restapi.model.Category;
import com.pwp.restapi.model.Contest;
import com.pwp.restapi.model.User;

public interface ContestService {

	public Boolean createContest(Contest contest, int user);
	public List<Contest> getContestList();
	public Contest getContestById(Contest c);
	public List<Contest> getContestByCategoryId(Category c);
	
}

package com.pwp.restapi.service;

import java.util.List;

import com.pwp.restapi.model.Contest;

public interface ContestService {

	public Boolean createContest(Contest contest, int user);
	public List<Contest> getContestList();
	
}

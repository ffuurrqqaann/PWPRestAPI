package com.pwp.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwp.restapi.dao.ContestDao;
import com.pwp.restapi.dao.UserDao;
import com.pwp.restapi.model.Contest;

@Service("ContestService")
public class ContestServiceImpl implements ContestService {
	
	@Autowired
	private ContestDao contestDAO;
	
	public void setContestDAO(ContestDao contestDAO) {
		this.contestDAO = contestDAO;
	}
	
	public Boolean createContest(Contest contest, int userId) {
		// TODO Auto-generated method stub
		Boolean isContestAdded = this.contestDAO.addContest(contest, userId);
		
		return isContestAdded;
	}

	public List<Contest> getContestList() {
		// TODO Auto-generated method stub
		List<Contest> contests = this.contestDAO.getAllContests();
		
		return contests;
	}
}
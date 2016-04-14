package com.pwp.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwp.restapi.dao.UserDao;
import com.pwp.restapi.dao.VoteDao;
import com.pwp.restapi.model.Contest;
import com.pwp.restapi.model.Vote;

@Service("VoteService")
public class VoteServiceImpl implements VoteService {
	
	@Autowired
	private VoteDao voteDAO;
	
	public void setVoteDAO(VoteDao voteDAO) {
		this.voteDAO = voteDAO;
	}
	
	public Boolean submitVote(Vote vote, int userId, int contestId) {
		// TODO Auto-generated method stub
		Boolean isVoteAdded = this.voteDAO.addVote(vote, userId, contestId);
		
		return isVoteAdded;
	}

	public List<Vote> voteList(int contestId) {
		// TODO Auto-generated method stub
		List<Vote> votes = this.voteDAO.getAllVotes(contestId);
		
		return votes;
	}
}
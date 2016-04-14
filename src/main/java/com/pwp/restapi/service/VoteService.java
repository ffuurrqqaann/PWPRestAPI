package com.pwp.restapi.service;

import java.util.List;

import com.pwp.restapi.model.Contest;
import com.pwp.restapi.model.Vote;

public interface VoteService {	
	public Boolean submitVote(Vote vote, int userId, int contestId);
	public List<Vote> voteList(int contestId);
}

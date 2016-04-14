package com.pwp.restapi.dao;

import java.util.List;

import com.pwp.restapi.model.Contest;
import com.pwp.restapi.model.Stringsets;
import com.pwp.restapi.model.User;
import com.pwp.restapi.model.Vote;

public interface VoteDao {
	 
    public Boolean addVote(Vote v, int userId, int contestId);
    public List<Vote> getAllVotes(int contestId);
    
}
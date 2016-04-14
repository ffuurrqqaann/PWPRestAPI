package com.comag10.crowdflower;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pwp.restapi.model.Vote;
import com.pwp.restapi.service.VoteService;

@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class VoteTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private VoteService voteService;
	
	@Test()
	public void addUserVoteTest() {
		String message = "";
		
		System.out.println( "Current Execution addUserVoteTest()" );
		System.out.println( "Executing addUserVoteTest() to allow user to submit a new vote." );
		
		Vote vote = new Vote();
		vote.setRating(1);
		
		Boolean isVoteSubmitted = this.voteService.submitVote(vote, 1, 1);
		
		if(isVoteSubmitted)
			message = "Submitted.";
		else
			message = "Not Submitted.";
		
		Assert.assertEquals("Submitted.", message);
	}
	
	@Test()
	public void getUserVotesListTest() {
		System.out.println( "Current Execution getUserVotesListTest()" );
		System.out.println( "Executing getUserVotesListTest() to return the contests that users have created." );
		
		System.out.println("Testing with Contest id 1");
		
		List<Vote> votes = this.voteService.voteList(1);
		
		System.out.println( "Comparing the size of the records returned from the vote database table." );
		Assert.assertEquals(1, votes.size());
	}
}
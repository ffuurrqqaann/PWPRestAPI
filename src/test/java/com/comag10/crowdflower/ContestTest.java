package com.comag10.crowdflower;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pwp.restapi.model.Contest;
import com.pwp.restapi.service.ContestService;

@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class ContestTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private ContestService contestService;

	@Test()
	public void getUserContestTest() {
		System.out.println( "Current Execution getUserContestTest()" );
		System.out.println( "Executing getUserContestTest() to return the contests that users have created." );
		
		List<Contest> contest = this.contestService.getContestList();
		
		System.out.println( "Comparing the size of the records returned from the contest database table." );
		Assert.assertEquals(1, contest.size());
	}
	
	@Test()
	public void addContestTest() {
		System.out.println( "Current Execution addContestTest()" );
		System.out.println( "Executing addContestTest() to create contest." );
		
		String userCreated = "";
		
		Contest contest = new Contest();
		
		contest.setTitle("testing title");
		contest.setDescription("testing description");
		contest.setStatus(1);
		
		Boolean isUserCreated = this.contestService.createContest(contest, 1);
		if(isUserCreated) {
			userCreated = "created";
		} else {
			userCreated = "Not created";
		}
		
		System.out.println( "user created is "+userCreated );
		
		Assert.assertEquals("created", userCreated);
	}
}
package com.pwp.restapi;

import java.util.List;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.pwp.restapi.model.Announcement;
import com.pwp.restapi.model.Contest;
import com.pwp.restapi.model.Setting;
import com.pwp.restapi.model.User;
import com.pwp.restapi.model.UserSetting;
import com.pwp.restapi.model.Vote;
import com.pwp.restapi.service.AnnouncementService;
import com.pwp.restapi.service.ContestService;
import com.pwp.restapi.service.UserService;
import com.pwp.restapi.service.UserSettingService;
import com.pwp.restapi.service.VoteService;

@Test
@DatabaseSetup(value="classpath:dataset.xml", type=com.github.springtestdbunit.annotation.DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value="classpath:dataset.xml", type=com.github.springtestdbunit.annotation.DatabaseOperation.DELETE_ALL)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
DbUnitTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
@Transactional
public class PsoasDatabaseTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private VoteService voteService;
	
	@Autowired
	private ContestService contestService;
	
	@Autowired
	private AnnouncementService announcementService;
	
	@Autowired
	private UserSettingService userSettingService;
	
	@Autowired
	private UserService userService;
		
	@Test
	public void testVoteList() {
		System.out.println("testing testContestList.");
		System.out.println("Comparing the size of the vote list in the database.");
		System.out.println("Contest id is 1.");
		System.out.println("Expected size of the list is 3.");
		System.out.println();
		System.out.println();
		System.out.println();
		
		List<Vote> vote = voteService.voteList(1);
		
		Assert.assertEquals(3, vote.size());
	}
	
	@Test
	public void testSubmitVote() {
		System.out.println("testing testSubmitVote.");
		System.out.println("Comparing the returned Boolean value inserted=true, exception=false.");
		System.out.println("user id for the vote is 1, contest id is 1 and the rating is 5");
		System.out.println();
		System.out.println();
		System.out.println();
		
		Vote vote = new Vote();
		
		//Contest object.
		Contest contest = new Contest();
		contest.setId(1);
		
		vote.setRating(5);
		
		Boolean isVoteSubmitted = voteService.submitVote(vote, 1, 1);
		
		Assert.assertEquals(true, isVoteSubmitted);
	}
	
	@Test
	public void testCreateContest() {
		System.out.println("testing testCreateContest.");
		System.out.println("Comparing the returned Boolean value inserted=true, exception=false.");
		
		System.out.println("user id for the contest is 1, category id is 1, status is 1");
		System.out.println();
		System.out.println();
		System.out.println();
		
		//Contest object.
		Contest contest = new Contest();
		
		contest.setCategory(1);
		contest.setDescription("testing description for the contest");
		contest.setStatus(1);
		contest.setTitle("testing title for the contest");
		
		Boolean isContestSubmitted = contestService.createContest(contest, 1);
		
		Assert.assertEquals(true, isContestSubmitted);
	}
	
	@Test
	public void testGetContestList() {
		System.out.println("testing testGetContestList.");
		System.out.println("Comparing the contest list size expected=2.");
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		List<Contest> contestList = contestService.getContestList();
		
		Assert.assertEquals(2, contestList.size());
	}
	
	@Test
	public void testDeleteContest() {
		System.out.println("testing testDeleteContest.");
		System.out.println("Comparing the contest list size expected=1.");
		System.out.println("Also Comparing the boolean value deleted=true, exception/notdeleted=false");
		
		System.out.println();
		System.out.println();
		System.out.println("Deleting the contest with id=2.");
		
		Contest contest = new Contest();
		contest.setId(2);
		
		Boolean isContestDeleted = contestService.deleteContestById(contest);
		
		List<Contest> contestList = contestService.getContestList();
		
		Assert.assertEquals(true, isContestDeleted);
		Assert.assertEquals(1, contestList.size());
	}
	
	@Test
	public void testDeleteAnnouncement() {
		System.out.println("testing testDeleteAnnouncement.");
		System.out.println("Comparing the announcement list size expected=1.");
		System.out.println("Also Comparing the boolean value deleted=true, exception/notdeleted=false");
		
		System.out.println();
		System.out.println();
		System.out.println("Deleting the announcement with id=1.");
		
		Announcement announcement = new Announcement();
		announcement.setId(1);
		
		Boolean isAnnouncementDeleted = announcementService.deleteAnnouncementById(announcement);
		
		List<Announcement> announcementsList = announcementService.announcementList();
		
		Assert.assertEquals(true, isAnnouncementDeleted);
		Assert.assertEquals(1, announcementsList.size());
	}
	
	@Test
	public void testGetAnnouncementList() {
		System.out.println("testing testGetAnnouncementList.");
		System.out.println("Comparing the announcement list size expected=2.");
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		List<Announcement> announcementsList = announcementService.announcementList();
		
		Assert.assertEquals(2, announcementsList.size());
	}
	
	@Test
	public void testUserSettingList() {
		System.out.println("testing testUserSettingList.");
		System.out.println("Comparing the user setting list size expected=2.");
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		User user = new User();
		user.setId(1);
		
		List<UserSetting> userSettingList = userSettingService.getUserSettings(user);
		
		Assert.assertEquals(2, userSettingList.size());
	}
	
	@Test
	public void testUpdateUserSetting() {
		System.out.println("testing testUpdateUserSetting.");
		System.out.println("update setting id 1 of user id 1 status to true/1");
		System.out.println("Checking the returned boolean value expected=true, exception=false.");
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		UserSetting userSetting = new UserSetting();
		
		//setting the user.
		User user = new User();
		user.setId(1);
		
		//preparing setting object.
		Setting setting = new Setting();
		setting.setId(1);
		
		userSetting.setUser(user);
		userSetting.setSetting(setting);
		userSetting.setStatus(1);
		
		Boolean isSettingUpdated = userSettingService.updateUserSetting(userSetting);
		
		Assert.assertEquals(true, isSettingUpdated);
	}
	
	
	@Test
	public void testUpdateUserEmailUpdate() {
		System.out.println("testing testUpdateUserEmailUpdate.");
		System.out.println("update user email from ahmedfurqan88@gmail.com to furqan@furqan.com");
		System.out.println("Checking the returned boolean value expected=true, exception=false.");
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		//setting the user.
		User user = new User();
		user.setId(1);
		user.setEmail("furqan@furqan.com");
		
		Boolean isEmailUpdated = userService.updateEmail(user);
		
		Assert.assertEquals(true, isEmailUpdated);
	}
	
}
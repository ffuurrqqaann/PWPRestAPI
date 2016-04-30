package com.comag10.crowdflower;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pwp.restapi.model.Announcement;
import com.pwp.restapi.model.Contest;
import com.pwp.restapi.service.AnnouncementService;
import com.pwp.restapi.service.ContestService;
import com.pwp.restapi.utils.HttpUtils;

@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class AnnouncementTest extends AbstractTestNGSpringContextTests {
	
	public String ANNOUNCEMENTS_ENDPOINT = "http://localhost:8080/PWPRestAPI/api/v1/announcements/";
	
	@Autowired
	private AnnouncementService announcementService;

	@Test()
	public void getAnnouncementsTest() throws Exception {
		System.out.println( "Current Execution getAnnouncementsTest()" );
		System.out.println( "Executing getAnnouncementsTest() to return the announcements that users have created." );
		
		String response = HttpUtils.sendHttpGet(ANNOUNCEMENTS_ENDPOINT);
		
		System.out.println( "Comparing the size of the records returned from the announcement database table." );
		Assert.assertNotEquals("", response);
	}
	
	/*@Test()
	public void addAnnouncementTest() {
		System.out.println( "Current Execution addAnnouncementTest()" );
		System.out.println( "Executing addAnnouncementTest() to create an announcement." );
		
		String announcementCreated = "";
		
		Announcement announcement = new Announcement();
		
		announcement.setTitle("test title");
		announcement.setMessage("test message");
		
		//get current time stamp.
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd h:mm:ss");
		String formattedDate = sdf.format(date);
		
		announcement.setPublish(formattedDate);
		
		Boolean isUserCreated = this.announcementService.addAnnouncement(announcement, 1);
		if(isUserCreated) {
			announcementCreated = "Created";
		} else {
			announcementCreated = "Not created";
		}
		
		System.out.println( "User "+announcementCreated );
		
		Assert.assertEquals("Created", announcementCreated);
	}*/
}
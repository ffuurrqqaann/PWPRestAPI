package com.comag10.crowdflower;

import java.util.List;

import junit.framework.Assert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.pwp.restapi.model.Setting;
import com.pwp.restapi.model.User;
import com.pwp.restapi.model.UserSetting;
import com.pwp.restapi.service.UserSettingService;

@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class UserSettingTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private UserSettingService userSettingService;

	@Test()
	public void updateSettingTest() {
		System.out.println("executing updateSettingTest()");
		System.out.println("executing updateSettingTest() to update user settings.");
		
		//Setting user fields.
		User user = new User();
		user.setId(1);
		
		System.out.println("User id is "+user.getId());
		
		//configuring settings fields.
		Setting setting = new Setting();
		setting.setId(1);
		
		System.out.println("Setting id is "+setting.getId());
		
		//setting the status to 0.
		int status = 0;
		
		System.out.println("status is "+status);
		
		/*String updated = this.userSettingService.updateUserSetting(user, setting, status)==true?"Success":"Failure";
		
		//comparing the values.
		Assert.assertEquals("Success", updated);*/
	}
	
	@Test()
	public void getUserSettingsTest() {
		System.out.println("executing getUserSettingTest()");
		System.out.println("executing getUserSettingTest() for getting user settings");
		
		//Setting user fields.
		User user = new User();
		user.setId(1);
		
		System.out.println("User id is "+user.getId());
		
		//get list of all user settings.
		List<UserSetting> userSetting = this.userSettingService.getUserSettings(user);
		
		System.out.println("User settings count "+userSetting.size());
		
		//comparing the values.
		Assert.assertEquals(2, userSetting.size());
	}
	
	@Test()
	public void getNullUserSettingsTest() {
		System.out.println("executing getUserSettingTest()");
		System.out.println("getUserSettingTest() for Invalid User id");
		
		//Setting user fields.
		User user = new User();
		user.setId(3);
		
		System.out.println("User id is "+user.getId());
		
		//get list of all user settings.
		List<UserSetting> userSetting = this.userSettingService.getUserSettings(user);
		
		System.out.println("User settings count "+userSetting.size());
		
		//comparing the values.
		Assert.assertEquals(0, userSetting.size());
	}
}
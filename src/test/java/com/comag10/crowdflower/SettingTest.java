package com.comag10.crowdflower;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pwp.restapi.model.Setting;
import com.pwp.restapi.service.SettingService;

@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class SettingTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SettingService settingService;

	@Test()
	public void getSettingsTest() {
		System.out.println( "Current Execution getSettingsTest()" );
		System.out.println( "Executing getSettingsTest() to return the contests that users have created." );
		
		List<Setting> setting = this.settingService.getSettingList();
		
		System.out.println( "Comparing the size of the records returned from the setting database table." );
		Assert.assertEquals(3, setting.size());
	}
	
	@Test()
	public void addSettingTest() {
		System.out.println( "Current Execution addSettingTest()" );
		System.out.println( "Executing addSettingTest() to create a setting." );
		
		String settingCreated = "";
		
		Setting setting = new Setting();
		setting.setSetting("test setting");
		
		Boolean isSettingCreated = this.settingService.addSetting(setting);
		if(isSettingCreated) {
			settingCreated = "Created";
		} else {
			settingCreated = "Not created";
		}
		
		System.out.println( "Setting "+settingCreated );
		
		Assert.assertEquals("Created", settingCreated);
	}
}
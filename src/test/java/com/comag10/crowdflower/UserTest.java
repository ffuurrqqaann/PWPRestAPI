package com.comag10.crowdflower;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pwp.restapi.model.Contest;
import com.pwp.restapi.model.User;
import com.pwp.restapi.model.UserSetting;
import com.pwp.restapi.service.UserService;

@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class UserTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private UserService userService;

	@Test()
	public void userLoginTest() {
		System.out.println( "Current Execution userLoginTest()" );
		System.out.println( "Executing userLoginTest() to get user by name and password." );
		
		User user = new User();
		
		user.setName("furqan");
		user.setPass_hash("df4358b3b8209d8123ac6384d02ef8d0");
		
		System.out.println( "Username is "+user.getName() );
		System.out.println( "Password is "+user.getPass_hash() );
		
		System.out.println( "Checking user Details." );
		User userDetails = userService.login(user);
		
		Assert.assertEquals(userDetails.getName(), "furqan");
		Assert.assertEquals(userDetails.getPass_hash(), "df4358b3b8209d8123ac6384d02ef8d0");
	}
	
	@Test()
	public void userExistTest() {
		System.out.println( "Current Execution userExistTest()" );
		System.out.println( "Executing userExistTest() to check if user already exist." );
		
		User user = new User();
		
		user.setName("furqan");
		user.setEmail("ahmedfurqan88@gmail.com");
		
		System.out.println( "Username is "+user.getName() );
		System.out.println( "Email is "+user.getEmail() );
		
		String userExist = this.userService.checkUserExist(user)==true?"Exist":"Does Not Exist";
		
		Assert.assertEquals("Exist", userExist);
	}
	
	@Test()
	public void userDoesNotExistTest() {
		System.out.println( "Current Execution userDoesNotExistTest()" );
		System.out.println( "Executing userDoesNotExistTest() to check if user does not exist." );
		
		User user = new User();
		
		user.setName("furqan+1");
		user.setEmail("ahmedfurqan88+1@gmail.com");
		
		System.out.println( "Username is "+user.getName() );
		System.out.println( "Email is "+user.getEmail() );
		
		String userExist = this.userService.checkUserExist(user)==true?"Exist":"Does Not Exist";
		
		Assert.assertEquals("Does Not Exist", userExist);
	}
	
	@Test()
	public void userSignUpTest() {
		System.out.println( "Current Execution userSignUpTest()" );
		System.out.println( "Executing userSignUpTest() to signup/create a new user in the database." );
		
		User user = new User();
		
		user.setName("furqan-ahmed");
		user.setEmail("ahmedfurqan88+2@gmail.com");
		user.setPass_hash("df4358b3b8209d8123ac6384d02ef8d0");
		user.setIs_active(1);
		
		System.out.println( "Username is "+user.getName() );
		System.out.println( "Email is "+user.getEmail() );
		System.out.println( "Pass Hash is "+user.getPass_hash() );
		System.out.println( "Isactive "+user.getIs_active() );
		
		String userSignedUp = this.userService.signup(user)==true?"Success":"Failure";
		
		Assert.assertEquals("Success", userSignedUp);
	}
}
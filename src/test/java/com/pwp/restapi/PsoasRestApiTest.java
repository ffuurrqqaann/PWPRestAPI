package com.pwp.restapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import net.hamnaberg.funclite.Optional;
import net.hamnaberg.json.Collection;
import net.hamnaberg.json.parser.CollectionParser;
import net.hamnaberg.json.Error;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.junit.runner.RunWith;
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

@Test
@DatabaseSetup(value="classpath:dataset.xml", type=com.github.springtestdbunit.annotation.DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value="classpath:dataset.xml", type=com.github.springtestdbunit.annotation.DatabaseOperation.DELETE_ALL)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
DbUnitTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
@Transactional
public class PsoasRestApiTest extends AbstractTestNGSpringContextTests {
			
	@Test(invocationCount=1, threadPoolSize=1)
	public void testGetAnnouncementCollection() {
		try {
			
			System.out.println("In testGetCollection");
			
			URL url = new URL(
					"http://10.20.209.187:8080/PWPRestAPI/api/v1/announcements/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			//set the sending type and receiving type to json
			connection.setRequestProperty("Content-Type", "application/vnd.collection+json");
			connection.setRequestProperty("Accept", "application/vnd.collection+json");

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			//connect to the server.
			System.out.println("Connecting to RestAPI server.");
			connection.connect();

			//checking response status code.
			int status = connection.getResponseCode();
			
			//Asserting the response.
			Assert.assertEquals(200, status);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
	
	@Test
	public void testGETAnnouncementMethodNotAllowed() {
		System.out.println("testing testGETAnnouncementMethodNotAllowed.");
		System.out.println("Comparing the returned http error code 405.");
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		try {
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/announcements/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			System.out.println("Connecting to RestAPI server.");

			//connect to the server.
			connection.connect();

			//checking response status code.
			int status = connection.getResponseCode();
			
			//comparing the response code.
			Assert.assertEquals(405, status);

		} catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}

	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testDeleteAnnouncement() {

		try {			
			System.out.println("In testDeleteAnnouncement.");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/announcement/1/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			System.out.println("Connecting to RestAPI server.");

			//connect to the server.
			connection.connect();

			//checking response status code.
			int status = connection.getResponseCode();

			//comparing the status code.
			Assert.assertEquals(200, status);

			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testDeleteAnnouncement405() {

		try {			
			System.out.println("In testDeleteAnnouncement405.");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/announcement/1/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			System.out.println("Connecting to RestAPI server.");

			//connect to the server.
			connection.connect();

			//checking response status code.
			int status = connection.getResponseCode();

			//comparing the status code.
			Assert.assertEquals(405, status);

			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}

	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testDeleteContest() {

		try {			
			System.out.println("In testDeleteContest.");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/category/1/contest/1/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			System.out.println("Connecting to RestAPI server.");

			//connect to the server.
			connection.connect();

			//checking response status code.
			int status = connection.getResponseCode();

			//comparing the status code.
			Assert.assertEquals(200, status);

			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testDeleteContest405() {

		try {			
			System.out.println("In testDeleteContest405.");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/category/1/contest/1/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			System.out.println("Connecting to RestAPI server.");

			//connect to the server.
			connection.connect();

			//checking response status code.
			int status = connection.getResponseCode();

			//comparing the status code.
			Assert.assertEquals(405, status);

			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}

	
	@Test
	public void testContestNotFound() {
		System.out.println("testing testContestNotFound.");
		System.out.println("Comparing the returned json code.");
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		try {
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/category/1/contest/10/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			System.out.println("Connecting to RestAPI server.");

			//connect to the server.
			connection.connect();

			//read the output.
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line + "\n");
			}

			//close the reader.
			bufferedReader.close();
			System.out.println("HTTP Client Received String : " + sb.toString());

			//return received string
			String response = sb.toString().trim();
			
			//get collection from the json.
			Collection collection = new CollectionParser().parse(response);
			
			//gettting the collection json error object.
			Optional<Error> error = collection.getError();
			
			//get the error code from collection json.
			String code = error.get().getCode();
			
			//comparing the error code.
			Assert.assertEquals("404", code);

		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}


	@Test
	public void testAnnouncementNotFound() {
		System.out.println("testing testAnnouncementNotFound.");
		System.out.println("Comparing the returned json message.");
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		try {
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/announcement/4/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			System.out.println("Connecting to RestAPI server.");

			//connect to the server.
			connection.connect();

			//read the output.
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line + "\n");
			}

			//close the reader.
			bufferedReader.close();
			System.out.println("HTTP Client Received String : " + sb.toString());

			//return received string
			String response = sb.toString().trim();
			
			//get collection from the json.
			Collection collection = new CollectionParser().parse(response);
			
			//gettting the collection json error object.
			Optional<Error> error = collection.getError();
			
			//get the error code from collection json.
			String code = error.get().getCode();
			
			//comparing the error code.
			Assert.assertEquals("404", code);

		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testAnnouncementMethodNotAllowed() {
		System.out.println("testing testMethodNotAllowed.");
		System.out.println("Comparing the returned http error code 405.");
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		try {
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/announcement/1/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			System.out.println("Connecting to RestAPI server.");

			//connect to the server.
			connection.connect();

			//checking response status code.
			int status = connection.getResponseCode();
			
			//comparing the response code.
			Assert.assertEquals(405, status);

		} catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testGetContestsCollection() {
		try {
			
			System.out.println("In testGetContestsCollection");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/category/1/contests/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			//set the sending type and receiving type to json
			connection.setRequestProperty("Content-Type", "application/vnd.collection+json");
			connection.setRequestProperty("Accept", "application/vnd.collection+json");

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			//connect to the server.
			System.out.println("Connecting to RestAPI server.");
			connection.connect();

			//checking response status code.
			int status = connection.getResponseCode();
			
			//Asserting the response.
			Assert.assertEquals(200, status);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testGetContestsCollection405() {
		try {
			
			System.out.println("In testGetContestsCollection405");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/category/1/contests/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");

			//set the sending type and receiving type to json
			connection.setRequestProperty("Content-Type", "application/vnd.collection+json");
			connection.setRequestProperty("Accept", "application/vnd.collection+json");

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			//connect to the server.
			System.out.println("Connecting to RestAPI server.");
			connection.connect();

			//checking response status code.
			int status = connection.getResponseCode();
			
			//Asserting the response.
			Assert.assertEquals(405, status);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}

	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testGetUserSettingsCollection() {
		try {
			
			System.out.println("In testGetUserSettingsCollection");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/user/1/settings/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			//set the sending type and receiving type to json
			connection.setRequestProperty("Content-Type", "application/vnd.collection+json");
			connection.setRequestProperty("Accept", "application/vnd.collection+json");

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			//connect to the server.
			System.out.println("Connecting to RestAPI server.");
			connection.connect();

			//checking response status code.
			int status = connection.getResponseCode();
			
			//Asserting the response.
			Assert.assertEquals(200, status);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testGetUserSettingsCollection405() {
		try {
			
			System.out.println("In testGetUserSettingsCollection405");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/user/1/settings/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");

			//set the sending type and receiving type to json
			connection.setRequestProperty("Content-Type", "application/vnd.collection+json");
			connection.setRequestProperty("Accept", "application/vnd.collection+json");

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			//connect to the server.
			System.out.println("Connecting to RestAPI server.");
			connection.connect();

			//checking response status code.
			int status = connection.getResponseCode();
			
			//Asserting the response.
			Assert.assertEquals(405, status);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}

	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testGetContestById() {
		try {
			
			System.out.println("In testGetContestById");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/category/1/contest/1/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			//set the sending type and receiving type to json
			connection.setRequestProperty("Content-Type", "application/vnd.collection+json");
			connection.setRequestProperty("Accept", "application/vnd.collection+json");

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			//connect to the server.
			System.out.println("Connecting to RestAPI server.");
			connection.connect();

			//checking response status code.
			int status = connection.getResponseCode();
			
			//Asserting the response.
			Assert.assertEquals(200, status);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testGetContestById405() {
		try {
			
			System.out.println("In testGetContestById405");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/category/1/contest/1/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");

			//set the sending type and receiving type to json
			connection.setRequestProperty("Content-Type", "application/vnd.collection+json");
			connection.setRequestProperty("Accept", "application/vnd.collection+json");

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			//connect to the server.
			System.out.println("Connecting to RestAPI server.");
			connection.connect();

			//checking response status code.
			int status = connection.getResponseCode();
			
			//Asserting the response.
			Assert.assertEquals(405, status);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}

	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testGetContestNotFound() {
		try {
			
			System.out.println("In testGetContestById");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/category/1/contest/6/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			//set the sending type and receiving type to json
			connection.setRequestProperty("Content-Type", "application/vnd.collection+json");
			connection.setRequestProperty("Accept", "application/vnd.collection+json");

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			//connect to the server.
			System.out.println("Connecting to RestAPI server.");
			connection.connect();

			//checking response status code.
			int status = connection.getResponseCode();
			
			if(status!=200) {
				throw new Exception("Response code = "+status);
			}
			
			//read the output.
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line + "\n");
			}

			//close the reader.
			bufferedReader.close();
			System.out.println("HTTP Client Received String : " + sb.toString());

			//return received string
			String response = sb.toString().trim();
			
			//get collection from the json.
			Collection collection = new CollectionParser().parse(response);
			
			//gettting the collection json error object.
			Optional<Error> error = collection.getError();
			
			//get the error code from collection json.
			String code = error.get().getCode();

			//Asserting the response.
			Assert.assertEquals("404", code);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testGetContestVotes() {
		try {
			System.out.println("In testGetContestVotes");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/contest/1/votes/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			//set the sending type and receiving type to json
			connection.setRequestProperty("Content-Type", "application/vnd.collection+json");
			connection.setRequestProperty("Accept", "application/vnd.collection+json");

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			//connect to the server.
			System.out.println("Connecting to RestAPI server.");
			connection.connect();

			//checking response status code.
			int status = connection.getResponseCode();
			
			if(status!=200) {
				throw new Exception("Response code = "+status);
			}
			
			//comparing the status code.
			Assert.assertEquals(200, status);
						
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testGetContestVotes405() {
		try {
			System.out.println("In testGetContestVotes405");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/contest/1/votes/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");

			//set the sending type and receiving type to json
			connection.setRequestProperty("Content-Type", "application/vnd.collection+json");
			connection.setRequestProperty("Accept", "application/vnd.collection+json");

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			//connect to the server.
			System.out.println("Connecting to RestAPI server.");
			connection.connect();

			//checking response status code.
			int status = connection.getResponseCode();
			
			//comparing the status code.
			Assert.assertEquals(405, status);
						
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}

	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testAddNewContest() {
		try {
			
			//add new contest json.
			String json = "{\"collection\":{\"version\":\"1.0\",\"href\":\"http://localhost:8080/PWPRestAPI/api/v1/user/1/email/\",\"template\":{\"data\":[{\"name\":\"title\",\"value\":\"test title\"},{\"name\":\"description\",\"value\":\"test description\"},{\"name\":\"category\",\"value\":\"1\"},{\"name\":\"creator\",\"value\":\"1\"},{\"name\":\"status\",\"value\":\"1\"},{\"name\":\"created\",\"value\":\"2016-03-20 17:33:28\"},{\"name\":\"updated\",\"value\":\"2016-03-20 17:33:28\"}]}}}";
			
			System.out.println("In testAddNewContest");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/category/1/contest/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(15000);
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			//post params.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("data", json));
			
			OutputStream os = connection.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(params, "contest"));
			writer.flush();
			writer.close();
			os.close();

			//connecting to api server.
			connection.connect();
			
			int status = connection.getResponseCode();
			
			if(status!=200) {
				throw new Exception("Response code = "+status);
			}
			
			//comparing the status code.
			Assert.assertEquals(200, status);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testAddNewContest405() {
		try {
			
			//add new contest json.
			String json = "{\"collection\":{\"version\":\"1.0\",\"href\":\"http://localhost:8080/PWPRestAPI/api/v1/user/1/email/\",\"template\":{\"data\":[{\"name\":\"description\",\"value\":\"test description\"},{\"name\":\"category\",\"value\":\"1\"},{\"name\":\"creator\",\"value\":\"1\"},{\"name\":\"status\",\"value\":\"1\"},{\"name\":\"created\",\"value\":\"2016-03-20 17:33:28\"},{\"name\":\"updated\",\"value\":\"2016-03-20 17:33:28\"}]}}}";
			
			System.out.println("In testAddNewContest405");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/category/1/contest/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(15000);
			connection.setRequestMethod("PUT");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			//post params.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("data", json));
			
			OutputStream os = connection.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(params, "contest"));
			writer.flush();
			writer.close();
			os.close();

			//connecting to api server.
			connection.connect();
			
			int status = connection.getResponseCode();
			
			//comparing the status code.
			Assert.assertEquals(405, status);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}

	@Test(invocationCount=1, threadPoolSize=1)
	public void testAddNewAnnouncement() {
		try {
			
			//add new contest json.
			String json = "{\"collection\":{\"version\":\"1.0\",\"href\":\"http://localhost:8080/PWPRestAPI/api/v1/user/1/email/\",\"template\":{\"data\":[{\"name\":\"title\",\"value\":\"test title\"},{\"name\":\"message\",\"value\":\"test message\"},{\"name\":\"announcer\",\"value\":\"1\"},{\"name\":\"publish\",\"value\":\"2016-03-20 17:33:28\"},{\"name\":\"created\",\"value\":\"2016-03-20 17:33:28\"},{\"name\":\"updated\",\"value\":\"2016-03-20 17:33:28\"}]}}}";
			
			System.out.println("In testAddNewAnnouncement");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/announcement/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(15000);
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			//post params.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("data", json));
			
			OutputStream os = connection.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(params, "announcement"));
			writer.flush();
			writer.close();
			os.close();

			//connecting to api server.
			connection.connect();
			
			int status = connection.getResponseCode();
			
			if(status!=200) {
				throw new Exception("Response code = "+status);
			}
			
			//comparing the status code.
			Assert.assertEquals(200, status);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testAddNewAnnouncement405() {
		try {
			
			//add new contest json.
			String json = "{\"collection\":{\"version\":\"1.0\",\"href\":\"http://localhost:8080/PWPRestAPI/api/v1/user/1/email/\",\"template\":{\"data\":[{\"name\":\"title\",\"value\":\"test title\"},{\"name\":\"message\",\"value\":\"test message\"},{\"name\":\"announcer\",\"value\":\"1\"},{\"name\":\"publish\",\"value\":\"2016-03-20 17:33:28\"},{\"name\":\"created\",\"value\":\"2016-03-20 17:33:28\"},{\"name\":\"updated\",\"value\":\"2016-03-20 17:33:28\"}]}}}";
			
			System.out.println("In testAddNewAnnouncement405");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/announcement/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(15000);
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			//post params.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("data", json));
			
			OutputStream os = connection.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(params, "announcement"));
			writer.flush();
			writer.close();
			os.close();

			//connecting to api server.
			connection.connect();
			
			int status = connection.getResponseCode();
			
			//comparing the status code.
			Assert.assertEquals(200, status);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}

	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testAddNewVote() {
		try {
			
			//add new contest json.
			String json = "{\"collection\":{\"version\":\"1.0\",\"href\":\"http://localhost:8080/PWPRestAPI/api/v1/user/1/email/\",\"template\":{\"data\":[{\"name\":\"user_id\",\"value\":\"1\"},{\"name\":\"contest\",\"value\":\"1\"},{\"name\":\"rating\",\"value\":\"1\"},{\"name\":\"created\",\"value\":\"2016-03-20 17:33:28\"},{\"name\":\"updated\",\"value\":\"2016-03-20 17:33:28\"}]}}}";
			
			System.out.println("In testAddNewVote");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/category/1/contest/1/vote/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(15000);
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			//post params.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("data", json));
			
			OutputStream os = connection.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(params, "vote"));
			writer.flush();
			writer.close();
			os.close();

			//connecting to api server.
			connection.connect();
			
			int status = connection.getResponseCode();
			
			if(status!=200) {
				throw new Exception("Response code = "+status);
			}
			
			//comparing the status code.
			Assert.assertEquals(200, status);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
	
	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testAddNewVote405() {
		try {
			
			//add new contest json.
			String json = "{\"collection\":{\"version\":\"1.0\",\"href\":\"http://localhost:8080/PWPRestAPI/api/v1/user/1/email/\",\"template\":{\"data\":[{\"name\":\"user_id\",\"value\":\"1\"},{\"name\":\"contest\",\"value\":\"1\"},{\"name\":\"rating\",\"value\":\"1\"},{\"name\":\"created\",\"value\":\"2016-03-20 17:33:28\"},{\"name\":\"updated\",\"value\":\"2016-03-20 17:33:28\"}]}}}";
			
			System.out.println("In testAddNewVote405");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/category/1/contest/1/vote/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(15000);
			connection.setRequestMethod("PUT");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			//post params.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("data", json));
			
			OutputStream os = connection.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(params, "vote"));
			writer.flush();
			writer.close();
			os.close();

			//connecting to api server.
			connection.connect();
			
			int status = connection.getResponseCode();
			
			//comparing the status code.
			Assert.assertEquals(405, status);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}

	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testUpdateEmail() {
		try {
			
			//add new contest json.
			String json = "{\"collection\":{\"version\":\"1.0\",\"href\":\"http://localhost:8080/PWPRestAPI/api/v1/user/1/email/\",\"template\":{\"data\":[{\"name\":\"email\",\"value\":\"furqan@furqan.com\"}]}}}";
			
			System.out.println("In testAddNewVote");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/user/1/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(15000);
			connection.setRequestMethod("PUT");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			//post params.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("data", json));
			
			OutputStream os = connection.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(params, "vote"));
			writer.flush();
			writer.close();
			os.close();

			//connecting to api server.
			connection.connect();
			
			int status = connection.getResponseCode();
			
			if(status!=200) {
				throw new Exception("Response code = "+status);
			}
			
			//comparing the status code.
			Assert.assertEquals(200, status);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testUpdateEmail405() {
		try {
			
			//add new contest json.
			String json = "{\"collection\":{\"version\":\"1.0\",\"href\":\"http://localhost:8080/PWPRestAPI/api/v1/user/1/email/\",\"template\":{\"data\":[{\"name\":\"email\",\"value\":\"furqan@furqan.com\"}]}}}";
			
			System.out.println("In testAddNewVote405");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/user/1/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(15000);
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			//post params.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("data", json));
			
			OutputStream os = connection.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(params, "vote"));
			writer.flush();
			writer.close();
			os.close();

			//connecting to api server.
			connection.connect();
			
			int status = connection.getResponseCode();
			
			//comparing the status code.
			Assert.assertEquals(405, status);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}

	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testUpdateSettings() {
		try {
			
			//add new contest json.
			String json = "{\"collection\":{\"version\":\"1.0\",\"href\":\"http://localhost:8080/PWPRestAPI/api/v1/user/1/email/\",\"template\":{\"data\":[{\"name\":\"user_id\",\"value\":\"1\"},{\"name\":\"setting\",\"value\":\"1\"},{\"name\":\"status\",\"value\":\"1\"}]}}}";
			
			System.out.println("In testUpdateSettings");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/user/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(15000);
			connection.setRequestMethod("PUT");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			//post params.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("data", json));
			
			OutputStream os = connection.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(params, "vote"));
			writer.flush();
			writer.close();
			os.close();

			//connecting to api server.
			connection.connect();
			
			int status = connection.getResponseCode();
			
			if(status!=200) {
				throw new Exception("Response code = "+status);
			}
			
			//comparing the status code.
			Assert.assertEquals(200, status);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testUpdateSettings405() {
		try {
			
			//add new contest json.
			String json = "{\"collection\":{\"version\":\"1.0\",\"href\":\"http://localhost:8080/PWPRestAPI/api/v1/user/1/email/\",\"template\":{\"data\":[{\"name\":\"user_id\",\"value\":\"1\"},{\"name\":\"setting\",\"value\":\"1\"},{\"name\":\"status\",\"value\":\"1\"}]}}}";
			
			System.out.println("In testUpdateSettings405");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/user/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(15000);
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			//post params.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("data", json));
			
			OutputStream os = connection.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(params, "vote"));
			writer.flush();
			writer.close();
			os.close();

			//connecting to api server.
			connection.connect();
			
			int status = connection.getResponseCode();
			
			//comparing the status code.
			Assert.assertEquals(405, status);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}

	
	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testInvalidEmail() {
		try {
			
			//add new contest json.
			String json = "{\"collection\":{\"version\":\"1.0\",\"href\":\"http://localhost:8080/PWPRestAPI/api/v1/user/1/email/\",\"template\":{\"data\":[{\"name\":\"email\",\"value\":\"furqan\"}]}}}";
			
			System.out.println("In testInvalidEmail");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/user/1/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(15000);
			connection.setRequestMethod("PUT");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			//post params.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("data", json));
			
			OutputStream os = connection.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(params, "vote"));
			writer.flush();
			writer.close();
			os.close();

			//connecting to api server.
			connection.connect();
			
			int status = connection.getResponseCode();
			
			if(status!=200) {
				throw new Exception("Response code = "+status);
			}
			
			//read the output.
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line + "\n");
			}

			//close the reader.
			bufferedReader.close();
			System.out.println("HTTP Client Received String : " + sb.toString());

			//return received string
			String response = sb.toString().trim();
			
			//get collection from the json.
			Collection collection = new CollectionParser().parse(response);
			
			//gettting the collection json error object.
			Optional<Error> error = collection.getError();
			
			//get the error code from collection json.
			String code = error.get().getCode();
			
			//Asserting the response.
			Assert.assertEquals("400", code);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testAddAnnouncement400() {
		try {
			
			//add new contest json.
			String json = "{\"collection\":{\"version\":\"1.0\",\"href\":\"http://localhost:8080/PWPRestAPI/api/v1/user/1/email/\",\"template\":{\"data\":[{\"name\":\"message\",\"value\":\"test message\"}, {\"name\":\"announcer\",\"value\":\"1\"}, {\"name\":\"publish\",\"value\":\"2016-05-18 17:24:49\"}, {\"name\":\"created\",\"value\":\"2016-05-18 17:24:49\"}, {\"name\":\"updated\",\"value\":\"2016-05-18 17:24:49\"}]}}}";
			
			System.out.println("In testAddAnnouncement400");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/announcement/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(15000);
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			//post params.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("data", json));
			
			OutputStream os = connection.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(params, "announcement"));
			writer.flush();
			writer.close();
			os.close();

			//connecting to api server.
			connection.connect();
			
			int status = connection.getResponseCode();
			
			if(status!=200) {
				throw new Exception("Response code = "+status);
			}
			
			//read the output.
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line + "\n");
			}

			//close the reader.
			bufferedReader.close();
			System.out.println("HTTP Client Received String : " + sb.toString());

			//return received string
			String response = sb.toString().trim();
			
			//get collection from the json.
			Collection collection = new CollectionParser().parse(response);
			
			//gettting the collection json error object.
			Optional<Error> error = collection.getError();
			
			//get the error code from collection json.
			String code = error.get().getCode();
			
			//Asserting the response.
			Assert.assertEquals("400", code);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testAddContest400() {
		try {
			
			//add new contest json.
			String json = "{\"collection\":{\"version\":\"1.0\",\"href\":\"http://localhost:8080/PWPRestAPI/api/v1/user/1/email/\",\"template\":{\"data\":[{\"name\":\"title\",\"value\":\"test title\"}, {\"name\":\"category\",\"value\":\"1\"}, {\"name\":\"creator\",\"value\":\"1\"}, {\"name\":\"status\",\"value\":\"1\"}, {\"name\":\"created\",\"value\":\"2016-05-18 17:24:49\"}, {\"name\":\"updated\",\"value\":\"2016-05-18 17:24:49\"}]}}}";
			
			System.out.println("In testAddContest400");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/category/1/contest/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(15000);
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			//post params.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("data", json));
			
			OutputStream os = connection.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(params, "announcement"));
			writer.flush();
			writer.close();
			os.close();

			//connecting to api server.
			connection.connect();
			
			int status = connection.getResponseCode();
			
			if(status!=200) {
				throw new Exception("Response code = "+status);
			}
			
			//read the output.
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line + "\n");
			}

			//close the reader.
			bufferedReader.close();
			System.out.println("HTTP Client Received String : " + sb.toString());

			//return received string
			String response = sb.toString().trim();
			
			//get collection from the json.
			Collection collection = new CollectionParser().parse(response);
			
			//gettting the collection json error object.
			Optional<Error> error = collection.getError();
			
			//get the error code from collection json.
			String code = error.get().getCode();
			
			//Asserting the response.
			Assert.assertEquals("400", code);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testAddVote400() {
		try {
			
			//add new contest json.
			String json = "{\"collection\":{\"version\":\"1.0\",\"href\":\"http://localhost:8080/PWPRestAPI/api/v1/user/1/email/\",\"template\":{\"data\":[{\"name\":\"user_id\",\"value\":\"1\"}, {\"name\":\"contest\",\"value\":\"1\"}, {\"name\":\"created\",\"value\":\"2016-05-18 17:24:49\"}, {\"name\":\"updated\",\"value\":\"2016-05-18 17:24:49\"}]}}}";
			
			System.out.println("In testAddVote400");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/category/1/contest/1/vote/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(15000);
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			//post params.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("data", json));
			
			OutputStream os = connection.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(params, "announcement"));
			writer.flush();
			writer.close();
			os.close();

			//connecting to api server.
			connection.connect();
			
			int status = connection.getResponseCode();
			
			if(status!=200) {
				throw new Exception("Response code = "+status);
			}
			
			//read the output.
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line + "\n");
			}

			//close the reader.
			bufferedReader.close();
			System.out.println("HTTP Client Received String : " + sb.toString());

			//return received string
			String response = sb.toString().trim();
			
			//get collection from the json.
			Collection collection = new CollectionParser().parse(response);
			
			//gettting the collection json error object.
			Optional<Error> error = collection.getError();
			
			//get the error code from collection json.
			String code = error.get().getCode();
			
			//Asserting the response.
			Assert.assertEquals("400", code);
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}

	private String getQuery(List<NameValuePair> params, String resource)
	{
	    StringBuilder result = new StringBuilder();
	    
	    try {
	    	boolean first = true;

		    for (NameValuePair pair : params)
		    {
		        if (first)
		            first = false;
		        else
		            result.append("&");

		        result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
		        result.append("=");
		        result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
		    }

	    } catch(Exception e) {
	    	System.out.println("In resource "+resource+" "+e.toString());
	    }
	    
	    return result.toString();
	}
}
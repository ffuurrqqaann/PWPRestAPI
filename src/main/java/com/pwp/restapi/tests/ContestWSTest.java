package com.pwp.restapi.tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContestWSTest {

	@Test(invocationCount=1, threadPoolSize=1)
	public void testAddNewContest() {
		try {
			System.out.println("In testAddNewContest.");
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/category/1/contest/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");

			String json = "{ \"collection\" :{\"version\" : \"1.0\",\"href\" : \"http://localhost:8080/PWPRestAPI/api/v1/user/\",\"template\" : {\"data\" : [{\"name\" : \"title\", \"value\" : \"test title\"},{\"name\" : \"description\", \"value\" : \"testdescription\"}, ,{\"name\" : \"category\", \"value\" : \"1\"},{\"name\" : \"creator\", \"value\" : \"1\"},{\"name\" : \"status\", \"value\" : \"1\"},{\"name\" : \"created\", \"value\" : \"2016-05-18 12:15:55\"},{\"name\" : \"updated\", \"value\" : \"2016-05-18 12:15:55\"} ]}}}";

			//set the sending type and receiving type to json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			connection.setRequestProperty("Content-length", json.getBytes().length + "");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			//send the json as body of the request
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(json.getBytes("UTF-8"));
			outputStream.close();

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

			//comparing the response.
			Assert.assertEquals(response, "New Contest has been Added.");

			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}

	@Test(invocationCount=1, threadPoolSize=1)
	public void testGetAllContests() {
		try {

			System.out.println("In testGetAllContests.");

			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/category/1/contests/");

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			//set the sending type and receiving type to json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

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

			//parsing json.
			JSONObject json = new JSONObject(response);

			//get collection from json.
			JSONObject collection = json.getJSONObject("collection");

			//get collection items.
			JSONArray items = collection.getJSONArray("items");

			//get items count.
			int count = items.length();

			//comparing the response.
			Assert.assertEquals(count, 2);

			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}

	@Test(invocationCount=1, threadPoolSize=1)
	public void testContestNotFound() {
		try {

			System.out.println("In testContestNotFound.");

			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/category/1/contest/6");

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");
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

			//comparing the response.
			Assert.assertEquals(response, "Contest not found");

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
					"http://localhost:8080/PWPRestAPI/api/v1/category/1/contest/5");
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

			//comparing the response.
			Assert.assertEquals(response, "Contest has been deleted successfully.");

			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}

	/*@Test(invocationCount=1, threadPoolSize=1)
	public void testGetContestVotes() {

	}

	@Test(invocationCount=1, threadPoolSize=1)
	public void testContestNotFound() {

	}

	@Test(invocationCount=1, threadPoolSize=1)
	public void testDeleteContest() {

	}*/

}

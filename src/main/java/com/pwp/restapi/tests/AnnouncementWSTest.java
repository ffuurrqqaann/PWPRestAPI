package com.pwp.restapi.tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AnnouncementWSTest {

	@Test(invocationCount=1, threadPoolSize=1)
	public void testGetCollection() {
		try {
			
			System.out.println("In testGetCollection");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/announcements/");
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
			Assert.assertEquals(count, 1);

			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}


	@Test(invocationCount=1, threadPoolSize=1)
	public void testDeleteAnnouncement() {

		try {
			
			System.out.println("In testDeleteAnnouncement.");
			
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
			Assert.assertEquals(response, "Announcement has been deleted successfully.");

			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
	
	@Test(invocationCount=1, threadPoolSize=1)
	public void testAnnouncementIdNotFound() {

		try {
			
			System.out.println("In testAnnouncementIdNotFound.");
			
			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/announcement/4/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");

			//set the sending type and receiving type to json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
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
			Assert.assertEquals(response, "Announcement not found");

			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}

	/*@Test(invocationCount=1, threadPoolSize=1)
	public void testEmailNotValid() {

		try {

			URL url = new URL(
					"http://localhost:8080/PWPRestAPI/api/v1/user/1/furqan@furqa/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("PUT");

			String json = "data=furqan@furqan.com";

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
			Assert.assertEquals(response, "Email is not valid.");

			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
*/	
}
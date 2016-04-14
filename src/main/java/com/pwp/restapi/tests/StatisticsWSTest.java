package com.pwp.restapi.tests;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.pwp.restapi.ui.model.Statistics;

public class StatisticsWSTest {

	@Test(invocationCount=100, threadPoolSize=5)
	public void loadTestStatistics() {

		try {
			URL url = new URL(
					"http://localhost:8080/FitnessTracker/api/statistics/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("HTTP error code : "+ conn.getResponseCode());
			}

			Scanner scan = new Scanner(url.openStream());
			String entireResponse = new String();
			while (scan.hasNext())
				entireResponse += scan.nextLine();

			System.out.println("Response :" +entireResponse);

			scan.close();

			Gson gson = new Gson();
			
			Statistics stats = gson.fromJson(entireResponse, Statistics.class);
			
			int StringCount = Integer.parseInt(stats.getStringCount());
			int minimum 	= Integer.parseInt(stats.getMinimum());
			int maximum		= Integer.parseInt(stats.getMaximum());
			int average		= Integer.parseInt(stats.getAverage());
			int median	 	= Integer.parseInt(stats.getMedian());
			
			System.out.println( "response is "+entireResponse );
			
			if( StringCount==5 && minimum==4 && maximum==10 && average==6 && median==7 ) {
				System.out.println("The result has been matched");
			} else {
				System.out.println("The result has not been matched");
			}
			
			Thread.sleep(2000);


		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
}
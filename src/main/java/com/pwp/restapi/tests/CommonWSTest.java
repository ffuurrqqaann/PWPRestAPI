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
import com.pwp.restapi.ui.model.Common;
import com.pwp.restapi.ui.model.Exactly;
import com.pwp.restapi.ui.model.Intersection;
import com.pwp.restapi.ui.model.Longest;
import com.pwp.restapi.ui.model.Statistics;

public class CommonWSTest {

	@Test(invocationCount=100, threadPoolSize=5)
	public void loadTestIntersection() {

		try {
			URL url = new URL(
					"http://localhost:8080/FitnessTracker/api/common");
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
			
			Common common = gson.fromJson(entireResponse, Common.class);
			
			System.out.println(common.getCommon().get(0));
			System.out.println(common.getCommon().get(1));
			
			Assert.assertEquals("furqan", common.getCommon().get(0));
			Assert.assertEquals("furqan1", common.getCommon().get(1));
			
			Thread.sleep(2000);
		} catch( Exception e ) {
			System.out.println(e.toString());
		}
	}
}
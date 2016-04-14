package com.pwp.restapi.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pwp.restapi.model.*;
import com.pwp.restapi.service.AnnouncementService;
import com.pwp.restapi.service.ContestService;
import com.pwp.restapi.utils.JsonUtil;
import com.pwp.restapi.utils.UrlUtil;


/**
 * PWP RestAPI Implementation.
 * 
 * @category 	Controller.
 * @package 	com.pwp.restapi.controller.
 * @author  	Furqan Ahmed <ahmedfurqan88@gmail.com>
 * @license     http://opensource.org/licenses/osl-3.0.php  Open Software License (OSL 3.0)
 * */

@Controller
public class PsoasController {

	private AnnouncementService announcementService;
	
	//announcements list endpoint.
	private String announcementsEndpoint = "/announcements";
	
	@Autowired(required=true)
	@Qualifier(value="AnnouncementService")
	public void setAnnouncementService(AnnouncementService announcementService) {
		this.announcementService = announcementService;
	}
	
	/**
	 * @author	furqan
	 * @param	Model model, HttpServletRequest request
	 * @return	String
	 */
	@RequestMapping(value="/announcements/", method=RequestMethod.GET)
	@ResponseBody
	public String getAllAnnouncements (Model model, HttpServletRequest request) {

		List<Announcement> announcementList = this.announcementService.announcementList();
		
		//getting application url from url utilities file.
		String appUrl = UrlUtil.getApplicationUrl(request);

		//api version.
		String version = "\"".concat(UrlUtil.apiVersionNumber).concat("\"");

		//endpoint url.
		String href=appUrl;
		href = href.concat(this.announcementsEndpoint);

		//endpoint related links.
		Map<String, String> links = new HashMap<String, String>();

		links.put("ref", "google.com");
		links.put("ref1", "google.com1");
		links.put("ref2", "google.com2");

		//endpoint items hrefs.
		List<String> itemsHref = new ArrayList<String>();

		itemsHref.add("www.google.com");
		itemsHref.add("www.google.com1");
		itemsHref.add("www.google.com2");
		itemsHref.add("www.google.com3");

		//preparing items data.
		List<Map<String, String>> itemsData = new ArrayList<Map<String,String>>();

		//data1
		Map<String, String> itemData1 = new HashMap<String, String>();

		itemData1.put("field1", "value1");
		itemData1.put("field2", "value2");
		itemData1.put("field3", "value3");

		itemsData.add(itemData1);

		//data2
		Map<String, String> itemData2 = new HashMap<String, String>();

		itemData2.put("field1", "value1");
		itemData2.put("field2", "value2");
		itemData2.put("field3", "value3");

		itemsData.add(itemData2);

		//data3
		Map<String, String> itemData3 = new HashMap<String, String>();

		itemData3.put("field1", "value1");
		itemData3.put("field2", "value2");
		itemData3.put("field3", "value3");

		itemsData.add(itemData3);

		//data4
		Map<String, String> itemData4 = new HashMap<String, String>();

		itemData4.put("field1", "value1");
		itemData4.put("field2", "value2");
		itemData4.put("field3", "value3");

		itemsData.add(itemData4);

		//preparing items links.
		List<Map<String, String>> itemslinks = new ArrayList<Map<String,String>>();

		//item link1.
		Map<String, String> itemLink1 = new HashMap<String, String>();

		itemLink1.put("field1", "value1");
		itemLink1.put("field2", "value2");
		itemLink1.put("field3", "value3");

		//item link2.
		Map<String, String> itemLink2 = new HashMap<String, String>();

		itemLink2.put("field1", "value1");
		itemLink2.put("field2", "value2");
		itemLink2.put("field3", "value3");

		//item link3.
		Map<String, String> itemLink3 = new HashMap<String, String>();

		itemLink3.put("field1", "value1");
		itemLink3.put("field2", "value2");
		itemLink3.put("field3", "value3");

		//item link4.
		Map<String, String> itemLink4 = new HashMap<String, String>();

		itemLink4.put("field1", "value1");
		itemLink4.put("field2", "value2");
		itemLink4.put("field3", "value3");

		itemslinks.add(itemLink1);
		itemslinks.add(itemLink2);
		itemslinks.add(itemLink3);
		itemslinks.add(itemLink4);

		//endpoint related template.
		Map<String, String> template = new HashMap<String, String>();

		template.put("key1", "value1");
		template.put("key2", "value1");
		template.put("key3", "value1");

		String resStr = JsonUtil.createCollectionJsonFormat(version, href, null, itemsHref, itemsData, null, null, 4);
		//String resStr = JsonUtil.createCollectionJsonFormat(version, href, links, itemsHref, itemData, itemLinks, template);

		//return resStr;
		return resStr;
	}

}
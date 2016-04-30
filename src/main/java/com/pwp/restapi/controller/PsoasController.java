package com.pwp.restapi.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.hamnaberg.funclite.Optional;
import net.hamnaberg.json.Error;
import net.hamnaberg.json.*;
import net.hamnaberg.json.parser.CollectionParser;
import net.hamnaberg.funclite.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;
import com.pwp.restapi.model.*;
import com.pwp.restapi.service.AnnouncementService;
import com.pwp.restapi.service.CategoryService;
import com.pwp.restapi.service.ContestService;
import com.pwp.restapi.service.SettingService;
import com.pwp.restapi.service.UserService;
import com.pwp.restapi.service.UserSettingService;
import com.pwp.restapi.service.VoteService;
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

	private static final URI ANNOUNCEMENTS_URI = URI.create("http://localhost:8080/PWPRestAPI/api/v1/announcements");
	private static final URI UPDATESETTING_URI = URI.create("http://localhost:8080/PWPRestAPI/api/v1/updatesetting/");
	private static final URI CONTEST_URI = URI.create("http://localhost:8080/PWPRestAPI/api/v1/contest/");
	private static final URI CATEGORY_URI = URI.create("http://localhost:8080/PWPRestAPI/api/v1/category/");
	private static final URI CATEGORIES_URI = URI.create("http://localhost:8080/PWPRestAPI/api/v1/categories/");

	private AnnouncementService announcementService;
	private UserService userService;
	private ContestService contestService;
	private VoteService voteService;
	private SettingService settingService;
	private UserSettingService usersettingService;
	private CategoryService categoryService;

	//announcements list endpoint.
	private String announcementsEndpoint = "/announcements";

	//users list endpoint.
	private String usersEndpoint = "/users";

	//announcement endpoint.
	private String announcementEndpoint = "/announcement/";

	//user endpoint.
	private String userEndpoint = "/user/";

	//settings endpoint.
	private String settingsEndpoint = "/settings/";

	//setting endpoint.
	private String settingEndpoint = "/setting/";

	//user settings endpoint.
	private String userSettingsEndpoint = "/usersettings/";

	//user setting endpoint.
	private String userSettingEndpoint = "/usersetting/";

	//contest endpoint.
	private String contestEndpoint = "/contest/";
	
	//contests endpoint.
	private String contestsEndpoint = "/contests/";
	
	//categories endpoint.
	private String categoriesEndpoint = "/categories/";
	
	//category endpoint.
	private String categoryEndpoint = "/category/";
	
	//votes endpoint.
	private String votesEndpoint = "/votes/";
	
	//vote endpoint.
	private String voteEndpoint = "/vote/";
	
	//autowiring announcement service.
	@Autowired(required=true)
	@Qualifier(value="AnnouncementService")
	public void setAnnouncementService(AnnouncementService announcementService) {
		this.announcementService = announcementService;
	}

	//autowiring user service.
	@Autowired(required=true)
	@Qualifier(value="UserService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	//autowiring contest service.
	@Autowired(required=true)
	@Qualifier(value="ContestService")
	public void setContestService(ContestService contestService) {
		this.contestService = contestService;
	}

	//autowiring vote service.
	@Autowired(required=true)
	@Qualifier(value="VoteService")
	public void setVoteService(VoteService voteService) {
		this.voteService = voteService;
	}

	//autowiring setting service.
	@Autowired(required=true)
	@Qualifier(value="SettingService")
	public void setSettingService(SettingService settingService) {
		this.settingService = settingService;
	}

	//autowiring setting service.
	@Autowired(required=true)
	@Qualifier(value="UserSettingService")
	public void setUsersettingService(UserSettingService usersettingService) {
		this.usersettingService = usersettingService;
	}

	//autowiring setting service.
	@Autowired(required=true)
	@Qualifier(value="CategoryService")
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * @author	furqan
	 * @param	Model model, HttpServletRequest request
	 * @return	String
	 */
	@RequestMapping(value="/announcements/", method=RequestMethod.GET)
	@ResponseBody
	public String getAllAnnouncements (Model model, HttpServletRequest request) {

		//announcements list.
		List<Announcement> announcementList = this.announcementService.announcementList();

		//Application url.
		String appUrl = UrlUtil.getApplicationUrl(request);

		//Rest Api version.
		String version = "\"".concat(UrlUtil.apiVersionNumber).concat("\"");

		//endpoint url.
		String href=appUrl.concat(this.announcementsEndpoint);

		//endpoint related links.
		Map<String, String> links = new HashMap<String, String>();

		//users complete url.
		String userLink = appUrl.concat(this.usersEndpoint);

		links.put("users", userLink);

		//endpoint items hrefs.
		List<String> itemsHref = new ArrayList<String>();

		//preparing data items.
		List<Map<String, String>> itemsData = new ArrayList<Map<String,String>>();

		//preparing items links.
		List<Map<String, String>> itemsLinks = new ArrayList<Map<String,String>>();

		for (Announcement announcement : announcementList) {

			//user rel link.
			String userRelLink = appUrl.concat(this.announcementEndpoint);
			userRelLink = userRelLink.concat(Integer.toString(announcement.getId()));

			itemsHref.add(userRelLink);

			//item's data.
			Map<String, String> itemData = new HashMap<String, String>();

			itemData.put("title", announcement.getTitle());
			itemData.put("message", announcement.getMessage());
			itemData.put("announcer", Integer.toString(announcement.getUser().getId()));
			itemData.put("publish", announcement.getPublish());
			itemData.put("created", announcement.getCreated());
			itemData.put("updated", announcement.getUpdated());	

			//Adding item data to item collection.
			itemsData.add(itemData);

			//item link.
			Map<String, String> itemLink = new HashMap<String, String>();

			String itemLinkHref = appUrl.concat(this.userEndpoint);
			itemLinkHref = itemLinkHref.concat(Integer.toString(announcement.getUser().getId()));

			itemLink.put("user", itemLinkHref);

			//Adding item link to itemLinks
			itemsLinks.add(itemLink);

		}

		//endpoint related template.
		Map<String, String> template = new HashMap<String, String>();

		template.put("title", "");
		template.put("message", "");
		template.put("announcer", "");
		template.put("publish", "");
		template.put("created", "");
		template.put("updated", "");	

		String resStr = JsonUtil.createCollectionJsonFormat(version, href, links, itemsHref, itemsData, itemsLinks, template, announcementList.size());

		//return resStr;
		return resStr;
	}

	/**
	 * @author	furqan
	 * @param	Model model, HttpServletRequest request, @PathVariable("id") userId
	 * @return	String
	 */
	@RequestMapping(value="/user/{id}/settings/", method=RequestMethod.GET)
	@ResponseBody
	public String getAllUserSettings (Model model, HttpServletRequest request, @PathVariable("id") String userId ) {


		//preparing usersetting's user object.
		User user = new User();
		user.setId(Integer.parseInt(userId));

		User usr = this.userService.getUserById(user);

		if( usr==null ) { //return 404 if user not found.
			String error = new Collection.Builder(UPDATESETTING_URI).withError(Error.create("Not Found",  "404", "User Id not found.")).build().asJson().toString();
			return error;
		}

		//announcements list.
		List<UserSetting> userSettingList = this.usersettingService.getUserSettings(usr);

		//Application url.
		String appUrl = UrlUtil.getApplicationUrl(request);

		//Rest Api version.
		String version = "\"".concat(UrlUtil.apiVersionNumber).concat("\"");

		//endpoint url.
		String href=appUrl.concat(this.userSettingEndpoint);

		//endpoint related links.
		Map<String, String> links = new HashMap<String, String>();

		//users collection's link url.
		String userLink = appUrl.concat(this.usersEndpoint);
		links.put("users", userLink);

		//usersettings collection's link url. 
		String settingsLink = appUrl.concat(this.settingsEndpoint);
		links.put("settings", settingsLink);

		//endpoint items hrefs.
		List<String> itemsHref = new ArrayList<String>();

		//preparing data items.
		List<Map<String, String>> itemsData = new ArrayList<Map<String,String>>();

		//preparing items links.
		List<Map<String, String>> itemsLinks = new ArrayList<Map<String,String>>();

		for (UserSetting userSetting : userSettingList) {

			//user rel link.
			String userSettingRelLink = appUrl.concat(this.userSettingEndpoint);
			userSettingRelLink = userSettingRelLink.concat(Integer.toString(userSetting.getId()));

			itemsHref.add(userSettingRelLink);

			//item's data.
			Map<String, String> itemData = new HashMap<String, String>();

			itemData.put("user_id", Integer.toString(userSetting.getUser().getId()));
			itemData.put("setting", Integer.toString(userSetting.getSetting().getId()));
			itemData.put("status",  Integer.toString(userSetting.getStatus()));
			itemData.put("created", userSetting.getCreated());
			itemData.put("updated", userSetting.getUpdated());

			//Adding item data to item collection.
			itemsData.add(itemData);

			//item link.
			Map<String, String> itemLink = new HashMap<String, String>();

			//user item link.
			String itemLinkHref = appUrl.concat(this.userEndpoint);
			itemLinkHref = itemLinkHref.concat(Integer.toString(userSetting.getUser().getId()));
			itemLink.put("user", itemLinkHref);

			//setting item link.
			String settingItemLinkHref = appUrl.concat(this.settingEndpoint);
			settingItemLinkHref = settingItemLinkHref.concat(Integer.toString(userSetting.getSetting().getId()));
			itemLink.put("setting", settingItemLinkHref);

			//Adding item link to itemLinks
			itemsLinks.add(itemLink);

		}

		//endpoint related template.
		Map<String, String> template = new HashMap<String, String>();

		template.put("user_id", "");
		template.put("setting", "");
		template.put("status",  "");
		template.put("created", "");
		template.put("updated", "");

		//String resStr = JsonUtil.createCollectionJsonFormat(version, href, null, itemsHref, itemsData, null, null, 4);
		String resStr = JsonUtil.createCollectionJsonFormat(version, href, links, itemsHref, itemsData, itemsLinks, template, userSettingList.size());

		//return resStr;
		return resStr;
	}

	/**
	 * @author	furqan
	 * @param	Model model, HttpServletRequest request, @PathVariable("catId") categoryId, @PathVariable("id") contestId
	 * @return	String
	 */
	@RequestMapping(value="/category/{catId}/contest/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String getContestByCategory (Model model, HttpServletRequest request, @PathVariable("catId") String categoryId, @PathVariable("id") String contestId ) {

		//preparing category object.
		Category cat = new Category();
		cat.setId(Integer.parseInt(categoryId));

		Category category = this.categoryService.getCategoryById(cat);

		if( category==null ) { //return 404 if user not found.
			String error = new Collection.Builder(CATEGORY_URI).withError(Error.create("Not Found",  "404", "Category Id not found.")).build().asJson().toString();
			return error;
		}

		//preparing category's contest object.
		Contest c = new Contest();
		c.setId(Integer.parseInt(contestId));

		Contest contest = this.contestService.getContestById(c);
		
		if( contest==null ) { //return 404 if user not found.
			String error = new Collection.Builder(CONTEST_URI).withError(Error.create("Not Found",  "404", "Contest Id not found.")).build().asJson().toString();
			return error;
		}
		
		System.out.println( "user id is"+contest.getUser().getId() );
		
		//announcements list.
		List<Contest> contestList = new ArrayList<Contest>();
		contestList.add(contest);

		//Application url.
		String appUrl = UrlUtil.getApplicationUrl(request);

		//Rest Api version.
		String version = "\"".concat(UrlUtil.apiVersionNumber).concat("\"");

		//endpoint url.
		String href=appUrl.concat(this.contestEndpoint);

		//endpoint related links.
		Map<String, String> links = new HashMap<String, String>();

		//users collection's link url.
		String userLink = appUrl.concat(this.usersEndpoint);
		links.put("users", userLink);

		//usersettings collection's link url. 
		String categoriesLink = appUrl.concat(this.categoriesEndpoint);
		links.put("categories", categoriesLink);

		//endpoint items hrefs.
		List<String> itemsHref = new ArrayList<String>();

		//preparing data items.
		List<Map<String, String>> itemsData = new ArrayList<Map<String,String>>();

		//preparing items links.
		List<Map<String, String>> itemsLinks = new ArrayList<Map<String,String>>();

		for (Contest catContest : contestList) {
			//user rel link.
			String contestRelLink = appUrl.concat(this.contestEndpoint);
			contestRelLink = contestRelLink.concat(Integer.toString(catContest.getId()));

			itemsHref.add(contestRelLink);

			//item's data.
			Map<String, String> itemData = new HashMap<String, String>();

			itemData.put("title", catContest.getTitle());
			itemData.put("description", catContest.getDescription());
			itemData.put("category",  Integer.toString(catContest.getCategory()));
			itemData.put("creator", Integer.toString(catContest.getUser().getId()));
			itemData.put("status", Integer.toString(catContest.getStatus()));
			itemData.put("created", catContest.getCreated());
			itemData.put("updated", catContest.getUpdated());

			//Adding item data to item collection.
			itemsData.add(itemData);

			//item link.
			Map<String, String> itemLink = new HashMap<String, String>();

			//user item link.
			String itemUserLinkHref = appUrl.concat(this.userEndpoint);
			itemUserLinkHref = itemUserLinkHref.concat(Integer.toString(catContest.getUser().getId()));
			itemLink.put("user", itemUserLinkHref);

			//setting item link.
			String itemCategoryLinkHref = appUrl.concat(this.categoryEndpoint);
			itemCategoryLinkHref = itemCategoryLinkHref.concat(Integer.toString(catContest.getCategory()));
			itemLink.put("category", itemCategoryLinkHref);

			//Adding item link to itemLinks
			itemsLinks.add(itemLink);

		}

		//endpoint related template.
		Map<String, String> template = new HashMap<String, String>();

		template.put("title", "");
		template.put("description", "");
		template.put("category",  "");
		template.put("creator", "");
		template.put("status", "");
		template.put("created", "");
		template.put("updated", "");

		//String resStr = JsonUtil.createCollectionJsonFormat(version, href, null, itemsHref, itemsData, null, null, 4);
		String resStr = JsonUtil.createCollectionJsonFormat(version, href, links, itemsHref, itemsData, itemsLinks, template, contestList.size());

		//return resStr;
		return resStr;
	}

	/**
	 * @author	furqan
	 * @param	Model model, HttpServletRequest request, @PathVariable("id") contestId
	 * @return	String
	 */
	@RequestMapping(value="/contest/{id}/votes/", method=RequestMethod.GET)
	@ResponseBody
	public String getContestVotes (Model model, HttpServletRequest request, @PathVariable("id") String contestId ) {

		//preparing category's contest object.
		Contest c = new Contest();
		c.setId(Integer.parseInt(contestId));

		Contest contest = this.contestService.getContestById(c);
		
		if( contest==null ) { //return 404 if user not found.
			String error = new Collection.Builder(CONTEST_URI).withError(Error.create("Not Found",  "404", "Contest Id not found.")).build().asJson().toString();
			return error;
		}
		
		//votes list.
		List<Vote> voteList = this.voteService.voteList(c.getId());

		//Application url.
		String appUrl = UrlUtil.getApplicationUrl(request);

		//Rest Api version.
		String version = "\"".concat(UrlUtil.apiVersionNumber).concat("\"");

		//endpoint url.
		String href=appUrl.concat(this.contestEndpoint);

		//endpoint related links.
		Map<String, String> links = new HashMap<String, String>();

		//users collection's link url.
		String userLink = appUrl.concat(this.usersEndpoint);
		links.put("users", userLink);

		//usersettings collection's link url. 
		String categoriesLink = appUrl.concat(this.contestsEndpoint);
		links.put("contest", categoriesLink);

		//endpoint items hrefs.
		List<String> itemsHref = new ArrayList<String>();

		//preparing data items.
		List<Map<String, String>> itemsData = new ArrayList<Map<String,String>>();

		//preparing items links.
		List<Map<String, String>> itemsLinks = new ArrayList<Map<String,String>>();

		for (Vote vote : voteList) {
			
			//vote rel link.
			String voteRelLink = appUrl.concat(this.voteEndpoint);
			voteRelLink = voteRelLink.concat(Integer.toString(vote.getId()));

			itemsHref.add(voteRelLink);

			//item's data.
			Map<String, String> itemData = new HashMap<String, String>();

			itemData.put("user_id", Integer.toString(vote.getUser().getId()));
			itemData.put("contest", Integer.toString(vote.getContest().getId()));
			itemData.put("rating",  Integer.toString(vote.getRating()));
			itemData.put("created", vote.getCreated());
			itemData.put("updated", vote.getUpdated());

			//Adding item data to item collection.
			itemsData.add(itemData);

			//item link.
			Map<String, String> itemLink = new HashMap<String, String>();

			//user item link.
			String itemUserLinkHref = appUrl.concat(this.userEndpoint);
			itemUserLinkHref = itemUserLinkHref.concat(Integer.toString(vote.getUser().getId()));
			itemLink.put("user", itemUserLinkHref);

			//setting item link.
			String itemContestLinkHref = appUrl.concat(this.contestEndpoint);
			itemContestLinkHref = itemContestLinkHref.concat(Integer.toString(vote.getContest().getId()));
			itemLink.put("contest", itemContestLinkHref);

			//Adding item link to itemLinks
			itemsLinks.add(itemLink);

		}

		//endpoint related template.
		Map<String, String> template = new HashMap<String, String>();

		template.put("user_id", "");
		template.put("contest", "");
		template.put("rating",  "");
		template.put("created", "");
		template.put("updated", "");

		String resStr = JsonUtil.createCollectionJsonFormat(version, href, links, itemsHref, itemsData, itemsLinks, template, voteList.size());

		return resStr;
	}
	
	/**
	 * @author	furqan
	 * @param	Model model, HttpServletRequest request, @PathVariable("id") contestId
	 * @return	String
	 */
	@RequestMapping(value="/category/{id}/contests/", method=RequestMethod.GET)
	@ResponseBody
	public String getAllContestsByCategory (Model model, HttpServletRequest request, @PathVariable("id") String categoryId ) {

		//preparing category's contest object.
		Category c = new Category();
		c.setId(Integer.parseInt(categoryId));

		Category category = this.categoryService.getCategoryById(c);
		
		if( category==null ) { //return 404 if user not found.
			String error = new Collection.Builder(CATEGORY_URI).withError(Error.create("Not Found",  "404", "Category Id not found.")).build().asJson().toString();
			return error;
		}
		
		//contest list.
		List<Contest> contestList = this.contestService.getContestByCategoryId(category);

		//Application url.
		String appUrl = UrlUtil.getApplicationUrl(request);

		//Rest Api version.
		String version = "\"".concat(UrlUtil.apiVersionNumber).concat("\"");

		//endpoint url.
		String href=appUrl.concat(this.contestsEndpoint);

		//endpoint related links.
		Map<String, String> links = new HashMap<String, String>();

		//users collection's link url.
		String userLink = appUrl.concat(this.usersEndpoint);
		links.put("users", userLink);

		//usersettings collection's link url. 
		String categoriesLink = appUrl.concat(this.categoriesEndpoint);
		links.put("categories", categoriesLink);

		//endpoint items hrefs.
		List<String> itemsHref = new ArrayList<String>();

		//preparing data items.
		List<Map<String, String>> itemsData = new ArrayList<Map<String,String>>();

		//preparing items links.
		List<Map<String, String>> itemsLinks = new ArrayList<Map<String,String>>();

		for (Contest contest : contestList) {
			//vote rel link.
			String contestRelLink = appUrl.concat(this.contestEndpoint);
			contestRelLink = contestRelLink.concat(Integer.toString(contest.getId()));

			itemsHref.add(contestRelLink);

			//item's data.
			Map<String, String> itemData = new HashMap<String, String>();

			itemData.put("title", contest.getTitle());
			itemData.put("description", contest.getDescription());
			itemData.put("category",  Integer.toString(contest.getCategory()));
			itemData.put("creator", Integer.toString(contest.getUser().getId()));
			itemData.put("status", Integer.toString(contest.getStatus()));
			itemData.put("created", contest.getCreated());
			itemData.put("updated", contest.getUpdated());

			//Adding item data to item collection.
			itemsData.add(itemData);

			//item link.
			Map<String, String> itemLink = new HashMap<String, String>();

			//user item link.
			String itemUserLinkHref = appUrl.concat(this.userEndpoint);
			itemUserLinkHref = itemUserLinkHref.concat(Integer.toString(contest.getUser().getId()));
			itemLink.put("user", itemUserLinkHref);

			//setting item link.
			String itemCategoryLinkHref = appUrl.concat(this.categoryEndpoint);
			itemCategoryLinkHref = itemCategoryLinkHref.concat(Integer.toString(contest.getCategory()));
			itemLink.put("contest", itemCategoryLinkHref);

			//Adding item link to itemLinks
			itemsLinks.add(itemLink);
		}

		//endpoint related template.
		Map<String, String> template = new HashMap<String, String>();

		template.put("title", "");
		template.put("description", "");
		template.put("category",  "");
		template.put("creator", "");
		template.put("status", "");
		template.put("created", "");
		template.put("updated", "");

		String resStr = JsonUtil.createCollectionJsonFormat(version, href, links, itemsHref, itemsData, itemsLinks, template, contestList.size());

		return resStr;
	}

	
	/**
	 * @author	furqan
	 * @param	Model model, HttpServletRequest request, @RequestParam data
	 * @return	String
	 */
	@RequestMapping(value="/announcement/", method=RequestMethod.POST)
	@ResponseBody
	public String addNewAnnouncements (Model model, HttpServletRequest request, @RequestParam("data") String data) {

		//endpoint data param
		String cjData = data;

		//endpoint response.
		String response = "";

		//parsing the collection json.
		try {
			Collection collection = new CollectionParser().parse(cjData);

			//gettting the template part from json.
			Optional<Template> template = collection.getTemplate();

			//getting the data from the template as Map.
			Map<String, Property> dataMap = template.get().getData().getDataAsMap();

			//preparing announcement object.
			Announcement announcement = new Announcement();

			//announcement title
			announcement.setTitle(dataMap.get("title").getValue().get().asString());

			//announcement message.
			announcement.setMessage(dataMap.get("message").getValue().get().asString());

			//announcement announcer.
			int userId = Integer.parseInt(dataMap.get("announcer").getValue().get().asString());

			//preparing user for announcement's announcer.
			User user = new User();
			user.setId(userId);

			//announcement user
			announcement.setUser(user);

			//announcement publish date.
			announcement.setPublish(dataMap.get("publish").getValue().get().asString());

			//announcement created date.
			announcement.setCreated(dataMap.get("created").getValue().get().asString());

			this.announcementService.addAnnouncement(announcement, userId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			String error = new Collection.Builder(ANNOUNCEMENTS_URI).withError(Error.create("Exception",  String.valueOf(e.hashCode()), e.getMessage())).build().asJson().toString();

			return error;
		}

		return "announcement added.";
	}


	/**
	 * @author	furqan
	 * @param	Model model, HttpServletRequest request, @RequestParam data
	 * @return	String
	 */
	@RequestMapping(value="/contest/", method=RequestMethod.POST)
	@ResponseBody
	public String addNewContest (Model model, HttpServletRequest request, @RequestParam("data") String data) {

		//endpoint data param
		String cjData = data;

		//endpoint response.
		String response = "";

		//parsing the collection json.
		try {
			Collection collection = new CollectionParser().parse(cjData);

			//gettting the template part from json.
			Optional<Template> template = collection.getTemplate();

			//getting the data from the template as Map.
			Map<String, Property> dataMap = template.get().getData().getDataAsMap();

			//preparing announcement object.
			Contest contest = new Contest();

			//contest title
			contest.setTitle(dataMap.get("title").getValue().get().asString());

			//contest message.
			contest.setDescription(dataMap.get("description").getValue().get().asString());

			//contest announcer.
			int userId = Integer.parseInt(dataMap.get("creator").getValue().get().asString());

			//preparing user for contest's user.
			User user = new User();
			user.setId(userId);
			
			User u = this.userService.getUserById(user);
			
			if( u==null ) {
				String error = new Collection.Builder(CATEGORY_URI).withError(Error.create("Not Found",  "404", "User not found.")).build().asJson().toString();
				return error;
			}
			
			//contest user
			contest.setUser(user);

			//contest publish date.
			contest.setStatus(Integer.parseInt(dataMap.get("status").getValue().get().asString()));

			//contest created date.
			contest.setCreated(dataMap.get("created").getValue().get().asString());

			//Adding new contest to database.
			Boolean isContestAdded = this.contestService.createContest(contest, userId);

			//preparing the response.
			if( isContestAdded )
				response = "New Contest has been Added.";
			else
				response = "Problem Adding New Contest.";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}


	/**
	 * @author	furqan
	 * @param	Model model, HttpServletRequest request, @RequestParam data
	 * @return	String
	 */
	@RequestMapping(value="/categories/{categoryId}/contests/{contestId}/vote/", method=RequestMethod.POST) ///categories/category_id/contest
	
	@ResponseBody
	public String addNewVote (Model model, HttpServletRequest request, @RequestParam("data") String data) {

		//endpoint data param
		String cjData = data;

		//endpoint response.
		String response = "";

		//parsing the collection json.
		try {
			Collection collection = new CollectionParser().parse(cjData);

			//gettting the template part from json.
			Optional<Template> template = collection.getTemplate();

			//getting the data from the template as Map.
			Map<String, Property> dataMap = template.get().getData().getDataAsMap();

			//preparing vote object.
			Vote vote = new Vote();

			//vote's user.
			int userId = Integer.parseInt(dataMap.get("user_id").getValue().get().asString());

			//preparing user for vote's user.
			User user = new User();
			user.setId(userId);

			//vote user
			vote.setUser(user);

			//vote's contest id.
			int contestId = Integer.parseInt(dataMap.get("contest").getValue().get().asString());

			//preparing and setting contest for vote.
			Contest contest = new Contest();
			contest.setId(contestId);

			vote.setContest(this.contestService.getContestById(contest));

			//vote publish date.
			vote.setRating(Integer.parseInt(dataMap.get("rating").getValue().get().asString()));

			//vote created date.
			vote.setCreated(dataMap.get("created").getValue().get().asString());

			//Adding new vote to database.
			Boolean isVoteAdded = this.voteService.submitVote(vote, userId, contestId);

			//preparing the response.
			if( isVoteAdded )
				response = "New Vote has been Added.";
			else
				response = "Problem Adding New Vote.";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * @author	furqan
	 * @param	Model model, HttpServletRequest request, @RequestParam data
	 * @return	String
	 */
	@RequestMapping(value="/user/{userId}/setting/{settingId}/status/{status}/", method=RequestMethod.PUT)
	@ResponseBody
	public String updateSetting (Model model, HttpServletRequest request, @PathVariable("userId") String userId, @PathVariable("settingId") String idSetting, @PathVariable("status") String status) {

		//endpoint response.
		String response = "";

		//endpoint error.
		String error = "";

		//parsing the collection json.
		try {
			//preparing user setting object.
			UserSetting userSetting = new UserSetting();

			//user setting's user.
			int usrId = Integer.parseInt(userId);

			//preparing usersetting's user object.
			User user = new User();
			user.setId(usrId);

			User usr = this.userService.getUserById(user);

			if( usr==null ) { //return 404 if user not found.
				error = new Collection.Builder(UPDATESETTING_URI).withError(Error.create("Not Found",  "404", "User Id not found.")).build().asJson().toString();
				return error;
			}

			//finally updating user setting's user
			userSetting.setUser(usr);

			int settingId = Integer.parseInt(idSetting);

			//setting's object.
			Setting setting = new Setting();
			setting.setId(settingId);

			Setting settingobj = this.settingService.getSettingById(setting);

			if(settingobj==null) { //return 404 if setting not found.
				error = new Collection.Builder(UPDATESETTING_URI).withError(Error.create("Not Found",  "404", "Setting Id not found.")).build().asJson().toString();
				return error;
			}

			userSetting.setSetting(settingobj);

			//usersetting's status.
			userSetting.setStatus(Integer.parseInt(status));

			//Adding new vote to database.
			Boolean isUserSettingUpdated = this.usersettingService.updateUserSetting(userSetting);

			//preparing the response.
			if( isUserSettingUpdated )
				response = "User Setting has been updated.";
			else
				response = "Problem Updating User Setting.";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}
}
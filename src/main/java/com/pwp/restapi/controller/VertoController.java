package com.pwp.restapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pwp.restapi.model.Contest;
import com.pwp.restapi.model.Stringsets;
import com.pwp.restapi.model.User;
import com.pwp.restapi.service.ContestService;
import com.pwp.restapi.service.SetService;
import com.pwp.restapi.service.UserService;
import com.pwp.restapi.ui.model.Common;
import com.pwp.restapi.ui.model.Exactly;
import com.pwp.restapi.ui.model.Intersection;
import com.pwp.restapi.ui.model.Longchain;
import com.pwp.restapi.ui.model.Longest;
import com.pwp.restapi.ui.model.Search;
import com.pwp.restapi.ui.model.Statistics;

@Controller
public class VertoController {
	
	/*private UserService userService;
	//private Gson gson = new Gson();
	
	@Autowired(required=true)
	@Qualifier(value="UserService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}*/
	
	private ContestService contestService;
	
	@Autowired(required=true)
	@Qualifier(value="ContestService")
	public void setContestService(ContestService contestService) {
		this.contestService = contestService;
	}
	
	
	@RequestMapping(value="/testpost", method=RequestMethod.POST)
	@ResponseBody
	public String testDelete(@RequestParam("data") String data) {
		//return "data value is"+data;
		
		List<Contest> contest = this.contestService.getContestList();
		
		return Integer.toString(contest.size());
	}
	
	/*@RequestMapping(value="/upload/{set}", method=RequestMethod.POST)
	@ResponseBody
	public String upload(@PathVariable("set") String setStr) {
		
		//verifying the string.
		String[] stringArr;
		
		stringArr = setStr.split(" ");
		
		Boolean isDuplicateSet = checkDuplicates(stringArr);
		
		if( isDuplicateSet ) {
			return "Error (duplicate values).";
		}
		
		Stringsets set = new Stringsets();
		
		set.setStringset(setStr);
		
		Boolean isAdded = this.setService.addSet(set);
		
		if( isAdded ) 
			return "Inserted.";
		else
			return "Error (Not Inserted)";
	}
	
	@RequestMapping(value="/search/{string}", method=RequestMethod.GET)
	@ResponseBody
	public String search(@PathVariable("string") String str) {
		
		List<Stringsets> sets = this.setService.getStringSets(str);
		
		Search srch = new Search();
		srch.setSearchSets(sets);
		
		return gson.toJson(srch);
	}
	
	@RequestMapping(value="/delete/{string}", method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("string") String str) {
		
		Boolean isSetDeleted = this.setService.removeStringSet(str);
		
		if( isSetDeleted )
			return "Deleted.";
		else 
			return "Error(Not Deleted).";
	}
	
	@RequestMapping(value="/statistics", method=RequestMethod.GET)
	@ResponseBody
	public String set_statistics() {
		
		//get all the string sets from the database.
		List<Stringsets> sets = this.setService.getAllStringSets();
		
		String str = sets.get(sets.size()-1).getStringset();
		String[] strArr = str.split(" ");
		
		int[] strArrLen = getArrayOfStringLength(strArr);
		
		Arrays.sort(strArrLen);
		
		int numberOfStrings = strArr.length;
		int minimum 		= strArrLen[0];
		int maximum 		= strArrLen[strArrLen.length-1];
		int average			= getAvg(strArrLen);
		int median 			= getMedian(strArrLen);
			
		Statistics stats = new Statistics();
		
		stats.setStringCount(Integer.toString(numberOfStrings));
		stats.setMinimum(Integer.toString(minimum));
		stats.setMaximum(Integer.toString(maximum));
		stats.setAverage(Integer.toString(average));
		stats.setMedian(Integer.toString(median));
		
		//returning the output in json format.
		return gson.toJson(stats);
	}
	
	@RequestMapping(value="/common", method=RequestMethod.GET)
	@ResponseBody
	public String most_common() {
		
		List<Stringsets> sets = this.setService.getAllStringSets();
		List<List<String>> strLst = new ArrayList<List<String>>();
		
		//sorting the list as per its occurence in the other sets.
		for( int i=0; i<sets.size(); i++ ) {
			
			String[] strArr = sets.get(i).getStringset().split(" ");
			
			for( int j=0; j<strArr.length; j++ ) {
				List<String> tempLst = new ArrayList<String>();
				for( int k=0; k<sets.size(); k++ ) {
					
					//don't look in the same index.
					if( k==i )
						continue;
					
					if( sets.get(k).getStringset().contains(strArr[j]) ) {
						tempLst.add(strArr[j]);
					}		
				}
				
				//if no occurence found in other sets then add that string instead of null.
				if( tempLst.size()==0 )
					tempLst.add(strArr[j]);
				
				strLst.add(tempLst);
			}
		}
		
		//finding the maximum number in the string's list.
		//String str = "";
		int max = strLst.get(0).size();
		
		for( int j=0; j<strLst.size(); j++ ) {
			if( strLst.get(j).size()>max )
				max = strLst.get(j).size();
		}
		
		Set<String> str = new HashSet<String>();
		
		//returning the string that occurs the maximum time in the sets.
		for( int k=0; k<strLst.size(); k++ ) {
			if( strLst.get(k).size()==max ) {
				str.add(strLst.get(k).get(0));
			}
		}
		
		List<String> finalList = new ArrayList<String>(str);
		Collections.sort(finalList);
		
		Common common = new Common();
		common.setCommon(finalList);
		
		return gson.toJson(common);
	}
	
	@RequestMapping(value="/longest", method=RequestMethod.GET)
	@ResponseBody
	public String longest() {
		
		int maxLength = 0;
		List<String> longestStrings = new ArrayList<String>();
		List<Stringsets> sets = this.setService.getAllStringSets();
		
		//getting the max length value from all the sets.
		for( int i=0; i<sets.size(); i++ ) {
			
			String str = sets.get(i).getStringset();
			String[] strArr = str.split(" ");
			
			int[] strArrLength = getArrayOfStringLength(strArr);
			
			Arrays.sort(strArrLength);
			
			int tempMaxLength = strArrLength[strArrLength.length-1];
			
			if( tempMaxLength>maxLength )
				maxLength = tempMaxLength;
			else
				continue;
		}
		
		//getting all the string that matches the length of the maximum lenght calculated above.
		for(int j=0; j<sets.size(); j++) {
			String str = sets.get(j).getStringset();
			String[] strArr = str.split(" ");
			
			for( int k=0; k<strArr.length; k++ ) {
				if( strArr[k].length()==maxLength )
					longestStrings.add(strArr[k]);
			}
		}
		
		//sorting the collection alphabetically.
		Collections.sort(longestStrings);
		
		
		Longest longest = new Longest();
		longest.setLongestStr(longestStrings);
		
		return gson.toJson(longest);
	}	
	
	@RequestMapping(value="/exactly", method=RequestMethod.GET)
	@ResponseBody
	public String exactly_in() {
		
		List<Stringsets> sets = this.setService.getAllStringSets();
		List<List<String>> strLst = new ArrayList<List<String>>();
		
		//sorting the list as per its occurence in the other sets.
		for( int i=0; i<sets.size(); i++ ) {
			
			String[] strArr = sets.get(i).getStringset().split(" ");
			
			for( int j=0; j<strArr.length; j++ ) {
				List<String> tempLst = new ArrayList<String>();
				for( int k=0; k<sets.size(); k++ ) {
					
					//don't look in the same index.
					if( sets.get(k).getStringset().contains(strArr[j]) ) {
						tempLst.add(strArr[j]);
					}		
				}
				
				//if no occurence found in other sets then add that string instead of null.
				if( tempLst.size()==0 )
					tempLst.add(strArr[j]);
				
				strLst.add(tempLst);
			}
		}
		
		Set<String> tempList = new HashSet<String>(); 
		
		for( int i=0; i<strLst.size(); i++ ) {
			if( strLst.get(i).size()==sets.size() ) {
				tempList.add(strLst.get(i).get(0));
			}
		}
		
		List<String> finalList = new ArrayList<String>(tempList);
		Collections.sort(finalList);
		
		Exactly exactly = new Exactly();
		exactly.setExactStr(finalList);
		
		return gson.toJson(exactly);
	}
	
	@RequestMapping(value="/intersection", method=RequestMethod.GET)
	@ResponseBody
	public String create_interection() {
		
		List<Stringsets> sets = this.setService.getAllStringSets();
		
		//splitting the string into array and converting them into sets.
		Set<String> setA = new HashSet<String>(Arrays.asList(sets.get(sets.size()-1).getStringset().split(" ")));
		Set<String> setB = new HashSet<String>(Arrays.asList(sets.get(sets.size()-2).getStringset().split(" ")));
		
		//calculating the intersection.
		setA.retainAll(setB);
		
		Intersection intersection = new Intersection();
		intersection.setIntersection(new ArrayList<String>(setA));
		
		return gson.toJson(intersection);
	}
	
	@RequestMapping(value="/longchain", method=RequestMethod.GET)
	@ResponseBody
	public String longest_chain() {
		
		//get all the sets from the database.
		List<Stringsets> sets = this.setService.getAllStringSets();
		List<String> chain = new ArrayList<String>();
		
		//starting with the string of the first set and adding it to the chain.
		chain.add(sets.get(0).getStringset().split(" ")[0]);
		
		for( int i=0; i<sets.size(); i++ ) {
			
			String[] strArr = sets.get(i).getStringset().split(" ");
			
			for( int j=0; j<strArr.length; j++ ) {
				
				//get the last string of the list.
				String prev = chain.get(chain.size()-1);
				
				//current string.
				String next = strArr[j];
				
				//if the last and first character of prev and next are matched then add the current string to the set.
				if( Character.toString(prev.charAt(prev.length()-1)).equals(Character.toString(next.charAt(0))) ) {
					chain.add(strArr[j]);
				}
			}
		}
		
		String finalChain = "";
		
		for(int i=0; i<chain.size(); i++) {
			finalChain+=chain.get(i);
			
			if( i==chain.size()-1 )
				continue;
			
			finalChain+="-";
		}
		
		Longchain longChain = new Longchain();
		longChain.setChain(finalChain);
		
		return gson.toJson(longChain, Longchain.class);
	}
	
	public Boolean checkDuplicates(String[] strArr) {
		
		for( int i=0; i<strArr.length; i++ ) {	
			String str = strArr[i];
			
			for( int j=0; j<strArr.length; j++ ) {
				if( i==j ) 
					continue;
				else {
					if( strArr[j].equals(str) ) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public int[] getArrayOfStringLength(String[] strArr) {
		
		int[] lengths = new int[strArr.length];
		for( int i=0; i<strArr.length; i++ ) {
			String str = strArr[i];
			
			lengths[i] = str.length();
		}
		
		return lengths;
	}
	
	public int getAvg(int[] strArr) {
		int sum = 0;
		
		for(int i=0; i<strArr.length; i++) {
			sum+=strArr[i];
		}
		
		int Avg = sum/strArr.length;
		
		return Avg;
	}
	
	public int getMedian(int[] strArr) {
		int median = 0;
		int middle = ((strArr.length) / 2);
		
		if(strArr.length % 2 == 0) {
		 int median1 = strArr[middle];
		 int median2 = strArr[middle-1];
		 median = (median1 + median2) / 2;
		} else {
		 median = strArr[middle + 1];
		}
		
		return median;
	}
*/	
}
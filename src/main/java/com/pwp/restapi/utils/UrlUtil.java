package com.pwp.restapi.utils;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * PWP RestAPI Implementation.
 * 
 * @category 	Util.
 * @package 	com.pwp.restapi.utils.
 * @author  	Furqan Ahmed <ahmedfurqan88@gmail.com>
 * @license     http://opensource.org/licenses/osl-3.0.php  Open Software License (OSL 3.0)
 * */
public class UrlUtil {
	
	public static String project 			= "/PWPRestAPI";
	public static String apiVersion 		= "/v1";
	public static String apiVersionNumber 	= "1.0";
	
	
	/**
	 * @author	furqan
	 * @param	HttpServletRequest request
	 * @return	String
	 */
	public static String getApplicationUrl(HttpServletRequest request) {
		
		URI uri = null;
		try {
			URL url = new URL(request.getRequestURL().toString());
			String host  = url.getHost();//getting the url host.
			String userInfo = url.getUserInfo();//getting the url user info.
			String scheme = url.getProtocol();//getting the url protocol.
			int port = url.getPort();//getting the url port.
			String path = (String) request.getAttribute("javax.servlet.forward.request_uri");//getting getting the path.
			String query = (String) request.getAttribute("javax.servlet.forward.query_string");//getting the query params.

			uri = new URI(scheme,userInfo,host,port,path,query,null);//creating up the uri.
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		String appUri = uri.toString();
		
		appUri = appUri.concat(project);
		appUri = appUri.concat("/api");
		appUri = appUri.concat(apiVersion);
		
		return appUri;
	}
}
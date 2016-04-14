package com.pwp.restapi.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonUtil {

	public static String createCollectionJsonFormat(String version, String href, Map<String, String> links, List<String> itemsHref, List<Map<String, String>> itemData, List<Map<String, String>> itemLinks, Map<String, String> templateData, int itemCount) {


		String collectionJsonStr = "";

		//json root collection.
		collectionJsonStr = collectionJsonStr.concat("{ \"collection\" :");
		collectionJsonStr = collectionJsonStr.concat("{");

		//api version.
		collectionJsonStr = collectionJsonStr.concat("\"version\" : "+version);
		collectionJsonStr = collectionJsonStr.concat(",");

		//api href.
		collectionJsonStr = collectionJsonStr.concat("\"href\" : \""+href+"\",");


		//api connected links
		collectionJsonStr = collectionJsonStr.concat("\"links\" : [");
		String linkStr = "";
		if(links!=null) {
			@SuppressWarnings("rawtypes")
			Iterator it = links.entrySet().iterator();
			while (it.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry pair = (Map.Entry)it.next();

				linkStr = linkStr.concat("{\"rel\" : \""+pair.getKey()+"\", \"href\" : \""+pair.getValue()+"\"}");

				if( it.hasNext() )
					linkStr = linkStr.concat(",");
			}
		}
		collectionJsonStr = collectionJsonStr.concat(linkStr);
		collectionJsonStr = collectionJsonStr.concat("],");

		//json+collection format's items node creation.
		String items = "";
		if(itemCount>0) {
			items = "\"items\" : [";

			for( int i=0; i<itemCount; i++ ) {
				items = items.concat("{");

				//preparing for the item href link.
				items = items.concat("\"href\" : \""+itemsHref.get(i)+"\",");


				//preparing item data.
				items = items.concat("\"data\" : [");
				String itemsDataObj = "";
				Map<String, String> data = itemData.get(i);

				@SuppressWarnings("rawtypes")
				Iterator itData = data.entrySet().iterator();
				while (itData.hasNext()) {
					@SuppressWarnings("rawtypes")
					Map.Entry pair = (Map.Entry)itData.next();

					itemsDataObj = itemsDataObj.concat("{\"rel\" : \""+pair.getKey()+"\", \"href\" : \""+pair.getValue()+"\", \"prompt\" : \""+pair.getKey()+"\"}");

					if( itData.hasNext() )
						itemsDataObj = itemsDataObj.concat(",");
				}

				items = items.concat(itemsDataObj);
				items = items.concat("],");


				//preparing item links.
				items = items.concat("\"links\" : [");

				String itemslinksObj = "";
				if(itemLinks!=null) {
					Map<String, String> linkMap = itemLinks.get(i);
					@SuppressWarnings("rawtypes")
					Iterator itItemsLinks = linkMap.entrySet().iterator();
					while (itItemsLinks.hasNext()) {
						@SuppressWarnings("rawtypes")
						Map.Entry pair = (Map.Entry)itItemsLinks.next();

						itemslinksObj = itemslinksObj.concat("{\"rel\" : \""+pair.getKey()+"\", \"href\" : \""+pair.getValue()+"\", \"prompt\" : \""+pair.getKey()+"\"}");

						if( itItemsLinks.hasNext() )
							itemslinksObj = itemslinksObj.concat(",");
					}


					items = items.concat(itemslinksObj);
				}
				items = items.concat("]");
				items = items.concat("}");

				if(i!=itemCount-1)
					items = items.concat(",");

			}

			items = items.concat("]");
		}
		collectionJsonStr = collectionJsonStr.concat(items);

		//json resource template.
		String resourceTemplate = "";
		if(templateData!=null) {
			resourceTemplate = ",\"template\" : {";
			resourceTemplate = resourceTemplate.concat("\"data\" : [");

			String dataObjs = "";

			@SuppressWarnings("rawtypes")
			Iterator itTemplate = templateData.entrySet().iterator();
			while (itTemplate.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry pair = (Map.Entry)itTemplate.next();

				dataObjs = dataObjs.concat("{\""+pair.getKey()+"\" : " +"\""+pair.getValue()+"\", \"prompt\" : \""+pair.getKey()+"\"}");

				if( itTemplate.hasNext() )
					dataObjs = dataObjs.concat(",");
			}
			resourceTemplate = resourceTemplate.concat(dataObjs);
			resourceTemplate = resourceTemplate.concat("]");//end template data.
			resourceTemplate = resourceTemplate.concat("}");//end template.
		}
		//concatenate template node with the root.
		collectionJsonStr = collectionJsonStr.concat(resourceTemplate);
		collectionJsonStr = collectionJsonStr.concat("}");//end collection object.
		collectionJsonStr = collectionJsonStr.concat("}");//end root.

		return collectionJsonStr;

	}
}
package com.wisdira.myBusFinder;


	
	 
	/**
	* @author Crunchify.com
	*/
	 
	import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import sun.misc.BASE64Encoder;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
 
	@Path("/callZonarService")
	public class CallZonarService {

	@GET
	@Produces("application/json")
	public String callZonarService(@QueryParam("dbid")String dbid) {
		String output="";
		try{
			System.out.println("callZonar called 7");
			System.out.println("callZonar dbid is "+dbid);
			if(dbid == null){
				System.out.println("hardcoding dbid to 5");
				dbid = "5";
			}
			
			SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS");
			Date startDate = sdf.parse("2015-05-10 05:00:00.000");
			long startTimeInMillisSinceEpoch = startDate.getTime(); 
			long startTimeInMinutesSinceEpoch = startTimeInMillisSinceEpoch / (60 * 1000);
			System.out.println("startTimeInMinutesSinceEpoch is "+startTimeInMinutesSinceEpoch);
			
			Date endDate = sdf.parse("2015-05-10 16:00:00.000");
			long endTimeInMillisSinceEpoch = endDate.getTime(); 
			long endTimeInMinutesSinceEpoch = endTimeInMillisSinceEpoch / (60 * 1000);
			System.out.println("endTimeInMinutesSinceEpoch is "+endTimeInMinutesSinceEpoch);
	
			long unixTime = System.currentTimeMillis() / 1000L;
			System.out.println("unixTime is "+unixTime);
			
			//String url = "http://sharkey.zonarsystems.net/interface.php?username=kroberts&password=welcome1&action=showposition&operation=pathsummary&format=json&customer=sharkey&starttime=1141397988&endtime=1500000000";
			String urlPrefix = "http://sharkey.zonarsystems.net/interface.php?username=kroberts&password=welcome1&customer=sharkey&";
	
			// show current positions XML only
			//String urlParams = "action=showposition&operation=current&format=xml&version=2&logvers=3&startrow=0&count=10&sortfield=lasttime&sortdir=asc";
			
			// show current position - specific asset
			//String urlParams = "action=showposition&operation=current&format=xml&version=2&logvers=3&reqtype=dbid&target=5";
				
			String urlParams = "action=showposition&operation=current&format=json&version=2&logvers=3&reqtype=dbid&target="+dbid;
			
			// get Path
			//String urlParams = "action=showposition&operation=pathsummary&format=json&starttime=1431248400&endtime=1431376666";
			
			// get Assets
			//String urlParams = "action=showopen&operation=showassets&format=xml";
			
			// get Specific Asset
			//String urlParams = "action=showopen&operation=showassets&format=xml&reqtype=dbid&target=5"; 
			
			String url = urlPrefix+urlParams;
			System.out.println("url is "+url);
			//String url = "http://localhost:8080/RestfulWebServices/order-inventory/order/1016";
			String username = "kroberts";
			String password = "welcome1!";
			String authString = username + ":" + password;
			String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
			System.out.println("Base64 encoded auth string: " + authStringEnc);
			Client restClient = Client.create();
			WebResource webResource = restClient.resource(url);
			ClientResponse resp = webResource.accept("application/json")
											 .header("Authorization", "Basic " + authStringEnc)
											 .get(ClientResponse.class);
			if(resp.getStatus() != 200){
				System.err.println("Unable to connect to the server");
			}
			output = resp.getEntity(String.class);
		//	System.out.println("response: "+output);
		}catch(Exception e){
			System.out.println("ERROR "+e.getLocalizedMessage());
		}
		System.out.println("returning "+output);
		return output;
	}
	 

	
}

	
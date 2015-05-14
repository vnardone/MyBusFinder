package com.wisdira.myBusFinder;
	 
	import javax.ws.rs.GET;
	import javax.ws.rs.Path;
	import javax.ws.rs.core.MediaType;
	import javax.ws.rs.Produces;
	 
	@Path("/")
	@Produces( {  MediaType.APPLICATION_XML } )
	public class JerseyTest {
 
	 
		@GET
		public String get() {
	        return
	        "<!--?xml version=\"1.0\" encoding=\"UTF-8\"?-->" +
	        "" +
	        	"Ilan Hazan" +
	        	"Hello World" +
	        "";
	    }
	}


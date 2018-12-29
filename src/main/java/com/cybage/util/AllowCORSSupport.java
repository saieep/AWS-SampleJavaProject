package com.cybage.util;

import org.springframework.http.HttpHeaders;

public class AllowCORSSupport {

	 public static  HttpHeaders addAccessControllAllowOrigin() {
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Access-Control-Allow-Origin", "*");
	        return headers;
	    }

	
}

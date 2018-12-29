package com.cybage.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cybage.authentication.TokenAuthenticationService;
import com.cybage.requestdto.GroupDetailDTO;
import com.cybage.service.AuthenticateService;

@Controller
public class LoginController {

	private Logger logger = Logger.getLogger(AdminController.class);

	
	@Autowired
	AuthenticateService authService;
	
	@Autowired
	TokenAuthenticationService tokenAuthService;
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<List>  login(@RequestBody GroupDetailDTO groupDetailDto){
		
		
		List list=authService.checkGroupId(groupDetailDto);
		if(list.size() != 0){
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer"+ " " + tokenAuthService.addAuthentication(groupDetailDto));
			return new ResponseEntity<List>(list,headers,HttpStatus.ACCEPTED);
		
		}
		return new ResponseEntity<List>(HttpStatus.BAD_REQUEST);

		
	}
	
}

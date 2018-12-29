package com.cybage.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cybage.command.Groupdetail;
import com.cybage.command.User;
import com.cybage.requestdto.GroupDetailDTO;
import com.cybage.service.GroupDetailService;
import com.cybage.service.UserService;

@Component
public class TokenAuthenticationService {

	@Autowired
	UserService userService;
	
	@Autowired
	GroupDetailService groupService;

	private long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 10; 
	private String secret = "ThisIsASecret";
	 
	public String addAuthentication(GroupDetailDTO groupDto) {
		String JWT = Jwts
				.builder()
				.setSubject(Integer.toString(groupDto.getId()))
				.setExpiration(
						new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, secret).compact();

		return JWT;

	}

	public boolean isAdmin(String jwt) {
		String token = jwt;
		try{
		if (token != null) {
			String id = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody().getSubject();
			User user = userService.findUserById(Integer.parseInt(id));
/*			Groupdetail groupdetail=groupService.findGroupById(Integer.parseInt(id));
*/			if(user != null && user.getRole().equalsIgnoreCase("admin"))
				return true;
		}
		}
		catch(Exception e){
			return false;
		}
		return false;
	}
	
	public boolean isTrainer(String jwt) {
		String token = jwt;
		try{
		if (token != null) {
			String id = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody().getSubject();
			User user = userService.findUserById(Integer.parseInt(id));
/*			Groupdetail groupdetail=groupService.findGroupById(Integer.parseInt(id));
*/			if(user != null && user.getRole().equalsIgnoreCase("trainer"))
				return true;
		}
		}
		catch(Exception e){
			return false;
		}
		return false;
	}
	
	public boolean isTrainee(String jwt) {
		String token = jwt;
		try{
		if (token != null) {
			String id = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody().getSubject();
/*			User user = userService.findUserById(Integer.parseInt(id));
*/			Groupdetail groupdetail=groupService.findGroupById(Integer.parseInt(id));
			if(groupdetail != null)
				return true;
		}
		}
		catch(Exception e){
			return false;
		}
		return false;
	}
	
}
package com.cybage.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.command.Groupdetail;
import com.cybage.command.User;
import com.cybage.dao.AuthenticationDAO;
import com.cybage.requestdto.GroupDetailDTO;

@Service
public class AuthenticateServiceImpl implements AuthenticateService{

	@Autowired
	AuthenticationDAO authDao;
	
	
	
	public List checkGroupId(GroupDetailDTO groupDetailDTO) {
		Groupdetail groupDetail=new Groupdetail();
		
		groupDetail.setId(groupDetailDTO.getId());
		groupDetail.setPassword(groupDetailDTO.getPassword());
		List<Groupdetail> list=authDao.checkGroupid(groupDetail);
		if(list.size()!=0){
			JSONObject obj = new JSONObject();
	        obj.put("role", "Trainee");
	       // obj.put("sessionid", list.get(1));
	        JSONArray result = new JSONArray();
	        result.add(obj);
	        
			return result;
		}
		else{
			
			return checkUserId(groupDetailDTO);
		}
		
		
	}

	public List<User> checkUserId(GroupDetailDTO groupDetailDTO) {
		User user=new User();
		
		user.setId(groupDetailDTO.getId());
		user.setPassword(groupDetailDTO.getPassword());
		List<User> role=authDao.checkUserId(user);
		JSONObject obj = new JSONObject();
		obj.put("role", role.get(0));
		List list=new ArrayList();
		list.add(obj);
		return list;
	}

}

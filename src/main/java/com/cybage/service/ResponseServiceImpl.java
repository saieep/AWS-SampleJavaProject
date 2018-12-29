package com.cybage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.command.Groupdetail;
import com.cybage.command.Response;
import com.cybage.command.ResponsePK;
import com.cybage.command.Scenario;
import com.cybage.dao.ResponseDAO;
import com.cybage.requestdto.ResponseDTO;
import com.cybage.responsedto.ResponseResposeDTO;

@Service
public class ResponseServiceImpl implements ResponseService {

	@Autowired
	ResponseDAO responseDao;
	
	

	public List<Response> showResponse() {
		List<Response> list=responseDao.showResponse();
		return list;
}

	public void insertResponse(ResponseDTO responseDto) {
		Response response=new Response();
		ResponsePK responsepk=new ResponsePK();
		Groupdetail groupdetail=new Groupdetail();
		Scenario scenario=new Scenario();
		
		groupdetail.setId(responseDto.getGroupid());
		scenario.setScenarioid(responseDto.getScenarioid());
		
		
		//response.setScore(responseDto.getScore());
		response.setResponse(responseDto.getResponse());
		responsepk.setGroupid(groupdetail.getId());
		responsepk.setSenarioid(scenario.getScenarioid());
		response.setId(responsepk);
		
		responseDao.insertResponse(response);
	}

	public void insertScore(Response response) {		
		responseDao.insertscore(response);
	}

	public Response findByGroupId(ResponsePK responsepk) {
		return responseDao.findByGroupId(responsepk);
		
	}
	
	
}
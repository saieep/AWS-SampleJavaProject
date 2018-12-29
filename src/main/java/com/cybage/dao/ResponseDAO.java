package com.cybage.dao;

import java.util.List;

import com.cybage.command.Response;
import com.cybage.command.ResponsePK;
import com.cybage.responsedto.ResponseResposeDTO;

public interface ResponseDAO {
	public List<Response> showResponse();

	public void insertResponse(Response response);
	public void insertscore(Response response);
	public Response findByGroupId(ResponsePK responsepk);
}

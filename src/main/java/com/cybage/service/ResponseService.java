package com.cybage.service;

import java.util.List;

import com.cybage.command.Response;
import com.cybage.command.ResponsePK;
import com.cybage.requestdto.ResponseDTO;
import com.cybage.responsedto.ResponseResposeDTO;

public interface ResponseService {
public List<Response> showResponse();
public void insertResponse(ResponseDTO responseDto);
public void insertScore(Response response);
public Response findByGroupId(ResponsePK responsepk);
}

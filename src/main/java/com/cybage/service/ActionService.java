package com.cybage.service;

import java.util.List;

import com.cybage.command.Action;
import com.cybage.requestdto.ActionDTO;


public interface ActionService {
	public List<Action> showAction();
	public void insertAction(ActionDTO actionDto);
}

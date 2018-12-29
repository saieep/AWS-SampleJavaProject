package com.cybage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.command.Action;
import com.cybage.dao.ActionDAO;
import com.cybage.requestdto.ActionDTO;

//To automatically create bean of service IMPL at the time of component base-scan
@Service 
public class ActionServiceImpl implements ActionService{
	
	@Autowired
	ActionDAO actionDao;
	


	public List<Action> showAction() {  //to fetch data from Action table
		List<Action> list=actionDao.showAction(); //calling showAction() method of actiondao
		return list;
}
	public void insertAction(ActionDTO actionDto){    //to insert data into action table
		Action actions=new Action();
		actions.setDescription(actionDto.getDescription());   //setting value of attribute description in action
    	actionDao.insertAction(actions);   //calling insertActioin method of actiondao
    }
	

}

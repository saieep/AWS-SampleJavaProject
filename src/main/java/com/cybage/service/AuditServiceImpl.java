package com.cybage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.command.Action;
import com.cybage.command.Audit;
import com.cybage.command.Groupdetail;
import com.cybage.command.Scenario;
import com.cybage.dao.AuditDAO;
import com.cybage.requestdto.AuditDTO;

//To automatically create bean of service IMPL at the time of component base-scan
@Service
public class AuditServiceImpl implements AuditService {

	
	@Autowired
	AuditDAO auditDao;
	
	

	public List<Audit> showAudit() {      //fetch data from audit table
		List<Audit> list=auditDao.showAudit();   //calling showAudit() method of AuditDao
		return list;
}

	public void insertAudit(AuditDTO auditDto) {   //insert data into audit table
		Action action=new Action();
		Audit audit=new Audit();                       
		Scenario scenario=new Scenario();
		
		
		Groupdetail groupdetail= new Groupdetail(); 
		groupdetail.setId(auditDto.getGroupid());          //setting groupid into groupdetail object
		action.setActionid(auditDto.getActionid());        //setting actionid into action object
		scenario.setScenarioid(auditDto.getScenarioid());  //setting scenarioid into scenario object
		
		audit.setGroupdetail(groupdetail);      
		audit.setScenario(scenario);    
		audit.setAction(action);
		
		auditDao.insertAudit(audit);       //calling insertAudit() method of auditDao
		}
}

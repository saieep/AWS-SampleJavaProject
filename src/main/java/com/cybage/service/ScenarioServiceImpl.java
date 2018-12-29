package com.cybage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.command.Scenario;
import com.cybage.command.Session;
import com.cybage.dao.ScenarioDAO;
import com.cybage.exception.RecordNotFoundException;
import com.cybage.exception.ScenarioServiceException;
import com.cybage.exception.SessionIdNotFoundException;
import com.cybage.requestdto.ScenarioDTO;

@Service
public class ScenarioServiceImpl implements ScenarioService {

	@Autowired
	ScenarioDAO scenarioDao;
	
	
	
	public List<Scenario> showScenario() {
		List<Scenario> list=scenarioDao.showScenario();
		return list;
	}
	
	public void insertScenario(ScenarioDTO scenarioDto) {
		Scenario scenario = new Scenario();
		Session session = new Session();
		session.setSessionid(scenarioDto.getSessionid());
	 
		scenario.setSession(session);
		scenario.setContent(scenarioDto.getContent());
		
		scenario.setDuration(scenarioDto.getDuration());
		scenario.setIsactive(scenarioDto.getIsactive());
	    
		scenarioDao.insertScenario(scenario);
		
	}
	
	public Scenario findScenarioById(int id) {
		return scenarioDao.findScenariobyId(id);
	}
	
	
	public void updateIsActive(Scenario scenario) {
		scenarioDao.updateIsActive(scenario);
 }

	public List<Scenario> showScenarioBySID(int sessionid){
		try{
		List<Scenario> list=scenarioDao.showScenarioBySID(sessionid);
		return list;
		}
		catch(RecordNotFoundException  e){
			throw new ScenarioServiceException("record not found");
		}
		catch( SessionIdNotFoundException e)
		{
			throw new ScenarioServiceException("record not found");

		}
		
		
	}

	public List<Scenario> showScenarioBySIDActive(int sessionid) {
		return scenarioDao.showScenarioBySIDActive(sessionid);
	}
}
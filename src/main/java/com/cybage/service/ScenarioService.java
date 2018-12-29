package com.cybage.service;

import java.util.List;

import com.cybage.command.Scenario;

import com.cybage.requestdto.ScenarioDTO;

public interface ScenarioService {
	public List<Scenario> showScenario();

	public void insertScenario(ScenarioDTO scenarioDto);

    public Scenario findScenarioById(int id);
	
	public void updateIsActive(Scenario scenario); 
	
	public List<Scenario> showScenarioBySID(int sessionid);
	
	public List<Scenario> showScenarioBySIDActive(int sessionid);

}

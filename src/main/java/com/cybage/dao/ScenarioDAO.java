package com.cybage.dao;

import java.util.List;

import com.cybage.command.Scenario;


public interface ScenarioDAO {
public List<Scenario> showScenario();

public void insertScenario(Scenario scenario);

public Scenario findScenariobyId(int id);

public void updateIsActive(Scenario scenario);

public List<Scenario> showScenarioBySID(int sessionid);

public List<Scenario> showScenarioBySIDActive(int sessionid);
}

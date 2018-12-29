package com.cybage.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.command.Scenario;
import com.cybage.command.Session;
import com.cybage.exception.RecordNotFoundException;
import com.cybage.exception.SessionIdNotFoundException;

//To automatically create bean of DAO IMPL at the time of component base-scan
@Repository
public class ScenarioDAOImpl implements ScenarioDAO {

	@Autowired //Autowired to create object of session factory bean
	SessionFactory sessionFactory;
	
	
	@Transactional //To automatically close session factory
	//Showing a Scenario
	public List<Scenario> showScenario() {
		Scenario scenario=new Scenario();
		byte a=1; //isActive Flag Value
		scenario.setIsactive(a); //Making Scenario Active
		@SuppressWarnings("unchecked")
		//Fectching Active Scenarios into list object
		List<Scenario> list=sessionFactory.getCurrentSession().createCriteria(Scenario.class).add(Restrictions.ne("isactive", scenario.getIsactive())).list();
		return list;
	}
	
	
	@Transactional 
	//Inserting Scenario into Scenario Table
	public void insertScenario(Scenario scenario) {
		sessionFactory.getCurrentSession().persist(scenario);
	}
	
	@Transactional 
	//Finding particular Scenario by ID
	public Scenario findScenariobyId(int id) {
		Scenario scenario=(Scenario)sessionFactory.getCurrentSession().get(Scenario.class, id);
		return scenario;
	}

	@Transactional 
	//Updating the Active Scenario
	public void updateIsActive(Scenario scenario) {
		sessionFactory.getCurrentSession().saveOrUpdate(scenario);
	}
	
	
	 
	 // To fetch the details of active scenario using Session ID
	@Transactional 
	public List<Scenario> showScenarioBySID(int sessionid){
		Scenario scenario=new Scenario();
		byte a=0;
		scenario.setIsactive(a);
		Session session=new Session();
		session.setSessionid(sessionid);
		@SuppressWarnings("unchecked")
		List<Scenario> list=sessionFactory.getCurrentSession().createCriteria(Scenario.class).add(Restrictions.eq("isactive", scenario.getIsactive())).add(Restrictions.eq("session", session)).list();
		return list;	
	}
	
	
	@Transactional 
	public List<Scenario> showScenarioBySIDActive(int sessionid){
		Scenario scenario=new Scenario();
		byte a=1;
		scenario.setIsactive(a);
		Session session=new Session();
		if(sessionid != 0)
		{
			session.setSessionid(sessionid);
			@SuppressWarnings("unchecked")
			List<Scenario> list=sessionFactory.getCurrentSession().createCriteria(Scenario.class).add(Restrictions.eq("isactive", scenario.getIsactive())).add(Restrictions.eq("session", session)).list();
			if(list.isEmpty()){
			return list;
		}
			else{
				throw new RecordNotFoundException("record not found");
			}
		}
		else
		{
			throw new SessionIdNotFoundException("session id is zero");
		}
	}
}

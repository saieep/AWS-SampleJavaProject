package com.cybage.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



import com.cybage.command.Action;

//To automatically create bean of DAO IMPL at the time of component base-scan
@Repository
public class ActionDAOImpl implements ActionDAO {
	
	
	@Autowired  //Autowired to create object of session factory bean
	SessionFactory sessionFactory;
	
	@Transactional //To automatically close session factory
	
	//
	public List<Action> showAction() //To show the data of Action Table
	{
		
		@SuppressWarnings("unchecked")
		//Fetching data of  Action Table into list via using Criteria API
		List<Action> list=sessionFactory.getCurrentSession().createCriteria(Action.class).list();
		
		return list;
	}
	
	@Transactional  //To automatically close session factory
	public void insertAction(Action action) 
		{
			//To persist/insert data into Action Table
			sessionFactory.getCurrentSession().persist(action);
		}
}

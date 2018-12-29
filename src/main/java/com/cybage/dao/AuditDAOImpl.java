package com.cybage.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.command.Audit;

//To automatically create bean of DAO IMPL at the time of component base-scan 
@Repository

public class AuditDAOImpl implements AuditDAO {

	//Autowired to create object of session factory bean
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	//To show the data of Audit Table
	public List<Audit> showAudit() {
		@SuppressWarnings("unchecked")
		//Fetching data of  Audit Table into list via using Criteria API
		List<Audit> list=sessionFactory.getCurrentSession().createCriteria(Audit.class).list();
		return list;
		 
	}
	
	@Transactional //To automatically close session factory
	
	public void insertAudit(Audit audit) {
		//To persist/insert data into Audit Table
		sessionFactory.getCurrentSession().persist(audit);
	}
}

package com.cybage.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.command.Session;
import com.cybage.command.User;

//To automatically create bean of DAO IMPL at the time of component base-scan
@Repository
public class SessionDAOImpl implements SessionDAO{

		@Autowired //Autowired to create object of session factory bean
		SessionFactory sessionFactory;
		
		
		
		@Transactional //To automatically close session factory
		
		//Fetching data of Session Table into list via using Criteria API
		public List<Session> showSession() {
			Session session=new Session();
			byte a=1;//Checking for Active Session
			session.setIsactive(a);
			@SuppressWarnings("unchecked")
			List<Session> list=sessionFactory.getCurrentSession().createCriteria(Session.class).add(Restrictions.ne("isactive", session.getIsactive())).list();
			
			return list;
		}
		
		@Transactional
		public void insertSession(Session session) {
			//Insert Session into Session Table
			sessionFactory.getCurrentSession().persist(session);
		}

		@Transactional
		public Session findSessionbyId(int id) {
			//Find a particular session by ID
			Session session=(Session)sessionFactory.getCurrentSession().get(Session.class, id);
			return session;
		}

		@Transactional
		public void updateStatus(Session session) {
			//Update session status active/inactive 
			sessionFactory.getCurrentSession().saveOrUpdate(session);
		}
		
		//Find Session by UserId using Criteria API
		@Transactional
		public List<Session> findSessionbyuserid(int id){
			User user=new User();
			user.setId(id);
			@SuppressWarnings("unchecked")
			List<Session> list=sessionFactory.getCurrentSession().createCriteria(Session.class).add(Restrictions.eq("user", user)).list();
			return list;
		}
	}


package com.cybage.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.command.Groupdetail;
import com.cybage.command.User;

//To automatically create bean of DAO IMPL at the time of component base-scan
@SuppressWarnings("unused")
@Repository
public class GroupDetailDAOImpl implements GroupDetailDAO{

	@Autowired  //Autowired to create object of session factory bean
	SessionFactory sessionFactory;
	
	
	@Transactional //To automatically close session factory
	public List<Groupdetail> showGroupDetail() {
		@SuppressWarnings("unchecked")
		
		//Fetching data of  GroupDetails Table into list via using Criteria API
		List<Groupdetail> list=sessionFactory.getCurrentSession().createCriteria(Groupdetail.class).list();
		return list;
	}
 
	@Transactional //To automatically close session factory
	public void insertGroupDetail(Groupdetail groupdetail){
		
		//To persist/insert data into GroupDetails Table
		sessionFactory.getCurrentSession().persist(groupdetail);
	}
	
	@Transactional //To automatically close session factory
	public Groupdetail findGroupById(int id) {
		
		//To Fetch the data of a group using GroupID in an object for UPDATE purpose
		Groupdetail group=(Groupdetail)sessionFactory.getCurrentSession().get(Groupdetail.class,id);
		return group;
	}
	
	@Transactional //To automatically close session factory
	public List<Groupdetail> showGroupById(int id){
	@SuppressWarnings("unchecked")
	
	//To Fetch the data of a group in a list using GroupID
	List<Groupdetail> list=sessionFactory.getCurrentSession().createCriteria(Groupdetail.class).add(Restrictions.eq("id", id)).list();
	return list;
	}
}

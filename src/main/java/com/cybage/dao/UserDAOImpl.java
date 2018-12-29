package com.cybage.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.command.User;

//To automatically create bean of DAO IMPL at the time of component base-scan
@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired //Autowired to create object of session factory bean
	SessionFactory sessionFactory;
	
	@Transactional //To automatically close session factory
	
	//Show User's using Criteria API
	public List<User> showUser(){
		@SuppressWarnings("unchecked")
		List<User> list=sessionFactory.getCurrentSession().createCriteria(User.class).list();
		
		return list;
	}

	@Transactional
	public void insertUser(User user) {
		
		//Insert a User
		sessionFactory.getCurrentSession().persist(user);
		
	}

	@Transactional
	//Checking for already Existing user by UserName
	public boolean isUserExist(User user) {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("username", user.getUsername()));
		Object result = criteria.uniqueResult();
        if (result == null) 
           return false;//User does not Exist
        return true;//User Already Exist
	
	}

	@Transactional
	public User findUserById(int id) {
		
		//Find a User By UserID
		User user=(User)sessionFactory.getCurrentSession().get(User.class,id);
		return user;
	}

	@Transactional
	public void updateRoleAndStatus(User user) {
		//Update Role and Status of User
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}
	
	@Transactional
	public List<User> showUserByid(int id){
		@SuppressWarnings("unchecked")
		
		//Fetching a User from User Table using UserId 
		List<User> list=sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("id", id)).list();
		return list;
	}
}

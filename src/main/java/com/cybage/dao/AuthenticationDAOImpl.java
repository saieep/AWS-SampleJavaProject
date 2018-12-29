package com.cybage.dao;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.command.Groupdetail;
import com.cybage.command.User;

//To automatically create bean of DAO IMPL at the time of component base-scan
@Repository
public class AuthenticationDAOImpl implements AuthenticationDAO{
	
	@Autowired //Autowired to create object of session factory bean
	SessionFactory sessionFactory;
	
	@Transactional
	public List<Groupdetail> checkGroupid(Groupdetail groupdetail) {
		//Query to return Group Name
		String hql=" select group.groupname from Groupdetail group  where group.id=:groupid and group.password=:password and group.isactive=1";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("groupid", groupdetail.getId());
		query.setParameter("password", groupdetail.getPassword());
		@SuppressWarnings("unchecked")
		List<Groupdetail> result=query.list();
		return result;
		
	}

	@Transactional  //To automatically close session factory
	public List<User> checkUserId(User user) {
		String hql="select u.role from User u where u.id=:userid and u.password=:password and u.isactive=1";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("userid", user.getId());
		query.setParameter("password", user.getPassword());
		@SuppressWarnings("unchecked")
		List<User> list=query.list();
		return list;
	}

}

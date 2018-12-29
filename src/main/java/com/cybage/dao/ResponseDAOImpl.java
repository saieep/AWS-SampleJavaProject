package com.cybage.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.command.Response;
import com.cybage.command.ResponsePK;
import com.cybage.responsedto.ResponseResposeDTO;

//To automatically create bean of DAO IMPL at the time of component base-scan
@Repository 
public class ResponseDAOImpl implements ResponseDAO{

	@Autowired //Autowired to create object of session factory bean
	SessionFactory sessionFactory;
	
	@Transactional //To automatically close session factory
	public List<Response> showResponse() {
		@SuppressWarnings("unchecked")
		List<Response> list=sessionFactory.getCurrentSession().createCriteria(Response.class).list();
		
		return list;
	}

	@Transactional //To automatically close session factory
	public void insertResponse(Response response) {
		
		//To persist/insert data into Response Table
		sessionFactory.getCurrentSession().persist(response);
	}

	@Transactional //To automatically close session factory
	public void insertscore(Response response) {
		
		//To UPDATE score into Response Table
		sessionFactory.getCurrentSession().saveOrUpdate(response);
	}

	@Transactional //To automatically close session factory
	public Response findByGroupId(ResponsePK responsepk){
		
		//Passed object of ResponsePK because COMPOSITE KEY exists
		Response response=(Response) sessionFactory.getCurrentSession().get(Response.class, responsepk);
		return response;
	}
	
	
}

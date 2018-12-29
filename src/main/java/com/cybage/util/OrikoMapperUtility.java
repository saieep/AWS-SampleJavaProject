package com.cybage.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class OrikoMapperUtility {
	public  MapperFacade getMapper() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
				.build();
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper;
	}
	
	
}
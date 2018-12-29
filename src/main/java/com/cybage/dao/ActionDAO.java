package com.cybage.dao;

import java.util.List;

import com.cybage.command.Action;


public interface ActionDAO {
	public List<Action> showAction();//To show the data of Action Table
	public void insertAction( Action action);//To insert data into Action Table
}

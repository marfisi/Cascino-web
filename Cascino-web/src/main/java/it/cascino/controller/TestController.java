package it.cascino.controller;

import it.cascino.dao.TestDao;
import it.cascino.h8.entity.Test;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class TestController{
	
	@Inject
	private TestDao testDao;
	
	private String a;
	
	public void selTest(){
		Test test = testDao.getA(1);
		if(test != null){
			a = "Aggiunta test: " + test.getA();
		}else{
			a = "non ho trovato test!";
		}
	}
	
	public String getA(){
		return a;
	}
	
	public void setA(String a){
		this.a = a;
	}

}

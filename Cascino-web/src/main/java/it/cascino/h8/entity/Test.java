package it.cascino.h8.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Test{
	private int id;
	private String a; // character varying(5)

	@Id
	@GeneratedValue
	@Column(name = "id")
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getA(){
		return a;
	}
	
	public void setA(String a){
		this.a = a;
	}
}
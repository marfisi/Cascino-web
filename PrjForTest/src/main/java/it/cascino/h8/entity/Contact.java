package it.cascino.h8.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "contatti")
@SequenceGenerator(name = "CONTACT_SEQUENCE_GENERATOR", sequenceName = "contatti_id_seq", allocationSize = 1, initialValue = 1)
public class Contact implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTACT_SEQUENCE_GENERATOR")
	private Integer id;
	
	public Contact(){
	}

	public Integer getId(){
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public String getCognome(){
		return cognome;
	}
	
	public void setCognome(String cognome){
		this.cognome = cognome;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getProfessione(){
		return professione;
	}
	
	public void setProfessione(String professione){
		this.professione = professione;
	}
	
	public String getTelefono(){
		return telefono;
	}
	
	public void setTelefono(String telefono){
		this.telefono = telefono;
	}
	
	private String cognome;
	private String nome;
	private String professione;
	private String telefono;
	
}
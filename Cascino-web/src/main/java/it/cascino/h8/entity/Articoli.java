package it.cascino.h8.entity;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Articoli{
	private int id;
	private String codice; // character varying(15) NOT NULL
	private String nome; // character varying(35) NOT NULL
	private int tipo; // integer NOT NULL DEFAULT 1
	private String descrizione; // character varying(70)
	private int articoloFornitore; // integer
	private Date updtime;
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	@Column(nullable = false)
	public String getCodice(){
		return codice;
	}
	
	public void setCodice(String codice){
		this.codice = codice;
	}
	
	@Column(nullable = false)
	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	@Column(nullable = false)
	public int getTipo(){
		return tipo;
	}
	
	public void setTipo(int tipo){
		this.tipo = tipo;
	}
	
	public String getDescrizione(){
		return descrizione;
	}
	
	public void setDescrizione(String descrizione){
		this.descrizione = descrizione;
	}
	
	@Column(name = "articolo_fornitore")
	public int getArticoloFornitore(){
		return articoloFornitore;
	}
	
	public void setArticoloFornitore(int articoloFornitore){
		this.articoloFornitore = articoloFornitore;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdtime(){
		return updtime;
	}
	
	public void setUpdtime(Date updtime){
		this.updtime = updtime;
	}
}
package it.cascino.h8.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "articoli_foto")
public class ArticoliFoto{
	private int id;
	private int  articolo;	// integer
	private int   articoloFornitore; // integer
	private int   foto;	// integer NOT NULL
	private int   ordinamento;	// smallint NOT NULL DEFAULT 1
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
	
	public int getArticolo(){
		return articolo;
	}

	public void setArticolo(int articolo){
		this.articolo = articolo;
	}

	@Column(name = "articolo_fornitore")
	public int getArticoloFornitore(){
		return articoloFornitore;
	}

	public void setArticoloFornitore(int articoloFornitore){
		this.articoloFornitore = articoloFornitore;
	}

	@Column(nullable = false)
	public int getFoto(){
		return foto;
	}

	public void setFoto(int foto){
		this.foto = foto;
	}

	@Column(nullable = false)
	public int getOrdinamento(){
		return ordinamento;
	}

	public void setOrdinamento(int ordinamento){
		this.ordinamento = ordinamento;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdtime(){
		return updtime;
	}
	
	public void setUpdtime(Date updtime){
		this.updtime = updtime;
	}
}
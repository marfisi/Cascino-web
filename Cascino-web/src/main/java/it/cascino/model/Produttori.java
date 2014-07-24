package it.cascino.model;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.*;
import org.jboss.logging.Logger;
import java.sql.Timestamp;

/**
 * The persistent class for the produttori database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Produttori.findAll", query = "SELECT p FROM Produttori p"),
		@NamedQuery(name = "Produttori.findById", query = "SELECT p FROM Produttori p WHERE p.id = :id")
})
public class Produttori implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	private Integer id;
	private String nome;
	private String dati;
	private Integer foto;
	private Timestamp updtime;
	
	public Produttori(){
	}
	
	public Produttori(Integer id, String nome, String dati, Integer foto, Timestamp updtime){
		super();
		this.id = id;
		this.nome = nome;
		this.dati = dati;
		this.foto = foto;
		this.updtime = updtime;
	}
	
	@Id
	@SequenceGenerator(name = "PRODUTTORI_ID_GENERATOR", sequenceName = "PRODUTTORI_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUTTORI_ID_GENERATOR")
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getDati(){
		return this.dati;
	}
	
	public void setDati(String dati){
		this.dati = dati;
	}
	
	public Integer getFoto(){
		return this.foto;
	}
	
	public void setFoto(Integer foto){
		this.foto = foto;
	}
	
	@Transient
	@Temporal(TemporalType.TIMESTAMP)
	public Timestamp getUpdtime(){
		return this.updtime;
	}
	
	public void setUpdtime(Timestamp updtime){
		this.updtime = updtime;
	}
	
	@Override
	public String toString(){
		if(id == null){
			return "nd";
		}
		return nome + " " + dati;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Produttori){
			if(this.id == ((Produttori)obj).id){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		log.info("hashCode: ");
		return id != null ? this.getClass().hashCode() + id.hashCode() : super.hashCode();
	}
}
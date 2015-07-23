package it.cascino.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the articoli_parte_di.articoli_ingrosso database table.
 * 
 */
@Entity(name="articoli_ingrosso")
@Table(name="articoli_ingrosso", schema="articoli_parte_di")
@NamedQueries({
		@NamedQuery(name = "PgArticoliIngrosso.findAll", query = "SELECT a FROM articoli_ingrosso a order by a.articolo asc"),
		@NamedQuery(name = "PgArticoliIngrosso.findById", query = "SELECT a FROM articoli_ingrosso a WHERE a.id = :id"),
		@NamedQuery(name = "PgArticoliIngrosso.findByIdArticolo", query = "SELECT a FROM articoli_ingrosso a WHERE a.articolo = :idArticolo"),
		@NamedQuery(name = "PgArticoliIngrosso.svuota", query = "DELETE FROM  articoli_ingrosso a WHERE a.id != '0'")
})
public class PgArticoliIngrosso implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer articolo;
	private Timestamp updtime;
	
	public PgArticoliIngrosso(){
	}
	
	public PgArticoliIngrosso(Integer id, Integer articolo, Timestamp updtime){
		super();
		this.id = id;
		this.articolo = articolo;
		this.updtime = updtime;
	}
	
	@Id
//	@SequenceGenerator(name = "ARTICOLI_INGROSSO_ID_GENERATOR", schema="articoli_parte_di", sequenceName = "articoli_ingrosso_id_seq", allocationSize = 1, initialValue = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICOLI_INGROSSO_ID_GENERATOR")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getArticolo(){
		return articolo;
	}
	
	public void setArticolo(Integer articolo){
		this.articolo = articolo;
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
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1));
		stringBuilder.append("[");
		if(id != null){
			stringBuilder.append("id=" + id).append(", ");
			stringBuilder.append("articolo=" + articolo);
		}else{
			stringBuilder.append("id=1");
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof PgArticoliIngrosso){
			if(this.id == ((PgArticoliIngrosso)obj).id){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((articolo == null) ? 0 : articolo.hashCode());
		return result;
	}
}
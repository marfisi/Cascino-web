package it.cascino.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.inject.Inject;
import javax.persistence.*;
import org.jboss.logging.Logger;
import java.sql.Timestamp;

/**
 * The persistent class for the caratteristiche database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Caratteristiche.findAll", query = "SELECT c FROM Caratteristiche c"),
		@NamedQuery(name = "Caratteristiche.findById", query = "SELECT c FROM Caratteristiche c WHERE c.id = :id"),
		@NamedQuery(name = "Caratteristiche.findByIdArticolo", query = "SELECT c FROM Caratteristiche c WHERE c.articolo = :idArticolo order by classe asc, unita_misura asc, scala*qty asc, valore asc, descrizione asc")
})
public class Caratteristiche implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	private Integer id;
	private Integer articolo;
	private String classe;
	private String unitaMisura;
	private Integer scala;
	private BigDecimal qty;
	private String valore;
	private String descrizione;
	private Timestamp updtime;
	
	public Caratteristiche(){
	}
	
	public Caratteristiche(Integer id, Integer articolo, String classe, String unitaMisura, Integer scala, BigDecimal qty, String valore, String descrizione, Timestamp updtime){
		super();
		this.id = id;
		this.articolo = articolo;
		this.classe = classe;
		this.unitaMisura = unitaMisura;
		this.scala = scala;
		this.qty = qty;
		this.valore = valore;
		this.descrizione = descrizione;
		this.updtime = updtime;
	}
	
	@Id
	@SequenceGenerator(name = "CARATTERISTICHE_ID_GENERATOR", sequenceName = "CARATTERISTICHE_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARATTERISTICHE_ID_GENERATOR")
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
	
	public String getClasse(){
		return classe;
	}
	
	public void setClasse(String classe){
		this.classe = classe;
	}
	
	@Column(name = "unita_misura")
	public String getUnitaMisura(){
		return unitaMisura;
	}
	
	public void setUnitaMisura(String unitaMisura){
		this.unitaMisura = unitaMisura;
	}
	
	public Integer getScala(){
		return scala;
	}
	
	public void setScala(Integer scala){
		this.scala = scala;
	}
	
	public BigDecimal getQty(){
		return qty;
	}
	
	public void setQty(BigDecimal qty){
		this.qty = qty;
	}
	
	public String getValore(){
		return valore;
	}
	
	public void setValore(String valore){
		this.valore = valore;
	}
	
	public String getDescrizione(){
		return this.descrizione;
	}
	
	public void setDescrizione(String descrizione){
		this.descrizione = descrizione;
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
		if(log != null){
			log.info("tmpDEBUGtmp: " + "> " + "toString(" + ")");
			log.info("tmpDEBUGtmp: " + "id: " + id);
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1));
		stringBuilder.append("[");
		if(id != null){
			stringBuilder.append("id=" + id).append(", ");
			stringBuilder.append("articolo=" + articolo).append(", ");
			stringBuilder.append("classe=" + classe).append(", ");
			stringBuilder.append("unitaMisura=" + unitaMisura).append(", ");
			stringBuilder.append("scala=" + scala).append(", ");
			stringBuilder.append("qty=" + qty).append(", ");
			stringBuilder.append("valore=" + valore).append(", ");
			stringBuilder.append("descrizione=" + descrizione);
		}else{
			stringBuilder.append("id=1");
		}
		stringBuilder.append("]");
		if(log != null){
			log.info("tmpDEBUGtmp: " + "< " + "toString");
		}
		return stringBuilder.toString();
	}
	
	@Override
	public boolean equals(Object obj){
		if(log != null){
			log.info("tmpDEBUGtmp: " + "> " + "equals(" + obj + ")");
			log.info("tmpDEBUGtmp: " + "id: " + id);
		}
		if(obj instanceof Caratteristiche){
			if(this.id == ((Caratteristiche)obj).id){
				return true;
			}else{
				return false;
			}
		}
		if(log != null){
			log.info("tmpDEBUGtmp: " + "< " + "equals");
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		if(log != null){
			log.info("tmpDEBUGtmp: " + "> " + "hashCode(" + ")");
			log.info("tmpDEBUGtmp: " + "id: " + id);
		}
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((articolo == null) ? 0 : articolo.hashCode());
		result = prime * result + ((classe == null) ? 0 : classe.hashCode());
		result = prime * result + ((unitaMisura == null) ? 0 : unitaMisura.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((scala == null) ? 0 : scala.hashCode());
		result = prime * result + ((qty == null) ? 0 : qty.hashCode());
		result = prime * result + ((valore == null) ? 0 : valore.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		if(log != null){
			log.info("tmpDEBUGtmp: " + "< " + "hashCode");
		}
		return result;
	}
}
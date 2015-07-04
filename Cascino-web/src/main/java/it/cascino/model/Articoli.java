package it.cascino.model;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.*;
import org.jboss.logging.Logger;
import java.sql.Timestamp;

/**
 * The persistent class for the articoli database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Articoli.findAll", query = "SELECT a FROM Articoli a"),
	@NamedQuery(name = "Articoli.findAllOrderCod", query = "SELECT a FROM Articoli a order by a.codice asc"),
	@NamedQuery(name = "Articoli.findById", query = "SELECT a FROM Articoli a WHERE a.id = :id"),
	@NamedQuery(name = "Articoli.findByCodice", query = "SELECT a FROM Articoli a WHERE a.codice = :codice")
})
public class Articoli implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	private Integer id;
	private String codice;
	private String descrizioneAs400;
	private String nome;
	private String descrizione;
	private Integer produttore;
	private String modello;
	private Integer articoloFornitore;
	private Integer tipo;
	private Timestamp updtime;
	
	public Articoli(){
	}
	
	public Articoli(Integer id, String codice, String descrizioneAs400, String nome, String descrizione, String modello, Integer produttore,  Integer articoloFornitore, Integer tipo, Timestamp updtime){
		super();
		this.id = id;
		this.codice = codice;
		this.descrizioneAs400 = descrizioneAs400;
		this.nome = nome;
		this.descrizione = descrizione;
		this.produttore = produttore;
		this.modello = modello;
		this.articoloFornitore = articoloFornitore;
		this.tipo = tipo;
		this.updtime = updtime;
	}
	
	@Id
	@SequenceGenerator(name = "ARTICOLI_ID_GENERATOR", sequenceName = "ARTICOLI_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICOLI_ID_GENERATOR")
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public String getCodice(){
		return this.codice;
	}
	
	public void setCodice(String codice){
		this.codice = codice;
	}
	
	@Column(name = "descrizioneAS400")
	public String getDescrizioneAs400(){
		return this.descrizioneAs400;
	}
	
	public void setDescrizioneAs400(String descrizioneAs400){
		this.descrizioneAs400 = descrizioneAs400;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getDescrizione(){
		return this.descrizione;
	}
	
	public void setDescrizione(String descrizione){
		this.descrizione = descrizione;
	}
	
	public Integer getProduttore(){
		return this.produttore;
	}
	
	public void setProduttore(Integer produttore){
		this.produttore = produttore;
	}
	
	public String getModello(){
		return this.modello;
	}
	
	public void setModello(String modello){
		this.modello = modello;
	}
	
	@Column(name = "articolo_fornitore")
	public Integer getArticoloFornitore(){
		return this.articoloFornitore;
	}
	
	public void setArticoloFornitore(Integer articoloFornitore){
		this.articoloFornitore = articoloFornitore;
	}
	
	public Integer getTipo(){
		return this.tipo;
	}
	
	public void setTipo(Integer tipo){
		this.tipo = tipo;
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
			stringBuilder.append("codice=" + codice).append(", ");
			stringBuilder.append("descrizioneAs400=" + descrizioneAs400).append(", ");
			stringBuilder.append("nome=" + nome).append(", ");
			stringBuilder.append("descrizione=" + descrizione).append(", ");
			stringBuilder.append("produttore=" + produttore).append(", ");
			stringBuilder.append("modello=" + modello).append(", ");
			stringBuilder.append("articoloFornitore=" + articoloFornitore).append(", ");
			stringBuilder.append("tipo=" + tipo);
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
		if(obj instanceof Articoli){
			if(this.id == ((Articoli)obj).id){
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
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
		result = prime * result + ((descrizioneAs400 == null) ? 0 : descrizioneAs400.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((produttore == null) ? 0 : produttore.hashCode());
		result = prime * result + ((modello == null) ? 0 : modello.hashCode());
		result = prime * result + ((articoloFornitore == null) ? 0 : articoloFornitore.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		if(log != null){
			log.info("tmpDEBUGtmp: " + "< " + "hashCode");
		}
		return result;
	}
	
//	// mi servono per in menu p:selectOneMenu
//	
//	private Tipi tipoMenu;
//	
//	@Transient
//	public Tipi getTipoMenu(){
//		return tipoMenu;
//	}
//	
//	public void setTipoMenu(Tipi tipoMenu){
//		this.tipoMenu = tipoMenu;
//		tipo = tipoMenu.getId();
//	}
//	
//	private Produttori produttoreMenu;
//	
//	@Transient
//	public Produttori getProduttoreMenu(){
//		return produttoreMenu;
//	}
//	
//	public void setProduttoreMenu(Produttori produttoreMenu){
//		this.produttoreMenu = produttoreMenu;
//		produttore = produttoreMenu.getId();
//	}
	
}
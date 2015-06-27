package it.cascino.model;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.*;
import org.jboss.logging.Logger;
import java.sql.Timestamp;

/**
 * The persistent class for the articoli_allegati database table.
 * 
 */
@Entity(name = "articoli_allegati")
@NamedQueries({
		@NamedQuery(name = "ArticoliAllegati.findAll", query = "SELECT a FROM articoli_allegati a"),
		@NamedQuery(name = "ArticoliAllegati.findById", query = "SELECT a FROM articoli_allegati a WHERE a.id = :id"),
		@NamedQuery(name = "ArticoliAllegati.findByIdArtIdAllegato", query = "SELECT a FROM articoli_allegati a WHERE a.articolo = :idArt and a.allegato = :idAllegato"),
		@NamedQuery(name = "ArticoliAllegati.findByIdArtIdAllegatoOrd", query = "SELECT a FROM articoli_allegati a WHERE a.articolo = :idArt and a.allegato = :idAllegato and a.ordinamento = :ord")
})
public class ArticoliAllegati implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	private Integer id;
	private Integer articolo;
	private Integer articoloFornitore;
	private Integer allegato;
	private Integer ordinamento;
	private Timestamp updtime;
	
	public ArticoliAllegati(){
	}
	
	public ArticoliAllegati(Integer id, Integer articolo, Integer articoloFornitore, Integer allegato, Integer ordinamento, Timestamp updtime){
		super();
		this.id = id;
		this.articolo = articolo;
		this.articoloFornitore = articoloFornitore;
		this.allegato = allegato;
		this.ordinamento = ordinamento;
		this.updtime = updtime;
	}
	
	@Id
	@SequenceGenerator(name = "ARTICOLI_ALLEGATI_ID_GENERATOR", sequenceName = "ARTICOLI_ALLEGATI_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICOLI_ALLEGATI_ID_GENERATOR")
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
	
	@Column(name = "articolo_fornitore")
	public Integer getArticoloFornitore(){
		return articoloFornitore;
	}
	
	public void setArticoloFornitore(Integer articoloFornitore){
		this.articoloFornitore = articoloFornitore;
	}
	
	public Integer getAllegato(){
		return allegato;
	}
	
	public void setAllegato(Integer allegato){
		this.allegato = allegato;
	}
	
	public Integer getOrdinamento(){
		return ordinamento;
	}
	
	public void setOrdinamento(Integer ordinamento){
		this.ordinamento = ordinamento;
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
			stringBuilder.append("articoloFornitore=" + articoloFornitore).append(", ");
			stringBuilder.append("allegato=" + allegato).append(", ");
			stringBuilder.append("ordinamento=" + ordinamento);
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
		if(obj instanceof ArticoliAllegati){
			if(this.id == ((ArticoliAllegati)obj).id){
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
		result = prime * result + ((articoloFornitore == null) ? 0 : articoloFornitore.hashCode());
		result = prime * result + ((allegato == null) ? 0 : allegato.hashCode());
		result = prime * result + ((ordinamento == null) ? 0 : ordinamento.hashCode());
		if(log != null){
			log.info("tmpDEBUGtmp: " + "< " + "hashCode");
		}
		return result;
	}
}
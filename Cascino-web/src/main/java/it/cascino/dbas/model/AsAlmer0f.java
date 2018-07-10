package it.cascino.dbas.model;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.*;
import org.jboss.logging.Logger;
import it.cascino.dbas.model.pkey.AsAlmer0fPKey;
/**
* The persistent class for the cas_dat/almer0f database table.
* 
*/
@Entity(name="Almer0f")
@NamedQueries({
	@NamedQuery(name = "AsAlmer0f.findAll", query = "SELECT a FROM Almer0f a WHERE a.id.amset in ('C', 'D') order by a.id.amset, a.id.amgru, a.id.amsot, a.id.amfam, a.id.amstf, a.id.amst1"),
	@NamedQuery(name = "AsAlmer0f.findById", query = "SELECT a FROM Almer0f a WHERE a.id.amset = :amset and a.id.amgru = :amgru and a.id.amsot = :amsot and a.id.amfam = :amfam and a.id.amstf = :amstf and a.id.amst1 = :amst1")
})
public class AsAlmer0f implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	@Transient
	@Inject
	private Logger log;
	
	@EmbeddedId
	private AsAlmer0fPKey id;
	private String amdes;
	
	public AsAlmer0f(){
	}

	public AsAlmer0f(AsAlmer0fPKey id, String amdes){
		super();
		this.id = id;
		this.amdes = amdes;
	}

	public AsAlmer0fPKey getId(){
		return id;
	}

	public void setId(AsAlmer0fPKey id){
		this.id = id;
	}

	public String getAmdes(){
		return amdes;
	}

	public void setAmdes(String amdes){
		this.amdes = amdes;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amdes == null) ? 0 : amdes.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(log != null){
			log.info("tmpDEBUGtmp: " + "> " + "equals(" + obj + ")");
			log.info("tmpDEBUGtmp: " + "id: " + id);
		}
		if(obj instanceof AsAlmer0f) {
			if(this.id == ((AsAlmer0f)obj).id) {
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
	public String toString(){
		return "AsAlmer0f [id=" + id + ", amdes=" + amdes + "]";
	}
}
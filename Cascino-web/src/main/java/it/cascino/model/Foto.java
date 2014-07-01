package it.cascino.model;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.*;
import org.jboss.logging.Logger;
import java.sql.Timestamp;

/**
 * The persistent class for the foto database table.
 * 
 */
@Entity
@NamedQuery(name = "Foto.findAll", query = "SELECT f FROM Foto f")
public class Foto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	private Integer id;
	private String path;
	private String originale;
	private String grande;
	private String grandeWatermark;
	private String thumbnail;
	private String thumbnailWatermark;
	private Timestamp updtime;
	
	public Foto(){
	}
	
	public Foto(Integer id, String path, String originale, String grande, String grandeWatermark, String thumbnail, String thumbnailWatermark, Timestamp updtime){
		super();
		this.id = id;
		this.path = path;
		this.originale = originale;
		this.grande = grande;
		this.grandeWatermark = grandeWatermark;
		this.thumbnail = thumbnail;
		this.thumbnailWatermark = thumbnailWatermark;
		this.updtime = updtime;
	}
	
	@Id
	@SequenceGenerator(name = "FOTO_ID_GENERATOR", sequenceName = "FOTO_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FOTO_ID_GENERATOR")
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public String getPath(){
		return this.path;
	}
	
	public void setPath(String path){
		this.path = path;
	}
	
	public String getOriginale(){
		return this.originale;
	}
	
	public void setOriginale(String originale){
		this.originale = originale;
	}
	
	public String getGrande(){
		return this.grande;
	}
	
	public void setGrande(String grande){
		this.grande = grande;
	}
	
	@Column(name = "grande_watermark")
	public String getGrandeWatermark(){
		return this.grandeWatermark;
	}
	
	public void setGrandeWatermark(String grandeWatermark){
		this.grandeWatermark = grandeWatermark;
	}
	
	public String getThumbnail(){
		return this.thumbnail;
	}
	
	public void setThumbnail(String thumbnail){
		this.thumbnail = thumbnail;
	}
	
	@Column(name = "thumbnail_watermark")
	public String getThumbnailWatermark(){
		return this.thumbnailWatermark;
	}
	
	public void setThumbnailWatermark(String thumbnailWatermark){
		this.thumbnailWatermark = thumbnailWatermark;
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
		// log.info("toString: " + id);
		if(id == null){
//			log.warn("toString: " + "id==null");
			return "nd";
		}
		return path + "\\" + originale;
	}
	
	@Override
	public boolean equals(Object obj){
		// log.info("equals: " + obj);
		if(obj instanceof Foto){
			if(this.id == ((Foto)obj).id){
				return true;
			}else{
				return false;
			}
		}else if(obj instanceof Integer){
			if(this.id == obj){
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
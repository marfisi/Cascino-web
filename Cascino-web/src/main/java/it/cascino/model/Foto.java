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
@NamedQueries({
		@NamedQuery(name = "Foto.findAll", query = "SELECT f FROM Foto f"),
		@NamedQuery(name = "Foto.findById", query = "SELECT f FROM Foto f WHERE f.id = :id"),
		@NamedQuery(name = "Foto.findMostra", query = "SELECT f FROM Foto f WHERE f.cosaMostra = :cosa_mostra")
})
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
	private String colore1;
	private String colore2;
	private String forma;
	private String cosaMostra; // es, P=prodotto, L=logo, S=scheda, A=altro, I=indefinito
	private String tag;
	private Timestamp updtime;
	
	public Foto(){
	}
	
	public Foto(Integer id, String path, String originale, String grande, String grandeWatermark, String thumbnail, String thumbnailWatermark, String colore1, String colore2, String forma, String cosaMostra, String tag, Timestamp updtime){
		super();
		this.id = id;
		this.path = path;
		this.originale = originale;
		this.grande = grande;
		this.grandeWatermark = grandeWatermark;
		this.thumbnail = thumbnail;
		this.thumbnailWatermark = thumbnailWatermark;
		this.colore1 = colore1;
		this.colore2 = colore2;
		this.forma = forma;
		this.cosaMostra = cosaMostra;
		this.tag = tag;
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
	
	public String getColore1(){
		return colore1;
	}

	public void setColore1(String colore1){
		this.colore1 = colore1;
	}	
	
	public String getColore2(){
		return colore2;
	}

	public void setColore2(String colore2){
		this.colore2 = colore2;
	}

	public String getForma(){
		return forma;
	}

	public void setForma(String forma){
		this.forma = forma;
	}

	@Column(name="cosa_mostra")
	public String getCosaMostra(){
		return cosaMostra;
	}

	public void setCosaMostra(String cosaMostra){
		this.cosaMostra = cosaMostra;
	}

	public String getTag(){
		return tag;
	}

	public void setTag(String tag){
		this.tag = tag;
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
			stringBuilder.append("path=" + path).append(", ");
			stringBuilder.append("originale=" + originale).append(", ");
			stringBuilder.append("grande=" + grande).append(", ");
			stringBuilder.append("grandeWatermark=" + grandeWatermark).append(", ");
			stringBuilder.append("thumbnail=" + thumbnail).append(", ");
			stringBuilder.append("thumbnailWatermark=" + thumbnailWatermark).append(", ");
			stringBuilder.append("colore1=" + colore1).append(", ");
			stringBuilder.append("colore2=" + colore2).append(", ");
			stringBuilder.append("forma=" + forma).append(", ");
			stringBuilder.append("cosaMostra=" + cosaMostra).append(", ");
			stringBuilder.append("tag=" + tag);
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
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((originale == null) ? 0 : originale.hashCode());
		result = prime * result + ((grande == null) ? 0 : grande.hashCode());
		result = prime * result + ((grandeWatermark == null) ? 0 : grandeWatermark.hashCode());
		result = prime * result + ((thumbnail == null) ? 0 : thumbnail.hashCode());
		result = prime * result + ((thumbnailWatermark == null) ? 0 : thumbnailWatermark.hashCode());
		result = prime * result + ((colore1 == null) ? 0 : colore1.hashCode());
		result = prime * result + ((colore2 == null) ? 0 : colore2.hashCode());
		result = prime * result + ((forma == null) ? 0 : forma.hashCode());
		result = prime * result + ((cosaMostra == null) ? 0 : cosaMostra.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		if(log != null){
			log.info("tmpDEBUGtmp: " + "< " + "hashCode");
		}
		return result;
	}
}
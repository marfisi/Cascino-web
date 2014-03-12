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
@Table
public class Foto{
	private int id;
	private String path; // character varying(80)
	private String originale; // character varying(50) NOT NULL
	private String grande; // character varying(50)
	private String grandeWatermark; // character varying(50)
	private String thumbnail; // character varying(50)
	private String thumbnailWatermark; // character varying(50)
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
	
	public String getPath(){
		return path;
	}
	
	public void setPath(String path){
		this.path = path;
	}
	
	@Column(nullable = false)
	public String getOriginale(){
		return originale;
	}
	
	public void setOriginale(String originale){
		this.originale = originale;
	}
	
	public String getGrande(){
		return grande;
	}
	
	public void setGrande(String grande){
		this.grande = grande;
	}
	
	@Column(name = "grande_watermark")
	public String getGrandeWatermark(){
		return grandeWatermark;
	}
	
	public void setGrandeWatermark(String grandeWatermark){
		this.grandeWatermark = grandeWatermark;
	}
	
	public String getThumbnail(){
		return thumbnail;
	}
	
	public void setThumbnail(String thumbnail){
		this.thumbnail = thumbnail;
	}
	
	@Column(name = "thumbnail_watermark")
	public String getThumbnailWatermark(){
		return thumbnailWatermark;
	}
	
	public void setThumbnailWatermark(String thumbnailWatermark){
		this.thumbnailWatermark = thumbnailWatermark;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdtime(){
		return updtime;
	}
	
	public void setUpdtime(Date updtime){
		this.updtime = updtime;
	}
}
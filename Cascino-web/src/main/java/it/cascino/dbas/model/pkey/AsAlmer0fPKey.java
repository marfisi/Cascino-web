package it.cascino.dbas.model.pkey;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class AsAlmer0fPKey implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String amset;
	private String amgru;
	private String amsot;
	private String amfam;
	private String amstf;
	private String amst1;
	
	public AsAlmer0fPKey(){
	}

	public AsAlmer0fPKey(String amset, String amgru, String amsot, String amfam, String amstf, String amst1){
		super();
		this.amset = amset;
		this.amgru = amgru;
		this.amsot = amsot;
		this.amfam = amfam;
		this.amstf = amstf;
		this.amst1 = amst1;
	}

	public String getAmset(){
		return amset;
	}

	public void setAmset(String amset){
		this.amset = amset;
	}

	public String getAmgru(){
		return amgru;
	}

	public void setAmgru(String amgru){
		this.amgru = amgru;
	}

	public String getAmsot(){
		return amsot;
	}

	public void setAmsot(String amsot){
		this.amsot = amsot;
	}

	public String getAmfam(){
		return amfam;
	}

	public void setAmfam(String amfam){
		this.amfam = amfam;
	}

	public String getAmstf(){
		return amstf;
	}

	public void setAmstf(String amstf){
		this.amstf = amstf;
	}

	public String getAmst1(){
		return amst1;
	}

	public void setAmst1(String amst1){
		this.amst1 = amst1;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amfam == null) ? 0 : amfam.hashCode());
		result = prime * result + ((amgru == null) ? 0 : amgru.hashCode());
		result = prime * result + ((amset == null) ? 0 : amset.hashCode());
		result = prime * result + ((amsot == null) ? 0 : amsot.hashCode());
		result = prime * result + ((amst1 == null) ? 0 : amst1.hashCode());
		result = prime * result + ((amstf == null) ? 0 : amstf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof AsAlmer0fPKey){
			if((this.amset == ((AsAlmer0fPKey)obj).amset)&&(this.amgru == ((AsAlmer0fPKey)obj).amgru)&&(this.amsot == ((AsAlmer0fPKey)obj).amsot)&&(this.amfam == ((AsAlmer0fPKey)obj).amfam)&&(this.amstf == ((AsAlmer0fPKey)obj).amstf)&&(this.amst1 == ((AsAlmer0fPKey)obj).amst1)){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	@Override
	public String toString(){
		return "AsAlmer0fPKey [amset=" + amset + ", amgru=" + amgru + ", amsot=" + amsot + ", amfam=" + amfam + ", amstf=" + amstf + ", amst1=" + amst1 + "]";
	}
}

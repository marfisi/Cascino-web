package it.cascino.dbas.model.pkey;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class AsCaratPKey implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String mcoda;
	private String crtip;
	
	public AsCaratPKey(){
	}

	public AsCaratPKey(String mcoda, String crtip){
		super();
		this.mcoda = mcoda;
		this.crtip = crtip;
	}

	public String getMcoda(){
		return mcoda;
	}

	public void setMcoda(String mcoda){
		this.mcoda = mcoda;
	}

	public String getCrtip(){
		return crtip;
	}

	public void setCrtip(String crtip){
		this.crtip = crtip;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mcoda == null) ? 0 : mcoda.hashCode());
		result = prime * result + ((crtip == null) ? 0 : crtip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof AsCaratPKey){
			if((this.mcoda == ((AsCaratPKey)obj).mcoda)&&(this.crtip == ((AsCaratPKey)obj).crtip)){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	@Override
	public String toString(){
		return "AsCaratKey [mcoda=" + mcoda + ", crtip=" + crtip + "]";
	}
}

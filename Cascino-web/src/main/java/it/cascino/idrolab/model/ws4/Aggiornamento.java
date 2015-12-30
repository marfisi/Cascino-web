//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine.
// Generato il: 2015.11.19 alle 09:28:53 AM CET
//

package it.cascino.idrolab.model.ws4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"data", "smaCodice", "smaDescrizione"
})
@XmlRootElement(name = "aggiornamento")
public class Aggiornamento{
	
	@XmlElement(required = true)
	protected String data;
	@XmlElement(name = "sma_codice", required = true)
	protected String smaCodice;
	@XmlElement(name = "sma_descrizione", required = true)
	protected String smaDescrizione;
	
	/**
	 * Recupera il valore della proprietà data.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getData(){
		return data;
	}
	
	/**
	 * Imposta il valore della proprietà data.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setData(String value){
		this.data = value;
	}
	
	/**
	 * Recupera il valore della proprietà smaCodice.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getSmaCodice(){
		return smaCodice;
	}
	
	/**
	 * Imposta il valore della proprietà smaCodice.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setSmaCodice(String value){
		this.smaCodice = value;
	}
	
	/**
	 * Recupera il valore della proprietà smaDescrizione.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getSmaDescrizione(){
		return smaDescrizione;
	}
	
	/**
	 * Imposta il valore della proprietà smaDescrizione.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setSmaDescrizione(String value){
		this.smaDescrizione = value;
	}
	
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((smaCodice == null) ? 0 : smaCodice.hashCode());
		result = prime * result + ((smaDescrizione == null) ? 0 : smaDescrizione.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Aggiornamento other = (Aggiornamento)obj;
		if(data == null) {
			if(other.data != null)
				return false;
		}else if(!data.equals(other.data))
			return false;
		if(smaCodice == null) {
			if(other.smaCodice != null)
				return false;
		}else if(!smaCodice.equals(other.smaCodice))
			return false;
		if(smaDescrizione == null) {
			if(other.smaDescrizione != null)
				return false;
		}else if(!smaDescrizione.equals(other.smaDescrizione))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return "Aggiornamento [data=" + data + ", smaCodice=" + smaCodice + ", smaDescrizione=" + smaDescrizione + "]";
	}
	
}

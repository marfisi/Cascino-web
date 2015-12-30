//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine.
// Generato il: 2015.11.19 alle 09:28:53 AM CET
//

package it.cascino.idrolab.model.ws4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"marCodice", "marDescrizione", "aggiornamenti"
})
@XmlRootElement(name = "marca")
public class Marca{
	
	@XmlAttribute(name = "id", required = true)
	@XmlJavaTypeAdapter(NormalizedStringAdapter.class)
	protected String id;
	@XmlElement(name = "mar_codice", required = true)
	protected String marCodice;
	@XmlElement(name = "mar_descrizione", required = true)
	protected String marDescrizione;
	@XmlElement(required = true)
	protected Aggiornamenti aggiornamenti;
	
	/**
	 * Recupera il valore della proprietà id.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getId(){
		return id;
	}
	
	/**
	 * Imposta il valore della proprietà id.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setId(String value){
		this.id = value;
	}
	
	/**
	 * Recupera il valore della proprietà marCodice.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getMarCodice(){
		return marCodice;
	}
	
	/**
	 * Imposta il valore della proprietà marCodice.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setMarCodice(String value){
		this.marCodice = value;
	}
	
	/**
	 * Recupera il valore della proprietà marDescrizione.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getMarDescrizione(){
		return marDescrizione;
	}
	
	/**
	 * Imposta il valore della proprietà marDescrizione.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setMarDescrizione(String value){
		this.marDescrizione = value;
	}
	
	/**
	 * Recupera il valore della proprietà aggiornamenti.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Aggiornamenti }
	 *     
	 */
	public Aggiornamenti getAggiornamenti(){
		return aggiornamenti;
	}
	
	/**
	 * Imposta il valore della proprietà aggiornamenti.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Aggiornamenti }
	 *     
	 */
	public void setAggiornamenti(Aggiornamenti value){
		this.aggiornamenti = value;
	}
	
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aggiornamenti == null) ? 0 : aggiornamenti.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((marCodice == null) ? 0 : marCodice.hashCode());
		result = prime * result + ((marDescrizione == null) ? 0 : marDescrizione.hashCode());
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
		Marca other = (Marca)obj;
		if(aggiornamenti == null) {
			if(other.aggiornamenti != null)
				return false;
		}else if(!aggiornamenti.equals(other.aggiornamenti))
			return false;
		if(id == null) {
			if(other.id != null)
				return false;
		}else if(!id.equals(other.id))
			return false;
		if(marCodice == null) {
			if(other.marCodice != null)
				return false;
		}else if(!marCodice.equals(other.marCodice))
			return false;
		if(marDescrizione == null) {
			if(other.marDescrizione != null)
				return false;
		}else if(!marDescrizione.equals(other.marDescrizione))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return "Marca [id=" + id + ", marCodice=" + marCodice + ", marDescrizione=" + marDescrizione + ", aggiornamenti=" + aggiornamenti + "]";
	}
	
}

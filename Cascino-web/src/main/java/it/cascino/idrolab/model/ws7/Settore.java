//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 09:31:55 AM CET 
//


package it.cascino.idrolab.model.ws7;

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
@XmlType(name = "", propOrder = {
    "setCodice",
    "setDescrizione"
})
@XmlRootElement(name = "settore")
public class Settore {

    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String id;
    @XmlElement(name = "set_codice", required = true)
    protected String setCodice;
    @XmlElement(name = "set_descrizione", required = true)
    protected String setDescrizione;

    /**
     * Recupera il valore della proprietà id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
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
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprietà setCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSetCodice() {
        return setCodice;
    }

    /**
     * Imposta il valore della proprietà setCodice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSetCodice(String value) {
        this.setCodice = value;
    }

    /**
     * Recupera il valore della proprietà setDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSetDescrizione() {
        return setDescrizione;
    }

    /**
     * Imposta il valore della proprietà setDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSetDescrizione(String value) {
        this.setDescrizione = value;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((setCodice == null) ? 0 : setCodice.hashCode());
		result = prime * result + ((setDescrizione == null) ? 0 : setDescrizione.hashCode());
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
		Settore other = (Settore)obj;
		if(id == null) {
			if(other.id != null)
				return false;
		}else if(!id.equals(other.id))
			return false;
		if(setCodice == null) {
			if(other.setCodice != null)
				return false;
		}else if(!setCodice.equals(other.setCodice))
			return false;
		if(setDescrizione == null) {
			if(other.setDescrizione != null)
				return false;
		}else if(!setDescrizione.equals(other.setDescrizione))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Settore [id=" + id + ", setCodice=" + setCodice + ", setDescrizione=" + setDescrizione + "]";
	}

    
}

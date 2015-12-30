//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 09:32:53 AM CET 
//


package it.cascino.idrolab.model.ws8;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "setCodice",
    "macCodice",
    "macDescrizione"
})
@XmlRootElement(name = "macrofamiglia")
public class Macrofamiglia {

    @XmlElement(name = "set_codice", required = true)
    protected String setCodice;
    @XmlElement(name = "mac_codice", required = true)
    protected String macCodice;
    @XmlElement(name = "mac_descrizione", required = true)
    protected String macDescrizione;

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
     * Recupera il valore della proprietà macCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMacCodice() {
        return macCodice;
    }

    /**
     * Imposta il valore della proprietà macCodice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMacCodice(String value) {
        this.macCodice = value;
    }

    /**
     * Recupera il valore della proprietà macDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMacDescrizione() {
        return macDescrizione;
    }

    /**
     * Imposta il valore della proprietà macDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMacDescrizione(String value) {
        this.macDescrizione = value;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((macCodice == null) ? 0 : macCodice.hashCode());
		result = prime * result + ((macDescrizione == null) ? 0 : macDescrizione.hashCode());
		result = prime * result + ((setCodice == null) ? 0 : setCodice.hashCode());
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
		Macrofamiglia other = (Macrofamiglia)obj;
		if(macCodice == null) {
			if(other.macCodice != null)
				return false;
		}else if(!macCodice.equals(other.macCodice))
			return false;
		if(macDescrizione == null) {
			if(other.macDescrizione != null)
				return false;
		}else if(!macDescrizione.equals(other.macDescrizione))
			return false;
		if(setCodice == null) {
			if(other.setCodice != null)
				return false;
		}else if(!setCodice.equals(other.setCodice))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Macrofamiglia [setCodice=" + setCodice + ", macCodice=" + macCodice + ", macDescrizione=" + macDescrizione + "]";
	}

}

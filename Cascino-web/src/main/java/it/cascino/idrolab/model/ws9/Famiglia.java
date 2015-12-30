//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 09:34:07 AM CET 
//


package it.cascino.idrolab.model.ws9;

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
    "famCodice",
    "famDescrizione"
})
@XmlRootElement(name = "famiglia")
public class Famiglia {

    @XmlElement(name = "set_codice", required = true)
    protected String setCodice;
    @XmlElement(name = "mac_codice", required = true)
    protected String macCodice;
    @XmlElement(name = "fam_codice", required = true)
    protected String famCodice;
    @XmlElement(name = "fam_descrizione", required = true)
    protected String famDescrizione;

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
     * Recupera il valore della proprietà famCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamCodice() {
        return famCodice;
    }

    /**
     * Imposta il valore della proprietà famCodice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamCodice(String value) {
        this.famCodice = value;
    }

    /**
     * Recupera il valore della proprietà famDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamDescrizione() {
        return famDescrizione;
    }

    /**
     * Imposta il valore della proprietà famDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamDescrizione(String value) {
        this.famDescrizione = value;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((famCodice == null) ? 0 : famCodice.hashCode());
		result = prime * result + ((famDescrizione == null) ? 0 : famDescrizione.hashCode());
		result = prime * result + ((macCodice == null) ? 0 : macCodice.hashCode());
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
		Famiglia other = (Famiglia)obj;
		if(famCodice == null) {
			if(other.famCodice != null)
				return false;
		}else if(!famCodice.equals(other.famCodice))
			return false;
		if(famDescrizione == null) {
			if(other.famDescrizione != null)
				return false;
		}else if(!famDescrizione.equals(other.famDescrizione))
			return false;
		if(macCodice == null) {
			if(other.macCodice != null)
				return false;
		}else if(!macCodice.equals(other.macCodice))
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
		return "Famiglia [setCodice=" + setCodice + ", macCodice=" + macCodice + ", famCodice=" + famCodice + ", famDescrizione=" + famDescrizione + "]";
	}

}

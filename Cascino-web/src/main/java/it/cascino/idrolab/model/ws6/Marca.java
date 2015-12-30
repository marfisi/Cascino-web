//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 09:31:07 AM CET 
//


package it.cascino.idrolab.model.ws6;

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
    "marCodice",
    "proCodice",
    "marDescrizione",
    "marCodiceAngaisa"
})
@XmlRootElement(name = "marca")
public class Marca {

    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String id;
    @XmlElement(name = "mar_codice", required = true)
    protected String marCodice;
    @XmlElement(name = "pro_codice", required = true)
    protected String proCodice;
    @XmlElement(name = "mar_descrizione", required = true)
    protected String marDescrizione;
    @XmlElement(name = "mar_codice_angaisa", required = true)
    protected String marCodiceAngaisa;

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
     * Recupera il valore della proprietà marCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarCodice() {
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
    public void setMarCodice(String value) {
        this.marCodice = value;
    }

    /**
     * Recupera il valore della proprietà proCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProCodice() {
        return proCodice;
    }

    /**
     * Imposta il valore della proprietà proCodice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProCodice(String value) {
        this.proCodice = value;
    }

    /**
     * Recupera il valore della proprietà marDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarDescrizione() {
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
    public void setMarDescrizione(String value) {
        this.marDescrizione = value;
    }

    /**
     * Recupera il valore della proprietà marCodiceAngaisa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarCodiceAngaisa() {
        return marCodiceAngaisa;
    }

    /**
     * Imposta il valore della proprietà marCodiceAngaisa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarCodiceAngaisa(String value) {
        this.marCodiceAngaisa = value;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((marCodice == null) ? 0 : marCodice.hashCode());
		result = prime * result + ((marCodiceAngaisa == null) ? 0 : marCodiceAngaisa.hashCode());
		result = prime * result + ((marDescrizione == null) ? 0 : marDescrizione.hashCode());
		result = prime * result + ((proCodice == null) ? 0 : proCodice.hashCode());
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
		if(marCodiceAngaisa == null) {
			if(other.marCodiceAngaisa != null)
				return false;
		}else if(!marCodiceAngaisa.equals(other.marCodiceAngaisa))
			return false;
		if(marDescrizione == null) {
			if(other.marDescrizione != null)
				return false;
		}else if(!marDescrizione.equals(other.marDescrizione))
			return false;
		if(proCodice == null) {
			if(other.proCodice != null)
				return false;
		}else if(!proCodice.equals(other.proCodice))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Marca [id=" + id + ", marCodice=" + marCodice + ", proCodice=" + proCodice + ", marDescrizione=" + marDescrizione + ", marCodiceAngaisa=" + marCodiceAngaisa + "]";
	}

}

//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 09:54:46 AM CET 
//


package it.cascino.idrolab.model.ws11;

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
    "linCodice",
    "linDescrizione"
})
@XmlRootElement(name = "linea")
public class Linea {

    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String id;
    @XmlElement(name = "mar_codice", required = true)
    protected String marCodice;
    @XmlElement(name = "lin_codice", required = true)
    protected String linCodice;
    @XmlElement(name = "lin_descrizione", required = true)
    protected String linDescrizione;

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
     * Recupera il valore della proprietà linCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinCodice() {
        return linCodice;
    }

    /**
     * Imposta il valore della proprietà linCodice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinCodice(String value) {
        this.linCodice = value;
    }

    /**
     * Recupera il valore della proprietà linDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinDescrizione() {
        return linDescrizione;
    }

    /**
     * Imposta il valore della proprietà linDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinDescrizione(String value) {
        this.linDescrizione = value;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((linCodice == null) ? 0 : linCodice.hashCode());
		result = prime * result + ((linDescrizione == null) ? 0 : linDescrizione.hashCode());
		result = prime * result + ((marCodice == null) ? 0 : marCodice.hashCode());
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
		Linea other = (Linea)obj;
		if(id == null) {
			if(other.id != null)
				return false;
		}else if(!id.equals(other.id))
			return false;
		if(linCodice == null) {
			if(other.linCodice != null)
				return false;
		}else if(!linCodice.equals(other.linCodice))
			return false;
		if(linDescrizione == null) {
			if(other.linDescrizione != null)
				return false;
		}else if(!linDescrizione.equals(other.linDescrizione))
			return false;
		if(marCodice == null) {
			if(other.marCodice != null)
				return false;
		}else if(!marCodice.equals(other.marCodice))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Linea [id=" + id + ", marCodice=" + marCodice + ", linCodice=" + linCodice + ", linDescrizione=" + linDescrizione + "]";
	}

}

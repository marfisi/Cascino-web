//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 10:44:42 AM CET 
//


package it.cascino.idrolab.model.ws20;

import java.util.ArrayList;
import java.util.List;
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
    "allegatoLinea"
})
@XmlRootElement(name = "linea")
public class Linea {

    @XmlAttribute(name = "codice", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String codice;
    @XmlAttribute(name = "descrizione", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String descrizione;
    @XmlElement(name = "allegato_linea")
    protected List<AllegatoLinea> allegatoLinea;

    /**
     * Recupera il valore della proprietà codice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della proprietà codice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Recupera il valore della proprietà descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprietà descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Gets the value of the allegatoLinea property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allegatoLinea property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllegatoLinea().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AllegatoLinea }
     * 
     * 
     */
    public List<AllegatoLinea> getAllegatoLinea() {
        if (allegatoLinea == null) {
            allegatoLinea = new ArrayList<AllegatoLinea>();
        }
        return this.allegatoLinea;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allegatoLinea == null) ? 0 : allegatoLinea.hashCode());
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
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
		if(allegatoLinea == null) {
			if(other.allegatoLinea != null)
				return false;
		}else if(!allegatoLinea.equals(other.allegatoLinea))
			return false;
		if(codice == null) {
			if(other.codice != null)
				return false;
		}else if(!codice.equals(other.codice))
			return false;
		if(descrizione == null) {
			if(other.descrizione != null)
				return false;
		}else if(!descrizione.equals(other.descrizione))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Linea [codice=" + codice + ", descrizione=" + descrizione + ", allegatoLinea=" + allegatoLinea + "]";
	}

}

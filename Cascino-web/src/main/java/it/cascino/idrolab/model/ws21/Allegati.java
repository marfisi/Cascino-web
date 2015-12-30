//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 10:45:28 AM CET 
//


package it.cascino.idrolab.model.ws21;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "allegato"
})
@XmlRootElement(name = "allegati")
public class Allegati {

    @XmlAttribute(name = "pagina_corrente", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String paginaCorrente;
    @XmlAttribute(name = "pagine_totali", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String pagineTotali;
    @XmlAttribute(name = "numallegati", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String numallegati;
    protected List<Allegato> allegato;

    /**
     * Recupera il valore della proprietà paginaCorrente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaginaCorrente() {
        return paginaCorrente;
    }

    /**
     * Imposta il valore della proprietà paginaCorrente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaginaCorrente(String value) {
        this.paginaCorrente = value;
    }

    /**
     * Recupera il valore della proprietà pagineTotali.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPagineTotali() {
        return pagineTotali;
    }

    /**
     * Imposta il valore della proprietà pagineTotali.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPagineTotali(String value) {
        this.pagineTotali = value;
    }

    /**
     * Recupera il valore della proprietà numallegati.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumallegati() {
        return numallegati;
    }

    /**
     * Imposta il valore della proprietà numallegati.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumallegati(String value) {
        this.numallegati = value;
    }

    /**
     * Gets the value of the allegato property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allegato property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllegato().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Allegato }
     * 
     * 
     */
    public List<Allegato> getAllegato() {
        if (allegato == null) {
            allegato = new ArrayList<Allegato>();
        }
        return this.allegato;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allegato == null) ? 0 : allegato.hashCode());
		result = prime * result + ((numallegati == null) ? 0 : numallegati.hashCode());
		result = prime * result + ((paginaCorrente == null) ? 0 : paginaCorrente.hashCode());
		result = prime * result + ((pagineTotali == null) ? 0 : pagineTotali.hashCode());
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
		Allegati other = (Allegati)obj;
		if(allegato == null) {
			if(other.allegato != null)
				return false;
		}else if(!allegato.equals(other.allegato))
			return false;
		if(numallegati == null) {
			if(other.numallegati != null)
				return false;
		}else if(!numallegati.equals(other.numallegati))
			return false;
		if(paginaCorrente == null) {
			if(other.paginaCorrente != null)
				return false;
		}else if(!paginaCorrente.equals(other.paginaCorrente))
			return false;
		if(pagineTotali == null) {
			if(other.pagineTotali != null)
				return false;
		}else if(!pagineTotali.equals(other.pagineTotali))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Allegati [paginaCorrente=" + paginaCorrente + ", pagineTotali=" + pagineTotali + ", numallegati=" + numallegati + ", allegato=" + allegato + "]";
	}

    
}

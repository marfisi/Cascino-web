//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 10:45:28 AM CET 
//


package it.cascino.idrolab.model.ws21;

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
    "allegatoNome",
    "allegatoDescrizione",
    "allegatoDimensione",
    "allegatoSicurezza",
    "allegatoSiglatipo",
    "allegatoDestipo",
    "allegatoLink"
})
@XmlRootElement(name = "allegato")
public class Allegato {

    @XmlElement(name = "allegato_nome", required = true)
    protected String allegatoNome;
    @XmlElement(name = "allegato_descrizione", required = true)
    protected String allegatoDescrizione;
    @XmlElement(name = "allegato_dimensione", required = true)
    protected String allegatoDimensione;
    @XmlElement(name = "allegato_sicurezza", required = true)
    protected String allegatoSicurezza;
    @XmlElement(name = "allegato_siglatipo", required = true)
    protected String allegatoSiglatipo;
    @XmlElement(name = "allegato_destipo", required = true)
    protected String allegatoDestipo;
    @XmlElement(name = "allegato_link", required = true)
    protected String allegatoLink;

    /**
     * Recupera il valore della proprietà allegatoNome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllegatoNome() {
        return allegatoNome;
    }

    /**
     * Imposta il valore della proprietà allegatoNome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllegatoNome(String value) {
        this.allegatoNome = value;
    }

    /**
     * Recupera il valore della proprietà allegatoDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllegatoDescrizione() {
        return allegatoDescrizione;
    }

    /**
     * Imposta il valore della proprietà allegatoDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllegatoDescrizione(String value) {
        this.allegatoDescrizione = value;
    }

    /**
     * Recupera il valore della proprietà allegatoDimensione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllegatoDimensione() {
        return allegatoDimensione;
    }

    /**
     * Imposta il valore della proprietà allegatoDimensione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllegatoDimensione(String value) {
        this.allegatoDimensione = value;
    }

    /**
     * Recupera il valore della proprietà allegatoSicurezza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllegatoSicurezza() {
        return allegatoSicurezza;
    }

    /**
     * Imposta il valore della proprietà allegatoSicurezza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllegatoSicurezza(String value) {
        this.allegatoSicurezza = value;
    }

    /**
     * Recupera il valore della proprietà allegatoSiglatipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllegatoSiglatipo() {
        return allegatoSiglatipo;
    }

    /**
     * Imposta il valore della proprietà allegatoSiglatipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllegatoSiglatipo(String value) {
        this.allegatoSiglatipo = value;
    }

    /**
     * Recupera il valore della proprietà allegatoDestipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllegatoDestipo() {
        return allegatoDestipo;
    }

    /**
     * Imposta il valore della proprietà allegatoDestipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllegatoDestipo(String value) {
        this.allegatoDestipo = value;
    }

    /**
     * Recupera il valore della proprietà allegatoLink.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllegatoLink() {
        return allegatoLink;
    }

    /**
     * Imposta il valore della proprietà allegatoLink.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllegatoLink(String value) {
        this.allegatoLink = value;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allegatoDescrizione == null) ? 0 : allegatoDescrizione.hashCode());
		result = prime * result + ((allegatoDestipo == null) ? 0 : allegatoDestipo.hashCode());
		result = prime * result + ((allegatoDimensione == null) ? 0 : allegatoDimensione.hashCode());
		result = prime * result + ((allegatoLink == null) ? 0 : allegatoLink.hashCode());
		result = prime * result + ((allegatoNome == null) ? 0 : allegatoNome.hashCode());
		result = prime * result + ((allegatoSicurezza == null) ? 0 : allegatoSicurezza.hashCode());
		result = prime * result + ((allegatoSiglatipo == null) ? 0 : allegatoSiglatipo.hashCode());
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
		Allegato other = (Allegato)obj;
		if(allegatoDescrizione == null) {
			if(other.allegatoDescrizione != null)
				return false;
		}else if(!allegatoDescrizione.equals(other.allegatoDescrizione))
			return false;
		if(allegatoDestipo == null) {
			if(other.allegatoDestipo != null)
				return false;
		}else if(!allegatoDestipo.equals(other.allegatoDestipo))
			return false;
		if(allegatoDimensione == null) {
			if(other.allegatoDimensione != null)
				return false;
		}else if(!allegatoDimensione.equals(other.allegatoDimensione))
			return false;
		if(allegatoLink == null) {
			if(other.allegatoLink != null)
				return false;
		}else if(!allegatoLink.equals(other.allegatoLink))
			return false;
		if(allegatoNome == null) {
			if(other.allegatoNome != null)
				return false;
		}else if(!allegatoNome.equals(other.allegatoNome))
			return false;
		if(allegatoSicurezza == null) {
			if(other.allegatoSicurezza != null)
				return false;
		}else if(!allegatoSicurezza.equals(other.allegatoSicurezza))
			return false;
		if(allegatoSiglatipo == null) {
			if(other.allegatoSiglatipo != null)
				return false;
		}else if(!allegatoSiglatipo.equals(other.allegatoSiglatipo))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Allegato [allegatoNome=" + allegatoNome + ", allegatoDescrizione=" + allegatoDescrizione + ", allegatoDimensione=" + allegatoDimensione + ", allegatoSicurezza=" + allegatoSicurezza + ", allegatoSiglatipo=" + allegatoSiglatipo + ", allegatoDestipo=" + allegatoDestipo + ", allegatoLink=" + allegatoLink + "]";
	}

    
}

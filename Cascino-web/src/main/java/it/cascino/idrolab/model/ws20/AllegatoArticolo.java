//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 10:44:42 AM CET 
//


package it.cascino.idrolab.model.ws20;

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
    "pdfNome",
    "pdfDescrizione",
    "pdfDimensione",
    "pdfSicurezza",
    "allegatoSiglatipo",
    "tpdDescrizione",
    "allegatoLink"
})
@XmlRootElement(name = "allegato_articolo")
public class AllegatoArticolo {

    @XmlElement(name = "pdf_nome", required = true)
    protected String pdfNome;
    @XmlElement(name = "pdf_descrizione", required = true)
    protected String pdfDescrizione;
    @XmlElement(name = "pdf_dimensione", required = true)
    protected String pdfDimensione;
    @XmlElement(name = "pdf_sicurezza", required = true)
    protected String pdfSicurezza;
    @XmlElement(name = "allegato_siglatipo", required = true)
    protected String allegatoSiglatipo;
    @XmlElement(name = "tpd_descrizione", required = true)
    protected String tpdDescrizione;
    @XmlElement(name = "allegato_link", required = true)
    protected String allegatoLink;

    /**
     * Recupera il valore della proprietà pdfNome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdfNome() {
        return pdfNome;
    }

    /**
     * Imposta il valore della proprietà pdfNome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdfNome(String value) {
        this.pdfNome = value;
    }

    /**
     * Recupera il valore della proprietà pdfDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdfDescrizione() {
        return pdfDescrizione;
    }

    /**
     * Imposta il valore della proprietà pdfDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdfDescrizione(String value) {
        this.pdfDescrizione = value;
    }

    /**
     * Recupera il valore della proprietà pdfDimensione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdfDimensione() {
        return pdfDimensione;
    }

    /**
     * Imposta il valore della proprietà pdfDimensione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdfDimensione(String value) {
        this.pdfDimensione = value;
    }

    /**
     * Recupera il valore della proprietà pdfSicurezza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdfSicurezza() {
        return pdfSicurezza;
    }

    /**
     * Imposta il valore della proprietà pdfSicurezza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdfSicurezza(String value) {
        this.pdfSicurezza = value;
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
     * Recupera il valore della proprietà tpdDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpdDescrizione() {
        return tpdDescrizione;
    }

    /**
     * Imposta il valore della proprietà tpdDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpdDescrizione(String value) {
        this.tpdDescrizione = value;
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
		result = prime * result + ((allegatoLink == null) ? 0 : allegatoLink.hashCode());
		result = prime * result + ((allegatoSiglatipo == null) ? 0 : allegatoSiglatipo.hashCode());
		result = prime * result + ((pdfDescrizione == null) ? 0 : pdfDescrizione.hashCode());
		result = prime * result + ((pdfDimensione == null) ? 0 : pdfDimensione.hashCode());
		result = prime * result + ((pdfNome == null) ? 0 : pdfNome.hashCode());
		result = prime * result + ((pdfSicurezza == null) ? 0 : pdfSicurezza.hashCode());
		result = prime * result + ((tpdDescrizione == null) ? 0 : tpdDescrizione.hashCode());
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
		AllegatoArticolo other = (AllegatoArticolo)obj;
		if(allegatoLink == null) {
			if(other.allegatoLink != null)
				return false;
		}else if(!allegatoLink.equals(other.allegatoLink))
			return false;
		if(allegatoSiglatipo == null) {
			if(other.allegatoSiglatipo != null)
				return false;
		}else if(!allegatoSiglatipo.equals(other.allegatoSiglatipo))
			return false;
		if(pdfDescrizione == null) {
			if(other.pdfDescrizione != null)
				return false;
		}else if(!pdfDescrizione.equals(other.pdfDescrizione))
			return false;
		if(pdfDimensione == null) {
			if(other.pdfDimensione != null)
				return false;
		}else if(!pdfDimensione.equals(other.pdfDimensione))
			return false;
		if(pdfNome == null) {
			if(other.pdfNome != null)
				return false;
		}else if(!pdfNome.equals(other.pdfNome))
			return false;
		if(pdfSicurezza == null) {
			if(other.pdfSicurezza != null)
				return false;
		}else if(!pdfSicurezza.equals(other.pdfSicurezza))
			return false;
		if(tpdDescrizione == null) {
			if(other.tpdDescrizione != null)
				return false;
		}else if(!tpdDescrizione.equals(other.tpdDescrizione))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "AllegatoArticolo [pdfNome=" + pdfNome + ", pdfDescrizione=" + pdfDescrizione + ", pdfDimensione=" + pdfDimensione + ", pdfSicurezza=" + pdfSicurezza + ", allegatoSiglatipo=" + allegatoSiglatipo + ", tpdDescrizione=" + tpdDescrizione + ", allegatoLink=" + allegatoLink + "]";
	}

    
}

//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 09:51:08 AM CET 
//


package it.cascino.idrolab.model.ws10;

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
    "marCodice",
    "lcaCodice",
    "lcaCodlisprod",
    "lcaDescrizionelistino",
    "lcaDatalistino",
    "lcrCodicerevisione",
    "lcrDescrizionerevisione",
    "lcrDatarevisione",
    "lciCodicesezione",
    "lciDescrizionesezione",
    "lciCodicesezioneprod",
    "lciCodicecapitolo",
    "lciDescrizionecapitolo",
    "lciCodicecapitoloprod",
    "lcaDatavalidita",
    "lcaObsoleto",
    "lcaNote"
})
@XmlRootElement(name = "listino")
public class Listino {

    @XmlElement(name = "mar_codice", required = true)
    protected String marCodice;
    @XmlElement(name = "lca_codice", required = true)
    protected String lcaCodice;
    @XmlElement(name = "lca_codlisprod", required = true)
    protected String lcaCodlisprod;
    @XmlElement(name = "lca_descrizionelistino", required = true)
    protected String lcaDescrizionelistino;
    @XmlElement(name = "lca_datalistino", required = true)
    protected String lcaDatalistino;
    @XmlElement(name = "lcr_codicerevisione", required = true)
    protected String lcrCodicerevisione;
    @XmlElement(name = "lcr_descrizionerevisione", required = true)
    protected String lcrDescrizionerevisione;
    @XmlElement(name = "lcr_datarevisione", required = true)
    protected String lcrDatarevisione;
    @XmlElement(name = "lci_codicesezione", required = true)
    protected String lciCodicesezione;
    @XmlElement(name = "lci_descrizionesezione", required = true)
    protected String lciDescrizionesezione;
    @XmlElement(name = "lci_codicesezioneprod", required = true)
    protected String lciCodicesezioneprod;
    @XmlElement(name = "lci_codicecapitolo", required = true)
    protected String lciCodicecapitolo;
    @XmlElement(name = "lci_descrizionecapitolo", required = true)
    protected String lciDescrizionecapitolo;
    @XmlElement(name = "lci_codicecapitoloprod", required = true)
    protected String lciCodicecapitoloprod;
    @XmlElement(name = "lca_datavalidita", required = true)
    protected String lcaDatavalidita;
    @XmlElement(name = "lca_obsoleto", required = true)
    protected String lcaObsoleto;
    @XmlElement(name = "lca_note", required = true)
    protected String lcaNote;

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
     * Recupera il valore della proprietà lcaCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLcaCodice() {
        return lcaCodice;
    }

    /**
     * Imposta il valore della proprietà lcaCodice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLcaCodice(String value) {
        this.lcaCodice = value;
    }

    /**
     * Recupera il valore della proprietà lcaCodlisprod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLcaCodlisprod() {
        return lcaCodlisprod;
    }

    /**
     * Imposta il valore della proprietà lcaCodlisprod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLcaCodlisprod(String value) {
        this.lcaCodlisprod = value;
    }

    /**
     * Recupera il valore della proprietà lcaDescrizionelistino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLcaDescrizionelistino() {
        return lcaDescrizionelistino;
    }

    /**
     * Imposta il valore della proprietà lcaDescrizionelistino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLcaDescrizionelistino(String value) {
        this.lcaDescrizionelistino = value;
    }

    /**
     * Recupera il valore della proprietà lcaDatalistino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLcaDatalistino() {
        return lcaDatalistino;
    }

    /**
     * Imposta il valore della proprietà lcaDatalistino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLcaDatalistino(String value) {
        this.lcaDatalistino = value;
    }

    /**
     * Recupera il valore della proprietà lcrCodicerevisione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLcrCodicerevisione() {
        return lcrCodicerevisione;
    }

    /**
     * Imposta il valore della proprietà lcrCodicerevisione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLcrCodicerevisione(String value) {
        this.lcrCodicerevisione = value;
    }

    /**
     * Recupera il valore della proprietà lcrDescrizionerevisione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLcrDescrizionerevisione() {
        return lcrDescrizionerevisione;
    }

    /**
     * Imposta il valore della proprietà lcrDescrizionerevisione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLcrDescrizionerevisione(String value) {
        this.lcrDescrizionerevisione = value;
    }

    /**
     * Recupera il valore della proprietà lcrDatarevisione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLcrDatarevisione() {
        return lcrDatarevisione;
    }

    /**
     * Imposta il valore della proprietà lcrDatarevisione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLcrDatarevisione(String value) {
        this.lcrDatarevisione = value;
    }

    /**
     * Recupera il valore della proprietà lciCodicesezione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLciCodicesezione() {
        return lciCodicesezione;
    }

    /**
     * Imposta il valore della proprietà lciCodicesezione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLciCodicesezione(String value) {
        this.lciCodicesezione = value;
    }

    /**
     * Recupera il valore della proprietà lciDescrizionesezione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLciDescrizionesezione() {
        return lciDescrizionesezione;
    }

    /**
     * Imposta il valore della proprietà lciDescrizionesezione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLciDescrizionesezione(String value) {
        this.lciDescrizionesezione = value;
    }

    /**
     * Recupera il valore della proprietà lciCodicesezioneprod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLciCodicesezioneprod() {
        return lciCodicesezioneprod;
    }

    /**
     * Imposta il valore della proprietà lciCodicesezioneprod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLciCodicesezioneprod(String value) {
        this.lciCodicesezioneprod = value;
    }

    /**
     * Recupera il valore della proprietà lciCodicecapitolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLciCodicecapitolo() {
        return lciCodicecapitolo;
    }

    /**
     * Imposta il valore della proprietà lciCodicecapitolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLciCodicecapitolo(String value) {
        this.lciCodicecapitolo = value;
    }

    /**
     * Recupera il valore della proprietà lciDescrizionecapitolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLciDescrizionecapitolo() {
        return lciDescrizionecapitolo;
    }

    /**
     * Imposta il valore della proprietà lciDescrizionecapitolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLciDescrizionecapitolo(String value) {
        this.lciDescrizionecapitolo = value;
    }

    /**
     * Recupera il valore della proprietà lciCodicecapitoloprod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLciCodicecapitoloprod() {
        return lciCodicecapitoloprod;
    }

    /**
     * Imposta il valore della proprietà lciCodicecapitoloprod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLciCodicecapitoloprod(String value) {
        this.lciCodicecapitoloprod = value;
    }

    /**
     * Recupera il valore della proprietà lcaDatavalidita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLcaDatavalidita() {
        return lcaDatavalidita;
    }

    /**
     * Imposta il valore della proprietà lcaDatavalidita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLcaDatavalidita(String value) {
        this.lcaDatavalidita = value;
    }

    /**
     * Recupera il valore della proprietà lcaObsoleto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLcaObsoleto() {
        return lcaObsoleto;
    }

    /**
     * Imposta il valore della proprietà lcaObsoleto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLcaObsoleto(String value) {
        this.lcaObsoleto = value;
    }

    /**
     * Recupera il valore della proprietà lcaNote.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLcaNote() {
        return lcaNote;
    }

    /**
     * Imposta il valore della proprietà lcaNote.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLcaNote(String value) {
        this.lcaNote = value;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lcaCodice == null) ? 0 : lcaCodice.hashCode());
		result = prime * result + ((lcaCodlisprod == null) ? 0 : lcaCodlisprod.hashCode());
		result = prime * result + ((lcaDatalistino == null) ? 0 : lcaDatalistino.hashCode());
		result = prime * result + ((lcaDatavalidita == null) ? 0 : lcaDatavalidita.hashCode());
		result = prime * result + ((lcaDescrizionelistino == null) ? 0 : lcaDescrizionelistino.hashCode());
		result = prime * result + ((lcaNote == null) ? 0 : lcaNote.hashCode());
		result = prime * result + ((lcaObsoleto == null) ? 0 : lcaObsoleto.hashCode());
		result = prime * result + ((lciCodicecapitolo == null) ? 0 : lciCodicecapitolo.hashCode());
		result = prime * result + ((lciCodicecapitoloprod == null) ? 0 : lciCodicecapitoloprod.hashCode());
		result = prime * result + ((lciCodicesezione == null) ? 0 : lciCodicesezione.hashCode());
		result = prime * result + ((lciCodicesezioneprod == null) ? 0 : lciCodicesezioneprod.hashCode());
		result = prime * result + ((lciDescrizionecapitolo == null) ? 0 : lciDescrizionecapitolo.hashCode());
		result = prime * result + ((lciDescrizionesezione == null) ? 0 : lciDescrizionesezione.hashCode());
		result = prime * result + ((lcrCodicerevisione == null) ? 0 : lcrCodicerevisione.hashCode());
		result = prime * result + ((lcrDatarevisione == null) ? 0 : lcrDatarevisione.hashCode());
		result = prime * result + ((lcrDescrizionerevisione == null) ? 0 : lcrDescrizionerevisione.hashCode());
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
		Listino other = (Listino)obj;
		if(lcaCodice == null) {
			if(other.lcaCodice != null)
				return false;
		}else if(!lcaCodice.equals(other.lcaCodice))
			return false;
		if(lcaCodlisprod == null) {
			if(other.lcaCodlisprod != null)
				return false;
		}else if(!lcaCodlisprod.equals(other.lcaCodlisprod))
			return false;
		if(lcaDatalistino == null) {
			if(other.lcaDatalistino != null)
				return false;
		}else if(!lcaDatalistino.equals(other.lcaDatalistino))
			return false;
		if(lcaDatavalidita == null) {
			if(other.lcaDatavalidita != null)
				return false;
		}else if(!lcaDatavalidita.equals(other.lcaDatavalidita))
			return false;
		if(lcaDescrizionelistino == null) {
			if(other.lcaDescrizionelistino != null)
				return false;
		}else if(!lcaDescrizionelistino.equals(other.lcaDescrizionelistino))
			return false;
		if(lcaNote == null) {
			if(other.lcaNote != null)
				return false;
		}else if(!lcaNote.equals(other.lcaNote))
			return false;
		if(lcaObsoleto == null) {
			if(other.lcaObsoleto != null)
				return false;
		}else if(!lcaObsoleto.equals(other.lcaObsoleto))
			return false;
		if(lciCodicecapitolo == null) {
			if(other.lciCodicecapitolo != null)
				return false;
		}else if(!lciCodicecapitolo.equals(other.lciCodicecapitolo))
			return false;
		if(lciCodicecapitoloprod == null) {
			if(other.lciCodicecapitoloprod != null)
				return false;
		}else if(!lciCodicecapitoloprod.equals(other.lciCodicecapitoloprod))
			return false;
		if(lciCodicesezione == null) {
			if(other.lciCodicesezione != null)
				return false;
		}else if(!lciCodicesezione.equals(other.lciCodicesezione))
			return false;
		if(lciCodicesezioneprod == null) {
			if(other.lciCodicesezioneprod != null)
				return false;
		}else if(!lciCodicesezioneprod.equals(other.lciCodicesezioneprod))
			return false;
		if(lciDescrizionecapitolo == null) {
			if(other.lciDescrizionecapitolo != null)
				return false;
		}else if(!lciDescrizionecapitolo.equals(other.lciDescrizionecapitolo))
			return false;
		if(lciDescrizionesezione == null) {
			if(other.lciDescrizionesezione != null)
				return false;
		}else if(!lciDescrizionesezione.equals(other.lciDescrizionesezione))
			return false;
		if(lcrCodicerevisione == null) {
			if(other.lcrCodicerevisione != null)
				return false;
		}else if(!lcrCodicerevisione.equals(other.lcrCodicerevisione))
			return false;
		if(lcrDatarevisione == null) {
			if(other.lcrDatarevisione != null)
				return false;
		}else if(!lcrDatarevisione.equals(other.lcrDatarevisione))
			return false;
		if(lcrDescrizionerevisione == null) {
			if(other.lcrDescrizionerevisione != null)
				return false;
		}else if(!lcrDescrizionerevisione.equals(other.lcrDescrizionerevisione))
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
		return "Listino [marCodice=" + marCodice + ", lcaCodice=" + lcaCodice + ", lcaCodlisprod=" + lcaCodlisprod + ", lcaDescrizionelistino=" + lcaDescrizionelistino + ", lcaDatalistino=" + lcaDatalistino + ", lcrCodicerevisione=" + lcrCodicerevisione + ", lcrDescrizionerevisione=" + lcrDescrizionerevisione + ", lcrDatarevisione=" + lcrDatarevisione + ", lciCodicesezione=" + lciCodicesezione + ", lciDescrizionesezione=" + lciDescrizionesezione + ", lciCodicesezioneprod=" + lciCodicesezioneprod + ", lciCodicecapitolo=" + lciCodicecapitolo + ", lciDescrizionecapitolo=" + lciDescrizionecapitolo + ", lciCodicecapitoloprod=" + lciCodicecapitoloprod + ", lcaDatavalidita=" + lcaDatavalidita + ", lcaObsoleto=" + lcaObsoleto + ", lcaNote=" + lcaNote + "]";
	}

    
}

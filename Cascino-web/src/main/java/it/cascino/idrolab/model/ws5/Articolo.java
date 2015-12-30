//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 09:30:16 AM CET 
//


package it.cascino.idrolab.model.ws5;

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
    "artCodiceproduttore",
    "artCodiceabarre",
    "artDescrizioneridotta",
    "artUm",
    "artUmConf",
    "artConf",
    "setCodice",
    "macCodice",
    "famCodice",
    "artCatsc",
    "fmpCodice",
    "fmpDescrizione",
    "linCodice",
    "linDescrizione",
    "artStato",
    "lisDatalistino",
    "lisDatavalidita",
    "lisPrezzoeuro1",
    "artTipoprezzo"
})
@XmlRootElement(name = "articolo")
public class Articolo {

    @XmlElement(name = "mar_codice", required = true)
    protected String marCodice;
    @XmlElement(name = "art_codiceproduttore", required = true)
    protected String artCodiceproduttore;
    @XmlElement(name = "art_codiceabarre", required = true)
    protected String artCodiceabarre;
    @XmlElement(name = "art_descrizioneridotta", required = true)
    protected String artDescrizioneridotta;
    @XmlElement(name = "art_um", required = true)
    protected String artUm;
    @XmlElement(name = "art_um_conf", required = true)
    protected String artUmConf;
    @XmlElement(name = "art_conf", required = true)
    protected String artConf;
    @XmlElement(name = "set_codice", required = true)
    protected String setCodice;
    @XmlElement(name = "mac_codice", required = true)
    protected String macCodice;
    @XmlElement(name = "fam_codice", required = true)
    protected String famCodice;
    @XmlElement(name = "art_catsc", required = true)
    protected String artCatsc;
    @XmlElement(name = "fmp_codice", required = true)
    protected String fmpCodice;
    @XmlElement(name = "fmp_descrizione", required = true)
    protected String fmpDescrizione;
    @XmlElement(name = "lin_codice", required = true)
    protected String linCodice;
    @XmlElement(name = "lin_descrizione", required = true)
    protected String linDescrizione;
    @XmlElement(name = "art_stato", required = true)
    protected String artStato;
    @XmlElement(name = "lis_datalistino", required = true)
    protected String lisDatalistino;
    @XmlElement(name = "lis_datavalidita", required = true)
    protected String lisDatavalidita;
    @XmlElement(name = "lis_prezzoeuro1", required = true)
    protected String lisPrezzoeuro1;
    @XmlElement(name = "art_tipoprezzo", required = true)
    protected String artTipoprezzo;

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
     * Recupera il valore della proprietà artCodiceproduttore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtCodiceproduttore() {
        return artCodiceproduttore;
    }

    /**
     * Imposta il valore della proprietà artCodiceproduttore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtCodiceproduttore(String value) {
        this.artCodiceproduttore = value;
    }

    /**
     * Recupera il valore della proprietà artCodiceabarre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtCodiceabarre() {
        return artCodiceabarre;
    }

    /**
     * Imposta il valore della proprietà artCodiceabarre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtCodiceabarre(String value) {
        this.artCodiceabarre = value;
    }

    /**
     * Recupera il valore della proprietà artDescrizioneridotta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtDescrizioneridotta() {
        return artDescrizioneridotta;
    }

    /**
     * Imposta il valore della proprietà artDescrizioneridotta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtDescrizioneridotta(String value) {
        this.artDescrizioneridotta = value;
    }

    /**
     * Recupera il valore della proprietà artUm.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtUm() {
        return artUm;
    }

    /**
     * Imposta il valore della proprietà artUm.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtUm(String value) {
        this.artUm = value;
    }

    /**
     * Recupera il valore della proprietà artUmConf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtUmConf() {
        return artUmConf;
    }

    /**
     * Imposta il valore della proprietà artUmConf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtUmConf(String value) {
        this.artUmConf = value;
    }

    /**
     * Recupera il valore della proprietà artConf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtConf() {
        return artConf;
    }

    /**
     * Imposta il valore della proprietà artConf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtConf(String value) {
        this.artConf = value;
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
     * Recupera il valore della proprietà artCatsc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtCatsc() {
        return artCatsc;
    }

    /**
     * Imposta il valore della proprietà artCatsc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtCatsc(String value) {
        this.artCatsc = value;
    }

    /**
     * Recupera il valore della proprietà fmpCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFmpCodice() {
        return fmpCodice;
    }

    /**
     * Imposta il valore della proprietà fmpCodice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFmpCodice(String value) {
        this.fmpCodice = value;
    }

    /**
     * Recupera il valore della proprietà fmpDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFmpDescrizione() {
        return fmpDescrizione;
    }

    /**
     * Imposta il valore della proprietà fmpDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFmpDescrizione(String value) {
        this.fmpDescrizione = value;
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

    /**
     * Recupera il valore della proprietà artStato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtStato() {
        return artStato;
    }

    /**
     * Imposta il valore della proprietà artStato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtStato(String value) {
        this.artStato = value;
    }

    /**
     * Recupera il valore della proprietà lisDatalistino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLisDatalistino() {
        return lisDatalistino;
    }

    /**
     * Imposta il valore della proprietà lisDatalistino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLisDatalistino(String value) {
        this.lisDatalistino = value;
    }

    /**
     * Recupera il valore della proprietà lisDatavalidita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLisDatavalidita() {
        return lisDatavalidita;
    }

    /**
     * Imposta il valore della proprietà lisDatavalidita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLisDatavalidita(String value) {
        this.lisDatavalidita = value;
    }

    /**
     * Recupera il valore della proprietà lisPrezzoeuro1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLisPrezzoeuro1() {
        return lisPrezzoeuro1;
    }

    /**
     * Imposta il valore della proprietà lisPrezzoeuro1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLisPrezzoeuro1(String value) {
        this.lisPrezzoeuro1 = value;
    }

    /**
     * Recupera il valore della proprietà artTipoprezzo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtTipoprezzo() {
        return artTipoprezzo;
    }

    /**
     * Imposta il valore della proprietà artTipoprezzo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtTipoprezzo(String value) {
        this.artTipoprezzo = value;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artCatsc == null) ? 0 : artCatsc.hashCode());
		result = prime * result + ((artCodiceabarre == null) ? 0 : artCodiceabarre.hashCode());
		result = prime * result + ((artCodiceproduttore == null) ? 0 : artCodiceproduttore.hashCode());
		result = prime * result + ((artConf == null) ? 0 : artConf.hashCode());
		result = prime * result + ((artDescrizioneridotta == null) ? 0 : artDescrizioneridotta.hashCode());
		result = prime * result + ((artStato == null) ? 0 : artStato.hashCode());
		result = prime * result + ((artTipoprezzo == null) ? 0 : artTipoprezzo.hashCode());
		result = prime * result + ((artUm == null) ? 0 : artUm.hashCode());
		result = prime * result + ((artUmConf == null) ? 0 : artUmConf.hashCode());
		result = prime * result + ((famCodice == null) ? 0 : famCodice.hashCode());
		result = prime * result + ((fmpCodice == null) ? 0 : fmpCodice.hashCode());
		result = prime * result + ((fmpDescrizione == null) ? 0 : fmpDescrizione.hashCode());
		result = prime * result + ((linCodice == null) ? 0 : linCodice.hashCode());
		result = prime * result + ((linDescrizione == null) ? 0 : linDescrizione.hashCode());
		result = prime * result + ((lisDatalistino == null) ? 0 : lisDatalistino.hashCode());
		result = prime * result + ((lisDatavalidita == null) ? 0 : lisDatavalidita.hashCode());
		result = prime * result + ((lisPrezzoeuro1 == null) ? 0 : lisPrezzoeuro1.hashCode());
		result = prime * result + ((macCodice == null) ? 0 : macCodice.hashCode());
		result = prime * result + ((marCodice == null) ? 0 : marCodice.hashCode());
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
		Articolo other = (Articolo)obj;
		if(artCatsc == null) {
			if(other.artCatsc != null)
				return false;
		}else if(!artCatsc.equals(other.artCatsc))
			return false;
		if(artCodiceabarre == null) {
			if(other.artCodiceabarre != null)
				return false;
		}else if(!artCodiceabarre.equals(other.artCodiceabarre))
			return false;
		if(artCodiceproduttore == null) {
			if(other.artCodiceproduttore != null)
				return false;
		}else if(!artCodiceproduttore.equals(other.artCodiceproduttore))
			return false;
		if(artConf == null) {
			if(other.artConf != null)
				return false;
		}else if(!artConf.equals(other.artConf))
			return false;
		if(artDescrizioneridotta == null) {
			if(other.artDescrizioneridotta != null)
				return false;
		}else if(!artDescrizioneridotta.equals(other.artDescrizioneridotta))
			return false;
		if(artStato == null) {
			if(other.artStato != null)
				return false;
		}else if(!artStato.equals(other.artStato))
			return false;
		if(artTipoprezzo == null) {
			if(other.artTipoprezzo != null)
				return false;
		}else if(!artTipoprezzo.equals(other.artTipoprezzo))
			return false;
		if(artUm == null) {
			if(other.artUm != null)
				return false;
		}else if(!artUm.equals(other.artUm))
			return false;
		if(artUmConf == null) {
			if(other.artUmConf != null)
				return false;
		}else if(!artUmConf.equals(other.artUmConf))
			return false;
		if(famCodice == null) {
			if(other.famCodice != null)
				return false;
		}else if(!famCodice.equals(other.famCodice))
			return false;
		if(fmpCodice == null) {
			if(other.fmpCodice != null)
				return false;
		}else if(!fmpCodice.equals(other.fmpCodice))
			return false;
		if(fmpDescrizione == null) {
			if(other.fmpDescrizione != null)
				return false;
		}else if(!fmpDescrizione.equals(other.fmpDescrizione))
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
		if(lisDatalistino == null) {
			if(other.lisDatalistino != null)
				return false;
		}else if(!lisDatalistino.equals(other.lisDatalistino))
			return false;
		if(lisDatavalidita == null) {
			if(other.lisDatavalidita != null)
				return false;
		}else if(!lisDatavalidita.equals(other.lisDatavalidita))
			return false;
		if(lisPrezzoeuro1 == null) {
			if(other.lisPrezzoeuro1 != null)
				return false;
		}else if(!lisPrezzoeuro1.equals(other.lisPrezzoeuro1))
			return false;
		if(macCodice == null) {
			if(other.macCodice != null)
				return false;
		}else if(!macCodice.equals(other.macCodice))
			return false;
		if(marCodice == null) {
			if(other.marCodice != null)
				return false;
		}else if(!marCodice.equals(other.marCodice))
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
		return "Articolo [marCodice=" + marCodice + ", artCodiceproduttore=" + artCodiceproduttore + ", artCodiceabarre=" + artCodiceabarre + ", artDescrizioneridotta=" + artDescrizioneridotta + ", artUm=" + artUm + ", artUmConf=" + artUmConf + ", artConf=" + artConf + ", setCodice=" + setCodice + ", macCodice=" + macCodice + ", famCodice=" + famCodice + ", artCatsc=" + artCatsc + ", fmpCodice=" + fmpCodice + ", fmpDescrizione=" + fmpDescrizione + ", linCodice=" + linCodice + ", linDescrizione=" + linDescrizione + ", artStato=" + artStato + ", lisDatalistino=" + lisDatalistino + ", lisDatavalidita=" + lisDatavalidita + ", lisPrezzoeuro1=" + lisPrezzoeuro1 + ", artTipoprezzo=" + artTipoprezzo + "]";
	}

}

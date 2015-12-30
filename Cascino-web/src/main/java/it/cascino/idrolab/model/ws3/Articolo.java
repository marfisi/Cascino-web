//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 09:27:23 AM CET 
//


package it.cascino.idrolab.model.ws3;

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
    "artDescrizioneridotta",
    "artUm",
    "artUmConf",
    "artConf",
    "artTipoprezzo",
    "lisDatalistino",
    "lisDatavalidita",
    "lisPrezzoeuro1",
    "lisPrezzoeuroprec",
    "lisDatavaliditaprec"
})
@XmlRootElement(name = "articolo")
public class Articolo {

    @XmlElement(name = "mar_codice", required = true)
    protected String marCodice;
    @XmlElement(name = "art_codiceproduttore", required = true)
    protected String artCodiceproduttore;
    @XmlElement(name = "art_descrizioneridotta", required = true)
    protected String artDescrizioneridotta;
    @XmlElement(name = "art_um", required = true)
    protected String artUm;
    @XmlElement(name = "art_um_conf", required = true)
    protected String artUmConf;
    @XmlElement(name = "art_conf", required = true)
    protected String artConf;
    @XmlElement(name = "art_tipoprezzo", required = true)
    protected String artTipoprezzo;
    @XmlElement(name = "lis_datalistino", required = true)
    protected String lisDatalistino;
    @XmlElement(name = "lis_datavalidita", required = true)
    protected String lisDatavalidita;
    @XmlElement(name = "lis_prezzoeuro1", required = true)
    protected String lisPrezzoeuro1;
    @XmlElement(name = "lis_prezzoeuroprec", required = true)
    protected String lisPrezzoeuroprec;
    @XmlElement(name = "lis_datavaliditaprec", required = true)
    protected String lisDatavaliditaprec;

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
     * Recupera il valore della proprietà lisPrezzoeuroprec.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLisPrezzoeuroprec() {
        return lisPrezzoeuroprec;
    }

    /**
     * Imposta il valore della proprietà lisPrezzoeuroprec.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLisPrezzoeuroprec(String value) {
        this.lisPrezzoeuroprec = value;
    }

    /**
     * Recupera il valore della proprietà lisDatavaliditaprec.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLisDatavaliditaprec() {
        return lisDatavaliditaprec;
    }

    /**
     * Imposta il valore della proprietà lisDatavaliditaprec.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLisDatavaliditaprec(String value) {
        this.lisDatavaliditaprec = value;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artCodiceproduttore == null) ? 0 : artCodiceproduttore.hashCode());
		result = prime * result + ((artConf == null) ? 0 : artConf.hashCode());
		result = prime * result + ((artDescrizioneridotta == null) ? 0 : artDescrizioneridotta.hashCode());
		result = prime * result + ((artTipoprezzo == null) ? 0 : artTipoprezzo.hashCode());
		result = prime * result + ((artUm == null) ? 0 : artUm.hashCode());
		result = prime * result + ((artUmConf == null) ? 0 : artUmConf.hashCode());
		result = prime * result + ((lisDatalistino == null) ? 0 : lisDatalistino.hashCode());
		result = prime * result + ((lisDatavalidita == null) ? 0 : lisDatavalidita.hashCode());
		result = prime * result + ((lisDatavaliditaprec == null) ? 0 : lisDatavaliditaprec.hashCode());
		result = prime * result + ((lisPrezzoeuro1 == null) ? 0 : lisPrezzoeuro1.hashCode());
		result = prime * result + ((lisPrezzoeuroprec == null) ? 0 : lisPrezzoeuroprec.hashCode());
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
		Articolo other = (Articolo)obj;
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
		if(lisDatavaliditaprec == null) {
			if(other.lisDatavaliditaprec != null)
				return false;
		}else if(!lisDatavaliditaprec.equals(other.lisDatavaliditaprec))
			return false;
		if(lisPrezzoeuro1 == null) {
			if(other.lisPrezzoeuro1 != null)
				return false;
		}else if(!lisPrezzoeuro1.equals(other.lisPrezzoeuro1))
			return false;
		if(lisPrezzoeuroprec == null) {
			if(other.lisPrezzoeuroprec != null)
				return false;
		}else if(!lisPrezzoeuroprec.equals(other.lisPrezzoeuroprec))
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
		return "Articolo [marCodice=" + marCodice + ", artCodiceproduttore=" + artCodiceproduttore + ", artDescrizioneridotta=" + artDescrizioneridotta + ", artUm=" + artUm + ", artUmConf=" + artUmConf + ", artConf=" + artConf + ", artTipoprezzo=" + artTipoprezzo + ", lisDatalistino=" + lisDatalistino + ", lisDatavalidita=" + lisDatavalidita + ", lisPrezzoeuro1=" + lisPrezzoeuro1 + ", lisPrezzoeuroprec=" + lisPrezzoeuroprec + ", lisDatavaliditaprec=" + lisDatavaliditaprec + "]";
	}

    
}

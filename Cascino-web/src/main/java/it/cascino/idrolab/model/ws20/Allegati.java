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
    "marCodice",
    "artCodiceproduttore",
    "artModello",
    "imgEstetica",
    "imgTecnica",
    "listaallegatiArticolo"
})
@XmlRootElement(name = "allegati")
public class Allegati {

    @XmlElement(name = "mar_codice", required = true)
    protected String marCodice;
    @XmlElement(name = "art_codiceproduttore", required = true)
    protected String artCodiceproduttore;
    @XmlElement(name = "art_modello", required = true)
    protected String artModello;
    @XmlElement(name = "img_estetica", required = true)
    protected ImgEstetica imgEstetica;
    @XmlElement(name = "img_tecnica", required = true)
    protected ImgTecnica imgTecnica;
    @XmlElement(name = "listaallegati_articolo", required = true)
    protected ListaallegatiArticolo listaallegatiArticolo;

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
     * Recupera il valore della proprietà artModello.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtModello() {
        return artModello;
    }

    /**
     * Imposta il valore della proprietà artModello.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtModello(String value) {
        this.artModello = value;
    }

    /**
     * Recupera il valore della proprietà imgEstetica.
     * 
     * @return
     *     possible object is
     *     {@link ImgEstetica }
     *     
     */
    public ImgEstetica getImgEstetica() {
        return imgEstetica;
    }

    /**
     * Imposta il valore della proprietà imgEstetica.
     * 
     * @param value
     *     allowed object is
     *     {@link ImgEstetica }
     *     
     */
    public void setImgEstetica(ImgEstetica value) {
        this.imgEstetica = value;
    }

    /**
     * Recupera il valore della proprietà imgTecnica.
     * 
     * @return
     *     possible object is
     *     {@link ImgTecnica }
     *     
     */
    public ImgTecnica getImgTecnica() {
        return imgTecnica;
    }

    /**
     * Imposta il valore della proprietà imgTecnica.
     * 
     * @param value
     *     allowed object is
     *     {@link ImgTecnica }
     *     
     */
    public void setImgTecnica(ImgTecnica value) {
        this.imgTecnica = value;
    }

    /**
     * Recupera il valore della proprietà listaallegatiArticolo.
     * 
     * @return
     *     possible object is
     *     {@link ListaallegatiArticolo }
     *     
     */
    public ListaallegatiArticolo getListaallegatiArticolo() {
        return listaallegatiArticolo;
    }

    /**
     * Imposta il valore della proprietà listaallegatiArticolo.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaallegatiArticolo }
     *     
     */
    public void setListaallegatiArticolo(ListaallegatiArticolo value) {
        this.listaallegatiArticolo = value;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artCodiceproduttore == null) ? 0 : artCodiceproduttore.hashCode());
		result = prime * result + ((artModello == null) ? 0 : artModello.hashCode());
		result = prime * result + ((imgEstetica == null) ? 0 : imgEstetica.hashCode());
		result = prime * result + ((imgTecnica == null) ? 0 : imgTecnica.hashCode());
		result = prime * result + ((listaallegatiArticolo == null) ? 0 : listaallegatiArticolo.hashCode());
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
		Allegati other = (Allegati)obj;
		if(artCodiceproduttore == null) {
			if(other.artCodiceproduttore != null)
				return false;
		}else if(!artCodiceproduttore.equals(other.artCodiceproduttore))
			return false;
		if(artModello == null) {
			if(other.artModello != null)
				return false;
		}else if(!artModello.equals(other.artModello))
			return false;
		if(imgEstetica == null) {
			if(other.imgEstetica != null)
				return false;
		}else if(!imgEstetica.equals(other.imgEstetica))
			return false;
		if(imgTecnica == null) {
			if(other.imgTecnica != null)
				return false;
		}else if(!imgTecnica.equals(other.imgTecnica))
			return false;
		if(listaallegatiArticolo == null) {
			if(other.listaallegatiArticolo != null)
				return false;
		}else if(!listaallegatiArticolo.equals(other.listaallegatiArticolo))
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
		return "Allegati [marCodice=" + marCodice + ", artCodiceproduttore=" + artCodiceproduttore + ", artModello=" + artModello + ", imgEstetica=" + imgEstetica + ", imgTecnica=" + imgTecnica + ", listaallegatiArticolo=" + listaallegatiArticolo + "]";
	}

    
}

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
    "artImmaginegrande",
    "imgLink",
    "imgThumbnail",
    "imgLinkThb"
})
@XmlRootElement(name = "img_estetica")
public class ImgEstetica {

    @XmlElement(name = "art_immaginegrande", required = true)
    protected String artImmaginegrande;
    @XmlElement(name = "img_link", required = true)
    protected String imgLink;
    @XmlElement(name = "img_thumbnail", required = true)
    protected String imgThumbnail;
    @XmlElement(name = "img_link_thb", required = true)
    protected String imgLinkThb;

    /**
     * Recupera il valore della proprietà artImmaginegrande.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtImmaginegrande() {
        return artImmaginegrande;
    }

    /**
     * Imposta il valore della proprietà artImmaginegrande.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtImmaginegrande(String value) {
        this.artImmaginegrande = value;
    }

    /**
     * Recupera il valore della proprietà imgLink.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImgLink() {
        return imgLink;
    }

    /**
     * Imposta il valore della proprietà imgLink.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImgLink(String value) {
        this.imgLink = value;
    }

    /**
     * Recupera il valore della proprietà imgThumbnail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImgThumbnail() {
        return imgThumbnail;
    }

    /**
     * Imposta il valore della proprietà imgThumbnail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImgThumbnail(String value) {
        this.imgThumbnail = value;
    }

    /**
     * Recupera il valore della proprietà imgLinkThb.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImgLinkThb() {
        return imgLinkThb;
    }

    /**
     * Imposta il valore della proprietà imgLinkThb.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImgLinkThb(String value) {
        this.imgLinkThb = value;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artImmaginegrande == null) ? 0 : artImmaginegrande.hashCode());
		result = prime * result + ((imgLink == null) ? 0 : imgLink.hashCode());
		result = prime * result + ((imgLinkThb == null) ? 0 : imgLinkThb.hashCode());
		result = prime * result + ((imgThumbnail == null) ? 0 : imgThumbnail.hashCode());
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
		ImgEstetica other = (ImgEstetica)obj;
		if(artImmaginegrande == null) {
			if(other.artImmaginegrande != null)
				return false;
		}else if(!artImmaginegrande.equals(other.artImmaginegrande))
			return false;
		if(imgLink == null) {
			if(other.imgLink != null)
				return false;
		}else if(!imgLink.equals(other.imgLink))
			return false;
		if(imgLinkThb == null) {
			if(other.imgLinkThb != null)
				return false;
		}else if(!imgLinkThb.equals(other.imgLinkThb))
			return false;
		if(imgThumbnail == null) {
			if(other.imgThumbnail != null)
				return false;
		}else if(!imgThumbnail.equals(other.imgThumbnail))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "ImgEstetica [artImmaginegrande=" + artImmaginegrande + ", imgLink=" + imgLink + ", imgThumbnail=" + imgThumbnail + ", imgLinkThb=" + imgLinkThb + "]";
	}

    
}

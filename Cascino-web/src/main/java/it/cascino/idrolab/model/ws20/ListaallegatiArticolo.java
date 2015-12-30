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
    "allegatiLinea",
    "allegatiModello",
    "allegatiArticolo"
})
@XmlRootElement(name = "listaallegati_articolo")
public class ListaallegatiArticolo {

    @XmlElement(name = "allegati_linea", required = true)
    protected AllegatiLinea allegatiLinea;
    @XmlElement(name = "allegati_modello", required = true)
    protected AllegatiModello allegatiModello;
    @XmlElement(name = "allegati_articolo", required = true)
    protected AllegatiArticolo allegatiArticolo;

    /**
     * Recupera il valore della proprietà allegatiLinea.
     * 
     * @return
     *     possible object is
     *     {@link AllegatiLinea }
     *     
     */
    public AllegatiLinea getAllegatiLinea() {
        return allegatiLinea;
    }

    /**
     * Imposta il valore della proprietà allegatiLinea.
     * 
     * @param value
     *     allowed object is
     *     {@link AllegatiLinea }
     *     
     */
    public void setAllegatiLinea(AllegatiLinea value) {
        this.allegatiLinea = value;
    }

    /**
     * Recupera il valore della proprietà allegatiModello.
     * 
     * @return
     *     possible object is
     *     {@link AllegatiModello }
     *     
     */
    public AllegatiModello getAllegatiModello() {
        return allegatiModello;
    }

    /**
     * Imposta il valore della proprietà allegatiModello.
     * 
     * @param value
     *     allowed object is
     *     {@link AllegatiModello }
     *     
     */
    public void setAllegatiModello(AllegatiModello value) {
        this.allegatiModello = value;
    }

    /**
     * Recupera il valore della proprietà allegatiArticolo.
     * 
     * @return
     *     possible object is
     *     {@link AllegatiArticolo }
     *     
     */
    public AllegatiArticolo getAllegatiArticolo() {
        return allegatiArticolo;
    }

    /**
     * Imposta il valore della proprietà allegatiArticolo.
     * 
     * @param value
     *     allowed object is
     *     {@link AllegatiArticolo }
     *     
     */
    public void setAllegatiArticolo(AllegatiArticolo value) {
        this.allegatiArticolo = value;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allegatiArticolo == null) ? 0 : allegatiArticolo.hashCode());
		result = prime * result + ((allegatiLinea == null) ? 0 : allegatiLinea.hashCode());
		result = prime * result + ((allegatiModello == null) ? 0 : allegatiModello.hashCode());
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
		ListaallegatiArticolo other = (ListaallegatiArticolo)obj;
		if(allegatiArticolo == null) {
			if(other.allegatiArticolo != null)
				return false;
		}else if(!allegatiArticolo.equals(other.allegatiArticolo))
			return false;
		if(allegatiLinea == null) {
			if(other.allegatiLinea != null)
				return false;
		}else if(!allegatiLinea.equals(other.allegatiLinea))
			return false;
		if(allegatiModello == null) {
			if(other.allegatiModello != null)
				return false;
		}else if(!allegatiModello.equals(other.allegatiModello))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "ListaallegatiArticolo [allegatiLinea=" + allegatiLinea + ", allegatiModello=" + allegatiModello + ", allegatiArticolo=" + allegatiArticolo + "]";
	}

}

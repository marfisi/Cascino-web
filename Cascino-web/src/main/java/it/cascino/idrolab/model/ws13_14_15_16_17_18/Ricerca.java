//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 09:56:14 AM CET 
//


package it.cascino.idrolab.model.ws13_14_15_16_17_18;

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
    "articolo"
})
@XmlRootElement(name = "ricerca")
public class Ricerca {

    @XmlAttribute(name = "pagina_corrente", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String paginaCorrente;
    @XmlAttribute(name = "pagine_totali", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String pagineTotali;
    @XmlAttribute(name = "articoli", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String articoli;
    protected List<Articolo> articolo;

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
     * Recupera il valore della proprietà articoli.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArticoli() {
        return articoli;
    }

    /**
     * Imposta il valore della proprietà articoli.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArticoli(String value) {
        this.articoli = value;
    }

    /**
     * Gets the value of the articolo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the articolo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArticolo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Articolo }
     * 
     * 
     */
    public List<Articolo> getArticolo() {
        if (articolo == null) {
            articolo = new ArrayList<Articolo>();
        }
        return this.articolo;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articoli == null) ? 0 : articoli.hashCode());
		result = prime * result + ((articolo == null) ? 0 : articolo.hashCode());
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
		Ricerca other = (Ricerca)obj;
		if(articoli == null) {
			if(other.articoli != null)
				return false;
		}else if(!articoli.equals(other.articoli))
			return false;
		if(articolo == null) {
			if(other.articolo != null)
				return false;
		}else if(!articolo.equals(other.articolo))
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
		return "Ricerca [paginaCorrente=" + paginaCorrente + ", pagineTotali=" + pagineTotali + ", articoli=" + articoli + ", articolo=" + articolo + "]";
	}

    
}

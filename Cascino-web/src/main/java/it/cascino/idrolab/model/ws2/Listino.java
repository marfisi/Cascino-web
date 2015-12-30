//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.21 alle 10:42:16 AM CET 
//


package it.cascino.idrolab.model.ws2;

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
@XmlRootElement(name = "listino")
public class Listino {

    @XmlAttribute(name = "data_aggiornamento", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String dataAggiornamento;
    protected List<Articolo> articolo;

    /**
     * Recupera il valore della proprietà dataAggiornamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataAggiornamento() {
        return dataAggiornamento;
    }

    /**
     * Imposta il valore della proprietà dataAggiornamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataAggiornamento(String value) {
        this.dataAggiornamento = value;
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
		result = prime * result + ((articolo == null) ? 0 : articolo.hashCode());
		result = prime * result + ((dataAggiornamento == null) ? 0 : dataAggiornamento.hashCode());
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
		if(articolo == null) {
			if(other.articolo != null)
				return false;
		}else if(!articolo.equals(other.articolo))
			return false;
		if(dataAggiornamento == null) {
			if(other.dataAggiornamento != null)
				return false;
		}else if(!dataAggiornamento.equals(other.dataAggiornamento))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Listino [dataAggiornamento=" + dataAggiornamento + ", articolo=" + articolo + "]";
	}

}

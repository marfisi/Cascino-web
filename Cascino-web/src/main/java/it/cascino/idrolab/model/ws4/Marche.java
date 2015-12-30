//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.23 alle 01:13:13 PM CET 
//


package it.cascino.idrolab.model.ws4;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "marca"
})
@XmlRootElement(name = "marche")
public class Marche {

    protected List<Marca> marca;

    /**
     * Gets the value of the marca property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the marca property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarca().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Marca }
     * 
     * 
     */
    public List<Marca> getMarca() {
        if (marca == null) {
            marca = new ArrayList<Marca>();
        }
        return this.marca;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
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
		Marche other = (Marche)obj;
		if(marca == null) {
			if(other.marca != null)
				return false;
		}else if(!marca.equals(other.marca))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Marche [marca=" + marca + "]";
	}

	
}

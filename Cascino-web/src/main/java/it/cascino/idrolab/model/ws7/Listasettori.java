//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 09:31:55 AM CET 
//


package it.cascino.idrolab.model.ws7;

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
    "settore"
})
@XmlRootElement(name = "listasettori")
public class Listasettori {

    protected List<Settore> settore;

    /**
     * Gets the value of the settore property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the settore property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSettore().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Settore }
     * 
     * 
     */
    public List<Settore> getSettore() {
        if (settore == null) {
            settore = new ArrayList<Settore>();
        }
        return this.settore;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((settore == null) ? 0 : settore.hashCode());
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
		Listasettori other = (Listasettori)obj;
		if(settore == null) {
			if(other.settore != null)
				return false;
		}else if(!settore.equals(other.settore))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Listasettori [settore=" + settore + "]";
	}

    
}

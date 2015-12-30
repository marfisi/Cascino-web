//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 09:32:53 AM CET 
//


package it.cascino.idrolab.model.ws8;

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
    "macrofamiglia"
})
@XmlRootElement(name = "listamacrofamiglie")
public class Listamacrofamiglie {

    protected List<Macrofamiglia> macrofamiglia;

    /**
     * Gets the value of the macrofamiglia property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the macrofamiglia property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMacrofamiglia().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Macrofamiglia }
     * 
     * 
     */
    public List<Macrofamiglia> getMacrofamiglia() {
        if (macrofamiglia == null) {
            macrofamiglia = new ArrayList<Macrofamiglia>();
        }
        return this.macrofamiglia;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((macrofamiglia == null) ? 0 : macrofamiglia.hashCode());
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
		Listamacrofamiglie other = (Listamacrofamiglie)obj;
		if(macrofamiglia == null) {
			if(other.macrofamiglia != null)
				return false;
		}else if(!macrofamiglia.equals(other.macrofamiglia))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Listamacrofamiglie [macrofamiglia=" + macrofamiglia + "]";
	}

}

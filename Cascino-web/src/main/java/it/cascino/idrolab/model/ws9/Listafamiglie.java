//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 09:34:07 AM CET 
//


package it.cascino.idrolab.model.ws9;

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
    "famiglia"
})
@XmlRootElement(name = "listafamiglie")
public class Listafamiglie {

    protected List<Famiglia> famiglia;

    /**
     * Gets the value of the famiglia property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the famiglia property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFamiglia().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Famiglia }
     * 
     * 
     */
    public List<Famiglia> getFamiglia() {
        if (famiglia == null) {
            famiglia = new ArrayList<Famiglia>();
        }
        return this.famiglia;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((famiglia == null) ? 0 : famiglia.hashCode());
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
		Listafamiglie other = (Listafamiglie)obj;
		if(famiglia == null) {
			if(other.famiglia != null)
				return false;
		}else if(!famiglia.equals(other.famiglia))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Listafamiglie [famiglia=" + famiglia + "]";
	}

}

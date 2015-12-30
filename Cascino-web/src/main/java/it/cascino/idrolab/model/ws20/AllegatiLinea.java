//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 10:44:42 AM CET 
//


package it.cascino.idrolab.model.ws20;

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
    "linea"
})
@XmlRootElement(name = "allegati_linea")
public class AllegatiLinea {

    protected List<Linea> linea;

    /**
     * Gets the value of the linea property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linea property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinea().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Linea }
     * 
     * 
     */
    public List<Linea> getLinea() {
        if (linea == null) {
            linea = new ArrayList<Linea>();
        }
        return this.linea;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((linea == null) ? 0 : linea.hashCode());
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
		AllegatiLinea other = (AllegatiLinea)obj;
		if(linea == null) {
			if(other.linea != null)
				return false;
		}else if(!linea.equals(other.linea))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "AllegatiLinea [linea=" + linea + "]";
	}

    
}

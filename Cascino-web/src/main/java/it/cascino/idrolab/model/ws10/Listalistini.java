//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 09:51:08 AM CET 
//


package it.cascino.idrolab.model.ws10;

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
    "listino"
})
@XmlRootElement(name = "listalistini")
public class Listalistini {

    protected List<Listino> listino;

    /**
     * Gets the value of the listino property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listino property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListino().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Listino }
     * 
     * 
     */
    public List<Listino> getListino() {
        if (listino == null) {
            listino = new ArrayList<Listino>();
        }
        return this.listino;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listino == null) ? 0 : listino.hashCode());
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
		Listalistini other = (Listalistini)obj;
		if(listino == null) {
			if(other.listino != null)
				return false;
		}else if(!listino.equals(other.listino))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Listalistini [listino=" + listino + "]";
	}

}

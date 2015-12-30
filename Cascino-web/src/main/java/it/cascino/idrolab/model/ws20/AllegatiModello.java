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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "allegatoModello"
})
@XmlRootElement(name = "allegati_modello")
public class AllegatiModello {

    @XmlElement(name = "allegato_modello")
    protected List<AllegatoModello> allegatoModello;

    /**
     * Gets the value of the allegatoModello property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allegatoModello property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllegatoModello().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AllegatoModello }
     * 
     * 
     */
    public List<AllegatoModello> getAllegatoModello() {
        if (allegatoModello == null) {
            allegatoModello = new ArrayList<AllegatoModello>();
        }
        return this.allegatoModello;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allegatoModello == null) ? 0 : allegatoModello.hashCode());
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
		AllegatiModello other = (AllegatiModello)obj;
		if(allegatoModello == null) {
			if(other.allegatoModello != null)
				return false;
		}else if(!allegatoModello.equals(other.allegatoModello))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "AllegatiModello [allegatoModello=" + allegatoModello + "]";
	}

    
}

//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine.
// Generato il: 2015.11.19 alle 09:28:53 AM CET
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
@XmlType(name = "", propOrder = {"aggiornamento"
})
@XmlRootElement(name = "aggiornamenti")
public class Aggiornamenti{
	
	protected List<Aggiornamento> aggiornamento;
	
	/**
	 * Gets the value of the aggiornamento property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the aggiornamento property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getAggiornamento().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link Aggiornamento }
	 * 
	 * 
	 */
	public List<Aggiornamento> getAggiornamento(){
		if(aggiornamento == null) {
			aggiornamento = new ArrayList<Aggiornamento>();
		}
		return this.aggiornamento;
	}
	
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aggiornamento == null) ? 0 : aggiornamento.hashCode());
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
		Aggiornamenti other = (Aggiornamenti)obj;
		if(aggiornamento == null) {
			if(other.aggiornamento != null)
				return false;
		}else if(!aggiornamento.equals(other.aggiornamento))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return "Aggiornamenti [aggiornamento=" + aggiornamento + "]";
	}
	
}

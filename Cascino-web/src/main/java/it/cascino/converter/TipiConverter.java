package it.cascino.converter;

import it.cascino.model.Tipi;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.jboss.logging.Logger;

@FacesConverter(forClass = it.cascino.model.Tipi.class, value = "tipiConv")
public class TipiConverter implements Converter{
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value){
		// log.info("getAsObject: " + value);
		if(value.isEmpty()){
			// log.warn("getAsObject: " + value + " isEmpty");
			value = "1";
		}
		Tipi o = new Tipi();
		int num;
		try{
			num = Integer.parseInt(value);
			o.setId(num);
		}catch(NumberFormatException e){
		}
		return o;
	}
	
	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object obj){
		if(obj == null){
			// log.warn("getAsString: " + obj + " == null");
			obj = "1";
		}
		// log.info("getAsString: " + obj);
		return obj.toString();
	}
}

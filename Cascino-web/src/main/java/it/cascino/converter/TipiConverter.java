package it.cascino.converter;

import it.cascino.h8.entity.Tipi;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.jboss.logging.Logger;

@FacesConverter(forClass = it.cascino.h8.entity.Tipi.class, value = "tipiConv")
public class TipiConverter implements Converter{
	/**
	 * Logger
	 */
	private static Logger log = Logger.getLogger(TipiConverter.class.getName());
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2){
//		log.info("getAsObject: " + arg2);
		if(arg2.isEmpty()){
			log.warn("getAsObject: " + arg2 + " isEmpty");
			arg2 = "1";
		}
		int num = Integer.parseInt(arg2);
		Tipi t = new Tipi();
		t.setId(num);
//		log.info("getAsObject: " + num);
		return t;
	}
	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2){
		if(arg2 == null){
			log.warn("getAsString: " + arg2 + " == null");
			arg2 = "1";
		}
//		log.info("getAsString: " + arg2);
		return arg2.toString();
	}
	
}

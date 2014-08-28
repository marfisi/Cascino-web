package it.cascino.converter;

import it.cascino.model.Produttori;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.jboss.logging.Logger;

@FacesConverter(forClass = it.cascino.model.Tipi.class, value = "produttoriConv")
public class ProduttoriConverter implements Converter{
//	/**
//	 * Logger
//	 */
//	@Inject
//	private Logger log;
//	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value){
//		log.info("tmpDEBUGtmp: " + "> " + "getAsObject(" + fc + ", " + uic + ", " + value + ")");
		if(value.isEmpty()){
			value = "1";
		}
		Produttori o = new Produttori();
		int num;
		try{
			num = Integer.parseInt(value);
			o.setId(num);
		}catch(NumberFormatException e){
		}
//		log.info("tmpDEBUGtmp: " + "< " + "getAsObject");
		return o;
	}
	
	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object obj){
//		log.info("tmpDEBUGtmp: " + "> " + "getAsString(" + fc + ", " + uic + ", " + obj + ")");
		if(obj == null){
			obj = "1";
		}
//		log.info("tmpDEBUGtmp: " + "< " + "getAsString");
		return obj.toString();
	}
}

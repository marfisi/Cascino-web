package it.cascino.converter;

import it.cascino.model.Produttori;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

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
		if(!(value.isEmpty())){
			value = value.substring(value.indexOf("id=")+3, value.indexOf(", ")).replace("]", "");
		}else{
			value = "1";
		}
				
		Produttori o = new Produttori();
		int num;
		try{
			num = Integer.parseInt(value);
			o.setId(num);
		}catch(NumberFormatException e){
			o.setId(1);
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

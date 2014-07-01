package it.cascino.converter;

import it.cascino.dao.ManBeanFotoDao;
import it.cascino.model.Foto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.jboss.logging.Logger;

@FacesConverter(forClass = it.cascino.model.Tipi.class, value = "fotoConv")
public class FotoConverter implements Converter{
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2){
		if(arg2.isEmpty()){
			arg2 = "1";
		}
		Foto o = new Foto();
		int num;
		try{
			num = Integer.parseInt(arg2);
			o.setId(num);
		}catch(NumberFormatException e){
//			if(arg2.contains("\\")){
//			o = (new ManagedBeanFotoDao()).getFotoFromNomeOriginale(arg2);
//			}
		}
		return o;
	}
	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2){
		if(arg2 == null){
			arg2 = "1";
		}
		return arg2.toString();
	}
	
}

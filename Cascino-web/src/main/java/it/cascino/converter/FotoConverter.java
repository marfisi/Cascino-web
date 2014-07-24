package it.cascino.converter;

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
	public Object getAsObject(FacesContext fc, UIComponent uic, String value){
		if(value.isEmpty()){
			value = "1";
		}
		Foto o = new Foto();
		int num;
		try{
			num = Integer.parseInt(value);
			o.setId(num);
		}catch(NumberFormatException e){
//			if(value.contains("\\")){
//			o = (new ManagedBeanFotoDao()).getFotoFromNomeOriginale(value);
//			}
		}
		return o;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object obj){
		if(obj == null){
			obj = "1";
		}
		return obj.toString();
	}
	
}

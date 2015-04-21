package it.cascino.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "toLowerCaseConverter")
public class ToLowerCaseConverter implements Converter{
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value){
		if(!(value.isEmpty())){
			
		}
		return value.toLowerCase();
	}
	
	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object obj){
		if((obj == null) || (!(obj instanceof String))){
			obj = "nullo";
		}
		return obj.toString().toLowerCase();
	}	
}

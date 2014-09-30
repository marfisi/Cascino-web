package it.cascino.converter;

import it.cascino.model.Produttori;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = it.cascino.model.Tipi.class, value = "produttoriConv")
public class ProduttoriConverter implements Converter{
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value){
		Produttori o = new Produttori();
		String valueSplit[] = null;
		if(!(value.isEmpty())){
			valueSplit = value.replace("Produttori[", "").replace("]", "").split(", ");
			for(int i = 0; i < valueSplit.length; i++){
				valueSplit[i] = valueSplit[i].substring(valueSplit[i].indexOf("=") + 1);
			}
		}else{
			o.setId(1);
		}
		
		try{
			try{
				o.setId(Integer.parseInt(valueSplit[0]));
			}catch(NumberFormatException e){
				o.setId(1);
			}
			o.setNome((!(valueSplit[1].equals("null"))) ? valueSplit[1] : null);
			o.setDati((!(valueSplit[2].equals("null"))) ? valueSplit[2] : null);
			o.setIdFoto((!(valueSplit[3].equals("null"))) ? Integer.parseInt(valueSplit[3]) : null);
		}catch(Exception e){
			o.setId(1);
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

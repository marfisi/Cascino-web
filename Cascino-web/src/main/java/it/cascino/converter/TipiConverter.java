package it.cascino.converter;

import it.cascino.model.Tipi;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = it.cascino.model.Tipi.class, value = "tipiConv")
public class TipiConverter implements Converter{
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value){
		Tipi o = new Tipi();
		String valueSplit[] = null;
		if(!(value.isEmpty())){
			valueSplit = value.replace("Tipi[", "").replace("]", "").split(", ");
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
			o.setDescrizione((!(valueSplit[2].equals("null"))) ? valueSplit[2] : null);
			if(!(valueSplit[3].equals("null"))){
				Tipi t = new Tipi();
				t.setId(Integer.parseInt(valueSplit[3]));
				o.setTipoPadre(t);
			}else{
				o.setTipoPadre(null);				
			}
			o.setIdFoto((!(valueSplit[4].equals("null"))) ? Integer.parseInt(valueSplit[4]) : null);
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

package it.cascino.converter;

import it.cascino.dao.FotoDao;
import it.cascino.model.Foto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(forClass = it.cascino.model.Tipi.class, value = "fotoConv")
public class FotoConverter implements Converter{
	// /**
	// * Logger
	// */
	// @Inject
	// private Logger log;
	//
	
	// @Inject
	// private FotoDao fotoDao;
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value){
		// log.info("tmpDEBUGtmp: " + "> " + "getAsObject(" + fc + ", " + uic + ", " + value + ")");
		Foto o = new Foto();
		String valueSplit[] = null;
		if(!(value.isEmpty())){
			valueSplit = value.replace("Foto[", "").replace("]", "").split(", ");
			// value = value.substring(value.indexOf("id=")+3, value.indexOf(", ")).replace("]", "");
			for(int i = 0; i < valueSplit.length; i++){
				valueSplit[i] = valueSplit[i].substring(valueSplit[i].indexOf("=") + 1);
			}
		}else{
			o.setId(1);
		}
		
		// o = fotoDao.getFotoDaIdFoto(num); // nei converter non e' facile utilizzare inject
		try{
			try{
				o.setId(Integer.parseInt(valueSplit[0]));
			}catch(NumberFormatException e){
				o.setId(1);
			}
			o.setPath((!(valueSplit[1].equals("null"))) ? valueSplit[1] : null);
			o.setOriginale((!(valueSplit[2].equals("null"))) ? valueSplit[2] : null);
			o.setGrande((!(valueSplit[3].equals("null"))) ? valueSplit[3] : null);
			o.setGrandeWatermark((!(valueSplit[4].equals("null"))) ? valueSplit[4] : null);
			o.setThumbnail((!(valueSplit[5].equals("null"))) ? valueSplit[5] : null);
			o.setThumbnailWatermark((!(valueSplit[6].equals("null"))) ? valueSplit[6] : null);
		}catch(Exception e){
			o.setId(1);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getAsObject");
		return o;
	}
	
	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object obj){
		// log.info("tmpDEBUGtmp: " + "> " + "getAsString(" + fc + ", " + uic + ", " + obj + ")");
		if(obj == null){
			obj = "1";
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getAsString");
		return obj.toString();
	}
	
}

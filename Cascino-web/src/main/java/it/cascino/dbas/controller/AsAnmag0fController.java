package it.cascino.dbas.controller;

import it.cascino.dbas.dao.AsAnmag0fDao;
//import it.cascino.model.Articoli;
import it.cascino.dbas.model.AsAnmag0f;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

@Named
@SessionScoped
public class AsAnmag0fController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private AsAnmag0fDao asAnmag0fDao;
	
	private String esito;
	
	@Inject
	private AsAnmag0fCondivisiController asAnmag0fCondivisiController; 
	
	private List<AsAnmag0f> filteredAsAnmag0fLs;

	private AsAnmag0f asAnmag0fSel = new AsAnmag0f();
	private AsAnmag0f asAnmag0fNew = new AsAnmag0f();
	
	public AsAnmag0f getAsAnmag0fSel(){
		return asAnmag0fSel;
	}
	
	public void setAsAnmag0fSel(AsAnmag0f asAnmag0fSel){
		// log.info("tmpDEBUGtmp: " + "> " + "setNodoSel(" + nodoSel + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((AsAnmag0f)nodoSel.getData()).getId() : "null"));
		if(asAnmag0fSel != null){
			this.asAnmag0fSel = asAnmag0fSel;
		}
		// log.info("tmpDEBUGtmp: " + "< " + "setNodoSel");
	}
	
	public AsAnmag0f getAsAnmag0fNew(){
		return asAnmag0fNew;
	}
	
	public void setAsAnmag0fNew(AsAnmag0f asAnmag0fNew){
		if(asAnmag0fNew != null){
			this.asAnmag0fNew = asAnmag0fNew;
		}
	}
	
	// chiamata quando faccio nuovo, per non avere i campi sporchi da una selezione che deriva dalla tabella
	public void resetOnNewAsAnmag0fSel(){
		AsAnmag0f o = new AsAnmag0f();
		o.setMcomp("00000");
		asAnmag0fSel = o;
	}
	
	public List<AsAnmag0f> getFilteredAsAnmag0fLs(){
		// log.info("tmpDEBUGtmp: " + "> " + "getFilteredFotoLs(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getFilteredFotoLs");
		return filteredAsAnmag0fLs;
	}
	
	public void setFilteredAsAnmag0fLs(List<AsAnmag0f> filteredAsAnmag0fLs){
		// log.info("tmpDEBUGtmp: " + "> " + "setFilteredFotoLs(" + filteredFotoLs + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "setFilteredFotoLs");
		this.filteredAsAnmag0fLs = filteredAsAnmag0fLs;
	}
	
	public String getEsito(){
		// log.info("tmpDEBUGtmp: " + "> " + "getEsito(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((AsAnmag0f)nodoSel.getData()).getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getEsito");
		return esito;
	}
	
	private void showGrowlInfoMessage(String message){
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlUpdMessage(){
		String message = "Aggiornato con successo - " + esito + " >" + asAnmag0fSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlInsMessage(){
		String message = "Inserito con successo - " + esito + " >" + asAnmag0fNew + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlDelMessage(){
		String message = "Eliminato con successo - " + esito + " >" + asAnmag0fSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlErrorMessage(){
		String message = "Operazione fallita - " + esito + " >" + asAnmag0fSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", message));
		log.error(message);
	}
	
	public List<AsAnmag0f> getArticoliDaMcomp(String mcomp){
		return asAnmag0fDao.getArticoliDaMcomp(mcomp);
	}
	
	public String getNomiTuttiArticoliDaMcomp(String mcomp){
		String o = "";
		List<AsAnmag0f> oLs = getArticoliDaMcomp(mcomp);
		if(oLs != null){
			AsAnmag0f asAnmag0f = null;
			Iterator<AsAnmag0f> iter_asAnmag = oLs.iterator();
			while(iter_asAnmag.hasNext()){
				asAnmag0f = iter_asAnmag.next();
				o = StringUtils.join(o, " - ", asAnmag0f.getMcoda());
			}
//			AsAnmag0f oAr[] = oLs.toArray(new AsAnmag0f[oLs.size()]);
//			for(int i = 0; i < oAr.length; i++){
//				o = StringUtils.join(o, " - ", oAr[i].getMcoda());
//			}
		}
//		log.info(o);
		return o;
	}	
	
	public boolean filterMcompDaArticolo(Object value, Object filter, Locale locale){
		String filterText = (filter == null) ? null : filter.toString().trim().toUpperCase(locale);
		if(filterText == null || filterText.equals("")){
			return true;
		}
		if(value == null){
			return false;
		}
		return asAnmag0fDao.getSeArticoloHaQuestoMcomp(filterText, String.valueOf(value));
	}
}
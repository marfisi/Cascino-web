package it.cascino.dbas.controller;

import it.cascino.dbas.dao.AsAlmer0fDao;
//import it.cascino.model.Articoli;
import it.cascino.dbas.model.AsAlmer0f;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

@Named
@SessionScoped
public class AsAlmer0fController implements Serializable{
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
	private AsAlmer0fDao asAlmer0fDao;
	
	private String esito;
	
//	@Inject
//	private AsAlmer0fCondivisiController asAlmer0fCondivisiController; 
	
	private List<AsAlmer0f> filteredAsAlmer0fLs;

	private AsAlmer0f asAlmer0fSel = new AsAlmer0f();
	private AsAlmer0f asAlmer0fNew = new AsAlmer0f();
	
	public AsAlmer0f getAsAlmer0fSel(){
		return asAlmer0fSel;
	}
	
	public void setAsAlmer0fSel(AsAlmer0f asAlmer0fSel){
		// log.info("tmpDEBUGtmp: " + "> " + "setNodoSel(" + nodoSel + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((AsAlmer0f)nodoSel.getData()).getId() : "null"));
		if(asAlmer0fSel != null){
			this.asAlmer0fSel = asAlmer0fSel;
		}
		// log.info("tmpDEBUGtmp: " + "< " + "setNodoSel");
	}
	
	public AsAlmer0f getAsAlmer0fNew(){
		return asAlmer0fNew;
	}
	
	public void setAsAlmer0fNew(AsAlmer0f asAlmer0fNew){
		if(asAlmer0fNew != null){
			this.asAlmer0fNew = asAlmer0fNew;
		}
	}
	
	// chiamata quando faccio nuovo, per non avere i campi sporchi da una selezione che deriva dalla tabella
//	public void resetOnNewAsAlmer0fSel(){
//		AsAlmer0f o = new AsAlmer0f();
//		o.setMcomp("00000");
//		asAlmer0fSel = o;
//	}
	
	public List<AsAlmer0f> getFilteredAsAlmer0fLs(){
		// log.info("tmpDEBUGtmp: " + "> " + "getFilteredFotoLs(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getFilteredFotoLs");
		return filteredAsAlmer0fLs;
	}
	
	public void setFilteredAsAlmer0fLs(List<AsAlmer0f> filteredAsAlmer0fLs){
		// log.info("tmpDEBUGtmp: " + "> " + "setFilteredFotoLs(" + filteredFotoLs + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "setFilteredFotoLs");
		this.filteredAsAlmer0fLs = filteredAsAlmer0fLs;
	}
	
	public String getEsito(){
		// log.info("tmpDEBUGtmp: " + "> " + "getEsito(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((AsAlmer0f)nodoSel.getData()).getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getEsito");
		return esito;
	}
	
	private void showGrowlInfoMessage(String message){
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlUpdMessage(){
		String message = "Aggiornato con successo - " + esito + " >" + asAlmer0fSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlInsMessage(){
		String message = "Inserito con successo - " + esito + " >" + asAlmer0fNew + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlDelMessage(){
		String message = "Eliminato con successo - " + esito + " >" + asAlmer0fSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlErrorMessage(){
		String message = "Operazione fallita - " + esito + " >" + asAlmer0fSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", message));
		log.error(message);
	}
	
	public String getAmdes(String amset, String amgru, String amsot, String amfam, String amstf, String amst1){
		AsAlmer0f o = asAlmer0fDao.getDaId(amset, amgru, amsot, amfam, amstf, amst1);
		return (o != null) ? o.getAmdes() : "";
	}
}
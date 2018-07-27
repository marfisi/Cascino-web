package it.cascino.dbas.controller;

import it.cascino.dbas.dao.AsTabe20fDao;
//import it.cascino.model.Articoli;
import it.cascino.dbas.model.AsTabe20f;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
//import org.jboss.logging.Logger;

@Named
@SessionScoped
public class AsTabe20fController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
//	@Inject
//	private Logger log;
//	
//	@Inject
//	private FacesContext facesContext;
	
	@Inject
	private AsTabe20fDao asTabe20fDao;
	
//	private String esito;
	
//	@Inject
//	private AsTabe20fCondivisiController asTabe20fCondivisiController; 
	
	private List<AsTabe20f> filteredAsTabe20fLs;

	private AsTabe20f asTabe20fSel = new AsTabe20f();
	private AsTabe20f asTabe20fNew = new AsTabe20f();
	
	public AsTabe20f getAsTabe20fSel(){
		return asTabe20fSel;
	}
	
	public void setAsTabe20fSel(AsTabe20f asTabe20fSel){
		// log.info("tmpDEBUGtmp: " + "> " + "setNodoSel(" + nodoSel + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((AsTabe20f)nodoSel.getData()).getId() : "null"));
		if(asTabe20fSel != null){
			this.asTabe20fSel = asTabe20fSel;
		}
		// log.info("tmpDEBUGtmp: " + "< " + "setNodoSel");
	}
	
	public AsTabe20f getAsTabe20fNew(){
		return asTabe20fNew;
	}
	
	public void setAsTabe20fNew(AsTabe20f asTabe20fNew){
		if(asTabe20fNew != null){
			this.asTabe20fNew = asTabe20fNew;
		}
	}
	
	// chiamata quando faccio nuovo, per non avere i campi sporchi da una selezione che deriva dalla tabella
//	public void resetOnNewAsTabe20fSel(){
//		AsTabe20f o = new AsTabe20f();
//		o.setMcomp("00000");
//		asTabe20fSel = o;
//	}
	
	public List<AsTabe20f> getFilteredAsTabe20fLs(){
		// log.info("tmpDEBUGtmp: " + "> " + "getFilteredFotoLs(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getFilteredFotoLs");
		return filteredAsTabe20fLs;
	}
	
	public void setFilteredAsTabe20fLs(List<AsTabe20f> filteredAsTabe20fLs){
		// log.info("tmpDEBUGtmp: " + "> " + "setFilteredFotoLs(" + filteredFotoLs + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "setFilteredFotoLs");
		this.filteredAsTabe20fLs = filteredAsTabe20fLs;
	}
	
//	public String getEsito(){
//		// log.info("tmpDEBUGtmp: " + "> " + "getEsito(" + ")");
//		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((AsTabe20f)nodoSel.getData()).getId() : "null"));
//		// log.info("tmpDEBUGtmp: " + "< " + "getEsito");
//		return esito;
//	}
//	
//	private void showGrowlInfoMessage(String message){
//		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
//		log.info(message);
//	}
//	
//	private void showGrowlUpdMessage(){
//		String message = "Aggiornato con successo - " + esito + " >" + asTabe20fSel + "<";
//		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
//		log.info(message);
//	}
//	
//	private void showGrowlInsMessage(){
//		String message = "Inserito con successo - " + esito + " >" + asTabe20fNew + "<";
//		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
//		log.info(message);
//	}
//	
//	private void showGrowlDelMessage(){
//		String message = "Eliminato con successo - " + esito + " >" + asTabe20fSel + "<";
//		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
//		log.info(message);
//	}
//	
//	private void showGrowlErrorMessage(){
//		String message = "Operazione fallita - " + esito + " >" + asTabe20fSel + "<";
//		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", message));
//		log.error(message);
//	}
	
	public AsTabe20f getMarchio(String tbele){
		return asTabe20fDao.getMarchio(tbele);
	}
	
	public String getNomeMarchio(String tbele){
		if(StringUtils.isBlank(tbele)){
			return "";
		}
		String str = asTabe20fDao.getMarchio(tbele).getTbdes();
		return (str != null) ? StringUtils.removeEnd(str,"C") : "";
	}
}
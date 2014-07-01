package it.cascino.controller;

import it.cascino.dao.UserLoginDao;
import it.cascino.model.Userloginrole;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;

@Named
@SessionScoped
public class UserLoginController implements Serializable{
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
	private UserLoginDao userLoginDao;
	
	private Userloginrole userLoginoSel = new Userloginrole();

	private String esito;
	
	private String user;
	private String password;
	
	private Boolean canAccess = false;
	
	public String getUser(){
		return user;
	}

	public void setUser(String user){
		this.user = user;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public void salva(){
//		userLoginDao.salva(nodoSel);
//		if(nodoSel != null){
//			esito = "Aggiunto tipo: " + ((Tipi)nodoSel.getData()).getDescrizione();
//			showGrowlInsMessage();
//		}else{
//			esito = "non ho trovato il tipo!";
//			showGrowlErrorMessage();
//		}
	}
	
	public void aggiorna(){
//		((Tipi)nodoSel.getData()).setTipoPadre(getPadreFromId());
//		
//		userLoginDao.aggiorna(nodoSel);
//		if(nodoSel != null){
//			esito = "Aggiorno tipo: " + ((Tipi)nodoSel.getData()).getDescrizione();
//			showGrowlUpdMessage();
//		}else{
//			esito = "non ho trovato il tipo!";
//			showGrowlErrorMessage();
//		}
	}
	
	public void elimina(){
//		userLoginDao.elimina(nodoSel);
//		if(nodoSel != null){
//			esito = "Elimino tipo: " + ((Tipi)nodoSel.getData()).getDescrizione();
//			showGrowlDelMessage();
//		}else{
//			esito = "non ho trovato il tipo!";
//			showGrowlErrorMessage();
//		}
	}
	
	public String getEsito(){
		return esito;
	}
	
	private void showGrowlInfoMessage(String message){
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlUpdMessage(){
		String message = "Aggiornato con successo - " + esito + " >" + userLoginoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlInsMessage(){
		String message = "Inserito con successo - " + esito + " >" + userLoginoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlDelMessage(){
		String message = "Eliminato con successo - " + esito + " >" + userLoginoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlErrorMessage(){
		String message = "Operazione fallita - " + esito + " >" + userLoginoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", message));
		log.error(message);
	}
	
	public Boolean getCanAccess(){
		if(canAccess == false){
			canAccess = userLoginDao.canAccess(user, password);
		}
		if((canAccess != null)&&(canAccess)){
			esito = "utente " + user + " e' autorizzato";
			showGrowlInfoMessage(esito);
		}else{
			esito = "utente " + user + " NON e' autorizzato (o non e' inserito)";
			showGrowlErrorMessage();
		}
		return canAccess;
	}

	public void setCanAccess(Boolean canAccess){
		this.canAccess = canAccess;
	}
}
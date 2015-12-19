package it.cascino.controller;

import it.cascino.dao.UserLoginDao;
//import it.cascino.model.Tipi;
import it.cascino.model.Users;
import it.cascino.model.Userspermissions;
import it.cascino.model.Usersrolenames;
//import it.cascino.model.Usersroles;
//import it.cascino.util.securety.shiro.ShiroSecured;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
//import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
//import org.apache.shiro.web.util.SavedRequest;
//import org.apache.shiro.web.util.WebUtils;
import org.jboss.logging.Logger;
// import org.omnifaces.util.Faces;
// import org.omnifaces.util.Messages;
import org.jboss.logging.MDC;

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
	
	private Users userSel = new Users();
	
	private String esito;
	
	private String user;
	private String password;
	private String nome;
	private String cognome;
	private String stato;
	private boolean remember;
	
	private Subject subject = null;
	
	private Boolean canAccess = false;
	private Boolean confirmRegistration = false;
	
	public String getUser(){
		// log.info("tmpDEBUGtmp: " + "> " + "getUser(" + ")");
		// log.info("tmpDEBUGtmp: " + "< " + "getUser");
		return user;
	}
	
	public void setUser(String user){
		// log.info("tmpDEBUGtmp: " + "> " + "setUser(" + user + ")");
		this.user = user.toLowerCase();
		// log.info("tmpDEBUGtmp: " + "< " + "setUser");
	}
	
	public String getPassword(){
		// log.info("tmpDEBUGtmp: " + "> " + "getPassword(" + ")");
		// log.info("tmpDEBUGtmp: " + "< " + "getPassword");
		return password;
	}
	
	public void setPassword(String password){
		// log.info("tmpDEBUGtmp: " + "> " + "setPassword(" + password + ")");
		// produce in ogni caso 64 byte
		this.password = password; // new Sha256Hash(password).toHex();
		// log.info("tmpDEBUGtmp: " + "< " + "setPassword");
	}
	
	public String getNome(){
		// log.info("tmpDEBUGtmp: " + "> " + "getNome(" + ")");
		if(StringUtils.isEmpty(nome)){
			nome = userLoginDao.getNomeDaUser(user);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getNome");
		return nome;
	}
	
	public void setNome(String nome){
		// log.info("tmpDEBUGtmp: " + "> " + "setNome(" + nome + ")");
		this.nome = nome.toLowerCase();
		// log.info("tmpDEBUGtmp: " + "< " + "setNome");
	}
	
	public String getCognome(){
		// log.info("tmpDEBUGtmp: " + "> " + "getCognome(" + ")");
		if(StringUtils.isEmpty(cognome)){
			cognome = userLoginDao.getCognomeDaUser(user);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getCognome");
		return cognome;
	}
	
	public void setCognome(String cognome){
		// log.info("tmpDEBUGtmp: " + "> " + "setCognome(" + cognome + ")");
		this.cognome = cognome.toLowerCase();
		// log.info("tmpDEBUGtmp: " + "< " + "setCognome");
	}
	
	public void setStato(String stato){
		// log.info("tmpDEBUGtmp: " + "> " + "setStato(" + stato + ")");
		this.stato = stato.toLowerCase();
		// log.info("tmpDEBUGtmp: " + "< " + "setStato");
	}
	
	public String getStato(){
		// log.info("tmpDEBUGtmp: " + "> " + "getStato(" + ")");
		// log.info("tmpDEBUGtmp: " + "< " + "getStato");
		return stato;
	}
	
	public boolean getRemember(){
		// log.info("tmpDEBUGtmp: " + "> " + "getRemember(" + ")");
		// log.info("tmpDEBUGtmp: " + "< " + "getRemember");
		return remember;
	}
	
	public void setRemember(boolean remember){
		// log.info("tmpDEBUGtmp: " + "> " + "setRemember(" + remember + ")");
		this.remember = remember;
		// log.info("tmpDEBUGtmp: " + "< " + "setRemember");
	}
	
	public List<Usersrolenames> getRolesDaUser(String username){
		// log.info("tmpDEBUGtmp: " + "> " + "getRolesDaUser(" + username + ")");
		// log.info("tmpDEBUGtmp: " + "< " + "getRolesDaUser");
		return userLoginDao.getRolesDaUser(username);
	}
	
	public List<Userspermissions> getPermissionsDaUser(String username){
		// log.info("tmpDEBUGtmp: " + "> " + "getPermissionsDaUser(" + username + ")");
		// log.info("tmpDEBUGtmp: " + "< " + "getPermissionsDaUser");
		return userLoginDao.getPermissionsDaUser(username);
	}
	
	public void salva(){
		// log.info("tmpDEBUGtmp: " + "> " + "salva(" + ")");
		userLoginDao.salva(userSel);
		if((userSel != null) && (userSel.getId() != null)){
			esito = "utente " + userSel.getLogin() + " e' stato registrato";
			showGrowlInsMessage();
		}else{
			esito = "utente " + userSel.getLogin() + " NON e' stato registrato";
			showGrowlErrorMessage();
		}
		// log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void aggiorna(){
		// log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + ")");
		// log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	public void elimina(){
		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + ")");
		// log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	public String getEsito(){
		// log.info("tmpDEBUGtmp: " + "> " + "getEsito(" + ")");
		// log.info("tmpDEBUGtmp: " + "< " + "getEsito");
		return esito;
	}
	
	private void showGrowlInfoMessage(String message){
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	@SuppressWarnings("unused")
	private void showGrowlUpdMessage(){
		String message = "Aggiornato con successo - " + esito + " >" + userSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlInsMessage(){
		String message = "Inserito con successo - " + esito + " >" + userSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	@SuppressWarnings("unused")
	private void showGrowlDelMessage(){
		String message = "Eliminato con successo - " + esito + " >" + userSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlErrorMessage(){
		String message = "Operazione fallita - " + esito + " >" + userSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", message));
		log.error(message);
	}
	
	// public Boolean getCanAccess(){
	// // log.info("tmpDEBUGtmp: " + "> " + "getCanAccess(" + ")");
	// // if(canAccess == false){
	// canAccess = userLoginDao.canAccess(user, password);
	// // }
	// if((canAccess != null) && (canAccess)){
	// esito = "utente " + user + " e' autorizzato";
	// showGrowlInfoMessage(esito);
	// }else{
	// esito = "utente " + user + " NON e' autorizzato (o non e' inserito)";
	// showGrowlErrorMessage();
	// }
	// // log.info("tmpDEBUGtmp: " + "< " + "getCanAccess");
	// return canAccess;
	// }
	
	public Boolean getCanAccess(){
		// log.info("tmpDEBUGtmp: " + "> " + "getCanAccess(" + ")");
		canAccess = false;
		if(login()){
			esito = "utente " + user + " e' autorizzato";
			showGrowlInfoMessage(esito);
			canAccess = true;
		}else{
			esito = "utente " + user + " NON e' autorizzato (o non e' inserito)";
			showGrowlErrorMessage();
			canAccess = false;
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getCanAccess");
		return canAccess;
	}
	
	public void setCanAccess(Boolean canAccess){
		// log.info("tmpDEBUGtmp: " + "> " + "setCanAccess(" + canAccess + ")");
		this.canAccess = canAccess;
		// log.info("tmpDEBUGtmp: " + "< " + "setCanAccess");
	}
	
	@SuppressWarnings("static-access")
	public Boolean login(){
		// log.info("tmpDEBUGtmp: " + "> " + "login(" + ")");
		try{
			HttpServletRequest request = (HttpServletRequest)facesContext.getCurrentInstance().getExternalContext().getRequest();
			String ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
			MDC.put("user-ipAdd", ipAddress);
			
			if(subject == null){
				subject = SecurityUtils.getSubject();
			}
			subject.login(new UsernamePasswordToken(user, password, remember));
		}catch(ShiroException e){
			return false;
		}
		MDC.put("user-id", user);
		// log.info("tmpDEBUGtmp: " + "< " + "login");
		return true;
	}
	
	public String logout(){
		// log.info("tmpDEBUGtmp: " + "> " + "logout(" + ")");
		try{
			subject.logout();
		}catch(ShiroException e){
			return "logoutFallito";
		}
		// log.info("tmpDEBUGtmp: " + "< " + "logout");
		return "logout";
	}
	
	public Boolean getConfirmRegistration(){
		// log.info("tmpDEBUGtmp: " + "> " + "getConfirmRegistration(" + ")");
		
		if(confirmRegistration == false){
			userSel = new Users();
			userSel.setLogin(user);
			userSel.setPassword(password);
			userSel.setNome(nome);
			userSel.setCognome(cognome);
			userSel.setStato(stato);
			
			salva();
			
			if(userSel.getId() != null){
				confirmRegistration = true;
			}
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getConfirmRegistration");
		return confirmRegistration;
	}
	
	public void setConfirmRegistration(Boolean confirmRegistration){
		// log.info("tmpDEBUGtmp: " + "> " + "setConfirmRegistration(" + confirmRegistration + ")");
		this.confirmRegistration = confirmRegistration;
		// log.info("tmpDEBUGtmp: " + "< " + "setConfirmRegistration");
	}
	
	// serve per decidere se visualizzare un elemento o meno (es. bottone)
	public Boolean canView(String permOrRole, String pORr){
		boolean can = false;
		try{
			Subject subject = SecurityUtils.getSubject();
			if(StringUtils.equals(pORr, "p")){ // permission
				subject.checkPermission(permOrRole);
			}else if(StringUtils.equals(pORr, "r")){ // role
				subject.checkRole(permOrRole);
			}else{
				return false;
			}
		}catch(ShiroException e){
			return false;
		}
		return !can;
	}
	
}
package it.cascino.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;

@Named
@SessionScoped
public class NavigationController implements Serializable{
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
	
	// private Boolean showLogIn = true;
	private Boolean showHome = true;
	private Boolean showTipiToFoto = false;
	private Boolean showFotoToTipi = false;
	private String navigationRuleAction = "daNessunaParte";
	
	public Boolean getShowHome(){
		// log.info("tmpDEBUGtmp: " + "> " + "getShowHome(" + ")");
		// log.info("tmpDEBUGtmp: " + "< " + "getShowHome");
		return showHome;
	}
	
	public void setShowHome(Boolean showHome){
		// log.info("tmpDEBUGtmp: " + "> " + "setShowHome(" + showHome + ")");
		this.showHome = showHome;
		// log.info("tmpDEBUGtmp: " + "< " + "setShowHome");
	}
	
	public Boolean getShowTipiToFoto(){
		// log.info("tmpDEBUGtmp: " + "> " + "getShowTipiToFoto(" + ")");
		// log.info("tmpDEBUGtmp: " + "< " + "getShowTipiToFoto");
		return showTipiToFoto;
	}
	
	public void setShowTipiToFoto(Boolean showTipiToFoto){
		// log.info("tmpDEBUGtmp: " + "> " + "setShowTipiToFoto(" + showTipiToFoto + ")");
		this.showTipiToFoto = showTipiToFoto;
		// log.info("tmpDEBUGtmp: " + "< " + "setShowTipiToFoto");
	}
	
	public Boolean getShowFotoToTipi(){
		// log.info("tmpDEBUGtmp: " + "> " + "getShowFotoToTipi(" + ")");
		// log.info("tmpDEBUGtmp: " + "< " + "getShowFotoToTipi");
		return showFotoToTipi;
	}
	
	public void setShowFotoToTipi(Boolean showFotoToTipi){
		// log.info("tmpDEBUGtmp: " + "> " + "setShowFotoToTipi(" + showFotoToTipi + ")");
		this.showFotoToTipi = showFotoToTipi;
		// log.info("tmpDEBUGtmp: " + "< " + "setShowFotoToTipi");
	}
	
	public String tipiToFoto(){
		// log.info("tmpDEBUGtmp: " + "> " + "tipiToFoto(" + ")");
		navigationRuleAction = "tipiToFoto";
		
		setShowHome(false);
		setShowFotoToTipi(true);
		
		// log.info("tmpDEBUGtmp: " + "< " + "tipiToFoto");
		return navigationRuleAction;
	}
	
	public String fotoToTipi(){
		// log.info("tmpDEBUGtmp: " + "> " + "fotoToTipi(" + ")");
		navigationRuleAction = "fotoToTipi";
		
		setShowHome(true);
		setShowFotoToTipi(false);
		
		// log.info("tmpDEBUGtmp: " + "< " + "fotoToTipi");
		return navigationRuleAction;
	}
	
	public String loginToHome(Boolean canAccess){
		// log.info("tmpDEBUGtmp: " + "> " + "loginToHome(" + canAccess + ")");
		navigationRuleAction = "denyAccess";
		
		setShowHome(true);
		
		if(canAccess){
			navigationRuleAction = "canAccess";
		}
		// log.info("tmpDEBUGtmp: " + "< " + "loginToHome");
//		return "/faces/homeManage.xhtml?faces-redirect=true";
		return navigationRuleAction;
	}
	
	public String loginToRegistration(){
		// log.info("tmpDEBUGtmp: " + "> " + "loginToRegistration(" + ")");
		navigationRuleAction = "loginToRegistration";
		// log.info("tmpDEBUGtmp: " + "< " + "loginToRegistration");
		return navigationRuleAction;
	}
	
	public String registrationToLogin(Boolean registration){
		// log.info("tmpDEBUGtmp: " + "> " + "registrationToLogin(" + registration + ")");
		navigationRuleAction = "registrationToLogin";
		
		if(registration){
			navigationRuleAction = "confirmRegistration";
		}
		// log.info("tmpDEBUGtmp: " + "< " + "registrationToLogin");
		return navigationRuleAction;
	}
}

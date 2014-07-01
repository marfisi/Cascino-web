package it.cascino.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
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
	
	private Boolean showHome = true;
	private Boolean showTipiToFoto = false;
	private Boolean showFotoToTipi = false;
	private String navigationRuleAction = "daNessunaParte";
	
	public Boolean getShowHome(){
		return showHome;
	}

	public void setShowHome(Boolean showHome){
		this.showHome = showHome;
	}

	public Boolean getShowTipiToFoto(){
		return showTipiToFoto;
	}

	public void setShowTipiToFoto(Boolean showTipiToFoto){
		this.showTipiToFoto = showTipiToFoto;
	}

	public Boolean getShowFotoToTipi(){
		return showFotoToTipi;
	}

	public void setShowFotoToTipi(Boolean showFotoToTipi){
		this.showFotoToTipi = showFotoToTipi;
	}

	public String tipiToFoto(){
		navigationRuleAction = "tipiToFoto";
		
		setShowHome(false);
		setShowFotoToTipi(true);
		
		return navigationRuleAction;
	}
	
	public String fotoToTipi(){
		navigationRuleAction = "fotoToTipi";
		
		setShowHome(true);
		setShowFotoToTipi(false);
		
		return navigationRuleAction;
	}
	
	public String loginToHome(Boolean canAccess){
		navigationRuleAction = "denyAccess";
		
		setShowHome(true);
		
		if(canAccess){
			navigationRuleAction = "canAccess";
		}
		return navigationRuleAction;
	}
}
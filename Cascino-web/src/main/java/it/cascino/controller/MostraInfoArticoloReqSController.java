package it.cascino.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.jboss.logging.Logger;

@Named
@RequestScoped
public class MostraInfoArticoloReqSController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
//	@Inject
//	private Logger log;
	
	@Inject
	private FacesContext facesContext;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	@Inject
	MostraInfoArticoloViwSController mostraInfoArticoloViwSController;
	
	@PostConstruct
	public void recuperaCodiceArticolo(){
		String codArt = getRequest().getParameter("codArt");// .toUpperCase().trim();
		if((codArt != null) && (!(codArt.isEmpty()))){
			codArt = codArt.toUpperCase().trim();
			mostraInfoArticoloViwSController.initArticolo(codArt);
		}
	}
	
	@SuppressWarnings("static-access")
	private HttpServletRequest getRequest(){
		request = (HttpServletRequest)facesContext.getCurrentInstance().getExternalContext().getRequest();
		return request;
	}
	
	@SuppressWarnings({"static-access", "unused"})
	private HttpServletResponse getResponse(){
		response = (HttpServletResponse)facesContext.getCurrentInstance().getExternalContext().getResponse();
		return response;
	}	
}
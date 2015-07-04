package it.cascino.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.logging.Logger;

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
	@Inject
	private Logger log;
	
	@Inject
	private FacesContext facesContext;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	@Inject
	MostraInfoArticoloSesSController mostraInfoArticoloSesSController;
	
	private String dummy;
	
	@PostConstruct
	public void recuperaCodiceArticolo(){
		String codArt = getRequest().getParameter("codArt");// .toUpperCase().trim();
		if((codArt != null) && (!(codArt.isEmpty()))){
			codArt = codArt.toUpperCase().trim();
			mostraInfoArticoloSesSController.initArticolo(codArt);
		}
	}
	
	private HttpServletRequest getRequest(){
		request = (HttpServletRequest)facesContext.getCurrentInstance().getExternalContext().getRequest();
		return request;
	}
	
	private HttpServletResponse getResponse(){
		response = (HttpServletResponse)facesContext.getCurrentInstance().getExternalContext().getResponse();
		return response;
	}

	public String getDummy(){
		return dummy;
	}

	public void setDummy(String dummy){
		this.dummy = dummy;
	}
	
	//
	// private Integer idFotoZoom;
	//
	// public Integer getIdFotoZoom(){
	// return idFotoZoom;
	// }
	//
	// public void setIdFotoZoom(Integer idFotoZoom){
	// this.idFotoZoom = idFotoZoom;
	// }
	
}
package it.cascino.controller;

import it.cascino.dao.UserLoginDao;
//import it.cascino.model.Tipi;
import it.cascino.model.Users;
import it.cascino.model.Userspermissions;
import it.cascino.model.Usersrolenames;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
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
@RequestScoped
public class MostraInfoArticoloController implements Serializable{
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
//	HttpServletRequest request = (HttpServletRequest)facesContext.getCurrentInstance().getExternalContext().getRequest();
//	HttpServletResponse response = (HttpServletResponse)facesContext.getCurrentInstance().getExternalContext().getResponse();

	
	private String codiceArticolo;
	
	public String getCodiceArticolo(){
		return codiceArticolo;
	}
	
	public void setCodiceArticolo(String codiceArticolo){
		this.codiceArticolo = codiceArticolo.toUpperCase();
	}
	
	public String recuperaCodiceArticolo(){
		HttpServletRequest request = (HttpServletRequest)facesContext.getCurrentInstance().getExternalContext().getRequest();
		String codArt = request.getParameter("codArt");
		codArt = codArt.toUpperCase();
		return codArt;
	}
}
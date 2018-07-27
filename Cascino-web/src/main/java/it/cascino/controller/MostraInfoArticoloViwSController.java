package it.cascino.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
//import javax.faces.view.ViewScoped; // non esiste  javax.enterprise.context.ViewScoped
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;
import it.cascino.dbas.dao.AsAnmag0fDao;
import it.cascino.dbas.model.AsAnmag0f;

@Named
@SessionScoped
//@ViewScoped
public class MostraInfoArticoloViwSController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;	
	
	private AsAnmag0f asAnmag0f;
	
	@Inject
	private AsAnmag0fDao asAnmag0fDao;
	
	public AsAnmag0f getAsAnmag0f(){
		return asAnmag0f;
	}

	public void setAsAnmag0f(AsAnmag0f asAnmag0f){
		if(asAnmag0f != null){
			this.asAnmag0f = asAnmag0f;
		}else{
			this.asAnmag0f = new AsAnmag0f();
		}
	}
	
	public void initArticolo(String codArt){
		asAnmag0f = new AsAnmag0f();
		setAsAnmag0f(asAnmag0fDao.getArticoloDaMcoda(codArt));
		log.info("Articolo_AS400: " + asAnmag0f.toString());
	}
}
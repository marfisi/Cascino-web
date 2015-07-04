package it.cascino.controller;

import it.cascino.model.Articoli;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;

@Named
@SessionScoped
public class MostraInfoArticoloSesSController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	

//	private String codiceArticolo;
	
	private Articoli articolo;
	
	@Inject
	ArticoliController articoliController;
	
	public Articoli getArticolo(){
		return articolo;
	}

	public void setArticolo(Articoli articolo){
		this.articolo = articolo;
	}
	
	public void initArticolo(String codArt){
		articolo = new Articoli();
		if((codArt != null) && (!(codArt.isEmpty()))){
			setArticolo(articoliController.getArticoloDaCodiceArticolo(codArt));
		}
		log.info("codiceArticolo: " + articolo.getCodice());
	}
	
	
//	
//	private Integer idFotoZoom;
//	
//	public Integer getIdFotoZoom(){
//		return idFotoZoom;
//	}
//
//	public void setIdFotoZoom(Integer idFotoZoom){
//		this.idFotoZoom = idFotoZoom;
//	}

	
}
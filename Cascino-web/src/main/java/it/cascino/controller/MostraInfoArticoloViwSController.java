package it.cascino.controller;

import it.cascino.dbas.dao.AsAnmag0fDao;
import it.cascino.dbas.model.AsAnmag0f;
import it.cascino.model.Articoli;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
//import javax.faces.view.ViewScoped; // non esiste  javax.enterprise.context.ViewScoped
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;

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
	
	private Articoli articoloPg;
	
	private AsAnmag0f articoloAs;

	@Inject
	private ArticoliController articoliController;
	
	@Inject
	private AsAnmag0fDao asAnmag0fDao;
	
	public Articoli getArticoloPg(){
		return articoloPg;
	}

	public void setArticoloPg(Articoli articoloPg){
		if(articoloPg != null){
			this.articoloPg = articoloPg;
		}else{
			this.articoloPg = new Articoli(1, null, null, null, null, null, null, null, null, null);
		}
	}
	
	public AsAnmag0f getArticoloAs(){
		return articoloAs;
	}

	public void setArticoloAs(AsAnmag0f articoloAs){
		if(articoloAs != null){
			this.articoloAs = articoloAs;
		}else{
			this.articoloAs = new AsAnmag0f();
		}
	}
	
	public void initArticolo(String codArt){
		articoloPg = new Articoli();
		if((codArt != null) && (!(codArt.isEmpty()))){
			setArticoloPg(articoliController.getArticoloDaCodiceArticolo(codArt));
		}
		log.info("codiceArticolo: " + articoloPg.getCodice());
		
		articoloAs = new AsAnmag0f();
		if(articoloPg.getId() != 1){
			setArticoloAs(asAnmag0fDao.getArticoloDaMcoda(articoloPg.getCodice()));
		}
		log.info("codiceArticoloAS400: " + articoloAs.toString());		
	}	
}
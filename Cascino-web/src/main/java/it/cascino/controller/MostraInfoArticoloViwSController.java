package it.cascino.controller;

import it.cascino.model.Articoli;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
//import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped; // non esiste  javax.enterprise.context.ViewScoped
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
	
	private Articoli articolo;
	
	@Inject
	ArticoliController articoliController;
	
	public Articoli getArticolo(){
		return articolo;
	}

	public void setArticolo(Articoli articolo){
		if(articolo != null){
			this.articolo = articolo;
		}else{
			this.articolo = new Articoli(1, null, null, null, null, null, null, null, null, null);
		}
	}
	
	public void initArticolo(String codArt){
		articolo = new Articoli();
		if((codArt != null) && (!(codArt.isEmpty()))){
			setArticolo(articoliController.getArticoloDaCodiceArticolo(codArt));
		}
		log.info("codiceArticolo: " + articolo.getCodice());
	}	
}
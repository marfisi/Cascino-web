package it.cascino.controller;

import it.cascino.dao.ArticoliDao;
import it.cascino.model.Articoli;
import java.util.List;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;

@Named
@ApplicationScoped
public class ArticoliCondivisiController implements Serializable{
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
	private ArticoliDao articoliDao;
	
	private List<Articoli> articoliLs;
	
	// private Boolean inizializzazione = true;
	// public ArticoliCondivisiController(){
	// articoliLs = articoliDao.getAll();
	// }
	
	public List<Articoli> getArticoliLs(){
		log.info("tmpDEBUGtmp: " + "> " + "getArticoliLs(" + ")");
		// if(inizializzazione){
		// articoliLs = articoliDao.getAll();
		// inizializzazione = false;
		// }
		// articoliLs = articoliDao.getAll();
		log.info("tmpDEBUGtmp: " + "< " + "getArticoliLs");
		return articoliLs;
	}
	
	public void setArticoliLs(List<Articoli> articoliLs){
		log.info("tmpDEBUGtmp: " + "> " + "setArticoliLs(" + articoliLs + ")");
		this.articoliLs = articoliLs;
		log.info("tmpDEBUGtmp: " + "< " + "setArticoliLs");
	}
	
	@PostConstruct
	public void aggiornaArticoliLs(){
		log.info("tmpDEBUGtmp: " + "> " + "aggiornaArticoliLs(" + ")");
		articoliLs = articoliDao.getAll();
		log.info("tmpDEBUGtmp: " + "< " + "aggiornaArticoliLs");
	}
	
	public int sortByNum(Object obj1, Object obj2){
		log.info("tmpDEBUGtmp: " + "> " + "sortByNum(" + obj1 + ", " + obj2 + ")");
		Integer o1 = (Integer)obj1;
		Integer o2 = (Integer)obj2;
		log.info("sortById: " + o1 + "-" + o2);
		log.info("tmpDEBUGtmp: " + "< " + "sortByNum");
		if(o1 < o2){
			return -1;
		}else if(o1 > o2){
			return 1;
		}
		return 0;
	}
	
	public int sortByStr(Object obj1, Object obj2){
		log.info("tmpDEBUGtmp: " + "> " + "sortByStr(" + obj1 + ", " + obj2 + ")");
		String o1 = (String)obj1;
		String o2 = (String)obj2;
		log.info("sortByname: " + o1 + "-" + o2);
		log.info("tmpDEBUGtmp: " + "< " + "sortByStr");
		if(o1.compareTo(o2) < 0){
			return -1;
		}else if(o1.compareTo(o2) > 0){
			return 1;
		}
		return 0;
	}
	
	public int sortByStrIC(Object obj1, Object obj2){
		log.info("tmpDEBUGtmp: " + "> " + "sortByStrIC(" + obj1 + ", " + obj2 + ")");
		String o1 = (String)obj1;
		String o2 = (String)obj2;
		log.info("sortBynameIC: " + o1 + "-" + o2);
		log.info("tmpDEBUGtmp: " + "< " + "sortByStrIC");
		if(o1.compareToIgnoreCase(o2) < 0){
			return -1;
		}else if(o1.compareToIgnoreCase(o2) > 0){
			return 1;
		}
		return 0;
	}
}

package it.cascino.dbas.controller;

import it.cascino.dbas.dao.AsAnmar0fDao;
import it.cascino.dbas.model.AsAnmar0f;
import java.util.List;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;

@Named
@ApplicationScoped
public class AsAnmar0fCondivisiController implements Serializable{
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
	private AsAnmar0fDao asAnmar0fDao;
	
	private List<AsAnmar0f> asAnmar0fLs;
	
	public List<AsAnmar0f> getAsAnmar0fLs(){
		// log.info("tmpDEBUGtmp: " + "> " + "getAsAnmar0fLs("  + ")");
		// log.info("tmpDEBUGtmp: " + "< " + "getAsAnmar0fLs");
		return asAnmar0fLs;
	}
	
	public void setAsAnmar0fLs(List<AsAnmar0f> asAnmar0fLs){
		// log.info("tmpDEBUGtmp: " + "> " + "setAsAnmar0fLs(" + AsAnmar0fLs + ")");
		this.asAnmar0fLs = asAnmar0fLs;
		// log.info("tmpDEBUGtmp: " + "< " + "setAsAnmar0fLs");
	}
	
	@PostConstruct
	public void aggiornaAsAnmar0fLs(){
		// log.info("tmpDEBUGtmp: " + "> " + "aggiornaAsAnmar0fLs(" + ")");
		asAnmar0fLs = asAnmar0fDao.getAll();
		// log.info("tmpDEBUGtmp: " + "< " + "aggiornaAsAnmar0fLs");
	}
	
	public int sortByNum(Object obj1, Object obj2){
		// log.info("tmpDEBUGtmp: " + "> " + "sortByNum(" + obj1 + ", " + obj2 + ")");
		Integer o1 = (Integer)obj1;
		Integer o2 = (Integer)obj2;
		log.info("sortById: " + o1 + "-" + o2);
		// log.info("tmpDEBUGtmp: " + "< " + "sortByNum");
		if(o1 < o2){
			return -1;
		}else if(o1 > o2){
			return 1;
		}
		return 0;
	}
	
	public int sortByStr(Object obj1, Object obj2){
		// log.info("tmpDEBUGtmp: " + "> " + "sortByStr(" + obj1 + ", " + obj2 + ")");
		String o1 = (String)obj1;
		String o2 = (String)obj2;
		log.info("sortByname: " + o1 + "-" + o2);
		// log.info("tmpDEBUGtmp: " + "< " + "sortByStr");
		if(o1.compareTo(o2) < 0){
			return -1;
		}else if(o1.compareTo(o2) > 0){
			return 1;
		}
		return 0;
	}
	
	public int sortByStrIC(Object obj1, Object obj2){
		// log.info("tmpDEBUGtmp: " + "> " + "sortByStrIC(" + obj1 + ", " + obj2 + ")");
		String o1 = (String)obj1;
		String o2 = (String)obj2;
		log.info("sortBynameIC: " + o1 + "-" + o2);
		// log.info("tmpDEBUGtmp: " + "< " + "sortByStrIC");
		if(o1.compareToIgnoreCase(o2) < 0){
			return -1;
		}else if(o1.compareToIgnoreCase(o2) > 0){
			return 1;
		}
		return 0;
	}
}

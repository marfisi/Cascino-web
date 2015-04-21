package it.cascino.controller;

import it.cascino.dao.CaratteristicheDao;
import it.cascino.model.Caratteristiche;
import java.util.List;
//import java.util.Locale;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;

@Named
@ApplicationScoped
public class CaratteristicheCondivisiController implements Serializable{
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
	private CaratteristicheDao caratteristicheDao;
	
	private List<Caratteristiche> caratteristicheLs;
	
	public List<Caratteristiche> getCaratteristicheLs(){
		return caratteristicheLs;
	}

	public void setCaratteristicheLs(List<Caratteristiche> caratteristicheLs){
		// log.info("tmpDEBUGtmp: " + "> " + "setArticoliLs(" + articoliLs + ")");
		this.caratteristicheLs = caratteristicheLs;
		// log.info("tmpDEBUGtmp: " + "< " + "setArticoliLs");
	}

	@PostConstruct
	public void aggiornaCaratteristicheLs(){
		// log.info("tmpDEBUGtmp: " + "> " + "aggiornaArticoliLs(" + ")");
		caratteristicheLs = caratteristicheDao.getAll();
		// log.info("tmpDEBUGtmp: " + "< " + "aggiornaArticoliLs");
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

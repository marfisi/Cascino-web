package it.cascino.controller;

import it.cascino.dao.FotoDao;
import it.cascino.model.Foto;
import java.util.ArrayList;
import java.util.List;
//import java.util.Locale;
//import java.io.File;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;

@Named
@ApplicationScoped
public class FotoCondivisiController implements Serializable{
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
	private FotoDao fotoDao;
	
	private List<Foto> fotoLs;

	private List<Foto> fotoMostraProdottoLs;
	private List<Foto> fotoMostraLogoLs;
	private List<Foto> fotoMostraSchedaLs;
	private List<Foto> fotoMostraAltroLs;
	private List<Foto> fotoMostraIndefinitaLs;

	private List<Foto> fotoMostraLeILs;
	private List<Foto> fotoMostraPeSeAeILs;
	
	private List<String> tagUtilizzatiLs;

	public List<Foto> getFotoLs(){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotoLs(" + ")");
		// log.info("tmpDEBUGtmp: " + "< " + "getFotoLs");				
		return fotoLs;
	}
	
	public void setFotoLs(List<Foto> fotoLs){
		// log.info("tmpDEBUGtmp: " + "> " + "setFotoLs(" + fotoLs + ")");
		this.fotoLs = fotoLs;
		// log.info("tmpDEBUGtmp: " + "< " + "setFotoLs");
	}
	
	public List<Foto> getFotoMostraProdottoLs(){
		return fotoMostraProdottoLs;
	}

	public void setFotoMostraProdottoLs(List<Foto> fotoMostraProdottoLs){
		this.fotoMostraProdottoLs = fotoMostraProdottoLs;
	}

	public List<Foto> getFotoMostraLogoLs(){
		return fotoMostraLogoLs;
	}

	public void setFotoMostraLogoLs(List<Foto> fotoMostraLogoLs){
		this.fotoMostraLogoLs = fotoMostraLogoLs;
	}

	public List<Foto> getFotoMostraSchedaLs(){
		return fotoMostraSchedaLs;
	}

	public void setFotoMostraSchedaLs(List<Foto> fotoMostraSchedaLs){
		this.fotoMostraSchedaLs = fotoMostraSchedaLs;
	}

	public List<Foto> getFotoMostraAltroLs(){
		return fotoMostraAltroLs;
	}

	public void setFotoMostraAltroLs(List<Foto> fotoMostraAltroLs){
		this.fotoMostraAltroLs = fotoMostraAltroLs;
	}

	public List<Foto> getFotoMostraIndefinitaLs(){
		return fotoMostraIndefinitaLs;
	}

	public void setFotoMostraIndefinitaLs(List<Foto> fotoMostraIndefinitaLs){
		this.fotoMostraIndefinitaLs = fotoMostraIndefinitaLs;
	}	
	
	public List<String> getTagUtilizzatiLs(){
		return tagUtilizzatiLs;
	}

	public void setTagUtilizzatiLs(List<String> tagUtilizzatiLs){
		this.tagUtilizzatiLs = tagUtilizzatiLs;
	}
 
	public List<Foto> getFotoMostraLeILs(){
		return fotoMostraLeILs;
	}

	public void setFotoMostraLeILs(List<Foto> fotoMostraLeILs){
		this.fotoMostraLeILs = fotoMostraLeILs;
	}

	public List<Foto> getFotoMostraPeSeAeILs(){
		return fotoMostraPeSeAeILs;
	}

	public void setFotoMostraPeSeAeILs(List<Foto> fotoMostraPeSeAeILs){
		this.fotoMostraPeSeAeILs = fotoMostraPeSeAeILs;
	}
	
	@PostConstruct
	public void aggiornaFotoLs(){
		// log.info("tmpDEBUGtmp: " + "> " + "aggiornaFotoLs(" + ")");
		fotoLs = fotoDao.getAll();
		tagUtilizzatiLs = fotoDao.getTagUtilizzati();
		
		fotoMostraProdottoLs = fotoDao.getMostraProdotto();
		fotoMostraLogoLs = fotoDao.getMostraLogo();
		fotoMostraSchedaLs = fotoDao.getMostraScheda();
		fotoMostraAltroLs = fotoDao.getMostraAltro();
		fotoMostraIndefinitaLs = fotoDao.getMostraIndefinito();
		
		fotoMostraLeILs = new ArrayList<Foto>();
		fotoMostraLeILs.addAll(fotoMostraLogoLs);
		fotoMostraLeILs.addAll(fotoMostraIndefinitaLs);
		
		fotoMostraPeSeAeILs = new ArrayList<Foto>();
		fotoMostraPeSeAeILs.addAll(fotoMostraProdottoLs);
		fotoMostraPeSeAeILs.addAll(fotoMostraSchedaLs);
		fotoMostraPeSeAeILs.addAll(fotoMostraAltroLs);
		fotoMostraPeSeAeILs.addAll(fotoMostraIndefinitaLs);

		// log.info("tmpDEBUGtmp: " + "< " + "aggiornaFotoLs");
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

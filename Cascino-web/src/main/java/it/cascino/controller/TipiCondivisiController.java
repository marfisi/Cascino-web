package it.cascino.controller;

import it.cascino.dao.TipiDao;
import it.cascino.model.Tipi;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named
@ApplicationScoped
public class TipiCondivisiController implements Serializable{
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
	private TipiDao tipiDao;
	
	private TreeNode albero;
	
	private List<Tipi> tipiLs;
	
	public List<Tipi> getTipiLs(){
		log.info("tmpDEBUGtmp: " + "> " + "getTipiLs("  + ")");
		log.info("tmpDEBUGtmp: " + "< " + "getTipiLs");
		return tipiLs;
	}
	
	public void setTipiLs(List<Tipi> tipiLs){
		log.info("tmpDEBUGtmp: " + "> " + "setTipiLs(" + tipiLs + ")");
		this.tipiLs = tipiLs;
		log.info("tmpDEBUGtmp: " + "< " + "setTipiLs");
	}
	
	public TreeNode getAlbero(){
		log.info("tmpDEBUGtmp: " + "> " + "getAlbero(" + ")");
		log.info("tmpDEBUGtmp: " + "< " + "getAlbero");
		return albero;
	}
	
	public void setAlbero(TreeNode albero){
		log.info("tmpDEBUGtmp: " + "> " + "setAlbero(" + albero + ")");
		this.albero = albero;
		log.info("tmpDEBUGtmp: " + "< " + "setAlbero");
	}
	
	// private, solo di servizio, ricorsiva
	private TreeNode popolaConFigli(int idPadre, TreeNode root){
		log.info("tmpDEBUGtmp: " + "> " + "popolaConFiglli(" + idPadre + ", " + root + ")");
		// log.info("popolaConFigli con id " + idPadre);
		
		TreeNode leaf = null;
		
		Tipi p = null;
		Iterator<Tipi> iterator = tipiLs.iterator();
		
		while(iterator.hasNext()){
			p = iterator.next();
			
			if(p.getTipoPadre().getId() == idPadre){
				leaf = new DefaultTreeNode(p, root);
				// sono nella riga nd, che ha tipo e tipoPadre = 1, avrei un loop infinito, quindi gestisco il caso senza chiamare la ricorsione
				if(p.getId() == 1){
					continue;
				}
				popolaConFigli(p.getId(), leaf);
			}
		}
		log.info("tmpDEBUGtmp: " + "< " + "popolaConFigli");
		return leaf;
	}
		
	@PostConstruct
	public void aggiornaTipiLs(){
		log.info("tmpDEBUGtmp: " + "> " + "aggiornaTipiLs(" + ")");
		tipiLs = tipiDao.getAll();
		albero = new DefaultTreeNode("root", null);
		popolaConFigli(1, albero);
		log.info("tmpDEBUGtmp: " + "< " + "aggiornaTipiLs");
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
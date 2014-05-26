package it.cascino.controller;

import it.cascino.dao.ArticoliDao;
import it.cascino.model.Articoli;
import it.cascino.model.Foto;
import java.util.List;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;

@Named
@SessionScoped
public class ArticoliController implements Serializable{
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
	
	@Inject
	private ArticoliDao articoliDao;
	
	private String esito;
	
	private List<Articoli> articoliLs;
	private List<Articoli> filteredArticoliLs;
	
	private Articoli articoloSel = new Articoli();
	
	public List<Articoli> getArticoliLs(){
		articoliLs = articoliDao.getAll();
		return articoliLs;
	}
	
	public void setArticoliLs(List<Articoli> articoliLs){
		this.articoliLs = articoliLs;
	}
	
	public Articoli getArticoloSel(){
		return articoloSel;
	}
	
	public void setArticoloSel(Articoli articoloSel){
		this.articoloSel = articoloSel;
	}
	
	public List<Articoli> getFilteredArticoliLs(){
		return filteredArticoliLs;
	}
	
	public void setFilteredArticoliLs(List<Articoli> filteredArticoliLs){
		this.filteredArticoliLs = filteredArticoliLs;
	}
	
	public void salva(){
		articoliDao.salva(articoloSel);
		if(articoloSel != null){
			esito = "Aggiunto articolo: " + articoloSel.getCodice();
			showGrowlInsMessage();
		}else{
			esito = "non e' stato caricato l'articolo!";
			showGrowlErrorMessage();
		}
	}
	
	public void aggiorna(){
		articoliDao.aggiorna(articoloSel);
		if(articoloSel != null){
			esito = "Aggiornato articolo: " + articoloSel.getCodice();
			showGrowlUpdMessage();
		}else{
			esito = "non e' stato aggiornato l'articolo! ";
			showGrowlErrorMessage();
		}
	}
	
	public void elimina(){
		articoliDao.elimina(articoloSel);
		if(articoloSel != null){
			esito = "Elimino articolo: " + articoloSel.getCodice();
			showGrowlDelMessage();
		}else{
			esito = "non ho trovato l'articolo!";
			showGrowlErrorMessage();
		}
	}
	
	public String getEsito(){
		return esito;
	}
	
	private void showGrowlInfoMessage(String message){
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}

	private void showGrowlUpdMessage(){
		String message = "Aggiornato con successo - " + esito + " >" + articoloSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlInsMessage(){
		String message = "Inserito con successo - " + esito + " >" + articoloSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlDelMessage(){
		String message = "Eliminato con successo - " + esito + " >" + articoloSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlErrorMessage(){
		String message = "Operazione fallita - " + esito + " >" + articoloSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", message));
		log.error(message);
	}
	
	public int sortByNum(Object obj1, Object obj2){
		Integer o1 = (Integer)obj1;
		Integer o2 = (Integer)obj2;
		log.info("sortById: " + o1 + "-" + o2);
		if(o1 < o2){
			return -1;
		}else if(o1 > o2){
			return 1;
		}
		return 0;
	}
	
	public int sortByStr(Object obj1, Object obj2){
		String o1 = (String)obj1;
		String o2 = (String)obj2;
		log.info("sortByname: " + o1 + "-" + o2);
		if(o1.compareTo(o2) < 0){
			return -1;
		}else if(o1.compareTo(o2) > 0){
			return 1;
		}
		return 0;
	}
	
	public int sortByStrIC(Object obj1, Object obj2){
		String o1 = (String)obj1;
		String o2 = (String)obj2;
		log.info("sortByname: " + o1 + "-" + o2);
		if(o1.compareToIgnoreCase(o2) < 0){
			return -1;
		}else if(o1.compareToIgnoreCase(o2) > 0){
			return 1;
		}
		return 0;
	}

	public Foto getFoto(Integer idArticoli){
		Foto fotoArticolo = new Foto();
		fotoArticolo = articoliDao.getFoto(idArticoli);
		if(fotoArticolo != null){
			esito = "selezionata foto " + fotoArticolo.getId() + " per articolo: " + idArticoli;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto per l'articolo!";
			showGrowlErrorMessage();
		}
		return fotoArticolo;
	}

//	public void setFotoArticolo(Foto fotoArticolo){
//		this.fotoArticolo = fotoArticolo;
//	}
	
}

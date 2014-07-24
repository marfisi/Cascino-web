package it.cascino.controller;

import it.cascino.dao.ArticoliDao;
import it.cascino.dao.FotoDao;
import it.cascino.model.Articoli;
import it.cascino.model.Foto;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

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
	
	@Inject
	private FotoDao fotoDao;
	
	private String esito;
	
	private List<Articoli> articoliLs;
	private List<Articoli> filteredArticoliLs;
	
	// private List<Articoli> articoliAutoCompleteLs;
	
	private Articoli articoloSel = new Articoli();
	
	DualListModel<Foto> fotoPickList;
	
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
	
	public DualListModel<Foto> getFotoPickList(){
		List<Foto> fotoPLsource = new ArrayList<Foto>();
		List<Foto> fotoPLtarget = new ArrayList<Foto>();
		
		fotoPLsource = fotoDao.getAll();
		fotoPLtarget = fotoDao.getAll().subList(3, 5);
		
		fotoPickList = new DualListModel<Foto>(fotoPLsource, fotoPLtarget);
		
		return fotoPickList;
	}
	
	public void setFotoPickList(DualListModel<Foto> fotoPickList){
		this.fotoPickList = fotoPickList;
	}
	
	public List<String> articoliAutoCompleteLs(String str){
		List<String> articoliAutoCompleteLs = articoliDao.getArticoliAutoCompleteLs(str.toUpperCase());
		
		return articoliAutoCompleteLs;
	}
	
	public List<String> produttoriAutoCompleteLs(String str){
		List<String> produttoriAutoCompleteLs = articoliDao.getProduttoriAutoCompleteLs(str.toUpperCase());
		
		return produttoriAutoCompleteLs;
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
	
	public Foto getFotoDaArticolo(Integer idArticoli){
		Foto fotoArticolo = new Foto();
		fotoArticolo = articoliDao.getFotoDaArticolo(idArticoli);
		if(fotoArticolo != null){
			esito = "selezionata foto " + fotoArticolo.getId() + " per articolo: " + idArticoli;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto per l'articolo!";
			showGrowlErrorMessage();
		}
		return fotoArticolo;
	}
	
	// public void setFotoArticolo(Foto fotoArticolo){
	// this.fotoArticolo = fotoArticolo;
	// }
	
	public void onPickListTransfer(TransferEvent event){
		StringBuilder builder = new StringBuilder();
		for(Object item : event.getItems()){
			builder.append(((Foto)item).getOriginale()).append("<br />");
		}
		
		FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary("Items Transferred");
		msg.setDetail(builder.toString());
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
}

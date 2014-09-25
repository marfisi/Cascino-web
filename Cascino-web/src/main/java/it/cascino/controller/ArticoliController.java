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
	
	private List<Foto> fotoPLsource = new ArrayList<Foto>();
	private List<Foto> fotoPLtarget = new ArrayList<Foto>();
	private DualListModel<Foto> fotoPickList = new DualListModel<Foto>(fotoPLsource, fotoPLtarget);
//	private Boolean fotoPLtargetModif = true;

//	ArticoliController(){
////		log.info("tmpDEBUGtmp: " + "> " + "ArticoliController(" + ")");
////		log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
//		Articoli articolo = new Articoli();
//		articolo.setId(1);
//		setArticoloSel(articolo);
////		log.info("tmpDEBUGtmp: " + "< " + "ArticoliController");
//	}
	
	public List<Articoli> getArticoliLs(){
		log.info("tmpDEBUGtmp: " + "> " + "getArticoliLs(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		articoliLs = articoliDao.getAll();
		log.info("tmpDEBUGtmp: " + "< " + "getArticoliLs");
		return articoliLs;
	}
	
	public void setArticoliLs(List<Articoli> articoliLs){
		log.info("tmpDEBUGtmp: " + "> " + "setArticoliLs(" + articoliLs + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		this.articoliLs = articoliLs;
		log.info("tmpDEBUGtmp: " + "< " + "setArticoliLs");
	}
	
	public Articoli getArticoloSel(){
		log.info("tmpDEBUGtmp: " + "> " + "getArticoloSel(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		log.info("tmpDEBUGtmp: " + "< " + "getArticoloSel");
		return articoloSel;
	}
	
	public void setArticoloSel(Articoli articoloSel){
		log.info("tmpDEBUGtmp: " + "> " + "setArticoloSel(" + articoloSel + ")");
		if((articoloSel != null) && (this.articoloSel.getId() != articoloSel.getId())){
			log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null") + " this.id: " + ((this.articoloSel != null) ? this.articoloSel.getId() : "null"));
			this.articoloSel = articoloSel;
			// aggiorno le variabili che dipendono dell'articolo selezionato
			if(true){
				fotoPLsource = fotoDao.getAll();
				fotoPLtarget = fotoDao.getFotoArticoloOrdLsDaIdArticolo(articoloSel.getId());				
				fotoPickList = new DualListModel<Foto>(fotoPLsource, fotoPLtarget);
//				fotoPickList = new DualListModel<Foto>(fotoPLsource, getFotoPLtarget());
			}
		}
		log.info("tmpDEBUGtmp: " + "< " + "setArticoloSel");
	}
	
	public List<Articoli> getFilteredArticoliLs(){
		log.info("tmpDEBUGtmp: " + "> " + "getFilteredArticoliLs(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		log.info("tmpDEBUGtmp: " + "< " + "getFilteredArticoliLs");
		return filteredArticoliLs;
	}
	
	public void setFilteredArticoliLs(List<Articoli> filteredArticoliLs){
		log.info("tmpDEBUGtmp: " + "> " + "setFilteredArticoliLs(" + filteredArticoliLs + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		this.filteredArticoliLs = filteredArticoliLs;
		log.info("tmpDEBUGtmp: " + "< " + "setFilteredArticoliLs");
	}
	
	public DualListModel<Foto> getFotoPickList(){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoPickList(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		log.info("tmpDEBUGtmp: " + "< " + "getFotoPickList");
		return fotoPickList;
	}
	
//	private List<Foto> getFotoPLtarget(){
//		log.info("tmpDEBUGtmp: " + "> " + "getFotoPLtarget(" + ")");
//		log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
//		log.info("tmpDEBUGtmp: " + "< " + "getFotoPLtarget");
//		return fotoPLtarget;
//	}
	
	public void setFotoPickList(DualListModel<Foto> fotoPickList){
		log.info("tmpDEBUGtmp: " + "> " + "setFotoPickList(" + fotoPickList + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		this.fotoPickList = fotoPickList;
		log.info("tmpDEBUGtmp: " + "< " + "setFotoPickList");
	}
	
	public List<String> articoliAutoCompleteLs(String str){
		log.info("tmpDEBUGtmp: " + "> " + "articoliAutoCompleteLs(" + str + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		List<String> articoliAutoCompleteLs = articoliDao.getArticoliAutoCompleteLs(str.toUpperCase());
		
		log.info("tmpDEBUGtmp: " + "< " + "articoliAutoCompleteLs");
		return articoliAutoCompleteLs;
	}
	
	public void salva(){
		log.info("tmpDEBUGtmp: " + "> " + "salva(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		
		articoliDao.salva(articoloSel);
		if(articoloSel != null){
			esito = "Aggiunto articolo: " + articoloSel.getCodice();
			showGrowlInsMessage();
		}else{
			esito = "non e' stato caricato l'articolo!" + " (articolo: " + ((articoloSel != null) ? articoloSel.getId() : "null") + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void aggiorna(){
		log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		
		articoliDao.aggiorna(articoloSel, fotoPLtarget);
		if(articoloSel != null){
			esito = "Aggiornato articolo: " + articoloSel.getCodice();
			showGrowlUpdMessage();
		}else{
			esito = "non e' stato aggiornato l'articolo!" + " (articolo: " + ((articoloSel != null) ? articoloSel.getId() : "null") + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	public void elimina(){
		log.info("tmpDEBUGtmp: " + "> " + "elimina(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		
		articoliDao.elimina(articoloSel);
		if(articoloSel != null){
			esito = "Elimino articolo: " + articoloSel.getCodice();
			showGrowlDelMessage();
		}else{
			esito = "non ho trovato l'articolo!" + " (articolo: " + ((articoloSel != null) ? articoloSel.getId() : "null") + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	public String getEsito(){
		log.info("tmpDEBUGtmp: " + "> " + "getEsito(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		log.info("tmpDEBUGtmp: " + "< " + "getEsito");
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
		log.info("tmpDEBUGtmp: " + "> " + "sortByNum(" + obj1 + ", " + obj2 + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
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
		log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
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
		log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
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
	
	public void onPickListTransfer(TransferEvent event){
		log.info("tmpDEBUGtmp: " + "> " + "onPickListTransfer(" + event + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
//		fotoPLtargetModif = true;
		StringBuilder builder = new StringBuilder();
		for(Object item : event.getItems()){
			builder.append("selezionata la foto: ").append(((Foto)item).getOriginale()).append(((Foto)item).getId()).append("\n");
		}
		showGrowlInfoMessage(builder.toString());
		// pltarget dovrei modificarlo qui
		log.info("tmpDEBUGtmp: " + "< " + "onPickListTransfer");
	}
	
}

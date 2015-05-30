package it.cascino.controller;

import it.cascino.dao.ArticoliDao;
//import it.cascino.dao.FotoDao;
import it.cascino.model.Articoli;
import it.cascino.model.Caratteristiche;
import it.cascino.model.Foto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;
//import org.primefaces.event.TransferEvent;
//import org.primefaces.model.DualListModel;

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
	
	@Inject
	private ArticoliCondivisiController articoliCondivisiController;
	
	@Inject
	FotoCondivisiController fotoCondivisiController;
	@Inject
	FotoController fotoController;
	@Inject
	CaratteristicheController caratteristicheController;
	
	// private List<Articoli> articoliLs;
	private List<Articoli> filteredArticoliLs;
	
	// private List<Articoli> articoliAutoCompleteLs;
	
	private Articoli articoloSel = new Articoli();
	private Articoli articoloNew = new Articoli();
	
	// private List<Foto> fotoPLsource = new ArrayList<Foto>();
	// private List<Foto> fotoPLtarget = new ArrayList<Foto>();
	// private DualListModel<Foto> fotoPickList = new DualListModel<Foto>(fotoPLsource, fotoPLtarget);
	// private DualListModel<Foto> fotoPickList = new DualListModel<Foto>(new ArrayList<Foto>(), new ArrayList<Foto>());
	// private Boolean fotoPLtargetModif = true;
	private List<Foto> fotoPerArticolo = new ArrayList<Foto>();
	
//	private List<Caratteristiche> caratteristichePerArticolo = new ArrayList<Caratteristiche>();
	
	// ArticoliController(){
	// // // log.info("tmpDEBUGtmp: " + "> " + "ArticoliController(" + ")");
	// // // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
	// Articoli articolo = new Articoli();
	// articolo.setId(1);
	// setArticoloSel(articolo);
	// // // log.info("tmpDEBUGtmp: " + "< " + "ArticoliController");
	// }
	
	// public List<Articoli> getArticoliLs(){
	// // log.info("tmpDEBUGtmp: " + "> " + "getArticoliLs(" + ")");
	// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
	// articoliLs = articoliDao.getAll();
	// // log.info("tmpDEBUGtmp: " + "< " + "getArticoliLs");
	// return articoliLs;
	// }
	//
	// public void setArticoliLs(List<Articoli> articoliLs){
	// // log.info("tmpDEBUGtmp: " + "> " + "setArticoliLs(" + articoliLs + ")");
	// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
	// this.articoliLs = articoliLs;
	// // log.info("tmpDEBUGtmp: " + "< " + "setArticoliLs");
	// }
	
	public Articoli getArticoloSel(){
		  log.info("tmpDEBUGtmp: " + "> " + "GetSel");
		// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
//		if(articoloSel == null){
//			Articoli a = new Articoli();
//			a.setId(1);
//			articoloSel = a;
//		}
		// // log.info("tmpDEBUGtmp: " + "< " + "getArticoloSel");
		return articoloSel;
	}
	
	public void setArticoloSel(Articoli articoloSel){
		  log.info("tmpDEBUGtmp: " + "> " + "SetSel");
		// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null") + " this.id: " + ((this.articoloSel != null) ? this.articoloSel.getId() : "null"));
		// fotoPLtarget = new ArrayList<Foto>();
		fotoPerArticolo = new ArrayList<Foto>();
		if(articoloSel != null){
			this.articoloSel = articoloSel;
			// aggiorno le variabili che dipendono dell'articolo selezionato
//			if(this.articoloSel.getId() != articoloSel.getId()){
			// fotoPLtarget = fotoController.getFotoArticoloOrdLsDaIdArticolo(articoloSel.getId());
			fotoPerArticolo = fotoController.getFotoArticoloOrdLsDaIdArticolo(articoloSel.getId());
//			}
		}
		// fotoPickList = new DualListModel<Foto>(fotoPLsource, fotoPLtarget);
		// // log.info("tmpDEBUGtmp: " + "< " + "setArticoloSel");
	}
	
	public Articoli getArticoloNew(){
		return articoloNew;
	}
	
	public void setArticoloNew(Articoli articoloNew){
		this.articoloNew = articoloNew;
	}
	
	// chiamata quando faccio nuovo, per non avere i campi sporchi da una selezione che deriva dalla tabella
	public void resetOnNewArticoloSel(){
		// log.info("tmpDEBUGtmp: " + "> " + "resetOnNew");
		Articoli a = new Articoli();
		a.setId(1);
		articoloSel = a;
		fotoPerArticolo = new ArrayList<Foto>();
		caratteristicheController.setCaratteristicheArticoloSelLs(new ArrayList<Caratteristiche>());
	}
	
	public List<Articoli> getFilteredArticoliLs(){
		// // log.info("tmpDEBUGtmp: " + "> " + "getFilteredArticoliLs(" + ")");
		// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		// // log.info("tmpDEBUGtmp: " + "< " + "getFilteredArticoliLs");
		return filteredArticoliLs;
	}
	
	public void setFilteredArticoliLs(List<Articoli> filteredArticoliLs){
		// // log.info("tmpDEBUGtmp: " + "> " + "setFilteredArticoliLs(" + filteredArticoliLs + ")");
		// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		this.filteredArticoliLs = filteredArticoliLs;
		// // log.info("tmpDEBUGtmp: " + "< " + "setFilteredArticoliLs");
	}
	
	public List<Foto> getFotoPerArticolo(){
		// // log.info("tmpDEBUGtmp: " + "> " + "getFotoPerArticolo(" + ")");
		// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		// // log.info("tmpDEBUGtmp: " + "< " + "getFotoPerArticolo");
		return fotoPerArticolo;
	}
	
	public void setFotoPerArticolo(List<Foto> fotoPerArticolo){
		// // log.info("tmpDEBUGtmp: " + "> " + "setFotoPerArticolo(" + fotoPerArticolo + ")");
		// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		this.fotoPerArticolo = fotoPerArticolo;
		// // log.info("tmpDEBUGtmp: " + "< " + "setFotoPerArticolo");
	}
	
	// public DualListModel<Foto> getFotoPickList(){
	// // log.info("tmpDEBUGtmp: " + "> " + "getFotoPickList(" + ")");
	// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
	//
	// // List<Foto> fotoPLsource = new ArrayList<Foto>();
	// // List<Foto> fotoPLtarget = new ArrayList<Foto>();
	// // if((articoloSel != null) && (articoloSel.getId() !=null)){
	// // fotoPLsource = fotoController.getFotoLsDynPop(); // .getFotoLs(); // fotoDao.getAll();
	// // fotoPLtarget = fotoController.getFotoArticoloOrdLsDaIdArticolo(articoloSel.getId());
	// // // libero la lista dei sorgenti da quelli già selezionati, per evitare i doppioni
	// // if(!(fotoPLtarget.isEmpty())){
	// // fotoPLsource.removeAll(fotoPLtarget);
	// // }
	// // }
	// // fotoPickList = new DualListModel<Foto>(fotoPLsource, fotoPLtarget);
	// //
	//
	// // log.info("tmpDEBUGtmp: " + "< " + "getFotoPickList");
	// return fotoPickList;
	// }
	
	// public void setFotoPickList(DualListModel<Foto> fotoPickList){
	// // log.info("tmpDEBUGtmp: " + "> " + "setFotoPickList(" + fotoPickList + ")");
	// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
	// this.fotoPickList = fotoPickList;
	// // log.info("tmpDEBUGtmp: " + "< " + "setFotoPickList");
	// }
	
	// public void popolaFotoPLsource(){
	// fotoPLsource = fotoCondivisiController.getFotoLs();
	// fotoPLsource = fotoPLsource.subList(0, 20); // TMP TMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMPPPPPPPPPPP
	// // libero la lista dei sorgenti da quelli già selezionati, per evitare i doppioni
	// if(!(fotoPLtarget.isEmpty())){
	// fotoPLsource.removeAll(fotoPLtarget);
	// }
	// fotoPickList = new DualListModel<Foto>(fotoPLsource, fotoPLtarget);
	// }
	//
	// public void svuotaFotoPLsource(){
	// fotoPLsource = new ArrayList<Foto>();
	// fotoPickList = new DualListModel<Foto>(fotoPLsource, fotoPLtarget);
	// }
	
	// private List<Foto> getFotoPLsource(){
	// // log.info("tmpDEBUGtmp: " + "> " + "getFotoPLsource(" + ")");
	// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
	// // log.info("tmpDEBUGtmp: " + "< " + "getFotoPLsource");
	//
	// return fotoPLsource;
	// }
	//
	// private void setFotoPLsource(List<Foto> fotoPLsource){
	// // log.info("tmpDEBUGtmp: " + "> " + "setFotoPLsource(" + ")");
	// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
	// this.fotoPLsource = fotoPLsource;
	// // log.info("tmpDEBUGtmp: " + "< " + "setFotoPLsource");
	// }
	//
	// private List<Foto> getFotoPLtarget(){
	// // log.info("tmpDEBUGtmp: " + "> " + "getFotoPLtarget(" + ")");
	// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
	// // log.info("tmpDEBUGtmp: " + "< " + "getFotoPLtarget");
	// return fotoPLtarget;
	// }
	//
	// private void setFotoPLtarget(List<Foto> fotoPLtarget){
	// // log.info("tmpDEBUGtmp: " + "> " + "setFotoPLtarget(" + ")");
	// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
	// this.fotoPLtarget = fotoPLtarget;
	// // log.info("tmpDEBUGtmp: " + "< " + "setFotoPLtarget");
	// }
	
	public List<String> articoliAutoCompleteLs(String str){
		// // log.info("tmpDEBUGtmp: " + "> " + "articoliAutoCompleteLs(" + str + ")");
		// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		List<String> articoliAutoCompleteLs = articoliDao.getArticoliAutoCompleteLs(str.toUpperCase());
		
		// // log.info("tmpDEBUGtmp: " + "< " + "articoliAutoCompleteLs");
		return articoliAutoCompleteLs;
	}
	
	public void salva(){
		// // log.info("tmpDEBUGtmp: " + "> " + "salva(" + ")");
		// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		
		// svuoto la lista delle foto
		fotoController.svuotaFotoLsDynPop();
		// svuotaFotoPLsource();
		
		if(articoloNew.getDescrizioneAs400() == null){
			articoloNew.setDescrizioneAs400("da ereditare da AS400");
		}
		// articoliDao.salva(articoloSel, fotoPickList.getTarget());
		articoliDao.salva(articoloNew, fotoPerArticolo, caratteristicheController.getCaratteristicheArticoloSelLs());
		if(articoloNew != null){
			esito = "Aggiunto articolo: " + articoloNew.getCodice();
			showGrowlInsMessage();
		}else{
			esito = "non e' stato caricato l'articolo!" + " (articolo: " + ((articoloNew != null) ? articoloNew.getId() : "null") + ")";
			showGrowlErrorMessage();
		}
		// aggiorno la lista condivisa
		articoliCondivisiController.aggiornaArticoliLs();
		articoloNew = new Articoli();
		// // log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void aggiorna(){
		// // log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + ")");
		// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		
		// svuoto la lista delle foto
		fotoController.svuotaFotoLsDynPop();
		// svuotaFotoPLsource();
		
		// articoliDao.aggiorna(articoloSel, fotoPickList.getTarget());
		articoliDao.aggiorna(articoloSel, fotoPerArticolo);
		if(articoloSel != null){
			esito = "Aggiornato articolo: " + articoloSel.getCodice();
			showGrowlUpdMessage();
		}else{
			esito = "non e' stato aggiornato l'articolo!" + " (articolo: " + ((articoloSel != null) ? articoloSel.getId() : "null") + ")";
			showGrowlErrorMessage();
		}
		// aggiorno la lista condivisa
		articoliCondivisiController.aggiornaArticoliLs();
		// // log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	public void elimina(){
		// // log.info("tmpDEBUGtmp: " + "> " + "elimina(" + ")");
		// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		
		// svuoto la lista delle foto
		fotoController.svuotaFotoLsDynPop();
		// svuotaFotoPLsource();
		
		articoliDao.elimina(articoloSel);
		if(articoloSel != null){
			esito = "Elimino articolo: " + articoloSel.getCodice();
			showGrowlDelMessage();
		}else{
			esito = "non ho trovato l'articolo!" + " (articolo: " + ((articoloSel != null) ? articoloSel.getId() : "null") + ")";
			showGrowlErrorMessage();
		}
		// aggiorno la lista condivisa
		articoliCondivisiController.aggiornaArticoliLs();
		// // log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	public String getEsito(){
		// // log.info("tmpDEBUGtmp: " + "> " + "getEsito(" + ")");
		// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		// // log.info("tmpDEBUGtmp: " + "< " + "getEsito");
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
		String message = "Inserito con successo - " + esito + " >" + articoloNew + "<";
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
	
	// public int sortByNum(Object obj1, Object obj2){
	// // // log.info("tmpDEBUGtmp: " + "> " + "sortByNum(" + obj1 + ", " + obj2 + ")");
	// // // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
	// Integer o1 = (Integer)obj1;
	// Integer o2 = (Integer)obj2;
	// log.info("sortById: " + o1 + "-" + o2);
	// // // log.info("tmpDEBUGtmp: " + "< " + "sortByNum");
	// if(o1 < o2){
	// return -1;
	// }else if(o1 > o2){
	// return 1;
	// }
	// return 0;
	// }
	//
	// public int sortByStr(Object obj1, Object obj2){
	// // log.info("tmpDEBUGtmp: " + "> " + "sortByStr(" + obj1 + ", " + obj2 + ")");
	// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
	// String o1 = (String)obj1;
	// String o2 = (String)obj2;
	// log.info("sortByname: " + o1 + "-" + o2);
	// // log.info("tmpDEBUGtmp: " + "< " + "sortByStr");
	// if(o1.compareTo(o2) < 0){
	// return -1;
	// }else if(o1.compareTo(o2) > 0){
	// return 1;
	// }
	// return 0;
	// }
	//
	// public int sortByStrIC(Object obj1, Object obj2){
	// // log.info("tmpDEBUGtmp: " + "> " + "sortByStrIC(" + obj1 + ", " + obj2 + ")");
	// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
	// String o1 = (String)obj1;
	// String o2 = (String)obj2;
	// log.info("sortBynameIC: " + o1 + "-" + o2);
	// // log.info("tmpDEBUGtmp: " + "< " + "sortByStrIC");
	// if(o1.compareToIgnoreCase(o2) < 0){
	// return -1;
	// }else if(o1.compareToIgnoreCase(o2) > 0){
	// return 1;
	// }
	// return 0;
	// }
	
//	public void onPickListTransfer(TransferEvent event){
//		// log.info("tmpDEBUGtmp: " + "> " + "onPickListTransfer(" + event + ")");
//		// log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
//		// fotoPLtargetModif = true;
//		StringBuilder builder = new StringBuilder();
//		for(Object item : event.getItems()){
//			builder.append("selezionata e spostata la foto: ").append(((Foto)item).getId()).append("\n");
//		}
//		showGrowlInfoMessage(builder.toString());
//		// pltarget dovrei modificarlo qui
//		// log.info("tmpDEBUGtmp: " + "< " + "onPickListTransfer");
//	}
	
	
	private Foto fotoPerArticoloSel = new Foto();

	public Foto getFotoPerArticoloSel(){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotoPerArticoloSel(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoPerArticoloSel != null) ? fotoPerArticoloSel.getId() : "null"));
		if(fotoPerArticoloSel == null){
			Foto f = new Foto();
			f.setId(1);
			fotoPerArticoloSel = f;
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getFotoPerArticoloSel");
		return fotoPerArticoloSel;
	}
	
	public void setFotoPerArticoloSel(Foto fotoPerArticoloSel){
		// log.info("tmpDEBUGtmp: " + "> " + "setFotoPerArticoloSel(" + fotoPerArticoloSel + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoPerArticoloSel != null) ? fotoPerArticoloSel.getId() : "null"));
		if(fotoPerArticoloSel != null){
			this.fotoPerArticoloSel = fotoPerArticoloSel;
		}
		// log.info("tmpDEBUGtmp: " + "< " + "setFotoPerArticoloSel");
	}

	public void onAggiungiFotoArticoloButton(ActionEvent actionEvent){
		// log.info("tmpDEBUGtmp: " + "> " + "onAggiungiFotoArticoloButton(" + actionEvent + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		boolean giaIns = false;
		Integer fsid = fotoController.getFotoSel().getId();
		if((fotoController.getFotoSel() != null)&&(fsid != 1)){
			// controllare se e' gia' in lista
			Iterator<Foto> iterF = fotoPerArticolo.iterator();
			while(iterF.hasNext()){
				Foto fd = iterF.next();
				if(fd.getId().intValue() == fsid.intValue()){
					giaIns = true;
					break;
				}
			}
			if(giaIns == false){
				fotoPerArticolo.add(fotoController.getFotoSel());
			}
		}
		// log.info("tmpDEBUGtmp: " + "< " + "onAggiungiFotoArticoloButton");
	}
	
	public void onRimuoviFotoArticoloButton(ActionEvent actionEvent){
		// log.info("tmpDEBUGtmp: " + "> " + "onRimuoviFotoArticoloButton(" + actionEvent + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
//		fotoPerArticolo.remove(fotoController.getFotoSel());
		fotoPerArticolo.remove(fotoPerArticoloSel);
		// log.info("tmpDEBUGtmp: " + "< " + "onRimuoviFotoArticoloButton");
	}
	
	public void onAlzaFotoArticoloButton(ActionEvent actionEvent){
		// log.info("tmpDEBUGtmp: " + "> " + "onAlzaFotoArticoloButton(" + actionEvent + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		int indiceFoto = fotoPerArticolo.indexOf(fotoPerArticoloSel);
		if(indiceFoto > 0){
			Foto fotoSwap =  fotoPerArticolo.get(indiceFoto - 1);
			fotoPerArticolo.set(indiceFoto - 1, fotoPerArticoloSel);
			fotoPerArticolo.set(indiceFoto , fotoSwap);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "onAlzaFotoArticoloButton");
	}
	
	public void onAbbassaFotoArticoloButton(ActionEvent actionEvent){
		// log.info("tmpDEBUGtmp: " + "> " + "onAbbassaFotoArticoloButton(" + actionEvent + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		int indiceFoto = fotoPerArticolo.indexOf(fotoPerArticoloSel);
		if(indiceFoto < fotoPerArticolo.size()-1){
			Foto fotoSwap =  fotoPerArticolo.get(indiceFoto + 1);
			fotoPerArticolo.set(indiceFoto +1, fotoPerArticoloSel);
			fotoPerArticolo.set(indiceFoto , fotoSwap);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "onAbbassaFotoArticoloButton");
	}
}

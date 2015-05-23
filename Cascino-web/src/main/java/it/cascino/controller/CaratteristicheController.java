package it.cascino.controller;

import it.cascino.dao.CaratteristicheDao;
import it.cascino.model.Caratteristiche;
import it.cascino.model.Foto;
// import it.cascino.model.Foto;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.Iterator;
import java.util.List;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
// import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
// import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.primefaces.event.RowEditEvent;

@Named
@SessionScoped
public class CaratteristicheController implements Serializable{
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
	private CaratteristicheDao caratteristicheDao;
	
	private String esito;
	
	@Inject
	private CaratteristicheCondivisiController caratteristicheCondivisiController;
	
	private List<Caratteristiche> filteredCaratteristicheLs;
	
	private Caratteristiche caratteristicaSel = new Caratteristiche();
	// private Caratteristiche caratteristicaNew = new Caratteristiche(); // non e' necessaria
	
	private Caratteristiche caratteristicaDaAggiungere;
	private Caratteristiche caratteristicaDaEliminare;
	private List<Caratteristiche> caratteristicheArticoloSelLs = new ArrayList<Caratteristiche>();
	
	public Caratteristiche getCaratteristicaSel(){
		// // log.info("tmpDEBUGtmp: " + "> " + "getArticoloSel(" + ")");
		// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		if(caratteristicaSel == null){
			Caratteristiche c = new Caratteristiche();
			c.setId(1);
			caratteristicaSel = c;
		}
		// // log.info("tmpDEBUGtmp: " + "< " + "getArticoloSel");
		return caratteristicaSel;
	}
	
	public void setCaratteristicaSel(Caratteristiche caratteristicaSel){
		// // log.info("tmpDEBUGtmp: " + "> " + "setArticoloSel(" + articoloSel + ")");
		// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null") + " this.id: " + ((this.articoloSel != null) ? this.articoloSel.getId() : "null"));
		if(caratteristicaSel != null){
			this.caratteristicaSel = caratteristicaSel;
		}
		// // log.info("tmpDEBUGtmp: " + "< " + "setArticoloSel");
	}
	
	// chiamata quando faccio nuovo, per non avere i campi sporchi da una selezione che deriva dalla tabella
	public void resetOnNewCaratteristicaSel(){
		Caratteristiche c = new Caratteristiche();
		c.setId(1);
		caratteristicaSel = c;
	}
	
	public List<String> caratteristicheClasseAutoCompleteLs(String str){
		List<String> caratteristicheAutoCompleteLs = caratteristicheDao.getCaratteristicheClasseAutoCompleteLs(str.toUpperCase());
		return caratteristicheAutoCompleteLs;
	}
	
	public List<String> caratteristicheUnitaMisuraAutoCompleteLs(String str){
		FacesContext context = FacesContext.getCurrentInstance();
		String classe = (String)UIComponent.getCurrentComponent(context).getAttributes().get("classeUnitaMisAutoCompl");
		if(classe == null){
			classe = "";
		}
		List<String> caratteristicheAutoCompleteLs = caratteristicheDao.getCaratteristicheUnitaMisuraAutoCompleteLs(str.toUpperCase(), classe.toUpperCase());
		return caratteristicheAutoCompleteLs;
	}
	
	public void salva(){
		// // log.info("tmpDEBUGtmp: " + "> " + "salva(" + ")");
		// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		
		caratteristicheDao.salva(caratteristicaSel);
		if(caratteristicaSel != null){
			esito = "Aggiunta caratteristica: " + caratteristicaSel.getId();
			showGrowlInsMessage();
		}else{
			esito = "non e' stata caricata la caratteristica!" + " (caratteristica: " + ((caratteristicaSel != null) ? caratteristicaSel.getId() : "null") + ")";
			showGrowlErrorMessage();
		}
		// aggiorno la lista condivisa
		caratteristicheCondivisiController.aggiornaCaratteristicheLs();
		// // log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void aggiorna(){
		// // log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + ")");
		// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		
		caratteristicheDao.aggiorna(caratteristicaSel);
		if(caratteristicaSel != null){
			esito = "Aggiornata caratteristica: " + caratteristicaSel.getId();
			showGrowlUpdMessage();
		}else{
			esito = "non e' stata aggiornata la caratteristica!" + " (caratteristica: " + ((caratteristicaSel != null) ? caratteristicaSel.getId() : "null") + ")";
			showGrowlErrorMessage();
		}
		// aggiorno la lista condivisa
		caratteristicheCondivisiController.aggiornaCaratteristicheLs();
		// // log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	public void elimina(){
		// // log.info("tmpDEBUGtmp: " + "> " + "elimina(" + ")");
		// // log.info("tmpDEBUGtmp: " + "id: " + ((articoloSel != null) ? articoloSel.getId() : "null"));
		
		caratteristicheDao.elimina(caratteristicaSel);
		if(caratteristicaSel != null){
			esito = "Elimina caratteristica: " + caratteristicaSel.getId();
			showGrowlDelMessage();
		}else{
			esito = "non ho trovato la caratteristica!" + " (caratteristica: " + ((caratteristicaSel != null) ? caratteristicaSel.getId() : "null") + ")";
			showGrowlErrorMessage();
		}
		// aggiorno la lista condivisa
		caratteristicheCondivisiController.aggiornaCaratteristicheLs();
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
		String message = "Aggiornato con successo - " + esito + " >" + caratteristicaSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlInsMessage(){
		String message = "Inserito con successo - " + esito + " >" + caratteristicaSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlDelMessage(){
		String message = "Eliminato con successo - " + esito + " >" + caratteristicaSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlErrorMessage(){
		String message = "Operazione fallita - " + esito + " >" + caratteristicaSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", message));
		log.error(message);
	}
	
	public Caratteristiche getCaratteristicaPerId(Integer idCaratteristica){
		Caratteristiche caratteristica = new Caratteristiche();
		caratteristica = caratteristicheDao.getCaratteristicaPerId(idCaratteristica);
		if(caratteristica != null){
			esito = "caratteristica " + caratteristica;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la caratteristica!" + " (id: " + idCaratteristica + ")";
			showGrowlErrorMessage();
		}
		return caratteristica;
	}
	
	public List<Caratteristiche> getCaratteristicheListPerTipo(Integer idTipo){
		List<Caratteristiche> caratteristicheLs = new ArrayList<Caratteristiche>();
		caratteristicheLs = caratteristicheDao.getCaratteristicheListPerTipo(idTipo);
		if(caratteristicheLs != null){
			esito = "caratteristica " + caratteristicheLs;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la caratteristica per tipo!" + " (id: " + idTipo + ")";
			showGrowlErrorMessage();
		}
		return caratteristicheLs;
	}
	
	public List<Caratteristiche> getCaratteristicheListPerArticolo(Integer idArticolo){
		List<Caratteristiche> caratteristicheLs = new ArrayList<Caratteristiche>();
		caratteristicheLs = caratteristicheDao.getCaratteristicheListPerArticolo(idArticolo);
		if(caratteristicheLs != null){
			esito = "caratteristica " + caratteristicheLs;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la caratteristica per articolo!" + " (id: " + idArticolo + ")";
			showGrowlErrorMessage();
		}
		return caratteristicheLs;
	}
	
	// public List<String> getCaratteristicheAutoCompleteLs(String str){
	// List<Caratteristiche> caratteristicheLs = new ArrayList<Caratteristiche>();
	// caratteristicheLs = caratteristicheDao.getCaratteristicheListPerTipo(idTipo);
	// if(caratteristicheLs != null){
	// esito = "caratteristica " + caratteristicheLs;
	// showGrowlInfoMessage(esito);
	// }else{
	// esito = "non e' stata trovata la caratteristica per tipo!" + " (id: " + idTipo + ")";
	// showGrowlErrorMessage();
	// }
	// return caratteristicheLs;
	// }
	
	// public List<String> tagAutoCompleteLs(String str){
	// List<String> tagAutoCompleteLs = new ArrayList<String>(fotoCondivisiController.getTagUtilizzatiLs());
	// Iterator<String> iterTag = tagAutoCompleteLs.iterator();
	// while(iterTag.hasNext()){
	// String tag = iterTag.next();
	// if(StringUtils.containsIgnoreCase(tag, str)){
	// continue;
	// }
	// iterTag.remove();
	// }
	// return tagAutoCompleteLs;
	// }
	
//	public List<Caratteristiche> getCaratteristicheListPerArticoliSimili(Integer idArticolo){
//		List<Caratteristiche> caratteristicheLs = new ArrayList<Caratteristiche>();
//		caratteristicheLs = caratteristicheDao.getCaratteristicheListPerArticoliSimili(idArticolo);
//		if(caratteristicheLs != null){
//			esito = "caratteristica " + caratteristicheLs;
//			showGrowlInfoMessage(esito);
//		}else{
//			esito = "non e' stata trovata la caratteristica per articolo!" + " (id: " + idArticolo + ")";
//			showGrowlErrorMessage();
//		}
//		return caratteristicheLs;
//	}
	
	public List<Caratteristiche> getCaratteristicheListPerArticoliSimili(List<Foto> fotoPerArticoloLs){
//		List<Foto> fotoPerArticoloLs = new ArrayList<Foto>();
		List<Caratteristiche> caratteristicheLs = new ArrayList<Caratteristiche>();
		caratteristicheLs = caratteristicheDao.getCaratteristicheListPerArticoliSimili(fotoPerArticoloLs);
		if(caratteristicheLs != null){
			esito = "caratteristica " + caratteristicheLs;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la caratteristica per le foto!" + " (" + fotoPerArticoloLs + ")";
			showGrowlErrorMessage();
		}
		return caratteristicheLs;
	}
	
	public Caratteristiche getCaratteristicaDaAggiungere(){
		return caratteristicaDaAggiungere;
	}
	
	public void setCaratteristicaDaAggiungere(Caratteristiche caratteristicaDaAggiungere){
		this.caratteristicaDaAggiungere = caratteristicaDaAggiungere;
	}
	
	public Caratteristiche getCaratteristicaDaEliminare(){
		return caratteristicaDaEliminare;
	}
	
	public void setCaratteristicaDaEliminare(Caratteristiche caratteristicaDaEliminare){
		this.caratteristicaDaEliminare = caratteristicaDaEliminare;
	}
	
	public void aggiungiCaratteristicaAlloArticolo(Boolean reset){
		if(reset){
			if(caratteristicaDaAggiungere != null){
				caratteristicaDaAggiungere.setScala(1);
				caratteristicaDaAggiungere.setQty(new BigDecimal(1));
				caratteristicaDaAggiungere.setDescrizione("");
				caratteristicaDaAggiungere.setValore("");
			}
		}
		aggiungiCaratteristicaAlloArticolo();
	}
	
	public void aggiungiCaratteristicaAlloArticolo(){
		if(caratteristicaDaAggiungere != null){
			caratteristicaDaAggiungere.setId(0);
			caratteristicaDaAggiungere.setArticolo(0);
			caratteristicheArticoloSelLs.add(caratteristicaDaAggiungere);
		}
		caratteristicaDaAggiungere = null;
		caratteristicaSel = null;
		log.info("tmpDEBUGtmp: " + "< " + "aggiungiCaratteristicaAlloArticolo");
	}
	
	public void rimuoviCaratteristicaAlloArticolo(){
		if(caratteristicaDaEliminare != null){
			caratteristicheArticoloSelLs.remove(caratteristicaDaEliminare);
		}
		log.info("tmpDEBUGtmp: " + "< " + "rimuoviCaratteristicaAlloArticolo");
	}
	
	public List<Caratteristiche> getCaratteristicheArticoloSelLs(){
		// String tag = foto.getTag();
		// if(tag == null){
		// tag = "";
		// }
		// String tagAr[] = StringUtils.split(tag, ";");
		// List<Caratteristiche> caratteristicheArticoloSelLs = null;//Arrays.asList(tagAr);
		return caratteristicheArticoloSelLs;
	}
	
	public void setCaratteristicheArticoloSelLs(List<Caratteristiche> caratteristicheArticoloSelLs){
		this.caratteristicheArticoloSelLs = caratteristicheArticoloSelLs;
	}
	
	public void onRowEdit(RowEditEvent event){
		showGrowlInfoMessage("Caratteristica Edited" + ((Caratteristiche)event.getObject()).getId());
	}
	
	public void onRowCancel(RowEditEvent event){
		showGrowlInfoMessage("Caratteristica Cancelled" + ((Caratteristiche)event.getObject()).getId());
	}
}

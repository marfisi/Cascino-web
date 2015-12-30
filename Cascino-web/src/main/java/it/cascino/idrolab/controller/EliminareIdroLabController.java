package it.cascino.idrolab.controller;

import java.util.List;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;
import it.cascino.idrolab.model.IdroLab;
import it.cascino.idrolab.dao.EliminareIdroLabDao;

@Named
@SessionScoped
public class EliminareIdroLabController implements Serializable{
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
	private EliminareIdroLabDao idroLabDao;
	
	private String esito;
	
	
	private List<IdroLab> filteredIdroLabLs;
	
	private IdroLab idroLabSel = new IdroLab();
	private IdroLab idroLabNew = new IdroLab();
	
	public IdroLab getIdroLabSel(){
		// log.info("tmpDEBUGtmp: " + "> " + "getProduttoreSel(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getProduttoreSel");
		// if(produttoreSel == null){
		// Produttori p = new Produttori();
		// p.setId(1);
		// produttoreSel = p;
		// }
		return idroLabSel;
	}
	
	public void setIdroLabSel(IdroLab idroLabSel){
		// log.info("tmpDEBUGtmp: " + "> " + "setProduttoreSel(" + produttoreSel + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
		if(idroLabSel != null){
			this.idroLabSel = idroLabSel;
		}
		// log.info("tmpDEBUGtmp: " + "< " + "setProduttoreSel");
	}
	
//	public Produttori getProduttoreNew(){
//		return idroLabNew;
//	}
//	
//	public void setProduttoreNew(Produttori produttoreNew){
//		if(produttoreNew != null){
//			this.idroLabNew = produttoreNew;
//		}		
//	}
	
//	// chiamata quando faccio nuovo, per non avere i campi sporchi da una selezione che deriva dalla tabella
//	public void resetOnNewProduttoreSel(){
////		log.info("tmpDEBUGtmp: " + "> " + "resetOnNew");
//		Produttori p = new Produttori();
//		p.setId(1);
//		idroLabSel = p;
//	}
	
	public List<IdroLab> getFilteredProduttoriLs(){
		// log.info("tmpDEBUGtmp: " + "> " + "getFilteredProduttoriLs(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getFilteredProduttoriLs");
		return filteredIdroLabLs;
	}
	
	public void setFilteredProduttoriLs(List<IdroLab> filteredIdroLabLs){
		// log.info("tmpDEBUGtmp: " + "> " + "setFilteredProduttoriLs(" + filteredProduttoriLs + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
		this.filteredIdroLabLs = filteredIdroLabLs;
		// log.info("tmpDEBUGtmp: " + "< " + "setFilteredProduttoriLs");
	}
	
	public void salva(){
		// log.info("tmpDEBUGtmp: " + "> " + "salva(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
		idroLabDao.salva(idroLabNew);
		if(idroLabNew != null){
			esito = "Aggiunto produttore: " + idroLabNew.toString();//.getNome();
			showGrowlInsMessage();
		}else{
//			esito = "non e' stato caricato il produttore!" + " (produttore: " + ((idroLabNew != null) ? idroLabNew.getId() : "null") + ")";
			showGrowlErrorMessage();
		}
		// aggiorno la lista condivisa
//		produttoriCondivisiController.aggiornaProduttoriLs();
		idroLabNew = new IdroLab();
		// log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void aggiorna(){
		// log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
		idroLabDao.aggiorna(idroLabSel);
		if(idroLabSel != null){
			esito = "Aggiornato produttore: " + idroLabSel.toString();//.getNome();
			showGrowlUpdMessage();
		}else{
//			esito = "non e' stato aggiornato il produttore!" + " (produttore: " + ((idroLabSel != null) ? idroLabSel.getId() : "null") + ")";
			showGrowlErrorMessage();
		}
		// aggiorno la lista condivisa
//		produttoriCondivisiController.aggiornaProduttoriLs();
		// log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	public void elimina(){
		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
		idroLabDao.elimina(idroLabSel);
		if(idroLabSel != null){
			esito = "Elimino produttore: " + idroLabSel.toString();//.getNome();
			showGrowlDelMessage();
		}else{
//			esito = "non ho trovato il produttore!" + " (produttore: " + ((idroLabSel != null) ? idroLabSel.getId() : "null") + ")";
			showGrowlErrorMessage();
		}
		// aggiorno la lista condivisa
//		produttoriCondivisiController.aggiornaProduttoriLs();
		// log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	public String getEsito(){
		// log.info("tmpDEBUGtmp: " + "> " + "getEsito(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getEsito");
		return esito;
	}
	
	@SuppressWarnings("unused")
	private void showGrowlInfoMessage(String message){
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlUpdMessage(){
		String message = "Aggiornato con successo - " + esito + " >" + idroLabSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlInsMessage(){
		String message = "Inserito con successo - " + esito + " >" + idroLabNew + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlDelMessage(){
		String message = "Eliminato con successo - " + esito + " >" + idroLabSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlErrorMessage(){
		String message = "Operazione fallita - " + esito + " >" + idroLabSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", message));
		log.error(message);
	}
	
//	public String getNomeProduttoreDaIdArticolo(Integer idArticolo){
//		// log.info("tmpDEBUGtmp: " + "> " + "getNomeProduttoreDaIdArticolo(" + idArticolo + ")");
//		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
//		String nomeProduttore = "";
//		nomeProduttore = produttoriDao.getNomeProduttoreDaIdArticolo(idArticolo);
////		if(nomeProduttore != null){
////			esito = "produttore " + nomeProduttore;
////			showGrowlInfoMessage(esito);
////		}else{
////			esito = "non e' stato trovato il produttore!" + " (articolo: " + idArticolo + ")";
////			showGrowlErrorMessage();
////		}
//		// log.info("tmpDEBUGtmp: " + "< " + "getNomeProduttoreDaIdArticolo");
//		return nomeProduttore;
//	}
//	
//	public Produttori getProduttoreDaIdProduttore(Integer idProduttore){
//		// log.info("tmpDEBUGtmp: " + "> " + "getProduttoreDaIdProduttore(" + idProduttore + ")");
//		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
//		Produttori produttore = new Produttori();
//		produttore = produttoriDao.getProduttoreDaIdProduttore(idProduttore);
//		if(produttore != null){
//			esito = "produttore " + produttore;
//			showGrowlInfoMessage(esito);
//		}else{
//			esito = "non e' stato trovato il produttore!" + " (id: " + idProduttore + ")";
//			showGrowlErrorMessage();
//		}
//		// log.info("tmpDEBUGtmp: " + "< " + "getProduttoreDaIdProduttore");
//		return produttore;
//	}
	
	public it.cascino.idrolab.model.ws1_19.Articolo getArticoloPerCodArtWS1(String siglaMarca, String codiceArticolo){
		log.info("aaaaaaaaa: ");
		it.cascino.idrolab.model.ws1_19.Articolo art = null;
		art = idroLabDao.getArticoloPerCodArtWS1(siglaMarca, codiceArticolo);
		return art;
	}
}
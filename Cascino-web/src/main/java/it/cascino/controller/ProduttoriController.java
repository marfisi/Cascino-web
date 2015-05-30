package it.cascino.controller;

import it.cascino.dao.ProduttoriDao;
// import it.cascino.model.Articoli;
import it.cascino.model.Produttori;
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
public class ProduttoriController implements Serializable{
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
	private ProduttoriDao produttoriDao;
	
	private String esito;
	
	@Inject
	private ProduttoriCondivisiController produttoriCondivisiController;
	
	private List<Produttori> filteredProduttoriLs;
	
	private Produttori produttoreSel = new Produttori();
	private Produttori produttoreNew = new Produttori();
	
	public Produttori getProduttoreSel(){
		// log.info("tmpDEBUGtmp: " + "> " + "getProduttoreSel(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getProduttoreSel");
		// if(produttoreSel == null){
		// Produttori p = new Produttori();
		// p.setId(1);
		// produttoreSel = p;
		// }
		return produttoreSel;
	}
	
	public void setProduttoreSel(Produttori produttoreSel){
		// log.info("tmpDEBUGtmp: " + "> " + "setProduttoreSel(" + produttoreSel + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
		if(produttoreSel != null){
			this.produttoreSel = produttoreSel;
		}
		// log.info("tmpDEBUGtmp: " + "< " + "setProduttoreSel");
	}
	
	public Produttori getProduttoreNew(){
		return produttoreNew;
	}
	
	public void setProduttoreNew(Produttori produttoreNew){
		this.produttoreNew = produttoreNew;
	}
	
	// chiamata quando faccio nuovo, per non avere i campi sporchi da una selezione che deriva dalla tabella
	public void resetOnNewProduttoreSel(){
//		log.info("tmpDEBUGtmp: " + "> " + "resetOnNew");
		Produttori p = new Produttori();
		p.setId(1);
		produttoreSel = p;
	}
	
	public List<Produttori> getFilteredProduttoriLs(){
		// log.info("tmpDEBUGtmp: " + "> " + "getFilteredProduttoriLs(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getFilteredProduttoriLs");
		return filteredProduttoriLs;
	}
	
	public void setFilteredProduttoriLs(List<Produttori> filteredProduttoriLs){
		// log.info("tmpDEBUGtmp: " + "> " + "setFilteredProduttoriLs(" + filteredProduttoriLs + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
		this.filteredProduttoriLs = filteredProduttoriLs;
		// log.info("tmpDEBUGtmp: " + "< " + "setFilteredProduttoriLs");
	}
	
	public void salva(){
		// log.info("tmpDEBUGtmp: " + "> " + "salva(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
		produttoriDao.salva(produttoreNew);
		if(produttoreNew != null){
			esito = "Aggiunto produttore: " + produttoreNew.getNome();
			showGrowlInsMessage();
		}else{
			esito = "non e' stato caricato il produttore!" + " (produttore: " + ((produttoreNew != null) ? produttoreNew.getId() : "null") + ")";
			showGrowlErrorMessage();
		}
		// aggiorno la lista condivisa
		produttoriCondivisiController.aggiornaProduttoriLs();
		produttoreNew = new Produttori();
		// log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void aggiorna(){
		// log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
		produttoriDao.aggiorna(produttoreSel);
		if(produttoreSel != null){
			esito = "Aggiornato produttore: " + produttoreSel.getNome();
			showGrowlUpdMessage();
		}else{
			esito = "non e' stato aggiornato il produttore!" + " (produttore: " + ((produttoreSel != null) ? produttoreSel.getId() : "null") + ")";
			showGrowlErrorMessage();
		}
		// aggiorno la lista condivisa
		produttoriCondivisiController.aggiornaProduttoriLs();
		// log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	public void elimina(){
		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
		produttoriDao.elimina(produttoreSel);
		if(produttoreSel != null){
			esito = "Elimino produttore: " + produttoreSel.getNome();
			showGrowlDelMessage();
		}else{
			esito = "non ho trovato il produttore!" + " (produttore: " + ((produttoreSel != null) ? produttoreSel.getId() : "null") + ")";
			showGrowlErrorMessage();
		}
		// aggiorno la lista condivisa
		produttoriCondivisiController.aggiornaProduttoriLs();
		// log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	public String getEsito(){
		// log.info("tmpDEBUGtmp: " + "> " + "getEsito(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getEsito");
		return esito;
	}
	
	private void showGrowlInfoMessage(String message){
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlUpdMessage(){
		String message = "Aggiornato con successo - " + esito + " >" + produttoreSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlInsMessage(){
		String message = "Inserito con successo - " + esito + " >" + produttoreNew + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlDelMessage(){
		String message = "Eliminato con successo - " + esito + " >" + produttoreSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlErrorMessage(){
		String message = "Operazione fallita - " + esito + " >" + produttoreSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", message));
		log.error(message);
	}
	
	public String getNomeProduttoreDaIdArticolo(Integer idArticolo){
		// log.info("tmpDEBUGtmp: " + "> " + "getNomeProduttoreDaIdArticolo(" + idArticolo + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
		String nomeProduttore = "";
		nomeProduttore = produttoriDao.getNomeProduttoreDaIdArticolo(idArticolo);
		if(nomeProduttore != null){
			esito = "produttore " + nomeProduttore;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stato trovato il produttore!" + " (articolo: " + idArticolo + ")";
			showGrowlErrorMessage();
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getNomeProduttoreDaIdArticolo");
		return nomeProduttore;
	}
	
	public Produttori getProduttoreDaIdProduttore(Integer idProduttore){
		// log.info("tmpDEBUGtmp: " + "> " + "getProduttoreDaIdProduttore(" + idProduttore + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((produttoreSel != null) ? produttoreSel.getId() : "null"));
		Produttori produttore = new Produttori();
		produttore = produttoriDao.getProduttoreDaIdProduttore(idProduttore);
		if(produttore != null){
			esito = "produttore " + produttore;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stato trovato il produttore!" + " (id: " + idProduttore + ")";
			showGrowlErrorMessage();
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getProduttoreDaIdProduttore");
		return produttore;
	}
}

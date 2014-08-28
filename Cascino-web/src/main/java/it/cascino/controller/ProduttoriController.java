package it.cascino.controller;

import it.cascino.dao.ProduttoriDao;
import it.cascino.model.Foto;
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
	
	private List<Produttori> produttoriLs;
	private List<Produttori> filteredProduttoriLs;
	
	private Produttori produttoreSel = new Produttori();
	
	public List<Produttori> getProduttoriLs(){
		log.info("tmpDEBUGtmp: " + "> " + "getProduttoriLs(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + produttoreSel.getId());
		produttoriLs = produttoriDao.getAll();
		log.info("tmpDEBUGtmp: " + "< " + "getProduttoriLs");
		return produttoriLs;
	}
	
	public void setProduttoriLs(List<Produttori> produttoriLs){
		log.info("tmpDEBUGtmp: " + "> " + "setProduttoriLs(" + produttoriLs + ")");
		log.info("tmpDEBUGtmp: " + "id: " + produttoreSel.getId());
		this.produttoriLs = produttoriLs;
		log.info("tmpDEBUGtmp: " + "< " + "setProduttoriLs");
	}
	
	public Produttori getProduttoreSel(){
		log.info("tmpDEBUGtmp: " + "> " + "getProduttoreSel(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + produttoreSel.getId());
		log.info("tmpDEBUGtmp: " + "< " + "getProduttoreSel");
		return produttoreSel;
	}
	
	public void setProduttoreSel(Produttori produttoreSel){
		log.info("tmpDEBUGtmp: " + "> " + "setProduttoreSel(" + produttoreSel + ")");
		log.info("tmpDEBUGtmp: " + "id: " + produttoreSel.getId());
		this.produttoreSel = produttoreSel;
		log.info("tmpDEBUGtmp: " + "< " + "setProduttoreSel");
	}
	
	public List<Produttori> getFilteredProduttoriLs(){
		log.info("tmpDEBUGtmp: " + "> " + "getFilteredProduttoriLs(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + produttoreSel.getId());
		log.info("tmpDEBUGtmp: " + "< " + "getFilteredProduttoriLs");
		return filteredProduttoriLs;
	}
	
	public void setFilteredProduttoriLs(List<Produttori> filteredProduttoriLs){
		log.info("tmpDEBUGtmp: " + "> " + "setFilteredProduttoriLs(" + filteredProduttoriLs + ")");
		log.info("tmpDEBUGtmp: " + "id: " + produttoreSel.getId());
		this.filteredProduttoriLs = filteredProduttoriLs;
		log.info("tmpDEBUGtmp: " + "< " + "setFilteredProduttoriLs");
	}
	
	public void salva(){
		log.info("tmpDEBUGtmp: " + "> " + "salva(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + produttoreSel.getId());
		produttoriDao.salva(produttoreSel);
		if(produttoreSel != null){
			esito = "Aggiunto produttore: " + produttoreSel.getNome();
			showGrowlInsMessage();
		}else{
			esito = "non e' stato caricato il produttore!" + " (produttore: " + produttoreSel.getId() + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void aggiorna(){
		log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + produttoreSel.getId());
		produttoriDao.aggiorna(produttoreSel);
		if(produttoreSel != null){
			esito = "Aggiornato produttore: " + produttoreSel.getNome();
			showGrowlUpdMessage();
		}else{
			esito = "non e' stato aggiornato il produttore!" + " (produttore: " + produttoreSel.getId() + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	public void elimina(){
		log.info("tmpDEBUGtmp: " + "> " + "elimina(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + produttoreSel.getId());
		produttoriDao.elimina(produttoreSel);
		if(produttoreSel != null){
			esito = "Elimino produttore: " + produttoreSel.getNome();
			showGrowlDelMessage();
		}else{
			esito = "non ho trovato il produttore!" + " (produttore: " + produttoreSel.getId() + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	public String getEsito(){
		log.info("tmpDEBUGtmp: " + "> " + "getEsito(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + produttoreSel.getId());
		log.info("tmpDEBUGtmp: " + "< " + "getEsito");
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
		String message = "Inserito con successo - " + esito + " >" + produttoreSel + "<";
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
	
	public int sortByNum(Object obj1, Object obj2){
		log.info("tmpDEBUGtmp: " + "> " + "sortByNum(" + obj1 + ", " + obj2 + ")");
		log.info("tmpDEBUGtmp: " + "id: " + produttoreSel.getId());
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
		log.info("tmpDEBUGtmp: " + "id: " + produttoreSel.getId());
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
		log.info("tmpDEBUGtmp: " + "id: " + produttoreSel.getId());
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
	
	
	public Foto getFotoProduttoreDaIdProduttore(Integer idProduttore){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoProduttoreDaIdProduttore(" + idProduttore + ")");
		log.info("tmpDEBUGtmp: " + "id: " + produttoreSel.getId());
		Foto fotoProduttore = new Foto();
		fotoProduttore = produttoriDao.getFotoProduttoreDaIdProduttore(idProduttore);
		if(fotoProduttore != null){
			esito = "selezionata foto " + fotoProduttore.getId() + " per produttore: " + idProduttore;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto!" + " (produttore: " + idProduttore + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoProduttoreDaIdProduttore");
		return fotoProduttore;
	}
	
	public Foto getFotoProduttoreDaIdArticolo(Integer idArticolo){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoProduttoreDaIdArticolo(" + idArticolo + ")");
		log.info("tmpDEBUGtmp: " + "id: " + produttoreSel.getId());
		Foto fotoProduttore = new Foto();
		fotoProduttore = produttoriDao.getFotoProduttoreDaIdArticolo(idArticolo);
		if(fotoProduttore != null){
			esito = "selezionata foto " + fotoProduttore.getId() + " per articolo: " + idArticolo;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto!" + " (articolo: " + idArticolo + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoProduttoreDaIdArticolo");
		return fotoProduttore;
	}
	
	public String getNomeProduttoreDaIdArticolo(Integer idArticolo){
		log.info("tmpDEBUGtmp: " + "> " + "getNomeProduttoreDaIdArticolo(" + idArticolo + ")");
		log.info("tmpDEBUGtmp: " + "id: " + produttoreSel.getId());
		String nomeProduttore = "";
		nomeProduttore = produttoriDao.getNomeProduttoreDaIdArticolo(idArticolo);
		if(nomeProduttore != null){
			esito = "produttore " + nomeProduttore;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stato trovato il produttore!" + " (articolo: " + idArticolo + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "getNomeProduttoreDaIdArticolo");
		return nomeProduttore;
	}
	
	public Produttori getProduttoreDaIdProduttore(Integer idProduttore){
		log.info("tmpDEBUGtmp: " + "> " + "getProduttoreDaIdProduttore(" + idProduttore + ")");
		log.info("tmpDEBUGtmp: " + "id: " + produttoreSel.getId());
		Produttori produttore = new Produttori();
		produttore = produttoriDao.getProduttoreDaIdProduttore(idProduttore);
		if(produttore != null){
			esito = "produttore " + produttore;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stato trovato il produttore!" + " (id: " + idProduttore + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "getProduttoreDaIdProduttore");
		return produttore;
	}
}

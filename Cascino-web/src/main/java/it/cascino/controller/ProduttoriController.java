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
		produttoriLs = produttoriDao.getAll();
		return produttoriLs;
	}
	
	public void setProduttoriLs(List<Produttori> ProduttoriLs){
		this.produttoriLs = ProduttoriLs;
	}
	
	public Produttori getProduttoreSel(){
		return produttoreSel;
	}
	
	public void setProduttoreSel(Produttori produttoreSel){
		this.produttoreSel = produttoreSel;
	}
	
	public List<Produttori> getFilteredProduttoriLs(){
		return filteredProduttoriLs;
	}
	
	public void setFilteredProduttoriLs(List<Produttori> filteredProduttoriLs){
		this.filteredProduttoriLs = filteredProduttoriLs;
	}
	
	public void salva(){
		produttoriDao.salva(produttoreSel);
		if(produttoreSel != null){
			esito = "Aggiunto produttore: " + produttoreSel.getNome();
			showGrowlInsMessage();
		}else{
			esito = "non e' stato caricato il produttore!";
			showGrowlErrorMessage();
		}
	}
	
	public void aggiorna(){
		produttoriDao.aggiorna(produttoreSel);
		if(produttoreSel != null){
			esito = "Aggiornato produttore: " + produttoreSel.getNome();
			showGrowlUpdMessage();
		}else{
			esito = "non e' stato aggiornato il produttore! ";
			showGrowlErrorMessage();
		}
	}
	
	public void elimina(){
		produttoriDao.elimina(produttoreSel);
		if(produttoreSel != null){
			esito = "Elimino produttore: " + produttoreSel.getNome();
			showGrowlDelMessage();
		}else{
			esito = "non ho trovato il produttore!";
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
	
	public Foto getFotoDaArticolo(Integer idArticolo){
		Foto fotoProduttore = new Foto();
		fotoProduttore = produttoriDao.getFotoDaArticolo(idArticolo);
		if(fotoProduttore != null){
			esito = "selezionata foto " + fotoProduttore.getId() + " per produttore: " + idArticolo;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto!";
			showGrowlErrorMessage();
		}
		return fotoProduttore;
	}
	
	public String getNomeDaArticolo(Integer idArticolo){
		String nomeProduttore = "";
		nomeProduttore = produttoriDao.getNomeDaArticolo(idArticolo);
		if(nomeProduttore != null){
			esito = "produttore " + nomeProduttore;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovato il produttore!";
			showGrowlErrorMessage();
		}
		return nomeProduttore;
	}
	
}

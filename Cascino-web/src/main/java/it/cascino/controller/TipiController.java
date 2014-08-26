package it.cascino.controller;

import it.cascino.dao.TipiDao;
import it.cascino.model.Foto;
import it.cascino.model.Produttori;
import it.cascino.model.Tipi;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named
@SessionScoped
public class TipiController implements Serializable{
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
	private TipiDao tipiDao;
	
	private String esito;
	
	private TreeNode albero;
	
	private TreeNode nodoSel;
	
	private List<Tipi> tipiLs;
	
	public List<Tipi> getTipiLs(){
		tipiLs = tipiDao.getAll();
		return tipiLs;
	}
	
	public void setTipiLs(List<Tipi> tipiLs){
		this.tipiLs = tipiLs;
	}
	
	public TreeNode getAlbero(){
		// if((tipiLs == null) || (tipiLs.isEmpty())){
		tipiLs = tipiDao.getAll();
		
		albero = new DefaultTreeNode("root", null);
		
		popolaConFigli(1, albero);
		// }
		return albero;
	}
	
	public void setAlbero(TreeNode albero){
		this.albero = albero;
	}
	
	public TreeNode getNodoSel(){
		return nodoSel;
	}
	
	public void setNodoSel(TreeNode nodoSel){
		this.nodoSel = nodoSel;
	}
	
	public void salva(Integer idFoto){
		((Tipi)nodoSel.getData()).setIdFoto(idFoto);
		salva();
	}
	
	public void salva(){
		tipiDao.salva(nodoSel);
		if(nodoSel != null){
			esito = "Aggiunto tipo: " + ((Tipi)nodoSel.getData()).getDescrizione();
			showGrowlInsMessage();
		}else{
			esito = "non ho trovato il tipo!" + " (tipo: " + ((Tipi)nodoSel.getData()).getId() + ")";
			showGrowlErrorMessage();
		}
	}
	
	public void aggiorna(){
		((Tipi)nodoSel.getData()).setTipoPadre(getPadreFromId());
		
		tipiDao.aggiorna(nodoSel);
		if(nodoSel != null){
			esito = "Aggiorno tipo: " + ((Tipi)nodoSel.getData()).getDescrizione();
			showGrowlUpdMessage();
		}else{
			esito = "non ho trovato il tipo!" + " (tipo: " + ((Tipi)nodoSel.getData()).getId() + ")";
			showGrowlErrorMessage();
		}
	}
	
	public void elimina(){
		tipiDao.elimina(nodoSel);
		if(nodoSel != null){
			esito = "Elimino tipo: " + ((Tipi)nodoSel.getData()).getDescrizione();
			showGrowlDelMessage();
		}else{
			esito = "non ho trovato il tipo!" + " (tipo: " + ((Tipi)nodoSel.getData()).getId() + ")";
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
		String message = "Aggiornato con successo - " + esito + " >" + nodoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlInsMessage(){
		String message = "Inserito con successo - " + esito + " >" + nodoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlDelMessage(){
		String message = "Eliminato con successo - " + esito + " >" + nodoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlErrorMessage(){
		String message = "Operazione fallita - " + esito + " >" + nodoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", message));
		log.error(message);
	}
	
	public void displaySelectedSingle(){
		if(nodoSel != null){
			String message = "Selezione " + nodoSel.getData().toString();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
			log.info(message);
		}
	}
	
	// private, solo di servizio, ricorsiva
	private TreeNode popolaConFigli(int idPadre, TreeNode root){
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
		return leaf;
	}
	
	// private, solo di servizio
	private Tipi getPadreFromId(){
		// tipoSel.tipoPadre ha solo l'id definito, e non si riesce a fare il merge, quindi configuro il padre con l'intero oggetto (comprendente quindi tutti i parametri come nome descrizione ecc)
		Tipi p = null;
		Iterator<Tipi> iterator = tipiLs.iterator();
		while(iterator.hasNext()){
			p = iterator.next();
			if(p.getId() == ((Tipi)nodoSel.getData()).getTipoPadre().getId()){
				break;
			}
			p = null;
		}
		return p;
	}
	
	public Foto getFoto(Integer id){
		Foto fotoTipo = new Foto();
		fotoTipo = tipiDao.getFoto(id);
		if(fotoTipo != null){
			esito = "selezionata foto " + fotoTipo.getId() + " per tipo: " + id;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto!" + " (tipo: " + id + ")";
			showGrowlErrorMessage();
		}
		return fotoTipo;
	}
	
	public Foto getFotoDaArticolo(Integer idArticolo){
		Foto fotoTipo = new Foto();
		fotoTipo = tipiDao.getFotoDaArticolo(idArticolo);
		if(fotoTipo != null){
			esito = "selezionata foto " + fotoTipo.getId() + " per articolo: " + idArticolo;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto!" + " (articolo: " + idArticolo + ")";
			showGrowlErrorMessage();
		}
		return fotoTipo;
	}
	
	public String getNomeDaArticolo(Integer idArticolo){
		String nomeTipo = "";
		nomeTipo = tipiDao.getNomeDaArticolo(idArticolo);
		if(nomeTipo != null){
			esito = "tipo " + nomeTipo + " per articolo: " + idArticolo;;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stato trovato il tipo!" + " (articolo: " + idArticolo + ")";
			showGrowlErrorMessage();
		}
		return nomeTipo;
	}
	
	public Tipi getTipoDaId(Integer id){
		Tipi tipo = new Tipi();
		tipo = tipiDao.getTipoDaId(id);
		if(tipo != null){
			esito = "tipo " + tipo;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stato trovato il tipo!" + " (id: " + id + ")";
			showGrowlErrorMessage();
		}
		return tipo;
	}
}
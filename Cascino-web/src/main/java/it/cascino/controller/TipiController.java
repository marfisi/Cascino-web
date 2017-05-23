package it.cascino.controller;

import it.cascino.dao.TipiDao;
//import it.cascino.model.Articoli;
import it.cascino.model.Tipi;
import java.io.Serializable;
import java.util.Iterator;
//import java.util.List;
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
	
	@Inject
	private TipiCondivisiController tipiCondivisiController;  

	@Inject
	private FotoController fotoController;  

	private TreeNode nodoSel;
	private TreeNode nodoNew = (TreeNode)new DefaultTreeNode(new Tipi(), null);
	
	public TreeNode getNodoSel(){
		// log.info("tmpDEBUGtmp: " + "> " + "getNodoSel(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
//		if(nodoSel == null){
//			Tipi t = new Tipi();
//			t.setId(1);
//			nodoSel = (TreeNode)new DefaultTreeNode(t, null);
//		}
		// log.info("tmpDEBUGtmp: " + "< " + "getNodoSel");
		return nodoSel;
	}
	
	public void setNodoSel(TreeNode nodoSel){
		// log.info("tmpDEBUGtmp: " + "> " + "setNodoSel(" + nodoSel + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		if(nodoSel != null){
			this.nodoSel = nodoSel;
		}
		// log.info("tmpDEBUGtmp: " + "< " + "setNodoSel");
	}
	
	public TreeNode getNodoNew(){
		return nodoNew;
	}
	
	public void setNodoNew(TreeNode nodoNew){
		if(nodoNew != null){
			this.nodoNew = nodoNew;
		}
	}
	
	// chiamata quando faccio nuovo, per non avere i campi sporchi da una selezione che deriva dalla tabella
	public void resetOnNewNodoSel(){
		Tipi t = new Tipi();
		t.setId(1);
		nodoSel = (TreeNode)new DefaultTreeNode(t, null);
	}
	
//	public void salva(Integer idFoto){
//		// log.info("tmpDEBUGtmp: " + "> " + "salva(" + idFoto + ")");
//		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
//		((Tipi)nodoSel.getData()).setIdFoto(idFoto);
//		salva();
//		// log.info("tmpDEBUGtmp: " + "< " + "salva");
//	}
	
	public void salva(){
		// log.info("tmpDEBUGtmp: " + "> " + "salva(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		
		// svuoto la lista delle foto
		fotoController.svuotaFotoLsDynPop();
		
		tipiDao.salva(nodoNew);
		if(nodoNew != null){
			esito = "Aggiunto tipo: " + ((Tipi)nodoNew.getData()).getDescrizione();
			showGrowlInsMessage();
		}else{
			esito = "non ho trovato il tipo!" + " (tipo: " + ((Tipi)nodoNew.getData()).getId() + ")";
			showGrowlErrorMessage();
		}
		// aggiorno la lista condivisa
		tipiCondivisiController.aggiornaTipiLs();
		nodoNew = (TreeNode)new DefaultTreeNode(new Tipi(), null);
		// log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void aggiorna(){
		// log.info("tmpDEBUGtmp: " + "> " + "aggiorna("  + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		((Tipi)nodoSel.getData()).setTipoPadre(getPadreFromIdPadre());
				
		// svuoto la lista delle foto
		fotoController.svuotaFotoLsDynPop();

		tipiDao.aggiorna(nodoSel);
		if(nodoSel != null){
			esito = "Aggiorno tipo: " + ((Tipi)nodoSel.getData()).getDescrizione();
			showGrowlUpdMessage();
		}else{
			esito = "non ho trovato il tipo!" + " (tipo: " + ((Tipi)nodoSel.getData()).getId() + ")";
			showGrowlErrorMessage();
		}
		// aggiorno la lista condivisa
		tipiCondivisiController.aggiornaTipiLs();
		// log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	public void elimina(){
		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		tipiDao.elimina(nodoSel);
		if(nodoSel != null){
			esito = "Elimino tipo: " + ((Tipi)nodoSel.getData()).getDescrizione();
			showGrowlDelMessage();
		}else{
			esito = "non ho trovato il tipo!" + " (tipo: " + ((Tipi)nodoSel.getData()).getId() + ")";
			showGrowlErrorMessage();
		}
		// aggiorno la lista condivisa
		tipiCondivisiController.aggiornaTipiLs();
		// log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	public String getEsito(){
		// log.info("tmpDEBUGtmp: " + "> " + "getEsito(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getEsito");
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
		String message = "Inserito con successo - " + esito + " >" + nodoNew + "<";
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
		// log.info("tmpDEBUGtmp: " + "> " + "displaySelectedSingle(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		if(nodoSel != null){
			String message = "Selezione " + nodoSel.getData().toString();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
			log.info(message);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "displaySelectedSingle");
	}
	
	// private, solo di servizio
	private Tipi getPadreFromIdPadre(){
		// log.info("tmpDEBUGtmp: " + "> " + "getPadreFromIdPadre(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		// tipoSel.tipoPadre ha solo l'id definito, e non si riesce a fare il merge, quindi configuro il padre con l'intero oggetto (comprendente quindi tutti i parametri come nome descrizione ecc)
		Tipi p = null;
		Iterator<Tipi> iterator = tipiCondivisiController.getTipiLs().iterator();
		while(iterator.hasNext()){
			p = iterator.next();
			if(p.getId().equals(((Tipi)nodoSel.getData()).getTipoPadre().getId())){
				break;
			}
			p = null;
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getPadreFromIdPadre");
		return p;
	}
		
	public String getNomeTipoDaIdArticolo(Integer idArticolo){
		// log.info("tmpDEBUGtmp: " + "> " + "getNomeTipoDaIdArticolo(" + idArticolo + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		String nomeTipo = "";
		nomeTipo = tipiDao.getNomeTipoDaIdArticolo(idArticolo);
		/*if(nomeTipo != null){
			esito = "tipo " + nomeTipo + " per articolo: " + idArticolo;;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stato trovato il tipo!" + " (articolo: " + idArticolo + ")";
			showGrowlErrorMessage();
		}*/
		// log.info("tmpDEBUGtmp: " + "< " + "getNomeTipoDaIdArticolo");
		return nomeTipo;
	}
	
	public Tipi getTipoDaIdTipo(Integer idTipo){
		// log.info("tmpDEBUGtmp: " + "> " + "getTipoDaIdTipo(" + idTipo + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		Tipi tipo = new Tipi();
		tipo = tipiDao.getTipoDaIdTipo(idTipo);
		if(tipo != null){
			esito = "tipo " + tipo;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stato trovato il tipo!" + " (id: " + idTipo + ")";
			showGrowlErrorMessage();
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getTipoDaIdTipo");
		return tipo;
	}
	
	// serve a qualcuno? c'è dentro la chiamata a getIdTipoDaLikeNomeTipo che ora torna una list non più un solo id
//	public Integer getIdTipoDaLikeNomeTipo(String nomeTipo){
//		Integer idTipo = 0;
//		idTipo = tipiDao.getIdTipoDaLikeNomeTipo(nomeTipo);
//		if(idTipo != null){
//			esito = "tipo " + idTipo;
//			showGrowlInfoMessage(esito);
//		}else{
//			esito = "non e' stato trovato il tipo!" + " (nome: " + nomeTipo + ")";
//			showGrowlErrorMessage();
//		}
//		// log.info("tmpDEBUGtmp: " + "< " + "getTipoDaIdTipo");
//		return idTipo;		
//	}
}
package it.cascino.controller;

import it.cascino.dao.TipiDao;
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
		log.info("tmpDEBUGtmp: " + "> " + "getTipiLs("  + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		tipiLs = tipiDao.getAll();
		log.info("tmpDEBUGtmp: " + "< " + "getTipiLs");
		return tipiLs;
	}
	
	public void setTipiLs(List<Tipi> tipiLs){
		log.info("tmpDEBUGtmp: " + "> " + "setTipiLs(" + tipiLs + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		this.tipiLs = tipiLs;
		log.info("tmpDEBUGtmp: " + "< " + "setTipiLs");
	}
	
	public TreeNode getAlbero(){
		log.info("tmpDEBUGtmp: " + "> " + "getAlbero(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		// if((tipiLs == null) || (tipiLs.isEmpty())){
		tipiLs = tipiDao.getAll();
		
		albero = new DefaultTreeNode("root", null);
		
		popolaConFigli(1, albero);
		// }
		log.info("tmpDEBUGtmp: " + "< " + "getAlbero");
		return albero;
	}
	
	public void setAlbero(TreeNode albero){
		log.info("tmpDEBUGtmp: " + "> " + "setAlbero(" + albero + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		this.albero = albero;
		log.info("tmpDEBUGtmp: " + "< " + "setAlbero");
	}
	
	public TreeNode getNodoSel(){
		log.info("tmpDEBUGtmp: " + "> " + "getNodoSel(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		log.info("tmpDEBUGtmp: " + "< " + "getNodoSel");
		return nodoSel;
	}
	
	public void setNodoSel(TreeNode nodoSel){
		log.info("tmpDEBUGtmp: " + "> " + "setNodoSel(" + nodoSel + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		this.nodoSel = nodoSel;
		log.info("tmpDEBUGtmp: " + "< " + "setNodoSel");
	}
	
	public void salva(Integer idFoto){
		log.info("tmpDEBUGtmp: " + "> " + "salva(" + idFoto + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		((Tipi)nodoSel.getData()).setIdFoto(idFoto);
		salva();
		log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void salva(){
		log.info("tmpDEBUGtmp: " + "> " + "salva(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		tipiDao.salva(nodoSel);
		if(nodoSel != null){
			esito = "Aggiunto tipo: " + ((Tipi)nodoSel.getData()).getDescrizione();
			showGrowlInsMessage();
		}else{
			esito = "non ho trovato il tipo!" + " (tipo: " + ((Tipi)nodoSel.getData()).getId() + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void aggiorna(){
		log.info("tmpDEBUGtmp: " + "> " + "aggiorna("  + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		((Tipi)nodoSel.getData()).setTipoPadre(getPadreFromId());
		
		tipiDao.aggiorna(nodoSel);
		if(nodoSel != null){
			esito = "Aggiorno tipo: " + ((Tipi)nodoSel.getData()).getDescrizione();
			showGrowlUpdMessage();
		}else{
			esito = "non ho trovato il tipo!" + " (tipo: " + ((Tipi)nodoSel.getData()).getId() + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	public void elimina(){
		log.info("tmpDEBUGtmp: " + "> " + "elimina(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		tipiDao.elimina(nodoSel);
		if(nodoSel != null){
			esito = "Elimino tipo: " + ((Tipi)nodoSel.getData()).getDescrizione();
			showGrowlDelMessage();
		}else{
			esito = "non ho trovato il tipo!" + " (tipo: " + ((Tipi)nodoSel.getData()).getId() + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	public String getEsito(){
		log.info("tmpDEBUGtmp: " + "> " + "getEsito(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		log.info("tmpDEBUGtmp: " + "< " + "getEsito");
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
		log.info("tmpDEBUGtmp: " + "> " + "displaySelectedSingle(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		if(nodoSel != null){
			String message = "Selezione " + nodoSel.getData().toString();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
			log.info(message);
		}
		log.info("tmpDEBUGtmp: " + "< " + "displaySelectedSingle");
	}
	
	// private, solo di servizio, ricorsiva
	private TreeNode popolaConFigli(int idPadre, TreeNode root){
		log.info("tmpDEBUGtmp: " + "> " + "popolaConFiglli(" + idPadre + ", " + root + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
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
		log.info("tmpDEBUGtmp: " + "< " + "popolaConFigli");
		return leaf;
	}
	
	// private, solo di servizio
	private Tipi getPadreFromId(){
		log.info("tmpDEBUGtmp: " + "> " + "getPadreFromId(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
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
		log.info("tmpDEBUGtmp: " + "< " + "getPadreFromId");
		return p;
	}
		
	public String getNomeTipoDaIdArticolo(Integer idArticolo){
		log.info("tmpDEBUGtmp: " + "> " + "getNomeTipoDaIdArticolo(" + idArticolo + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		String nomeTipo = "";
		nomeTipo = tipiDao.getNomeTipoDaIdArticolo(idArticolo);
		if(nomeTipo != null){
			esito = "tipo " + nomeTipo + " per articolo: " + idArticolo;;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stato trovato il tipo!" + " (articolo: " + idArticolo + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "getNomeTipoDaIdArticolo");
		return nomeTipo;
	}
	
	public Tipi getTipoDaIdTipo(Integer idTipo){
		log.info("tmpDEBUGtmp: " + "> " + "getTipoDaIdTipo(" + idTipo + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
		Tipi tipo = new Tipi();
		tipo = tipiDao.getTipoDaIdTipo(idTipo);
		if(tipo != null){
			esito = "tipo " + tipo;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stato trovato il tipo!" + " (id: " + idTipo + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "getTipoDaIdTipo");
		return tipo;
	}
	
	public int sortByNum(Object obj1, Object obj2){
		log.info("tmpDEBUGtmp: " + "> " + "sortByNum(" + obj1 + ", " + obj2 + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
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
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
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
		log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((Tipi)nodoSel.getData()).getId() : "null"));
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

}
package it.cascino.controller;

import java.util.Iterator;
import java.util.List;
import it.cascino.dao.TipiDao;
import it.cascino.model.Tipi;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;

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
	
	private List<Tipi> tipiLs;
	private List<Tipi> filteredTipiLs;
	
	private Tipi tipoSel = new Tipi();

	public List<Tipi> getTipiLs(){
		tipiLs = tipiDao.getAll();
		return tipiLs;
	}

	public void setTipiLs(List<Tipi> tipiLs){
		this.tipiLs = tipiLs;
	}
	
	public Tipi getTipoSel(){
		return tipoSel;
	}

	public void setTipoSel(Tipi tipoSel){
		this.tipoSel = tipoSel;
	}
	
	public List<Tipi> getFilteredTipiLs(){
		return filteredTipiLs;
	}

	public void setFilteredTipiLs(List<Tipi> filteredTipiLs){
		this.filteredTipiLs = filteredTipiLs;
	}
	
//	@Named
//    @Produces
//   // @RequestScoped
//    private Tipi tipo = new Tipi();
	
	public void salva(){
		tipiDao.salva(tipoSel);
		if(tipoSel != null){
			esito = "Aggiunto tipo: " + tipoSel.getDescrizione();
			showGrowlInsMessage();
		}else{
			esito = "non ho trovato il tipo!";
			showGrowlErrorMessage();
		}
	}
	
	public void aggiorna(){
		tipoSel.setTipoPadre(getPadreFromId());
		
		tipiDao.aggiorna(tipoSel);
		if(tipoSel != null){
			esito = "Aggiorno tipo: " + tipoSel.getDescrizione();
			showGrowlUpdMessage();
		}else{
			esito = "non ho trovato il tipo!";
			showGrowlErrorMessage();
		}
	}

	public void elimina(){
		tipiDao.elimina(tipoSel);
		if(tipoSel != null){
			esito = "Elimino tipo: " + tipoSel.getDescrizione();
			showGrowlDelMessage();
		}else{
			esito = "non ho trovato il tipo!";
			showGrowlErrorMessage();
		}
	}
	
	public String getEsito(){
		return esito;
	}
	
	private void showGrowlUpdMessage(){
		String message = "Aggiornato con successo - " + esito + " >" + tipoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlInsMessage(){
		String message = "Inserito con successo - " + esito + " >" + tipoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlDelMessage(){
		String message = "Eliminato con successo - " + esito + " >" + tipoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlErrorMessage(){
		String message = "Operazione fallita - " + esito + " >" + tipoSel + "<";
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
	
	// private, solo di servizio
	private Tipi getPadreFromId(){
		// tipoSel.tipoPadre ha solo l'id definito, e non si riesce a fare il merge, quindi configuro il padre con l'intero oggetto (comprendente quindi tutti i parametri come nome descrizione ecc)
		Tipi p = null;
		Iterator<Tipi> iterator = tipiLs.iterator();
		while(iterator.hasNext()){
			p = iterator.next();
			if(p.getId() == tipoSel.getTipoPadre().getId()){
				break;
			}
			p = null;
		}
		return p;
	}
}

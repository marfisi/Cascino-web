package it.cascino.controller;

//import java.util.Iterator;
import java.util.Iterator;
import java.util.List;
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.context.RequestScoped;
import it.cascino.dao.TipiDao;
import it.cascino.model.Tipi;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
//import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
//import org.jboss.logging.Logger;
import java.util.logging.Logger;

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
//	@Inject
	private static Logger log = Logger.getLogger(TipiController.class.getName());
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private TipiDao tipiDao;
	
	private String esito;
	
	private List<Tipi> tipiLs;
	private List<Tipi> filteredTipiLs;
	
	
	private Tipi tipoSel = new Tipi();
//	private Tipi tipoSel2 = new Tipi();
//
//	public Tipi getTipoSel2(){
//		return tipoSel2;
//	}
//
//	public void setTipoSel2(Tipi tipoSel2){
//		this.tipoSel2 = tipoSel2;
//	}

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
//		tipoSel.setTipoPadre(getPadreFromId());

		tipiDao.salva(tipoSel);
		if(tipoSel != null){
			esito = "Aggiunto tipo: " + tipoSel.getDescrizione();
		}else{
			esito = "non ho trovato il tipo!";
			showGrowlErrorMessage();
		}
		showGrowlInsMessage();
	}
	
	public void aggiorna(){
		tipoSel.setTipoPadre(getPadreFromId());
		
		tipiDao.aggiorna(tipoSel);
		if(tipoSel != null){
			esito = "Aggiorno tipo: " + tipoSel.getDescrizione();
		}else{
			esito = "non ho trovato il tipo!";
			showGrowlErrorMessage();
		}
		showGrowlUpdMessage();
	}

	public void elimina(){
		tipiDao.elimina(tipoSel);
		if(tipoSel != null){
			esito = "Elimino tipo: " + tipoSel.getDescrizione();
		}else{
			esito = "non ho trovato il tipo!";
			showGrowlErrorMessage();
		}
		showGrowlDelMessage();
	}
	
	public String getEsito(){
		return esito;
	}
	
	private void showGrowlUpdMessage(){
		String message = esito + " >" + tipoSel + "<";
		facesContext.addMessage(null, new FacesMessage("Successful", "Contatto aggiornato con successo" + message));
	}
	
	private void showGrowlInsMessage(){
		String message = esito + " >" + tipoSel + "<";
		facesContext.addMessage(null, new FacesMessage("Successful", "Contatto inserito con successo" + message));
	}
	
	private void showGrowlDelMessage(){
		String message = esito + " >" + tipoSel + "<";
		facesContext.addMessage(null, new FacesMessage("Successful", "Contatto eliminato con successo" + message));
	}
	
	private void showGrowlErrorMessage(){
		String message = esito + " >" + tipoSel + "<";
		facesContext.addMessage(null, new FacesMessage("Errore", "Operazione fallita" + message));
	}
	
	public int sortById(Object o1, Object o2){
		Integer t1 = (Integer)o1;
		Integer t2 = (Integer)o2;
//		log.info("sortById: " + t1 + "-" + t2);
		if(t1 < t2){
			return -1;
		}else if(t1 > t2){
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

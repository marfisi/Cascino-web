package it.cascino.controller;

import it.cascino.h8.entity.Tipi;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;

@ManagedBean(name = "tipiBean")
public class TipiBean{
	/**
	 * Logger
	 */
	private static Logger log = Logger.getLogger(TipiBean.class.getName());
		
	private List<Tipi> tipiLs;
	
	private Tipi tipoSel = new Tipi();

	@PersistenceUnit(unitName = "perEjb")
	private EntityManagerFactory emf;
	
	@Resource
	private UserTransaction utx;
	
	public List<Tipi> getTipiLs(){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT t FROM Tipi t");
		log.debug(query.toString());
		tipiLs = (List<Tipi>)query.getResultList();
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
		
	public void salva(ActionEvent event){
		EntityManager em = emf.createEntityManager();
		try{
			utx.begin();
			em.joinTransaction();
			log.info("salva: " + tipoSel.getId() + " " + tipoSel.getNome() + " " + tipoSel.getDescrizione() + " " + tipoSel.getTipoPadre());
			em.persist(tipoSel);
			utx.commit();
			showGrowlInsMessage();
		}catch(Exception e){
			try{
				utx.rollback();
			}catch(IllegalStateException e1){
				showGrowlErrorMessage();
				e1.printStackTrace();
			}catch(SecurityException e1){
				showGrowlErrorMessage();
				e1.printStackTrace();
			}catch(SystemException e1){
				showGrowlErrorMessage();
				e1.printStackTrace();
			}
		}finally{
			em.close();
		}
		tipoSel = null;
	}
	
	public void aggiorna(ActionEvent event){
		EntityManager em = emf.createEntityManager();
		try{
			utx.begin();
			em.joinTransaction();
			log.info("aggiorna: " + tipoSel.getId() + " " + tipoSel.getNome() + " " + tipoSel.getDescrizione() + " " + tipoSel.getTipoPadre());
			// tipoSel.tipoPadre ha solo l'id definito, e non si riesce a fare il merge, quindi configuro il padre con l'intero oggetto (comprendente quindi tutti i parametri come nome descrizione ecc)
			Tipi p = null;
			Iterator<Tipi> iterator = tipiLs.iterator();
			while (iterator.hasNext()){
				p = iterator.next();
				if(p.getId() == tipoSel.getTipoPadre().getId()){
					break;
				}
				p = null;
			}
			tipoSel.setTipoPadre(p);
			em.merge(tipoSel);
			utx.commit();
			showGrowlUpdMessage();
		}catch(Exception e){
			try{
				utx.rollback();
			}catch(IllegalStateException e1){
				showGrowlErrorMessage();
				e1.printStackTrace();
			}catch(SecurityException e1){
				showGrowlErrorMessage();
				e1.printStackTrace();
			}catch(SystemException e1){
				showGrowlErrorMessage();
				e1.printStackTrace();
			}
		}finally{
			em.close();
		}
		tipoSel = null;
	}
	
	public void elimina(ActionEvent event){
		EntityManager em = emf.createEntityManager();
		try{
			utx.begin();
			em.joinTransaction();
			Tipi objSel = em.find(Tipi.class, tipoSel.getId());
			log.info("elimina: " + objSel.getId() + " " + objSel.getNome() + " " + objSel.getDescrizione() + " " + objSel.getTipoPadre());
			em.remove(objSel);
			utx.commit();
			this.showGrowlDelMessage();
		}catch(Exception e){
			try{
				utx.rollback();
			}catch(IllegalStateException e1){
				showGrowlErrorMessage();
				e1.printStackTrace();
			}catch(SecurityException e1){
				showGrowlErrorMessage();
				e1.printStackTrace();
			}catch(SystemException e1){
				showGrowlErrorMessage();
				e1.printStackTrace();
			}
		}finally{
			em.close();
		}
		tipoSel = null;
	}

	private void showGrowlUpdMessage(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Successful", "Contatto aggiornato con successo"));
	}
	
	private void showGrowlInsMessage(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Successful", "Contatto inserito con successo"));
	}
	
	private void showGrowlDelMessage(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Successful", "Contatto eliminato con successo"));
	}
	
	private void showGrowlErrorMessage(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Errore", "Operazione fallita"));
	}
}
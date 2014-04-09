package it.cascino.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import it.cascino.model.Tipi;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;
//import java.util.logging.Logger;

@SessionScoped
public class ManagedBeanTipiDao implements TipiDao, Serializable{
	/**
	 * Logger
	 */
//	@Inject
	private static Logger log = Logger.getLogger(ManagedBeanTipiDao.class.getName());
	
	@Inject
	private EntityManager entityManager;
	
	@Inject
	private UserTransaction utx;
	
	public List<Tipi> getAll(){
		try{
			List<Tipi> tipi;
			try{
				utx.begin();
				String sql = "FROM Tipi t";
				Query query = entityManager.createQuery(sql);
				tipi = (List<Tipi>)query.getResultList();
			}catch(NoResultException e){
				tipi = null;
			}
			utx.commit();
			return tipi;
		}catch(Exception e){
			try{
				utx.rollback();
			}catch(SystemException se){
				throw new RuntimeException(se);
			}
			throw new RuntimeException(e);
		}
	}

	
	public void salva(Tipi tipo){
		try{
			try{
				utx.begin();
				log.info("1");
				log.info("transaction:" + " " + utx.getStatus());
				tipo.setId(null);
				log.info("salva: " + tipo.getId() + ", " + tipo.getNome() + ", " + tipo.getDescrizione() + ", " + tipo.getTipoPadre());
				entityManager.persist(tipo);
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				log.info("2");
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
//				log.info("5");
			}
		}catch(Exception e){
			try{
				log.info("3");
				log.info("transaction:" + " " + utx.getStatus());
				log.info("4");
				utx.rollback();
			}catch(SystemException se){
				log.info("5");
				throw new RuntimeException(se);
			}
			log.info("6");
			try{
				log.info("transaction:" + " " + utx.getStatus());
			}catch(SystemException e1){
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}
	}
	
	public void aggiorna(Tipi tipo){
		try{
			try{
				utx.begin();
				log.info("1");
				log.info("transaction:" + " " + utx.getStatus());
				log.info("aggiorna: " + tipo.getId() + ", " + tipo.getNome() + ", " + tipo.getDescrizione() + ", " + tipo.getTipoPadre());
				entityManager.merge(tipo);
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				log.info("2");
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
//				log.info("5");
			}
		}catch(Exception e){
			try{
				log.info("3");
				log.info("transaction:" + " " + utx.getStatus());
				log.info("4");
				utx.rollback();
			}catch(SystemException se){
				log.info("5");
				throw new RuntimeException(se);
			}
			log.info("6");
			try{
				log.info("transaction:" + " " + utx.getStatus());
			}catch(SystemException e1){
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}
	}
	
	public void elimina(Tipi tipo){
		try{
			try{
				utx.begin();
				Tipi objSel = entityManager.find(Tipi.class, tipo.getId());
				log.info("elimina: " + objSel.getId() + ", " + objSel.getNome() + ", " + objSel.getDescrizione() + ", " + objSel.getTipoPadre());
				entityManager.remove(objSel);
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				log.info("2");
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
//				log.info("5");
			}
		}catch(Exception e){
			try{
				log.info("3");
				log.info("transaction:" + " " + utx.getStatus());
				log.info("4");
				utx.rollback();
			}catch(SystemException se){
				log.info("5");
				throw new RuntimeException(se);
			}
			log.info("6");
			try{
				log.info("transaction:" + " " + utx.getStatus());
			}catch(SystemException e1){
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}		
	}
}

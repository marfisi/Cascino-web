package it.cascino.dao;

import java.io.Serializable;
import java.util.List;
import it.cascino.model.Tipi;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;
import org.primefaces.model.TreeNode;

@SessionScoped
public class ManagedBeanTipiTreeDao implements TipiTreeDao, Serializable{
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
	private EntityManager entityManager;
	
	@Inject
	private UserTransaction utx;
	
	public List<Tipi> getAll(){
		log.info("getAll: " + 1);
		try{
			List<Tipi> tipi;
			log.info("getAll: " + 2);
			try{
				log.info("getAll: " + 3);
				utx.begin();
				log.info("getAll: " + 4);
				String sql = "FROM Tipi t";
				log.info("getAll: " + 5);
				Query query = entityManager.createQuery(sql);
				log.info("getAll: " + 6);
				tipi = (List<Tipi>)query.getResultList();
				log.info("getAll: " + 7);
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
	
	public void salva(TreeNode nodo){
		Tipi tipo = (Tipi)nodo.getData();
		
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
	
	public void aggiorna(TreeNode nodo){
		Tipi tipo = (Tipi)nodo.getData();
		
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
	
	public void elimina(TreeNode nodo){
		Tipi tipoElimina = (Tipi)nodo.getData();

		try{
			try{
				utx.begin();
				Tipi tipo = entityManager.find(Tipi.class, tipoElimina.getId());
				log.info("elimina: " + tipo.getId() + ", " + tipo.getNome() + ", " + tipo.getDescrizione() + ", " + tipo.getTipoPadre());
				entityManager.remove(tipo);
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

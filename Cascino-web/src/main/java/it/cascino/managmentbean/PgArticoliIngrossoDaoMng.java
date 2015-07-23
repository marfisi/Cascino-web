package it.cascino.managmentbean;

import java.io.Serializable;
import java.util.List;
import it.cascino.model.PgArticoliIngrosso;
import it.cascino.util.Utility;
import it.cascino.dao.PgArticoliIngrossoDao;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;

@SessionScoped
public class PgArticoliIngrossoDaoMng implements PgArticoliIngrossoDao, Serializable{
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
	
	@SuppressWarnings("unchecked")
	public List<PgArticoliIngrosso> getAll(){
		List<PgArticoliIngrosso> articoli = null;
		try{
			try{
				utx.begin();
				Query query = entityManager.createNamedQuery("PgArticoliIngrosso.findAll");
				articoli = (List<PgArticoliIngrosso>)query.getResultList();
			}catch(NoResultException e){
				articoli = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return articoli;
	}
	
	public PgArticoliIngrosso getArticoloDaIdArticolo(Integer idArticolo){
		PgArticoliIngrosso articolo = null;
		try{
			try{
				utx.begin();
				Query query = entityManager.createNamedQuery("PgArticoliIngrosso.findByIdArticolo");
				query.setParameter("idArticolo", idArticolo);
				articolo = (PgArticoliIngrosso)query.getSingleResult();
			}catch(NoResultException e){
				articolo = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return articolo;
	}
}

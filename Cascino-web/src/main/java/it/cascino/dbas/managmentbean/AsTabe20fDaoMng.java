package it.cascino.dbas.managmentbean;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;
import it.cascino.dbas.dao.AsTabe20fDao;
import it.cascino.dbas.model.AsTabe20f;
import it.cascino.util.DatabaseDB2AS400DS;
import it.cascino.util.Utility;

public class AsTabe20fDaoMng implements AsTabe20fDao, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	@DatabaseDB2AS400DS
	@Inject
	private EntityManager emAS;
	
	@Inject
	private UserTransaction utx;
	
	@SuppressWarnings("unchecked")
	public List<AsTabe20f> getAll(){
		List<AsTabe20f> t = null;
		try{
			try{
				utx.begin();
				Query query = emAS.createNamedQuery("AsTabe20f.findAll");
				t = (List<AsTabe20f>)query.getResultList();
			}catch(NoResultException e){
				t = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return t;
	}
	
	public AsTabe20f getMarchio(String tbele){
		AsTabe20f t = null;
		try{
			try{
				utx.begin();
				Query query = emAS.createNamedQuery("AsTabe20f.findByTbele");
				query.setParameter("tbele", tbele);
				t = (AsTabe20f)query.getSingleResult();
			}catch(NoResultException e){
				t = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return t;
	}
}

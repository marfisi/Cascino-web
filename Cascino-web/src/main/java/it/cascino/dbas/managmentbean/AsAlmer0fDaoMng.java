package it.cascino.dbas.managmentbean;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;
import it.cascino.dbas.dao.AsAlmer0fDao;
import it.cascino.dbas.model.AsAlmer0f;
import it.cascino.util.DatabaseDB2AS400DS;
import it.cascino.util.Utility;

public class AsAlmer0fDaoMng implements AsAlmer0fDao, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
//	@Inject
//	private Logger log;
	
	@DatabaseDB2AS400DS
	@Inject
	private EntityManager emAS;
	
	@Inject
	private UserTransaction utx;
	
	@SuppressWarnings("unchecked")
	public List<AsAlmer0f> getAll(){
		List<AsAlmer0f> o = null;
		try{
			try{
				utx.begin();
				Query query = emAS.createNamedQuery("AsAlmer0f.findAll");
				o = (List<AsAlmer0f>)query.getResultList();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
//			Utility.manageException(e, utx, log);
		}
		return o;
	}

	public AsAlmer0f getDaId(String amset, String amgru, String amsot, String amfam, String amstf, String amst1){
		AsAlmer0f o = null;
		try{
			try{
				utx.begin();
				Query query = emAS.createNamedQuery("AsAlmer0f.findById");
				query.setParameter("amset", amset);
				query.setParameter("amgru", amgru);
				query.setParameter("amsot", amsot);
				query.setParameter("amfam", amfam);
				query.setParameter("amstf", amstf);
				query.setParameter("amst1", amst1);
				o = (AsAlmer0f)query.getSingleResult();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
//			Utility.manageException(e, utx, log);
		}
		return o;
	}
}

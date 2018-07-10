package it.cascino.dbas.managmentbean;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;
import it.cascino.dbas.dao.AsAnmar0fDao;
import it.cascino.dbas.model.AsAnmar0f;
import it.cascino.util.DatabaseDB2AS400DS;
import it.cascino.util.Utility;

public class AsAnmar0fDaoMng implements AsAnmar0fDao, Serializable{
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
	public List<AsAnmar0f> getAll(){
		List<AsAnmar0f> o = null;
		try{
			try{
				utx.begin();
				Query query = emAS.createNamedQuery("AsAnmar0f.findAll");
				o = (List<AsAnmar0f>)query.getResultList();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return o;
	}
	
//	public void salva(AsAnmar0f a){
//		try{
//			try{
//				utx.begin();
//				// precodice.setId(null);
//				log.info("salva: " + a.toString());
//				em.persist(a);
//			}finally{
//				utx.commit();
//			}
//		}catch(Exception e){
//			log.fatal(e.toString());
//		}
//	}
//	
//	public void aggiorna(AsAnmar0f a){
//		try{
//			try{
//				utx.begin();
//				log.info("aggiorna: " + a.toString());
//				em.merge(a);
//			}finally{
//				utx.commit();
//			}
//		}catch(Exception e){
//			log.fatal(e.toString());
//		}
//	}
//	
//	public void elimina(AsAnmar0f aElimina){
//		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + produttoreElimina + ")");
//		try{
//			try{
//				utx.begin();
//				AsAnmar0f a = em.find(AsAnmar0f.class, aElimina.getMcoda());
//				log.info("elimina: " + a.toString());
//				em.remove(a);
//			}finally{
//				utx.commit();
//			}
//		}catch(Exception e){
//			log.fatal(e.toString());
//		}
//	}
	
	public AsAnmar0f getGruppoDaMcomp(String mcomp){
		AsAnmar0f o = null;
		try{
			try{
				utx.begin();
				Query query = emAS.createNamedQuery("AsAnmar0f.findByMcomp");
				query.setParameter("mcomp", mcomp);
				o = (AsAnmar0f)query.getSingleResult();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return o;
	}
}

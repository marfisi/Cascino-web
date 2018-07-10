package it.cascino.dbas.managmentbean;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;
import it.cascino.dbas.dao.AsAnmag0fDao;
import it.cascino.dbas.model.AsAnmag0f;
import it.cascino.util.DatabaseDB2AS400DS;
import it.cascino.util.Utility;

public class AsAnmag0fDaoMng implements AsAnmag0fDao, Serializable{
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
	public List<AsAnmag0f> getAll(){
		List<AsAnmag0f> o = null;
		try{
			try{
				utx.begin();
				Query query = emAS.createNamedQuery("AsAnmag0f.findAll");
				o = (List<AsAnmag0f>)query.getResultList();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return o;
	}
	
//	public void salva(AsAnmag0f a){
//		try{
//			try{
//				utx.begin();
//				// precodice.setId(null);
//				log.info("salva: " + a.toString());
//				emAS.persist(a);
//			}finally{
//				utx.commit();
//			}
//		}catch(Exception e){
//			log.fatal(e.toString());
//		}
//	}
//	
//	public void aggiorna(AsAnmag0f a){
//		try{
//			try{
//				utx.begin();
//				log.info("aggiorna: " + a.toString());
//				emAS.merge(a);
//			}finally{
//				utx.commit();
//			}
//		}catch(Exception e){
//			log.fatal(e.toString());
//		}
//	}
//	
//	public void elimina(AsAnmag0f aElimina){
//		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + produttoreElimina + ")");
//		try{
//			try{
//				utx.begin();
//				AsAnmag0f a = emAS.find(AsAnmag0f.class, aElimina.getMcoda());
//				log.info("elimina: " + a.toString());
//				em.remove(a);
//			}finally{
//				utx.commit();
//			}
//		}catch(Exception e){
//			log.fatal(e.toString());
//		}
//	}
	
	public AsAnmag0f getArticoloDaMcoda(String mcoda){
		AsAnmag0f o = null;
		try{
			try{
				utx.begin();
				Query query = emAS.createNamedQuery("AsAnmag0f.findByMcoda");
				query.setParameter("mcoda", mcoda);
				o = (AsAnmag0f)query.getSingleResult();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return o;
	}
	
	@SuppressWarnings("unchecked")
	public List<AsAnmag0f> getArticoliIngrosso(){
		List<AsAnmag0f> o = null;
		try{
			try{
				utx.begin();
				Query query = emAS.createNamedQuery("AsAnmag0f.findAllIngrosso");
				o = (List<AsAnmag0f>)query.getResultList();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return o;
	}
	
	@SuppressWarnings("unchecked")
	public List<AsAnmag0f> getArticoliDaMcomp(String mcomp){
		List<AsAnmag0f> o = null;
		try{
			try{
				utx.begin();
				Query query = emAS.createNamedQuery("AsAnmag0f.findByMcomp");
				query.setParameter("mcomp", mcomp);
				o = (List<AsAnmag0f>)query.getResultList();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return o;
	}

	public Boolean getSeArticoloHaQuestoMcomp(String mcoda, String mcomp){
		Boolean o = false;
		try{
			try{
				utx.begin();
				Query query = emAS.createNamedQuery("AsAnmag0f.seArticoloHaQuestoMcomp");
				query.setParameter("mcoda", mcoda + "%");
				query.setParameter("mcomp", mcomp);
				o = (((Long)query.getSingleResult()).intValue() == 0) ? false : true;
			}catch(NoResultException e){
				o = false;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return o;
	}
}

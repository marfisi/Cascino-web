package it.cascino.dbas.mng;

import java.io.Serializable;
import java.util.List;
import it.cascino.dbas.model.AsAnmag0f;
import it.cascino.util.DatabaseDB2AS400DS;
//import it.cascino.util.DatabasePostgresqlDS;
import it.cascino.util.Utility;
import it.cascino.dbas.dao.AsAnmag0fDao;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;

@SessionScoped
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
		List<AsAnmag0f> cod = null;
		try{
			try{
				utx.begin();
				Query query = emAS.createNamedQuery("AsAnmag0f.findAll");
				cod = (List<AsAnmag0f>)query.getResultList();
			}catch(NoResultException e){
				cod = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return cod;
	}
	
//	public void salva(AsAnmag0f a){
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
//			Utility.manageException(e, utx, log);
//		}
//	}
//	
//	public void aggiorna(AsAnmag0f a){
//		try{
//			try{
//				utx.begin();
//				log.info("aggiorna: " + a.toString());
//				em.merge(a);
//			}finally{
//				utx.commit();
//			}
	//	}catch(Exception e){
	//	Utility.manageException(e, utx, log);
	//}
//	}
//	
//	public void elimina(AsAnmag0f aElimina){
//		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + produttoreElimina + ")");
//		try{
//			try{
//				utx.begin();
//				AsAnmag0f a = em.find(AsAnmag0f.class, aElimina.getMcoda());
//				log.info("elimina: " + a.toString());
//				em.remove(a);
//			}finally{
//				utx.commit();
//			}
//	}catch(Exception e){
//	Utility.manageException(e, utx, log);
//}
//	}
	
	public AsAnmag0f getArticoloDaMcoda(String mcoda){
		AsAnmag0f cod = null;
		try{
			try{
				utx.begin();
				Query query = emAS.createNamedQuery("AsAnmag0f.findByMcoda");
				query.setParameter("mcoda", mcoda);
				cod = (AsAnmag0f)query.getSingleResult();
			}catch(NoResultException e){
				cod = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return cod;
	}
	
	@SuppressWarnings("unchecked")
	public List<AsAnmag0f> getArticoliIngrosso(){
		List<AsAnmag0f> cod = null;
		try{
			try{
				utx.begin();
				Query query = emAS.createNamedQuery("AsAnmag0f.findAllIngrosso");
				cod = (List<AsAnmag0f>)query.getResultList();
			}catch(NoResultException e){
				cod = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return cod;
	}

}

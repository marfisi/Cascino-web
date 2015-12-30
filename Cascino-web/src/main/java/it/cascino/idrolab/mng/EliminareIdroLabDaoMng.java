package it.cascino.idrolab.mng;

import java.io.Serializable;
import it.cascino.util.DatabaseDB2AS400DS;
import it.cascino.util.Utility;
import it.cascino.idrolab.model.IdroLab;
import it.cascino.idrolab.dao.EliminareIdroLabDao;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;

@SessionScoped
public class EliminareIdroLabDaoMng implements EliminareIdroLabDao, Serializable{
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
	
	// @SuppressWarnings("unchecked")
	// public List<PgArticoliIngrosso> getAll(){
	// List<PgArticoliIngrosso> articoli = null;
	// try{
	// try{
	// utx.begin();
	// Query query = emAs.createNamedQuery("PgArticoliIngrosso.findAll");
	// articoli = (List<PgArticoliIngrosso>)query.getResultList();
	// }catch(NoResultException e){
	// articoli = null;
	// }
	// utx.commit();
	// }catch(Exception e){
	// log.fatal(e.toString());
	// }
	// return articoli;
	// }
	
	public void salva(IdroLab o){
		try{
			try{
				utx.begin();
				// precodice.setId(null);
				log.info("salva: " + o.toString());
				emAS.persist(o);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
	}
	
	public void aggiorna(IdroLab o){
		try{
			try{
				utx.begin();
				log.info("aggiorna: " + o.toString());
				emAS.merge(o);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
	}
	
	public void elimina(IdroLab oElimina){
		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + produttoreElimina + ")");
		try{
			try{
				utx.begin();
				IdroLab o = emAS.find(IdroLab.class, 1);
				log.info("elimina: " + o.toString());
				emAS.remove(o);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
	}
	
	// public PgArticoliIngrosso getArticoloDaIdArticolo(Integer idArticolo){
	// PgArticoliIngrosso articolo = null;
	// try{
	// try{
	// utx.begin();
	// Query query = emAs.createNamedQuery("PgArticoliIngrosso.findByIdArticolo");
	// query.setParameter("idArticolo", idArticolo);
	// articolo = (PgArticoliIngrosso)query.getSingleResult();
	// }catch(NoResultException e){
	// articolo = null;
	// }
	// utx.commit();
	// }catch(Exception e){
	// Utility.manageException(e, utx, log);
	// }
	// return articolo;
	// }
	
	public it.cascino.idrolab.model.ws1_19.Articolo getArticoloPerCodArtWS1(String siglaMarca, String codiceArticolo){
		IdroLab o = new IdroLab();
//		o.creaClient();
		
		log.info("iiiiiiiiiiiiiiiiiiiiii: ");
		return o.getArticoloPerCodArtWS1(siglaMarca, codiceArticolo);
	}
}

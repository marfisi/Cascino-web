package it.cascino.idrolab.dbpg.mng;

import java.io.Serializable;
import java.util.List;
import it.cascino.idrolab.dbpg.dao.PgMarcheDao;
import it.cascino.idrolab.dbpg.model.PgMarche;
import it.cascino.util.DatabasePostgresqlDS;
import it.cascino.util.Utility;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
//import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;

@SessionScoped
public class PgMarcheDaoMng implements PgMarcheDao, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	@DatabasePostgresqlDS
	@Inject
	private EntityManager emPg;
	
	@Inject
	private UserTransaction utx;
	
	public void salva(PgMarche o){
		// log.info("tmpDEBUGtmp: " + "> " + "salva(" + produttore + ")");
		try{
			try{
				utx.begin();
				o.setId(null);
				log.info("salva: " + o.getId() + ", " + o.getIdMarca() + ", " + o.getMarDescrizione());
				emPg.persist(o);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void aggiorna(PgMarche o){
		// log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + produttore + ")");
		try{
			try{
				utx.begin();
				log.info("aggiorna: " + o.getId() + ", " + o.getIdMarca() + ", " + o.getMarDescrizione());
				emPg.merge(o);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	public void elimina(PgMarche oElimina){
		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + produttoreElimina + ")");
		try{
			try{
				utx.begin();
				PgMarche o = emPg.find(PgMarche.class, oElimina.getId());
//				log.info("elimina: " + o.getId() + ", " + o.getNome() + ", " + o.getDati() + ", " + o.getIdFoto());
				emPg.remove(o);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	@SuppressWarnings("unchecked")
	public List<PgMarche> getAll(){
		List<PgMarche> obj = null;
		try{
			try{
				utx.begin();
				Query query = emPg.createNamedQuery("PgMarche.findAll");
				obj = (List<PgMarche>)query.getResultList();
			}catch(NoResultException e){
				obj = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return obj;
	}
	
	public PgMarche getMarcaDaId(Integer idObj){
		PgMarche obj = null;
		try{
			try{
				utx.begin();
				Query query = emPg.createNamedQuery("PgMarche.findById");
				query.setParameter("id", idObj);
				obj = (PgMarche)query.getSingleResult();
//				obj = (PgMarche)query.getResultList().get(0);
			}catch(NoResultException e){
				obj = null;
			}catch(IndexOutOfBoundsException iob){
				obj = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return obj;
	}
	
	public PgMarche getMarcaDaIdMarca(String idObj){
		PgMarche obj = null;
		try{
			try{
				utx.begin();
				Query query = emPg.createNamedQuery("PgMarche.findByIdMarca");
				query.setParameter("idMarca", idObj);
				obj = (PgMarche)query.getSingleResult();
			}catch(NoResultException e){
				obj = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return obj;
	}
	
	public PgMarche getMarcaDaMcofo(Integer idObj){
		PgMarche obj = null;
		try{
			try{
				utx.begin();
				Query query = emPg.createNamedQuery("PgMarche.findByMcofo");
				query.setParameter("id", idObj);
				obj = (PgMarche)query.getSingleResult();
			}catch(NoResultException e){
				obj = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return obj;
	}
	
	public PgMarche getMarcaDaPortalefotoIdProduttore(Integer idObj){
		PgMarche obj = null;
		try{
			try{
				utx.begin();
				Query query = emPg.createNamedQuery("PgMarche.findByPortalefotoIdProduttore");
				query.setParameter("id", idObj);
				obj = (PgMarche)query.getSingleResult();
			}catch(NoResultException e){
				obj = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return obj;
	}
}

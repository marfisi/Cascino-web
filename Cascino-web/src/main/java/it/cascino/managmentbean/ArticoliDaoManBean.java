package it.cascino.managmentbean;

import java.io.Serializable;
import java.util.List;
import it.cascino.dao.ArticoliDao;
import it.cascino.model.Articoli;
import it.cascino.model.Foto;
import it.cascino.util.Utility;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;

@SessionScoped
public class ArticoliDaoManBean implements ArticoliDao, Serializable{
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
	public List<Articoli> getAll(){
		log.info("tmpDEBUGtmp: " + "> " + "getAll(" + ")");
		List<Articoli> articoli = null;
		try{
			try{
				utx.begin();
				Query query = entityManager.createNamedQuery("Articoli.findAll");
				articoli = (List<Articoli>)query.getResultList();
			}catch(NoResultException e){
				articoli = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "getAll");
		return articoli;
	}
	
	public void salva(Articoli articolo){
		log.info("tmpDEBUGtmp: " + "> " + "salva(" + articolo + ")");
		try{
			try{
				utx.begin();
				log.info("transaction:" + " " + utx.getStatus());
				articolo.setId(null);
				log.info("salva: " + articolo.getId() + ", " + articolo.getCodice() + ", " + articolo.getNome() + ", " + articolo.getDescrizione());
				entityManager.persist(articolo);
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void aggiorna(Articoli articolo){
		log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + articolo + ")");
		try{
			try{
				utx.begin();
				log.info("transaction:" + " " + utx.getStatus());
				log.info("aggiorna: " + articolo.getId() + ", " + articolo.getCodice() + ", " + articolo.getNome() + ", " + articolo.getDescrizione());
				entityManager.merge(articolo);
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	public void elimina(Articoli articoloElimina){
		log.info("tmpDEBUGtmp: " + "> " + "elimina(" + articoloElimina + ")");
		try{
			try{
				utx.begin();
				Articoli articolo = entityManager.find(Articoli.class, articoloElimina.getId());
				log.info("elimina: " + articolo.getId() + ", " + articolo.getCodice() + ", " + articolo.getNome() + ", " + articolo.getDescrizione());
				entityManager.remove(articolo);
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	public Foto getFotoArticoloDaIdArticolo(Integer idArticolo){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoArticoloDaIdArticolo(" + idArticolo + ")");
		log.info("tmpDEBUGtmp: " + "< " + "getFotoArticoloDaIdArticolo");
		return (getFotoArticoloOrdLsDaIdArticolo(idArticolo).size() > 0) ? getFotoArticoloOrdLsDaIdArticolo(idArticolo).get(0) : null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Foto> getFotoArticoloOrdLsDaIdArticolo(Integer idArticolo){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoArticoloOrdLsDaIdArticolo(" + idArticolo + ")");
		List<Foto> foto = null;
		try{
			try{
				utx.begin();
				// String sql = "select * " + // solo la prima
				// "from foto " +
				// "where id = ( " +
				// "select selordrow.foto " +
				// "from ( " +
				// "select row_number() OVER () AS rownum, selord.foto  " +
				// "from (select foto " +
				// "from articoli_foto af join articoli a on af.articolo = a.id " +
				// "where articolo = :id " +
				// "order by ordinamento, af.updtime desc) as selord " +
				// ") as selordrow " +
				// "where selordrow.rownum = 1 " +
				// ")";
				String sql = "select f.* " +
				"from ( " +
				"select row_number() OVER () AS rownum, selord.foto  " +
				"from (select foto " +
				"from articoli_foto af join articoli a on af.articolo = a.id  " +
				"where articolo = :id " +
				"order by ordinamento, af.updtime desc) as selord) as selordjoin left join foto f on selordjoin.foto = f.id";
				Query query = entityManager.createNativeQuery(sql, Foto.class); // Native
				query.setParameter("id", idArticolo);
				foto = (List<Foto>)query.getResultList();
			}catch(NoResultException e){
				foto = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoArticoloOrdLsDaIdArticolo");
		return foto;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getArticoliAutoCompleteLs(String str){
		log.info("tmpDEBUGtmp: " + "> " + "getArticoliAutoCompleteLs(" + str + ")");
		List<String> articoli = null;
		try{
			try{
				utx.begin();
				String sql = "SELECT upper(a.codice) FROM Articoli a WHERE upper(a.codice) like upper(:str)";
				Query query = entityManager.createQuery(sql);
				query.setParameter("str", "%" + str + "%");
				articoli = (List<String>)query.getResultList();
			}catch(NoResultException e){
				articoli = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "getArticoliAutoCompleteLs");
		return articoli;
	}
	
	// @SuppressWarnings("unchecked")
	// public List<String> getProduttoriAutoCompleteLs(String str){
	// log.info("tmpDEBUGtmp: " + "> " + "getProduttoriAutoCompleteLs(" + str + ")");
	// List<String> articoli = null;
	// try{
	// try{
	// utx.begin();
	// String sql = "SELECT upper(p.nome) FROM Produttori p WHERE upper(p.nome) like upper(:str)";
	// Query query = entityManager.createQuery(sql);
	// query.setParameter("str", "%" + str + "%");
	// articoli = (List<String>)query.getResultList();
	// }catch(NoResultException e){
	// articoli = null;
	// }
	// utx.commit();
	// }catch(Exception e){
	// Utility.manageException(e, utx, log);
	// }
	// log.info("tmpDEBUGtmp: " + "< " + "getProduttoriAutoCompleteLs");
	// return articoli;
	// }
}

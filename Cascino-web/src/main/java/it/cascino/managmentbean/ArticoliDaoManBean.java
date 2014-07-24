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
		List<Articoli> articoli = null;
		try{
			try{
				utx.begin();
				// String sql = "FROM Articoli a";
				// Query query = entityManager.createQuery(sql);
				Query query = entityManager.createNamedQuery("Articoli.findAll");
				articoli = (List<Articoli>)query.getResultList();
			}catch(NoResultException e){
				articoli = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return articoli;
	}
	
	public void salva(Articoli articolo){
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
	}
	
	public void aggiorna(Articoli articolo){
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
	}
	
	public void elimina(Articoli articoloElimina){
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
	}
	
	public Foto getFotoDaArticolo(Integer idArticolo){
		Foto foto = null;
		try{
			try{
				utx.begin();
				// String sql = "FROM Foto f Where f.id = :id";
				String sql = "select * from foto " +
				"where id = (select foto " +
				"from (select row_number() OVER (ORDER BY foto) AS rownum, foto " +
				"from articoli_foto af join articoli a on af.articolo = a.id " +
				"where articolo = :id " +
				"order by ordinamento, af.updtime) as sub " +
				"where rownum = 1)";
				Query query = entityManager.createNativeQuery(sql, Foto.class); // Native
				query.setParameter("id", idArticolo);
				foto = (Foto)query.getSingleResult(); // .getResultList().get(0);
			}catch(NoResultException e){
				foto = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return foto;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getArticoliAutoCompleteLs(String str){
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
		return articoli;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getProduttoriAutoCompleteLs(String str){
		List<String> articoli = null;
		try{
			try{
				utx.begin();
				String sql = "SELECT upper(p.nome) FROM Produttori p WHERE upper(p.nome) like upper(:str)";
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
		return articoli;
	}
}

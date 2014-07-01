package it.cascino.dao;

import java.io.Serializable;
import java.util.List;
import it.cascino.model.Articoli;
import it.cascino.model.Foto;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;

@SessionScoped
public class ManBeanArticoliDao implements ArticoliDao, Serializable{
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
	
	public List<Articoli> getAll(){
		try{
			List<Articoli> articoli;
			try{
				utx.begin();
				String sql = "FROM Articoli a";
				Query query = entityManager.createQuery(sql);
				articoli = (List<Articoli>)query.getResultList();
			}catch(NoResultException e){
				articoli = null;
			}
			utx.commit();
			return articoli;
		}catch(Exception e){
			try{
				utx.rollback();
			}catch(SystemException se){
				throw new RuntimeException(se);
			}
			throw new RuntimeException(e);
		}
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
			try{
				log.info("transaction:" + " " + utx.getStatus());
				utx.rollback();
			}catch(SystemException se){
				throw new RuntimeException(se);
			}
			try{
				log.info("transaction:" + " " + utx.getStatus());
			}catch(SystemException e1){
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
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
			try{
				log.info("transaction:" + " " + utx.getStatus());
				utx.rollback();
			}catch(SystemException se){
				throw new RuntimeException(se);
			}
			try{
				log.info("transaction:" + " " + utx.getStatus());
			}catch(SystemException e1){
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
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
			try{
				log.info("transaction:" + " " + utx.getStatus());
				utx.rollback();
			}catch(SystemException se){
				throw new RuntimeException(se);
			}
			try{
				log.info("transaction:" + " " + utx.getStatus());
			}catch(SystemException e1){
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}		
	}
	
	public Foto getFotoDaArticolo(Integer idArticolo){
		Foto foto;
		try{
			try{
				utx.begin();
//				String sql = "FROM Foto f Where f.id = :id";
				String sql = "select * from foto " +
				"where id = (select foto " +
				"from (select row_number() OVER (ORDER BY foto) AS rownum, foto " +
				"from articoli_foto af join articoli a on af.articolo = a.id " +
				"where articolo = :id " +
				"order by ordinamento, af.updtime) as sub " +
				"where rownum = 1)";
				Query query = entityManager.createNativeQuery(sql, Foto.class);	// Native
				query.setParameter("id", idArticolo);
				foto = (Foto)query.getSingleResult();	// .getResultList().get(0);
			}catch(NoResultException e){
				foto = null;
			}
			utx.commit();
		}catch(Exception e){
			try{
				utx.rollback();
			}catch(SystemException se){
				throw new RuntimeException(se);
			}
			throw new RuntimeException(e);
		}	
		return foto;
	}
	
}

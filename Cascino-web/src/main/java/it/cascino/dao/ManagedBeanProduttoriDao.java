package it.cascino.dao;

import java.io.Serializable;
import java.util.List;
import it.cascino.model.Produttori;
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
public class ManagedBeanProduttoriDao implements ProduttoriDao, Serializable{
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
	
	public List<Produttori> getAll(){
		try{
			List<Produttori> produttori;
			try{
				utx.begin();
				String sql = "FROM Produttori p";
				Query query = entityManager.createQuery(sql);
				produttori = (List<Produttori>)query.getResultList();
			}catch(NoResultException e){
				produttori = null;
			}
			utx.commit();
			return produttori;
		}catch(Exception e){
			try{
				utx.rollback();
			}catch(SystemException se){
				throw new RuntimeException(se);
			}
			throw new RuntimeException(e);
		}
	}
	
	public void salva(Produttori produttore){
		try{
			try{
				utx.begin();
				log.info("transaction:" + " " + utx.getStatus());
				produttore.setId(null);
				log.info("salva: " + produttore.getId() + ", " + produttore.getNome() + ", " + produttore.getDati() + ", " + produttore.getFoto());
				entityManager.persist(produttore);
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
	
	public void aggiorna(Produttori produttore){
		try{
			try{
				utx.begin();
				log.info("transaction:" + " " + utx.getStatus());
				log.info("aggiorna: " + produttore.getId() + ", " + produttore.getNome() + ", " + produttore.getDati() + ", " + produttore.getFoto());
				entityManager.merge(produttore);
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
	
	public void elimina(Produttori produttoreElimina){
		try{
			try{
				utx.begin();
				Produttori produttore = entityManager.find(Produttori.class, produttoreElimina.getId());
				log.info("elimina: " + produttore.getId() + ", " + produttore.getNome() + ", " + produttore.getDati() + ", " + produttore.getFoto());
				entityManager.remove(produttore);
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
	
	public Foto getFoto(Integer idArticolo){
		Foto foto;
		try{
			try{
				utx.begin();
				String sql = "select * from foto " +
				"where id = ( " +
				"select foto " +
				"from produttori p join articoli a on p.id = a.produttore " +
				"where a.id = :id)";
				Query query = entityManager.createNativeQuery(sql, Foto.class);	// Native
				query.setParameter("id", idArticolo);
				foto = (Foto)query.getSingleResult();
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
	
	public String getNome(Integer idArticolo){
		String nome;
		try{
			try{
				utx.begin();
				String sql = "select p.nome " +
				"from produttori p join articoli a on p.id = a.produttore " +
				"where a.id = :id";
				Query query = entityManager.createNativeQuery(sql);	// Native     ??? devo prevedere Produttore.class????? quindi ritornare Produttore e non string
				query.setParameter("id", idArticolo);
				nome = (String)query.getSingleResult();
			}catch(NoResultException e){
				nome = null;
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
		return nome;
	}
}

package it.cascino.managmentbean;

import java.io.Serializable;
import java.util.List;
import it.cascino.dao.ProduttoriDao;
import it.cascino.model.Produttori;
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
public class ProduttoriDaoManBean implements ProduttoriDao, Serializable{
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
	public List<Produttori> getAll(){
		List<Produttori> produttori = null;
		try{
			try{
				utx.begin();
				// String sql = "FROM Produttori p";
				// Query query = entityManager.createQuery(sql);
				Query query = entityManager.createNamedQuery("Produttori.findAll");
				produttori = (List<Produttori>)query.getResultList();
			}catch(NoResultException e){
				produttori = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return produttori;
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
			Utility.manageException(e, utx, log);
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
			Utility.manageException(e, utx, log);
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
			Utility.manageException(e, utx, log);
		}
	}
	
	public Foto getFoto(Integer id){
		Foto foto = null;
		try{
			try{
				utx.begin();
				String sql = "select * from foto " +
				"where id = ( " +
				"select foto " +
				"from produttori p " +
				"where p.id = :id)";
				Query query = entityManager.createNativeQuery(sql, Foto.class); // Native
				query.setParameter("id", id);
				foto = (Foto)query.getSingleResult();
			}catch(NoResultException e){
				foto = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return foto;
	}
	
	public Foto getFotoDaArticolo(Integer idArticolo){
		Foto foto = null;
		try{
			try{
				utx.begin();
				String sql = "select * from foto " +
				"where id = ( " +
				"select foto " +
				"from produttori p join articoli a on p.id = a.produttore " +
				"where a.id = :id)";
				Query query = entityManager.createNativeQuery(sql, Foto.class); // Native
				query.setParameter("id", idArticolo);
				foto = (Foto)query.getSingleResult();
			}catch(NoResultException e){
				foto = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return foto;
	}
	
	public String getNomeDaArticolo(Integer idArticolo){
		String nome = null;
		try{
			try{
				utx.begin();
				String sql = "select p.nome " +
				"from produttori p join articoli a on p.id = a.produttore " +
				"where a.id = :id";
				Query query = entityManager.createNativeQuery(sql); // Native ??? devo prevedere Produttore.class????? quindi ritornare Produttore e non string
				query.setParameter("id", idArticolo);
				nome = (String)query.getSingleResult();
			}catch(NoResultException e){
				nome = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return nome;
	}
	
	public Produttori getProduttoreDaId(Integer id){
		Produttori produttore = new Produttori();
		try{
			try{
				utx.begin();
				Query query = entityManager.createNamedQuery("Produttori.findById", Produttori.class);
				query.setParameter("id", id);
				produttore = (Produttori)query.getSingleResult();
			}catch(NoResultException e){
				produttore = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return produttore;
	}

	
}

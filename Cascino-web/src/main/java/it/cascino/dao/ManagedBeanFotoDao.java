package it.cascino.dao;

import java.util.List;
import it.cascino.h8.entity.Foto;
import it.cascino.h8.entity.Test;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;

//@SessionScoped
public class ManagedBeanFotoDao implements FotoDao{
	/**
	 * Logger
	 */
	private static Logger log = Logger.getLogger(ManagedBeanFotoDao.class.getName());
	
	@Inject
	private EntityManager entityManager;
	
	@Inject
	private UserTransaction utx;
	
	public Foto getForName(String fotoName){
		Foto foto;
		try{
			try{
				utx.begin();
				String sql = "from Foto as f where f.originale = :fotoname";
				Query query = entityManager.createQuery(sql);
				query.setParameter("fotoname", fotoName);
				foto = (Foto)query.getSingleResult();
			}catch(NoResultException e){
				foto = null;
			}
			utx.commit();
			return foto;
		}catch(Exception e){
			try{
				utx.rollback();
			}catch(SystemException se){
				throw new RuntimeException(se);
			}
			throw new RuntimeException(e);
		}
	}
	
	public Foto getForId(int fotoId){
		Foto foto;
		if(fotoId < 0){
			return null;
		}
		try{
			try{
				utx.begin();
				foto = entityManager.find(Foto.class, fotoId);
			}finally{
				utx.commit();
			}
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
	
	public List<Foto> getAll(){
		try{
			List<Foto> foto;
			try{
				utx.begin();
				String sql = "from Foto as f";
				Query query = entityManager.createQuery(sql);
				foto = (List<Foto>)query.getResultList();
			}catch(NoResultException e){
				foto = null;
			}
			utx.commit();
			return foto;
		}catch(Exception e){
			try{
				utx.rollback();
			}catch(SystemException se){
				throw new RuntimeException(se);
			}
			throw new RuntimeException(e);
		}
	}
	
	public void insertFoto(Foto foto){
		try{
			try{
				utx.begin();
				log.info("1");
//				Test t = new Test();
//				t.setId(5);
//				t.setA("ciao");
//				log.info("2");
				log.info("transaction:" + " " + utx.getStatus());
				entityManager.persist(foto);//t);
				log.info("transaction:" + " " + utx.getStatus());
//				log.info("3");
			}finally{
				log.info("2");
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
//				log.info("5");
			}
		}catch(Exception e){
			try{
				log.info("3");
				log.info("transaction:" + " " + utx.getStatus());
				log.info("4");
				utx.rollback();
//				log.info("8");
			}catch(SystemException se){
				log.info("5");
				throw new RuntimeException(se);
			}
			log.info("6");
			try{
				log.info("transaction:" + " " + utx.getStatus());
			}catch(SystemException e1){
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}
	}
	
	public void insertFoto2(Foto foto){
		Foto f = new Foto();
		f.setPath("path");
		f.setOriginale("originale");
		try{
			try{
				// utx.begin();
				entityManager.getTransaction().begin();
				// entityManager.persist(f); //foto);
				Test t = new Test();
				t.setA("ciao");
				entityManager.persist(t);
			}finally{
				// utx.commit();
				entityManager.getTransaction().commit();
			}
		}catch(Exception e){
			// try{
			// utx.rollback();
			entityManager.getTransaction().rollback();
			// }catch(SystemException se){
			// throw new RuntimeException(se);
			// }
			throw new RuntimeException(e);
		}
	}
}

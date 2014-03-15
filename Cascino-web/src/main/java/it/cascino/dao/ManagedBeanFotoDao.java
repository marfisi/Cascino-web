package it.cascino.dao;

import java.util.List;
import it.cascino.h8.entity.Foto;
import it.cascino.h8.entity.Test;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;


public class ManagedBeanFotoDao implements FotoDao{
	
	@Inject
	private EntityManager entityManager;
	
	@Inject
	private UserTransaction utx;
	
	public Foto getForName(String fotoName){
		try{
			Foto foto;
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
		Foto foto = new Foto();
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
				Test t = new Test();
				t.setA("ciao");
				entityManager.persist(t);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			try{
				if(utx.getStatus()==Status.STATUS_ACTIVE){
					utx.rollback();
				}
			}catch(SystemException se){
				throw new RuntimeException(se);
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
//				utx.begin();
				entityManager.getTransaction().begin();
//				entityManager.persist(f); //foto);
				Test t = new Test();
				t.setA("ciao");
				entityManager.persist(t);
			}finally{
//				utx.commit();
				entityManager.getTransaction().commit();
			}
		}catch(Exception e){
//			try{
//				utx.rollback();
				entityManager.getTransaction().rollback();
//			}catch(SystemException se){
//				throw new RuntimeException(se);
//			}
			throw new RuntimeException(e);
		}
	}
}

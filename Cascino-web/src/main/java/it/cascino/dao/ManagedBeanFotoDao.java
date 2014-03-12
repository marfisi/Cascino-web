package it.cascino.dao;

import java.util.List;
import it.cascino.h8.entity.Foto;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
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
		Foto f = new Foto();
		f.setPath("path");
		f.setOriginale("originale");
		try{
			try{
				utx.begin();
				entityManager.persist(f); //foto);
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
	}
}

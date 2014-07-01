package it.cascino.dao;

import java.io.Serializable;
import it.cascino.model.Userloginrole;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;

@SessionScoped
public class ManBeanUserLoginDao implements UserLoginDao, Serializable{
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
	
	public void salva(Userloginrole user){
		try{
			try{
				utx.begin();
				log.info("transaction:" + " " + utx.getStatus());
				user.setId(null);
				log.info("salva: " + user.getId() + ", " + user.getLogin());
				entityManager.persist(user);
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
	
	public void aggiorna(Userloginrole user){
		try{
			try{
				utx.begin();
				log.info("transaction:" + " " + utx.getStatus());
				log.info("aggiorna: " + user.getId() + ", " + user.getLogin());
				entityManager.merge(user);
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
	
	public void elimina(Userloginrole userElimina){
		try{
			try{
				utx.begin();
				Userloginrole user = entityManager.find(Userloginrole.class, userElimina.getId());
				log.info("elimina: " + user.getId() + ", " + user.getLogin());
				entityManager.remove(user);
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
	
	public Boolean canAccess(String user, String password){
		Boolean canAccess = false;
		try{
			try{
				utx.begin();
				String sql = "select count(u) FROM Userloginrole u Where u.login = :user and u.password = :password";
				Query query = entityManager.createQuery(sql);
				query.setParameter("user", user);
				query.setParameter("password", password);
				canAccess = ((((Long)query.getSingleResult()) == 1) ? true : false);
			}catch(NoResultException e){
				canAccess = false;
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
		return canAccess;
	}
}

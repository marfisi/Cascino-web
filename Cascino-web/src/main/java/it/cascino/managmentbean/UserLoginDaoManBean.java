package it.cascino.managmentbean;

import java.io.Serializable;
import java.util.List;
import it.cascino.dao.UserLoginDao;
import it.cascino.model.Produttori;
import it.cascino.model.Users;
import it.cascino.model.Userspermissions;
import it.cascino.model.Usersroles;
import it.cascino.util.Utility;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;
import org.jboss.logging.MDC;

@SessionScoped
public class UserLoginDaoManBean implements UserLoginDao, Serializable{
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
	
	public void salva(Users user){
		log.info("tmpDEBUGtmp: " + "> " + "salva(" + user + ")");
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
			log.info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void aggiorna(Users user){
		log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + user + ")");
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
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	public void elimina(Users userElimina){
		log.info("tmpDEBUGtmp: " + "> " + "Userloginrole(" + userElimina + ")");
		try{
			try{
				utx.begin();
				Users user = entityManager.find(Users.class, userElimina.getId());
				log.info("elimina: " + user.getId() + ", " + user.getLogin());
				entityManager.remove(user);
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "Userloginrole");
	}
	
	public Boolean canAccess(String user, String password){
		log.info("tmpDEBUGtmp: " + "> " + "canAccess(" + user + ", " + password + ")");
		Boolean canAccess = false;
		try{
			try{
				utx.begin();
				// String sql = "select count(u) FROM Userloginrole u Where u.login = :user and u.password = :password";
				// Query query = entityManager.createQuery(sql);
				Query query = entityManager.createNamedQuery("Userloginrole.countByLoginPsw");
				query.setParameter("user", user);
				query.setParameter("password", password);
				canAccess = ((((Long)query.getSingleResult()) == 1) ? true : false);
				if(canAccess){
					MDC.put("user-id", user);
					MDC.put("user-ipAdd", "1.2.3.4");
				}
			}catch(NoResultException e){
				canAccess = false;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "canAccess");
		return canAccess;
	}
	
	public String getNomeDaUser(String username){
		log.info("tmpDEBUGtmp: " + "> " + "getNomeDaUser(" + username + ")");
		Users user = new Users();
		try{
			try{
				utx.begin();
				Query query = entityManager.createNamedQuery("Users.findByLogin", Users.class);
				query.setParameter("username", username);
				user = (Users)query.getSingleResult();
			}catch(NoResultException e){
				user = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "getNomeDaUser");
		return (user == null) ? "" : user.getNome();
	}	
	
	public String getCognomeDaUser(String username){
		log.info("tmpDEBUGtmp: " + "> " + "getCognomeDaUser(" + username + ")");
		Users user = new Users();
		try{
			try{
				utx.begin();
				Query query = entityManager.createNamedQuery("Users.findByLogin", Users.class);
				query.setParameter("username", username);
				user = (Users)query.getSingleResult();
			}catch(NoResultException e){
				user = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "getCognomeDaUser");
		return (user == null) ? "" : user.getCognome();
	}
	
	public List<Usersroles> getRolesDaUser(String username){
		log.info("tmpDEBUGtmp: " + "> " + "getRolesDaUser(" + username + ")");
		List<Usersroles> roles = null;
		try{
			try{
				utx.begin();
				Query query = entityManager.createNamedQuery("Usersroles.findByUserName", Usersroles.class);
				query.setParameter("username", username);
				roles = (List<Usersroles>)query.getResultList();
			}catch(NoResultException e){
				roles = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "getRolesDaUser");
		return roles;
	}
	
	public List<Userspermissions> getPermissionsDaUser(String username){
		log.info("tmpDEBUGtmp: " + "> " + "getPermissionsDaUser(" + username + ")");
		List<Userspermissions> permissions = null;
		try{
			try{
				utx.begin();
				Query query = entityManager.createNamedQuery("Userspermissions.findByUserName", Userspermissions.class);
				query.setParameter("username", username);
				permissions = (List<Userspermissions>)query.getResultList();
			}catch(NoResultException e){
				permissions = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "getPermissionsDaUser");
		return permissions;
	}
}

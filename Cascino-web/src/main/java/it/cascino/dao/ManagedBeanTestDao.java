package it.cascino.dao;

import java.util.Iterator;
import java.util.List;
import it.cascino.h8.entity.Foto;
import it.cascino.h8.entity.Test;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
//import org.hibernate.Query;

public class ManagedBeanTestDao implements TestDao{
	
	@Inject
	private EntityManager entityManager;
	
	@Inject
	private UserTransaction utx;
	
	public Test getA(int id){
		try{
			Test test;
			try{
				utx.begin();
				String sql = "from Test as t where t.id = :id";
				Query query = entityManager.createQuery(sql);
				query.setParameter("id", id);
				test = (Test)query.getSingleResult();
			}catch(NoResultException e){
				test = null;
			}
			utx.commit();
			return test;
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

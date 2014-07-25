package it.cascino.managmentbean;

import java.io.Serializable;
import java.util.List;
import it.cascino.dao.TipiDao;
import it.cascino.model.Foto;
import it.cascino.model.Tipi;
import it.cascino.util.Utility;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;
import org.primefaces.model.TreeNode;

@SessionScoped
public class TipiDaoManBean implements TipiDao, Serializable{
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
	public List<Tipi> getAll(){
		List<Tipi> tipi = null;
		try{
			try{
				utx.begin();
				// String sql = "FROM Tipi t";
				// Query query = entityManager.createQuery(sql);
				Query query = entityManager.createNamedQuery("Tipi.findAll");
				tipi = (List<Tipi>)query.getResultList();
			}catch(NoResultException e){
				tipi = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return tipi;
	}
	
	public void salva(TreeNode nodo){
		Tipi tipo = (Tipi)nodo.getData();
		
		try{
			try{
				utx.begin();
				log.info("transaction:" + " " + utx.getStatus());
				tipo.setId(null);
				log.info("salva: " + tipo.getId() + ", " + tipo.getNome() + ", " + tipo.getDescrizione() + ", " + tipo.getTipoPadre());
				entityManager.persist(tipo);
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
	}
	
	public void aggiorna(TreeNode nodo){
		Tipi tipo = (Tipi)nodo.getData();		
		try{
			try{
				utx.begin();
				log.info("aggiorna: " + tipo.getId() + ", " + tipo.getNome() + ", " + tipo.getDescrizione() + ", " + tipo.getTipoPadre());
				entityManager.merge(tipo);
			}finally{
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
	}
	
	public void elimina(TreeNode nodo){
		Tipi tipoElimina = (Tipi)nodo.getData();		
		try{
			try{
				utx.begin();
				Tipi tipo = entityManager.find(Tipi.class, tipoElimina.getId());
				log.info("elimina: " + tipo.getId() + ", " + tipo.getNome() + ", " + tipo.getDescrizione() + ", " + tipo.getTipoPadre());
				entityManager.remove(tipo);
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
	}
	
	public Foto getFoto(Integer id){ // TreeNode nodo){
		// Tipi tipo = (Tipi)nodo.getData();
		Foto foto = null;
		try{
			try{
				utx.begin();
				String sql = "select * from foto " +
				"where id = ( " +
				"select foto " +
				"from tipi t " +
				"where t.id = :id)";
				Query query = entityManager.createNativeQuery(sql, Foto.class); // Native
				// Query query = entityManager.createNamedQuery("Foto.findByIdTipo", Foto.class);
				query.setParameter("id", id); // tipo.getId());
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
	
	// public Foto getFotoPadre(TreeNode nodo){
	// Tipi tipo = (Tipi)nodo.getData();
	// Foto foto;
	// try{
	// try{
	// utx.begin();
	// String sql = "select * from foto " +
	// "where id = ( " +
	// "select foto " +
	// "from tipi t " +
	// "where t.id = :id)";
	// Query query = entityManager.createNativeQuery(sql, Foto.class); // Native
	// query.setParameter("id", tipo.getTipoPadre());
	// foto = (Foto)query.getSingleResult();
	// }catch(NoResultException e){
	// foto = null;
	// }
	// utx.commit();
	// }catch(Exception e){
	// try{
	// utx.rollback();
	// }catch(SystemException se){
	// throw new RuntimeException(se);
	// }
	// throw new RuntimeException(e);
	// }
	// return foto;
	// }
	
	public Foto getFotoDaArticolo(Integer idArticolo){
		Foto foto = null;
		try{
			try{
				utx.begin();
				String sql = "select * from foto " +
				"where id = ( " +
				"select foto " +
				"from tipi t join articoli a on t.id = a.tipo " +
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
				String sql = "select t.nome " +
				"from tipi t join articoli a on t.id = a.tipo " +
				"where a.id = :id";
				Query query = entityManager.createNativeQuery(sql); // Native
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
}

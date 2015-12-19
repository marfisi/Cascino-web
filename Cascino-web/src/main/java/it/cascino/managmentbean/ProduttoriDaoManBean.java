package it.cascino.managmentbean;

import java.io.Serializable;
import java.util.List;
import it.cascino.dao.ProduttoriDao;
import it.cascino.model.Produttori;
import it.cascino.util.DatabasePostgresqlDS;
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
	
	@DatabasePostgresqlDS
	@Inject
	private EntityManager entityManager;
	
	@Inject
	private UserTransaction utx;
	
	@SuppressWarnings("unchecked")
	public List<Produttori> getAll(){
		// log.info("tmpDEBUGtmp: " + "> " + "getAll(" + ")");
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
		// log.info("tmpDEBUGtmp: " + "< " + "getAll");
		return produttori;
	}
	
	public void salva(Produttori produttore){
		// log.info("tmpDEBUGtmp: " + "> " + "salva(" + produttore + ")");
		try{
			try{
				utx.begin();
				produttore.setId(null);
				log.info("salva: " + produttore.getId() + ", " + produttore.getNome() + ", " + produttore.getDati() + ", " + produttore.getIdFoto());
				entityManager.persist(produttore);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void aggiorna(Produttori produttore){
		// log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + produttore + ")");
		try{
			try{
				utx.begin();
				log.info("aggiorna: " + produttore.getId() + ", " + produttore.getNome() + ", " + produttore.getDati() + ", " + produttore.getIdFoto());
				entityManager.merge(produttore);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	public void elimina(Produttori produttoreElimina){
		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + produttoreElimina + ")");
		try{
			try{
				utx.begin();
				Produttori produttore = entityManager.find(Produttori.class, produttoreElimina.getId());
				log.info("elimina: " + produttore.getId() + ", " + produttore.getNome() + ", " + produttore.getDati() + ", " + produttore.getIdFoto());
				entityManager.remove(produttore);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	public String getNomeProduttoreDaIdArticolo(Integer idArticolo){
		// log.info("tmpDEBUGtmp: " + "> " + "getNomeProduttoreDaIdArticolo(" + idArticolo + ")");
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
		// log.info("tmpDEBUGtmp: " + "< " + "getNomeProduttoreDaIdArticolo");
		return nome;
	}
	
	public Produttori getProduttoreDaIdProduttore(Integer idProduttore){
		// log.info("tmpDEBUGtmp: " + "> " + "getProduttoreDaIdProduttore(" + idProduttore + ")");
		Produttori produttore = new Produttori();
		try{
			try{
				utx.begin();
				Query query = entityManager.createNamedQuery("Produttori.findById", Produttori.class);
				query.setParameter("id", idProduttore);
				produttore = (Produttori)query.getSingleResult();
			}catch(NoResultException e){
				produttore = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getProduttoreDaIdProduttore");
		return produttore;
	}	
}

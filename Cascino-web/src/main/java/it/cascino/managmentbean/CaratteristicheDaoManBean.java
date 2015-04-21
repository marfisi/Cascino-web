package it.cascino.managmentbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
import java.util.List;
//import java.util.Map;
//import java.util.NoSuchElementException;
import it.cascino.dao.CaratteristicheDao;
import it.cascino.model.Caratteristiche;
import it.cascino.model.Foto;
import it.cascino.util.Utility;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
//import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;

@SessionScoped
public class CaratteristicheDaoManBean implements CaratteristicheDao, Serializable{
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
	public List<Caratteristiche> getAll(){
		// log.info("tmpDEBUGtmp: " + "> " + "getAll(" + ")");
		List<Caratteristiche> caratteristica = null;
		try{
			try{
				utx.begin();
				Query query = entityManager.createNamedQuery("Caratteristiche.findAll");
				caratteristica = (List<Caratteristiche>)query.getResultList();
			}catch(NoResultException e){
				caratteristica = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getAll");
		return caratteristica;
	}
	
	public void salva(Caratteristiche caratteristica){
		// log.info("tmpDEBUGtmp: " + "> " + "salva(" + articolo + ", " + fotoPerArticolo + ")");
		try{
			try{
				utx.begin();
				log.info("salva: " + caratteristica.getId());
				caratteristica.setId(null);
				entityManager.persist(caratteristica);
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void aggiorna(Caratteristiche caratteristica){
		// log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + articolo + ", " + fotoPerArticolo + ")");
		try{
			try{
				utx.begin();
				log.info("aggiorna: " + caratteristica.getId());
				entityManager.merge(caratteristica);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	public void elimina(Caratteristiche caratteristicaElimina){
		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + articoloElimina + ")");
		try{
			try{
				utx.begin();
				Caratteristiche caratteristica = entityManager.find(Caratteristiche.class, caratteristicaElimina.getId());
				log.info("elimina: " + caratteristica.getId());
				entityManager.remove(caratteristica);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getCaratteristicheClasseAutoCompleteLs(String str){
		// log.info("tmpDEBUGtmp: " + "> " + "getArticoliAutoCompleteLs(" + str + ")");
		List<String> caratteristiche = null;
		try{
			try{
				utx.begin();
				String sql = "SELECT distinct(upper(c.classe)) FROM Caratteristiche c WHERE upper(c.classe) like :str";
				Query query = entityManager.createQuery(sql);
				query.setParameter("str", "%" + str.toUpperCase() + "%");
				caratteristiche = (List<String>)query.getResultList();
			}catch(NoResultException e){
				caratteristiche = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getArticoliAutoCompleteLs");
		return caratteristiche;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getCaratteristicheUnitaMisuraAutoCompleteLs(String str, String classe){
		log.info("tmpDEBUGtmp: " + "> " + "getCaratteristicheUnitaMisuraAutoCompleteLs(" + str + ", " + classe + ")");
		List<String> caratteristiche = null;
		try{
			try{
				utx.begin();
				String sql = "SELECT distinct(upper(c.unitaMisura)) FROM Caratteristiche c WHERE c.unitaMisura != '' and upper(c.classe) like :classe and upper(c.unitaMisura) like :str";
				Query query = entityManager.createQuery(sql);
				query.setParameter("classe", "%" + classe.toUpperCase().trim() + "%");
				query.setParameter("str", "%" + str.toUpperCase().trim() + "%");
				caratteristiche = (List<String>)query.getResultList();
			}catch(NoResultException e){
				caratteristiche = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getArticoliAutoCompleteLs");
		return caratteristiche;
	}
	
	public Caratteristiche getCaratteristicaPerId(Integer idCaratteristica){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotoListPerSel(" + f + ")");
		Caratteristiche caratteristica = null;
		try{
			try{
				utx.begin();
				Query query = entityManager.createNamedQuery("Caratteristiche.findById", Caratteristiche.class);
				query.setParameter("id", idCaratteristica);
				caratteristica = (Caratteristiche)query.getSingleResult();
			}catch(NoResultException e){
				caratteristica = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getFotoListPerSel");
		return caratteristica;
	}
	
	@SuppressWarnings("unchecked")
	public List<Caratteristiche> getCaratteristicheListPerTipo(Integer idTipo){
		List<Caratteristiche> caratteristicheLs = null;
		try{
			try{
				utx.begin();
//				String sql = "select * " +
//				"from caratteristiche " +
//				"where articolo in (select id " +
//				"from articoli " +
//				"where tipo = :id) " +
//				"order by classe asc, unita_misura asc, scala*qty asc, valore asc, descrizione asc";
				String sql = "select min(id) id, min(articolo) articolo, classe, unita_misura, min(scala) scala, min(qty) qty, min(valore) valore, min(descrizione) descrizione, min(updtime) updtime " +
				"from caratteristiche " +
				"where articolo in (select id " +
				"from articoli " +
				"where tipo = :id) " +
				"group by classe, unita_misura " +
				"order by classe asc, unita_misura asc";
				Query query = entityManager.createNativeQuery(sql, Caratteristiche.class);
				query.setParameter("id", idTipo);
				caratteristicheLs = (List<Caratteristiche>)query.getResultList();
			}catch(NoResultException e){
				caratteristicheLs = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return caratteristicheLs;
	}
	
	@SuppressWarnings("unchecked")
	public List<Caratteristiche> getCaratteristicheListPerArticolo(Integer idArticolo){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotoListPerSel(" + f + ")");
		List<Caratteristiche> caratteristicheLs = null;
		try{
			try{
				utx.begin();
				Query query = entityManager.createNamedQuery("Caratteristiche.findByIdArticolo", Caratteristiche.class);
				query.setParameter("idArticolo", idArticolo);
				caratteristicheLs = (List<Caratteristiche>)query.getResultList();
			}catch(NoResultException e){
				caratteristicheLs = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getFotoListPerSel");
		return caratteristicheLs;
	}
	
//	@SuppressWarnings("unchecked")
//	public List<Caratteristiche> getCaratteristicheListPerArticoliSimili(Integer idArticolo){
//		List<Caratteristiche> caratteristicheLs = null;
//		try{
//			try{
//				utx.begin();
//				String sql = "select * " +
//				"from caratteristiche " +
//				"where articolo in (select distinct(articolo) " +
//				"from articoli_foto " +
//				"where foto in (select foto " +
//				"from articoli_foto " +
//				"where articolo = :idArticolo)) " +
//				"order by classe asc, unita_misura asc, scala*qty asc, valore asc, descrizione asc";
//				Query query = entityManager.createNativeQuery(sql, Caratteristiche.class);
//				query.setParameter("idArticolo", idArticolo);
//				caratteristicheLs = (List<Caratteristiche>)query.getResultList();
//			}catch(NoResultException e){
//				caratteristicheLs = null;
//			}
//			utx.commit();
//		}catch(Exception e){
//			Utility.manageException(e, utx, log);
//		}
//		return caratteristicheLs;
//	}
	
	@SuppressWarnings("unchecked")
	public List<Caratteristiche> getCaratteristicheListPerArticoliSimili(List<Foto> fotoPerArticoloLs){
		List<Caratteristiche> caratteristicheLs = null;
		try{
			try{
				utx.begin();
				String sql = "select * " +
				"from caratteristiche " +
				"where articolo in (select distinct(articolo) " +
				"from articoli_foto " +
				"where foto in (:idFoto)) " +
				"order by classe asc, unita_misura asc, scala*qty asc, valore asc, descrizione asc";
				Query query = entityManager.createNativeQuery(sql, Caratteristiche.class);
				List<Integer> idFoto = new ArrayList<Integer>();
				Iterator<Foto> iterator = fotoPerArticoloLs.iterator();
				Foto o = null;
				while(iterator.hasNext()){
					o = iterator.next();
					idFoto.add(o.getId());
				}
				query.setParameter("idFoto", (idFoto.isEmpty()?0:idFoto));
				caratteristicheLs = (List<Caratteristiche>)query.getResultList();
			}catch(NoResultException e){
				caratteristicheLs = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return caratteristicheLs;
	}
	
}

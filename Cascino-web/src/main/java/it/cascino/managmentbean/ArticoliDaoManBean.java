package it.cascino.managmentbean;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
//import java.util.Map;
import java.util.NoSuchElementException;
import it.cascino.dao.ArticoliDao;
import it.cascino.model.Articoli;
import it.cascino.model.ArticoliAllegati;
import it.cascino.model.ArticoliFoto;
import it.cascino.model.Caratteristiche;
import it.cascino.model.Foto;
import it.cascino.model.Allegati;
//import it.cascino.model.Produttori;
import it.cascino.util.Utility;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

@SessionScoped
public class ArticoliDaoManBean implements ArticoliDao, Serializable{
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
	public List<Articoli> getAll(){
		// log.info("tmpDEBUGtmp: " + "> " + "getAll(" + ")");
		List<Articoli> articoli = null;
		try{
			try{
				utx.begin();
				Query query = entityManager.createNamedQuery("Articoli.findAll");
				articoli = (List<Articoli>)query.getResultList();
			}catch(NoResultException e){
				articoli = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getAll");
		return articoli;
	}
	
	public void salva(Articoli articolo, List<Foto> fotoPerArticolo, List<Caratteristiche> caratteristichePerArticolo, List<Allegati> allegatiPerArticolo){
		try{
			try{
				utx.begin();
				log.info("transaction:" + " " + utx.getStatus());
				articolo.setId(null);
				log.info("salva: " + articolo.getId() + ", " + articolo.getCodice() + ", " + articolo.getNome() + ", " + articolo.getDescrizione());
				entityManager.persist(articolo);
				// per la lista di foto, aggiorno articoli_foto	
				try{
					if(!manageArticoliFoto(articolo.getId(), fotoPerArticolo)){
						// entityManager.clear();
						utx.rollback();
					}
				}catch(Exception e){
					utx.rollback();
					Utility.manageException(e, utx, log);
				}
				try{
					if(!manageArticoliCaratteristiche(articolo.getId(), caratteristichePerArticolo)){
						utx.rollback();
					}
				}catch(Exception e){
					utx.rollback();
					Utility.manageException(e, utx, log);
				}
				try{
					if(!manageArticoliAllegati(articolo.getId(), allegatiPerArticolo)){
						utx.rollback();
					}
				}catch(Exception e){
					utx.rollback();
					Utility.manageException(e, utx, log);
				}
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
	}
	
	public void aggiorna(Articoli articolo, List<Foto> fotoPerArticolo, List<Caratteristiche> caratteristichePerArticolo, List<Allegati> allegatiPerArticolo){
		// log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + articolo + ", " + fotoPerArticolo + ", " + caratteristichePerArticolo + ")");
		try{
			try{
				utx.begin();
				log.info("transaction:" + " " + utx.getStatus());
				log.info("aggiorna: " + articolo.getId() + ", " + articolo.getCodice() + ", " + articolo.getNome() + ", " + articolo.getDescrizione());
				entityManager.merge(articolo);
				// per la lista di foto, aggiorno articoli_foto	
				try{
					if(!manageArticoliFoto(articolo.getId(), fotoPerArticolo)){
						// entityManager.clear();
						utx.rollback();
					}
				}catch(Exception e){
					utx.rollback();
					Utility.manageException(e, utx, log);
				}
				try{
					if(!manageArticoliCaratteristiche(articolo.getId(), caratteristichePerArticolo)){
						utx.rollback();
					}
				}catch(Exception e){
					utx.rollback();
					Utility.manageException(e, utx, log);
				}
				try{
					if(!manageArticoliAllegati(articolo.getId(), allegatiPerArticolo)){
						utx.rollback();
					}
				}catch(Exception e){
					utx.rollback();
					Utility.manageException(e, utx, log);
				}
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
		
	public void elimina(Articoli articoloElimina){
		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + articoloElimina + ")");
		try{
			try{
				utx.begin();
				log.info("transaction:" + " " + utx.getStatus());
				Articoli articolo = entityManager.find(Articoli.class, articoloElimina.getId());
				log.info("elimina: " + articolo.getId() + ", " + articolo.getCodice() + ", " + articolo.getNome() + ", " + articolo.getDescrizione());
				// per la lista di foto, aggiorno articoli_foto	
				try{
					if(!manageArticoliFoto(articolo.getId(), new ArrayList<Foto>())){ // passo fotoPerArticolo = new ArrayList<Foto>(), per ottenere idFotoDaNonEliminare vuoto, quindi cancellare tutti i riferimenti
						// entityManager.clear();
						utx.rollback();
					}
				}catch(Exception e){
					utx.rollback();
					Utility.manageException(e, utx, log);
				}
				try{
					if(!manageArticoliCaratteristiche(articolo.getId(), new ArrayList<Caratteristiche>())){ // passo caratteristichePerArticolo = new ArrayList<Caratteristiche>(), per ottenere idCaratDaNonEliminare vuoto, quindi cancellare tutti i riferimenti
						utx.rollback();
					}
				}catch(Exception e){
					utx.rollback();
					Utility.manageException(e, utx, log);
				}
				try{
					if(!manageArticoliAllegati(articolo.getId(), new ArrayList<Allegati>())){ // passo allegatiPerArticolo = new ArrayList<Allegati>(), per ottenere idAllegatiDaNonEliminare vuoto, quindi cancellare tutti i riferimenti
						utx.rollback();
					}
				}catch(Exception e){
					utx.rollback();
					Utility.manageException(e, utx, log);
				}
				log.info("transaction:" + " " + utx.getStatus());
				entityManager.remove(articolo);
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	@SuppressWarnings("unchecked")
	private Boolean manageArticoliFoto(Integer idArticolo, List<Foto> fotoPerArticolo) throws SystemException{
		// log.info("tmpDEBUGtmp: " + "> " + "manageArticoliFoto(" + idArticolo + ", " + fotoPerArticolo + ")");
		ArticoliFoto articoloFoto = null;
		Foto o = null;
		log.info("transaction:" + " " + utx.getStatus());
		Iterator<Foto> iterator = fotoPerArticolo.iterator();
		
		// elimino le entry in tabella che non sono piu' in essere
		String sql = "select * "
		+ "from articoli_foto a "
		+ "where a.articolo = :id "
		+ "and a.foto not in (:idFotoDaNonEliminare)";
		Query query = entityManager.createNativeQuery(sql, ArticoliFoto.class);
		query.setParameter("id", idArticolo);
		List<Integer> idFotoDaNonEliminare = new ArrayList<Integer>();
		while(iterator.hasNext()){
			o = iterator.next();
			idFotoDaNonEliminare.add(o.getId());
		}
		query.setParameter("idFotoDaNonEliminare", (idFotoDaNonEliminare.isEmpty() ? 0 : idFotoDaNonEliminare));
		
		List<ArticoliFoto> articoliFotoDaEliminare = new ArrayList<ArticoliFoto>();
		try{
			articoliFotoDaEliminare = (List<ArticoliFoto>)query.getResultList();
		}catch(NoResultException e){
			articoliFotoDaEliminare = null;
		}catch(Exception e){
			articoliFotoDaEliminare = null;
		}
		if(!articoliFotoDaEliminare.isEmpty()){
			Iterator<ArticoliFoto> iteratorRem = articoliFotoDaEliminare.iterator();
			ArticoliFoto af = null;
			// Object tmp = null;
			while(iteratorRem.hasNext()){
				try{
					// tmp = iteratorRem.next();
					af = iteratorRem.next();
					// af = new ArticoliFoto(null, null, null, null, null, null); // null;
				}catch(NoSuchElementException e){
					Utility.manageException(e, utx, log);
				}catch(Exception e){
					Utility.manageException(e, utx, log);
				}
				log.info("elimina: " + af.getId() + ", " + af.getArticolo() + ", " + af.getFoto() + ", " + af.getOrdinamento());
				entityManager.remove(af);
			}
		}
		
		// dopo aver eliminato, gestisco le aggiunte e modifiche
		iterator = fotoPerArticolo.iterator();
		Integer ordinamento = 0;
		query = entityManager.createNamedQuery("ArticoliFoto.findByIdArtIdFotoOrd", ArticoliFoto.class);
		query.setParameter("idArt", idArticolo);
		while(iterator.hasNext()){
			o = iterator.next();
			ordinamento++;
			
			try{
				query.setParameter("idFoto", o.getId());
				query.setParameter("ord", ordinamento);
				articoloFoto = (ArticoliFoto)query.getSingleResult();
			}catch(NoResultException e){
				articoloFoto = null;
			}catch(Exception e){
				articoloFoto = null;
			}
			
			// se trovo corrispondenza, significa che non e' cambiato nulla tra la lista e come gia' e' salvato, posso passare all'elemento successivo
			if(articoloFoto != null){
				log.info("articolo " + articoloFoto.getArticolo() + ", foto " + articoloFoto.getFoto() + ", ordinamento " + articoloFoto.getOrdinamento() + " gia' memorizzata");
				continue; // prossimo giro while
			}
			
			// se non trovo corrispondenza, allora o e' cambiato qualche dato tra foto e/o ordinamento o e' stata aggiunta una foto o eliminata
			
			query = entityManager.createNamedQuery("ArticoliFoto.findByIdArtIdFoto", ArticoliFoto.class);
			query.setParameter("idArt", idArticolo);
			
			try{
				query.setParameter("idFoto", o.getId());
				articoloFoto = (ArticoliFoto)query.getSingleResult();
			}catch(NoResultException e){
				articoloFoto = null;
			}catch(Exception e){
				articoloFoto = null;
			}
			
			// se la trovo significa che e' cambiato solo l'ordine e faccio update
			if(articoloFoto != null){
				log.info("articolo " + articoloFoto.getArticolo() + ", foto " + articoloFoto.getFoto() + ", ordinamento (da " + articoloFoto.getOrdinamento() + " a " + ordinamento + ")");
				articoloFoto.setOrdinamento(ordinamento);
				entityManager.merge(articoloFoto);
				continue; // prossimo giro while
			}
			
			// se non la trovo significa che e' da aggiungere (insert)
			articoloFoto = new ArticoliFoto(null, idArticolo, null, o.getId(), ordinamento, null);
			entityManager.persist(articoloFoto);
			log.info("articolo " + articoloFoto.getArticolo() + ", foto " + articoloFoto.getFoto() + ", ordinamento " + articoloFoto.getOrdinamento() + " aggiunto");
		}
		
		log.info("transaction:" + " " + utx.getStatus());
		return true;
	}
	
	@SuppressWarnings("unchecked")
	private Boolean manageArticoliCaratteristiche(Integer idArticolo, List<Caratteristiche> caratteristichePerArticolo) throws SystemException{
		Caratteristiche o = null;
		log.info("transaction:" + " " + utx.getStatus());
		Iterator<Caratteristiche> iterator = caratteristichePerArticolo.iterator();
		
		// elimino le entry in tabella che non sono piu' in essere
		String sql = "select * "
		+ "from caratteristiche c "
		+ "where c.articolo = :id "
		+ "and c.id not in (:idCaratDaNonEliminare)";
		Query query = entityManager.createNativeQuery(sql, Caratteristiche.class);
		query.setParameter("id", idArticolo);
		List<Integer> idCaratDaNonEliminare = new ArrayList<Integer>();
		while(iterator.hasNext()){
			o = iterator.next();
			idCaratDaNonEliminare.add(o.getId());
		}
		query.setParameter("idCaratDaNonEliminare", (idCaratDaNonEliminare.isEmpty() ? 0 : idCaratDaNonEliminare));
		
		List<Caratteristiche> caratteristicheDaEliminare = new ArrayList<Caratteristiche>();
		try{
			caratteristicheDaEliminare = (List<Caratteristiche>)query.getResultList();
		}catch(NoResultException e){
			caratteristicheDaEliminare = null;
		}catch(Exception e){
			caratteristicheDaEliminare = null;
		}
		if(!caratteristicheDaEliminare.isEmpty()){
			Iterator<Caratteristiche> iteratorRem = caratteristicheDaEliminare.iterator();
			Caratteristiche c = null;
			while(iteratorRem.hasNext()){
				try{
					c = iteratorRem.next();
				}catch(NoSuchElementException e){
					Utility.manageException(e, utx, log);
				}catch(Exception e){
					Utility.manageException(e, utx, log);
				}
				log.info("elimina: " + c.getId() + ", " + c.getArticolo() + ", " + c.getClasse() + ", " + c.getUnitaMisura());
				entityManager.remove(c);
			}
		}
		
		// dopo aver eliminato, gestisco le aggiunte e modifiche
		iterator = caratteristichePerArticolo.iterator();
		while(iterator.hasNext()){
			o = iterator.next();
			
			if(o.getId() == 0){
				o.setId(null);
			}
			o.setArticolo(idArticolo);
			entityManager.merge(o);
			log.info("articolo " + o.getArticolo() + ", " + o.toString() + " aggiunto");
		}
		
		log.info("transaction:" + " " + utx.getStatus());
		return true;
	}
	
	@SuppressWarnings("unchecked")
	private Boolean manageArticoliAllegati(Integer idArticolo, List<Allegati> allegatiPerArticolo) throws SystemException{
		ArticoliAllegati articoliAllegati = null;
		Allegati o = null;
		log.info("transaction:" + " " + utx.getStatus());
		Iterator<Allegati> iterator = allegatiPerArticolo.iterator();
		
		// elimino le entry in tabella che non sono piu' in essere
		String sql = "select * "
		+ "from articoli_allegati a "
		+ "where a.articolo = :id "
		+ "and a.allegato not in (:idAllegatiDaNonEliminare)";
		Query query = entityManager.createNativeQuery(sql, ArticoliFoto.class);
		query.setParameter("id", idArticolo);
		List<Integer> idAllegatiDaNonEliminare = new ArrayList<Integer>();
		while(iterator.hasNext()){
			o = iterator.next();
			idAllegatiDaNonEliminare.add(o.getId());
		}
		query.setParameter("idAllegatiDaNonEliminare", (idAllegatiDaNonEliminare.isEmpty() ? 0 : idAllegatiDaNonEliminare));
		
		List<ArticoliAllegati> articoliAllegatiDaEliminare = new ArrayList<ArticoliAllegati>();
		try{
			articoliAllegatiDaEliminare = (List<ArticoliAllegati>)query.getResultList();
		}catch(NoResultException e){
			articoliAllegatiDaEliminare = null;
		}catch(Exception e){
			articoliAllegatiDaEliminare = null;
		}
		if(!articoliAllegatiDaEliminare.isEmpty()){
			Iterator<ArticoliAllegati> iteratorRem = articoliAllegatiDaEliminare.iterator();
			ArticoliAllegati aa = null;
			// Object tmp = null;
			while(iteratorRem.hasNext()){
				try{
					// tmp = iteratorRem.next();
					aa = iteratorRem.next();
				}catch(NoSuchElementException e){
					Utility.manageException(e, utx, log);
				}catch(Exception e){
					Utility.manageException(e, utx, log);
				}
				log.info("elimina: " + aa.getId() + ", " + aa.getArticolo() + ", " + aa.getAllegato() + ", " + aa.getOrdinamento());
				entityManager.remove(aa);
			}
		}
		
		// dopo aver eliminato, gestisco le aggiunte e modifiche
		iterator = allegatiPerArticolo.iterator();
		Integer ordinamento = 0;
		query = entityManager.createNamedQuery("ArticoliAllegati.findByIdArtIdAllegatoOrd", ArticoliAllegati.class);
		query.setParameter("idArt", idArticolo);
		while(iterator.hasNext()){
			o = iterator.next();
			ordinamento++;
			
			try{
				query.setParameter("idAllegato", o.getId());
				query.setParameter("ord", ordinamento);
				articoliAllegati = (ArticoliAllegati)query.getSingleResult();
			}catch(NoResultException e){
				articoliAllegati = null;
			}catch(Exception e){
				articoliAllegati = null;
			}
			
			// se trovo corrispondenza, significa che non e' cambiato nulla tra la lista e come gia' e' salvato, posso passare all'elemento successivo
			if(articoliAllegati != null){
				log.info("articolo " + articoliAllegati.getArticolo() + ", allegato " + articoliAllegati.getAllegato() + ", ordinamento " + articoliAllegati.getOrdinamento() + " gia' memorizzata");
				continue; // prossimo giro while
			}
			
			// se non trovo corrispondenza, allora o e' cambiato qualche dato tra allegato e/o ordinamento o e' stata aggiunta un allegato o eliminato
			
			query = entityManager.createNamedQuery("ArticoliAllegati.findByIdArtIdAllegato", ArticoliAllegati.class);
			query.setParameter("idArt", idArticolo);
			
			try{
				query.setParameter("idAllegato", o.getId());
				articoliAllegati = (ArticoliAllegati)query.getSingleResult();
			}catch(NoResultException e){
				articoliAllegati = null;
			}catch(Exception e){
				articoliAllegati = null;
			}
			
			// se la trovo significa che e' cambiato solo l'ordine e faccio update
			if(articoliAllegati != null){
				log.info("articolo " + articoliAllegati.getArticolo() + ", allegato " + articoliAllegati.getAllegato() + ", ordinamento (da " + articoliAllegati.getOrdinamento() + " a " + ordinamento + ")");
				articoliAllegati.setOrdinamento(ordinamento);
				entityManager.merge(articoliAllegati);
				continue; // prossimo giro while
			}
			
			// se non la trovo significa che e' da aggiungere (insert)
			articoliAllegati = new ArticoliAllegati(null, idArticolo, null, o.getId(), ordinamento, null);
			entityManager.persist(articoliAllegati);
			log.info("articolo " + articoliAllegati.getArticolo() + ", allegato " + articoliAllegati.getAllegato() + ", ordinamento " + articoliAllegati.getOrdinamento() + " aggiunto");
		}
		
		log.info("transaction:" + " " + utx.getStatus());
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getArticoliAutoCompleteLs(String str){
		// log.info("tmpDEBUGtmp: " + "> " + "getArticoliAutoCompleteLs(" + str + ")");
		List<String> articoli = null;
		try{
			try{
				utx.begin();
				String sql = "SELECT upper(a.codice) FROM Articoli a WHERE upper(a.codice) like :str";
				Query query = entityManager.createQuery(sql);
				query.setParameter("str", "%" + str.toUpperCase() + "%");
				articoli = (List<String>)query.getResultList();
			}catch(NoResultException e){
				articoli = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getArticoliAutoCompleteLs");
		return articoli;
	}
	
	// @SuppressWarnings("unchecked")
	// public List<String> getProduttoriAutoCompleteLs(String str){
	// // log.info("tmpDEBUGtmp: " + "> " + "getProduttoriAutoCompleteLs(" + str + ")");
	// List<String> articoli = null;
	// try{
	// try{
	// utx.begin();
	// String sql = "SELECT upper(p.nome) FROM Produttori p WHERE upper(p.nome) like upper(:str)";
	// Query query = entityManager.createQuery(sql);
	// query.setParameter("str", "%" + str + "%");
	// articoli = (List<String>)query.getResultList();
	// }catch(NoResultException e){
	// articoli = null;
	// }
	// utx.commit();
	// }catch(Exception e){
	// Utility.manageException(e, utx, log);
	// }
	// // log.info("tmpDEBUGtmp: " + "< " + "getProduttoriAutoCompleteLs");
	// return articoli;
	// }
	
	public Articoli getArticoloDaIdArticolo(Integer idArticolo){
		Articoli articolo = null;
		try{
			try{
				utx.begin();
				Query query = entityManager.createNamedQuery("Articoli.findById");
				query.setParameter("id", idArticolo);
				articolo = (Articoli)query.getSingleResult();
			}catch(NoResultException e){
				articolo = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return articolo;
	}

	public Articoli getArticoloDaCodiceArticolo(String codiceArticolo){
		Articoli articolo = null;
		try{
			try{
				utx.begin();
				Query query = entityManager.createNamedQuery("Articoli.findByCodice");
				query.setParameter("codice", codiceArticolo);
				articolo = (Articoli)query.getSingleResult();
			}catch(NoResultException e){
				articolo = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return articolo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Articoli> getArticoliFratelliLsDaCodiceFoto(Integer idFoto){
		List<Articoli> articoli = null;
		try{
			try{
				utx.begin();
				String sql = "select a.* " +
				"from articoli a inner join articoli_foto af on a.id = af.articolo " +
				"where af.foto = :idFoto";
				Query query = entityManager.createNativeQuery(sql, Articoli.class);
				query.setParameter("idFoto", idFoto);
				articoli = (List<Articoli>)query.getResultList();
			}catch(NoResultException e){
				articoli = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return articoli;
	}
	
	@SuppressWarnings("unchecked")
	public List<Articoli> getArticoliSuccessiviLsDaCodiceFoto(String codiceArticolo, Integer size){
		List<Articoli> articoli = null;
		if(codiceArticolo.isEmpty()){
			return articoli;
		}
		try{
			try{
				utx.begin();
				String sql = "select * " +
				"from articoli " +
				"where codice >= :codice " +
				"order by codice";
				Query query = entityManager.createNativeQuery(sql, Articoli.class);
				query.setParameter("codice", StringUtils.upperCase(codiceArticolo));
				articoli = (List<Articoli>)query.getResultList();
				if(articoli.size() > size){
					articoli = articoli.subList(0, size);
				}
			}catch(NoResultException e){
				articoli = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return articoli;
	}
}

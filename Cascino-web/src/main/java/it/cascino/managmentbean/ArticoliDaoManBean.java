package it.cascino.managmentbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import it.cascino.dao.ArticoliDao;
import it.cascino.model.Articoli;
import it.cascino.model.ArticoliFoto;
import it.cascino.model.Foto;
import it.cascino.model.Produttori;
import it.cascino.util.Utility;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
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
		log.info("tmpDEBUGtmp: " + "> " + "getAll(" + ")");
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
		log.info("tmpDEBUGtmp: " + "< " + "getAll");
		return articoli;
	}
	
	public void salva(Articoli articolo){
		log.info("tmpDEBUGtmp: " + "> " + "salva(" + articolo + ")");
		try{
			try{
				utx.begin();
				log.info("transaction:" + " " + utx.getStatus());
				articolo.setId(null);
				log.info("salva: " + articolo.getId() + ", " + articolo.getCodice() + ", " + articolo.getNome() + ", " + articolo.getDescrizione());
				entityManager.persist(articolo);
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void aggiorna(Articoli articolo, List<Foto> fotoPLtarget){
		log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + articolo + ", " + fotoPLtarget + ")");
		try{
			try{
				utx.begin();
				log.info("transaction:" + " " + utx.getStatus());
				log.info("aggiorna: " + articolo.getId() + ", " + articolo.getCodice() + ", " + articolo.getNome() + ", " + articolo.getDescrizione());
				entityManager.merge(articolo);
				// per la lista di foto, aggiorno articoli_foto
				if(!manageArticoliFoto(articolo.getId(), fotoPLtarget)){
					// entityManager.clear();
					utx.rollback();
				}
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
	
	private Boolean manageArticoliFoto(Integer idArticolo, List<Foto> fotoPLtarget){
		log.info("tmpDEBUGtmp: " + "> " + "manageArticoliFoto(" + idArticolo + ", " + fotoPLtarget + ")");
		ArticoliFoto articoloFoto = null;
		Foto o = null;
		try{
			try{
				// utx.begin();
				log.info("transaction:" + " " + utx.getStatus());
				Iterator<Foto> iterator = fotoPLtarget.iterator();
				
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
				query.setParameter("idFotoDaNonEliminare", idFotoDaNonEliminare);
				
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
				iterator = fotoPLtarget.iterator();
				Integer ordinamento = 0;
				query = entityManager.createNamedQuery("ArticoliFoto.findByIDArtIdFotoOrd", ArticoliFoto.class);
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
					}
					
					// se trovo corrispondenza, significa che non e' cambiato nulla tra la lista e come gia' e' salvato, posso passare all'elemento successivo
					if(articoloFoto != null){
						log.info("articolo " + articoloFoto.getArticolo() + ", foto " + articoloFoto.getFoto() + ", ordinamento " + articoloFoto.getOrdinamento() + " gia' memorizzata");
						continue; // prossimo giro while
					}
					
					// se non trovo corrispondenza, allora o e' cambiato qaulche dato tra foto e/o ordinamento o e' stata aggiunta una foto o eliminata
					
					query = entityManager.createNamedQuery("ArticoliFoto.findByIDArtIdFoto", ArticoliFoto.class);
					query.setParameter("idArt", idArticolo);
					
					try{
						query.setParameter("idFoto", o.getId());
						articoloFoto = (ArticoliFoto)query.getSingleResult();
					}catch(NoResultException e){
						articoloFoto = null;
					}
					
					// se la trovo significa che e' cambiato solo l'ordine e faccio update
					if(articoloFoto != null){
						articoloFoto.setOrdinamento(ordinamento);
						entityManager.merge(articoloFoto);
						log.info("articolo " + articoloFoto.getArticolo() + ", foto " + articoloFoto.getFoto() + ", ordinamento (da " + articoloFoto.getOrdinamento() + " a " + ordinamento + ")");
						continue; // prossimo giro while
					}
					
					// se non la trovo significa che e' da aggiungere (insert)
					articoloFoto = new ArticoliFoto(null, idArticolo, null, o.getId(), ordinamento, null);
					entityManager.persist(articoloFoto);
					log.info("articolo " + articoloFoto.getArticolo() + ", foto " + articoloFoto.getFoto() + ", ordinamento " + articoloFoto.getOrdinamento() + " aggiunto");
				}
				
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "manageArticoliFoto");
		return true;
	}
	
	public void elimina(Articoli articoloElimina){
		log.info("tmpDEBUGtmp: " + "> " + "elimina(" + articoloElimina + ")");
		try{
			try{
				utx.begin();
				Articoli articolo = entityManager.find(Articoli.class, articoloElimina.getId());
				log.info("elimina: " + articolo.getId() + ", " + articolo.getCodice() + ", " + articolo.getNome() + ", " + articolo.getDescrizione());
				entityManager.remove(articolo);
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getArticoliAutoCompleteLs(String str){
		log.info("tmpDEBUGtmp: " + "> " + "getArticoliAutoCompleteLs(" + str + ")");
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
		log.info("tmpDEBUGtmp: " + "< " + "getArticoliAutoCompleteLs");
		return articoli;
	}
	
	// @SuppressWarnings("unchecked")
	// public List<String> getProduttoriAutoCompleteLs(String str){
	// log.info("tmpDEBUGtmp: " + "> " + "getProduttoriAutoCompleteLs(" + str + ")");
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
	// log.info("tmpDEBUGtmp: " + "< " + "getProduttoriAutoCompleteLs");
	// return articoli;
	// }
}

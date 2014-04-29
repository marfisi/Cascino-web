package it.cascino.controller;

import it.cascino.dao.TipiTreeDao;
import it.cascino.model.Tipi;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named
@SessionScoped
public class TipiTreeController implements Serializable{
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
	private FacesContext facesContext;
	
	@Inject
	private TipiTreeDao tipiTreeDao;
	
	private String esito;
	
	private TreeNode albero;
	
	private TreeNode nodoSel;
	
	private List<Tipi> tipiLs;
	
	public List<Tipi> getTipiLs(){
		return tipiLs;
	}
	
	public void setTipiLs(List<Tipi> tipiLs){
		this.tipiLs = tipiLs;
	}
	
	// public TipiTreeController(){
	// // popolo tipiLs
	// //getTipiLs();
	//
	// root = new DefaultTreeNode("Root", null);
	// TreeNode node0 = new DefaultTreeNode("Node 0", root);
	// TreeNode node1 = new DefaultTreeNode("Node 1", root);
	// TreeNode node2 = new DefaultTreeNode("Node 2", root);
	//
	// TreeNode node00 = new DefaultTreeNode("Node 0.0", node0);
	// TreeNode node01 = new DefaultTreeNode("Node 0.1", node0);
	//
	// TreeNode node10 = new DefaultTreeNode("Node 1.0", node1);
	// TreeNode node11 = new DefaultTreeNode("Node 1.1", node1);
	//
	// TreeNode node000 = new DefaultTreeNode("Node 0.0.0", node00);
	// TreeNode node001 = new DefaultTreeNode("Node 0.0.1", node00);
	// TreeNode node010 = new DefaultTreeNode("Node 0.1.0", node01);
	//
	// TreeNode node100 = new DefaultTreeNode("Node 1.0.0", node10);
	//
	// TreeNode nd = new DefaultTreeNode("nd", root);
	//
	// TreeNode n0 = new DefaultTreeNode(new Tipi(), root);
	// TreeNode n00 = new DefaultTreeNode(new Tipi(), n0);
	// TreeNode n01 = new DefaultTreeNode(new Tipi(), n0);
	// TreeNode n010 = new DefaultTreeNode(new Tipi(2, "nome", "desc", new Tipi(), new Timestamp(0)), n01);
	//
	// List<TreeNode> nodiTreeLs = root.getChildren();
	// Iterator<TreeNode> iteratorNodiTreeLs = nodiTreeLs.iterator();
	//
	// Tipi p = null;
	// Iterator<Tipi> iterator = tipiLs.iterator();
	// while(iterator.hasNext()){
	// p = iterator.next();
	//
	// new DefaultTreeNode(p, n01);
	//
	// nodiTreeLs = root.getChildren();
	// TreeNode nodoIter;
	// while(iteratorNodiTreeLs.hasNext()){
	// nodoIter = iteratorNodiTreeLs.next();
	// // cerca se già nell'albero c'è l'id
	// }
	// }
	// //
	// // try{
	// // try{
	// // utx.begin();
	// // String sql = "FROM Tipi t";
	// // Query query = entityManager.createQuery(sql);
	// // tipiLs = (List<Tipi>)query.getResultList();
	// // }catch(NoResultException e){
	// // tipiLs = null;
	// // }
	// // utx.commit();
	// // }catch(Exception e){
	// // try{
	// // utx.rollback();
	// // }catch(SystemException se){
	// // throw new RuntimeException(se);
	// // }
	// // throw new RuntimeException(e);
	// // }
	// //
	// }
	
	public TreeNode getAlbero(){
		if((tipiLs == null) || (tipiLs.isEmpty())){
			tipiLs = tipiTreeDao.getAll();
			
			albero = new DefaultTreeNode("root", null);
			
			popolaConFigli(1, albero);
		}
		return albero;
	}
	
	public void setAlbero(TreeNode albero){
		this.albero = albero;
	}
	
	public TreeNode getNodoSel(){
		return nodoSel;
	}
	
	public void setNodoSel(TreeNode nodoSel){
		this.nodoSel = nodoSel;
	}
	
	public void salva(){
		tipiTreeDao.salva(nodoSel);
		if(nodoSel != null){
			esito = "Aggiunto tipo: " + ((Tipi)nodoSel.getData()).getDescrizione();
			showGrowlInsMessage();
		}else{
			esito = "non ho trovato il tipo!";
			showGrowlErrorMessage();
		}
	}
	
	public void aggiorna(){
		((Tipi)nodoSel.getData()).setTipoPadre(getPadreFromId());
		
		tipiTreeDao.aggiorna(nodoSel);
		if(nodoSel != null){
			esito = "Aggiorno tipo: " + ((Tipi)nodoSel.getData()).getDescrizione();
			showGrowlUpdMessage();
		}else{
			esito = "non ho trovato il tipo!";
			showGrowlErrorMessage();
		}
	}
	
	public void elimina(){
		tipiTreeDao.elimina(nodoSel);
		if(nodoSel != null){
			esito = "Elimino tipo: " + ((Tipi)nodoSel.getData()).getDescrizione();
			showGrowlDelMessage();
		}else{
			esito = "non ho trovato il tipo!";
			showGrowlErrorMessage();
		}
	}
	
	public String getEsito(){
		return esito;
	}
	
	private void showGrowlUpdMessage(){
		String message = "Aggiornato con successo - " + esito + " >" + nodoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlInsMessage(){
		String message = "Inserito con successo - " + esito + " >" + nodoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlDelMessage(){
		String message = "Eliminato con successo - " + esito + " >" + nodoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlErrorMessage(){
		String message = "Operazione fallita - " + esito + " >" + nodoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", message));
		log.error(message);
	}

	public void displaySelectedSingle(){
		if(nodoSel != null){
			String message =  "Selezione " + nodoSel.getData().toString();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
			log.info(message);
		}
	}
	
	// servizio, ricorsiva
	private TreeNode popolaConFigli(int idPadre, TreeNode root){
		log.info("popolaConFigli con id " + idPadre);
		
		TreeNode leaf = null;
		
		Tipi p = null;
		Iterator<Tipi> iterator = tipiLs.iterator();
		
		while(iterator.hasNext()){
			p = iterator.next();
			
			if(p.getTipoPadre().getId() == idPadre){
				leaf = new DefaultTreeNode(p, root);
				// sono nella riga nd, che ha tipo e tipoPadre = 1, avrei un loop infinito, quindi gestisco il caso senza chiamare la ricorsione
				if(p.getId() == 1){
					continue;
				}
				popolaConFigli(p.getId(), leaf);
			}
		}
		return leaf;
	}
	
	// private, solo di servizio
	private Tipi getPadreFromId(){
		// tipoSel.tipoPadre ha solo l'id definito, e non si riesce a fare il merge, quindi configuro il padre con l'intero oggetto (comprendente quindi tutti i parametri come nome descrizione ecc)
		Tipi p = null;
		Iterator<Tipi> iterator = tipiLs.iterator();
		while(iterator.hasNext()){
			p = iterator.next();
			if(p.getId() == ((Tipi)nodoSel.getData()).getTipoPadre().getId()){
				break;
			}
			p = null;
		}
		return p;
	}
	
}
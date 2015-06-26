package it.cascino.controller;

import it.cascino.dao.AllegatiDao;
import it.cascino.dao.TipiDao;
import it.cascino.model.Allegati;
import it.cascino.model.Articoli;
import it.cascino.util.securety.shiro.ShiroSecured;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.io.InputStream;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@Named
@SessionScoped
@ShiroSecured
public class AllegatiController implements Serializable{
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
	private AllegatiDao allegatiDao;
	
	@Inject
	private TipiDao tipiDao;
	
	private String esito;
	
	@Inject
	private AllegatiCondivisiController allegatiCondivisiController;
	
	private List<Allegati> filteredAllegatiLs;
	
	private Allegati allegatoSel = new Allegati();
	private Allegati allegatoNew = new Allegati();
	
	// contiene, per l'elemento selezionato l'allegato
	private List<Allegati> allegatiPerSelLs;
	
	private List<UploadedFile> fileUploadedLs = new ArrayList<UploadedFile>();
	
	private List<Allegati> allegatiLsDynPop;
	
	public Allegati getAllegatoSel(){
		return allegatoSel;
	}
	
	public void setAllegatoSel(Allegati allegatoSel){
		if(allegatoSel != null){
			this.allegatoSel = allegatoSel;
		}
	}
	
	public Allegati getAllegatoNew(){
		return allegatoNew;
	}
	
	public void setAllegatoNew(Allegati allegatoNew){
		if(allegatoNew != null){
			this.allegatoNew = allegatoNew;
		}
	}
	
	// chiamata quando faccio nuovo, per non avere i campi sporchi
	public void resetOnNewAllegatoSel(){
		Allegati a = new Allegati();
		a.setId(1);
		allegatoSel = a;
	}
	
	public List<Allegati> getFilteredAllegatiLs(){
		return filteredAllegatiLs;
	}
	
	public void setFilteredAllegatiLs(List<Allegati> filteredAllegatiLs){
		this.filteredAllegatiLs = filteredAllegatiLs;
	}
	
	public void salva(){
		// log.info("tmpDEBUGtmp: " + "> " + "salva(" + ")");
		String risp = allegatiDao.salva(allegatoNew, fileUploadedLs);
		if(risp.startsWith("OK-")){
			risp = risp.substring(3);
			esito = risp;
			showGrowlInsMessage();
		}else{
			esito = "non sono stati caricati gli allegati! " + risp;
			showGrowlErrorMessage();
		}
		fileUploadedLs.clear();
		// aggiorno la lista condivisa
		allegatiCondivisiController.aggiornaAllegatiLs();
		allegatoNew = new Allegati();
		svuotaAllegatiLsDynPop();
		// log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void annullaUpdate(){
		// log.info("tmpDEBUGtmp: " + "> " + "annullaUpdate(" + ")");
		Boolean risp = allegatiDao.annullaUpdate(fileUploadedLs);
		if(risp){
			esito = "Annullamento allegato eseguito";
			showGrowlAnnullaMessage();
		}else{
			esito = "non sono stati caricati gli allegati!";
			showGrowlErrorMessage();
		}
		fileUploadedLs.clear();
		// log.info("tmpDEBUGtmp: " + "< " + "annullaUpdate");
	}
	
	public void annullaUpload(){
		// log.info("tmpDEBUGtmp: " + "> " + "annullaUpload(" + ")");
		Boolean risp = allegatiDao.annullaUpload(fileUploadedLs);
		if(risp){
			esito = "Annullamento allegato eseguito";
			showGrowlAnnullaMessage();
		}else{
			esito = "non sono stati caricati gli allegati!";
			showGrowlErrorMessage();
		}
		fileUploadedLs.clear();
		// log.info("tmpDEBUGtmp: " + "< " + "annullaUpload");
	}
	
	public void annullaUploadUndef(){
		// log.info("tmpDEBUGtmp: " + "> " + "annullaUploadUndef(" + ")");
		annullaUploadUndef(0, 0);
		// log.info("tmpDEBUGtmp: " + "< " + "annullaUploadUndef");
	}
	
	public void annullaUploadUndef(int t, int u){
		// log.info("tmpDEBUGtmp: " + "> " + "annullaUploadUndef(" + t + ", " + u + ")");
		if(t == 0){
			t = 1; // -----------------------------
		}
		
		Boolean risp = allegatiDao.annullaUploadUndef(t, fileUploadedLs, u);
		if(risp){
			esito = "Annullamento allegato eseguito";
			showGrowlAnnullaMessage();
		}else{
			esito = "non sono stati caricati gli allegati!";
			showGrowlErrorMessage();
		}
		// log.info("tmpDEBUGtmp: " + "< " + "annullaUploadUndef");
	}
	
	public void aggiorna(){
		// log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + ")");
		String risp = allegatiDao.aggiorna(allegatoSel, fileUploadedLs);
		if(risp.startsWith("OK-")){
			risp = risp.substring(3);
			esito = risp;
			showGrowlUpdMessage();
		}else{
			esito = "non sono state aggiornati gli allegati! " + risp;
			showGrowlErrorMessage();
		}
		fileUploadedLs.clear();
		// aggiorno la lista condivisa
		allegatiCondivisiController.aggiornaAllegatiLs();
		svuotaAllegatiLsDynPop();
		// log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	// @RequiresRoles("ruoloNome")
	@RequiresPermissions("allegati:write:delete")
	public void elimina(){
		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + ")");
		allegatiDao.elimina(allegatoSel, fileUploadedLs);
		if(allegatoSel != null){
			esito = "Elimino allegato: " + allegatoSel.getNome();
			showGrowlDelMessage();
		}else{
			esito = "non ho trovato l'allegato!";
			showGrowlErrorMessage();
		}
		// aggiorno la lista condivisa
		allegatiCondivisiController.aggiornaAllegatiLs();
		svuotaAllegatiLsDynPop();
		// log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	public void eliminaSingAllegato(){
		eliminaSingAllegato(0, 1);
	}
	
	public void eliminaSingAllegato(int t, int u){
		if(t == 0){
			t = 1; // ------------------;
		}
		allegatiDao.elimina(allegatoSel, t, fileUploadedLs, u);
		if(allegatoSel != null){
			esito = "Elimino allegato: " + allegatoSel.getNome();
			showGrowlDelMessage();
		}else{
			esito = "non ho trovato l'allegato!";
			showGrowlErrorMessage();
		}
	}
	
	public String getEsito(){
		return esito;
	}
	
	public List<Allegati> getAllegatiPerSelLs(){
		allegatiPerSelLs = allegatiDao.getAllegatiListPerSel(allegatoSel);
		return allegatiPerSelLs;
	}
	
	public void setAllegatiPerSelLs(List<Allegati> allegatiPerSelLs){
		this.allegatiPerSelLs = allegatiPerSelLs;
	}
	
	private void showGrowlInfoMessage(String message){
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlUpdMessage(){
		String message = "Aggiornato con successo - " + esito + " >" + allegatoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlInsMessage(){
		String message = "Inserito con successo - " + esito + " >" + allegatoNew + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlDelMessage(){
		String message = "Eliminato con successo - " + esito + " >" + allegatoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlAnnullaMessage(){
		String message = "Annullato con successo - " + esito + " >" + allegatoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlErrorMessage(){
		String message = "Operazione fallita - " + esito + " >" + allegatoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", message));
		log.error(message);
	}
	
	// public String size(Allegati a, int t){
	// // log.info("tmpDEBUGtmp: " + "> " + "size(" + f + ", " + t + ")");
	// // log.info("tmpDEBUGtmp: " + "< " + "size");
	// return size(a, t, 0);
	// }
	//
	// public String size(Allegati a, int t, int u){
	// // log.info("tmpDEBUGtmp: " + "> " + "size(" + f + ", " + t + ", " + u + ")");
	// if(t == 0){
	// t = 1; // -------------;
	// }
	// String size = allegatiDao.getSize(a, t, fileUploadedLs, u);
	// // log.info("tmpDEBUGtmp: " + "< " + "size");
	// return size;
	// }
	
	public String size(Allegati a){
		// String size = allegatiDao.getSize(a, t, fileUploadedLs, u);
		String size = allegatiDao.getSize(a, 1, fileUploadedLs, 0);
		return size;
	}
	
	public String resolvePath(Allegati a){
		String path = allegatiDao.resolvePath(a);
		return path;
	}
	
	public String getAllegatonameUrl(Allegati a, int t){
		return getAllegatonameUrl(a, t, 0);
	}
	
	public String getAllegatonameUrl(Allegati a, int t, int u){
		String nomePerUrl = "";
		nomePerUrl = getAllegatoname(a, t, u);
		// gestione dei simboli che creano anomalie nei nomi url (ci va %hx)
		if(StringUtils.contains(nomePerUrl, "%")){ // e' importante fare per primo %, altrimenti lo mettessi dopo tutti gli altri mi sostituirebbe i % dei precedenti
			nomePerUrl = StringUtils.replace(nomePerUrl, "%", "%25");
		}
		if(StringUtils.contains(nomePerUrl, "°")){
			nomePerUrl = StringUtils.replace(nomePerUrl, "°", "%B0");
		}
		return nomePerUrl;
	}
	
	public String getAllegatoname(Allegati a, int t){
		return getAllegatoname(a, t, 0);
	}
	
	public String getAllegatoname(Allegati a, int t, int u){
		if(t == 0){
			t = 1; // ----------------;
		}
		String name = allegatiDao.getAllegatoName(a, t, fileUploadedLs, u);
		return name;
	}
	
	public void fileUploadOrig(FileUploadEvent event){
		fileUpload(event, ".orig");
	}
	
	public void fileUploadUndef(FileUploadEvent event){
		String type = "";
		type = ".orig";// -------------------------
		log.info("fileUploadUndef - " + type);
		
		fileUpload(event, type);
	}
	
	public void fileUpload(FileUploadEvent event, String type){
		if(type.equals(".err")){
			return;
		}
		
		allegatiDao.fileUpload(event, type, fileUploadedLs);
		
		showGrowlInfoMessage(event.getFile().getFileName() + " upload terminato");
	}
	
	// public Boolean canDelete(){
	// // log.info("tmpDEBUGtmp: " + "> " + "canDelete(" + ")");
	// if(tipoFoNum == 1){
	// return false;
	// }
	// // log.info("tmpDEBUGtmp: " + "< " + "canDelete");
	// return true;
	// }
	
	public List<Allegati> getAllegatiLsDynPop(){
		if(allegatiLsDynPop == null){
			allegatiLsDynPop = new ArrayList<Allegati>();
		}
		return allegatiLsDynPop;
	}
	
	public void setAllegatiLsDynPop(List<Allegati> allegatiLsDynPop){
		this.allegatiLsDynPop = allegatiLsDynPop;
	}
	
//	public void popolaAllegatiLsDynPop(Integer idArticolo){
//		allegatiLsDynPop = getAllegatiArticoloOrdLsDaIdArticolo(idArticolo); // allegatiCondivisiController.getAllegatiLs();// ---------
//	}
	public void popolaAllegatiLsDynPop(){
		allegatiLsDynPop = allegatiCondivisiController.getAllegatiLs();
	}
	
	public void svuotaAllegatiLsDynPop(){
		allegatiLsDynPop = new ArrayList<Allegati>();
	}
	
	public boolean filterPerTipoDelloArticolo(Object value, Object filter, Locale locale){
		// log.info("tmpDEBUGtmp: " + "> " + "filterArticoloCodice(" + value + ", " + filter + ", " + locale + ")");
		List<Integer> filterNumb = new ArrayList<Integer>();
		String filterStr = filter.toString().trim().toUpperCase(locale);
		if((filter == null) || StringUtils.isEmpty(filterStr)){
			return true;
		}
		try{
			filterNumb.add(Integer.parseInt(filterStr));
		}catch(NumberFormatException e){
			filterNumb = tipiDao.getIdTipoDaLikeNomeTipo(filterStr);
		}
		if(filterNumb == null){
			return false;
		}
		if(value == null){
			return false;
		}
		Integer valueNumb = Integer.parseInt(value.toString().trim().toUpperCase(locale));
		
		List<Integer> idTipoLs = getTipoLsDaIdAllegato(valueNumb);
		Iterator<Integer> iterTipoLs = idTipoLs.iterator();
		while(iterTipoLs.hasNext()){
			Integer idTipo = iterTipoLs.next();
			Iterator<Integer> iterFilterNumb = filterNumb.iterator();
			while(iterFilterNumb.hasNext()){
				Integer idFilterNumb = iterFilterNumb.next();
				if(idFilterNumb.equals(idTipo)){
					return true;
				}
				if(tipiDao.getTipoDiscendeDaTipo(idTipo, idFilterNumb)){
					return true;
				}
			}
		}
		return false;
	}
	
	public List<Integer> getTipoLsDaIdAllegato(Integer idAllegato){
		List<Integer> idTipoLs = allegatiDao.getTipoLsDaIdAllegato(idAllegato);
		return idTipoLs;
	}
	
	public Allegati getAllegatiArticoloDaIdArticolo(Integer idArticolo){
		Allegati allegatiArticolo = new Allegati();
		allegatiArticolo = allegatiDao.getAllegatiArticoloDaIdArticolo(idArticolo);
		if(allegatiArticolo != null){
			esito = "selezionato allegato " + allegatiArticolo.getId() + " per articolo: " + idArticolo;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stato trovato l'allegato!" + " (articolo: " + idArticolo + ")";
			showGrowlErrorMessage();
		}
		return allegatiArticolo;
	}
	
	public List<Allegati> getAllegatiArticoloOrdLsDaIdArticolo(Integer idArticolo){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotoArticoloOrdLsDaIdArticolo(" + idArticolo + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		List<Allegati> allegatiArticolo = new ArrayList<Allegati>();
		allegatiArticolo = allegatiDao.getAllegatiArticoloOrdLsDaIdArticolo(idArticolo);
		if(allegatiArticolo != null){
			esito = "selezionati " + allegatiArticolo.size() + " allegati per articolo: " + idArticolo;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stato trovato l'allegato!" + " (articolo: " + idArticolo + ")";
			showGrowlErrorMessage();
		}
		return allegatiArticolo;
	}
	
	private StreamedContent fileAllegatoDownload;
	
	public StreamedContent getFileAllegatoDownload(String filename){
		ExternalContext a1 = facesContext.getExternalContext();
		ServletContext a2 = (ServletContext)a1.getContext();
		InputStream stream = a2.getResourceAsStream("/resources/allegati/" + filename);
		fileAllegatoDownload = new DefaultStreamedContent(stream, "application/octet-stream", filename);
		return fileAllegatoDownload;
	}
	
//	public StreamedContent getFileAllegatoDownload(Integer idArticolo){
//		List<Allegati> articoli = getAllegatiArticoloOrdLsDaIdArticolo(idArticolo);
//		ExternalContext a1 = facesContext.getExternalContext();
//		ServletContext a2 = (ServletContext)a1.getContext();
//		InputStream stream = a2.getResourceAsStream("/resources/allegati/" + filename);
//		fileAllegatoDownload = new DefaultStreamedContent(stream, "application/pdf", filename);
//		return fileAllegatoDownload;
//	}
}

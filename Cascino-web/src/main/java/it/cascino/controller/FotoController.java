package it.cascino.controller;

import it.cascino.dao.FotoDao;
import it.cascino.model.Articoli;
import it.cascino.model.Foto;
import it.cascino.model.Tipi;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.UploadedFile;

@Named
@SessionScoped
public class FotoController implements Serializable{
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
	private FotoDao fotoDao;
	
	private String esito;
	
	@Inject
	private FotoCondivisiController fotoCondivisiController;  

	private List<Foto> filteredFotoLs;
	
	private Foto fotoSel = new Foto();
	
	// contiene, per l'elemento selezionato, le foto originale, HD, HD wm, LD, LD wm in lista, per essere utilizzate nella gallery
	private List<Foto> fotoPerSelLs;
	
	private String tipoFoto;
	private int tipoFotoNum;
	
	private List<UploadedFile> fileUploadedLs = new ArrayList<UploadedFile>();
	
	private List<Foto> fotoLsDynPop;
	
	public void onChangeTipoFoto(){
		log.info("tmpDEBUGtmp: " + "> " + "onChangeTipoFoto(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		tipoFotoNum = 1;
		if(tipoFoto.equals("orig")){
			tipoFotoNum = 1;
		}else if(tipoFoto.equals("hd")){
			tipoFotoNum = 2;
		}else if(tipoFoto.equals("hdwm")){
			tipoFotoNum = 3;
		}else if(tipoFoto.equals("ld")){
			tipoFotoNum = 4;
		}else if(tipoFoto.equals("ldwm")){
			tipoFotoNum = 5;
		}else{
			tipoFotoNum = 0;
		}
		log.info("onChangeTipoFoto - " + tipoFotoNum);
		log.info("tmpDEBUGtmp: " + "< " + "onChangeTipoFoto");
	}
	
	public String getTipoFoto(){
		log.info("tmpDEBUGtmp: " + "> " + "getTipoFoto(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if((tipoFoto == null) || (tipoFoto.isEmpty())){
			tipoFotoNum = 1;
			tipoFoto = "orig";
		}
		log.info("tmpDEBUGtmp: " + "< " + "getTipoFoto");
		return tipoFoto;
	}
	
	public void setTipoFoto(String tipoFoto){
		log.info("tmpDEBUGtmp: " + "> " + "setTipoFoto(" + tipoFoto + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		this.tipoFoto = tipoFoto;
		log.info("tmpDEBUGtmp: " + "< " + "setTipoFoto");
	}
	
	public Foto getFotoSel(){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoSel(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if(fotoSel == null){
			Foto f = new Foto();
			f.setId(1);
			fotoSel = f;
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoSel");
		return fotoSel;
	}
	
	public void setFotoSel(Foto fotoSel){
		log.info("tmpDEBUGtmp: " + "> " + "setFotoSel(" + fotoSel + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if(fotoSel != null){
			this.fotoSel = fotoSel;
		}
		log.info("tmpDEBUGtmp: " + "< " + "setFotoSel");
	}
	
	public List<Foto> getFilteredFotoLs(){
		log.info("tmpDEBUGtmp: " + "> " + "getFilteredFotoLs(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		log.info("tmpDEBUGtmp: " + "< " + "getFilteredFotoLs");
		return filteredFotoLs;
	}
	
	public void setFilteredFotoLs(List<Foto> filteredFotoLs){
		log.info("tmpDEBUGtmp: " + "> " + "setFilteredFotoLs(" + filteredFotoLs + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		log.info("tmpDEBUGtmp: " + "< " + "setFilteredFotoLs");
		this.filteredFotoLs = filteredFotoLs;
	}
	
	public void salva(){
		log.info("tmpDEBUGtmp: " + "> " + "salva(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		String risp = fotoDao.salva(fileUploadedLs);
		if(risp.startsWith("OK-")){
			risp = risp.substring(3);
			esito = risp;
			showGrowlInsMessage();
		}else{
			esito = "non sono state caricate le foto! " + risp;
			showGrowlErrorMessage();
		}
		fileUploadedLs.clear();
		log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void annullaUpdate(){
		log.info("tmpDEBUGtmp: " + "> " + "annullaUpdate(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		Boolean risp = fotoDao.annullaUpdate(fileUploadedLs);
		if(risp){
			esito = "Annullamento foto eseguito";
			showGrowlAnnullaMessage();
		}else{
			esito = "non sono state caricate le foto!";
			showGrowlErrorMessage();
		}
		fileUploadedLs.clear();
		log.info("tmpDEBUGtmp: " + "< " + "annullaUpdate");
	}
	
	public void annullaUpload(){
		log.info("tmpDEBUGtmp: " + "> " + "annullaUpload(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		Boolean risp = fotoDao.annullaUpload(fileUploadedLs);
		if(risp){
			esito = "Annullamento foto eseguito";
			showGrowlAnnullaMessage();
		}else{
			esito = "non sono state caricate le foto!";
			showGrowlErrorMessage();
		}
		fileUploadedLs.clear();
		log.info("tmpDEBUGtmp: " + "< " + "annullaUpload");
	}
	
	public void annullaUploadUndef(){
		log.info("tmpDEBUGtmp: " + "> " + "annullaUploadUndef(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		annullaUploadUndef(0, 0);
		log.info("tmpDEBUGtmp: " + "< " + "annullaUploadUndef");
	}
	
	public void annullaUploadUndef(int t, int u){
		log.info("tmpDEBUGtmp: " + "> " + "annullaUploadUndef(" + t + ", " + u + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if(t == 0){
			t = tipoFotoNum;
		}
		
		Boolean risp = fotoDao.annullaUploadUndef(t, fileUploadedLs, u);
		if(risp){
			esito = "Annullamento foto eseguito";
			showGrowlAnnullaMessage();
		}else{
			esito = "non sono state caricate le foto!";
			showGrowlErrorMessage();
		}
		// fileUploadedLs.clear();
		log.info("tmpDEBUGtmp: " + "< " + "annullaUploadUndef");
	}
	
	public void aggiorna(){
		log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		String risp = fotoDao.aggiorna(fotoSel, fileUploadedLs);
		if(risp.startsWith("OK-")){
			risp = risp.substring(3);
			esito = risp;
			showGrowlUpdMessage();
		}else{
			esito = "non sono state aggiornate le foto! " + risp;
			showGrowlErrorMessage();
		}
		fileUploadedLs.clear();
		log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	public void elimina(){
		log.info("tmpDEBUGtmp: " + "> " + "elimina(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		fotoDao.elimina(fotoSel, fileUploadedLs);
		if(fotoSel != null){
			esito = "Elimino foto: " + fotoSel.getOriginale();
			showGrowlDelMessage();
		}else{
			esito = "non ho trovato la foto!";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	public void eliminaSingFoto(){
		log.info("tmpDEBUGtmp: " + "> " + "eliminaSingFoto(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		eliminaSingFoto(0, 1);
		log.info("tmpDEBUGtmp: " + "< " + "eliminaSingFoto");
	}
	
	public void eliminaSingFoto(int t, int u){
		log.info("tmpDEBUGtmp: " + "> " + "eliminaSingFoto(" + t + ", " + u + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if(t == 0){
			t = tipoFotoNum;
		}
		fotoDao.elimina(fotoSel, t, fileUploadedLs, u);
		if(fotoSel != null){
			esito = "Elimino foto: " + fotoSel.getOriginale();
			showGrowlDelMessage();
		}else{
			esito = "non ho trovato la foto!";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "eliminaSingFoto");
	}
	
	public String getEsito(){
		log.info("tmpDEBUGtmp: " + "> " + "getEsito(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		log.info("tmpDEBUGtmp: " + "< " + "getEsito");
		return esito;
	}
	
	public List<Foto> getFotoPerSelLs(){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoPerSelLs(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		fotoPerSelLs = fotoDao.getFotoListPerSel(fotoSel);
		log.info("tmpDEBUGtmp: " + "< " + "getFotoPerSelLs");
		return fotoPerSelLs;
	}
	
	public void setFotoPerSelLs(List<Foto> fotoPerSelLs){
		log.info("tmpDEBUGtmp: " + "> " + "setFotoPerSelLs(" + fotoPerSelLs + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		this.fotoPerSelLs = fotoPerSelLs;
		log.info("tmpDEBUGtmp: " + "< " + "setFotoPerSelLs");
	}
	
	private void showGrowlInfoMessage(String message){
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlUpdMessage(){
		String message = "Aggiornato con successo - " + esito + " >" + fotoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlInsMessage(){
		String message = "Inserito con successo - " + esito + " >" + fotoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlDelMessage(){
		String message = "Eliminato con successo - " + esito + " >" + fotoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlAnnullaMessage(){
		String message = "Annullato con successo - " + esito + " >" + fotoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlErrorMessage(){
		String message = "Operazione fallita - " + esito + " >" + fotoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", message));
		log.error(message);
	}
	
	public int sortByNum(Object obj1, Object obj2){
		log.info("tmpDEBUGtmp: " + "> " + "sortByNum(" + obj1 + ", " + obj2 + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		Integer o1 = (Integer)obj1;
		Integer o2 = (Integer)obj2;
		log.info("sortById: " + o1 + "-" + o2);
		log.info("tmpDEBUGtmp: " + "< " + "sortByNum");
		if(o1 < o2){
			return -1;
		}else if(o1 > o2){
			return 1;
		}
		return 0;
	}
	
	public int sortByStr(Object obj1, Object obj2){
		log.info("tmpDEBUGtmp: " + "> " + "sortByStr(" + obj1 + ", " + obj2 + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		String o1 = (String)obj1;
		String o2 = (String)obj2;
		log.info("sortByname: " + o1 + "-" + o2);
		log.info("tmpDEBUGtmp: " + "< " + "sortByStr");
		if(o1.compareTo(o2) < 0){
			return -1;
		}else if(o1.compareTo(o2) > 0){
			return 1;
		}
		return 0;
	}
	
	public int sortByStrIC(Object obj1, Object obj2){
		log.info("tmpDEBUGtmp: " + "> " + "sortByStrIC(" + obj1 + ", " + obj2 + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		String o1 = (String)obj1;
		String o2 = (String)obj2;
		log.info("sortBynameIC: " + o1 + "-" + o2);
		log.info("tmpDEBUGtmp: " + "< " + "sortByStrIC");
		if(o1.compareToIgnoreCase(o2) < 0){
			return -1;
		}else if(o1.compareToIgnoreCase(o2) > 0){
			return 1;
		}
		return 0;
	}
		
	public String size(Foto f, int t){
		log.info("tmpDEBUGtmp: " + "> " + "size(" + f + ", " + t + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		log.info("tmpDEBUGtmp: " + "< " + "size");
		return size(f, t, 0);
	}
	
	public String size(Foto f, int t, int u){
		log.info("tmpDEBUGtmp: " + "> " + "size(" + f + ", " + t + ", " + u + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if(t == 0){
			t = tipoFotoNum;
		}
		String size = fotoDao.getSize(f, t, fileUploadedLs, u);
		log.info("tmpDEBUGtmp: " + "< " + "size");
		return size;
	}
	
	public String resolution(Foto f, int t){
		log.info("tmpDEBUGtmp: " + "> " + "resolution(" + f + ", " + t + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		log.info("tmpDEBUGtmp: " + "< " + "resolution");
		return resolution(f, t, 0);
	}
	
	public String resolution(Foto f, int t, int u){
		log.info("tmpDEBUGtmp: " + "> " + "resolution(" + f + ", " + t + ", " + u + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if(t == 0){
			t = tipoFotoNum;
		}
		String resolution = fotoDao.getResolution(f, t, fileUploadedLs, u);
		log.info("tmpDEBUGtmp: " + "< " + "resolution");
		return resolution;
	}
	
	public int getHeightFromResolution(Foto f, int t, int h, int l){
		log.info("tmpDEBUGtmp: " + "> " + "getHeightFromResolution(" + f + ", " + t + ", " + h + ", " + l + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		log.info("tmpDEBUGtmp: " + "< " + "getHeightFromResolution");
		return getHeightFromResolution(f, t, h, l, 0);
	}
	
	public int getHeightFromResolution(Foto f, int t, int h, int l, int u){
		log.info("tmpDEBUGtmp: " + "> " + "getHeightFromResolution(" + f + ", " + t + ", " + h + ", " + l + ", " + u + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if(t == 0){
			t = tipoFotoNum;
		}
		log.info("tmpDEBUGtmp: " + "< " + "getHeightFromResolution");
		return fotoDao.getHeightFromResolution(f, t, h, l, u);
	}
	
	public String resolvePath(Foto f){
		log.info("tmpDEBUGtmp: " + "> " + "resolvePath(" + f + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// return resolvePath(f, -1);
		// }
		//
		// public String resolvePath(Foto f, int i){
		// String path = fotoDao.resolvePath(f, i, fileUploadedLs);
		String path = fotoDao.resolvePath(f);
		log.info("tmpDEBUGtmp: " + "< " + "resolvePath");
		return path;
	}
	
	public String getFotonameUrl(Foto f, int t){
		log.info("tmpDEBUGtmp: " + "> " + "getFotonameUrl(" + f + ", " + t + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		log.info("tmpDEBUGtmp: " + "< " + "getFotonameUrl");
		return getFotonameUrl(f, t, 0);
	}
	
	public String getFotonameUrl(Foto f, int t, int u){
		log.info("tmpDEBUGtmp: " + "> " + "getFotonameUrl(" + f + ", " + t + ", " + u + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		String nomePerUrl = "";
		nomePerUrl = getFotoname(f, t, u);
		// gestione dei simboli che creano anomalie nei nomi url (ci va %hx)
		if(StringUtils.contains(nomePerUrl, "%")){	// e' importante fare per primo %, altrimenti lo mettessi dopo tutti gli atri mi sostituirebbe i % dei precedenti
			nomePerUrl = StringUtils.replace(nomePerUrl, "%", "%25");
		}
		if(StringUtils.contains(nomePerUrl, "°")){
			nomePerUrl = StringUtils.replace(nomePerUrl, "°", "%B0");
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotonameUrl");
		return nomePerUrl;
	}
	
	public String getFotoname(Foto f, int t){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoname(" + f + ", " + t + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		log.info("tmpDEBUGtmp: " + "< " + "getFotoname");
		return getFotoname(f, t, 0);
	}
	
	public String getFotoname(Foto f, int t, int u){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoname(" + f + ", " + t + ", " + u + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if(t == 0){
			t = tipoFotoNum;
		}
		String name = fotoDao.getFotoname(f, t, fileUploadedLs, u);
		log.info("tmpDEBUGtmp: " + "< " + "getFotoname");
		return name;
	}
	
	public void fileUploadOrig(FileUploadEvent event){
		log.info("tmpDEBUGtmp: " + "> " + "fileUploadOrig(" + event + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		fileUpload(event, ".orig");
		log.info("tmpDEBUGtmp: " + "< " + "fileUploadOrig");
	}
	
	public void fileUploadHD(FileUploadEvent event){
		log.info("tmpDEBUGtmp: " + "> " + "fileUploadHD(" + event + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		fileUpload(event, ".hd");
		log.info("tmpDEBUGtmp: " + "< " + "fileUploadHD");
	}
	
	public void fileUploadHDWM(FileUploadEvent event){
		log.info("tmpDEBUGtmp: " + "> " + "fileUploadHDWM(" + event + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		fileUpload(event, ".hdwm");
		log.info("tmpDEBUGtmp: " + "< " + "fileUploadHDWM");
	}
	
	public void fileUploadLD(FileUploadEvent event){
		log.info("tmpDEBUGtmp: " + "> " + "fileUploadLD(" + event + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		fileUpload(event, ".ld");
		log.info("tmpDEBUGtmp: " + "< " + "fileUploadLD");
	}
	
	public void fileUploadLDWM(FileUploadEvent event){
		log.info("tmpDEBUGtmp: " + "> " + "fileUploadLDWM(" + event + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		fileUpload(event, ".ldwm");
		log.info("tmpDEBUGtmp: " + "< " + "fileUploadLDWM");
	}
	
	public void fileUploadUndef(FileUploadEvent event){
		log.info("tmpDEBUGtmp: " + "> " + "fileUploadUndef(" + event + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		String type = "";
		if(tipoFotoNum == 1){
			type = ".orig";
		}else if(tipoFotoNum == 2){
			type = ".hd";
		}else if(tipoFotoNum == 3){
			type = ".hdwm";
		}else if(tipoFotoNum == 4){
			type = ".ld";
		}else if(tipoFotoNum == 5){
			type = ".ldwm";
		}else{
			type = ".err";
		}
		log.info("fileUploadUndef - " + type);
		
		fileUpload(event, type);
		log.info("tmpDEBUGtmp: " + "< " + "fileUploadUndef");
	}
	
	public void fileUpload(FileUploadEvent event, String type){
		log.info("tmpDEBUGtmp: " + "> " + "fileUpload(" + event + ", " + type + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if(type.equals(".err")){
			return;
		}
		
		fotoDao.fileUpload(event, type, fileUploadedLs);
		
		showGrowlInfoMessage(event.getFile().getFileName() + " upload terminato");
		log.info("tmpDEBUGtmp: " + "< " + "fileUpload");
	}
	
	public Boolean canDelete(){
		log.info("tmpDEBUGtmp: " + "> " + "canDelete(" + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if(tipoFotoNum == 1){
			return false;
		}
		log.info("tmpDEBUGtmp: " + "< " + "canDelete");
		return true;
	}
	
	public List<Foto> getFotoLsDynPop(){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoLsDynPop(" + ")");
		if(fotoLsDynPop == null){
			fotoLsDynPop = new ArrayList<Foto>();
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoLsDynPop");				
		return fotoLsDynPop;
	}
	
	public void setFotoLsDynPop(List<Foto> fotoLsDynPop){
		log.info("tmpDEBUGtmp: " + "> " + "fotoLsDynPop(" + fotoLsDynPop + ")");
		this.fotoLsDynPop = fotoLsDynPop;
		log.info("tmpDEBUGtmp: " + "< " + "fotoLsDynPop");
	}
	
	public void popolaFotoLsDynPop(){
		fotoLsDynPop = fotoCondivisiController.getFotoLs();
	}
	
	public void svuotaFotoLsDynPop(){
		fotoLsDynPop = new ArrayList<Foto>();
	}
	
	// ***** inizio Foto *****
	public Foto getFotoDaIdFoto(Integer idFoto){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoDaIdFoto(" + idFoto + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		log.info("tmpDEBUGtmp: " + "< " + "getFotoDaIdFoto");
		return fotoDao.getFotoDaIdFoto(idFoto);
	}
	// ***** fine Foto *****
	
	// ***** inizio Tipi *****
	public Foto getFotoTipoDaIdTipo(Integer idTipo){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoTipoDaIdTipo(" + idTipo + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		Foto fotoTipo = new Foto();
		fotoTipo = fotoDao.getFotoTipoDaIdTipo(idTipo);
		if(fotoTipo != null){
			esito = "selezionata foto " + fotoTipo.getId() + " per tipo: " + idTipo;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto!" + " (tipo: " + idTipo + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoTipoDaIdTipo");
		return fotoTipo;
	}
	
	public Foto getFotoTipoDaIdArticolo(Integer idArticolo){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoTipoDaIdArticolo(" + idArticolo + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		Foto fotoTipo = new Foto();
		fotoTipo = fotoDao.getFotoTipoDaIdArticolo(idArticolo);
		if(fotoTipo != null){
			esito = "selezionata foto " + fotoTipo.getId() + " per articolo: " + idArticolo;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto!" + " (articolo: " + idArticolo + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoTipoDaIdArticolo");
		return fotoTipo;
	}
	// ***** fine Tipi *****

	// ***** inizio Produttori *****
	public Foto getFotoProduttoreDaIdProduttore(Integer idProduttore){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoProduttoreDaIdProduttore(" + idProduttore + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		Foto fotoProduttore = new Foto();
		fotoProduttore = fotoDao.getFotoProduttoreDaIdProduttore(idProduttore);
		if(fotoProduttore != null){
			esito = "selezionata foto " + fotoProduttore.getId() + " per produttore: " + idProduttore;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto!" + " (produttore: " + idProduttore + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoProduttoreDaIdProduttore");
		return fotoProduttore;
	}
	
	public Foto getFotoProduttoreDaIdArticolo(Integer idArticolo){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoProduttoreDaIdArticolo(" + idArticolo + ")");
		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		Foto fotoProduttore = new Foto();
		fotoProduttore = fotoDao.getFotoProduttoreDaIdArticolo(idArticolo);
		if(fotoProduttore != null){
			esito = "selezionata foto " + fotoProduttore.getId() + " per articolo: " + idArticolo;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto!" + " (articolo: " + idArticolo + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoProduttoreDaIdArticolo");
		return fotoProduttore;
	}
	// ***** fine Produttori *****
	
	// ***** inizio Articoli *****
	public Foto getFotoArticoloDaIdArticolo(Integer idArticolo){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoArticoloDaIdArticolo(" + idArticolo + ")");
		log.info("tmpDEBUGtmp: " + "id: " +  ((fotoSel != null) ? fotoSel.getId() : "null"));
		Foto fotoArticolo = new Foto();
		fotoArticolo = fotoDao.getFotoArticoloDaIdArticolo(idArticolo);
		if(fotoArticolo != null){
			esito = "selezionata foto " + fotoArticolo.getId() + " per articolo: " + idArticolo;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto!" + " (articolo: " + idArticolo + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoArticoloDaIdArticolo");
		return fotoArticolo;
	}

	public List<Foto> getFotoArticoloOrdLsDaIdArticolo(Integer idArticolo){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoArticoloOrdLsDaIdArticolo(" + idArticolo + ")");
		log.info("tmpDEBUGtmp: " + "id: " +  ((fotoSel != null) ? fotoSel.getId() : "null"));
		List<Foto> fotoArticolo = new ArrayList<Foto>();
		fotoArticolo = fotoDao.getFotoArticoloOrdLsDaIdArticolo(idArticolo);
		if(fotoArticolo != null){
			esito = "selezionate " + fotoArticolo.size() + " foto per articolo: " + idArticolo;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto!" + " (articolo: " + idArticolo + ")";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoArticoloOrdLsDaIdArticolo");
		return fotoArticolo;
	}
	// ***** fine Articoli *****

}

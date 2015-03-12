package it.cascino.controller;

import it.cascino.dao.FotoDao;
import it.cascino.model.Articoli;
import it.cascino.model.Foto;
import it.cascino.model.Tipi;
import it.cascino.util.securety.shiro.ShiroSecured;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.io.File;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.UploadedFile;

@Named
@SessionScoped
@ShiroSecured
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
	
	private String tagDaAggiungere;
	private String tagDaEliminare;
	
	private List<String> tagFotoSelLs = new ArrayList<String>();
	
	private String cosaMostra = "I";
	private String color1 = "ind";
	private String color2 = "ind";
	private String forma = "ind";

	public void onChangeTipoFoto(){
		// log.info("tmpDEBUGtmp: " + "> " + "onChangeTipoFoto(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
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
		// log.info("tmpDEBUGtmp: " + "< " + "onChangeTipoFoto");
	}
	
	public String getTipoFoto(){
		// log.info("tmpDEBUGtmp: " + "> " + "getTipoFoto(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if((tipoFoto == null) || (tipoFoto.isEmpty())){
			tipoFotoNum = 1;
			tipoFoto = "orig";
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getTipoFoto");
		return tipoFoto;
	}
	
	public void setTipoFoto(String tipoFoto){
		// log.info("tmpDEBUGtmp: " + "> " + "setTipoFoto(" + tipoFoto + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		this.tipoFoto = tipoFoto;
		// log.info("tmpDEBUGtmp: " + "< " + "setTipoFoto");
	}
	
	public Foto getFotoSel(){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoSel(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if(fotoSel == null){
			Foto f = new Foto();
			f.setId(1);
			fotoSel = f;
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getFotoSel");
		return fotoSel;
	}
	
	public void setFotoSel(Foto fotoSel){
		log.info("tmpDEBUGtmp: " + "> " + "setFotoSel(" + fotoSel + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if(fotoSel != null){
			this.fotoSel = fotoSel;
		}
		// log.info("tmpDEBUGtmp: " + "< " + "setFotoSel");
	}
	
	// chiamata quando faccio nuovo, per non avere i campi sporchi da una selezione che deriva dalla tabella
	public void resetOnNewFotoSel(){
		log.info("tmpDEBUGtmp: " + "> " + "resetOnNewFotoSel(" + ")");
//		log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		Foto f = new Foto();
		f.setId(1);
//		fotoSel = f;
		setFotoSel(f);
		// resetto anche le altre var
//		getTagFotoSelLs();
//		log.info("tmpDEBUGtmp: " + "< " + "resetOnNewFotoSel");
	}
	
	public List<Foto> getFilteredFotoLs(){
		// log.info("tmpDEBUGtmp: " + "> " + "getFilteredFotoLs(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getFilteredFotoLs");
		return filteredFotoLs;
	}
	
	public void setFilteredFotoLs(List<Foto> filteredFotoLs){
		// log.info("tmpDEBUGtmp: " + "> " + "setFilteredFotoLs(" + filteredFotoLs + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "setFilteredFotoLs");
		this.filteredFotoLs = filteredFotoLs;
	}
	
	public void salva(){
		// log.info("tmpDEBUGtmp: " + "> " + "salva(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		String risp = fotoDao.salva(fotoSel, fileUploadedLs);
		if(risp.startsWith("OK-")){
			risp = risp.substring(3);
			esito = risp;
			showGrowlInsMessage();
		}else{
			esito = "non sono state caricate le foto! " + risp;
			showGrowlErrorMessage();
		}
		fileUploadedLs.clear();
		// aggiorno la lista condivisa
		fotoCondivisiController.aggiornaFotoLs();
		// log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void annullaUpdate(){
		// log.info("tmpDEBUGtmp: " + "> " + "annullaUpdate(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		Boolean risp = fotoDao.annullaUpdate(fileUploadedLs);
		if(risp){
			esito = "Annullamento foto eseguito";
			showGrowlAnnullaMessage();
		}else{
			esito = "non sono state caricate le foto!";
			showGrowlErrorMessage();
		}
		fileUploadedLs.clear();
		// log.info("tmpDEBUGtmp: " + "< " + "annullaUpdate");
	}
	
	public void annullaUpload(){
		// log.info("tmpDEBUGtmp: " + "> " + "annullaUpload(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		Boolean risp = fotoDao.annullaUpload(fileUploadedLs);
		if(risp){
			esito = "Annullamento foto eseguito";
			showGrowlAnnullaMessage();
		}else{
			esito = "non sono state caricate le foto!";
			showGrowlErrorMessage();
		}
		fileUploadedLs.clear();
		// log.info("tmpDEBUGtmp: " + "< " + "annullaUpload");
	}
	
	public void annullaUploadUndef(){
		// log.info("tmpDEBUGtmp: " + "> " + "annullaUploadUndef(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		annullaUploadUndef(0, 0);
		// log.info("tmpDEBUGtmp: " + "< " + "annullaUploadUndef");
	}
	
	public void annullaUploadUndef(int t, int u){
		// log.info("tmpDEBUGtmp: " + "> " + "annullaUploadUndef(" + t + ", " + u + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
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
		// log.info("tmpDEBUGtmp: " + "< " + "annullaUploadUndef");
	}
	
	public void aggiorna(){
		// log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
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
		// aggiorno la lista condivisa
		fotoCondivisiController.aggiornaFotoLs();
		// log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	// @RequiresRoles("ruoloNome")
	@RequiresPermissions("foto:write:delete")
	public void elimina(){
		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		fotoDao.elimina(fotoSel, fileUploadedLs);
		if(fotoSel != null){
			esito = "Elimino foto: " + fotoSel.getOriginale();
			showGrowlDelMessage();
		}else{
			esito = "non ho trovato la foto!";
			showGrowlErrorMessage();
		}
		// aggiorno la lista condivisa
		fotoCondivisiController.aggiornaFotoLs();
		// log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	public void eliminaSingFoto(){
		// log.info("tmpDEBUGtmp: " + "> " + "eliminaSingFoto(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		eliminaSingFoto(0, 1);
		// log.info("tmpDEBUGtmp: " + "< " + "eliminaSingFoto");
	}
	
	public void eliminaSingFoto(int t, int u){
		// log.info("tmpDEBUGtmp: " + "> " + "eliminaSingFoto(" + t + ", " + u + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
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
		// log.info("tmpDEBUGtmp: " + "< " + "eliminaSingFoto");
	}
	
	public String getEsito(){
		// log.info("tmpDEBUGtmp: " + "> " + "getEsito(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getEsito");
		return esito;
	}
	
	public List<Foto> getFotoPerSelLs(){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotoPerSelLs(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		fotoPerSelLs = fotoDao.getFotoListPerSel(fotoSel);
		// log.info("tmpDEBUGtmp: " + "< " + "getFotoPerSelLs");
		return fotoPerSelLs;
	}
	
	public void setFotoPerSelLs(List<Foto> fotoPerSelLs){
		// log.info("tmpDEBUGtmp: " + "> " + "setFotoPerSelLs(" + fotoPerSelLs + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		this.fotoPerSelLs = fotoPerSelLs;
		// log.info("tmpDEBUGtmp: " + "< " + "setFotoPerSelLs");
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
		// log.info("tmpDEBUGtmp: " + "> " + "sortByNum(" + obj1 + ", " + obj2 + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		Integer o1 = (Integer)obj1;
		Integer o2 = (Integer)obj2;
		// log.info("sortById: " + o1 + "-" + o2);
		// log.info("tmpDEBUGtmp: " + "< " + "sortByNum");
		if(o1 < o2){
			return -1;
		}else if(o1 > o2){
			return 1;
		}
		return 0;
	}
	
	public int sortByStr(Object obj1, Object obj2){
		// log.info("tmpDEBUGtmp: " + "> " + "sortByStr(" + obj1 + ", " + obj2 + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		String o1 = (String)obj1;
		String o2 = (String)obj2;
		// log.info("sortByname: " + o1 + "-" + o2);
		// log.info("tmpDEBUGtmp: " + "< " + "sortByStr");
		if(o1.compareTo(o2) < 0){
			return -1;
		}else if(o1.compareTo(o2) > 0){
			return 1;
		}
		return 0;
	}
	
	public int sortByStrIC(Object obj1, Object obj2){
		// log.info("tmpDEBUGtmp: " + "> " + "sortByStrIC(" + obj1 + ", " + obj2 + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		String o1 = (String)obj1;
		String o2 = (String)obj2;
		// log.info("sortBynameIC: " + o1 + "-" + o2);
		// log.info("tmpDEBUGtmp: " + "< " + "sortByStrIC");
		if(o1.compareToIgnoreCase(o2) < 0){
			return -1;
		}else if(o1.compareToIgnoreCase(o2) > 0){
			return 1;
		}
		return 0;
	}
	
	public String size(Foto f, int t){
		// log.info("tmpDEBUGtmp: " + "> " + "size(" + f + ", " + t + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "size");
		return size(f, t, 0);
	}
	
	public String size(Foto f, int t, int u){
		// log.info("tmpDEBUGtmp: " + "> " + "size(" + f + ", " + t + ", " + u + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if(t == 0){
			t = tipoFotoNum;
		}
		String size = fotoDao.getSize(f, t, fileUploadedLs, u);
		// log.info("tmpDEBUGtmp: " + "< " + "size");
		return size;
	}
	
	public String resolution(Foto f, int t){
		// log.info("tmpDEBUGtmp: " + "> " + "resolution(" + f + ", " + t + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "resolution");
		return resolution(f, t, 0);
	}
	
	public String resolution(Foto f, int t, int u){
		// log.info("tmpDEBUGtmp: " + "> " + "resolution(" + f + ", " + t + ", " + u + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if(t == 0){
			t = tipoFotoNum;
		}
		String resolution = fotoDao.getResolution(f, t, fileUploadedLs, u);
		// log.info("tmpDEBUGtmp: " + "< " + "resolution");
		return resolution;
	}
	
	public int getHeightFromResolution(Foto f, int t, int h, int l){
		// log.info("tmpDEBUGtmp: " + "> " + "getHeightFromResolution(" + f + ", " + t + ", " + h + ", " + l + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getHeightFromResolution");
		return getHeightFromResolution(f, t, h, l, 0);
	}
	
	public int getHeightFromResolution(Foto f, int t, int h, int l, int u){
		// log.info("tmpDEBUGtmp: " + "> " + "getHeightFromResolution(" + f + ", " + t + ", " + h + ", " + l + ", " + u + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if(t == 0){
			t = tipoFotoNum;
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getHeightFromResolution");
		return fotoDao.getHeightFromResolution(f, t, h, l, u);
	}
	
	public String resolvePath(Foto f){
		// log.info("tmpDEBUGtmp: " + "> " + "resolvePath(" + f + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// return resolvePath(f, -1);
		// }
		//
		// public String resolvePath(Foto f, int i){
		// String path = fotoDao.resolvePath(f, i, fileUploadedLs);
		String path = fotoDao.resolvePath(f);
		// log.info("tmpDEBUGtmp: " + "< " + "resolvePath");
		return path;
	}
	
	public String getFotonameUrl(Foto f, int t){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotonameUrl(" + f + ", " + t + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getFotonameUrl");
		return getFotonameUrl(f, t, 0);
	}
	
	public String getFotonameUrl(Foto f, int t, int u){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotonameUrl(" + f + ", " + t + ", " + u + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		String nomePerUrl = "";
		nomePerUrl = getFotoname(f, t, u);
		// gestione dei simboli che creano anomalie nei nomi url (ci va %hx)
		if(StringUtils.contains(nomePerUrl, "%")){ // e' importante fare per primo %, altrimenti lo mettessi dopo tutti gli altri mi sostituirebbe i % dei precedenti
			nomePerUrl = StringUtils.replace(nomePerUrl, "%", "%25");
		}
		if(StringUtils.contains(nomePerUrl, "°")){
			nomePerUrl = StringUtils.replace(nomePerUrl, "°", "%B0");
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getFotonameUrl");
		return nomePerUrl;
	}
	
	public String getFotoname(Foto f, int t){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotoname(" + f + ", " + t + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getFotoname");
		return getFotoname(f, t, 0);
	}
	
	public String getFotoname(Foto f, int t, int u){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotoname(" + f + ", " + t + ", " + u + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if(t == 0){
			t = tipoFotoNum;
		}
		String name = fotoDao.getFotoname(f, t, fileUploadedLs, u);
		// log.info("tmpDEBUGtmp: " + "< " + "getFotoname");
		return name;
	}
	
	public void fileUploadOrig(FileUploadEvent event){
		// log.info("tmpDEBUGtmp: " + "> " + "fileUploadOrig(" + event + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		fileUpload(event, ".orig");
		// log.info("tmpDEBUGtmp: " + "< " + "fileUploadOrig");
	}
	
	public void fileUploadHD(FileUploadEvent event){
		// log.info("tmpDEBUGtmp: " + "> " + "fileUploadHD(" + event + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		fileUpload(event, ".hd");
		// log.info("tmpDEBUGtmp: " + "< " + "fileUploadHD");
	}
	
	public void fileUploadHDWM(FileUploadEvent event){
		// log.info("tmpDEBUGtmp: " + "> " + "fileUploadHDWM(" + event + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		fileUpload(event, ".hdwm");
		// log.info("tmpDEBUGtmp: " + "< " + "fileUploadHDWM");
	}
	
	public void fileUploadLD(FileUploadEvent event){
		// log.info("tmpDEBUGtmp: " + "> " + "fileUploadLD(" + event + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		fileUpload(event, ".ld");
		// log.info("tmpDEBUGtmp: " + "< " + "fileUploadLD");
	}
	
	public void fileUploadLDWM(FileUploadEvent event){
		// log.info("tmpDEBUGtmp: " + "> " + "fileUploadLDWM(" + event + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		fileUpload(event, ".ldwm");
		// log.info("tmpDEBUGtmp: " + "< " + "fileUploadLDWM");
	}
	
	public void fileUploadUndef(FileUploadEvent event){
		// log.info("tmpDEBUGtmp: " + "> " + "fileUploadUndef(" + event + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
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
		// log.info("tmpDEBUGtmp: " + "< " + "fileUploadUndef");
	}
	
	public void fileUpload(FileUploadEvent event, String type){
		// log.info("tmpDEBUGtmp: " + "> " + "fileUpload(" + event + ", " + type + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if(type.equals(".err")){
			return;
		}
		
		fotoDao.fileUpload(event, type, fileUploadedLs);
		
		showGrowlInfoMessage(event.getFile().getFileName() + " upload terminato");
		// log.info("tmpDEBUGtmp: " + "< " + "fileUpload");
	}
	
	public Boolean canDelete(){
		// log.info("tmpDEBUGtmp: " + "> " + "canDelete(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		if(tipoFotoNum == 1){
			return false;
		}
		// log.info("tmpDEBUGtmp: " + "< " + "canDelete");
		return true;
	}
	
	public List<Foto> getFotoLsDynPop(){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotoLsDynPop(" + ")");
		if(fotoLsDynPop == null){
			fotoLsDynPop = new ArrayList<Foto>();
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getFotoLsDynPop");
		return fotoLsDynPop;
	}
	
	public void setFotoLsDynPop(List<Foto> fotoLsDynPop){
		// log.info("tmpDEBUGtmp: " + "> " + "fotoLsDynPop(" + fotoLsDynPop + ")");
		this.fotoLsDynPop = fotoLsDynPop;
		// log.info("tmpDEBUGtmp: " + "< " + "fotoLsDynPop");
	}
	
	public void popolaFotoLsDynPop(){
		// fotoLsDynPop = fotoCondivisiController.getFotoLs();
		fotoLsDynPop = fotoCondivisiController.getFotoMostraPeSeAeILs();
	}
	
	public void svuotaFotoLsDynPop(){
		fotoLsDynPop = new ArrayList<Foto>();
	}
	
	public String getCosaMostra(){
		return cosaMostra;
	}
	
	public void setCosaMostra(String cosaMostra){
		this.cosaMostra = cosaMostra;
	}
	
	public void onChangeCosaMostra(){
		getFotoSel().setCosaMostra(cosaMostra);
	}
	
	public String getColor1(Foto foto){
		return StringUtils.substringBefore(getColor(foto), ";");
	}
	
	public String getColor2(Foto foto){
		return StringUtils.substringAfter(getColor(foto), ";");
	}
	
	public String getColor(Foto foto){
		return foto.getColore();
	}
	
	public String getColor1(){
		String colore = getFotoSel().getColore();
		if(StringUtils.contains(colore, ";")){
			colore = StringUtils.substringBefore(colore, ";");
		}
		color1 = colore;
		return color1;
	}
	
	public void setColor1(String color1){
		this.color1 = color1;
	}
	
	public void onChangeColor1(){
		String colore = getFotoSel().getColore();
		if(StringUtils.contains(colore, ";")){
			colore = StringUtils.substringAfter(colore, ";");
		}
		colore = color1 + ";" + colore;
		getFotoSel().setColore(colore);
	}
	
	public String getColor2(){
		String colore = getFotoSel().getColore();
		if(StringUtils.contains(colore, ";")){
			colore = StringUtils.substringAfter(colore, ";");
		}
		color2 = colore;
		return color2;
	}
	
	public void setColor2(String color2){
		this.color2 = color2;
	}
	
	public void onChangeColor2(){
		String colore = getFotoSel().getColore();
		if(StringUtils.contains(colore, ";")){
			colore = StringUtils.substringBefore(colore, ";");
		}
		colore = colore + ";" + color2;
		getFotoSel().setColore(colore);
	}
	
	public String getForma(){
		return forma;
	}
	
	public void setForma(String forma){
		this.forma = forma;
	}
	
	public void onChangeForma(){
		getFotoSel().setForma(forma);
	}
	
	public List<String> tagAutoCompleteLs(String str){
		List<String> tagAutoCompleteLs = new ArrayList<String>(fotoCondivisiController.getTagUtilizzatiLs());
		Iterator<String> iterTag = tagAutoCompleteLs.iterator();
		while(iterTag.hasNext()){
			String tag = iterTag.next();
			if(StringUtils.containsIgnoreCase(tag, str)){
				continue;
			}
			iterTag.remove();
		}
		return tagAutoCompleteLs;
	}
	
	public String getTagDaAggiungere(){
		return tagDaAggiungere;
	}
	
	public void setTagDaAggiungere(String tagDaAggiungere){
		this.tagDaAggiungere = tagDaAggiungere;
	}
	
	public void aggiungiTagAllaFoto(){
		if(!(StringUtils.isEmpty(tagDaAggiungere))){
			tagFotoSelLs.add(tagDaAggiungere);
			String tagPerLaFoto = getFotoSel().getTag();
			tagPerLaFoto = tagPerLaFoto + ";" + tagDaAggiungere;
			if(StringUtils.startsWith(tagPerLaFoto, "null;")){
				tagPerLaFoto = StringUtils.substring(tagPerLaFoto, 5);
			}
			getFotoSel().setTag(tagPerLaFoto);
		}
		tagDaAggiungere = "";
		log.info("tmpDEBUGtmp: " + "< " + "aggiungiTagAllaFoto");
	}
	
	public String getTagDaEliminare(){
		return tagDaEliminare;
	}
	
	public void setTagDaEliminare(String tagDaEliminare){
		this.tagDaEliminare = tagDaEliminare;
	}
	
	public void rimuoviTagAllaFoto(){
		if(!(StringUtils.isEmpty(tagDaEliminare))){
			tagFotoSelLs.remove(tagDaEliminare);
			String tagPerLaFoto = "#;" + getFotoSel().getTag() + ";#";
			tagPerLaFoto = StringUtils.replace(tagPerLaFoto, tagDaEliminare + ";", "#");
			tagPerLaFoto = StringUtils.replace(tagPerLaFoto, ";#", "#");
			tagPerLaFoto = StringUtils.replace(tagPerLaFoto, "#;", "#");
			tagPerLaFoto = StringUtils.remove(tagPerLaFoto, "#");
			getFotoSel().setTag(tagPerLaFoto);
		}
		log.info("tmpDEBUGtmp: " + "< " + "rimuoviTagAllaFoto");
	}
	
	public List<String> getTagFotoSelLs(){
		log.info("tmpDEBUGtmp: " + "> " + "getTagFotoSelLs(" + ")");
		String tag = getFotoSel().getTag();
		if(tag == null){
			tag = "";
		}
		String tagAr[] = StringUtils.split(tag, ";");
		tagFotoSelLs = new ArrayList<String>(Arrays.asList(tagAr));
		return tagFotoSelLs;
	}
	
	public List<String> getTagFotoSelLs(Foto foto){
		String tag = foto.getTag();
		if(tag == null){
			tag = "";
		}
		String tagAr[] = StringUtils.split(tag, ";");
		List<String> tagFotoSelLs = Arrays.asList(tagAr);
		return tagFotoSelLs;
	}
	
	public void setTagFotoSelLs(List<String> tagFotoSelLs){
		this.tagFotoSelLs = tagFotoSelLs;
	}
	
	public boolean filterPerTipoDelloArticolo(Object value, Object filter, Locale locale){
		// log.info("tmpDEBUGtmp: " + "> " + "filterArticoloCodice(" + value + ", " + filter + ", " + locale + ")");
		Integer filterNumb = null;
		try{
			filterNumb = (filter == null) ? null : Integer.parseInt(filter.toString().trim().toUpperCase(locale));
		}catch(NumberFormatException e){
			return true;
		}
		if(filterNumb == null){
			return true;
		}
		if(value == null){
			return false;
		}
		Integer valueNumb = Integer.parseInt(value.toString().trim().toUpperCase(locale));
		
		List<Integer> idTipoLs = getTipoLsDaIdFoto(valueNumb);
		Iterator<Integer> iterTipoLs = idTipoLs.iterator();
		while(iterTipoLs.hasNext()){
			Integer idTipo = iterTipoLs.next();
			if(filterNumb.equals(idTipo)){
				return true;
			}
			if(fotoDao.getTipoDiscendeDaTipo(idTipo, filterNumb)){
				return true;
			}
		}
		return false;
	}
	
	public boolean filterPerCaratteristiche(Object value, Object filter, Locale locale){
		String filterString = (filter == null) ? null : filter.toString().trim().toUpperCase(locale);
		if(filterString == null){
			return true;
		}
		if(value == null){
			return true;
		}
		
		String filtroSplit[] = StringUtils.split(filterString, "-");
		
		if(filtroSplit.length == 0){
			return true;
		}
		
		// tag1,tag2,tag3-c1.bianco-c2.nero-f.irr-t.S
		String tag[] = null;
		String campo[] = null;
		String c1 = "ind";
		String c2 = "ind";
		String f = "ind";
		String t = "I";
		
		for(int i = 0; i < filtroSplit.length; i++){
			if(StringUtils.contains(filtroSplit[i], ".")){ // non e' tag, puo' essere qualsiasi degli altri
				campo = StringUtils.split(filtroSplit[i], ".");
				if(campo.length == 2){
					if(StringUtils.equals(StringUtils.trim(campo[0]), "C") || StringUtils.equals(StringUtils.trim(campo[0]), "C1")){
						c1 = StringUtils.trim(campo[1]);
					}else if(StringUtils.equals(StringUtils.trim(campo[0]), "CC") || StringUtils.equals(StringUtils.trim(campo[0]), "C2")){
						c2 = StringUtils.trim(campo[1]);
					}else if(StringUtils.equals(StringUtils.trim(campo[0]), "F")){
						f = StringUtils.trim(campo[1]);
					}else if(StringUtils.equals(StringUtils.trim(campo[0]), "T")){
						t = StringUtils.trim(campo[1]);
					}
				}
			}else{ // tag
				tag = StringUtils.split(filtroSplit[i], ",");
				for(int j = 0; j < tag.length; j++){
					tag[j] = StringUtils.trim(tag[j]);
				}
			}
		}
		
		Foto valueFoto = (Foto)value;
		Boolean filtroEsito = true;
		if(tag != null){
			for(int j = 0; j < tag.length; j++){
				if(!(StringUtils.contains(StringUtils.defaultIfEmpty(valueFoto.getTag(), "").toUpperCase(locale), tag[j]))){
					filtroEsito = false;
					return filtroEsito;
				}
			}
		}
		if(!(StringUtils.equals(c1, "ind"))){
			String col1 = StringUtils.trim(getColor1(valueFoto));
			if(StringUtils.equals(col1, "ind")){	// io cerco un colore specifico ma la foto l'ha indefinito
				return false;
			}
			filtroEsito = false;
			if(StringUtils.startsWith(c1, "NE") && (StringUtils.equals(col1, "000000"))){
				filtroEsito = true;
			}else if(StringUtils.startsWith(c1, "BI") && (StringUtils.equals(col1, "FFFFFF"))){
				filtroEsito = true;
			}else if(StringUtils.startsWith(c1, "RO") && (StringUtils.equals(col1, "FF0000"))){
				filtroEsito = true;
			}else if(StringUtils.startsWith(c1, "BL") && (StringUtils.equals(col1, "0000FF"))){
				filtroEsito = true;
			}else if((StringUtils.startsWith(c1, "GR") || StringUtils.startsWith(c1, "FE") || StringUtils.startsWith(c1, "AR")) && (StringUtils.equals(col1, "808080"))){
				filtroEsito = true;
			}else if(StringUtils.startsWith(c1, "VE") && (StringUtils.equals(col1, "008000"))){
				filtroEsito = true;
			}else if((StringUtils.startsWith(c1, "GI") || StringUtils.startsWith(c1, "OR") || StringUtils.startsWith(c1, "BR") || StringUtils.startsWith(c1, "RA")) && (StringUtils.equals(col1, "FFFF00"))){
				filtroEsito = true;
			}else if((StringUtils.startsWith(c1, "CE") || StringUtils.startsWith(c1, "AZ")) && (StringUtils.equals(col1, "00FFFF"))){
				filtroEsito = true;
			}else if(StringUtils.startsWith(c1, "AR") && (StringUtils.equals(col1, "FF8000"))){
				filtroEsito = true;
			}else if(StringUtils.startsWith(c1, "VI") && (StringUtils.equals(col1, "FF00FF"))){
				filtroEsito = true;
			}else if(StringUtils.startsWith(c1, "MA") && (StringUtils.equals(col1, "964B00"))){
				filtroEsito = true;
			}
			if(!filtroEsito){
				return filtroEsito;
			}
		}
		if(!(StringUtils.equals(c2, "ind"))){
			String col2 = StringUtils.trim(getColor2(valueFoto));
			if(StringUtils.equals(col2, "ind")){	// io cerco un colore specifico ma la foto l'ha indefinito
				return false;
			}
			filtroEsito = false;
			if(StringUtils.startsWith(c2, "NE") && (StringUtils.equals(col2, "000000"))){
				filtroEsito = true;
			}else if(StringUtils.startsWith(c2, "BI") && (StringUtils.equals(col2, "FFFFFF"))){
				filtroEsito = true;
			}else if(StringUtils.startsWith(c2, "RO") && (StringUtils.equals(col2, "FF0000"))){
				filtroEsito = true;
			}else if(StringUtils.startsWith(c2, "BL") && (StringUtils.equals(col2, "0000FF"))){
				filtroEsito = true;
			}else if((StringUtils.startsWith(c2, "GR") || StringUtils.startsWith(c2, "FE") || StringUtils.startsWith(c2, "AR")) && (StringUtils.equals(col2, "808080"))){
				filtroEsito = true;
			}else if(StringUtils.startsWith(c2, "VE") && (StringUtils.equals(col2, "008000"))){
				filtroEsito = true;
			}else if((StringUtils.startsWith(c2, "GI") || StringUtils.startsWith(c2, "OR") || StringUtils.startsWith(c2, "BR") || StringUtils.startsWith(c2, "RA")) && (StringUtils.equals(col2, "FFFF00"))){
				filtroEsito = true;
			}else if((StringUtils.startsWith(c2, "CE") || StringUtils.startsWith(c2, "AZ")) && (StringUtils.equals(col2, "00FFFF"))){
				filtroEsito = true;
			}else if(StringUtils.startsWith(c2, "AR") && (StringUtils.equals(col2, "FF8000"))){
				filtroEsito = true;
			}else if(StringUtils.startsWith(c2, "VI") && (StringUtils.equals(col2, "FF00FF"))){
				filtroEsito = true;
			}else if(StringUtils.startsWith(c2, "MA") && (StringUtils.equals(col2, "964B00"))){
				filtroEsito = true;
			}
			if(!filtroEsito){
				return filtroEsito;
			}
		}
		if(!(StringUtils.equals(f, "ind"))){
			String forma =  StringUtils.left(StringUtils.trim(StringUtils.defaultIfEmpty(valueFoto.getForma(), "").toUpperCase(locale)), 2);
			if(StringUtils.equals(forma, "ind")){	// io cerco una forma specifica ma la foto l'ha indefinita
				return false;
			}
			filtroEsito = false;
			if((StringUtils.startsWith(f, "QU") || StringUtils.startsWith(f, "SQ")) && (StringUtils.equals(forma, "SQ"))){
				filtroEsito = true;
			}else if((StringUtils.startsWith(f, "TO") || StringUtils.startsWith(f, "RO") || StringUtils.startsWith(f, "CE")) && (StringUtils.equals(forma, "TO"))){
				filtroEsito = true;
			}else if((StringUtils.startsWith(f, "TR") || StringUtils.startsWith(f, "AN")) && (StringUtils.equals(forma, "AN"))){
				filtroEsito = true;
			}else if((StringUtils.startsWith(f, "LI") || StringUtils.startsWith(f, "LO")) && (StringUtils.equals(forma, "LO"))){
				filtroEsito = true;
			}else if((StringUtils.startsWith(f, "IR") || StringUtils.startsWith(f, "BO")) && (StringUtils.equals(forma, "IR"))){
				filtroEsito = true;
			}
			if(!filtroEsito){
				return filtroEsito;
			}
		}
		if(!(StringUtils.equals(t, "I"))){
			if(!(StringUtils.equals(StringUtils.defaultIfEmpty(valueFoto.getCosaMostra(), "").toUpperCase(locale), t))){
				filtroEsito = false;
			}
			if(!filtroEsito){
				return filtroEsito;
			}
		}
		
		return filtroEsito;
	}
	
	// ***** inizio Foto *****
	public Foto getFotoDaIdFoto(Integer idFoto){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotoDaIdFoto(" + idFoto + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getFotoDaIdFoto");
		return fotoDao.getFotoDaIdFoto(idFoto);
	}
	
	// ***** fine Foto *****
	
	// ***** inizio Tipi *****
	public Foto getFotoTipoDaIdTipo(Integer idTipo){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotoTipoDaIdTipo(" + idTipo + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		Foto fotoTipo = new Foto();
		fotoTipo = fotoDao.getFotoTipoDaIdTipo(idTipo);
		if(fotoTipo != null){
			esito = "selezionata foto " + fotoTipo.getId() + " per tipo: " + idTipo;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto!" + " (tipo: " + idTipo + ")";
			showGrowlErrorMessage();
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getFotoTipoDaIdTipo");
		return fotoTipo;
	}
	
	public Foto getFotoTipoDaIdArticolo(Integer idArticolo){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotoTipoDaIdArticolo(" + idArticolo + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		Foto fotoTipo = new Foto();
		fotoTipo = fotoDao.getFotoTipoDaIdArticolo(idArticolo);
		if(fotoTipo != null){
			esito = "selezionata foto " + fotoTipo.getId() + " per articolo: " + idArticolo;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto!" + " (articolo: " + idArticolo + ")";
			showGrowlErrorMessage();
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getFotoTipoDaIdArticolo");
		return fotoTipo;
	}
	
	public List<Integer> getTipoLsDaIdFoto(Integer idFoto){
		List<Integer> idTipoLs = fotoDao.getTipoLsDaIdFoto(idFoto);
		return idTipoLs;
	}
	
	// ***** fine Tipi *****
	
	// ***** inizio Produttori *****
	public Foto getFotoProduttoreDaIdProduttore(Integer idProduttore){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotoProduttoreDaIdProduttore(" + idProduttore + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		Foto fotoProduttore = new Foto();
		fotoProduttore = fotoDao.getFotoProduttoreDaIdProduttore(idProduttore);
		if(fotoProduttore != null){
			esito = "selezionata foto " + fotoProduttore.getId() + " per produttore: " + idProduttore;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto!" + " (produttore: " + idProduttore + ")";
			showGrowlErrorMessage();
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getFotoProduttoreDaIdProduttore");
		return fotoProduttore;
	}
	
	public Foto getFotoProduttoreDaIdArticolo(Integer idArticolo){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotoProduttoreDaIdArticolo(" + idArticolo + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		Foto fotoProduttore = new Foto();
		fotoProduttore = fotoDao.getFotoProduttoreDaIdArticolo(idArticolo);
		if(fotoProduttore != null){
			esito = "selezionata foto " + fotoProduttore.getId() + " per articolo: " + idArticolo;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto!" + " (articolo: " + idArticolo + ")";
			showGrowlErrorMessage();
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getFotoProduttoreDaIdArticolo");
		return fotoProduttore;
	}
	
	// ***** fine Produttori *****
	
	// ***** inizio Articoli *****
	public Foto getFotoArticoloDaIdArticolo(Integer idArticolo){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotoArticoloDaIdArticolo(" + idArticolo + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		Foto fotoArticolo = new Foto();
		fotoArticolo = fotoDao.getFotoArticoloDaIdArticolo(idArticolo);
		if(fotoArticolo != null){
			esito = "selezionata foto " + fotoArticolo.getId() + " per articolo: " + idArticolo;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto!" + " (articolo: " + idArticolo + ")";
			showGrowlErrorMessage();
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getFotoArticoloDaIdArticolo");
		return fotoArticolo;
	}
	
	public List<Foto> getFotoArticoloOrdLsDaIdArticolo(Integer idArticolo){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotoArticoloOrdLsDaIdArticolo(" + idArticolo + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		List<Foto> fotoArticolo = new ArrayList<Foto>();
		fotoArticolo = fotoDao.getFotoArticoloOrdLsDaIdArticolo(idArticolo);
		if(fotoArticolo != null){
			esito = "selezionate " + fotoArticolo.size() + " foto per articolo: " + idArticolo;
			showGrowlInfoMessage(esito);
		}else{
			esito = "non e' stata trovata la foto!" + " (articolo: " + idArticolo + ")";
			showGrowlErrorMessage();
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getFotoArticoloOrdLsDaIdArticolo");
		return fotoArticolo;
	}
	// ***** fine Articoli *****
	
}

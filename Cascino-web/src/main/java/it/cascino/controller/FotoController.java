package it.cascino.controller;

import it.cascino.dao.FotoDao;
import it.cascino.model.Foto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
	
	private List<Foto> fotoLs;
	private List<Foto> filteredFotoLs;
	
	private Foto fotoSel = new Foto();
	
	// contiene, per l'elemento selezionato, le foto originale, HD, HD wm, LD, LD wm in lista, per essere utilizzate nella gallery
	private List<Foto> fotoPerSelLs;
	
	private String tipoFoto;
	private int tipoFotoNum;
	
	private boolean modifO = false;
	private boolean modifHD = false;
	private boolean modifHDwm = false;
	private boolean modifLD = false;
	private boolean modifLDwm = false;
	
	public void onChangeTipoFoto(){
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
	}
	
	public String getTipoFoto(){
		if((tipoFoto == null) || (tipoFoto.isEmpty())){
			tipoFotoNum = 1;
			tipoFoto = "orig";
		}
		return tipoFoto;
	}
	
	public void setTipoFoto(String tipoFoto){
		this.tipoFoto = tipoFoto;
	}
	
	public List<Foto> getFotoLs(){
		fotoLs = fotoDao.getAll();
		return fotoLs;
	}
	
	public void setFotoLs(List<Foto> fotoLs){
		this.fotoLs = fotoLs;
	}
	
	public Foto getFotoSel(){
		return fotoSel;
	}
	
	public void setFotoSel(Foto fotoSel){
		this.fotoSel = fotoSel;
	}
	
	public List<Foto> getFilteredFotoLs(){
		return filteredFotoLs;
	}
	
	public void setFilteredFotoLs(List<Foto> filteredFotoLs){
		this.filteredFotoLs = filteredFotoLs;
	}
	
	public void salva(){
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
	}
	
	public void annullaUpdate(){
		Boolean risp = fotoDao.annullaUpdate(fileUploadedLs);
		if(risp){
			esito = "Annullamento foto eseguito";
			showGrowlDelMessage();
		}else{
			esito = "non sono state caricate le foto!";
			showGrowlErrorMessage();
		}
		fileUploadedLs.clear();
	}
	
	public void annullaUpload(){
		Boolean risp = fotoDao.annullaUpload(fileUploadedLs);
		if(risp){
			esito = "Annullamento foto eseguito";
			showGrowlDelMessage();
		}else{
			esito = "non sono state caricate le foto!";
			showGrowlErrorMessage();
		}
		fileUploadedLs.clear();
	}
	
	public void annullaUploadUndef(){
		annullaUploadUndef(0, 0);
	}
	
	public void annullaUploadUndef(int t, int u){
		if(t == 0){
			t = tipoFotoNum;
		}
		
		Boolean risp = fotoDao.annullaUploadUndef(t, fileUploadedLs, u);
		if(risp){
			esito = "Annullamento foto eseguito";
			showGrowlDelMessage();
		}else{
			esito = "non sono state caricate le foto!";
			showGrowlErrorMessage();
		}
		// fileUploadedLs.clear();
	}
	
	public void aggiorna(){
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
	}
	
	public void elimina(){
		fotoDao.elimina(fotoSel, fileUploadedLs);
		if(fotoSel != null){
			esito = "Elimino foto: " + fotoSel.getOriginale();
			showGrowlDelMessage();
		}else{
			esito = "non ho trovato la foto!";
			showGrowlErrorMessage();
		}
	}
	
	public void eliminaSingFoto(){
		eliminaSingFoto(0, 1);
	}

	public void eliminaSingFoto(int t, int u){
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
	}
	
	public String getEsito(){
		return esito;
	}
	
	public List<Foto> getFotoPerSelLs(){
		fotoPerSelLs = fotoDao.getFotoListPerSel(fotoSel);
		return fotoPerSelLs;
	}
	
	public void setFotoPerSelLs(List<Foto> fotoPerSelLs){
		this.fotoPerSelLs = fotoPerSelLs;
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
	
	private void showGrowlErrorMessage(){
		String message = "Operazione fallita - " + esito + " >" + fotoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", message));
		log.error(message);
	}
	
	public int sortByNum(Object obj1, Object obj2){
		Integer o1 = (Integer)obj1;
		Integer o2 = (Integer)obj2;
		log.info("sortById: " + o1 + "-" + o2);
		if(o1 < o2){
			return -1;
		}else if(o1 > o2){
			return 1;
		}
		return 0;
	}
	
	public int sortByStr(Object obj1, Object obj2){
		String o1 = (String)obj1;
		String o2 = (String)obj2;
		log.info("sortByname: " + o1 + "-" + o2);
		if(o1.compareTo(o2) < 0){
			return -1;
		}else if(o1.compareTo(o2) > 0){
			return 1;
		}
		return 0;
	}
	
	public int sortByStrIC(Object obj1, Object obj2){
		String o1 = (String)obj1;
		String o2 = (String)obj2;
		log.info("sortByname: " + o1 + "-" + o2);
		if(o1.compareToIgnoreCase(o2) < 0){
			return -1;
		}else if(o1.compareToIgnoreCase(o2) > 0){
			return 1;
		}
		return 0;
	}
	
	public String size(Foto f, int t){
		return size(f, t, 0);
	}

	public String size(Foto f, int t, int u){
		if(t == 0){
			t = tipoFotoNum;
		}
		String size = fotoDao.getSize(f, t, fileUploadedLs, u);
		return size;
	}
	
	public String resolution(Foto f, int t){
		return resolution(f, t, 0);
	}
	
	public String resolution(Foto f, int t, int u){
		if(t == 0){
			t = tipoFotoNum;
		}
		String resolution = fotoDao.getResolution(f, t, fileUploadedLs, u);
		return resolution;
	}
	
	public String resolvePath(Foto f){
//		return resolvePath(f, -1);
//	}
//	
//	public String resolvePath(Foto f, int i){
//		String path = fotoDao.resolvePath(f, i, fileUploadedLs);
		String path = fotoDao.resolvePath(f);
		return path;
	}
	
	public String getFotoname(Foto f, int t){
		return getFotoname(f, t, 0);
	}

	public String getFotoname(Foto f, int t, int u){
		if(t == 0){
			t = tipoFotoNum;
		}
		String name = fotoDao.getFotoname(f, t, fileUploadedLs, u);
		return name;
	}

	private List<UploadedFile> fileUploadedLs = new ArrayList<UploadedFile>();;
	
	public void fileUploadOrig(FileUploadEvent event){
		fileUpload(event, ".orig");
	}
	
	public void fileUploadHD(FileUploadEvent event){
		fileUpload(event, ".hd");
	}
	
	public void fileUploadHDWM(FileUploadEvent event){
		fileUpload(event, ".hdwm");
	}
	
	public void fileUploadLD(FileUploadEvent event){
		fileUpload(event, ".ld");
	}
	
	public void fileUploadLDWM(FileUploadEvent event){
		fileUpload(event, ".ldwm");
	}
	
	public void fileUploadUndef(FileUploadEvent event){
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
	}
	
	public void fileUpload(FileUploadEvent event, String type){
		if(type.equals(".err")){
			return;
		}
		
		fotoDao.fileUpload(event, type, fileUploadedLs);
			
		showGrowlInfoMessage(event.getFile().getFileName() + " upload terminato");
	}
	
	public Boolean canDelete(){
		if(tipoFotoNum == 1){
			return false;
		}
		return true;
	}
}

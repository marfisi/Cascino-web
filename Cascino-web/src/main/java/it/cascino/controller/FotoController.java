package it.cascino.controller;

import it.cascino.dao.FotoDao;
import it.cascino.model.Foto;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
	
	public void annullaUpload(){
		Boolean risp = fotoDao.annullaUpload(fileUploadedLs);
		if(risp){
			esito = "Annulamento foto eseguito";
			showGrowlDelMessage();
		}else{
			esito = "non sono state caricate le foto! la foto orignale e' obbligatoria";
			showGrowlErrorMessage();
		}
		fileUploadedLs.clear();
	}
	
	public void aggiorna(){
		// fotoSel.setTipoPadre(getPadreFromId());
		
		fotoDao.aggiorna(fotoSel);
		if(fotoSel != null){
			esito = "Aggiorno foto: " + fotoSel.getOriginale();
			showGrowlUpdMessage();
		}else{
			esito = "non ho trovato la foto!";
			showGrowlErrorMessage();
		}
	}
	
	public void elimina(){
		fotoDao.elimina(fotoSel);
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
		String size = fotoDao.getSize(f, t);
		return size;
	}
	
	public String resolution(Foto f, int t){
		String resolution = fotoDao.getResolution(f, t);
		return resolution;
	}
	
	public String resolvePath(Foto f){
		String path = fotoDao.resolvePath(f);
		return path;
	}
	
	public String getFotoname(Foto f, int t){
		String name = fotoDao.getFotoname(f, t);
		return name;
	}
	
	private List<UploadedFile> fileUploadedLs =  new ArrayList<UploadedFile>();;

	public void fileUploadOrig(FileUploadEvent event){
		fileUpload(event, ".orig");
	}
	
	public void fileUploadHD(FileUploadEvent event){
		fileUpload(event, ".hd");
	}

	public void fileUploadHDWM(FileUploadEvent event){
		fileUpload(event, ".hdw");
	}
	
	public void fileUploadLD(FileUploadEvent event){
		fileUpload(event, ".ld");
	}

	public void fileUploadLDWM(FileUploadEvent event){
		fileUpload(event, ".ldw");
	}
	
	public void fileUpload(FileUploadEvent event, String type){
		UploadedFile fileOriginale = event.getFile();
		
		try{
			copyFile(fileOriginale.getFileName()+type, fileOriginale.getInputstream());
		}catch(IOException e){
			e.printStackTrace();
		}
		
		fileUploadedLs.add(fileOriginale);
		
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", event.getFile().getFileName() + " upload terminato"));
	}
	
	public void copyFile(String fileName, InputStream in){
		try{
			File targetFolder = new File("c:\\dev\\foto\\uploadTmp\\");
			
			OutputStream out = new FileOutputStream(new File(targetFolder, fileName));
			
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while((read = in.read(bytes)) != -1){
				out.write(bytes, 0, read);
			}
			
			in.close();
			out.flush();
			out.close();
			
			log.info("nuovo file copiato/creato!");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
}

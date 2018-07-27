package it.cascino.dbas.controller;

import it.cascino.dbas.dao.AsAnmar0fDao;
//import it.cascino.model.Articoli;
import it.cascino.dbas.model.AsAnmar0f;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

@Named
@SessionScoped
public class AsAnmar0fController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
//	@Inject
//	private FacesContext facesContext;
	
	@Inject
	private AsAnmar0fDao asAnmar0fDao;
	
//	private String esito;
//	
	@Inject
	private AsAnmar0fCondivisiController asAnmar0fCondivisiController; 
	
	private List<AsAnmar0f> asAnmar0fLs;
	private List<AsAnmar0f> filteredAsAnmar0fLs;

	private AsAnmar0f asAnmar0fSel = new AsAnmar0f();
	private AsAnmar0f asAnmar0fNew = new AsAnmar0f();
	
	private final String dirFoto = "c:\\dev\\foto\\";	// "./resources/gfx/foto/";
	
	private String testoArticoloFiltro;
	
	public List<AsAnmar0f> getAsAnmar0fLs(){
		return asAnmar0fLs;
	}
	
	public void setAsAnmar0fLs(List<AsAnmar0f> asAnmar0fLs){
		this.asAnmar0fLs = asAnmar0fLs;
	}
	
	public AsAnmar0f getAsAnmar0fSel(){
		return asAnmar0fSel;
	}
	
	@PostConstruct
	public void aggiornaAsAnmar0fLs(){
//		asAnmar0fCondivisiController.aggiornaAsAnmar0fLs();
//		setAsAnmar0fLs(asAnmar0fCondivisiController.getAsAnmar0fLs());
		setAsAnmar0fLs(new ArrayList<AsAnmar0f>(asAnmar0fCondivisiController.getAsAnmar0fLs()));
	}
	
	public void setAsAnmar0fSel(AsAnmar0f asAnmar0fSel){
		// log.info("tmpDEBUGtmp: " + "> " + "setNodoSel(" + nodoSel + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((AsAnmar0f)nodoSel.getData()).getId() : "null"));
		if(asAnmar0fSel != null){
			this.asAnmar0fSel = asAnmar0fSel;
		}
		// log.info("tmpDEBUGtmp: " + "< " + "setNodoSel");
	}
	
	public AsAnmar0f getAsAnmar0fNew(){
		return asAnmar0fNew;
	}
	
	public void setAsAnmar0fNew(AsAnmar0f asAnmar0fNew){
		if(asAnmar0fNew != null){
			this.asAnmar0fNew = asAnmar0fNew;
		}
	}
	
	// chiamata quando faccio nuovo, per non avere i campi sporchi da una selezione che deriva dalla tabella
	public void resetOnNewAsAnmar0fSel(){
		AsAnmar0f o = new AsAnmar0f();
		o.setMcomp("00000");
		asAnmar0fSel = o;
	}
	
	public List<AsAnmar0f> getFilteredAsAnmar0fLs(){
		// log.info("tmpDEBUGtmp: " + "> " + "getFilteredFotoLs(" + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "getFilteredFotoLs");
		return filteredAsAnmar0fLs;
	}
	
	public void setFilteredAsAnmar0fLs(List<AsAnmar0f> filteredAsAnmar0fLs){
		// log.info("tmpDEBUGtmp: " + "> " + "setFilteredFotoLs(" + filteredFotoLs + ")");
		// log.info("tmpDEBUGtmp: " + "id: " + ((fotoSel != null) ? fotoSel.getId() : "null"));
		// log.info("tmpDEBUGtmp: " + "< " + "setFilteredFotoLs");
		this.filteredAsAnmar0fLs = filteredAsAnmar0fLs;
	}
	
//	public String getEsito(){
//		// log.info("tmpDEBUGtmp: " + "> " + "getEsito(" + ")");
//		// log.info("tmpDEBUGtmp: " + "id: " + ((nodoSel != null) ? ((AsAnmar0f)nodoSel.getData()).getId() : "null"));
//		// log.info("tmpDEBUGtmp: " + "< " + "getEsito");
//		return esito;
//	}
//	
//	private void showGrowlInfoMessage(String message){
//		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
//		log.info(message);
//	}
//	
//	private void showGrowlUpdMessage(){
//		String message = "Aggiornato con successo - " + esito + " >" + asAnmar0fSel + "<";
//		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
//		log.info(message);
//	}
//	
//	private void showGrowlInsMessage(){
//		String message = "Inserito con successo - " + esito + " >" + asAnmar0fNew + "<";
//		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
//		log.info(message);
//	}
//	
//	private void showGrowlDelMessage(){
//		String message = "Eliminato con successo - " + esito + " >" + asAnmar0fSel + "<";
//		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
//		log.info(message);
//	}
//	
//	private void showGrowlErrorMessage(){
//		String message = "Operazione fallita - " + esito + " >" + asAnmar0fSel + "<";
//		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", message));
//		log.error(message);
//	}
	
	public String trimString(String str){
		return StringUtils.trim(str);
	}
	
	private BufferedImage manageFileJpeg(File f) throws IOException, NullPointerException{
		// log.info("tmpDEBUGtmp: " + "> " + "manageFileJpeg(" + f + ")");
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("JPEG");
		ImageReader reader = null;
		while(readers.hasNext()){
			reader = (ImageReader)readers.next();
			if(reader.canReadRaster()){
				break;
			}
		}
		// Stream the image file (the original CMYK image)
		ImageInputStream input = null;
		
		input = ImageIO.createImageInputStream(f);
		
		reader.setInput(input);
		
		// Read the image raster
		Raster raster = null;
		raster = reader.readRaster(0, null);
		
		// Create a new RGB image
		BufferedImage bi = new BufferedImage(raster.getWidth(), raster.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		
		// Fill the new image with the old raster
		// bi.getRaster().setRect(raster);
		// log.info("tmpDEBUGtmp: " + "< " + "manageFileJpeg");
		return bi;
	}
	
	public int getHeightFromResolution(String fname, int h, int w){
		if(StringUtils.isBlank(fname)){
			return 1;
		}
		fname = StringUtils.trim(fname);
		
		File f = new File(dirFoto + fname);
		
		int height = 0;
		float rapportoTarget = 0.0f;
		float rapportoH = 0.0f;
		float rapportoW = 0.0f;
		
		BufferedImage fimg = null;
		try{
			if((StringUtils.containsIgnoreCase(f.getPath(), "jpg")) || (StringUtils.containsIgnoreCase(f.getPath(), "jpeg"))){
				// gestisce i file jpeg, che se sono in formato CMYK, con ImageIO.read vanno in eccezione
				fimg = manageFileJpeg(f);
			}else{
				fimg = ImageIO.read(f);
			}
		}catch(IOException e){
			log.error("file " + f.getName() + " gestito con eccezione");
			e.printStackTrace();
		}catch(Exception e){
			log.error("File: " + f.getName());
			return 1;
		}
		// log.info("tmpDEBUGtmp: " + "< " + "getResolution");
		String resolution = (fimg == null) ? "n.d." : fimg.getWidth() + "x" + fimg.getHeight() + "px";

		if(StringUtils.equals(resolution, "n.d.")){
			return h;
		}
		int imgH = Integer.parseInt(resolution.substring(resolution.indexOf("x") + 1, resolution.indexOf("px")));
		int imgW = Integer.parseInt(resolution.substring(0, resolution.indexOf("x")));
		rapportoH = (float)imgH / h;
		rapportoW = (float)imgW / w;
		
		if(rapportoH > rapportoW){
			rapportoTarget = rapportoH;
		}else{
			rapportoTarget = rapportoW;
		}
		
		height = (int)Math.floor(imgH / rapportoTarget);
		
		// log.info("tmpDEBUGtmp: " + "< " + "getHeightFromResolution");
		return height;
	}
	
	public AsAnmar0f getGruppoDaMcomp(String mcomp){
		return asAnmar0fDao.getGruppoDaMcomp(mcomp);
	}
	
//	public void filterListener(FilterEvent filterEvent){
//		  log.info(filterEvent.toString());
//		  log.info(filterEvent.getSource().toString());
//		  log.info(asAnmar0fCondivisiController.getAsAnmar0fLs().size());
//		  
//		  setFilteredAsAnmar0fLs(asAnmar0fCondivisiController.getAsAnmar0fLs().subList(0, 10));
//	
//		  
//		  log.info(filteredAsAnmar0fLs.size());
//	}
		
	public void filtraArticoli(){
		setAsAnmar0fLs(new ArrayList<AsAnmar0f>(asAnmar0fCondivisiController.getAsAnmar0fLs()));
		
		log.info("filtro: " + getTestoArticoloFiltro());

		if(StringUtils.isBlank(getTestoArticoloFiltro())){
			return;
		}
		log.info(getAsAnmar0fLs().size());
		
//		setTestoArticoloFiltro("TX180");
		String mcompString = asAnmar0fDao.getStringaMcompDaArticolo(getTestoArticoloFiltro());
		log.info("filtro: " + mcompString);
		
		AsAnmar0f asAnmar0f = null;
		Iterator<AsAnmar0f> iter_asAnmar0f = getAsAnmar0fLs().iterator();
		while(iter_asAnmar0f.hasNext()){
			asAnmar0f = iter_asAnmar0f.next();
			if(!(StringUtils.contains(mcompString, asAnmar0f.getMcomp()))){
				iter_asAnmar0f.remove();
			}
		}
		log.info(getAsAnmar0fLs().size());
		log.info(asAnmar0fCondivisiController.getAsAnmar0fLs().size());
	}
	
	public void filtraArticoliCancella(){
		//setAsAnmar0fLs(asAnmar0fCondivisiController.getAsAnmar0fLs());
		aggiornaAsAnmar0fLs();
		setTestoArticoloFiltro(null);
		log.info(getAsAnmar0fLs().size());
		log.info(asAnmar0fCondivisiController.getAsAnmar0fLs().size());
	}
	
	public String getTestoArticoloFiltro(){
		return testoArticoloFiltro;
	}

	public void setTestoArticoloFiltro(String testoArticoloFiltro){
		this.testoArticoloFiltro = testoArticoloFiltro;
	}

}
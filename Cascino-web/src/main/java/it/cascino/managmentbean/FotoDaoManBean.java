package it.cascino.managmentbean;

import it.cascino.dao.FotoDao;
import it.cascino.model.Foto;
import it.cascino.util.CommonsUtility;
import it.cascino.util.Utility;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@SessionScoped
public class FotoDaoManBean implements FotoDao, Serializable{
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
	
	private final String fotoNotDefined = "n.d..jpg";
	private final String dirFoto = "c:\\dev\\foto";	// this.getClass().getClassLoader().getResource(fotoNotDefined).getPath();
	// private String dirFotoUpload = "c:\\dev\\foto\\uploadTmp";
	private final String dirFotoUri = ".\\resources\\gfx\\foto\\";
	
	private List<File> fotoDeletedLs = new ArrayList<File>();
		
	@SuppressWarnings("unchecked")
	public List<Foto> getAll(){
		log.info("tmpDEBUGtmp: " + "> " + "getAll(" + ")");
		List<Foto> foto = null;
		try{
			try{
				utx.begin();
				// String sql = "FROM Foto f";
				// Query query = entityManager.createQuery(sql);
				Query query = entityManager.createNamedQuery("Foto.findAll");
				foto = (List<Foto>)query.getResultList();
			}catch(NoResultException e){
				foto = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "getAll");
		return foto;
	}
	
	public String getDirFoto(){
		log.info("tmpDEBUGtmp: " + "> " + "getDirFoto(" + ")");
		log.info("tmpDEBUGtmp: " + "< " + "getDirFoto");
		return dirFoto;
	}
	
	// public String getDirFotoUpload(){
	// return dirFotoUpload;
	// }
	
	public String getDirFotoUri(){
		log.info("tmpDEBUGtmp: " + "> " + "getDirFotoUri(" + ")");
		log.info("tmpDEBUGtmp: " + "< " + "getDirFotoUri");
		return dirFotoUri;
	}
	
	public Boolean annullaUpdate(List<UploadedFile> fotoLs){
		log.info("tmpDEBUGtmp: " + "> " + "annullaUpdate(" + fotoLs + ")");
		if((fotoLs != null) && (!fotoLs.isEmpty())){
			// ripristino i .delete
			File fd = null;
			if((fotoDeletedLs != null) && (!fotoDeletedLs.isEmpty())){
				Iterator<File> iterFD = fotoDeletedLs.iterator();
				while(iterFD.hasNext()){
					fd = iterFD.next();
					if(fd.exists()){
						File fileRip = new File(dirFoto, fd.getName().substring(0, fd.getName().indexOf(".delete")));
						fd.renameTo(fileRip);
						fotoDeletedLs.remove(fd);
						iterFD = fotoDeletedLs.iterator();
						log.info("file " + fd.getName() + " annullato come cancellato");
					}
				}
			}
			fotoDeletedLs.clear();
			
			annullaUpload(fotoLs);
		}
		log.info("tmpDEBUGtmp: " + "< " + "annullaUpdate");
		return true;
	}
	
	public Boolean annullaUpload(List<UploadedFile> fotoLs){
		log.info("tmpDEBUGtmp: " + "> " + "annullaUpload(" + fotoLs + ")");
		log.info("tmpDEBUGtmp: " + "< " + "annullaUpload");
		if((fotoLs != null) && (!fotoLs.isEmpty())){
			File sourceFolder = new File(dirFoto);
			
			Iterator<UploadedFile> iterator = fotoLs.iterator();
			
			Utility.deleteMultiFiles(sourceFolder, iterator, log);
		}
		return true;
	}
	
	public Boolean annullaUploadUndef(int t, List<UploadedFile> fotoLs, int u){
		log.info("tmpDEBUGtmp: " + "> " + "annullaUploadUndef(" + t + ", " + fotoLs + ", " + u + ")");
		// devo cercare nella lista c'è gia' un altro file dello stesso tipo, se si bisogna eliminarlo sia dalla lista che come file uploadato
		if((fotoLs != null) && (!fotoLs.isEmpty())){
			UploadedFile o = null;
			File f = null;
			Iterator<UploadedFile> iterator = fotoLs.iterator();
			
			while(iterator.hasNext()){
				o = iterator.next();
				
				String uploadFileName = CommonsUtility.fileTypeFromNumberMap.get(t) != null ? "upload-" + o.getFileName() + CommonsUtility.fileTypeFromNumberMap.get(t) : fotoNotDefined;
				
				f = new File(dirFoto, uploadFileName);
				
				if(f.exists()){
					f.delete();
					fotoLs.remove(o);
					iterator = fotoLs.iterator();
					log.info("eliminato il file " + f.getName());
					return true;
				}
			}
		}
		log.info("tmpDEBUGtmp: " + "< " + "annullaUploadUndef");
		return false;
	}
	
	public void salva(Foto foto){
		log.info("tmpDEBUGtmp: " + "> " + "salva(" + foto + ")");
		try{
			try{
				utx.begin();
				foto.setId(null);
				log.info("salva: " + foto.getId() + ", " + foto.getPath() + ", " + foto.getOriginale());
				entityManager.persist(foto);
			}finally{
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public String salva(List<UploadedFile> fotoLs){
		log.info("tmpDEBUGtmp: " + "> " + "salva(" + fotoLs + ")");
		File sourceFolder = new File(dirFoto); // File(dirFotoUpload);
		File targetFolder = new File(dirFoto);
		
		File fileN = null;
		File fileO = null;
		File fileH = null;
		File fileHW = null;
		File fileL = null;
		File fileLW = null;
		
		// controllo che almeno originale sia stato uploadato
		Boolean origDef = false;
		UploadedFile o = null;
		Iterator<UploadedFile> iterator = fotoLs.iterator();
		while(iterator.hasNext()){
			o = iterator.next();
			
			fileO = new File(sourceFolder, "upload-" + o.getFileName() + ".orig");
			
			if(fileO.exists()){
				origDef = true;
			}
		}
		if(!origDef){
			// cancello tutti i file copiati
			iterator = fotoLs.iterator();
			
			Utility.deleteMultiFiles(sourceFolder, iterator, log);
			
			return "la foto orignale e' obbligatoria";
		}
		
		// controllo che già non sia esistente nella directory lo stesso file tra quelli uploadati
		Boolean giaEsiste = false;
		String nomeFileEsistente = "";
		iterator = fotoLs.iterator();
		while(iterator.hasNext()){
			o = iterator.next();
			
			fileN = new File(targetFolder, o.getFileName());
			
			if(fileN.exists()){
				giaEsiste = true;
				nomeFileEsistente += " - " + fileN.getName(); // se sono più di uno
			}
		}
		if(giaEsiste){
			log.info(nomeFileEsistente.substring(3) + " gia' esistente");
			// cancello tutti i file copiati
			iterator = fotoLs.iterator();
			
			Utility.deleteMultiFiles(sourceFolder, iterator, log);
			
			return "la foto definita " + nomeFileEsistente + " e' gia' esistente";
		}
		
		Foto foto = new Foto();
		foto.setPath(dirFoto);
		
		iterator = fotoLs.iterator();
		while(iterator.hasNext()){
			o = iterator.next();
			
			fileN = new File(sourceFolder, o.getFileName());
			fileO = new File(sourceFolder, "upload-" + o.getFileName() + ".orig");
			fileH = new File(sourceFolder, "upload-" + o.getFileName() + ".hd");
			fileHW = new File(sourceFolder, "upload-" + o.getFileName() + ".hdwm");
			fileL = new File(sourceFolder, "upload-" + o.getFileName() + ".ld");
			fileLW = new File(sourceFolder, "upload-" + o.getFileName() + ".ldwm");
			
			if(fileO.exists()){
				foto.setOriginale(o.getFileName());
				fileO.renameTo(new File(targetFolder, fileN.getName()));
			}else if(fileH.exists()){
				foto.setGrande(o.getFileName());
				fileH.renameTo(new File(targetFolder, fileN.getName()));
			}else if(fileHW.exists()){
				foto.setGrandeWatermark(o.getFileName());
				fileHW.renameTo(new File(targetFolder, fileN.getName()));
			}else if(fileL.exists()){
				foto.setThumbnail(o.getFileName());
				fileL.renameTo(new File(targetFolder, fileN.getName()));
			}else if(fileLW.exists()){
				foto.setThumbnailWatermark(o.getFileName());
				fileLW.renameTo(new File(targetFolder, fileN.getName()));
			}
		}
		
		try{
			try{
				utx.begin();
				log.info("salva: " + foto.getId() + ", " + foto.getPath() + ", " + foto.getOriginale());
				entityManager.persist(foto);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		
		fotoLs = null;
		
		log.info("tmpDEBUGtmp: " + "< " + "salva");
		return "OK-Aggiunte foto";
	}
	
	public void aggiorna(Foto foto){
		log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + foto + ")");
		try{
			try{
				utx.begin();
				log.info("aggiorna: " + foto.getId() + ", " + foto.getPath() + ", " + foto.getOriginale());
				entityManager.merge(foto);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	public String aggiorna(Foto foto, List<UploadedFile> fotoLs){
		log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + foto + ", " + fotoLs + ")");
		File sourceFolder = new File(dirFoto); // File(dirFotoUpload);
		File targetFolder = new File(dirFoto);
		
		File fileN = null;
		File fileO = null;
		File fileH = null;
		File fileHW = null;
		File fileL = null;
		File fileLW = null;
		
		UploadedFile o = null;
		Iterator<UploadedFile> iterator = fotoLs.iterator();
		
		// controllo che già non sia esistente nella directory lo stesso file tra quelli uploadati
		Boolean giaEsiste = false;
		String nomeFileEsistente = "";
		iterator = fotoLs.iterator();
		while(iterator.hasNext()){
			o = iterator.next();
			
			fileN = new File(targetFolder, o.getFileName());
			
			if(fileN.exists()){
				giaEsiste = true;
				nomeFileEsistente += " - " + fileN.getName(); // se sono più di uno
			}
		}
		if(giaEsiste){
			log.info(nomeFileEsistente.substring(3) + " gia' esistente");
			annullaUpload(fotoLs);
			return "la foto definita " + nomeFileEsistente + " e' gia' esistente";
		}
		
		iterator = fotoLs.iterator();
		while(iterator.hasNext()){
			o = iterator.next();
			
			fileN = new File(sourceFolder, o.getFileName());
			fileO = new File(sourceFolder, "upload-" + o.getFileName() + ".orig");
			fileH = new File(sourceFolder, "upload-" + o.getFileName() + ".hd");
			fileHW = new File(sourceFolder, "upload-" + o.getFileName() + ".hdwm");
			fileL = new File(sourceFolder, "upload-" + o.getFileName() + ".ld");
			fileLW = new File(sourceFolder, "upload-" + o.getFileName() + ".ldwm");
			
			File fd = null;
			if(fileO.exists()){
				fd = null;
				if(foto.getOriginale() != null){
					fd = new File(foto.getPath(), foto.getOriginale());
				}
				Utility.deleteFile(fd, log);
				foto.setOriginale(o.getFileName());
				fileO.renameTo(new File(targetFolder, fileN.getName()));
			}else if(fileH.exists()){
				fd = null;
				if(foto.getGrande() != null){
					fd = new File(foto.getPath(), foto.getGrande());
				}
				Utility.deleteFile(fd, log);
				foto.setGrande(o.getFileName());
				fileH.renameTo(new File(targetFolder, fileN.getName()));
			}else if(fileHW.exists()){
				fd = null;
				if(foto.getGrandeWatermark() != null){
					fd = new File(foto.getPath(), foto.getGrandeWatermark());
				}
				Utility.deleteFile(fd, log);
				foto.setGrandeWatermark(o.getFileName());
				fileHW.renameTo(new File(targetFolder, fileN.getName()));
			}else if(fileL.exists()){
				fd = null;
				if(foto.getThumbnail() != null){
					fd = new File(foto.getPath(), foto.getThumbnail());
				}
				Utility.deleteFile(fd, log);
				foto.setThumbnail(o.getFileName());
				fileL.renameTo(new File(targetFolder, fileN.getName()));
			}else if(fileLW.exists()){
				fd = null;
				if(foto.getThumbnailWatermark() != null){
					fd = new File(foto.getPath(), foto.getThumbnailWatermark());
				}
				Utility.deleteFile(fd, log);
				foto.setThumbnailWatermark(o.getFileName());
				fileLW.renameTo(new File(targetFolder, fileN.getName()));
			}
		}
		
		// gestione file .delete (settare a null il campo)
		File fd = null;
		if(fotoDeletedLs != null){
			Iterator<File> iterFD = fotoDeletedLs.iterator();
			while(iterFD.hasNext()){
				fd = iterFD.next();
				
				if(fd.exists()){
					String type = fd.getName().substring(fd.getName().lastIndexOf("."));
					
					if(StringUtils.equals(type, ".orig")){
						foto.setOriginale(null);
					}else if(StringUtils.equals(type, ".hd")){
						foto.setGrande(null);
					}else if(StringUtils.equals(type, ".hdwm")){
						foto.setGrandeWatermark(null);
					}else if(StringUtils.equals(type, ".ld")){
						foto.setThumbnail(null);
					}else if(StringUtils.equals(type, ".ldwm")){
						foto.setThumbnailWatermark(null);
					}else{
						foto.setOriginale(null);
					}
					fd.delete();
					fotoDeletedLs.remove(fd);
					iterFD = fotoDeletedLs.iterator();
					log.info("file " + fd.getName() + " cancellato");
				}
			}
		}
		
		try{
			try{
				utx.begin();
				log.info("aggiorna: " + foto.getId() + ", " + foto.getPath() + ", " + foto.getOriginale());
				entityManager.merge(foto);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		
		fotoLs = null;
		fotoDeletedLs.clear();
		
		log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
		return "OK-Aggiornate foto";
	}
	
	public void elimina(Foto fotoElimina, List<UploadedFile> fotoLs){
		log.info("tmpDEBUGtmp: " + "> " + "elimina(" + fotoElimina + ", " + fotoLs + ")");
		if((fotoElimina == null) || (fotoElimina.getOriginale() == null)){
			return;
		}
		
		try{
			try{
				utx.begin();
				Foto foto = entityManager.find(Foto.class, fotoElimina.getId());
				log.info("elimina: " + foto.getId() + ", " + foto.getPath() + ", " + foto.getOriginale());
				
				// rimuovere le foto, referenziate nel db, nella lista dei file modificati/eliminati (se uno prima fa le modifche e poi decide di premere cancella)
				
				// gestione file .delete
				File fd = null;
				if(fotoDeletedLs != null){
					Iterator<File> iterFD = fotoDeletedLs.iterator();
					while(iterFD.hasNext()){
						fd = iterFD.next();
						if(fd.exists()){
							fd.delete();
							fotoDeletedLs.remove(fd);
							iterFD = fotoDeletedLs.iterator();
							log.info("file " + fd.getName() + " cancellato");
						}
					}
				}
				fotoDeletedLs.clear();
				
				// gestione file modificati
				if((fotoLs != null) && (!fotoLs.isEmpty())){
					annullaUpload(fotoLs);
					fotoLs.clear();
				}
				
				// gestione foto referenziate nel db
				if(foto.getOriginale() != null){
					Utility.deleteFile(foto.getPath(), foto.getOriginale(), log);
				}
				if(foto.getGrande() != null){
					Utility.deleteFile(foto.getPath(), foto.getGrande(), log);
				}
				if(foto.getGrandeWatermark() != null){
					Utility.deleteFile(foto.getPath(), foto.getGrandeWatermark(), log);
				}
				if(foto.getThumbnail() != null){
					Utility.deleteFile(foto.getPath(), foto.getThumbnail(), log);
				}
				if(foto.getThumbnailWatermark() != null){
					Utility.deleteFile(foto.getPath(), foto.getThumbnailWatermark(), log);
				}
				
				// cancello dal db
				entityManager.remove(foto);
			}finally{
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	public void elimina(Foto fotoElimina, int t, List<UploadedFile> fotoLs, int u){
		log.info("tmpDEBUGtmp: " + "> " + "elimina(" + fotoElimina + ", " + t + ", " + fotoLs + ", " + u + ")");
		if((fotoElimina == null) || (fotoElimina.getOriginale() == null)){
			return;
		}
		
		File f = getFileBetweenDefAndUpl(fotoElimina, t, fotoLs, u);
		if(StringUtils.equals(f.getName(), fotoNotDefined)){
			return;
		}
		
		String deleteType = CommonsUtility.fileTypeFromNumberMap.get(t) != null ? CommonsUtility.fileTypeFromNumberMap.get(t) : ".err";
		
		File fd = new File(dirFoto, f.getName() + ".delete" + deleteType);
		f.renameTo(fd);
		fotoDeletedLs.add(fd);
		log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	public String getSize(Foto foto, int t){
		log.info("tmpDEBUGtmp: " + "> " + "getSize(" + foto + ", " + t + ")");
		log.info("tmpDEBUGtmp: " + "< " + "getSize");
		return getSize(foto, t, null, 0);
	}
	
	public String getSize(Foto foto, int t, List<UploadedFile> fotoLs, int u){
		log.info("tmpDEBUGtmp: " + "> " + "getSize(" + foto + ", " + t + ", " + fotoLs + ", " + u + ")");
		if(foto == null){
			return "n.d.";
		}
		File f = getFileBetweenDefAndUpl(foto, t, fotoLs, u);
		if(StringUtils.equals(f.getName(), fotoNotDefined)){
			return "n.d.";
		}
		float size = f.length();
		size = f.length() / 1024.0f;
		String df = new DecimalFormat("#,##0.0").format(size);
		log.info("tmpDEBUGtmp: " + "< " + "getSize");
		return df + "KB";
	}
	
	public String getResolution(Foto foto, int t){
		log.info("tmpDEBUGtmp: " + "> " + "getResolution(" + foto + ", " + t + ")");
		log.info("tmpDEBUGtmp: " + "< " + "getResolution");
		return getResolution(foto, t, null, 0);
	}
	
	public String getResolution(Foto foto, int t, List<UploadedFile> fotoLs, int u){
		log.info("tmpDEBUGtmp: " + "> " + "getResolution(" + foto + ", " + t + ", " + fotoLs + ", " + u + ")");
		if(foto == null){
			return "n.d.";
		}
		File f = getFileBetweenDefAndUpl(foto, t, fotoLs, u);
		if(StringUtils.equals(f.getName(), fotoNotDefined)){
			return "n.d.";
		}
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
		}
		log.info("tmpDEBUGtmp: " + "< " + "getResolution");
		return (fimg == null) ? "n.d." : fimg.getWidth() + "x" + fimg.getHeight() + "px";
	}
	
	private BufferedImage manageFileJpeg(File f) throws IOException{
		log.info("tmpDEBUGtmp: " + "> " + "manageFileJpeg(" + f + ")");
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
		log.info("tmpDEBUGtmp: " + "< " + "manageFileJpeg");
		return bi;
	}
	
	public int getHeightFromResolution(Foto foto, int t, int h, int w, int u){
		log.info("tmpDEBUGtmp: " + "> " + "getHeightFromResolution(" + foto + ", " + t + ", " + h + ", " + w + ", " + u + ")");
		int height = 0;
		float rapportoTarget = 0.0f;
		float rapportoH = 0.0f;
		float rapportoW = 0.0f;
		
		String resolution = getResolution(foto, t, null, u);
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
		
		log.info("tmpDEBUGtmp: " + "< " + "getHeightFromResolution");
		return height;
	}
	
	public String resolvePath(Foto foto){
		log.info("tmpDEBUGtmp: " + "> " + "resolvePath(" + foto + ")");
		String path = dirFotoUri;
		if((foto == null) || (foto.getOriginale() == null)){
			return path;
		}
		path = StringUtils.replace(foto.getPath(), foto.getPath(), path); // foto.getPath().replace(foto.getPath(), path);
		log.info("tmpDEBUGtmp: " + "< " + "resolvePath");
		return path;
	}
	
	public String getFotoname(Foto foto, int t, List<UploadedFile> fotoLs, int u){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoname(" + foto + ", " + t + ", " + fotoLs + ", " + u + ")");
		if((foto == null) || (foto.getOriginale() == null)){
			return fotoNotDefined;
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoname");
		return getFileBetweenDefAndUpl(foto, t, fotoLs, u).getName();
	}
	
	// private
	private File getFileBetweenDefAndUpl(Foto foto, int t, List<UploadedFile> fotoLs, int u){
		log.info("tmpDEBUGtmp: " + "> " + "getFileBetweenDefAndUpl(" + foto + ", " + t + ", " + fotoLs + ", " + u + ")");
		File f = null;
		switch(t){
			case 1:
				f = new File(foto.getPath(), (foto.getOriginale() == null) ? fotoNotDefined : foto.getOriginale());
				break;
			case 2:
				f = new File(foto.getPath(), (foto.getGrande() == null) ? fotoNotDefined : foto.getGrande());
				break;
			case 3:
				f = new File(foto.getPath(), (foto.getGrandeWatermark() == null) ? fotoNotDefined : foto.getGrandeWatermark());
				break;
			case 4:
				f = new File(foto.getPath(), (foto.getThumbnail() == null) ? fotoNotDefined : foto.getThumbnail());
				break;
			case 5:
				f = new File(foto.getPath(), (foto.getThumbnailWatermark() == null) ? fotoNotDefined : foto.getThumbnailWatermark());
				break;
			default:
				f = new File(foto.getPath(), fotoNotDefined);
				break;
		}
		if(!f.exists()){
			f = new File(foto.getPath(), fotoNotDefined);
		}
		
		if((fotoLs != null) && (!fotoLs.isEmpty())){
			UploadedFile o = null;
			File f2 = null;
			Iterator<UploadedFile> iterator = fotoLs.iterator();
			while(iterator.hasNext()){
				o = iterator.next();
				
				String uploadFileName = CommonsUtility.fileTypeFromNumberMap.get(t) != null ? "upload-" + o.getFileName() + CommonsUtility.fileTypeFromNumberMap.get(t) : fotoNotDefined;
				f2 = new File(dirFoto, uploadFileName);
				
				if((f2.exists()) && (u == 1)){
					f = f2;
				}
			}
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFileBetweenDefAndUpl");
		return f;
	}
	
	@SuppressWarnings("unchecked")
	public List<Foto> getFotoListPerSel(Foto f){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoListPerSel(" + f + ")");
		List<Foto> fotoLs = null;
		try{
			List<Foto> foto;
			fotoLs = new ArrayList<Foto>();
			if((f == null) || (f.getId() == null)){
				fotoLs.add(new Foto(null, dirFoto, fotoNotDefined, null, null, null, null, null));
				return fotoLs;
			}
			try{
				utx.begin();
				// String sql = "FROM Foto f Where f.id = :id";
				// Query query = entityManager.createQuery(sql);
				Query query = entityManager.createNamedQuery("Foto.findById", Foto.class);
				query.setParameter("id", f.getId());
				foto = (List<Foto>)query.getResultList();
			}catch(NoResultException e){
				foto = null;
			}
			utx.commit();
			
			Foto o = null;
			Iterator<Foto> iterator = foto.iterator();
			while(iterator.hasNext()){
				o = iterator.next();
				
				if((o.getOriginale() != null) && (!o.getOriginale().isEmpty())){
					fotoLs.add(new Foto(null, o.getPath(), o.getOriginale(), getSize(o, 1), null, getResolution(o, 1), null, null));
				}
				if((o.getGrande() != null) && (!o.getGrande().isEmpty())){
					fotoLs.add(new Foto(null, o.getPath(), o.getGrande(), getSize(o, 2), null, getResolution(o, 2), null, null));
				}
				if((o.getGrandeWatermark() != null) && (!o.getGrandeWatermark().isEmpty())){
					fotoLs.add(new Foto(null, o.getPath(), o.getGrandeWatermark(), getSize(o, 3), null, getResolution(o, 3), null, null));
				}
				if((o.getThumbnail() != null) && (!o.getThumbnail().isEmpty())){
					fotoLs.add(new Foto(null, o.getPath(), o.getThumbnail(), getSize(o, 4), null, getResolution(o, 4), null, null));
				}
				if((o.getThumbnailWatermark() != null) && (!o.getThumbnailWatermark().isEmpty())){
					fotoLs.add(new Foto(null, o.getPath(), o.getThumbnailWatermark(), getSize(o, 5), null, getResolution(o, 5), null, null));
				}
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoListPerSel");
		return fotoLs;
	}
	
	public void fileUpload(FileUploadEvent event, String type, List<UploadedFile> fotoLs){
		log.info("tmpDEBUGtmp: " + "> " + "fileUpload(" + event + ", " + type + ", " + fotoLs + ")");
		if(type.equals(".err")){
			return;
		}
		
		UploadedFile fileOriginale = event.getFile();
		
		try{
			copyFile("upload-" + fileOriginale.getFileName() + type, fileOriginale.getInputstream());
		}catch(IOException e){
			e.printStackTrace();
		}
		
		manageSeGiaCaricato(fileOriginale, type, fotoLs);
		
		fotoLs.add(fileOriginale);
		log.info("tmpDEBUGtmp: " + "< " + "fileUpload");
	}
	
	private void manageSeGiaCaricato(UploadedFile fileOriginale, String type, List<UploadedFile> fotoLs){
		log.info("tmpDEBUGtmp: " + "> " + "manageSeGiaCaricato(" + fileOriginale + ", " + type + ", " + fotoLs + ")");
		// devo cercare nella lista, se già un altro file dello stesso tipo è stato aggiunto, se si bisogna eliminarlo sia dalla lista che come file uploadato
		if((fotoLs != null) && (!fotoLs.isEmpty())){
			UploadedFile o = null;
			File f = null;
			Iterator<UploadedFile> iterator = fotoLs.iterator();
			while(iterator.hasNext()){
				o = iterator.next();
				
				String uploadFileName = CommonsUtility.fileTypeFromStringMap.get(type) != null ? "upload-" + o.getFileName() + CommonsUtility.fileTypeFromStringMap.get(type) : fotoNotDefined;
				f = new File(dirFoto, uploadFileName);
				
				if(f.exists()){
					f.delete();
					fotoLs.remove(o);
					log.info("nuovo file " + fileOriginale.getFileName() + " rimpiazza " + f.getName());
					return;
				}
			}
			return;
		}
		log.info("tmpDEBUGtmp: " + "< " + "manageSeGiaCaricato");
	}
	
	public void copyFile(String fileName, InputStream in){
		log.info("tmpDEBUGtmp: " + "> " + "copyFile(" + fileName + ", " + in + ")");
		OutputStream out = null;
		try{
			File targetFolder = new File(dirFoto);
			
			out = new FileOutputStream(new File(targetFolder, fileName));
			
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while((read = in.read(bytes)) != -1){
				out.write(bytes, 0, read);
			}
			
//	        in.close();
//	        in = null;
//	        out.flush();
//	        out.close();
//	        out = null;
//	        System.gc();
			
			log.info("nuovo file " + fileName + " copiato/creato!");
		}catch(IOException e){
			e.printStackTrace();
		}
		log.info("tmpDEBUGtmp: " + "< " + "copyFile");
	}
	
	// public Foto getFotoFromNomeOriginale(String fName){
	// Foto foto;
	// if(fName.contains("\\")){
	// fName = fName.substring(fName.lastIndexOf("\\")+1);
	// }
	// try{
	// try{
	// utx.begin();
	// String sql = "FROM Foto f Where f.originale = :nome";
	// Query query = entityManager.createQuery(sql);
	// // Query query = entityManager.createNativeQuery(sql, Foto.class); // Native
	// query.setParameter("nome", fName);
	// foto = (Foto)query.getSingleResult();
	// }catch(NoResultException e){
	// foto = null;
	// }
	// utx.commit();
	// }catch(Exception e){
	// try{
	// utx.rollback();
	// }catch(SystemException se){
	// throw new RuntimeException(se);
	// }
	// throw new RuntimeException(e);
	// }
	// return foto;
	// }
	
	// ***** inizio Foto *****
	public Foto getFotoDaIdFoto(Integer idFoto){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoDaIdFoto(" + idFoto + ")");
		Foto foto = null;
		try{
			try{
				utx.begin();
				// String sql = "select * from foto " +
				// "where id = (select foto " +
				// "from (select row_number() OVER (ORDER BY foto) AS rownum, foto " +
				// "from articoli_foto af join articoli a on af.articolo = a.id " +
				// "where articolo = :id " +
				// "order by ordinamento, af.updtime) as sub " +
				// "where rownum = 1)";
				// String sql = "FROM Foto f Where f.id = :id";
				// Query query = entityManager.createQuery(sql);
				Query query = entityManager.createNamedQuery("Foto.findById", Foto.class);
				query.setParameter("id", idFoto);
				foto = (Foto)query.getSingleResult();
			}catch(NoResultException e){
				foto = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoDaIdFoto");
		return foto;
	}
	
	// ***** fine Foto *****
	
	// ***** inizio Tipi *****
	public Foto getFotoTipoDaIdTipo(Integer idTipo){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoTipoDaIdTipo(" + idTipo + ")");
		// Tipi tipo = (Tipi)nodo.getData();
		Foto foto = null;
		try{
			try{
				utx.begin();
				String sql = "select * from foto " +
				"where id = ( " +
				"select foto " +
				"from tipi t " +
				"where t.id = :id)";
				Query query = entityManager.createNativeQuery(sql, Foto.class); // Native
				// Query query = entityManager.createNamedQuery("Foto.findByIdTipo", Foto.class);
				query.setParameter("id", idTipo); // tipo.getId());
				foto = (Foto)query.getSingleResult();
			}catch(NoResultException e){
				foto = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoTipoDaIdTipo");
		return foto;
	}
	
	public Foto getFotoTipoDaIdArticolo(Integer idArticolo){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoTipoDaIdArticolo(" + idArticolo + ")");
		Foto foto = null;
		try{
			try{
				utx.begin();
				String sql = "select * from foto " +
				"where id = ( " +
				"select foto " +
				"from tipi t join articoli a on t.id = a.tipo " +
				"where a.id = :id)";
				Query query = entityManager.createNativeQuery(sql, Foto.class); // Native
				query.setParameter("id", idArticolo);
				foto = (Foto)query.getSingleResult();
			}catch(NoResultException e){
				foto = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoTipoDaIdArticolo");
		return foto;
	}
	
	// ***** fine Tipi *****
	
	// ***** inizio Produttori *****
	public Foto getFotoProduttoreDaIdProduttore(Integer idProduttore){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoProduttoreDaIdProduttore(" + idProduttore + ")");
		Foto foto = null;
		try{
			try{
				utx.begin();
				String sql = "select * from foto " +
				"where id = ( " +
				"select foto " +
				"from produttori p " +
				"where p.id = :id)";
				Query query = entityManager.createNativeQuery(sql, Foto.class); // Native
				query.setParameter("id", idProduttore);
				foto = (Foto)query.getSingleResult();
			}catch(NoResultException e){
				foto = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoProduttoreDaIdProduttore");
		return foto;
	}
	
	public Foto getFotoProduttoreDaIdArticolo(Integer idArticolo){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoProduttoreDaIdArticolo(" + idArticolo + ")");
		Foto foto = null;
		try{
			try{
				utx.begin();
				String sql = "select * from foto " +
				"where id = ( " +
				"select foto " +
				"from produttori p join articoli a on p.id = a.produttore " +
				"where a.id = :id)";
				Query query = entityManager.createNativeQuery(sql, Foto.class); // Native
				query.setParameter("id", idArticolo);
				foto = (Foto)query.getSingleResult();
			}catch(NoResultException e){
				foto = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoProduttoreDaIdArticolo");
		return foto;
	}
	
	// ***** fine Produttori *****
	
	// ***** inizio Articoli *****
	public Foto getFotoArticoloDaIdArticolo(Integer idArticolo){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoArticoloDaIdArticolo(" + idArticolo + ")");
		log.info("tmpDEBUGtmp: " + "< " + "getFotoArticoloDaIdArticolo");
		return (getFotoArticoloOrdLsDaIdArticolo(idArticolo).size() > 0) ? getFotoArticoloOrdLsDaIdArticolo(idArticolo).get(0) : null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Foto> getFotoArticoloOrdLsDaIdArticolo(Integer idArticolo){
		log.info("tmpDEBUGtmp: " + "> " + "getFotoArticoloOrdLsDaIdArticolo(" + idArticolo + ")");
		List<Foto> foto = null;
		try{
			try{
				utx.begin();
				// String sql = "select * " + // solo la prima
				// "from foto " +
				// "where id = ( " +
				// "select selordrow.foto " +
				// "from ( " +
				// "select row_number() OVER () AS rownum, selord.foto  " +
				// "from (select foto " +
				// "from articoli_foto af join articoli a on af.articolo = a.id " +
				// "where articolo = :id " +
				// "order by ordinamento, af.updtime desc) as selord " +
				// ") as selordrow " +
				// "where selordrow.rownum = 1 " +
				// ")";
				String sql = "select f.* " +
				"from ( " +
				"select row_number() OVER () AS rownum, selord.foto  " +
				"from (select foto " +
				"from articoli_foto af join articoli a on af.articolo = a.id  " +
				"where articolo = :id " +
				"order by ordinamento, af.updtime desc) as selord) as selordjoin left join foto f on selordjoin.foto = f.id";
				Query query = entityManager.createNativeQuery(sql, Foto.class); // Native
				query.setParameter("id", idArticolo);
				foto = (List<Foto>)query.getResultList();
			}catch(NoResultException e){
				foto = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		log.info("tmpDEBUGtmp: " + "< " + "getFotoArticoloOrdLsDaIdArticolo");
		return foto;
	}
	// ***** fine Articoli *****
}

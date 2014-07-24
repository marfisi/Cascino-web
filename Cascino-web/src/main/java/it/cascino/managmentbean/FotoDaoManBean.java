package it.cascino.managmentbean;

import java.awt.image.BufferedImage;
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
import it.cascino.dao.FotoDao;
import it.cascino.model.Foto;
import it.cascino.util.Utility;
import javax.faces.bean.SessionScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
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
	
	@SuppressWarnings("unchecked")
	public List<Foto> getAll(){
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
		return foto;
	}
	
	private final String dirFoto = "c:\\dev\\foto";
	// private String dirFotoUpload = "c:\\dev\\foto\\uploadTmp";
	private final String dirFotoUri = ".\\resources\\gfx\\foto\\";
	
	private List<File> fotoDeletedLs = new ArrayList<File>();
	
	private final String fotoNotDefined = "n.d..jpeg";
	
	public String getDirFoto(){
		return dirFoto;
	}
	
	// public String getDirFotoUpload(){
	// return dirFotoUpload;
	// }
	
	public String getDirFotoUri(){
		return dirFotoUri;
	}
	
	public Boolean annullaUpdate(List<UploadedFile> fotoLs){
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
		return true;
	}
	
	public Boolean annullaUpload(List<UploadedFile> fotoLs){
		if((fotoLs != null) && (!fotoLs.isEmpty())){
			File sourceFolder = new File(dirFoto); // File(dirFotoUpload);
			
			// File fileN = null;
			File fileO = null;
			File fileH = null;
			File fileHW = null;
			File fileL = null;
			File fileLW = null;
			
			UploadedFile o = null;
			Iterator<UploadedFile> iterator = fotoLs.iterator();
			while(iterator.hasNext()){
				o = iterator.next();
				
				// fileN = new File(sourceFolder, o.getFileName());
				fileO = new File(sourceFolder, "upload-" + o.getFileName() + ".orig");
				fileH = new File(sourceFolder, "upload-" + o.getFileName() + ".hd");
				fileHW = new File(sourceFolder, "upload-" + o.getFileName() + ".hdwm");
				fileL = new File(sourceFolder, "upload-" + o.getFileName() + ".ld");
				fileLW = new File(sourceFolder, "upload-" + o.getFileName() + ".ldwm");
				
				if(fileO.exists()){
					fileO.delete();
					log.info("file " + fileO.getName() + " cancellato");
				}else if(fileH.exists()){
					fileH.delete();
					log.info("file " + fileH.getName() + " cancellato");
				}else if(fileHW.exists()){
					fileHW.delete();
					log.info("file " + fileHW.getName() + " cancellato");
				}else if(fileL.exists()){
					fileL.delete();
					log.info("file " + fileL.getName() + " cancellato");
				}else if(fileLW.exists()){
					fileLW.delete();
					log.info("file " + fileLW.getName() + " cancellato");
				}
			}
		}
		return true;
	}
	
	public Boolean annullaUploadUndef(int t, List<UploadedFile> fotoLs, int u){
		// devo cercare nella lista c'è gia' un altro file dello stesso tipo, se si bisogna eliminarlo sia dalla lista che come file uploadato
		if((fotoLs != null) && (!fotoLs.isEmpty())){
			UploadedFile o = null;
			File f = null;
			Iterator<UploadedFile> iterator = fotoLs.iterator();
			while(iterator.hasNext()){
				o = iterator.next();
				
				switch(t){
					case 1:
						f = new File(dirFoto, "upload-" + o.getFileName() + ".orig");
						break;
					case 2:
						f = new File(dirFoto, "upload-" + o.getFileName() + ".hd");
						break;
					case 3:
						f = new File(dirFoto, "upload-" + o.getFileName() + ".hdwm");
						break;
					case 4:
						f = new File(dirFoto, "upload-" + o.getFileName() + ".ld");
						break;
					case 5:
						f = new File(dirFoto, "upload-" + o.getFileName() + ".ldwm");
						break;
					default:
						f = new File(dirFoto, fotoNotDefined);
						break;
				}
				if(f.exists()){
					f.delete();
					fotoLs.remove(o);
					iterator = fotoLs.iterator();
					log.info("eliminato il file " + f.getName());
					return true;
				}
			}
		}
		return false;
	}
	
	public void salva(Foto foto){
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
	}
	
	public String salva(List<UploadedFile> fotoLs){
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
			while(iterator.hasNext()){
				o = iterator.next();
				
				// fileN = new File(sourceFolder, o.getFileName());
				fileO = new File(sourceFolder, "upload-" + o.getFileName() + ".orig");
				fileH = new File(sourceFolder, "upload-" + o.getFileName() + ".hd");
				fileHW = new File(sourceFolder, "upload-" + o.getFileName() + ".hdwm");
				fileL = new File(sourceFolder, "upload-" + o.getFileName() + ".ld");
				fileLW = new File(sourceFolder, "upload-" + o.getFileName() + ".ldwm");
				
				if(fileO.exists()){
					fileO.delete();
				}else if(fileH.exists()){
					fileH.delete();
				}else if(fileHW.exists()){
					fileHW.delete();
				}else if(fileL.exists()){
					fileL.delete();
				}else if(fileLW.exists()){
					fileLW.delete();
				}
			}
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
			while(iterator.hasNext()){
				o = iterator.next();
				
				// fileN = new File(sourceFolder, o.getFileName());
				fileO = new File(sourceFolder, "upload-" + o.getFileName() + ".orig");
				fileH = new File(sourceFolder, "upload-" + o.getFileName() + ".hd");
				fileHW = new File(sourceFolder, "upload-" + o.getFileName() + ".hdwm");
				fileL = new File(sourceFolder, "upload-" + o.getFileName() + ".ld");
				fileLW = new File(sourceFolder, "upload-" + o.getFileName() + ".ldwm");
				
				if(fileO.exists()){
					fileO.delete();
				}else if(fileH.exists()){
					fileH.delete();
				}else if(fileHW.exists()){
					fileHW.delete();
				}else if(fileL.exists()){
					fileL.delete();
				}else if(fileLW.exists()){
					fileLW.delete();
				}
			}
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
		
		return "OK-Aggiunte foto";
	}
	
	public void aggiorna(Foto foto){
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
	}
	
	public String aggiorna(Foto foto, List<UploadedFile> fotoLs){
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
			// // cancello tutti i file copiati
			// iterator = fotoLs.iterator();
			// while(iterator.hasNext()){
			// o = iterator.next();
			//
			// // fileN = new File(sourceFolder, o.getFileName());
			// fileO = new File(sourceFolder, "upload-" + o.getFileName() + ".orig");
			// fileH = new File(sourceFolder, "upload-" + o.getFileName() + ".hd");
			// fileHW = new File(sourceFolder, "upload-" + o.getFileName() + ".hdwm");
			// fileL = new File(sourceFolder, "upload-" + o.getFileName() + ".ld");
			// fileLW = new File(sourceFolder, "upload-" + o.getFileName() + ".ldwm");
			//
			// if(fileO.exists()){
			// fileO.delete();
			// }else if(fileH.exists()){
			// fileH.delete();
			// }else if(fileHW.exists()){
			// fileHW.delete();
			// }else if(fileL.exists()){
			// fileL.delete();
			// }else if(fileLW.exists()){
			// fileLW.delete();
			// }
			// }
			annullaUpload(fotoLs);
			return "la foto definita " + nomeFileEsistente + " e' gia' esistente";
		}
		
		// Foto foto = new Foto();
		// foto.setPath(dirFoto);
		
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
				if((fd != null) && (fd.exists())){
					fd.delete();
					log.info("file " + fd.getName() + " cancellato");
				}
				foto.setOriginale(o.getFileName());
				fileO.renameTo(new File(targetFolder, fileN.getName()));
			}else if(fileH.exists()){
				fd = null;
				if(foto.getGrande() != null){
					fd = new File(foto.getPath(), foto.getGrande());
				}
				if((fd != null) && (fd.exists())){
					fd.delete();
					log.info("file " + fd.getName() + " cancellato");
				}
				foto.setGrande(o.getFileName());
				fileH.renameTo(new File(targetFolder, fileN.getName()));
			}else if(fileHW.exists()){
				fd = null;
				if(foto.getGrandeWatermark() != null){
					fd = new File(foto.getPath(), foto.getGrandeWatermark());
				}
				if((fd != null) && (fd.exists())){
					fd.delete();
					log.info("file " + fd.getName() + " cancellato");
				}
				foto.setGrandeWatermark(o.getFileName());
				fileHW.renameTo(new File(targetFolder, fileN.getName()));
			}else if(fileL.exists()){
				fd = null;
				if(foto.getThumbnail() != null){
					fd = new File(foto.getPath(), foto.getThumbnail());
				}
				if((fd != null) && (fd.exists())){
					fd.delete();
					log.info("file " + fd.getName() + " cancellato");
				}
				foto.setThumbnail(o.getFileName());
				fileL.renameTo(new File(targetFolder, fileN.getName()));
			}else if(fileLW.exists()){
				fd = null;
				if(foto.getThumbnailWatermark() != null){
					fd = new File(foto.getPath(), foto.getThumbnailWatermark());
				}
				if((fd != null) && (fd.exists())){
					fd.delete();
					log.info("file " + fd.getName() + " cancellato");
				}
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
					if(type.equals(".orig")){
						foto.setOriginale(null);
					}else if(type.equals(".hd")){
						foto.setGrande(null);
					}else if(type.equals(".hdwm")){
						foto.setGrandeWatermark(null);
					}else if(type.equals(".ld")){
						foto.setThumbnail(null);
					}else if(type.equals(".ldwm")){
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
		
		return "OK-Aggiornate foto";
	}
	
	public void elimina(Foto fotoElimina, List<UploadedFile> fotoLs){
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
					// File sourceFolder = new File(dirFoto);
					// File fileO = null;
					// File fileH = null;
					// File fileHW = null;
					// File fileL = null;
					// File fileLW = null;
					// UploadedFile o = null;
					// Iterator<UploadedFile> iterator = fotoLs.iterator();
					// while(iterator.hasNext()){
					// o = iterator.next();
					//
					// fileO = new File(sourceFolder, "upload-" + o.getFileName() + ".orig");
					// fileH = new File(sourceFolder, "upload-" + o.getFileName() + ".hd");
					// fileHW = new File(sourceFolder, "upload-" + o.getFileName() + ".hdwm");
					// fileL = new File(sourceFolder, "upload-" + o.getFileName() + ".ld");
					// fileLW = new File(sourceFolder, "upload-" + o.getFileName() + ".ldwm");
					//
					// if(fileO.exists()){
					// fileO.delete();
					// }else if(fileH.exists()){
					// fileH.delete();
					// }else if(fileHW.exists()){
					// fileHW.delete();
					// }else if(fileL.exists()){
					// fileL.delete();
					// }else if(fileLW.exists()){
					// fileLW.delete();
					// }
					// log.info("file " + o.getFileName() + " cancellato");
					// }
					annullaUpload(fotoLs);
					fotoLs.clear();
				}
				
				// gestione foto referenziate nel db
				fd = null;
				if(foto.getOriginale() != null){
					fd = new File(foto.getPath(), foto.getOriginale());
				}
				if((fd != null) && (fd.exists())){
					fd.delete();
					log.info("file " + fd.getName() + " cancellato");
				}
				fd = null;
				if(foto.getGrande() != null){
					fd = new File(foto.getPath(), foto.getGrande());
				}
				if((fd != null) && (fd.exists())){
					fd.delete();
					log.info("file " + fd.getName() + " cancellato");
				}
				fd = null;
				if(foto.getGrandeWatermark() != null){
					fd = new File(foto.getPath(), foto.getGrandeWatermark());
				}
				if((fd != null) && (fd.exists())){
					fd.delete();
					log.info("file " + fd.getName() + " cancellato");
				}
				fd = null;
				if(foto.getThumbnail() != null){
					fd = new File(foto.getPath(), foto.getThumbnail());
				}
				if((fd != null) && (fd.exists())){
					fd.delete();
					log.info("file " + fd.getName() + " cancellato");
				}
				fd = null;
				if(foto.getThumbnailWatermark() != null){
					fd = new File(foto.getPath(), foto.getThumbnailWatermark());
				}
				if((fd != null) && (fd.exists())){
					fd.delete();
					log.info("file " + fd.getName() + " cancellato");
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
	}
	
	public void elimina(Foto fotoElimina, int t, List<UploadedFile> fotoLs, int u){
		if((fotoElimina == null) || (fotoElimina.getOriginale() == null)){
			return;
		}
		
		File f = getFileBetweenDefAndUpl(fotoElimina, t, fotoLs, u);
		if(f.getName().equals(fotoNotDefined)){
			return;
		}
		
		String deleteType = ".err";
		switch(t){
			case 1:
				deleteType = ".orig";
				break;
			case 2:
				deleteType = ".hd";
				break;
			case 3:
				deleteType = ".hdwm";
				break;
			case 4:
				deleteType = ".ld";
				break;
			case 5:
				deleteType = ".ldwm";
				break;
			default:
				deleteType = ".err";
				break;
		}
		
		File fd = new File(dirFoto, f.getName() + ".delete" + deleteType);
		f.renameTo(fd);
		fotoDeletedLs.add(fd);
	}
	
	public String getSize(Foto foto, int t){
		return getSize(foto, t, null, 0);
	}
	
	public String getSize(Foto foto, int t, List<UploadedFile> fotoLs, int u){
		if(foto == null){
			return "n.d.";
		}
		File f = getFileBetweenDefAndUpl(foto, t, fotoLs, u);
		if(f.getName().equals(fotoNotDefined)){
			return "n.d.";
		}
		float size = f.length();
		size = f.length() / 1024.0f;
		String df = new DecimalFormat("#,##0.0").format(size);
		return df + "KB";
	}
	
	public String getResolution(Foto foto, int t){
		return getResolution(foto, t, null, 0);
	}
	
	public String getResolution(Foto foto, int t, List<UploadedFile> fotoLs, int u){
		if(foto == null){
			return "n.d.";
		}
		File f = getFileBetweenDefAndUpl(foto, t, fotoLs, u);
		if(f.getName().equals(fotoNotDefined)){
			return "n.d.";
		}
		BufferedImage fimg = null;
		try{
			fimg = ImageIO.read(f);
		}catch(IOException e){
			e.printStackTrace();
		}
		return fimg.getWidth() + "x" + fimg.getHeight() + "px";
	}
	
	// public String resolvePath(Foto foto, int i, List<UploadedFile> fotoLs){
	// String path = dirFotoUri;
	// if(i == 0){
	// path = dirFotoUri + "uploadTmp\\";
	// }
	// if((foto == null)||(foto.getOriginale() == null)){
	// return path;
	// }
	// path = foto.getPath().replace(foto.getPath(), path);
	// return path;
	// }
	
	public String resolvePath(Foto foto){
		String path = dirFotoUri;
		if((foto == null) || (foto.getOriginale() == null)){
			return path;
		}
		path = foto.getPath().replace(foto.getPath(), path);
		return path;
	}
	
	public String getFotoname(Foto foto, int t, List<UploadedFile> fotoLs, int u){
		if((foto == null) || (foto.getOriginale() == null)){
			return fotoNotDefined;
		}
		return getFileBetweenDefAndUpl(foto, t, fotoLs, u).getName();
	}
	
	// private
	private File getFileBetweenDefAndUpl(Foto foto, int t, List<UploadedFile> fotoLs, int u){
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
				
				switch(t){ // dirFotoUpload -> dirFoto
					case 1:
						f2 = new File(dirFoto, "upload-" + o.getFileName() + ".orig");
						break;
					case 2:
						f2 = new File(dirFoto, "upload-" + o.getFileName() + ".hd");
						break;
					case 3:
						f2 = new File(dirFoto, "upload-" + o.getFileName() + ".hdwm");
						break;
					case 4:
						f2 = new File(dirFoto, "upload-" + o.getFileName() + ".ld");
						break;
					case 5:
						f2 = new File(dirFoto, "upload-" + o.getFileName() + ".ldwm");
						break;
					default:
						f2 = new File(foto.getPath(), fotoNotDefined);
						break;
				}
				if((f2.exists()) && (u == 1)){
					f = f2;
				}
			}
		}
		return f;
	}
	
	@SuppressWarnings("unchecked")
	public List<Foto> getFotoListPerSel(Foto f){
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
		return fotoLs;
	}
	
	public void fileUpload(FileUploadEvent event, String type, List<UploadedFile> fotoLs){
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
	}
	
	private void manageSeGiaCaricato(UploadedFile fileOriginale, String type, List<UploadedFile> fotoLs){
		// devo cercare nella lista, se già un altro file dello stesso tipo è stato aggiunto, se si bisogna eliminarlo sia dalla lista che come file uploadato
		if((fotoLs != null) && (!fotoLs.isEmpty())){
			UploadedFile o = null;
			File f = null;
			Iterator<UploadedFile> iterator = fotoLs.iterator();
			while(iterator.hasNext()){
				o = iterator.next();
				
				if(type.equals(".orig")){
					f = new File(dirFoto, "upload-" + o.getFileName() + ".orig");
				}else if(type.equals(".hd")){
					f = new File(dirFoto, "upload-" + o.getFileName() + ".hd");
				}else if(type.equals(".hdwm")){
					f = new File(dirFoto, "upload-" + o.getFileName() + ".hdwm");
				}else if(type.equals(".ld")){
					f = new File(dirFoto, "upload-" + o.getFileName() + ".ld");
				}else if(type.equals(".ldwm")){
					f = new File(dirFoto, "upload-" + o.getFileName() + ".ldwm");
				}else{
					f = new File(dirFoto, fotoNotDefined);
				}
				if(f.exists()){
					f.delete();
					fotoLs.remove(o);
					log.info("nuovo file " + fileOriginale.getFileName() + " rimpiazza " + f.getName());
					return;
				}
			}
			return;
		}
		
	}
	
	public void copyFile(String fileName, InputStream in){
		try{
			File targetFolder = new File(dirFoto);
			
			OutputStream out = new FileOutputStream(new File(targetFolder, fileName));
			
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while((read = in.read(bytes)) != -1){
				out.write(bytes, 0, read);
			}
			
			in.close();
			out.flush();
			out.close();
			
			log.info("nuovo file " + fileName + " copiato/creato!");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public Foto getFotoFromId(Integer idFoto){
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
		return foto;
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
}

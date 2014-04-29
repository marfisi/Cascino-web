package it.cascino.dao;

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
import it.cascino.model.Foto;
import javax.faces.bean.SessionScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;
import org.primefaces.model.UploadedFile;

@SessionScoped
public class ManagedBeanFotoDao implements FotoDao, Serializable{
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
	
	public List<Foto> getAll(){
		try{
			List<Foto> foto;
			try{
				utx.begin();
				String sql = "FROM Foto f";
				Query query = entityManager.createQuery(sql);
				foto = (List<Foto>)query.getResultList();
			}catch(NoResultException e){
				foto = null;
			}
			utx.commit();
			return foto;
		}catch(Exception e){
			try{
				utx.rollback();
			}catch(SystemException se){
				throw new RuntimeException(se);
			}
			throw new RuntimeException(e);
		}
	}
	
	public String salva(List<UploadedFile> fotoLs){
		File sourceFolder = new File("c:\\dev\\foto\\uploadTmp");
		File targetFolder = new File("c:\\dev\\foto");

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
			
			fileO = new File(sourceFolder, o.getFileName() + ".orig");

			if(fileO.exists()){
				 origDef = true;
			}
		}
		if(!origDef){
			// cancello tutti i file copiati
			iterator = fotoLs.iterator();
			while(iterator.hasNext()){
				o = iterator.next();
				
				fileN = new File(sourceFolder, o.getFileName());
				fileO = new File(sourceFolder, o.getFileName() + ".orig");
				fileH = new File(sourceFolder, o.getFileName() + ".hd");
				fileHW = new File(sourceFolder, o.getFileName() + ".hdw");
				fileL = new File(sourceFolder, o.getFileName() + ".ld");
				fileLW = new File(sourceFolder, o.getFileName() + ".ldw");
				
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
				nomeFileEsistente += " - " + fileN.getName();	// se sono più di uno
			}
		}
		if(giaEsiste){
			log.info(nomeFileEsistente.substring(3) + " gia' esistente");
			// cancello tutti i file copiati
			iterator = fotoLs.iterator();
			while(iterator.hasNext()){
				o = iterator.next();
				
				fileN = new File(sourceFolder, o.getFileName());
				fileO = new File(sourceFolder, o.getFileName() + ".orig");
				fileH = new File(sourceFolder, o.getFileName() + ".hd");
				fileHW = new File(sourceFolder, o.getFileName() + ".hdw");
				fileL = new File(sourceFolder, o.getFileName() + ".ld");
				fileLW = new File(sourceFolder, o.getFileName() + ".ldw");
				
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
		foto.setPath("c:\\dev\\foto\\");
		
		iterator = fotoLs.iterator();
		while(iterator.hasNext()){
			o = iterator.next();
			
			fileN = new File(sourceFolder, o.getFileName());
			fileO = new File(sourceFolder, o.getFileName() + ".orig");
			fileH = new File(sourceFolder, o.getFileName() + ".hd");
			fileHW = new File(sourceFolder, o.getFileName() + ".hdw");
			fileL = new File(sourceFolder, o.getFileName() + ".ld");
			fileLW = new File(sourceFolder, o.getFileName() + ".ldw");
			
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
			try{
				log.info("transaction:" + " " + utx.getStatus());
				utx.rollback();
			}catch(SystemException se){
				throw new RuntimeException(se);
			}
			throw new RuntimeException(e);
		}
		
		fotoLs = null;
		
		return "OK-Aggiunte foto";
	}
	
	public Boolean annullaUpload(List<UploadedFile> fotoLs){
		File sourceFolder = new File("c:\\dev\\foto\\uploadTmp");

		File fileN = null;
		File fileO = null;
		File fileH = null;
		File fileHW = null;
		File fileL = null;
		File fileLW = null;
		
		UploadedFile o = null;
		Iterator<UploadedFile> iterator = fotoLs.iterator();
		while(iterator.hasNext()){
			o = iterator.next();
			
			fileN = new File(sourceFolder, o.getFileName());
			fileO = new File(sourceFolder, o.getFileName() + ".orig");
			fileH = new File(sourceFolder, o.getFileName() + ".hd");
			fileHW = new File(sourceFolder, o.getFileName() + ".hdw");
			fileL = new File(sourceFolder, o.getFileName() + ".ld");
			fileLW = new File(sourceFolder, o.getFileName() + ".ldw");
			
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
		return true;
	}
	
	public void salva(Foto foto){
		try{
			try{
				utx.begin();
				log.info("1");
				log.info("transaction:" + " " + utx.getStatus());
				foto.setId(null);
				log.info("salva: " + foto.getId() + ", " + foto.getPath() + ", " + foto.getOriginale());
				entityManager.persist(foto);
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				log.info("2");
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
				// log.info("5");
			}
		}catch(Exception e){
			try{
				log.info("3");
				log.info("transaction:" + " " + utx.getStatus());
				log.info("4");
				utx.rollback();
			}catch(SystemException se){
				log.info("5");
				throw new RuntimeException(se);
			}
			log.info("6");
			try{
				log.info("transaction:" + " " + utx.getStatus());
			}catch(SystemException e1){
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}
	}
	
	public void aggiorna(Foto foto){
		try{
			try{
				utx.begin();
				log.info("1");
				log.info("transaction:" + " " + utx.getStatus());
				log.info("aggiorna: " + foto.getId() + ", " + foto.getPath() + ", " + foto.getOriginale());
				entityManager.merge(foto);
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				log.info("2");
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
				// log.info("5");
			}
		}catch(Exception e){
			try{
				log.info("3");
				log.info("transaction:" + " " + utx.getStatus());
				log.info("4");
				utx.rollback();
			}catch(SystemException se){
				log.info("5");
				throw new RuntimeException(se);
			}
			log.info("6");
			try{
				log.info("transaction:" + " " + utx.getStatus());
			}catch(SystemException e1){
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}
	}
	
	public void elimina(Foto fotoElimina){
		try{
			try{
				utx.begin();
				Foto foto = entityManager.find(Foto.class, fotoElimina.getId());
				log.info("elimina: " + foto.getId() + ", " + foto.getPath() + ", " + foto.getOriginale());
				entityManager.remove(foto);
				log.info("transaction:" + " " + utx.getStatus());
			}finally{
				log.info("2");
				log.info("transaction:" + " " + utx.getStatus());
				utx.commit();
				// log.info("5");
			}
		}catch(Exception e){
			try{
				log.info("3");
				log.info("transaction:" + " " + utx.getStatus());
				log.info("4");
				utx.rollback();
			}catch(SystemException se){
				log.info("5");
				throw new RuntimeException(se);
			}
			log.info("6");
			try{
				log.info("transaction:" + " " + utx.getStatus());
			}catch(SystemException e1){
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}
	}
	
	public String getSize(Foto foto, int t){
		if(foto == null){
			return "n.d.";
		}
		File f = null;
		switch(t){
			case 1:
				f = new File(foto.getPath() + foto.getOriginale());
				break;
			case 2:
				f = new File(foto.getPath() + foto.getGrande());
				break;
			case 3:
				f = new File(foto.getPath() + foto.getGrandeWatermark());
				break;
			case 4:
				f = new File(foto.getPath() + foto.getThumbnail());
				break;
			case 5:
				f = new File(foto.getPath() + foto.getThumbnailWatermark());
				break;
			default:
				f = null;
				break;
		}
		if(!f.exists()){
			return "n.d.";
		}
		float size = f.length();
		size = f.length() / 1024.0f;
		String df = new DecimalFormat("#,##0.0").format(size);
		return df + "KB";
	}
	
	public String getResolution(Foto foto, int t){
		if(foto == null){
			return "n.d.";
		}
		File f = null;
		switch(t){
			case 1:
				f = new File(foto.getPath() + foto.getOriginale());
				break;
			case 2:
				f = new File(foto.getPath() + foto.getGrande());
				break;
			case 3:
				f = new File(foto.getPath() + foto.getGrandeWatermark());
				break;
			case 4:
				f = new File(foto.getPath() + foto.getThumbnail());
				break;
			case 5:
				f = new File(foto.getPath() + foto.getThumbnailWatermark());
				break;
			default:
				f = null;
				break;
		}
		if(!f.exists()){
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
	
	public String resolvePath(Foto foto){
		String path = ".\\resources\\gfx\\foto\\";
		if(foto == null){
			return path;
		}
		path = foto.getPath().replace(foto.getPath(), path);
		return path;
	}
	
	public String getFotoname(Foto foto, int t){
		if(foto == null){
			return "n.d..jpeg";
		}
		File f = null;
		switch(t){
			case 1:
				f = new File(foto.getPath() + foto.getOriginale());
				break;
			case 2:
				f = new File(foto.getPath() + foto.getGrande());
				break;
			case 3:
				f = new File(foto.getPath() + foto.getGrandeWatermark());
				break;
			case 4:
				f = new File(foto.getPath() + foto.getThumbnail());
				break;
			case 5:
				f = new File(foto.getPath() + foto.getThumbnailWatermark());
				break;
			default:
				f = new File(foto.getPath() + "n.d..jpeg");
				break;
		}
		if(!f.exists()){
			f = new File(foto.getPath() + "n.d..jpeg");
		}
		return f.getName();
	}
	
	public List<Foto> getFotoListPerSel(Foto f){
		
		try{
			List<Foto> foto;
			List<Foto> fotoLs = new ArrayList<Foto>();
			if((f == null) || (f.getId() == null)){
				fotoLs.add(new Foto(null, null, "n.d..jpeg", null, null, null, null, null));
				return fotoLs;
			}
			try{
				utx.begin();
				String sql = "FROM Foto f Where f.id = :id";
				Query query = entityManager.createQuery(sql);
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
			
			return fotoLs;
		}catch(Exception e){
			try{
				utx.rollback();
			}catch(SystemException se){
				throw new RuntimeException(se);
			}
			throw new RuntimeException(e);
		}
	}
}

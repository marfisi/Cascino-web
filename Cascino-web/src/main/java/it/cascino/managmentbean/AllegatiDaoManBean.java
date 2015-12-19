package it.cascino.managmentbean;

import it.cascino.dao.AllegatiDao;
import it.cascino.model.Allegati;
import it.cascino.util.CommonsUtility;
import it.cascino.util.DatabasePostgresqlDS;
import it.cascino.util.Utility;
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
public class AllegatiDaoManBean implements AllegatiDao, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	@DatabasePostgresqlDS
	@Inject
	private EntityManager entityManager;
	
	@Inject
	private UserTransaction utx;
	
	private final String allegatoNotDefined = "n.d..pdf";
	private final String dirAllegati = "c:\\dev\\allegati";
	private final String dirAllegatiUri = ".\\resources\\allegati\\";
	
	private List<File> allegatiDeletedLs = new ArrayList<File>();
		
	@SuppressWarnings("unchecked")
	public List<Allegati> getAll(){
		List<Allegati> allegati = null;
		try{
			try{
				utx.begin();
				Query query = entityManager.createNamedQuery("Allegati.findAll");
				allegati = (List<Allegati>)query.getResultList();
			}catch(NoResultException e){
				allegati = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return allegati;
	}
	
	public String getDirAllegati(){
		return dirAllegati;
	}
	
	public String getDirAllegatiUri(){
		return dirAllegatiUri;
	}
	
	public Boolean annullaUpdate(List<UploadedFile> allegatiLs){
		// ripristino i .delete
		File fd = null;
		if((allegatiDeletedLs != null) && (!allegatiDeletedLs.isEmpty())){
			Iterator<File> iterFD = allegatiDeletedLs.iterator();
			while(iterFD.hasNext()){
				fd = iterFD.next();
				if(fd.exists()){
					File fileRip = new File(dirAllegati, fd.getName().substring(0, fd.getName().indexOf(".delete")));
					Utility.renameFile(fd, fileRip, log);
					allegatiDeletedLs.remove(fd);
					iterFD = allegatiDeletedLs.iterator();
					log.info("file " + fd.getName() + " annullato come cancellato");
				}
			}
		}
		allegatiDeletedLs.clear();
		
		if((allegatiLs != null) && (!allegatiLs.isEmpty())){
			annullaUpload(allegatiLs);
		}
		return true;
	}
	
	public Boolean annullaUpload(List<UploadedFile> allegatiLs){
		if((allegatiLs != null) && (!allegatiLs.isEmpty())){
			File sourceFolder = new File(dirAllegati);
			
			Iterator<UploadedFile> iterator = allegatiLs.iterator();
			
			Utility.deleteMultiFiles(sourceFolder, iterator, "upload-", log);
		}
		return true;
	}
	
	public Boolean annullaUploadUndef(int t, List<UploadedFile> allegatiLs, int u){
		// devo cercare nella lista c'è gia' un altro file dello stesso tipo, se si bisogna eliminarlo sia dalla lista che come file uploadato
		if((allegatiLs != null) && (!allegatiLs.isEmpty())){
			UploadedFile o = null;
			File f = null;
			Iterator<UploadedFile> iterator = allegatiLs.iterator();
			
			while(iterator.hasNext()){
				o = iterator.next();
				
				String uploadFileName = CommonsUtility.fileTypeFromNumberMap.get(t) != null ? "upload-" + o.getFileName() + CommonsUtility.fileTypeFromNumberMap.get(t) : allegatoNotDefined;
				
				f = new File(dirAllegati, uploadFileName);
				
				if(f.exists()){
					Utility.deleteFile(f, log);
					allegatiLs.remove(o);
					iterator = allegatiLs.iterator();
					log.info("eliminato il file " + f.getName());
					return true;
				}
			}
		}
		return false;
	}
	
	public String salva(Allegati allegato, List<UploadedFile> allegatiLs){
		File sourceFolder = new File(dirAllegati);
		File targetFolder = new File(dirAllegati);
		
		File fileN = null;
		File fileO = null;
		
		// controllo che almeno originale sia stato uploadato
		Boolean origDef = false;
		UploadedFile o = null;
		Iterator<UploadedFile> iterator = allegatiLs.iterator();
		while(iterator.hasNext()){
			o = iterator.next();
			
			fileO = new File(sourceFolder, "upload-" + o.getFileName() + ".orig");
			
			if(fileO.exists()){
				origDef = true;
			}
		}
		if(!origDef){
			// cancello tutti i file copiati
			iterator = allegatiLs.iterator();
			
			Utility.deleteMultiFiles(sourceFolder, iterator, "upload-", log);
			
			return "l'allegato e' obbligatoria";
		}
		
		// controllo che già non sia esistente nella directory lo stesso file tra quelli uploadati
		Boolean giaEsiste = false;
		String nomeFileEsistente = "";
		iterator = allegatiLs.iterator();
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
			iterator = allegatiLs.iterator();
			
			Utility.deleteMultiFiles(sourceFolder, iterator, "upload-", log);
			
			return "l'allegato definito " + nomeFileEsistente + " e' gia' esistente";
		}
		
		allegato.setPath(dirAllegati);
		
		iterator = allegatiLs.iterator();
		while(iterator.hasNext()){
			o = iterator.next();
			
			fileN = new File(sourceFolder, o.getFileName());
			fileO = new File(sourceFolder, "upload-" + o.getFileName() + ".orig");
			
			if(fileO.exists()){
				allegato.setNome(o.getFileName());
				Utility.renameFile(fileO, new File(targetFolder, fileN.getName()), log);
			}
		}
		
		try{
			try{
				utx.begin();
				log.info("salva: " + allegato.getId() + ", " + allegato.getPath() + ", " + allegato.getNome());
				allegato.setId(null);
				entityManager.persist(allegato);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			// in caso di eccezione, cancello i file appena salvati
			iterator = allegatiLs.iterator();
			Utility.deleteMultiFiles(sourceFolder, iterator, log);
			
			Utility.manageException(e, utx, log);			
		}
		
		allegatiLs = null;

		return "OK-Aggiunto allegato";
	}
	
	public String aggiorna(Allegati allegato, List<UploadedFile> allegatiLs){
		File sourceFolder = new File(dirAllegati);
		File targetFolder = new File(dirAllegati);
		
		File fileN = null;
		File fileO = null;
		
		UploadedFile o = null;
		Iterator<UploadedFile> iterator = allegatiLs.iterator();
		
		// controllo che già non sia esistente nella directory lo stesso file tra quelli uploadati
		Boolean giaEsiste = false;
		String nomeFileEsistente = "";
		iterator = allegatiLs.iterator();
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
			annullaUpload(allegatiLs);
			return "la foto definita " + nomeFileEsistente + " e' gia' esistente";
		}
		
		iterator = allegatiLs.iterator();
		while(iterator.hasNext()){
			o = iterator.next();
			
			fileN = new File(sourceFolder, o.getFileName());
			fileO = new File(sourceFolder, "upload-" + o.getFileName() + ".orig");
			
			File fd = null;
			if(fileO.exists()){
				fd = null;
				if(allegato.getNome() != null){
					fd = new File(allegato.getPath(), allegato.getNome());
				}
				Utility.deleteFile(fd, log);
				allegato.setNome(o.getFileName());
				Utility.renameFile(fileO, new File(targetFolder, fileN.getName()), log);
			}
		}
		
		// gestione file .delete (settare a null il campo)
		File fd = null;
		if(allegatiDeletedLs != null){
			Iterator<File> iterFD = allegatiDeletedLs.iterator();
			while(iterFD.hasNext()){
				fd = iterFD.next();
				
				if(fd.exists()){
					String type = fd.getName().substring(fd.getName().lastIndexOf("."));
					
					if(StringUtils.equals(type, ".orig")){
						allegato.setNome(null);
					}else{
						allegato.setNome(null);
					}
					Utility.deleteFile(fd, log);
					allegatiDeletedLs.remove(fd);
					iterFD = allegatiDeletedLs.iterator();
					log.info("file " + fd.getName() + " cancellato");
				}
			}
		}
		
		try{
			try{
				utx.begin();
				log.info("aggiorna: " + allegato.getId() + ", " + allegato.getPath() + ", " + allegato.getNome());
				entityManager.merge(allegato);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		
		allegatiLs = null;
		allegatiDeletedLs.clear();
		
		// log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
		return "OK-Aggiornato allegato";
	}
	
	public void elimina(Allegati allegatoElimina, List<UploadedFile> allegatiLs){
		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + fotoElimina + ", " + fotoLs + ")");
		if((allegatoElimina == null) || (allegatoElimina.getNome() == null)){
			return;
		}
		
		try{
			utx.begin();
			Allegati allegato = entityManager.find(Allegati.class, allegatoElimina.getId());
			log.info("elimina: " + allegato.getId() + ", " + allegato.getPath() + ", " + allegato.getNome());
			
			// rimuovere gli allegati, referenziate nel db, nella lista dei file modificati/eliminati (se uno prima fa le modifche e poi decide di premere cancella)
			
			// gestione file .delete
			File fd = null;
			if(allegatiDeletedLs != null){
				Iterator<File> iterFD = allegatiDeletedLs.iterator();
				while(iterFD.hasNext()){
					fd = iterFD.next();
					if(fd.exists()){
						Utility.deleteFile(fd, log);
						allegatiDeletedLs.remove(fd);
						iterFD = allegatiDeletedLs.iterator();
						log.info("file " + fd.getName() + " cancellato");
					}
				}
			}
			allegatiDeletedLs.clear();
			
			// gestione file modificati
			if((allegatiLs != null) && (!allegatiLs.isEmpty())){
				annullaUpload(allegatiLs);
				allegatiLs.clear();
			}
			
			// devo cancellare dal DB prima di canellare i file, perché se fallisce per esempio per una FK, non rimango con il DB popolato e i file cancellati.
			try{
				// cancello dal db
				entityManager.remove(allegato);
				utx.commit();		
			}catch(Exception e){
				Utility.manageException(e, utx, log);
			}
			// solo se effettivamente si e' cancellata la foto dal DB, cancello i file
		
			// gestione foto referenziate nel db
			if(allegato.getNome() != null){
				Utility.deleteFile(allegato.getPath(), allegato.getNome(), log);
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
	}
	
	public void elimina(Allegati allegatoElimina, int t, List<UploadedFile> allegatiLs, int u){
		if((allegatoElimina == null) || (allegatoElimina.getNome() == null)){
			return;
		}
		
		File f = getFileBetweenDefAndUpl(allegatoElimina, t, allegatiLs, u);
		if(StringUtils.equals(f.getName(), allegatoNotDefined)){
			return;
		}
		
		String deleteType = CommonsUtility.fileTypeFromNumberMap.get(t) != null ? CommonsUtility.fileTypeFromNumberMap.get(t) : ".err";
		
		File fd = new File(dirAllegati, f.getName() + ".delete" + deleteType);
		Utility.renameFile(f,  fd, log);
		allegatiDeletedLs.add(fd);
	}
	
	public String getSize(Allegati allegato, int t){
		return getSize(allegato, t, null, 0);
	}
	
	public String getSize(Allegati allegato, int t, List<UploadedFile> allegatiLs, int u){
		if(allegato == null){
			return "n.d.";
		}
		File f = getFileBetweenDefAndUpl(allegato, t, allegatiLs, u);
		if(StringUtils.equals(f.getName(), allegatoNotDefined)){
			return "n.d.";
		}
		float size = f.length();
		size = f.length() / 1024.0f;
		String df = new DecimalFormat("#,##0.0").format(size);
		return df + "KB";
	}
	
	public String resolvePath(Allegati allegato){
		String path = dirAllegatiUri;
		if((allegato == null) || (allegato.getNome() == null)){
			return path;
		}
		path = StringUtils.replace(allegato.getPath(), allegato.getPath(), path);
		return path;
	}
	
	public String getAllegatoName(Allegati allegato, int t, List<UploadedFile> allegatiLs, int u){
		if((allegato == null) || (allegato.getNome() == null)){
			return allegatoNotDefined;
		}
		return getFileBetweenDefAndUpl(allegato, t, allegatiLs, u).getName();
	}
	
	// private
	private File getFileBetweenDefAndUpl(Allegati allegato, int t, List<UploadedFile> allegatiLs, int u){
		File f = null;
		switch(t){
			case 1:
				f = new File(allegato.getPath(), (allegato.getNome() == null) ? allegatoNotDefined : allegato.getNome());
				break;
			default:
				f = new File(allegato.getPath(), allegatoNotDefined);
				break;
		}
		if(!f.exists()){
			f = new File(allegato.getPath(), allegatoNotDefined);
		}
		
		if((allegatiLs != null) && (!allegatiLs.isEmpty())){
			UploadedFile o = null;
			File f2 = null;
			Iterator<UploadedFile> iterator = allegatiLs.iterator();
			while(iterator.hasNext()){
				o = iterator.next();
				
				String uploadFileName = CommonsUtility.fileTypeFromNumberMap.get(t) != null ? "upload-" + o.getFileName() + CommonsUtility.fileTypeFromNumberMap.get(t) : allegatoNotDefined;
				f2 = new File(dirAllegati, uploadFileName);
				
				if((f2.exists()) && (u == 1)){
					f = f2;
				}
			}
		}
		return f;
	}
	
	@SuppressWarnings("unchecked")
	public List<Allegati> getAllegatiListPerSel(Allegati a){
		List<Allegati> allegatiLs = null;
		try{
			List<Allegati> allegati;
			allegatiLs = new ArrayList<Allegati>();
			if((a == null) || (a.getId() == null)){
				allegatiLs.add(new Allegati(null, dirAllegati, allegatoNotDefined, null, null));
				return allegatiLs;
			}
			try{
				utx.begin();
				Query query = entityManager.createNamedQuery("Allegati.findById", Allegati.class);
				query.setParameter("id", a.getId());
				allegati = (List<Allegati>)query.getResultList();
			}catch(NoResultException e){
				allegati = null;
			}
			utx.commit();
			
			Allegati o = null;
			Iterator<Allegati> iterator = allegati.iterator();
			while(iterator.hasNext()){
				o = iterator.next();
				
				if((o.getNome() != null) && (!o.getNome().isEmpty())){
					allegatiLs.add(new Allegati(null, o.getPath(), o.getNome(), getSize(o, 1), null));
				}
			}
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return allegatiLs;
	}
	
	public void fileUpload(FileUploadEvent event, String type, List<UploadedFile> allegatiLs){
		if(type.equals(".err")){
			return;
		}
		
		UploadedFile fileOriginale = event.getFile();
		
		try{
			copyFile("upload-" + fileOriginale.getFileName() + type, fileOriginale.getInputstream());
		}catch(IOException e){
			e.printStackTrace();
		}
		
		manageSeGiaCaricato(fileOriginale, type, allegatiLs);
		
		allegatiLs.add(fileOriginale);
		// log.info("tmpDEBUGtmp: " + "< " + "fileUpload");
	}
	
	private void manageSeGiaCaricato(UploadedFile fileOriginale, String type, List<UploadedFile> allegatiLs){
		// devo cercare nella lista, se già un altro file dello stesso tipo è stato aggiunto, se si bisogna eliminarlo sia dalla lista che come file uploadato
		if((allegatiLs != null) && (!allegatiLs.isEmpty())){
			UploadedFile o = null;
			File f = null;
			Iterator<UploadedFile> iterator = allegatiLs.iterator();
			while(iterator.hasNext()){
				o = iterator.next();
				
				String uploadFileName = CommonsUtility.fileTypeFromStringMap.get(type) != null ? "upload-" + o.getFileName() + CommonsUtility.fileTypeFromStringMap.get(type) : allegatoNotDefined;
				f = new File(dirAllegati, uploadFileName);
				
				if(f.exists()){
					Utility.deleteFile(f, log);
					allegatiLs.remove(o);
					log.info("nuovo file " + fileOriginale.getFileName() + " rimpiazza " + f.getName());
					return;
				}
			}
			return;
		}
	}
	
	public void copyFile(String fileName, InputStream in){
		// log.info("tmpDEBUGtmp: " + "> " + "copyFile(" + fileName + ", " + in + ")");
		OutputStream out = null;
		try{
			File targetFolder = new File(dirAllegati);
			
			out = new FileOutputStream(new File(targetFolder, fileName));
			
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while((read = in.read(bytes)) != -1){
				out.write(bytes, 0, read);
			}
			
	        in.close();
	        in = null;
	        out.flush();
	        out.close();
	        out = null;
	        System.gc();
			
			log.info("nuovo file " + fileName + " copiato/creato!");
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public Allegati getAllegatoDaIdAllegato(Integer idAllegato){
		// log.info("tmpDEBUGtmp: " + "> " + "getFotoDaIdFoto(" + idFoto + ")");
		Allegati allegato = null;
		try{
			try{
				utx.begin();
				Query query = entityManager.createNamedQuery("Allegati.findById", Allegati.class);
				query.setParameter("id", idAllegato);
				allegato = (Allegati)query.getSingleResult();
			}catch(NoResultException e){
				allegato = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return allegato;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getTipoLsDaIdAllegato(Integer idAllegato){
		List<Integer> idTipiLs = null;
		try{
			try{
				utx.begin();
				String sql = "select distinct(tipo) " +
				"from articoli " +
				"where id in (select articolo " +
				"from articoli_allegati " +
				"where allegato = :id) " +
				"order by tipo asc";
				Query query = entityManager.createNativeQuery(sql);
				query.setParameter("id", idAllegato);
				idTipiLs = (List<Integer>)query.getResultList();
			}catch(NoResultException e){
				idTipiLs = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return idTipiLs;
	}
	
	public Allegati getAllegatiArticoloDaIdArticolo(Integer idArticolo){
		return (getAllegatiArticoloOrdLsDaIdArticolo(idArticolo).size() > 0) ? getAllegatiArticoloOrdLsDaIdArticolo(idArticolo).get(0) : null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Allegati> getAllegatiArticoloOrdLsDaIdArticolo(Integer idArticolo){
		List<Allegati> allegati = null;
		try{
			try{
				utx.begin();
				String sql = "select a.* " +
				"from ( " +
				"select row_number() OVER () AS rownum, selord.allegato " +
				"from (select allegato " +
				"from articoli_allegati aa join articoli art on aa.articolo = art.id " +
				"where articolo = :id " +
				"order by ordinamento, aa.updtime desc) as selord) as selordjoin left join allegati a on selordjoin.allegato = a.id";
				Query query = entityManager.createNativeQuery(sql, Allegati.class); // Native
				query.setParameter("id", idArticolo);
				allegati = (List<Allegati>)query.getResultList();
			}catch(NoResultException e){
				allegati = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return allegati;
	}
}

package it.cascino.util;

import it.cascino.model.Foto;
import java.io.File;
import java.util.Iterator;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;
import org.primefaces.model.UploadedFile;

public class Utility{
	public static RuntimeException manageException(Exception e, UserTransaction utx, Logger log){
		try{
			log.info("transaction:" + " " + utx.getStatus());
			utx.rollback();
		}catch(SystemException se){
			throw new RuntimeException(se);
		}
		try{
			log.info("transaction:" + " " + utx.getStatus());
		}catch(SystemException e1){
			e1.printStackTrace();
		}
		throw new RuntimeException(e);
	}
	
	public static void deleteFromFileSystem(String path, String name){
		File fd = null;
		if(name != null){
			fd = new File(path, name);
		}
		if(fd != null && fd.exists()){
			fd.delete();
		}
	}
	
	public static void deleteFileSystemMultiFiles(File sourceFolder, Iterator<UploadedFile> iterator)	{
		UploadedFile o = null;
		while(iterator.hasNext()){
			o = iterator.next();
			
			// fileN = new File(sourceFolder, o.getFileName());
			File fileO = new File(sourceFolder, "upload-" + o.getFileName() + ".orig");
			File fileH = new File(sourceFolder, "upload-" + o.getFileName() + ".hd");
			File fileHW = new File(sourceFolder, "upload-" + o.getFileName() + ".hdwm");
			File fileL = new File(sourceFolder, "upload-" + o.getFileName() + ".ld");
			File fileLW = new File(sourceFolder, "upload-" + o.getFileName() + ".ldwm");
			
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
	}
	
	public static void verifyFile(File sourceFile, Foto foto, File targetFolder, String fileNameToRename, UploadedFile o){
		if(sourceFile.exists()){
			File fd = null;
			if(foto.getOriginale() != null){
				fd = new File(foto.getPath(), foto.getOriginale());
			}
			
			if(fd != null && fd.exists()){
				fd.delete();
				// log.info("file " + fd.getName() + " cancellato");
			}
			foto.setOriginale(o.getFileName());
			sourceFile.renameTo(new File(targetFolder, fileNameToRename));
		}
	}
}
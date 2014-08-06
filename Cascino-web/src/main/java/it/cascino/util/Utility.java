package it.cascino.util;

import it.cascino.model.Foto;
import java.io.File;
import java.io.IOException;
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
	
	public static void deleteFile(String path, String name, Logger log){
		File file = null;
		if((path != null) && (name != null)){
			file = new File(path, name);
		}
		deleteFile(file, log);
	}
	
	public static void deleteFile(File file, Logger log){
		if((file != null) && (file.exists())){
			file.delete();
			log.info("file " + file.getName() + " cancellato");
		}
		
	}
	
	public static void deleteMultiFiles(File sourceFolder, Iterator<UploadedFile> iterator, Logger log)	{
		UploadedFile o = null;
		while(iterator.hasNext()){
			o = iterator.next();
			
			File fileO = new File(sourceFolder, "upload-" + o.getFileName() + ".orig");
			File fileH = new File(sourceFolder, "upload-" + o.getFileName() + ".hd");
			File fileHW = new File(sourceFolder, "upload-" + o.getFileName() + ".hdwm");
			File fileL = new File(sourceFolder, "upload-" + o.getFileName() + ".ld");
			File fileLW = new File(sourceFolder, "upload-" + o.getFileName() + ".ldwm");
			
			deleteFile(fileO, log);
			deleteFile(fileH, log);
			deleteFile(fileHW, log);	
			deleteFile(fileL, log);
			deleteFile(fileLW, log);
		}
	}
}
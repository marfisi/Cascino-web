package it.cascino.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
		System.gc();
//		log.info("file " + (file.canExecute()?"true":"false"));
//		log.info("file " + (file.canRead()?"true":"false"));
//		log.info("file " + (file.canWrite()?"true":"false"));
		if((file != null) && (file.exists())){
//			if(file.delete()){
			try{
				if(Files.deleteIfExists(file.toPath())){
					log.info("file " + file.getName() + " cancellato");
				}else{
					log.error("file " + file.getName() + " NON cancellato");
				}
			}catch(IOException e){
				log.error("file " + file.getName() + " gestito con eccezione");
				e.printStackTrace();
			}
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
package it.cascino.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
		if((file != null) && (Files.exists(file.toPath()))){
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
	
	public static void deleteMultiFiles(File sourceFolder, Iterator<UploadedFile> iterator, String prefisso, Logger log){
		UploadedFile o = null;
		boolean suffisso = ((prefisso.length() == 0) ? false : true);
		while(iterator.hasNext()){
			o = iterator.next();
			
			File fileO = new File(sourceFolder, prefisso + o.getFileName() + (suffisso ? ".orig" : ""));
			File fileH = new File(sourceFolder, prefisso + o.getFileName() +  (suffisso ? ".hd" : ""));
			File fileHW = new File(sourceFolder, prefisso + o.getFileName() +  (suffisso ? ".hdwm" : ""));
			File fileL = new File(sourceFolder, prefisso + o.getFileName() +  (suffisso ? ".ld" : ""));
			File fileLW = new File(sourceFolder, prefisso + o.getFileName() +  (suffisso ? ".ldwm" : ""));
			
			deleteFile(fileO, log);
			deleteFile(fileH, log);
			deleteFile(fileHW, log);	
			deleteFile(fileL, log);
			deleteFile(fileLW, log);
		}
	}
	
	public static void deleteMultiFiles(File sourceFolder, Iterator<UploadedFile> iterator, Logger log){
		String prefisso = "";
		deleteMultiFiles(sourceFolder, iterator, prefisso, log);
	}
	
	public static Path renameFile(String pathSource, String nameSouce, String pathDestination, String nameDestination, Logger log){
		File fileSource = null;
		File fileDestination = null;
		
		if((pathSource != null) && (nameSouce != null)){
			fileSource = new File(pathSource, nameSouce);
		}
		if((pathDestination != null) && (nameDestination != null)){
			fileDestination = new File(pathDestination, nameDestination);
		}
		return renameFile(fileSource, fileDestination, log);
	}
	
	public static Path renameFile(File fileSource, File fileDestination, Logger log){
		System.gc();
		Path targetFile = null;
		if((fileSource != null) && (Files.exists(fileSource.toPath())) && (fileDestination != null) && (Files.notExists(fileDestination.toPath()))){
			try{
				targetFile = Files.move(fileSource.toPath(), fileDestination.toPath());
				if(Files.isSameFile(targetFile, fileDestination.toPath())){
					log.info("file " + fileSource.getName() + " rinominato in " +  fileDestination.getName());
				}else{
					log.error("file " + fileSource.getName() + " NON rinominato in " +  fileDestination.getName());
				}
			}catch(IOException e){
				log.error("file " + fileSource.getName() + " gestito con eccezione");
				e.printStackTrace();
			}
		}
		return targetFile;
	}
}
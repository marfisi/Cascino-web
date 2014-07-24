package it.cascino.util;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;

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

}

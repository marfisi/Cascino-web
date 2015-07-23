package it.cascino.controller;

import it.cascino.dao.PgArticoliIngrossoDao;
import it.cascino.model.PgArticoliIngrosso;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;

@Named
@SessionScoped
public class PgArticoliIngrossoController implements Serializable{
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
	private PgArticoliIngrossoDao pgArticoliIngrossoDao;	
		
	public String getSeArticoloIngrossoDaIdArticolo(Integer idArticolo){
		String ing = "NO";
		PgArticoliIngrosso a = null;
		a = pgArticoliIngrossoDao.getArticoloDaIdArticolo(idArticolo);
		if(a != null){
			ing = "INGROSSO";
		}else{
			ing = "no";
		}
		return ing;
	}
}
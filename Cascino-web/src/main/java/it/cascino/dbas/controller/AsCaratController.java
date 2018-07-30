package it.cascino.dbas.controller;

import it.cascino.dbas.dao.AsCaratDao;
import it.cascino.dbas.model.AsCarat;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class AsCaratController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
//	@Inject
//	private Logger log;

	@Inject
	private AsCaratDao asCaratDao;
		
	public List<AsCarat> getArticoliDaMcomp(String mcoda){
		return asCaratDao.getCaratteristicheDaArticoli(mcoda);
	}
}
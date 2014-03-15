package it.cascino.controller;

import java.util.Iterator;
import java.util.List;
import it.cascino.dao.FotoDao;
import it.cascino.h8.entity.Foto;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class FotoController{
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private FotoDao fotoDao;
	
	private String fotoName;
	private String fotoPath;
	
	private String esito;
	
	@Named
    @Produces
   // @RequestScoped
    private Foto foto = new Foto();
	
	public void getFotoForName(){
		Foto foto = fotoDao.getForName(fotoName);
		if(foto != null){
			esito = "Reperita foto: " + foto.getPath() + foto.getOriginale();
		}else{
			esito = "non ho trovato la foto!";
		}
		String message = esito + " >" + fotoName + "<";
		facesContext.addMessage(null, new FacesMessage(message));
	}
	
	public void getFotoForId(){
		Foto foto = fotoDao.getForId(Integer.parseInt(fotoName));
		if(foto != null){
			esito = "Reperita foto: " + foto.getPath() + foto.getOriginale();
		}else{
			esito = "non ho trovato la foto!";
		}
		String message = esito + " >" + fotoName + "<";
		facesContext.addMessage(null, new FacesMessage(message));
	}

	public void addFoto(){
		fotoDao.insertFoto(foto);
		if(foto != null){
			esito = "Aggiunta foto: " + foto.getPath() + foto.getOriginale();
		}else{
			esito = "non ho trovato la foto!";
		}
		String message = esito + " >" + fotoName + "<";
		facesContext.addMessage(null, new FacesMessage(message));
	}

	public void listFoto(){
		Foto foto;
		List<Foto> fotoList = fotoDao.getAll();
		for(Iterator iterator = fotoList.iterator(); iterator.hasNext();){
			foto = (Foto)iterator.next();
			System.out.print("Name: " + foto.getOriginale());
			System.out.print("Path: " + foto.getPath());
			esito += "Aggiunta foto: " + foto.getPath() + foto.getOriginale() + "\n";
		}
		String message = esito + " >" + fotoName + "<";
		facesContext.addMessage(null, new FacesMessage(message));
	}
	
	public String getFotoName(){
		return fotoName;
	}
	
	public void setFotoName(String fotoName){
		this.fotoName = fotoName;
	}
	
	public String getFotoPath(){
		return fotoPath;
	}
	
	public void setFotoPath(String fotoPath){
		this.fotoPath = fotoPath;
	}
	
	public String getEsito(){
		return esito;
	}
	
}

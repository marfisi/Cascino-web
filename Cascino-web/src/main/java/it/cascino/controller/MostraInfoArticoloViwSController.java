package it.cascino.controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
//import javax.faces.view.ViewScoped; // non esiste  javax.enterprise.context.ViewScoped
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import it.cascino.dbas.dao.AsAnmag0fDao;
import it.cascino.dbas.model.AsAnmag0f;
import it.cascino.idrolab.dbpg.dao.PgMarcheDao;
import it.cascino.idrolab.dbpg.model.PgMarche;
import it.cascino.idrolab.model.Filtro;
import it.cascino.idrolab.model.IdroLab;
import it.cascino.idrolab.model.ws13_14_15_16_17_18.Articolo;
import it.cascino.idrolab.model.ws13_14_15_16_17_18.Ricerca;
import it.cascino.idrolab.model.ws20.AllegatiArticolo;
import it.cascino.idrolab.model.ws20.AllegatiLinea;
import it.cascino.idrolab.model.ws20.AllegatiModello;
import it.cascino.idrolab.model.ws20.AllegatoArticolo;
import it.cascino.idrolab.model.ws20.AllegatoLinea;
import it.cascino.idrolab.model.ws20.AllegatoModello;
import it.cascino.idrolab.model.ws20.Linea;
import it.cascino.idrolab.model.ws20.ListaallegatiArticolo;
import it.cascino.model.Articoli;

@Named
@SessionScoped
//@ViewScoped
public class MostraInfoArticoloViwSController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	private Articoli articoloPg;
	
	private AsAnmag0f articoloAs;
	
	private it.cascino.idrolab.model.ws1_19.Articolo articoloIdro;
	
	private it.cascino.idrolab.model.ws20.Allegati articoloIdroAllegati;

	private List<String> articoloIdroAllegatiLinkFotoLs = new ArrayList<String>();
	private String articoloIdroAllegatiLinkFotoSel = "";

//	private List<String> articoloIdroAllegatiLinkFotoLs = new ArrayList<String>();
//	private String articoloIdroAllegatiLinkFotoSel = "";
	
	private List<AllegatoArticolo> articoloIdroAllegatoArticoloLs;
	private List<AllegatoLinea> articoloIdroAllegatoLineaLs = new ArrayList<AllegatoLinea>();
	private List<AllegatoModello> articoloIdroAllegatoModelloLs = new ArrayList<AllegatoModello>();

	@Inject
	private ArticoliController articoliController;
	
	@Inject
	private AsAnmag0fDao asAnmag0fDao;
	
	@Inject
	private PgMarcheDao pgMarcheDao;
	
//	@Inject
//	private IdroLabController idroLabController;
	
	private IdroLab idroLab = new IdroLab();
	private Filtro filtro = new Filtro();
	private List<String> terminiLs = new ArrayList<String>();
	private String siglaMarca;
	private String codiceArticoloProduttore;
	
	public Articoli getArticoloPg(){
		return articoloPg;
	}

	public void setArticoloPg(Articoli articoloPg){
		if(articoloPg != null){
			this.articoloPg = articoloPg;
		}else{
			this.articoloPg = new Articoli(1, null, null, null, null, null, null, null, null, null);
		}
	}
	
	public AsAnmag0f getArticoloAs(){
		return articoloAs;
	}

	public void setArticoloAs(AsAnmag0f articoloAs){
		if(articoloAs != null){
			this.articoloAs = articoloAs;
		}else{
			this.articoloAs = new AsAnmag0f();
		}
	}
	
	public it.cascino.idrolab.model.ws1_19.Articolo getArticoloIdro(){
		return articoloIdro;
	}

	public void setArticoloIdro(it.cascino.idrolab.model.ws1_19.Articolo articoloIdro){
		this.articoloIdro = articoloIdro;
	}
	
	public it.cascino.idrolab.model.ws20.Allegati getArticoloIdroAllegati(){
		return articoloIdroAllegati;
	}

	public void setArticoloIdroAllegati(it.cascino.idrolab.model.ws20.Allegati articoloIdroAllegati){
		this.articoloIdroAllegati = articoloIdroAllegati;
	}
	
	public List<String> getArticoloIdroLinkFotoAllegatiLs(){
		return articoloIdroAllegatiLinkFotoLs;
	}

	public void setArticoloIdroLinkFotoAllegatiLs(List<String> articoloIdroLinkFotoAllegatiLs){
		this.articoloIdroAllegatiLinkFotoLs = articoloIdroLinkFotoAllegatiLs;
	}

	public String getArticoloIdroLinkFotoAllegatiSel(){
		return articoloIdroAllegatiLinkFotoSel;
	}

	public void setArticoloIdroLinkFotoAllegatiSel(String articoloIdroLinkFotoAllegatiSel){
		this.articoloIdroAllegatiLinkFotoSel = articoloIdroLinkFotoAllegatiSel;
	}
	
	public void initArticolo(String codArt){
		articoloPg = new Articoli();
		if((codArt != null) && (!(codArt.isEmpty()))){
			setArticoloPg(articoliController.getArticoloDaCodiceArticolo(codArt));
		}
		log.info("Articolo_PG: " + articoloPg.toString());
		
		articoloAs = new AsAnmag0f();
		if(articoloPg.getId() != 1){
			setArticoloAs(asAnmag0fDao.getArticoloDaMcoda(articoloPg.getCodice()));
		}
		log.info("Articolo_AS400: " + articoloAs.toString());
		
//		articoloIdro = new it.cascino.idrolab.model.ws1_19.Articolo();
//		IdroLab idroLab = new IdroLab();
//		Filtro filtro = new Filtro();
//		List<String> terminiLs = new ArrayList<String>();
//		setArticoloIdro(idroLabController.getArticoloPerCodArtWS1("HAN", "31940000"));
		manageCodiceArticoloProduttore();
		manageSiglaMarca();
		setArticoloIdro(manageArticoloIdro());
		if(articoloIdro != null){
			log.info("codiceArticoloIdroLab: " + articoloIdro.toString());		
		}
	}
	
	private void manageSiglaMarca(){
		if(StringUtils.isEmpty(codiceArticoloProduttore)){
			siglaMarca = "";
			return;
		}
		
		PgMarche pgMarche = null;
		pgMarche = pgMarcheDao.getMarcaDaMcofo(articoloAs.getMcofo());
		if(pgMarche == null){
			pgMarche = pgMarcheDao.getMarcaDaPortalefotoIdProduttore(articoloPg.getProduttore());
		}
		
		if(pgMarche != null){
			siglaMarca = StringUtils.trimToEmpty(pgMarche.getIdMarca());
		}
	}
	
	private void manageCodiceArticoloProduttore(){
		codiceArticoloProduttore = StringUtils.trimToEmpty(articoloAs.getMcoaf());
	}
	
	private it.cascino.idrolab.model.ws1_19.Articolo manageArticoloIdro(){
		it.cascino.idrolab.model.ws1_19.Articolo artIdro = null;
		artIdro = idroLab.getArticoloPerCodArtWS1(siglaMarca, codiceArticoloProduttore);
		if((artIdro == null) && (!(StringUtils.isEmpty(StringUtils.trimToEmpty(siglaMarca)))) && (!(StringUtils.isEmpty(StringUtils.trimToEmpty(codiceArticoloProduttore))))){
			terminiLs.clear();
			filtro.reset();
//			filtro.setMarca(siglaMarca);
			terminiLs.add(codiceArticoloProduttore);
			
			Ricerca ricerca = idroLab.getArticoliPerPorzioneCodArtWS17(siglaMarca, terminiLs, filtro);
			Articolo artWS17 =  null;
			if((ricerca != null) && (!(ricerca.getArticolo().isEmpty()))){
				artWS17 =  ricerca.getArticolo().get(0);
			}
			if(artWS17 != null){
				artIdro = idroLab.getArticoloPerCodArtWS1(siglaMarca, artWS17.getArtCodiceproduttore());
			}
		}
		articoloIdroAllegatiLinkFotoLs.clear();
		if(artIdro != null){	
			terminiLs.clear();
			terminiLs.add(artIdro.getArtCodiceproduttore());
			articoloIdroAllegati = idroLab.getArticoloAllegatiWS20(siglaMarca, terminiLs);
			
			if(articoloIdroAllegati != null){
				// articoloIdroLinkFotoAllegatiLs.clear();
				articoloIdroAllegatiLinkFotoLs.add(articoloIdroAllegati.getImgEstetica().getImgLink());
				articoloIdroAllegatiLinkFotoLs.add(articoloIdroAllegati.getImgTecnica().getImgLink());
				
				ListaallegatiArticolo listaallegatiArticolo = articoloIdroAllegati.getListaallegatiArticolo();
				AllegatiArticolo allegatiArticolo = listaallegatiArticolo.getAllegatiArticolo();
				AllegatiLinea allegatiLinea = listaallegatiArticolo.getAllegatiLinea();
				AllegatiModello allegatiModello = listaallegatiArticolo.getAllegatiModello();
				articoloIdroAllegatoArticoloLs = allegatiArticolo.getAllegatoArticolo();
				List<Linea> lineaLs = allegatiLinea.getLinea();
				articoloIdroAllegatoModelloLs = allegatiModello.getAllegatoModello();
				
				Linea lin = null;
				articoloIdroAllegatoLineaLs.clear();
				Iterator<Linea> iter_lineaLs = lineaLs.iterator();
				while(iter_lineaLs.hasNext()){
					lin = iter_lineaLs.next();
					AllegatoLinea allegatoLineaIntestazione = new AllegatoLinea();
					allegatoLineaIntestazione.setAllegatoNome("*** Descrizione: " + lin.getDescrizione() + " (codice: " + lin.getCodice() + ") ***");
					allegatoLineaIntestazione.setAllegatoLink("");
					allegatoLineaIntestazione.setAllegatoDimensione("0");
					articoloIdroAllegatoLineaLs.add(allegatoLineaIntestazione);
					articoloIdroAllegatoLineaLs.addAll(lin.getAllegatoLinea());
				}
			}
		}
		return artIdro;
	}
	
	public String sizeByteToMByte(String s){
		Integer i = 0;
		try{
			i = Integer.parseInt(s);
		}catch(NumberFormatException e){
			i = 0;
		}
		String ss =  sizeByteToMByte(i);
		return ss;
	}

	public String sizeByteToMByte(Integer i){
		Double d = i / 1024.0d / 1024.0d;
		DecimalFormat myFormatter = (DecimalFormat)DecimalFormat.getNumberInstance(Locale.ITALY); //new DecimalFormat("#,###.##");
		myFormatter.applyPattern("#,###.##");
		String s = myFormatter.format(d);
		return s;
	}

	public List<AllegatoArticolo> getArticoloIdroAllegatoArticoloLs(){
		return articoloIdroAllegatoArticoloLs;
	}

	public void setArticoloIdroAllegatoArticoloLs(List<AllegatoArticolo> articoloIdroAllegatoArticoloLs){
		this.articoloIdroAllegatoArticoloLs = articoloIdroAllegatoArticoloLs;
	}

//	public List<Linea> getArticoloIdroAllegatoLineaLs(){
//		return articoloIdroAllegatoLineaLs;
//	}
//
//	public void setArticoloIdroAllegatoLineaLs(List<Linea> articoloIdroAllegatoLineaLs){
//		this.articoloIdroAllegatoLineaLs = articoloIdroAllegatoLineaLs;
//	}
//
//	public List<AllegatoModello> getArticoloIdroAllegatoModelloLs(){
//		return articoloIdroAllegatoModelloLs;
//	}
//
//	public void setArticoloIdroAllegatoModelloLs(List<AllegatoModello> articoloIdroAllegatoModelloLs){
//		this.articoloIdroAllegatoModelloLs = articoloIdroAllegatoModelloLs;
//	}

	public List<AllegatoLinea> getArticoloIdroAllegatoLineaLs(){
		return articoloIdroAllegatoLineaLs;
	}

	public void setArticoloIdroAllegatoLineaLs(List<AllegatoLinea> articoloIdroAllegatoLineaLs){
		this.articoloIdroAllegatoLineaLs = articoloIdroAllegatoLineaLs;
	}

	public List<AllegatoModello> getArticoloIdroAllegatoModelloLs(){
		return articoloIdroAllegatoModelloLs;
	}

	public void setArticoloIdroAllegatoModelloLs(List<AllegatoModello> articoloIdroAllegatoModelloLs){
		this.articoloIdroAllegatoModelloLs = articoloIdroAllegatoModelloLs;
	}
}
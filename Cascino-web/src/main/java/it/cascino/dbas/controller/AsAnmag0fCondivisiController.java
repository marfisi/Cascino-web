package it.cascino.dbas.controller;

import it.cascino.dao.AllegatiDao;
import it.cascino.dao.ArticoliDao;
import it.cascino.dao.CaratteristicheDao;
import it.cascino.dao.FotoDao;
import it.cascino.dbas.dao.AsAnmag0fDao;
import it.cascino.dbas.model.AsAnmag0f;
import it.cascino.model.Allegati;
import it.cascino.model.Articoli;
import it.cascino.model.Caratteristiche;
import it.cascino.model.Foto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

@Named
@ApplicationScoped
public class AsAnmag0fCondivisiController implements Serializable{
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
	private AsAnmag0fDao aSanmag0fDao;
	
	@Inject
	private ArticoliDao pGarticoliDao; // = new ArticoliDaoManBean();
	private static List<Articoli> pGarticoliLs;
	
	
	
	@Inject
	private FotoDao fotoDao;
	@Inject
	private CaratteristicheDao caratteristicheDao;
	@Inject
	private AllegatiDao allegatiDao;
	
	// anagrafica articoli (anmaG) (non annullati logicamente)
	//private AsAnmag0fDao aSanmag0fDao = new AsAnmag0fDaoMng();
	private List<AsAnmag0f> aSanmag0fLs;
	
	private static List<AsAnmag0f> artInAsNonPgLs = new ArrayList<AsAnmag0f>();
	
	private static List<Articoli> artInPgConDescDaAggiornareLs = new ArrayList<Articoli>();
	
	public void aggiungiArticoliIngrossoSuPG(){
		log.info("start: " + "aggiungiArticoliIngrossoSuPG");
		artInAsNonPgLs.clear();
		pGarticoliLs = pGarticoliDao.getAll();
		aSanmag0fLs = aSanmag0fDao.getArticoliIngrosso(); //.getAll();
		
		Iterator<Articoli> iter_pGarticoli = pGarticoliLs.iterator();
		Iterator<AsAnmag0f> iter_aSanmag0f = aSanmag0fLs.iterator();
		
		while(iter_aSanmag0f.hasNext()){
			AsAnmag0f asArt = iter_aSanmag0f.next();
			boolean artInPgTrovato = false;
			iter_pGarticoli = pGarticoliLs.iterator();
			while(iter_pGarticoli.hasNext()){
				Articoli pgArt = iter_pGarticoli.next();
				if(StringUtils.equals(StringUtils.upperCase(pgArt.getCodice()), StringUtils.stripEnd(asArt.getMcoda(), " "))){
					artInPgTrovato = true;
					iter_pGarticoli.remove();
					// log.info("Rimossa: " + pgArt.getCodice());
					break;
				}
			}
			if(artInPgTrovato == false){
				artInAsNonPgLs.add(asArt);
			}		
		}
		gestisci_artInAsNonPgLs();
		log.info("stop: " + "aggiungiArticoliIngrossoSuPG");
	}
	
	private void gestisci_artInAsNonPgLs(){
		log.info("start: " + "gestisci_artInAsNonPgLs");
		Iterator<AsAnmag0f> iter_art = artInAsNonPgLs.iterator();
		while(iter_art.hasNext()){
			AsAnmag0f artAs = iter_art.next();
			Articoli artPg = new Articoli();
			artPg.setCodice(StringUtils.trim(artAs.getMcoda()));
			artPg.setNome(StringUtils.trim(artAs.getMdesc()));
			artPg.setDescrizione(StringUtils.trim(artAs.getMdesc()));
			artPg.setDescrizioneAs400(StringUtils.trim(artAs.getMdesc()));
			artPg.setProduttore(1);
			artPg.setTipo(1);
			artPg.setModello("n.d.");
			pGarticoliDao.salva(artPg, new ArrayList<Foto>(), new ArrayList<Caratteristiche>(), new ArrayList<Allegati>());
			log.info("Aggiunto: " + artPg.getCodice());
		}
		log.info("stop: " + "gestisci_artInAsNonPgLs");
	}
	
	public void aggiornaDescrizioneArticoliDaAs(){
		log.info("start: " + "aggiornaDescrizioneArticoliDaAs");
		artInPgConDescDaAggiornareLs.clear();
		pGarticoliLs = pGarticoliDao.getAll();
		aSanmag0fLs = aSanmag0fDao.getAll();
		
		Iterator<Articoli> iter_pGarticoli = pGarticoliLs.iterator();
		Iterator<AsAnmag0f> iter_aSanmag0f = aSanmag0fLs.iterator();
		
		while(iter_pGarticoli.hasNext()){
			Articoli pgArt = iter_pGarticoli.next();
			boolean artInAsTrovato = false;
			String descAs400 = "";
			iter_aSanmag0f = aSanmag0fLs.iterator();
			while(iter_aSanmag0f.hasNext()){
				AsAnmag0f asArt = iter_aSanmag0f.next();
				if(StringUtils.equals(StringUtils.upperCase(pgArt.getCodice()), StringUtils.stripEnd(asArt.getMcoda(), " "))){
					artInAsTrovato = true;
					iter_aSanmag0f.remove();
					descAs400 = StringUtils.trim(asArt.getMdesc());
					break;
				}
			}
			if(artInAsTrovato == false){
				descAs400 = "ANNULLATO/ELIMINATO";
				if(!(StringUtils.equals(pgArt.getDescrizioneAs400(), descAs400))){
					pgArt.setDescrizioneAs400(descAs400);
					artInPgConDescDaAggiornareLs.add(pgArt);
				}
			}else{ // if(artInAsTrovato == true){
				if(!(StringUtils.equals(pgArt.getDescrizioneAs400(), descAs400))){
					pgArt.setDescrizioneAs400(descAs400);
					artInPgConDescDaAggiornareLs.add(pgArt);
				}
			}
		}
		gestisci_artInPgConDescDaAggiornareLs();
		log.info("stop: " + "aggiornaDescrizioneArticoliDaAs");
	}
	
	private void gestisci_artInPgConDescDaAggiornareLs(){
		log.info("start: " + "gestisci_artInPgConDescDaAggiornareLs");
		Iterator<Articoli> iter_art = artInPgConDescDaAggiornareLs.iterator();
		while(iter_art.hasNext()){
			Articoli artPg = iter_art.next();
			pGarticoliDao.aggiorna(artPg, fotoDao.getFotoArticoloOrdLsDaIdArticolo(artPg.getId()), caratteristicheDao.getCaratteristicheListPerArticolo(artPg.getId()), allegatiDao.getAllegatiArticoloOrdLsDaIdArticolo(artPg.getId()));
			log.info("Aggiorno: " + artPg.getCodice());
		}
		log.info("stop: " + "gestisci_artInPgConDescDaAggiornareLs");
	}
}

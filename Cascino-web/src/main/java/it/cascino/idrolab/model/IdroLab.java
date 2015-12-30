package it.cascino.idrolab.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Serializable;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.inject.Inject;
//import javax.persistence.Entity;
//import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
//import org.jboss.logging.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.filter.HTTPDigestAuthFilter;
import it.cascino.idrolab.dbpg.model.PgMarche;
import it.cascino.idrolab.model.Filtro;
import it.cascino.idrolab.model.ws10.Listalistini;
import it.cascino.idrolab.model.ws11.Listalinee;
import it.cascino.idrolab.model.ws13_14_15_16_17_18.Ricerca;
import it.cascino.idrolab.model.ws4.Marca;
import it.cascino.idrolab.model.ws4.Marche;
import it.cascino.idrolab.model.ws6.Listamarche;
import it.cascino.idrolab.model.ws7.Listasettori;
import it.cascino.idrolab.model.ws8.Listamacrofamiglie;
import it.cascino.idrolab.model.ws9.Listafamiglie;

//@Entity
public class IdroLab implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
//	@Inject
//	private Logger log;
	
	private final String username = "nicola.cascino@cascino.it";
	private final String password = "Servizio2015";
	
	private Client client;
	private final String urlWs = "http://ws.eureca2008.net/";
	
	private WebResource webResource = null;
	private Builder builder = null;
	private ClientResponse response = null;
	private String jaxbOutput = null;
	private JAXBContext jaxbContext = null;
	private Unmarshaller jaxbUnmarshaller = null;
	private StringReader jaxbReader = null;
	private SAXSource jaxbSaxSource = null;
	
//	private StringBuilder stringBuilder = new StringBuilder();
	
	private final String basePathDownload = "C:/dev/IdroLab/download_file/";
	private String fileNameListino = "";
	private File fileListino = null;
	
//	@Inject
//	private PgMarcheDao pgMarcheDao; // = new PgMarcheDaoMng(); 
	
	public IdroLab(){
//		log.info("[ " + "IdroLab");
		
		creaClient();
		
//		log.info("] " + "IdroLab");
	}
	
	private void creaClient(){
//		log.info("[ " + "creaClient");
		client = Client.create();
		client.addFilter(new HTTPDigestAuthFilter(username, password));
//		log.info("] " + "creaClient");
	}
	
	private void manageCallWS(){
//		log.info("[ " + "manageCallWS");
//		log.info(webResource.toString());
		builder = webResource.accept("application/xml");
		response = builder.get(ClientResponse.class);
		if(response.getStatus() != 200) {
//			log.fatal("Failed : HTTP error code : " + response.getStatus());
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		jaxbOutput = response.getEntity(String.class);
		// jaxbOutput = StringUtils.removePattern(output, "<!DOCTYPE.*dtd\">");
		// System.out.println(jaxbOutput);
		jaxbReader = new StringReader(jaxbOutput);
//		log.info("] " + "manageCallWS");
	}
	
	private void manageConversione(String src){
//		log.info("[ " + "manageConversione");
		try{
			SAXParserFactory spf = SAXParserFactory.newInstance();
			// spf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			spf.setFeature("http://apache.org/xml/features/validation/schema", false);
			spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			XMLReader xmlReader = spf.newSAXParser().getXMLReader();
			InputSource inputSource = new InputSource(new StringReader(src));
			jaxbSaxSource = new SAXSource(xmlReader, inputSource);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "manageConversione");
	}
	
	private String terminiLsToUrl(List<String> terminiLs){
		String termini = "";
		Iterator<String> iter_terminiLs = terminiLs.iterator();
		while(iter_terminiLs.hasNext()){
			 termini =  termini + iter_terminiLs.next() + " ";
		}
		try{
			termini = URLEncoder.encode(StringUtils.normalizeSpace(termini), "UTF-8");
		}catch(UnsupportedEncodingException e1){
//			log.fatal(e1.toString());
		}
		return termini;
	}
	
	public it.cascino.idrolab.model.ws1_19.Articolo getArticoloPerCodArtWS1(String siglaMarca, String codiceArticolo){
		it.cascino.idrolab.model.ws1_19.Articolo articolo = null;
//		log.info("[ " + "getArticoloPerCodArtWS1");
		try{
			webResource = client.resource(urlWs + "articoli/" + siglaMarca + "/" + codiceArticolo + ".xml");
			
			manageCallWS();
			
			jaxbContext = JAXBContext.newInstance(it.cascino.idrolab.model.ws1_19.Articolo.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			articolo = (it.cascino.idrolab.model.ws1_19.Articolo)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(articolo);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getArticoloPerCodArtWS1");
		return articolo;
	}
	
	public void getListinoWS2(String siglaMarca){
//		log.info("[ " + "getListinoWS2");
		try{
			webResource = client.resource(urlWs + "listino/" + siglaMarca + ".xml");
			
			// manageCallWS();
			fileNameListino = basePathDownload + siglaMarca + ".xml";
			builder = webResource.accept("application/xml");
			response = builder.get(ClientResponse.class);
			if(response.getStatus() != 200) {
//				log.fatal("Failed : HTTP error code : " + response.getStatus());
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			File s = response.getEntity(File.class);
			File ff = new File(fileNameListino + ".zip");
			s.renameTo(ff);
			FileWriter fr = new FileWriter(s);
			fr.flush();
			fr.close();
			
			byte[] buffer = new byte[1024];
			ZipInputStream zis = new ZipInputStream(new FileInputStream(fileNameListino + ".zip"));
			ZipEntry ze = zis.getNextEntry();
			while(ze != null){
				String fileName = ze.getName();
				File newFile = new File(basePathDownload + fileName);
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while((len = zis.read(buffer)) > 0){
					fos.write(buffer, 0, len);
				}
				fos.close();
				ze = zis.getNextEntry();
			}
			zis.closeEntry();
			zis.close();
			
			fileListino = new File(fileNameListino);
			
			jaxbContext = JAXBContext.newInstance(it.cascino.idrolab.model.ws2.Listino.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(FileUtils.readFileToString(fileListino));
			
			it.cascino.idrolab.model.ws2.Listino listino = (it.cascino.idrolab.model.ws2.Listino)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(listino);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getListinoWS2");
	}
	
	public void getArticoloPrezzoWS3(String siglaMarca, String codiceArticolo){
//		log.info("[ " + "getArticoloPrezzoWS3");
		try{
			webResource = client.resource(urlWs + "prezzi/" + siglaMarca + "/" + codiceArticolo + ".xml");
			
			manageCallWS();
			
			jaxbContext = JAXBContext.newInstance(it.cascino.idrolab.model.ws3.Articolo.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			it.cascino.idrolab.model.ws3.Articolo articolo = (it.cascino.idrolab.model.ws3.Articolo)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(articolo);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getArticoloPrezzoWS3");
	}
	
	public void getAggiornamentiWS4(String siglaMarca, Boolean soloRecenti){
//		log.info("[ " + "getAggiornamentiWS4");
		Boolean marcaSingola;
		if(siglaMarca != null){
			marcaSingola = true;
		}else{
			marcaSingola = false;
		}
		try{
			if(marcaSingola){
				if(soloRecenti){
					webResource = client.resource(urlWs + "aggiornamento_recente/" + siglaMarca + ".xml");
				}else{
					webResource = client.resource(urlWs + "aggiornamenti/" + siglaMarca + ".xml");
				}
			}else{
				webResource = client.resource(urlWs + "aggiornamenti_recenti.xml");
			}
			
			manageCallWS();
			
			if(marcaSingola){
				jaxbContext = JAXBContext.newInstance(Marca.class);
			}else{
				jaxbContext = JAXBContext.newInstance(Marche.class);
			}
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			if(marcaSingola){
				Marca marca = (Marca)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//				log.info(marca);
			}else{
				Marche marche = (Marche)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//				log.info(marche);
			}
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getAggiornamentiWS4");
	}
	
	public void getListinoRidottoWS5(String siglaMarca, Filtro filtro){
//		log.info("[ " + "getListinoRidottoWS5");
		try{
			webResource = client.resource(urlWs + "listino_prezzi/" + siglaMarca + ".xml" + filtro.filterToUrl());
			
			manageCallWS();
			
			jaxbContext = JAXBContext.newInstance(it.cascino.idrolab.model.ws5.Listino.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			it.cascino.idrolab.model.ws5.Listino listino = (it.cascino.idrolab.model.ws5.Listino)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(listino);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getListinoRidottoWS5");
	}
	
	public void getMarcheWS6(Filtro filtro){
//		log.info("[ " + "getMarcheWS6");
		try{
			// senza specificare tutte, restituisce solo quelle sottoscritte
			webResource = client.resource(urlWs + "listamarche.xml" + filtro.filterToUrl());
			
			manageCallWS();
			
			jaxbContext = JAXBContext.newInstance(Listamarche.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			Listamarche listamarche = (Listamarche)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(listamarche);
			
			List<it.cascino.idrolab.model.ws6.Marca> marcheLS = listamarche.getMarca();
			it.cascino.idrolab.model.ws6.Marca marca = null;
			PgMarche pgMarcaInDb = null;
			Iterator<it.cascino.idrolab.model.ws6.Marca> iter_listamarche = marcheLS.iterator();
			while(iter_listamarche.hasNext()){
				marca = iter_listamarche.next();
				
//				da ripristinare, l'ho commentato solo per eleiminiare temporan pgMarcheDao
//				pgMarcaInDb = pgMarcheDao.getMarcaDaIdMarca(marca.getMarCodice());
//				if(pgMarcaInDb == null){
//					pgMarcaInDb = new PgMarche();
//					pgMarcaInDb.setIdMarca(marca.getId());
//					pgMarcaInDb.setMarCodice(marca.getMarCodice());
//					pgMarcaInDb.setProCodice(marca.getMarCodice());
//					pgMarcaInDb.setMarDescrizione(marca.getMarDescrizione());
//					pgMarcaInDb.setMarCodiceAngaisa(marca.getMarCodiceAngaisa());
//					pgMarcaInDb.setSelezionata("0");
//					if(filtro.getTutte() == null){
//						pgMarcaInDb.setSelezionata("1");
//					}
//					pgMarcaInDb.setMcofo(null);
//					pgMarcaInDb.setPortalefotoIdProduttore(null);
//					pgMarcaInDb.setGruppoSottogruppo(null);
//					
//					pgMarcheDao.salva(pgMarcaInDb);
//				}else{
//					pgMarcaInDb.setSelezionata("0");
//					if(filtro.getTutte() == null){
//						pgMarcaInDb.setSelezionata("1");
//					}
//					
//					pgMarcheDao.aggiorna(pgMarcaInDb);
//				}
			}
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getMarcheWS6");
	}
	
	public void getSettoriWS7(Filtro filtro){
//		log.info("[ " + "getSettoriWS7");
		try{
			webResource = client.resource(urlWs + "listasettori.xml" + filtro.filterToUrl());
			
			manageCallWS();
			
			jaxbContext = JAXBContext.newInstance(Listasettori.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			Listasettori listasettori = (Listasettori)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(listasettori);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getSettoriWS7");
	}
	
	public void getMacrofamiglieWS8(Filtro filtro){
//		log.info("[ " + "getMacrofamiglieWS8");
		try{
			webResource = client.resource(urlWs + "listamacrofamiglie.xml" + filtro.filterToUrl());
			
			manageCallWS();
			
			jaxbContext = JAXBContext.newInstance(Listamacrofamiglie.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			Listamacrofamiglie listamacrofamiglie = (Listamacrofamiglie)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(listamacrofamiglie);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getMacrofamiglieWS8");
	}
	
	public void getFamiglieWS9(Filtro filtro){
//		log.info("[ " + "getFamiglieWS9");
		try{
			webResource = client.resource(urlWs + "listafamiglie.xml" + filtro.filterToUrl());
			
			manageCallWS();
			
			jaxbContext = JAXBContext.newInstance(Listafamiglie.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			Listafamiglie listafamiglie = (Listafamiglie)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(listafamiglie);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getFamiglieWS9");
	}
	
	public void getListiniWS10(Filtro filtro){
//		log.info("[ " + "getListiniWS10");
		try{
			webResource = client.resource(urlWs + "listalistini.xml" + filtro.filterToUrl());
			
			manageCallWS();
			
			jaxbContext = JAXBContext.newInstance(Listalistini.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			Listalistini listalistini = (Listalistini)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(listalistini);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getListiniWS10");
	}
	
	public void getLineeWS11(Filtro filtro){
//		log.info("[ " + "getLineeWS11");
		try{
			webResource = client.resource(urlWs + "listalinee.xml" + filtro.filterToUrl());
			
			manageCallWS();
			
			jaxbContext = JAXBContext.newInstance(Listalinee.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			Listalinee listalinee = (Listalinee)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(listalinee);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getLineeWS11");
	}
	
	public void getListinoFormatoWS12(String siglaMarca, String tracciato, String formato){
//		log.info("[ " + "getListinoFormatoWS12");
//		  tracciato		|   formato
//		idrolab1		| txt, csv
//		idrolab1a		| txt, csv
//		idrolab2		| txt, csv
//		idrolab2a		| txt, csv
//		idrolab3		| txt, csv
//		idrolab3a		| txt, csv
//		idrolab3b		| txt, csv
//		idrolab4		| txt, csv
//		idrolab4a		| txt, csv, xml
//		idrolab4b		| txt, csv, xml
//		angaisa		| txt
		try{
			webResource = client.resource(urlWs + "inviolistino/" + tracciato + "/" + formato + "/" + siglaMarca + ".xml");
			
			// manageCallWS();
			fileNameListino = basePathDownload + "listino_" + siglaMarca + "_" + tracciato +  "." + formato;
			builder = webResource.accept("application/xml");
			response = builder.get(ClientResponse.class);
			if(response.getStatus() != 200) {
//				log.fatal("Failed : HTTP error code : " + response.getStatus());
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			File s = response.getEntity(File.class);
			File ff = new File(fileNameListino + ".zip");
			s.renameTo(ff);
			FileWriter fr = new FileWriter(s);
			fr.flush();
			fr.close();
			
			byte[] buffer = new byte[1024];
			ZipInputStream zis = new ZipInputStream(new FileInputStream(fileNameListino + ".zip"));
			ZipEntry ze = zis.getNextEntry();
			while(ze != null){
				String fileName = ze.getName();
				File newFile = new File(basePathDownload + fileName);
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while((len = zis.read(buffer)) > 0){
					fos.write(buffer, 0, len);
				}
				fos.close();
				ze = zis.getNextEntry();
			}
			zis.closeEntry();
			zis.close();
//			
//			fileListino = new File(fileNameListino);
//			
//			jaxbContext = JAXBContext.newInstance(it.cascino.idrolab.model.ws2.Listino.class);
//			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//			
//			manageConversione(FileUtils.readFileToString(fileListino));
//			
//			it.cascino.idrolab.model.ws2.Listino listino = (it.cascino.idrolab.model.ws2.Listino)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(listino);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getListinoFormatoWS12");
	}
	
	public void getArticoliPerDescWS13(List<String> terminiLs, Filtro filtro){
//		log.info("[ " + "getArticoliPerDescWS13");
		try{
			webResource = client.resource(urlWs + "ricdesart/" + terminiLsToUrl(terminiLs) + ".xml" + filtro.filterToUrl());
			
			manageCallWS();
			
			jaxbContext = JAXBContext.newInstance(Ricerca.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			Ricerca ricerca = (Ricerca)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(ricerca);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getArticoliPerDescWS13");
	}
	
	public Ricerca getArticoliPerCodArtWS14(List<String> terminiLs, Filtro filtro){
		Ricerca ricerca = null;
//		log.info("[ " + "getArticoliPerCodArtWS14");
		try{
			// inizio del codice articolo
			webResource = client.resource(urlWs + "riccodart/" + terminiLsToUrl(terminiLs) + ".xml" + filtro.filterToUrl());
			
			manageCallWS();
			
			jaxbContext = JAXBContext.newInstance(Ricerca.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			ricerca = (Ricerca)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(ricerca);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getArticoliPerCodArtWS14");
		return ricerca;
	}
	
	public void getArticoliPerCodBarreWS15(List<String> terminiLs, Filtro filtro){
//		log.info("[ " + "getArticoliPerCodBarreWS15");
		try{
			// inizio del codice a barre
			webResource = client.resource(urlWs + "riccodbar/" + terminiLsToUrl(terminiLs) + ".xml" + filtro.filterToUrl());
			
			manageCallWS();
			
			jaxbContext = JAXBContext.newInstance(Ricerca.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			Ricerca ricerca = (Ricerca)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(ricerca);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getArticoliPerCodBarreWS15");
	}
	
	public void getArticoliPerPorzioneDescWS16(String siglaMarca, List<String> terminiLs, Filtro filtro){
//		log.info("[ " + "getArticoliPerPorzioneDescWS16");
		try{
			webResource = client.resource(urlWs + "ricdesartporz/" + siglaMarca + "/" + terminiLsToUrl(terminiLs) + ".xml" + filtro.filterToUrl());
			
			manageCallWS();
			
			jaxbContext = JAXBContext.newInstance(Ricerca.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			Ricerca ricerca = (Ricerca)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(ricerca);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getArticoliPerPorzioneDescWS16");
	}
	
	public Ricerca getArticoliPerPorzioneCodArtWS17(String siglaMarca, List<String> terminiLs, Filtro filtro){
		Ricerca ricerca = null;
		//		log.info("[ " + "getArticoliPerPorzioneCodArtWS17");
		try{
			webResource = client.resource(urlWs + "riccodartporz/" + siglaMarca + "/" + terminiLsToUrl(terminiLs) + ".xml" + filtro.filterToUrl());
			
			manageCallWS();
			
			jaxbContext = JAXBContext.newInstance(Ricerca.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			ricerca = (Ricerca)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(ricerca);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getArticoliPerPorzioneCodArtWS17");
		return ricerca;
	}
	
	public void getArticoliPerPorzioneCodBarreWS18(String siglaMarca, List<String> terminiLs, Filtro filtro){
//		log.info("[ " + "getArticoliPerPorzioneCodBarreWS18");
		try{
			webResource = client.resource(urlWs + "riccodbarporz/" + siglaMarca + "/" + terminiLsToUrl(terminiLs) + ".xml" + filtro.filterToUrl());
			
			manageCallWS();
			
			jaxbContext = JAXBContext.newInstance(Ricerca.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			Ricerca ricerca = (Ricerca)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(ricerca);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getArticoliPerPorzioneCodBarreWS18");
	}
	
	public void getArticoloPerCodBarreWS19(String siglaMarca, List<String> terminiLs){
//		log.info("[ " + "getArticoloPerCodBarreWS19");
		try{
			webResource = client.resource(urlWs + "articolicodbar/" + siglaMarca + "/" + terminiLsToUrl(terminiLs) + ".xml");
			
			manageCallWS();
			
			jaxbContext = JAXBContext.newInstance(it.cascino.idrolab.model.ws1_19.Articolo.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			it.cascino.idrolab.model.ws1_19.Articolo articolo = (it.cascino.idrolab.model.ws1_19.Articolo)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(articolo);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getArticoloPerCodBarreWS19");
	}
	
	public it.cascino.idrolab.model.ws20.Allegati getArticoloAllegatiWS20(String siglaMarca, List<String> terminiLs){
		it.cascino.idrolab.model.ws20.Allegati allegati = null;
		//		log.info("[ " + "getArticoloAllegatiWS20");
		try{
			webResource = client.resource(urlWs + "articoli_allegati/" + siglaMarca + "/" + terminiLsToUrl(terminiLs) + ".xml");
			
			manageCallWS();
			
			jaxbContext = JAXBContext.newInstance(it.cascino.idrolab.model.ws20.Allegati.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			allegati = (it.cascino.idrolab.model.ws20.Allegati)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(allegati);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getArticoloAllegatiWS20");
		return allegati;
	}
	
	public void getMarcaAllegatiWS21(String siglaMarca, Filtro filtro){
//		log.info("[ " + "getMarcaAllegatiWS21");
		try{
			webResource = client.resource(urlWs + "listaallegatimarca/" + siglaMarca + ".xml" + filtro.filterToUrl());
			
			manageCallWS();
			
			jaxbContext = JAXBContext.newInstance(it.cascino.idrolab.model.ws21.Allegati.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			manageConversione(jaxbOutput);
			
			it.cascino.idrolab.model.ws21.Allegati allegati = (it.cascino.idrolab.model.ws21.Allegati)jaxbUnmarshaller.unmarshal(jaxbSaxSource);
//			log.info(allegati);
		}catch(Exception e){
//			log.fatal(e.toString());
		}
//		log.info("] " + "getMarcaAllegatiWS21");
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basePathDownload == null) ? 0 : basePathDownload.hashCode());
		result = prime * result + ((builder == null) ? 0 : builder.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((fileListino == null) ? 0 : fileListino.hashCode());
		result = prime * result + ((fileNameListino == null) ? 0 : fileNameListino.hashCode());
		result = prime * result + ((jaxbContext == null) ? 0 : jaxbContext.hashCode());
		result = prime * result + ((jaxbOutput == null) ? 0 : jaxbOutput.hashCode());
		result = prime * result + ((jaxbReader == null) ? 0 : jaxbReader.hashCode());
		result = prime * result + ((jaxbSaxSource == null) ? 0 : jaxbSaxSource.hashCode());
		result = prime * result + ((jaxbUnmarshaller == null) ? 0 : jaxbUnmarshaller.hashCode());
//		result = prime * result + ((log == null) ? 0 : log.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((response == null) ? 0 : response.hashCode());
		result = prime * result + ((urlWs == null) ? 0 : urlWs.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((webResource == null) ? 0 : webResource.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		IdroLab other = (IdroLab)obj;
		if(basePathDownload == null) {
			if(other.basePathDownload != null)
				return false;
		}else if(!basePathDownload.equals(other.basePathDownload))
			return false;
		if(builder == null) {
			if(other.builder != null)
				return false;
		}else if(!builder.equals(other.builder))
			return false;
		if(client == null) {
			if(other.client != null)
				return false;
		}else if(!client.equals(other.client))
			return false;
		if(fileListino == null) {
			if(other.fileListino != null)
				return false;
		}else if(!fileListino.equals(other.fileListino))
			return false;
		if(fileNameListino == null) {
			if(other.fileNameListino != null)
				return false;
		}else if(!fileNameListino.equals(other.fileNameListino))
			return false;
		if(jaxbContext == null) {
			if(other.jaxbContext != null)
				return false;
		}else if(!jaxbContext.equals(other.jaxbContext))
			return false;
		if(jaxbOutput == null) {
			if(other.jaxbOutput != null)
				return false;
		}else if(!jaxbOutput.equals(other.jaxbOutput))
			return false;
		if(jaxbReader == null) {
			if(other.jaxbReader != null)
				return false;
		}else if(!jaxbReader.equals(other.jaxbReader))
			return false;
		if(jaxbSaxSource == null) {
			if(other.jaxbSaxSource != null)
				return false;
		}else if(!jaxbSaxSource.equals(other.jaxbSaxSource))
			return false;
		if(jaxbUnmarshaller == null) {
			if(other.jaxbUnmarshaller != null)
				return false;
		}else if(!jaxbUnmarshaller.equals(other.jaxbUnmarshaller))
			return false;
//		if(log == null) {
//			if(other.log != null)
//				return false;
//		}else if(!log.equals(other.log))
//			return false;
		if(password == null) {
			if(other.password != null)
				return false;
		}else if(!password.equals(other.password))
			return false;
		if(response == null) {
			if(other.response != null)
				return false;
		}else if(!response.equals(other.response))
			return false;
		if(urlWs == null) {
			if(other.urlWs != null)
				return false;
		}else if(!urlWs.equals(other.urlWs))
			return false;
		if(username == null) {
			if(other.username != null)
				return false;
		}else if(!username.equals(other.username))
			return false;
		if(webResource == null) {
			if(other.webResource != null)
				return false;
		}else if(!webResource.equals(other.webResource))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "IdroLab [username=" + username + ", password=" + password + ", client=" + client + ", urlWs=" + urlWs + ", webResource=" + webResource + ", builder=" + builder + ", response=" + response + ", jaxbOutput=" + jaxbOutput + ", jaxbContext=" + jaxbContext + ", jaxbUnmarshaller=" + jaxbUnmarshaller + ", jaxbReader=" + jaxbReader + ", jaxbSaxSource=" + jaxbSaxSource + ", basePathDownload=" + basePathDownload + ", fileNameListino=" + fileNameListino + ", fileListino=" + fileListino + "]";
	}
	
	

}
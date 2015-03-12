package it.cascino.dao;

import java.util.List;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import it.cascino.model.Foto;

public interface FotoDao{
//	void salva(Foto f);
	
	String salva(Foto foto, List<UploadedFile> f);
	
	Boolean annullaUpdate(List<UploadedFile> f);
	
	Boolean annullaUpload(List<UploadedFile> f);
	
	Boolean annullaUploadUndef(int t, List<UploadedFile> uf, int u);
	
//	void aggiorna(Foto f);
	
	String aggiorna(Foto foto, List<UploadedFile> f);
	
	void elimina(Foto f, List<UploadedFile> uf);
	
	void elimina(Foto f, int t, List<UploadedFile> uf, int u);
	
	List<Foto> getAll();
	
	List<Foto> getMostraProdotto();
	List<Foto> getMostraLogo();
	List<Foto> getMostraScheda();
	List<Foto> getMostraAltro();
	List<Foto> getMostraIndefinito();
	
	String getSize(Foto f, int t, List<UploadedFile> uf, int u);
	
	String getResolution(Foto f, int t, List<UploadedFile> uf, int u);
	
	String resolvePath(Foto f);
	
	String getFotoname(Foto f, int t, List<UploadedFile> uf, int u);
	
	List<Foto> getFotoListPerSel(Foto f);
	
	String getDirFoto();
	
	void fileUpload(FileUploadEvent event, String type, List<UploadedFile> uf);
	
	List<String> getTagUtilizzati();
	
	// Foto getFotoFromNomeOriginale(String fName);
	
	int getHeightFromResolution(Foto foto, int t, int h, int l, int u);
	
	// ***** inizio Foto *****
	Foto getFotoDaIdFoto(Integer idFoto);
	// ***** fine Foto *****
	
	// ***** inizio Tipi *****
	Foto getFotoTipoDaIdTipo(Integer idTipo);
	
	Foto getFotoTipoDaIdArticolo(Integer idArticolo);
	
	List<Integer> getTipoLsDaIdFoto(Integer idFoto);
	
	Boolean getTipoDiscendeDaTipo(Integer idTipo, Integer idTipoPadre);
	// ***** fine Tipi *****
	
	// ***** inizio Produttori *****
	Foto getFotoProduttoreDaIdProduttore(Integer idProduttore);
	
	Foto getFotoProduttoreDaIdArticolo(Integer idArticolo);
	// ***** fine Produttori *****
	
	// ***** inizio Articoli *****
	Foto getFotoArticoloDaIdArticolo(Integer idArticolo);
	
	List<Foto> getFotoArticoloOrdLsDaIdArticolo(Integer idArticolo);	
	// ***** fine Articoli *****
}

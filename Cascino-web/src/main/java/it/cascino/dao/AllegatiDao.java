package it.cascino.dao;

import java.util.List;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import it.cascino.model.Allegati;

public interface AllegatiDao{
	String salva(Allegati foto, List<UploadedFile> f);
	
	Boolean annullaUpdate(List<UploadedFile> f);
	
	Boolean annullaUpload(List<UploadedFile> f);
	
	Boolean annullaUploadUndef(int t, List<UploadedFile> uf, int u);
	
	String aggiorna(Allegati a, List<UploadedFile> f);
	
	void elimina(Allegati a, List<UploadedFile> uf);
	
	void elimina(Allegati a, int t, List<UploadedFile> uf, int u);
	
	List<Allegati> getAll();
	
	String getSize(Allegati a, int t, List<UploadedFile> uf, int u);
	
	String resolvePath(Allegati a);
	
	String getAllegatoName(Allegati a, int t, List<UploadedFile> uf, int u);
	
	List<Allegati> getAllegatiListPerSel(Allegati a);
	
	String getDirAllegati();
	
	void fileUpload(FileUploadEvent event, String type, List<UploadedFile> uf);
	
	Allegati getAllegatoDaIdAllegato(Integer idAllegato);
	
	List<Integer> getTipoLsDaIdAllegato(Integer idAllegato);
	
	Allegati getAllegatiArticoloDaIdArticolo(Integer idArticolo);
	
	List<Allegati> getAllegatiArticoloOrdLsDaIdArticolo(Integer idArticolo);
}

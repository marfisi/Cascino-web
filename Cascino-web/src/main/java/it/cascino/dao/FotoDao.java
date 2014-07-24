package it.cascino.dao;

import java.util.List;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import it.cascino.model.Foto;

public interface FotoDao{
	void salva(Foto f);
	String salva(List<UploadedFile> f);
	Boolean annullaUpdate(List<UploadedFile> f);
	Boolean annullaUpload(List<UploadedFile> f);
	Boolean annullaUploadUndef(int t, List<UploadedFile> uf, int u);
	void aggiorna(Foto f);
	String aggiorna(Foto foto, List<UploadedFile> f);
	void elimina(Foto f, List<UploadedFile> uf);
	void elimina(Foto f, int t, List<UploadedFile> uf, int u);
	List<Foto> getAll();
	String getSize(Foto f, int t, List<UploadedFile> uf, int u);
	String getResolution(Foto f, int t, List<UploadedFile> uf, int u);
	String resolvePath(Foto f);
	String getFotoname(Foto f, int t, List<UploadedFile> uf, int u);
	List<Foto> getFotoListPerSel(Foto f);
	String getDirFoto();
	void fileUpload(FileUploadEvent event, String type, List<UploadedFile> uf);
	Foto getFotoFromId(Integer idFoto);
//	Foto getFotoFromNomeOriginale(String fName);	
}

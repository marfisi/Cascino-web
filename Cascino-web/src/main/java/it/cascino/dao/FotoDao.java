package it.cascino.dao;

import java.util.List;
import org.primefaces.model.UploadedFile;
import it.cascino.model.Foto;

public interface FotoDao{
	void salva(Foto f);
	String salva(List<UploadedFile> f);
	Boolean annullaUpload(List<UploadedFile> f);
	void aggiorna(Foto f);
	void elimina(Foto f);
	List<Foto> getAll();
	String getSize(Foto f, int t);
	String getResolution(Foto f, int t);
	String resolvePath(Foto f);
	String getFotoname(Foto f, int t);
	List<Foto> getFotoListPerSel(Foto f);
}

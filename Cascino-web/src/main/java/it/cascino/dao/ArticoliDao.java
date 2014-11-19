package it.cascino.dao;

import java.util.List;
import it.cascino.model.Articoli;
import it.cascino.model.Foto;

public interface ArticoliDao{
	void salva(Articoli t,  List<Foto> fotoPLtarget);
	
	void aggiorna(Articoli t,  List<Foto> fotoPLtarget);
	
	void elimina(Articoli t);
	
	List<Articoli> getAll();
	
	List<String> getArticoliAutoCompleteLs(String str);
	
	// List<String> getProduttoriAutoCompleteLs(String str);
}

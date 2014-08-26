package it.cascino.dao;

import java.util.List;
import it.cascino.model.Articoli;
import it.cascino.model.Foto;

public interface ArticoliDao{
	void salva(Articoli t);
	
	void aggiorna(Articoli t);
	
	void elimina(Articoli t);
	
	List<Articoli> getAll();
	
	Foto getFotoDaArticolo(Integer idArticolo);
	List<Foto> getFotoOrdLsDaArticolo(Integer idArticolo);
	
	List<String> getArticoliAutoCompleteLs(String str);
	
//	List<String> getProduttoriAutoCompleteLs(String str);
}

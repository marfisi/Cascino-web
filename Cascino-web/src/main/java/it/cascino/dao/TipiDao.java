package it.cascino.dao;

import java.util.List;
import it.cascino.model.Tipi;

public interface TipiDao{
	
	void salva(Tipi t);

//	void aggiorna(Tipi t, List<Tipi> tipiLs);
	void aggiorna(Tipi t);
	
	void elimina(Tipi t);
	
	List<Tipi> getAll();	
}

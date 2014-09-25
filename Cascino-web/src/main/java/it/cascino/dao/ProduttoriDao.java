package it.cascino.dao;

import java.util.List;
import it.cascino.model.Produttori;

public interface ProduttoriDao{
	void salva(Produttori p);
	
	void aggiorna(Produttori p);
	
	void elimina(Produttori p);
	
	List<Produttori> getAll();
	
	Produttori getProduttoreDaIdProduttore(Integer idProduttore);
	
	String getNomeProduttoreDaIdArticolo(Integer idArticolo);
}

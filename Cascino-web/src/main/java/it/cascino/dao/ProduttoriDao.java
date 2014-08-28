package it.cascino.dao;

import java.util.List;
import it.cascino.model.Produttori;
import it.cascino.model.Foto;

public interface ProduttoriDao{
	void salva(Produttori p);
	
	void aggiorna(Produttori p);
	
	void elimina(Produttori p);
	
	List<Produttori> getAll();
	
	Produttori getProduttoreDaIdProduttore(Integer idProduttore);
	
	Foto getFotoProduttoreDaIdProduttore(Integer idProduttore);
	
	Foto getFotoProduttoreDaIdArticolo(Integer idArticolo);
	
	String getNomeProduttoreDaIdArticolo(Integer idArticolo);
}

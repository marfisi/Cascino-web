package it.cascino.dao;

import java.util.List;
import it.cascino.model.Produttori;
import it.cascino.model.Foto;

public interface ProduttoriDao{
	void salva(Produttori t);
	void aggiorna(Produttori t);
	void elimina(Produttori t);
	List<Produttori> getAll();
	
	Foto getFotoDaArticolo(Integer idArticolo);
	String getNomeDaArticolo(Integer idArticolo);
}
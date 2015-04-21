package it.cascino.dao;

import java.util.List;
import it.cascino.model.Caratteristiche;
import it.cascino.model.Foto;

public interface CaratteristicheDao{
	void salva(Caratteristiche c);
	
	void aggiorna(Caratteristiche c);
	
	void elimina(Caratteristiche c);
	
	List<Caratteristiche> getAll();
	
	Caratteristiche getCaratteristicaPerId(Integer idCaratteristica);
	
	List<Caratteristiche> getCaratteristicheListPerTipo(Integer idTipo);
	
	List<Caratteristiche> getCaratteristicheListPerArticolo(Integer idArticolo);
	
//	List<Caratteristiche> getCaratteristicheListPerArticoliSimili(Integer idArticolo);
	List<Caratteristiche> getCaratteristicheListPerArticoliSimili(List<Foto> fotoPerArticoloLs);
	
	List<String> getCaratteristicheClasseAutoCompleteLs(String str);
	List<String> getCaratteristicheUnitaMisuraAutoCompleteLs(String str, String classe);
}

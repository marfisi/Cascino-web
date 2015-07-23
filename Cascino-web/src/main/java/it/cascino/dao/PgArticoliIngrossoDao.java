package it.cascino.dao;

import java.util.List;
import it.cascino.model.PgArticoliIngrosso;

public interface PgArticoliIngrossoDao{
	List<PgArticoliIngrosso> getAll();
	
	PgArticoliIngrosso getArticoloDaIdArticolo(Integer idArticolo);
}

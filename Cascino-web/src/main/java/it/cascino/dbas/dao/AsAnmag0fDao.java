package it.cascino.dbas.dao;

import java.util.List;
import it.cascino.dbas.model.AsAnmag0f;

public interface AsAnmag0fDao{
	List<AsAnmag0f> getAll();
	
//	void salva(AsAnmag0f a);
//	
//	void aggiorna(AsAnmag0f a);
//	
//	void elimina(AsAnmag0f a);

	AsAnmag0f getArticoloDaMcoda(String mcoda);
	
	List<AsAnmag0f> getArticoliIngrosso();
	
	List<AsAnmag0f> getArticoliDaMcomp(String mcomp);
	
	Boolean getSeArticoloHaQuestoMcomp(String mcoda, String mcomp);
}

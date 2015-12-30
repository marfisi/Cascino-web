package it.cascino.idrolab.dao;

//import java.util.List;
import it.cascino.idrolab.model.IdroLab;

public interface EliminareIdroLabDao{
//	List<AsAnmag0f> getAll();
	
	void salva(IdroLab o);
	
	void aggiorna(IdroLab o);
	
	void elimina(IdroLab o);

//	AsAnmag0f getArticoloDaMcoda(String mcoda);
	
	it.cascino.idrolab.model.ws1_19.Articolo getArticoloPerCodArtWS1(String siglaMarca, String codiceArticolo);
}

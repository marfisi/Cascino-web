package it.cascino.idrolab.dbpg.dao;

import java.util.List;
import it.cascino.idrolab.dbpg.model.PgMarche;

public interface PgMarcheDao{
	void salva(PgMarche o);
	
	void aggiorna(PgMarche o);
	
	void elimina(PgMarche o);
	
	List<PgMarche> getAll();
	
	PgMarche getMarcaDaId(Integer idObj);
	
	PgMarche getMarcaDaIdMarca(String idObj);

	PgMarche getMarcaDaMcofo(Integer idObj);

	PgMarche getMarcaDaPortalefotoIdProduttore(Integer idObj);
}
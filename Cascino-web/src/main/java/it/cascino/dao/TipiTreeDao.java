package it.cascino.dao;

import java.util.List;
import org.primefaces.model.TreeNode;
import it.cascino.model.Foto;
import it.cascino.model.Tipi;

public interface TipiTreeDao{
	void salva(TreeNode n);
	void aggiorna(TreeNode n);
	void elimina(TreeNode n);
	List<Tipi> getAll();
	
	Foto getFoto(Integer idArticolo);
	String getNome(Integer idArticolo);
}

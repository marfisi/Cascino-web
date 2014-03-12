package it.cascino.dao;

import java.util.List;
import it.cascino.h8.entity.Foto;

public interface FotoDao{
	Foto getForName(String fotoName);
	
	List<Foto> getAll();
	
	void insertFoto(Foto foto);
}

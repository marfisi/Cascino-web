package it.cascino.dbas.dao;

import java.util.List;
import it.cascino.dbas.model.AsTabe20f;

public interface AsTabe20fDao{
	List<AsTabe20f> getAll();

	AsTabe20f getMarchio(String tbele);
}

package it.cascino.dbas.dao;

import java.util.List;
import it.cascino.dbas.model.AsCarat;

public interface AsCaratDao{
	List<AsCarat> getCaratteristicheDaArticoli(String mcoda);
}

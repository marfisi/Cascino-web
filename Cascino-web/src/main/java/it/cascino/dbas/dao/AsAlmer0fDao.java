package it.cascino.dbas.dao;

import java.util.List;
import it.cascino.dbas.model.AsAlmer0f;

public interface AsAlmer0fDao{
	List<AsAlmer0f> getAll();
	
	AsAlmer0f getDaId(String amset, String amgru, String amsot, String amfam, String amstf, String amst1);
}

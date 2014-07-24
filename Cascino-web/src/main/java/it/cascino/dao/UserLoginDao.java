package it.cascino.dao;

import it.cascino.model.Userloginrole;

public interface UserLoginDao{
	void salva(Userloginrole u);
	void aggiorna(Userloginrole u);
	void elimina(Userloginrole u);
	
	Boolean canAccess(String u, String p);
}

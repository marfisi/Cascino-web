package it.cascino.dao;

import java.util.List;
import it.cascino.model.Users;
import it.cascino.model.Userspermissions;
import it.cascino.model.Usersroles;

public interface UserLoginDao{
	void salva(Users u);
	
	void aggiorna(Users u);
	
	void elimina(Users u);
	
	Boolean canAccess(String u, String p);
	
	String getNomeDaUser(String u);

	String getCognomeDaUser(String u);
	
	List<Usersroles> getRolesDaUser(String u);

	List<Userspermissions> getPermissionsDaUser(String u);
}

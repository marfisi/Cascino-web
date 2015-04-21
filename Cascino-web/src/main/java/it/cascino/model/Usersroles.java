package it.cascino.model;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.jboss.logging.Logger;
import java.sql.Timestamp;

/**
 * The persistent class for the usersroles database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Usersroles.findAll", query = "SELECT ur FROM Usersroles ur"),
		@NamedQuery(name = "Usersroles.findById", query = "SELECT ur FROM Usersroles ur WHERE ur.id = :id"),
		@NamedQuery(name = "Usersroles.findByUserId", query = "SELECT ur FROM Usersroles ur WHERE ur.idUser = :userid")/*,
@NamedQuery(name = "Usersroles.findByUserName", query = "SELECT ur FROM Usersroles ur WHERE ur.idUser in (SELECT u FROM Users u WHERE u.login = :username)")*/
})
public class Usersroles implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	private Integer id;
	private Integer idUser;
	private Integer idRole;
	private Timestamp updtime;
	
	public Usersroles(){
	}
	
	public Usersroles(Integer id, Integer idUser, Integer idRole, Timestamp updtime){
		super();
		this.id = id;
		this.idUser = idUser;
		this.idRole = idRole;
		this.updtime = updtime;
	}
	
	@Id
	@SequenceGenerator(name = "USERSROLES_ID_GENERATOR", sequenceName = "USERSROLES_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERSROLES_ID_GENERATOR")
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	@NotNull
	@Column(name = "user")
	public Integer getIdUser(){
		return this.idUser;
	}
	
	public void setIdUser(Integer idUser){
		this.idUser = idUser;
	}
	
	@NotNull
	@Column(name = "role")
	public Integer getIdRole(){
		return this.idRole;
	}
	
	public void setIdRole(Integer idRole){
		this.idRole = idRole;
	}
	
	@Transient
	@Temporal(TemporalType.TIMESTAMP)
	public Timestamp getUpdtime(){
		return this.updtime;
	}
	
	public void setUpdtime(Timestamp updtime){
		this.updtime = updtime;
	}
	
	// @OneToMany
	// @JoinColumn(name="role", referencedColumnName="id")
	// public List<Userspermissions> getPermissions(){
	// return permissions;
	// }
	//
	// public void setPermissions(List<Userspermissions> permissions){
	// this.permissions = permissions;
	// }
	
	@Override
	public String toString(){
		if(log != null){
			log.info("tmpDEBUGtmp: " + "> " + "toString(" + ")");
			log.info("tmpDEBUGtmp: " + "id: " + id);
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1));
		stringBuilder.append("[");
		if(id != null){
			stringBuilder.append("id=" + id).append(", ");
			stringBuilder.append("user=" + idUser).append(", ");
			stringBuilder.append("role=" + idRole);
		}else{
			stringBuilder.append("id=sconosciuto");
		}
		stringBuilder.append("]");
		if(log != null){
			log.info("tmpDEBUGtmp: " + "< " + "toString");
		}
		return stringBuilder.toString();
	}
	
	@Override
	public boolean equals(Object obj){
		if(log != null){
			log.info("tmpDEBUGtmp: " + "> " + "equals(" + obj + ")");
			log.info("tmpDEBUGtmp: " + "id: " + id);
		}
		if(obj instanceof Usersroles){
			if(this.id == ((Usersroles)obj).id){
				return true;
			}else{
				return false;
			}
		}
		if(log != null){
			log.info("tmpDEBUGtmp: " + "< " + "equals");
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		if(log != null){
			log.info("tmpDEBUGtmp: " + "> " + "hashCode(" + ")");
			log.info("tmpDEBUGtmp: " + "id: " + id);
		}
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		result = prime * result + ((idRole == null) ? 0 : idRole.hashCode());
		if(log != null){
			log.info("tmpDEBUGtmp: " + "< " + "hashCode");
		}
		return result;
	}
}
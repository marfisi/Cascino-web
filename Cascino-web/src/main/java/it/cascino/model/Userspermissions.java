package it.cascino.model;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.jboss.logging.Logger;
import java.sql.Timestamp;

/**
 * The persistent class for the userspermissions database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Userspermissions.findAll", query = "SELECT up FROM Userspermissions up"),
		@NamedQuery(name = "Userspermissions.findById", query = "SELECT up FROM Userspermissions up WHERE up.id = :id"),
		@NamedQuery(name = "Userspermissions.findByRoleId", query = "SELECT up FROM Userspermissions up WHERE up.idRole = :roleid"),
		@NamedQuery(name = "Userspermissions.findByRoleName", query = "SELECT up FROM Userspermissions up WHERE up.idRole in (SELECT ur FROM Usersroles ur WHERE ur.role = :rolename)"),
		@NamedQuery(name = "Userspermissions.findByUserName", query = "SELECT up FROM Userspermissions up WHERE up.idRole in (SELECT ur FROM Usersroles ur WHERE ur.idUser in (SELECT u FROM Users u WHERE u.login = :username))")
})
public class Userspermissions implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	private Integer id;
	private Integer idRole;
	private String azione;
	private Timestamp updtime;
	
	public Userspermissions(){
	}
	
	public Userspermissions(Integer id, Integer idRole, String azione, Timestamp updtime){
		super();
		this.id = id;
		this.idRole = idRole;
		this.azione = azione;
		this.updtime = updtime;
	}
	
	@Id
	@SequenceGenerator(name = "USERSPERMISSIONS_ID_GENERATOR", sequenceName = "USERSPERMISSIONS_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERSPERMISSIONS_ID_GENERATOR")
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	@NotNull
	@Column(name = "role")
	public Integer getIdRole(){
		return this.idRole;
	}
	
	public void setIdRole(Integer idRole){
		this.idRole = idRole;
	}
	
	@NotNull
	public String getAzione(){
		return this.azione;
	}
	
	public void setAzione(String azione){
		this.azione = azione;
	}
	
	@Transient
	@Temporal(TemporalType.TIMESTAMP)
	public Timestamp getUpdtime(){
		return this.updtime;
	}
	
	public void setUpdtime(Timestamp updtime){
		this.updtime = updtime;
	}
	
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
			stringBuilder.append("role=" + idRole).append(", ");
			stringBuilder.append("azione=" + azione);
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
		if(obj instanceof Userspermissions){
			if(this.id == ((Userspermissions)obj).id){
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
		result = prime * result + ((idRole == null) ? 0 : idRole.hashCode());
		result = prime * result + ((azione == null) ? 0 : azione.hashCode());
		if(log != null){
			log.info("tmpDEBUGtmp: " + "< " + "hashCode");
		}
		return result;
	}
}
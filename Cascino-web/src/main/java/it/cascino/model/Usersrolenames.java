package it.cascino.model;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.jboss.logging.Logger;
import java.sql.Timestamp;

/**
 * The persistent class for the usersrolenames database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Usersrolenames.findAll", query = "SELECT urn FROM Usersrolenames urn"),
		@NamedQuery(name = "Usersrolenames.findById", query = "SELECT urn FROM Usersrolenames urn WHERE urn.id = :id"),
		@NamedQuery(name = "Usersrolenames.findByRoleName", query = "SELECT urn FROM Usersrolenames urn WHERE urn.role = :rolename")
})
public class Usersrolenames implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	private Integer id;
	private String role;
	private Timestamp updtime;
	
	public Usersrolenames(){
	}
	
	public Usersrolenames(Integer id, String role, Timestamp updtime){
		super();
		this.id = id;
		this.role = role;
		this.updtime = updtime;
	}
	
	@Id
	@SequenceGenerator(name = "USERSROLENAMES_ID_GENERATOR", sequenceName = "USERSPERMISSIONS_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERSROLENAMES_ID_GENERATOR")
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	@NotNull
	public String getRole(){
		return this.role;
	}
	
	public void setRole(String role){
		this.role = role;
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
			stringBuilder.append("role=" + role).append(", ");
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
		if(obj instanceof Usersrolenames){
			if(this.id == ((Usersrolenames)obj).id){
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
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		if(log != null){
			log.info("tmpDEBUGtmp: " + "< " + "hashCode");
		}
		return result;
	}
}
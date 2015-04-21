package it.cascino.model;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.jboss.logging.Logger;
import java.sql.Timestamp;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
		@NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
		@NamedQuery(name = "Users.findByLogin", query = "SELECT u FROM Users u WHERE u.login = :username"),
		@NamedQuery(name = "Users.findByLoginPsw", query = "SELECT u FROM Users u WHERE u.login = :username and u.password = :password"),
		@NamedQuery(name = "Users.countByLoginPsw", query = "SELECT count(u) FROM Users u WHERE u.login = :username and u.password = :password")
})
public class Users implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	private Integer id;
	private String login;
	private String password;
	private String nome;
	private String cognome;
	private String stato;
	private Timestamp updtime;
	
	// private List<Usersroles> roles;
	
	public Users(){
	}
	
	public Users(Integer id, String login, String password, String nome, String cognome, String stato, Timestamp updtime){
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.stato = stato;
		this.updtime = updtime;
	}
	
	@Id
	@SequenceGenerator(name = "USERS_ID_GENERATOR", sequenceName = "USERS_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_ID_GENERATOR")
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	@NotNull
	@Column(unique = true)
	public String getLogin(){
		return this.login;
	}
	
	public void setLogin(String login){
		this.login = login;
	}
	
	@NotNull
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	@NotNull
	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	@NotNull
	public String getCognome(){
		return cognome;
	}
	
	public void setCognome(String cognome){
		this.cognome = cognome;
	}
	
	@NotNull
	public String getStato(){
		return stato;
	}
	
	public void setStato(String stato){
		this.stato = stato;
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
	// @JoinColumn(name="user", referencedColumnName="id")
	// public List<Usersroles> getRoles(){
	// return roles;
	// }
	//
	// public void setRoles(List<Usersroles> roles){
	// this.roles = roles;
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
			stringBuilder.append("login=" + login).append(", ");
			stringBuilder.append("password=" + password).append(", ");
			stringBuilder.append("nome=" + nome).append(", ");
			stringBuilder.append("cognome=" + cognome).append(", ");
			stringBuilder.append("stato" + stato);
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
		if(obj instanceof Users){
			if(this.id == ((Users)obj).id){
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
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((stato == null) ? 0 : stato.hashCode());
		if(log != null){
			log.info("tmpDEBUGtmp: " + "< " + "hashCode");
		}
		return result;
	}
}
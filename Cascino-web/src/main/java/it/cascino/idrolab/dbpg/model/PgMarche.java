package it.cascino.idrolab.dbpg.model;

import java.io.Serializable;
import javax.persistence.*;
//import javax.xml.bind.annotation.XmlElement;
import org.apache.commons.lang3.StringUtils;
import java.sql.Timestamp;

/**
 * The persistent class for the idrolab.marche database table.
 * 
 */
@Entity(name = "marche")
@Table(name = "marche", schema = "idrolab")
@NamedQueries({
	@NamedQuery(name = "PgMarche.findAll", query = "SELECT o FROM marche o order by o.id asc"),
	@NamedQuery(name = "PgMarche.findById", query = "SELECT o FROM  marche o WHERE o.id = :id"),
	@NamedQuery(name = "PgMarche.findByIdMarca", query = "SELECT o FROM  marche o WHERE o.idMarca = :idMarca"),
	@NamedQuery(name = "PgMarche.findByMcofo", query = "SELECT o FROM  marche o WHERE o.mcofo = :id"),
	@NamedQuery(name = "PgMarche.findByPortalefotoIdProduttore", query = "SELECT o FROM  marche o WHERE o.portalefotoIdProduttore = :id")
//	@NamedQuery(name = "PgMarche.svuota", query = "DELETE FROM  articoli_ingrosso a WHERE a.id != '0'")
})
public class PgMarche implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String idMarca;
	private String marCodice;
	private String proCodice;
	private String marDescrizione;
	private String marCodiceAngaisa;
	private String selezionata;
	private Integer mcofo;
	private Integer portalefotoIdProduttore;
	private Integer gruppoSottogruppo;
	private Timestamp updtime;
	
	public PgMarche(){
	
	}
	
	public PgMarche(Integer id, String idMarca, String marCodice, String proCodice, String marDescrizione, String marCodiceAngaisa, String selezionata, Integer mcofo, Integer portalefotoIdProduttore, Integer gruppoSottogruppo, Timestamp updtime){
		super();
		this.id = id;
		this.idMarca = idMarca;
		this.marCodice = marCodice;
		this.proCodice = proCodice;
		this.marDescrizione = marDescrizione;
		this.marCodiceAngaisa = marCodiceAngaisa;
		this.selezionata = selezionata;
		this.mcofo = mcofo;
		this.portalefotoIdProduttore = portalefotoIdProduttore;
		this.gruppoSottogruppo = gruppoSottogruppo;
		this.updtime = updtime;
	}
	
	@Id
	@SequenceGenerator(name = "MARCHE_ID_GENERATOR", schema = "idrolab", sequenceName = "idrolab.marche_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MARCHE_ID_GENERATOR")
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	@Column(name = "idmarca")
	public String getIdMarca(){
		return idMarca;
	}
	
	public void setIdMarca(String idMarca){
		this.idMarca = idMarca;
	}
	
	@Column(name = "mar_codice")
	public String getMarCodice(){
		return marCodice;
	}
	
	public void setMarCodice(String marCodice){
		this.marCodice = marCodice;
	}
	
	@Column(name = "pro_codice")
	public String getProCodice(){
		return proCodice;
	}
	
	public void setProCodice(String proCodice){
		this.proCodice = proCodice;
	}
	
	@Column(name = "mar_descrizione")
	public String getMarDescrizione(){
		return marDescrizione;
	}
	
	public void setMarDescrizione(String marDescrizione){
		this.marDescrizione = marDescrizione;
	}
	
	@Column(name = "mar_codice_angaisa")
	public String getMarCodiceAngaisa(){
		return marCodiceAngaisa;
	}
	
	public void setMarCodiceAngaisa(String marCodiceAngaisa){
		this.marCodiceAngaisa = marCodiceAngaisa;
	}
	
	public String getSelezionata(){
		return selezionata;
	}
	
	public void setSelezionata(String selezionata){
		this.selezionata = selezionata;
	}
	
	public Integer getMcofo(){
		return mcofo;
	}
	
	public void setMcofo(Integer mcofo){
		this.mcofo = mcofo;
	}
	
	@Column(name = "portalefoto_id_produttore")
	public Integer getPortalefotoIdProduttore(){
		return portalefotoIdProduttore;
	}
	
	public void setPortalefotoIdProduttore(Integer portalefotoIdProduttore){
		this.portalefotoIdProduttore = portalefotoIdProduttore;
	}
	
	@Column(name = "gruppo_sottogruppo")
	public Integer getGruppoSottogruppo(){
		return gruppoSottogruppo;
	}
	
	public void setGruppoSottogruppo(Integer gruppoSottogruppo){
		this.gruppoSottogruppo = gruppoSottogruppo;
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
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gruppoSottogruppo == null) ? 0 : gruppoSottogruppo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idMarca == null) ? 0 : idMarca.hashCode());
		result = prime * result + ((marCodice == null) ? 0 : marCodice.hashCode());
		result = prime * result + ((marCodiceAngaisa == null) ? 0 : marCodiceAngaisa.hashCode());
		result = prime * result + ((marDescrizione == null) ? 0 : marDescrizione.hashCode());
		result = prime * result + ((mcofo == null) ? 0 : mcofo.hashCode());
		result = prime * result + ((portalefotoIdProduttore == null) ? 0 : portalefotoIdProduttore.hashCode());
		result = prime * result + ((proCodice == null) ? 0 : proCodice.hashCode());
		result = prime * result + ((selezionata == null) ? 0 : selezionata.hashCode());
		result = prime * result + ((updtime == null) ? 0 : updtime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof PgMarche) {
			if((this.id == ((PgMarche)obj).id)||(StringUtils.equals(this.idMarca, ((PgMarche)obj).idMarca))) {
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public String toString(){
		return "PgMarche [id=" + id + ", idMarca=" + idMarca + ", marCodice=" + marCodice + ", proCodice=" + proCodice + ", marDescrizione=" + marDescrizione + ", marCodiceAngaisa=" + marCodiceAngaisa + ", selezionata=" + selezionata + ", mcofo=" + mcofo + ", portalefotoIdProduttore=" + portalefotoIdProduttore + ", gruppoSottogruppo=" + gruppoSottogruppo + ", updtime=" + updtime + "]";
	}
}
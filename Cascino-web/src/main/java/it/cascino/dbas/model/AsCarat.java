package it.cascino.dbas.model;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.*;
import org.jboss.logging.Logger;
import it.cascino.dbas.model.pkey.AsCaratPKey;
/**
* The persistent class for the cas_dat/***** database table.
* 
* e' una unione di diverse tabelle delle caratteristiche
* 
*/
@Entity(name="Carat")
//@NamedQueries({
//	@NamedQuery(name = "AsCarat.findByMcoda", query = "SELECT a FROM (SELECT MCODA, MAGRU, MASOT, MAFAM, MASTF, T1.CRTIP, CRVID, CRVAL, CRDES, CRUMI FROM (SELECT MCODA, MAGRU, MASOT, MAFAM, MASTF, ANALAL.CRTIP, CRVID, CRVAL FROM (SELECT * FROM (SELECT MCODA, MAGRU, MASOT, MAFAM, MASTF, RRN(ALB) RIG FROM ANMAG0F ART JOIN ALMER0F ALB ON ART.MADIV = ALB.AMSET AND ART.MAGRU = ALB.AMGRU AND ART.MASOT = ALB.AMSOT AND ART.MAFAM = ALB.AMFAM AND ART.MASTF = ALB.AMSTF WHERE MASTF <> '' AND MCODA IN (SELECT CRCOA FROM CARAR0F)) ANAL JOIN CARAL0F AL ON ANAL.RIG = AL.CRIDA) ANALAL LEFT JOIN (SELECT CRCOA, AR.CRTIP, AR.CRVID, CRVAL FROM CARAR0F AR JOIN CARVL0F VL ON AR.CRTIP = VL.CRTIP AND AR.CRVID = VL.CRVID) ARVL ON ANALAL.MCODA = ARVL.CRCOA AND ANALAL.CRTIP = ARVL.CRTIP) T1 JOIN CARTP0F TP ON T1.CRTIP = TP.CRTIP) a WHERE MCODA = :mcoda ORDER BY MCODA, a.CRTIP")
//})
public class AsCarat implements Serializable{
	private static final long serialVersionUID = 1L;
// 	@NamedQuery(name = "AsAlmer0f.findByMcoda", query = "SELECT a FROM Almer0f a WHERE a.id.amset = :amset and a.id.amgru = :amgru and a.id.amsot = :amsot and a.id.amfam = :amfam and a.id.amstf = :amstf and a.id.amst1 = :amst1")

	/**
	 * Logger
	 */
	// @Transient
	@Inject
	private Logger log;
	
	@EmbeddedId
	private AsCaratPKey id;
	private String magru;
	private String masot;
	private String mafam;
	private String mastf	;
	private String crvid;
	private String crval;
	private String crdes;
	private String crumi;
	
	public AsCarat(){
	}

	public AsCarat(AsCaratPKey id, String magru, String masot, String mafam, String mastf, String crvid, String crval, String crdes, String crumi){
		super();
		this.id = id;
		this.magru = magru;
		this.masot = masot;
		this.mafam = mafam;
		this.mastf = mastf;
		this.crvid = crvid;
		this.crval = crval;
		this.crdes = crdes;
		this.crumi = crumi;
	}

	public AsCaratPKey getId(){
		return id;
	}

	public void setId(AsCaratPKey id){
		this.id = id;
	}

	public String getMagru(){
		return magru;
	}

	public void setMagru(String magru){
		this.magru = magru;
	}

	public String getMasot(){
		return masot;
	}

	public void setMasot(String masot){
		this.masot = masot;
	}

	public String getMafam(){
		return mafam;
	}

	public void setMafam(String mafam){
		this.mafam = mafam;
	}

	public String getMastf(){
		return mastf;
	}

	public void setMastf(String mastf){
		this.mastf = mastf;
	}

	public String getCrvid(){
		return crvid;
	}

	public void setCrvid(String crvid){
		this.crvid = crvid;
	}

	public String getCrval(){
		return crval;
	}

	public void setCrval(String crval){
		this.crval = crval;
	}

	public String getCrdes(){
		return crdes;
	}

	public void setCrdes(String crdes){
		this.crdes = crdes;
	}

	public String getCrumi(){
		return crumi;
	}

	public void setCrumi(String crumi){
		this.crumi = crumi;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crdes == null) ? 0 : crdes.hashCode());
		result = prime * result + ((crumi == null) ? 0 : crumi.hashCode());
		result = prime * result + ((crval == null) ? 0 : crval.hashCode());
		result = prime * result + ((crvid == null) ? 0 : crvid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mafam == null) ? 0 : mafam.hashCode());
		result = prime * result + ((magru == null) ? 0 : magru.hashCode());
		result = prime * result + ((masot == null) ? 0 : masot.hashCode());
		result = prime * result + ((mastf == null) ? 0 : mastf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(log != null){
			log.info("tmpDEBUGtmp: " + "> " + "equals(" + obj + ")");
			log.info("tmpDEBUGtmp: " + "id: " + id);
		}
		if(obj instanceof AsCarat) {
			if(this.id == ((AsCarat)obj).id) {
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
	public String toString(){
		return "AsCarat [id=" + id + ", magru=" + magru + ", masot=" + masot + ", mafam=" + mafam + ", mastf=" + mastf + ", crvid=" + crvid + ", crval=" + crval + ", crdes=" + crdes + ", crumi=" + crumi + "]";
	}


}
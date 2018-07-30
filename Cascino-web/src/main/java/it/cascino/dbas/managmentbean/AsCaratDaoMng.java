package it.cascino.dbas.managmentbean;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;
import it.cascino.dbas.dao.AsCaratDao;
import it.cascino.dbas.model.AsCarat;
import it.cascino.util.DatabaseDB2AS400DS;
import it.cascino.util.Utility;

public class AsCaratDaoMng implements AsCaratDao, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	@DatabaseDB2AS400DS
	@Inject
	private EntityManager emAS;
	
	@Inject
	private UserTransaction utx;
	
	@SuppressWarnings("unchecked")
	public List<AsCarat> getCaratteristicheDaArticoli(String mcoda){
//		mcoda = "BC90142";
		List<AsCarat> o = null;
		try{
			try{
				utx.begin();
				//Query query = emAS.createNamedQuery("AsCarat.findByMcoda");
				String sql = "SELECT * FROM (SELECT MCODA, MAGRU, MASOT, MAFAM, MASTF, T1.CRTIP, CRVID, CRVAL, CRDES, CRUMI FROM (SELECT MCODA, MAGRU, MASOT, MAFAM, MASTF, ANALAL.CRTIP, CRVID, CRVAL FROM (SELECT * FROM (SELECT MCODA, MAGRU, MASOT, MAFAM, MASTF, RRN(ALB) RIG FROM ANMAG0F ART JOIN ALMER0F ALB ON ART.MADIV = ALB.AMSET AND ART.MAGRU = ALB.AMGRU AND ART.MASOT = ALB.AMSOT AND ART.MAFAM = ALB.AMFAM AND ART.MASTF = ALB.AMSTF WHERE MASTF <> '' AND MCODA = :mcoda AND MCODA IN (SELECT CRCOA FROM CARAR0F)) ANAL JOIN CARAL0F AL ON ANAL.RIG = AL.CRIDA) ANALAL LEFT JOIN (SELECT CRCOA, AR.CRTIP, AR.CRVID, CRVAL FROM CARAR0F AR JOIN CARVL0F VL ON AR.CRTIP = VL.CRTIP AND AR.CRVID = VL.CRVID) ARVL ON ANALAL.MCODA = ARVL.CRCOA AND ANALAL.CRTIP = ARVL.CRTIP) T1 JOIN CARTP0F TP ON T1.CRTIP = TP.CRTIP) a WHERE MCODA = :mcoda ORDER BY MCODA, a.CRTIP";
				Query query = emAS.createNativeQuery(sql);
				query.setParameter("mcoda", mcoda);
				o = (List<AsCarat>)query.getResultList();
				//log.info("AAAAAAAAAAAAAAAAAA: " + o.size());
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return o;
	}
}

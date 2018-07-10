package it.cascino.dbas.managmentbean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.jboss.logging.Logger;
import it.cascino.dbas.dao.AsNativeQueryDao;
import it.cascino.util.DatabaseDB2AS400DS;
import it.cascino.util.Utility;

public class AsNativeQueryDaoMng implements AsNativeQueryDao, Serializable{
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
		
	// in trasferimento verso deposito in Arrivo
	public BigDecimal getDaMovtr0f_MtquaDaMtcodAndMtdpa(String mtcod, Integer mtdpa){
		BigDecimal o = null;
		try{
			try{
				utx.begin();
				String sql = "SELECT sum(a.mtqua) FROM Movtr0f a WHERE a.mtcod = :mtcod and a.mtsta = ' ' and a.mtdpa = :mtdpa and a.mtdat > :mtdat";
				Query query = emAS.createNativeQuery(sql);
				query.setParameter("mtcod", mtcod);
				query.setParameter("mtdpa", mtdpa);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
				LocalDateTime dateTime = LocalDateTime.now();
				dateTime = dateTime.minusMonths(6);	// 6 mesi
				query.setParameter("mtdat", dateTime.format(formatter));
				o = (BigDecimal)query.getSingleResult();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return o;
	}
	
	// in trasferimento da deposito in Partenza
	public BigDecimal getDaMovtr0f_MtquaDaMtcodAndMtdpp(String mtcod, Integer mtdpp){
		BigDecimal o = null;
		try{
			try{
				utx.begin();
				String sql = "SELECT sum(a.mtqua) FROM Movtr0f a WHERE a.mtcod = :mtcod and a.mtsta = ' '  and a.mtdpp = :mtdpp and a.mtdat > :mtdat";
				Query query = emAS.createNativeQuery(sql);
				query.setParameter("mtcod", mtcod);
				query.setParameter("mtdpp", mtdpp);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
				LocalDateTime dateTime = LocalDateTime.now();
				dateTime = dateTime.minusDays(7); // 7 giorni;
				query.setParameter("mtdat", dateTime.format(formatter));
				o = (BigDecimal)query.getSingleResult();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			Utility.manageException(e, utx, log);
		}
		return o;
	}
	
	public Timestamp getDaSysdummy1_TimestampAs400(){
		Timestamp o = null;
		try{
			try{
				utx.begin();
				String sql = "select a.ts from (select current timestamp ts from sysibm.sysdummy1) a";
				Query query = emAS.createNativeQuery(sql);
				o = (Timestamp)query.getSingleResult();
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

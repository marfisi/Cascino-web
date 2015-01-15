package it.cascino.util.securety.shiro;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Singleton;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Edward P. Legaspi
 * @since Oct 10, 2012 Produces an instance of Shiro's subject so that it can be
 *        injected.
 */
@Singleton
public class SecurityProducer{
	Logger logger = LoggerFactory.getLogger(SecurityProducer.class);
	private SecurityManager securityManager;
	
	@PostConstruct
	public void init(){
		final String iniFile = "classpath:shiroAAAAAAAAA.ini";
		logger.info("Initializing Shiro INI SecurityManager using " + iniFile);
		securityManager = new IniSecurityManagerFactory(iniFile).getInstance();
		SecurityUtils.setSecurityManager(securityManager);
	}
	
	@Produces
	@Named("securityManager")
	public SecurityManager getSecurityManager(){
		return securityManager;
	}
	
	@Produces
	public Subject getSubject(){
		return SecurityUtils.getSubject();
	}
}
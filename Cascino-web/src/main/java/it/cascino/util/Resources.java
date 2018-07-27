/*
 * JBoss, Home of Professional Open Source Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual contributors by the @authors tag. See the copyright.txt in the distribution for a full
 * listing of individual contributors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package it.cascino.util;

import javax.enterprise.context.RequestScoped;
//import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.logging.Logger;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence context, to CDI beans
 * 
 * <p>
 * Example injection on a managed bean field:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * private EntityManager em;
 * </pre>
 */
public class Resources{
	
	// use @SuppressWarnings to tell IDE to ignore warnings about field not being referenced directly
//	@SuppressWarnings("unused")
//	@Produces
////	@Alternative
//	@PersistenceContext(unitName = "Postgresql")
//    @DatabasePostgresqlDS
//	private EntityManager emPg;
	
	@Produces
//	@Alternative
	@PersistenceContext(unitName = "DB2AS400")
    @DatabaseDB2AS400DS
	private EntityManager emAs;

	
	// @PersistenceUnit(unitName = "primary")
	// protected static EntityManagerFactory emf;
	//
	// @Produces
	// @RequestScoped
	// public EntityManager createEntityManager(){
	// EntityManager em = emf.createEntityManager();
	// em.clear();
	// System.out.println("EntityProducer / createEntityManager - em:" + em);
	// return em;
	// }
	//
	// public void destroyEntityManager(@Disposes EntityManager em){
	// // logger.info("Disposing Entity Manager");
	// System.out.println("EntityProducer / destroyEntityManager - em:" + em);
	// if(em.isOpen()){
	// em.close();
	// }
	// }
	
	@Produces
	public Logger produceLog(InjectionPoint injectionPoint){
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
	
	@Produces
	@RequestScoped
	public FacesContext produceFacesContext(){
		return FacesContext.getCurrentInstance();
	}
}

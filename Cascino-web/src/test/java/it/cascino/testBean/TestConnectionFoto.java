package it.cascino.testBean;


import it.cascino.model.Foto;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class TestConnectionFoto 
{
    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "Cascino-web.war")
            .addPackage(Foto.class.getPackage())
            .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
 
    @PersistenceContext
    EntityManager em;
    
    @Inject
    UserTransaction utx;
 
    @Before
    public void preparePersistenceTest() throws Exception {
//        clearData();
        insertData();
        startTransaction();
    }

//    private void clearData() throws Exception {
//        utx.begin();
//        em.joinTransaction();
//        System.out.println("Dumping old records...");
//        em.createQuery("delete from Game").executeUpdate();
//        utx.commit();
//    }

    private void insertData() throws Exception {
        utx.begin();
        em.joinTransaction();
        System.out.println("Inserting records...");
            em.persist(null);
        utx.commit();
        // clear the persistence context (first-level cache)
        em.clear();
    }

    private void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }
    
    @After
    public void commitTransaction() throws Exception {
        utx.commit();
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void shouldFindAllGamesUsingJpqlQuery() throws Exception 
    {
    	System.out.println("PROVA");
        List<Foto> games = (List<Foto>) em.createNamedQuery("Foto.findAll");

        // then
        System.out.println("Found " + games.size() + " games (using JPQL):");
    }
}

package it.cascino.h8.entity;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public class ContactsBean{
	
	private List<Contact> contacts;
	private Contact contact = new Contact();
	
	@PersistenceUnit(unitName = "perEjb") //perEjb")
	private EntityManagerFactory emf;
	
	@Resource
	private UserTransaction utx;
	
	public List<Contact> getContacts(){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT c FROM Contact c");
		contacts = (List<Contact>)query.getResultList();
		return contacts;
	}
	
	public void salva(ActionEvent event){
		EntityManager em = emf.createEntityManager();
		try{
			utx.begin();
			em.joinTransaction();
			em.persist(contact);
			utx.commit();
			showGrowlInsMessage();
		}catch(Exception e){
			try{
				utx.rollback();
			}catch(IllegalStateException e1){
				showGrowlErrorMessage();
				e1.printStackTrace();
			}catch(SecurityException e1){
				showGrowlErrorMessage();
				e1.printStackTrace();
			}catch(SystemException e1){
				showGrowlErrorMessage();
				e1.printStackTrace();
			}
		}finally{
			em.close();
		}
		contact = null;
	}
	
	public void aggiorna(ActionEvent event){
		EntityManager em = emf.createEntityManager();
		try{
			utx.begin();
			em.joinTransaction();
			em.merge(contact);
			utx.commit();
			showGrowlUpdMessage();
		}catch(Exception e){
			try{
				utx.rollback();
			}catch(IllegalStateException e1){
				showGrowlErrorMessage();
				e1.printStackTrace();
			}catch(SecurityException e1){
				showGrowlErrorMessage();
				e1.printStackTrace();
			}catch(SystemException e1){
				showGrowlErrorMessage();
				e1.printStackTrace();
			}
		}finally{
			em.close();
		}
		contact = null;
	}
	
	public void elimina(ActionEvent event){
		EntityManager em = emf.createEntityManager();
		try{
			utx.begin();
			em.joinTransaction();
			Contact c = em.find(Contact.class, contact.getId());
			em.remove(c);
			utx.commit();
			this.showGrowlDelMessage();
		}catch(Exception e){
			try{
				utx.rollback();
			}catch(IllegalStateException e1){
				showGrowlErrorMessage();
				e1.printStackTrace();
			}catch(SecurityException e1){
				showGrowlErrorMessage();
				e1.printStackTrace();
			}catch(SystemException e1){
				showGrowlErrorMessage();
				e1.printStackTrace();
			}
		}finally{
			em.close();
		}
		contact = null;
	}
	
	public void setContacts(List<Contact> contacts){
		this.contacts = contacts;
	}
	
	public Contact getContact(){
		return contact;
	}
	
	public void setContact(Contact contact){
		this.contact = contact;
	}
	
	private void showGrowlUpdMessage(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Successful", "Contatto aggiornato con successo"));
	}
	
	private void showGrowlInsMessage(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Successful", "Contatto inserito con successo"));
	}
	
	private void showGrowlDelMessage(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Successful", "Contatto eliminato con successo"));
	}
	
	private void showGrowlErrorMessage(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Errore", "Operazione fallita"));
	}
}
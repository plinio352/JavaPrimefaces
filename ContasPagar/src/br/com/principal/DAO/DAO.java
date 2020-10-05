package br.com.principal.DAO;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO {
	
	private static EntityManagerFactory emf = null;

	public EntityManagerFactory getEntityManager(){
		if(emf == null){
			emf = Persistence.createEntityManagerFactory("mysql");			
		}
		
		return emf;
	}
	
	public DAO() {
		
	}
}

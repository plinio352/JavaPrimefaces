package br.com.principal.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.principal.VO.Fornecedor;

public class FornecedorDao extends DAO {
	private EntityManager em = getEntityManager().createEntityManager();
	private Fornecedor vo;
	private String sql = "select object(f) from Fornecedor as f";

	private void reconecte(){
		if(!em.isOpen()){
			em = getEntityManager().createEntityManager();
		}
	}
	
	private void beginTran(){
		if(!em.getTransaction().isActive()){
			em.getTransaction().begin();				
		}	
	}
	
	public void delete(Fornecedor f){
		
		try{
			this.reconecte();
			this.beginTran();
			vo = em.find(Fornecedor.class, f.getId());
			em.remove(vo);			
			em.getTransaction().commit();
			em.close();
		}catch (Exception e) {
			System.out.println("PPS: " + e.getMessage());
			em.getTransaction().rollback();
			em.close();
		}
	}

	public void update(Fornecedor f){
		
		try{
			this.reconecte();
			this.beginTran();
			vo = em.find(Fornecedor.class, f.getId());
			vo.setNome(f.getNome());
			vo.setEndereco(f.getEndereco());
			vo.setTelefone(f.getTelefone());
			vo.setObs(f.getObs());
			
			em.getTransaction().commit();
			em.close();
		}catch (Exception e) {
			System.out.println("PPS: " + e.getMessage());
			em.getTransaction().rollback();
			em.close();
		}
	}
	
	public void salvar(Fornecedor f){
		
		try{
			this.reconecte();
			this.beginTran();
			em.persist(f);
			em.getTransaction().commit();
			em.close();
		}catch (Exception e) {
			System.out.println("PPS: " + e.getMessage());
			em.getTransaction().rollback();
			em.close();
		}
	}
	
	public Fornecedor getReg(int ID){
		return em.find(Fornecedor.class , ID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> getLista(){
		
		List<Fornecedor> lista = null;
		
		try{
			this.reconecte();
			Query q = em.createQuery(this.sql);
			lista = q.getResultList();
		} catch(Exception e){
			System.out.println("PPS: " + e.getMessage());
			em.close();
		}
		
		return lista;

	}

}

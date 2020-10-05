package br.com.principal.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.principal.VO.ContasPagar;

public class ContasPagarDao extends DAO {
	
	private EntityManager em = getEntityManager().createEntityManager();
	private ContasPagar vo;
	private String sql = "select object(c) from ContasPagar as c";

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
	
	public void delete(ContasPagar c){
		
		try{
			this.reconecte();
			this.beginTran();
			vo = em.find(ContasPagar.class, c.getId());
			em.remove(vo);			
			em.getTransaction().commit();
			em.close();
		}catch (Exception e) {
			System.out.println("PPS: " + e.getMessage());
			em.getTransaction().rollback();
			em.close();
		}
	}

	public void update(ContasPagar c){
		
		try{
			this.reconecte();
			this.beginTran();
			vo = em.find(ContasPagar.class, c.getId());
			vo.setDescricao(c.getDescricao());
			vo.setData_venc(c.getData_venc());
			vo.setValor(c.getValor());
			vo.setFornecedor(c.getFornecedor());
			
			em.getTransaction().commit();
			em.close();
		}catch (Exception e) {
			System.out.println("PPS: " + e.getMessage());
			em.getTransaction().rollback();
			em.close();
		}
	}
	
	public void salvar(ContasPagar c){
		
		try{
			this.reconecte();
			this.beginTran();
			em.persist(c);
			em.getTransaction().commit();
			em.close();
		}catch (Exception e) {
			System.out.println("PPS: " + e.getMessage());
			em.getTransaction().rollback();
			em.close();
		}
	}

	public ContasPagar getReg(int ID){
		this.reconecte();
		return em.find(ContasPagar.class , ID);
	}
	
	@SuppressWarnings("unchecked")
	public List<ContasPagar> getLista(){
		
		List<ContasPagar> lista = null;
		
		try{
			this.reconecte();
			Query q = em.createQuery(this.sql);
			lista = q.getResultList();
		} catch(Exception e){
			em.close();
		}
		
		return lista;

	}

}

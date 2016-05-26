package br.com.impacta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.impacta.model.Funcionario;

public class FuncionarioDAO {

	private static FuncionarioDAO instance;
	protected EntityManager entityManager;
	
	private EntityManager getEntityManager(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("impacta_biblioteca");
		if(entityManager == null){
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}
	
	public static FuncionarioDAO getInstance(){
		if(instance == null){
			instance = new FuncionarioDAO();
		}
		return instance;
	}
	
	public FuncionarioDAO (){
		entityManager = getEntityManager();
	}
	
	public void adiciona (Funcionario func){
		entityManager.getTransaction().begin();
		try{
			entityManager.persist(func);
			entityManager.getTransaction().commit();
		}catch(RuntimeException e){
			entityManager.getTransaction().rollback();
			System.out.println("Ocorreu um erro no banco de dados");
		}finally{
			
		}
	}
	
	public void remove (Funcionario func){
		entityManager.getTransaction().begin();
		try{
			entityManager.remove(func);
			entityManager.getTransaction().commit();
			entityManager.close();
		}catch(RuntimeException e){
			entityManager.getTransaction().rollback();
			System.out.println("Ocorreu um erro no banco de dados");
		}finally {
			
		}
	
	}
	
	public void altera (Funcionario func){
		entityManager.getTransaction().begin();
		try{
			entityManager.merge(func);
			entityManager.getTransaction().commit();
		}catch(RuntimeException e){
			entityManager.getTransaction().rollback();
			System.out.println("Ocorreu um erro no banco de dados");
		}finally{
		
		}
	
	}
	
	public List<Funcionario> getLista(){
		Query query = entityManager.createQuery("SELECT f FROM Funcionario as f");
		return query.getResultList();
	}
	
	public List<Funcionario> getListaId(Funcionario func){
		Query query = entityManager.createQuery("SELECT f FROM Funcionario as f WHERE id = " + func.getId());
		return query.getResultList();
	}
	
	public List<Funcionario> login(Funcionario func){
		Query query = entityManager.createQuery("SELECT f FROM Funcionario as f WHERE f.usuario = :usuario and f.senha = :senha");
		query.setParameter("usuario",func.getUsuario() );
		query.setParameter("senha",func.getSenha() );
		return query.getResultList();
	}

	
}

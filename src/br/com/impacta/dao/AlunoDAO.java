package br.com.impacta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.primefaces.component.datatable.DataTable;

import br.com.impacta.model.Aluno;
import br.com.impacta.model.Funcionario;

public class AlunoDAO {
	
	private static AlunoDAO instance ;
	private EntityManager entityManager;
	
	public AlunoDAO(){
		entityManager = getEntityManager();
	}
	
	public EntityManager getEntityManager(){
		EntityManagerFactory factory =  Persistence.createEntityManagerFactory("impacta_biblioteca");
		if(entityManager==null){
			entityManager = factory.createEntityManager();
		}
		
		return entityManager;
	}
	
	public static AlunoDAO getInstance(){
		if(instance == null){
			instance = new AlunoDAO();
		}
		return instance;
	}
	
	public void adiciona (Aluno aluno){
		entityManager.getTransaction().begin();
		try{
			entityManager.persist(aluno);
			entityManager.getTransaction().commit();
		}catch(RuntimeException e){
			entityManager.getTransaction().rollback();
			System.out.println("Ocorreu um erro no banco de dados");
		}finally{

		}
	}
	
	public void remove (Aluno aluno){
		entityManager.getTransaction().begin();
		try{
			entityManager.remove(aluno);
			entityManager.getTransaction().commit();
		}catch(RuntimeException e){
			entityManager.getTransaction().rollback();
			System.out.println("Ocorreu um erro no banco de dados");
		} finally{
			
		}
		
	
	}
	
	public void altera(Aluno aluno){
		entityManager.getTransaction().begin();
		try{
			entityManager.persist(aluno.getId());;
			entityManager.getTransaction().commit();
		}catch(RuntimeException e){
			entityManager.getTransaction().rollback();
			System.out.println("Ocorreu um erro no banco de dados");
		}
		finally{
	
		}
	}
	
	public List<Aluno> getLista(){
		Query query = entityManager.createQuery("SELECT a FROM Aluno as a");
		return query.getResultList();
	}
	
	public List<Aluno> getListaId(Aluno aluno){
		Query query = entityManager.createQuery("SELECT a FROM Aluno as a WHERE id = " + aluno.getId());
		return query.getResultList();
	}
	
	public List<Aluno> login(Aluno aluno){
		Query query = entityManager.createQuery("SELECT a FROM Aluno as a WHERE a.usuario = :ra and a.senha = :senha");
		query.setParameter("ra",aluno.getRa() );
		query.setParameter("senha",aluno.getSenha() );
		return query.getResultList();
	}

}

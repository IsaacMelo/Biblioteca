package br.com.impacta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.impacta.model.Aluno;

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
	
	public boolean salvar (Aluno aluno){
			entityManager.getTransaction().begin();
			entityManager.persist(aluno);
			entityManager.getTransaction().commit();
			return true;
	}
	
	public boolean remover (Aluno aluno){
		entityManager.getTransaction().begin();
		entityManager.remove(aluno);
		entityManager.getTransaction().commit();
		return true;
	}
	
	
	public List<Aluno> consulta(){
		Query query = entityManager.createQuery("SELECT a FROM Aluno as a");
		return query.getResultList();
	}
	
	public List<Aluno> pesquisar(String pesquisa){
		Query query = entityManager.createQuery("SELECT a FROM Aluno as a WHERE nome LIKE %(:nome)%");
		query.setParameter("nome", pesquisa);
		return query.getResultList();
	}


}

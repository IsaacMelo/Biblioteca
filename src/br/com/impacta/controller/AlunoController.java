package br.com.impacta.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.component.datatable.DataTable;

import br.com.impacta.dao.AlunoDAO;
import br.com.impacta.model.Aluno;

@ManagedBean
@RequestScoped
public class AlunoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Aluno aluno;
	private DataModel<Aluno> listaAlunos;
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public AlunoController(){
		this.aluno = new Aluno();
	}
	
	public void removerAluno(){
		AlunoDAO dao = AlunoDAO.getInstance();
		dao.remove(aluno);		
	}
	
	public void alterarAluno(){
		AlunoDAO dao = AlunoDAO.getInstance();
		dao.altera(aluno);		
	}
	
	public String cadastrarAluno (){
		try{
			AlunoDAO dao = AlunoDAO.getInstance();
			dao.adiciona(aluno);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso!","Aluno cadastrado com sucesso"));
			aluno=null;
			return "cadastro_aluno.xhtml";	
		}
		catch(RuntimeException e){
			
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro!","Aconteceu um erro ao cadastrar"));
		return "cadastro_aluno.xhtml";
	}
	
	public DataModel<Aluno> listarAlunos(){
		AlunoDAO dao =AlunoDAO.getInstance();
		listaAlunos = new ListDataModel<Aluno>(dao.getLista());
		return listaAlunos;
	}

	public DataModel<Aluno> getListaAlunos() {
		return listaAlunos;
	}

	public void setListaAlunos(DataModel<Aluno> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}
	
}

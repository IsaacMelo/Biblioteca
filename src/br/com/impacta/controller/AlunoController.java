package br.com.impacta.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;


import br.com.impacta.dao.AlunoDAO;
import br.com.impacta.model.Aluno;

@ManagedBean
@ViewScoped
public class AlunoController implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private String pesquisa;
	private Aluno aluno;
	private List<Aluno> listaAlunos;
	private Aluno alunoSelecionado;


	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getListaAlunos() {
		listaAlunos = consultar();
		return listaAlunos;
	}


	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}
	
	public List<Aluno> consultar(){
		AlunoDAO dao = AlunoDAO.getInstance();
		return dao.consulta();
	}
	
	public void pesquisar(){
		AlunoDAO dao = AlunoDAO.getInstance();
		this.listaAlunos = dao.pesquisar(this.pesquisa);
	}
	
	public void salvar(){
		AlunoDAO dao = AlunoDAO.getInstance();
		dao.salvar(this.aluno);
		System.out.println(this.aluno);
		Mensagem msg = new Mensagem();
		dao.consulta();
		msg.info("Aluno salvo com sucesso!");
	}
	
	public void remover(){
		AlunoDAO dao = AlunoDAO.getInstance();
		dao.remover(this.aluno);
		Mensagem msg = new Mensagem();
		dao.consulta();
		msg.info("Aluno removido com sucesso!");
	}
	
	public void novoAluno(){
		this.aluno = new Aluno();
	}
}

package br.com.impacta.controller;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import br.com.impacta.dao.FuncionarioDAO;
import br.com.impacta.model.Funcionario;

@ManagedBean
@RequestScoped
public class FuncionarioController implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Funcionario func;
	private DataModel<Funcionario> listaFuncionarios;
	
	public FuncionarioController(){
		this.func = new Funcionario();
	}
	

	public Funcionario getFunc() {
		return func;
	}

	public void setFunc(Funcionario func) {
		this.func = func;
	}
	
	public DataModel<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(DataModel<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}
		
	public String cadastrarFuncionario(){
		try{
			FuncionarioDAO dao = FuncionarioDAO.getInstance();
			dao.adiciona(func);	
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso!","Funcionário cadastrado com sucesso"));
			func = null;
			return "cadastro_funcionarios.xhtml";
		}catch(RuntimeException e){
			
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO!","Erro ao cadastrar funcionário"));
		return "cadastro_funcionarios.xhtml";
	}
	
	public void removerFuncionario(){
		FuncionarioDAO dao = FuncionarioDAO.getInstance();
		dao.remove(func);		
	}
	
	public void AlterarFuncionario(){
		FuncionarioDAO dao = FuncionarioDAO.getInstance();
		dao.altera(func);		
	}
	
	public DataModel<Funcionario> listarFuncionarios(){
		FuncionarioDAO dao = FuncionarioDAO.getInstance();
		listaFuncionarios = new ListDataModel(dao.getLista());
		return listaFuncionarios;
	}

}

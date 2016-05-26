package br.com.impacta.model;
import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.primefaces.context.RequestContext;

import br.com.impacta.dao.FuncionarioDAO;

@Entity
public class Funcionario  {
	
	@Id
	@GeneratedValue
	private Long id;
	private String usuario;
	private String senha;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	 public void login(ActionEvent event) {
	        RequestContext context = RequestContext.getCurrentInstance();
	        FacesMessage message = null;
	        boolean loggedIn = false;
	        
	        
	        FuncionarioDAO dao = FuncionarioDAO.getInstance();
	        List<Funcionario> lista = dao.getLista();
	        
	        for(Funcionario f : lista){
	        	if(usuario == f.getUsuario() && senha == f.getSenha() ){
	        		loggedIn = true;
	        	}
	        };
	         
	        if(loggedIn) {
	            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-vindo", usuario);
	            try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("Sistema/principal.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } else {
	            loggedIn = false;
	            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro de Login", "Usuário ou senha inválidos");
	        }
	         
	        FacesContext.getCurrentInstance().addMessage(null, message);
	        context.addCallbackParam("loggedIn", loggedIn);
	        //return "paginas/login.xhtml";
	    }  
}

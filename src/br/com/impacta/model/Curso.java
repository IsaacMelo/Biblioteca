package br.com.impacta.model;

public enum Curso {
	ADS("ADS"),BD("BD");
	
	private String descricao;

	Curso(String curso){
		this.descricao = curso;
	}

	public String getCurso() {
		return descricao;
	}

}

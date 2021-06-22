package com.arch.model;

public enum StakeholderCargo {

	MANAGER("Manager"),
	ARCHITECT("Architect"),
	ANALYST("Analyst"),
	TECHNICIAN("Technician");
	
	private String nome;
	private String valor;

	private StakeholderCargo (String nome) {
		this.nome = nome;
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	public String getValor() {
		return valor = this.name();
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}

package com.arch.model;

public enum StakeholderType {

	USER("User"), OPERATOR("Operator"), ACQUIRER("Acquirer"), OWNER("Owner"), SUPPLIER("Supplier"),
	DEVELOPER("Developer"), BUILDER("Builder"), MAINTAINER("Mantainer");

	private String nome;
	private String valor;

	private StakeholderType(String nome) {
		this.nome = nome;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}

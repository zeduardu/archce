package dev.arch420x0.archce.domain.enums;

public enum ConcernPriority {

	HIGH("High"), MEDIUM("Medium"), LOW("Low");

	private String nome;
	private String valor;

	private ConcernPriority(String nome) {
		this.nome = nome;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}

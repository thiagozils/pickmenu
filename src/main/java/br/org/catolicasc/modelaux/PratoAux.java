package br.org.catolicasc.modelaux;

public class PratoAux {
	

	private Long id;
	private String nome;
	private String descricao;
	private Long categoriaPrato;
	private Double calorias;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getCategoriaPrato() {
		return categoriaPrato;
	}
	public void setCategoriaPrato(Long categoriaPrato) {
		this.categoriaPrato = categoriaPrato;
	}
	public Double getCalorias() {
		return calorias;
	}
	public void setCalorias(Double calorias) {
		this.calorias = calorias;
	}
	
	
	
}
package br.org.catolicasc.models;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Cacheable

public class Prato {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String descricao;
	@ManyToOne
	private CategoriaPrato categoriaPrato;
	private Double calorias;
	
	@ManyToMany(mappedBy = "pratos", cascade = CascadeType.ALL)
	private List<Cardapio> cardapios;
	
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
	
	public CategoriaPrato getCategoriaPrato() {
		return categoriaPrato;
	}
	
	public void setCategoriaPrato(CategoriaPrato categoriaPrato) {
		this.categoriaPrato = categoriaPrato;
	}
	
	public double getCalorias() {
		return calorias;
	}
	

	public List<Cardapio> getCardapios() {
		return cardapios;
	}

	public void setCardapios(List<Cardapio> cardapios) {
		this.cardapios = cardapios;
	}

	public void setCalorias(Double calorias) {
		this.calorias = calorias;
	}
	
	
}
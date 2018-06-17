package br.org.catolicasc.bean;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Cacheable

public class CategoriaRestaurante {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String descricao;
	@OneToMany(mappedBy = "categoriaRestaurante")
	private List<Restaurante> restaurantes;
	
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

}

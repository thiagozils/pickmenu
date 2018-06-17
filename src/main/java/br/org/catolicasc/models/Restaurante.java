package br.org.catolicasc.bean;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Cacheable

public class Restaurante {

	@Id
	@GeneratedValue	
	private Long id;
	private String cidade;
	private String endereco;
	private String telefone;
	@ManyToOne
	private CategoriaRestaurante categoriaRestaurante;
	
	@OneToMany(mappedBy = "restaurante")
	private List<Cardapio> cardapios;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public CategoriaRestaurante getCategoriaRestaurante() {
		return categoriaRestaurante;
	}
	
	public void setCategoriaRestaurante(CategoriaRestaurante categoriaRestaurante) {
		this.categoriaRestaurante = categoriaRestaurante;
	}
	
	

}

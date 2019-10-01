package br.org.catolicasc.modelaux;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



public class RestauranteAux {


	private Long id;
	private String nome;
	private String cidade;
	private String endereco;
	private String telefone;
	private Long categoriaRestaurante;
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
	public Long getCategoriaRestaurante() {
		return categoriaRestaurante;
	}
	public void setCategoriaRestaurante(Long categoriaRestaurante) {
		this.categoriaRestaurante = categoriaRestaurante;
	}
	
	
	


}

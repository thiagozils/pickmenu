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
public class Cardapio {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToMany
	 @JoinTable(
		     joinColumns={@JoinColumn(name="cardapios_id",referencedColumnName="id")},  
		     inverseJoinColumns={@JoinColumn(name="pratos_id", referencedColumnName="id")}
	)
	private List<Prato> pratos;
	
	@ManyToOne
	private Restaurante restaurante;
	
	private String data;
	private double preco;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Prato> getPratos() {
		return pratos;
	}
	
	public void setPratos(List<Prato> pratos) {
		this.pratos = pratos;
	}
	public Restaurante getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}

}
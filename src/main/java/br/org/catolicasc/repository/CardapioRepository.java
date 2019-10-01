package br.org.catolicasc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.catolicasc.models.Cardapio;

public interface CardapioRepository extends JpaRepository<Cardapio,String> {

	Cardapio findById(Long id);
	
	
	List<Cardapio> findByRestaurante(Long id);
	
	
	
}
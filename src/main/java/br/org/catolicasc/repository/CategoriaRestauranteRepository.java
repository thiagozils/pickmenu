package br.org.catolicasc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.catolicasc.models.CategoriaRestaurante;

public interface CategoriaRestauranteRepository extends JpaRepository<CategoriaRestaurante,String> {

	CategoriaRestaurante findById(Long id);
	
}

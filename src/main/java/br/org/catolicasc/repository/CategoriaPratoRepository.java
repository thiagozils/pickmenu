package br.org.catolicasc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.catolicasc.models.CategoriaPrato;

public interface CategoriaPratoRepository extends JpaRepository<CategoriaPrato,String> {

	CategoriaPrato findById(Long id);
	
}
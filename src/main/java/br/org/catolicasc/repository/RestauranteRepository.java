package br.org.catolicasc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.catolicasc.models.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante,String> {

	Restaurante findById(Long id);
	
}

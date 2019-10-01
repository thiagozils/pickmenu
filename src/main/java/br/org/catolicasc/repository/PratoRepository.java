package br.org.catolicasc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.catolicasc.models.Prato;

@Repository
public interface PratoRepository extends JpaRepository<Prato, String> {

	Prato findById(Long id);

}

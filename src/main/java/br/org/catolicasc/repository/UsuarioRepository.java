package br.org.catolicasc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.org.catolicasc.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {

	Usuario findById(Long id);
	
}

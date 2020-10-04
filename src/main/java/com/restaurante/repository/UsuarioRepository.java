package com.restaurante.repository;

import com.restaurante.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findUsuarioByLogin(String login);

    Optional<Usuario> findById(Long id);

    boolean existsByLoginIgnoreCase(String login);

}

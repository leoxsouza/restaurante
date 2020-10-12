package com.restaurante.repository;

import com.restaurante.domain.Usuario;
import com.restaurante.service.dto.DropDownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findUsuarioByLogin(String login);

    Optional<Usuario> findById(Long id);

    boolean existsByLoginIgnoreCase(String login);

    @Query("SELECT new com.restaurante.service.dto.DropDownDTO(u.id, u.pessoa.nome) FROM Usuario u WHERE u.pessoa.tipoPessoa = 'PF'")
    List<DropDownDTO> getClientesDropdown();

    @Query("SELECT u.id FROM Usuario u WHERE u.login = :login")
    Long getUsuarioByLogin(@Param("login") String login);
}

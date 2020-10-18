package com.restaurante.repository;

import com.restaurante.domain.UsuariosRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuariosRoleRepository extends JpaRepository<UsuariosRole, Long> {

    @Query("SELECT ur.id.role.descricaoRole FROM UsuariosRole ur WHERE ur.id.usuario.login = :login")
    List<String> getRolesByLogin(@Param("login") String login);

}

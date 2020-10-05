package com.restaurante.repository;

import com.restaurante.domain.UsuariosRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRoleRepository extends JpaRepository<UsuariosRole, Long> {

}

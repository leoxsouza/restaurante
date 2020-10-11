package com.restaurante.repository;

import com.restaurante.domain.Empresa;
import com.restaurante.service.dto.DropDownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @Query("SELECT new com.restaurante.service.dto.DropDownDTO(e.id, e.nome) FROM Empresa e")
    List<DropDownDTO> getEmpresasDropdown();

}

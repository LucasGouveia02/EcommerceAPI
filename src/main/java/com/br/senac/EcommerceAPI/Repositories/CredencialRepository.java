package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Models.CredencialModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CredencialRepository extends JpaRepository<CredencialModel, Long> {

    @Query("SELECT u FROM CredencialModel u WHERE u.idUsuario = ?1")
    CredencialModel findByUsuario(Long idUsuario);

}

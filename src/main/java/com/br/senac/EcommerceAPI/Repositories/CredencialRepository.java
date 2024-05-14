package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Models.CredencialModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CredencialRepository extends JpaRepository<CredencialModel, Long> {

    @Query("SELECT u FROM CredencialModel u WHERE u.email = ?1")
    CredencialModel findByUsuario(String email);

}

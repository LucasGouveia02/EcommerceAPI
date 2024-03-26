package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Models.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {

    @Query("SELECT u FROM EnderecoModel u WHERE u.cep = ?1")
    EnderecoModel buscaPorCep(String cep);
}

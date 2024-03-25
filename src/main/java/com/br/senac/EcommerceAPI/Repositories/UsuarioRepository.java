package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    @Query("SELECT u FROM UsuarioModel u WHERE u.nome = ?1")
    UsuarioModel buscaPorNome(String nome);
    @Query("SELECT u FROM UsuarioModel u WHERE u.cpf = ?1")
    UsuarioModel buscaPorCPF(String cpf);
}

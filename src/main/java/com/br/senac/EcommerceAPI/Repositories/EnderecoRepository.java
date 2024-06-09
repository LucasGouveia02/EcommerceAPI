package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Models.EnderecoModel;
import com.br.senac.EcommerceAPI.Models.EnderecoUsuario;
import com.br.senac.EcommerceAPI.Models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {

    @Query("SELECT u FROM EnderecoModel u WHERE u.cep = ?1")
    EnderecoModel buscaPorCep(String cep);

    @Query("SELECT e " +
            "FROM EnderecoUsuario e " +
            "WHERE e.id.usuarioId = :usuarioId")
    List<EnderecoUsuario> retornoEnderecoPorUsuario(@Param("usuarioId") UsuarioModel id);

}

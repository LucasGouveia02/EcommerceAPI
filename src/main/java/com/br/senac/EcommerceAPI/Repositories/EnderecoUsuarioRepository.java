package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Models.EnderecoModel;
import com.br.senac.EcommerceAPI.Models.EnderecoUsuario;
import com.br.senac.EcommerceAPI.Keys.EnderecoUsuarioKey;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnderecoUsuarioRepository extends JpaRepository<EnderecoUsuario, EnderecoUsuarioKey> {
    @Transactional
    @Modifying
    @Query("DELETE FROM EnderecoUsuario e WHERE e.id.enderecoId = :enderecoId")
    void deletarEndereco(@Param("enderecoId") EnderecoModel id);
}

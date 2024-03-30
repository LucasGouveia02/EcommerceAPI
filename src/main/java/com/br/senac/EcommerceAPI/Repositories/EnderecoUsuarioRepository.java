package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Models.EnderecoUsuario;
import com.br.senac.EcommerceAPI.Models.EnderecoUsuarioKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoUsuarioRepository extends JpaRepository<EnderecoUsuario, EnderecoUsuarioKey> {
}

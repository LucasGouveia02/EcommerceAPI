package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Models.EnderecoUsuario;
import com.br.senac.EcommerceAPI.Models.EnderecoUsuarioKey;
import com.br.senac.EcommerceAPI.Models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnderecoUsuarioRepository extends JpaRepository<EnderecoUsuario, EnderecoUsuarioKey> {}

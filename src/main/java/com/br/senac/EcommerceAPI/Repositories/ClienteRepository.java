package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    ClienteModel buscaPorNome(String nome);

    ClienteModel buscaPorCPF(String cpf);
}

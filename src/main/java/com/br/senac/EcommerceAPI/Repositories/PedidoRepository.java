package com.br.senac.EcommerceAPI.Repositories;

import com.br.senac.EcommerceAPI.Models.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {

}
